package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.network.packet.s2c.RemovePreggoMobJigglePhysicsS2CPacket;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import dev.dixmk.minepreggo.world.item.IFemaleArmor;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class PreggoMobHelper {
			
	private PreggoMobHelper() {}
	
	// COPY AND TRANSFER DATA - START
	public static void copyOwner(@NonNull TamableAnimal source, @NonNull TamableAnimal target) {
		if (source.isTame() && source.getOwner() != null)
			target.tame((Player) source.getOwner());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<IFemaleEntity>> void copyTamableData(@NonNull E source, @NonNull E target) {		
		target.setBreakBlocks(source.canBreakBlocks());
		target.setCanPickUpLoot(source.canPickUpLoot());	
		target.getTamableData().deserializeNBT(source.getTamableData().serializeNBT());
		target.getGenderedData().deserializeNBT(source.getGenderedData().serializeNBT());
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void copyPregnancyData(@NonNull E source, @NonNull E target) {
		var targetPregnancyData = target.getPregnancyData();
		targetPregnancyData.deserializeNBT(source.getPregnancyData().serializeNBT());	
		targetPregnancyData.resetDaysPassed();
		targetPregnancyData.resetPregnancyTimer();
	}
		
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void copyAllData(@NonNull E source, @NonNull E target) {
		LivingEntityHelper.copyRotation(source, target);
		copyOwner(source, target);
		LivingEntityHelper.copyHealth(source, target);
		EntityHelper.copyName(source, target);
		copyTamableData(source, target);
		LivingEntityHelper.copyMobEffects(source, target);
		transferInventory(source, target);
		copyPregnancyData(source, target);
	}
	// COPY AND TRANSFER DATA - END
	
	
	
	// INVENTORY OPERATIONS - START
	public static<E extends PreggoMob & ITamablePreggoMob<?>> void transferInventory(E source, E target) throws IllegalArgumentException {
		InventoryHelper.transferAll(source.getInventory(), target.getInventory());
		InventoryHelper.syncToVanilla(target);
	}
	
	public static<T extends PreggoMob & ITamablePreggoMob<?>> void syncFromEquipmentSlotToInventory(@NonNull T preggoMob) {
		if (!preggoMob.level().isClientSide) {
			InventoryHelper.syncFromVanilla(preggoMob);
		}
	}
	
	public static<T extends PreggoMob & ITamablePreggoMob<?>> void syncFromInventoryToEquipmentSlot(@NonNull T preggoMob) {
	    if (!preggoMob.level().isClientSide) {
	    	InventoryHelper.syncToVanillaIfChanged(preggoMob);
	    }
	}
	
    /**
     * Stores an item from an entity into the extra slots of the inventory
     * @param preggoMob The mob that will pick up the item
     * @param itemEntity The item entity to pick up
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> void storeItemInExtraSlots(@NonNull E preggoMob, @NonNull ItemEntity itemEntity) {
        if (preggoMob.level().isClientSide) return;
        
        Inventory inventory = preggoMob.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        ItemStack originalItemStack = itemEntity.getItem();
        int originalCount = originalItemStack.getCount();
        
        int baseIndex = slotMapper.getMappedSlotsCount();
        int extraCount = slotMapper.getExtraSlots();
        
        for (int i = 0; i < extraCount && !originalItemStack.isEmpty(); i++) {
            int slotIndex = baseIndex + i;
            originalItemStack = inventory.getHandler().insertItem(slotIndex, originalItemStack, false);
        }
        
        if (originalItemStack.getCount() != originalCount) {
            preggoMob.level().playSound(
                null, 
                BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()),
                ForgeRegistries.SOUND_EVENTS.getValue(MinepreggoHelper.withDefaultNamespace("entity.item.pickup")),
                SoundSource.AMBIENT, 
                0.75F, 
                0.75F
            );
            
            if (originalItemStack.isEmpty()) {
                itemEntity.discard();
            } else {
                itemEntity.setItem(originalItemStack);
            }
        }
    }
    
    /**
     * Stores an item in the extra slots or drops it if it doesn't fit
     * @param preggoMob The mob that will try to store the item
     * @param itemStack The item to store
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> void storeItemInExtraSlotsOrDrop(@NonNull E preggoMob, @NonNull ItemStack itemStack) {
        if (preggoMob.level().isClientSide) return;
        
        Inventory inventory = preggoMob.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        ItemStack originalItemStack = itemStack.copy();
        
        // Intentar insertar en los slots extra
        int baseIndex = slotMapper.getMappedSlotsCount();
        int extraCount = slotMapper.getExtraSlots();
        
        for (int i = 0; i < extraCount && !originalItemStack.isEmpty(); i++) {
            int slotIndex = baseIndex + i;
            originalItemStack = inventory.getHandler().insertItem(slotIndex, originalItemStack, false);
        }
        
        // Si sobró algo o no se insertó nada, dropear
        if (!originalItemStack.isEmpty()) {
            EntityHelper.dropItemStack(preggoMob, originalItemStack);
        }
    }
    
    /**
     * Transfers an item from the inventory to the mob's hand
     * @param preggoMob The mob
     * @param item The type of item to search for
     * @param hand The hand where to place the item
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> void transferItemFromInventoryToHand(
            @NonNull E preggoMob, 
            @NonNull Item item, 
            @NonNull InteractionHand hand) {
        
        if (preggoMob.level().isClientSide) return;
        
        Inventory inventory = preggoMob.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        ItemStack foundStack = ItemStack.EMPTY;
        int foundSlotIndex = InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX;
        
        int baseIndex = slotMapper.getMappedSlotsCount();
        int extraCount = slotMapper.getExtraSlots();
        
        for (int i = 0; i < extraCount; i++) {
            int slotIndex = baseIndex + i;
            ItemStack stack = inventory.getHandler().getStackInSlot(slotIndex);
            
            if (stack.is(item)) {
                foundStack = stack;
                foundSlotIndex = slotIndex;
                break;
            }
        }
        
        if (!foundStack.isEmpty() && foundSlotIndex != InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
            inventory.getHandler().setStackInSlot(foundSlotIndex, ItemStack.EMPTY);
            replaceAndDropItemstackInHand(preggoMob, hand, foundStack);
        }
    }
    
    /**
     * Transfers a specific item from a range of slots to the hand
     * @param preggoMob The mob
     * @param item The type of item to search for
     * @param hand The hand where to place the item
     * @param searchInExtraSlotsOnly Whether to search only in extra slots (true) or in all slots (false)
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> void transferItemFromInventoryToHand(
            @NonNull E preggoMob, 
            @NonNull Item item, 
            @NonNull InteractionHand hand,
            boolean searchInExtraSlotsOnly) {
        
        if (preggoMob.level().isClientSide) return;
        
        Inventory inventory = preggoMob.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        ItemStack foundStack = ItemStack.EMPTY;
        int foundSlotIndex = InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX;
        
        if (searchInExtraSlotsOnly) {
            int baseIndex = slotMapper.getMappedSlotsCount();
            int extraCount = slotMapper.getExtraSlots();
            
            for (int i = 0; i < extraCount; i++) {
                int slotIndex = baseIndex + i;
                ItemStack stack = inventory.getHandler().getStackInSlot(slotIndex);
                
                if (stack.is(item)) {
                    foundStack = stack;
                    foundSlotIndex = slotIndex;
                    break;
                }
            }
        } else {
            int totalSlots = slotMapper.getTotalSlots();
            
            for (int slotIndex = 0; slotIndex < totalSlots; slotIndex++) {
                ItemStack stack = inventory.getHandler().getStackInSlot(slotIndex);
                
                if (stack.is(item)) {
                    foundStack = stack;
                    foundSlotIndex = slotIndex;
                    break;
                }
            }
        }
        
        if (!foundStack.isEmpty() && foundSlotIndex != InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
            inventory.getHandler().setStackInSlot(foundSlotIndex, ItemStack.EMPTY);
            replaceAndDropItemstackInHand(preggoMob, hand, foundStack);
        }
    }
    
    /**
     * Adds an item to the mob's inventory (in any available slot)
     * @param preggoMob The mob
     * @param itemEntity The item entity to add
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> void addItemToInventory(@NonNull E preggoMob, @NonNull ItemEntity itemEntity) {
        if (preggoMob.level().isClientSide) return;
        
        Inventory inventory = preggoMob.getInventory();
        
        ItemStack originalItemStack = itemEntity.getItem();
        ItemStack remainder = ItemHandlerHelper.insertItemStacked(inventory.getHandler(), originalItemStack, false);
        
        if (remainder.getCount() != originalItemStack.getCount()) {
            if (remainder.isEmpty()) {
                itemEntity.discard();
            } else {
                itemEntity.setItem(remainder);
            }
        }
    }
    
    /**
     * Replaces the item in the mob's hand and drops the previous item if it existed
     * @param preggoMob The mob
     * @param hand The hand to replace
     * @param itemStack The new item
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> void replaceAndDropItemstackInHand(
            @Nonnull E preggoMob, 
            @NonNull InteractionHand hand, 
            @Nonnull ItemStack itemStack) {
        
        if (preggoMob.level().isClientSide) return;
        
        Inventory inventory = preggoMob.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        InventorySlot inventorySlot = hand == InteractionHand.MAIN_HAND 
            ? InventorySlot.MAINHAND 
            : InventorySlot.OFFHAND;
        
        if (!slotMapper.hasSlot(inventorySlot)) {
            return;
        }
        
        int slotIndex = slotMapper.getSlotIndex(inventorySlot);
        
        if (slotIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
            return;
        }
        
        ItemStack currentStack = inventory.getHandler().getStackInSlot(slotIndex);
        if (!currentStack.isEmpty()) {
            removeAndDropItemStackFromEquipmentSlot(preggoMob, inventorySlot);
        }
        
        inventory.getHandler().setStackInSlot(slotIndex, itemStack.copy());
        
        if (inventorySlot.vanilla.isPresent()) {
            preggoMob.setItemSlot(inventorySlot.vanilla.get(), itemStack.copy());
        }
    }
    
    /**
     * Removes and drops the item from an equipment slot
     * @param preggoMob The mob
     * @param inventorySlot The slot to empty
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> void removeAndDropItemStackFromEquipmentSlot(
            @NonNull E preggoMob, 
            @NonNull InventorySlot inventorySlot) {
        
        if (preggoMob.level().isClientSide) return;
        
        Inventory inventory = preggoMob.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        int slotIndex = slotMapper.getSlotIndex(inventorySlot);
        
        if (slotIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
            return;
        }
        
        ItemStack stack = inventory.getHandler().getStackInSlot(slotIndex);
        
        if (!stack.isEmpty() && EntityHelper.dropItemStack(preggoMob, stack)) {
        	inventory.getHandler().setStackInSlot(slotIndex, ItemStack.EMPTY);   
        	inventorySlot.vanilla.ifPresent(equipmentSlot -> preggoMob.setItemSlot(equipmentSlot, ItemStack.EMPTY));
        }
    }
    
    /**
     * Removes and drops all items from the inventory
     * @param preggoMob The mob
     * @param includeEquipment Whether to also drop equipment
     * @param includeExtraSlots Whether to also drop extra slots
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> void dropAllInventory(
            @NonNull E preggoMob, 
            boolean includeEquipment, 
            boolean includeExtraSlots) {
        
        if (preggoMob.level().isClientSide) return;
        
        Inventory inventory = preggoMob.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        if (includeEquipment) {
            for (InventorySlot slot : slotMapper.getAvailableSlots()) {
                if (slot.type == InventorySlot.Type.EQUIPMENT) {
                    removeAndDropItemStackFromEquipmentSlot(preggoMob, slot);
                }
            }
        }
        
        for (InventorySlot slot : slotMapper.getAvailableSlots()) {
            if (slot.type == InventorySlot.Type.INVENTORY) {
                int slotIndex = slotMapper.getSlotIndex(slot);
                ItemStack stack = inventory.getHandler().getStackInSlot(slotIndex);
                
                if (!stack.isEmpty() && EntityHelper.dropItemStack(preggoMob, stack)) {
                        inventory.getHandler().setStackInSlot(slotIndex, ItemStack.EMPTY);
                    }
                
            }
        }
        
        if (includeExtraSlots) {
            int baseIndex = slotMapper.getMappedSlotsCount();
            int extraCount = slotMapper.getExtraSlots();
            
            for (int i = 0; i < extraCount; i++) {
                int slotIndex = baseIndex + i;
                ItemStack stack = inventory.getHandler().getStackInSlot(slotIndex);
                
                if (!stack.isEmpty() && EntityHelper.dropItemStack(preggoMob, stack)) {
                        inventory.getHandler().setStackInSlot(slotIndex, ItemStack.EMPTY);
                }      
            }
        }
    }
    
    /**
     * Counts how many items of a specific type are in the inventory
     * @param preggoMob The mob
     * @param item The type of item to count
     * @param searchInExtraSlotsOnly Whether to search only in extra slots
     * @return The total number of items found
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> int countItemsInInventory(
            @NonNull E preggoMob, 
            @NonNull Item item,
            boolean searchInExtraSlotsOnly) {
        
        Inventory inventory = preggoMob.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        int count = 0;
        
        if (searchInExtraSlotsOnly) {
            int baseIndex = slotMapper.getMappedSlotsCount();
            int extraCount = slotMapper.getExtraSlots();
            
            for (int i = 0; i < extraCount; i++) {
                int slotIndex = baseIndex + i;
                ItemStack stack = inventory.getHandler().getStackInSlot(slotIndex);
                
                if (stack.is(item)) {
                    count += stack.getCount();
                }
            }
        } else {
            int totalSlots = slotMapper.getTotalSlots();
            
            for (int slotIndex = 0; slotIndex < totalSlots; slotIndex++) {
                ItemStack stack = inventory.getHandler().getStackInSlot(slotIndex);
                
                if (stack.is(item)) {
                    count += stack.getCount();
                }
            }
        }
        
        return count;
    }
    
    /**
     * Checks if the inventory has a specific item
     * @param preggoMob The mob
     * @param item The type of item to search for
     * @param searchInExtraSlotsOnly Whether to search only in extra slots
     * @return true if the item was found
     */
    public static <E extends PreggoMob & ITamablePreggoMob<?>> boolean hasItem(
            @NonNull E preggoMob, 
            @NonNull Item item,
            boolean searchInExtraSlotsOnly) {
        
        return countItemsInInventory(preggoMob, item, searchInExtraSlotsOnly) > 0;
    }
	
	// INVENTORY OPERATIONS - END
	
	
	// PREGNANCY SYSTEM - START
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> boolean initPregnancy(@NonNull E preggoMob) {					
		final IFemaleEntity femaleData = preggoMob.getGenderedData();
		
		if (!femaleData.isPregnant()) {
			return false;
		}
		
		Optional<Boolean> gotPregnant = femaleData.getPrePregnancyData()
				.map(prePregnancyData -> {
					final ITamablePregnantPreggoMobData pregnancyData = preggoMob.getPregnancyData();
					final var numOfBabies = prePregnancyData.fertilizedEggs();
					final PregnancyPhase lastPregnancyStage = PregnancySystemHelper.calculateMaxPregnancyPhaseByTotalNumOfBabies(numOfBabies);
					final int totalDays = MinepreggoModConfig.SERVER.getTotalPregnancyDays();
					final var mother = ImmutableTriple.of(preggoMob.getUUID(), preggoMob.getTypeOfSpecies(), preggoMob.getTypeOfCreature());
					final var father = ImmutableTriple.of(Optional.ofNullable(prePregnancyData.fatherId()), prePregnancyData.typeOfSpeciesOfFather(), prePregnancyData.typeOfCreatureOfFather());
					final var map = new MapPregnancyPhase(totalDays, lastPregnancyStage);
					final var womb = new Womb(mother, father, preggoMob.getRandom(), numOfBabies);

					pregnancyData.setLastPregnancyStage(lastPregnancyStage);	
					pregnancyData.setMapPregnancyPhase(map);	
					pregnancyData.setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH); 
					pregnancyData.setWomb(womb);
					pregnancyData.setDaysToGiveBirth(PregnancySystemHelper.calculateDaysToGiveBirth(pregnancyData));	
					
					pregnancyData.resetDaysPassed();
					
					MinepreggoMod.LOGGER.debug("INIT PREGNANCY: class={}, lastPregnancyStage={}, totalDays={}, daysByStage={}, womb={}",
							preggoMob.getClass().getSimpleName(), lastPregnancyStage, totalDays, map, womb);
					
					return true;
				});
		
		return gotPregnant.isPresent() && gotPregnant.get().booleanValue();
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> void initDefaultPregnancy(@NonNull E preggoMob) throws IllegalStateException {				
		final IFemaleEntity femaleData = preggoMob.getGenderedData();
		final ITamablePregnantPreggoMobData pregnancyData = preggoMob.getPregnancyData();
		final var numOfBabies = PregnancySystemHelper.calculateRandomNumOfBabiesByMaxPregnancyPhase(pregnancyData.getLastPregnancyStage(), preggoMob.getRandom());
		
		if (numOfBabies.isEmpty()) {
			preggoMob.discard();
			throw new IllegalStateException("Cannot init default pregnancy: numOfBabies is null");
		}

		femaleData.tryImpregnate(numOfBabies.getAsInt(), ImmutableTriple.of(Optional.empty(), preggoMob.getTypeOfSpecies(), preggoMob.getTypeOfCreature()));	
		initPregnancy(preggoMob);
	}
	
	public static <E extends PreggoMob & ITamablePreggoMob<IFemaleEntity>> boolean initPrePregnancy(@NonNull E preggoMob, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, @Nonnegative int fertilizedEggs) {	
		final IFemaleEntity femaleData = preggoMob.getGenderedData();
		return femaleData.tryImpregnate(fertilizedEggs, father);
	}
	
	public static <E extends PreggoMob & ITamablePregnantPreggoMob> boolean initPregnancyByPotion(@NonNull E preggoMob, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, @Nonnegative int amplifier) {	
		final int numOfBabies = PregnancySystemHelper.calculateNumOfBabiesByPotion(amplifier);	
		final var r1 = initPrePregnancy(preggoMob, father, numOfBabies);	
		final var r2 = initPregnancy(preggoMob);
		
		if (!r1 || !r2) {
			MinepreggoMod.LOGGER.warn("CANNOT INIT PREGNANCY BY POTION: class={}, fatherId={}, speciesOfFather={}, creatureOfFather={}, amplifier={}, numOfBabies={}, initPrePregnancyResult={}, initPregnancyResult={}",
					preggoMob.getClass().getSimpleName(), father.getLeft().orElse(null), father.getMiddle(), father.getRight(), amplifier, numOfBabies, r1, r2);
		}
		
		return r1 && r2;
	}
	
	public static <E extends PreggoMob & ITamablePreggoMob<IFemaleEntity>> boolean initPregnancyBySex(@NonNull E preggoMob, @NonNull ServerPlayer serverPlayer) {	
		if (!preggoMob.isOwnedBy(serverPlayer)) {
			MinepreggoMod.LOGGER.debug("Cannot init pregnancy by sex - not the owner: preggoMobClass={}, ownerId={}, playerId={}",
					preggoMob.getClass().getSimpleName(), preggoMob.getOwnerUUID(), serverPlayer.getUUID());
			return false;
		}
		
		Optional<Integer> numOfBabiesOpt = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getMaleData().resolve())
				.map(maleData -> PregnancySystemHelper.calculateNumOfBabiesByFertility(		
						maleData.getFertilityRate(),
						preggoMob.getGenderedData().getFertilityRate()));
				
		if (numOfBabiesOpt.isEmpty()) {
			MinepreggoMod.LOGGER.debug("Cannot init pregnancy by sex - cannot calculate num of babies: preggoMobClass={}, ownerId={}, playerId={}",
					preggoMob.getClass().getSimpleName(), preggoMob.getOwnerUUID(), serverPlayer.getUUID());
			return false;
		}
		
		if (numOfBabiesOpt.get().intValue() == 0) {
			MinepreggoMod.LOGGER.debug("Cannot init pregnancy by sex - num of babies is zero: preggoMobClass={}, ownerId={}, playerId={}",
					preggoMob.getClass().getSimpleName(), preggoMob.getOwnerUUID(), serverPlayer.getUUID());
			return false;
		}
	
		return initPrePregnancy(preggoMob, ImmutableTriple.of(Optional.of(serverPlayer.getUUID()), Species.HUMAN, Creature.HUMANOID), numOfBabiesOpt.get().intValue());
	}
	
	private static<E extends PreggoMob & ITamablePregnantPreggoMob> float getSpawnProbabilityBasedPregnancy(@NonNull E preggoMob, float t0, float k, float pMin, float pMax) {	
		return PregnancySystemHelper.calculateSpawnProbabilityBasedPregnancy(preggoMob.getPregnancyData(), t0, k, pMin, pMax);
	}
	// PREGNANCY SYSTEM - END
	
	
	
	
	
	
	
	
	
	
	// ARMOR AND ITEMS - START
	public static boolean canUseChestPlateInLactation(IFemaleEntity preggoMob, Item armor) {
		var result = preggoMob.getPostPregnancyData().map(post -> 
				post.getPostPartumLactation() < PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM
			);	
		if (result.isPresent() && !result.get().booleanValue()) {
			return armor instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed();
		}
		return true;
	}
	
	public static<E extends PreggoMob & ITamablePregnantPreggoMob> boolean canUseChestPlateInLactation(E preggoMob, Item armor) {
		final ITamablePregnantPreggoMobData pregnancyData = preggoMob.getPregnancyData();
		if (!pregnancyData.getSyncedPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.MILKING)) {
			return true;
		}
		return armor instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed();
	}
	
	public static boolean canUseChestplate(Item armor) {	
		return canUseChestplate(armor, false);
	}
	
	public static boolean canUseChestplate(Item armor, boolean considerBoobs) {
		if (!ItemHelper.isChest(armor)) {
			return false;
		}				
		if (considerBoobs) {
			return armor instanceof IFemaleArmor;
		}		
		return true;
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?>> void tryToDamageItemOnMainHand(@NonNull E preggoMob) {	    
	    Level world = preggoMob.level();
	    ItemStack stack = preggoMob.getMainHandItem();
	    RandomSource random = world.random;
	    
	    // Check Unbreaking enchantment
	    int unbreakingLevel = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.UNBREAKING, stack);
	    boolean bypassUnbreaking = false;

	    if (unbreakingLevel > 0) {
	        double breakChance = 0.65 / (unbreakingLevel + 1);
	        bypassUnbreaking = random.nextDouble() < breakChance;
	    }
	     
	    if (stack.isEmpty() || !stack.isDamageableItem() || bypassUnbreaking) return;


	    // Let ItemStack handle Unbreaking internally
	    if (stack.hurt(1, random, null)) {
	        stack.shrink(1);
	        stack.setDamageValue(0);
	    }

	    // Sound & sync
	    if (stack.getDamageValue() >= stack.getMaxDamage()) {
	        world.playSound(null, preggoMob.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
	    } else {
	    	Inventory inventory = preggoMob.getInventory();
	    	InventorySlotMapper slotMapper = inventory.getSlotMapper();
	    	int mainHandSlotIndex = slotMapper.getSlotIndex(InventorySlot.MAINHAND);

	    	if (mainHandSlotIndex != InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
		    	inventory.getHandler().setStackInSlot(mainHandSlotIndex, stack);
	    	}	    
	    }	
	}
	
	public static <E extends PreggoMob & ITamablePreggoMob<?>> void tryToDamageArmor(@NonNull E preggoMob, DamageSource damageSource) {
		if (damageSource.is(MinepreggoModDamageSources.PREGNANCY_PAIN)) {
			return;
		}
		
		var world = preggoMob.level();
	
	    List<ItemStack> armorStacks = new ArrayList<>(4);
	
	    // Collect valid armor stacks and count them
	    for (int i = 0; i < 4; i++) {
	        ItemStack stack = preggoMob.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i));
	        armorStacks.add(stack);
	    }
	
	    RandomSource random = world.random;
	
	    for (int i = 0; i < 4; i++) {
	        ItemStack stack = armorStacks.get(i);
	        
	        if (stack.isEmpty() || hasUnbreakingProtection(stack, random)) {
	        	continue;
	        }
   
	        boolean isExplosion = damageSource.is(DamageTypes.EXPLOSION) || damageSource.is(DamageTypes.PLAYER_EXPLOSION);
	        int damageAmount = isExplosion ? 2 : 1;
	        
	        // Apply conditional damage based on armor count and slot
	        if (shouldTakeDamage(i, random) && stack.hurt(damageAmount, random, null)) {
                stack.shrink(1);
                stack.setDamageValue(0);
                
    	        if (stack.getDamageValue() >= stack.getMaxDamage()) {
    	            world.playSound(null, preggoMob.blockPosition(), 
    	                ForgeRegistries.SOUND_EVENTS.getValue(MinepreggoHelper.withDefaultNamespace("entity.item.break")),
    	                SoundSource.NEUTRAL, 1.0F, 1.0F);
    	        }
                updateItemHandler(preggoMob, i + 1, stack);
	        }	
	    }
	}
	
	private static<E extends PreggoMob & ITamablePreggoMob<?>> void updateItemHandler(E preggoMob, int slotId, @Nonnull ItemStack stack) {
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
	        if (handler instanceof IItemHandlerModifiable modHandler) {
	            modHandler.setStackInSlot(slotId, stack);
	        }
	    });
	}

	private static boolean hasUnbreakingProtection(ItemStack stack, RandomSource random) {
	    int unbreakingLevel = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.UNBREAKING, stack);
	    if (unbreakingLevel <= 0) return false;
	    float avoidanceChance = (60.0F + 40.0F / (1 + unbreakingLevel)) * 0.01F;
	    return random.nextFloat() > (1F - avoidanceChance);
	}

	private static boolean shouldTakeDamage(int slotIndex, RandomSource random) {
	    float threshold = (slotIndex == 3 || slotIndex == 0) ? 0.7F : 0.85F;
	    return random.nextFloat() < threshold;
	}
	// ARMOR AND ITEMS - END
	
	
	// SPAWN BABIES AND FETUSES - START
	private static void spawnBabyOrFetusZombies(ServerLevel serverLevel, float p, int numOfBabies, @NonNull AbstractZombieGirl zombieGirl) {
			
		final var minHealth = (int) Math.floor(zombieGirl.getMaxHealth() * 0.2F);
		final var maxHealth = (int) Math.floor(zombieGirl.getMaxHealth() * 0.4F);
		var randomSource = zombieGirl.getRandom();
			
		MinepreggoMod.LOGGER.debug("SPAWN ZOMBIE BABIES AND FETUSES: class={}, p={}, numOfBabies={}",
				zombieGirl.getClass().getSimpleName(), p, numOfBabies);
		
		for (int i = 0; i < numOfBabies; ++i) {	
			if (randomSource.nextFloat() < p) {	
				Mob entityToSpawn;
				if (randomSource.nextBoolean()) {
					entityToSpawn = EntityType.ZOMBIE.spawn(serverLevel, BlockPos.containing(zombieGirl.getX(), zombieGirl.getY() + zombieGirl.getBbHeight() / 2F, zombieGirl.getZ()), MobSpawnType.MOB_SUMMONED);
				}
				else {
					entityToSpawn = MinepreggoModEntities.MONSTER_ZOMBIE_GIRL.get().spawn(serverLevel, BlockPos.containing(zombieGirl.getX(), zombieGirl.getY() + zombieGirl.getBbHeight() / 2F, zombieGirl.getZ()), MobSpawnType.MOB_SUMMONED);
				}				
				entityToSpawn.setBaby(true);
				entityToSpawn.setYRot(randomSource.nextFloat() * 360F);
				entityToSpawn.setHealth(randomSource.nextInt(minHealth, maxHealth));				
			
				var target = zombieGirl.getLastHurtByMob();
				if (target instanceof Player targetPlayer && !PlayerHelper.isInvencible(targetPlayer)) {
					entityToSpawn.setTarget(target);
				}				
			}	
			else {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, zombieGirl.getX(), zombieGirl.getY(), zombieGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}	
	}
	
	private static void spawnBabyOrFetusCreepers(ServerLevel serverLevel, float p, int numOfBabies, @NonNull AbstractCreeperGirl creeperGirl) {
		final var minHealth = (int) Math.floor(creeperGirl.getMaxHealth() * 0.2F);
		final var maxHealth = (int) Math.floor(creeperGirl.getMaxHealth() * 0.5F);
		var randomSource = creeperGirl.getRandom();
		
		MinepreggoMod.LOGGER.debug("SPAWN CREEPER BABIES OR FETUSES: class={}, p={}, numOfbabies={}",
				creeperGirl.getClass().getSimpleName(), p, numOfBabies);
		
		for (int i = 0; i < numOfBabies; ++i) {				
			if (randomSource.nextFloat() < p) {								
				var entityToSpawn = MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(creeperGirl.getX(), creeperGirl.getY() + creeperGirl.getBbHeight() / 2F, creeperGirl.getZ()), MobSpawnType.MOB_SUMMONED);
				entityToSpawn.setBaby(true);
				entityToSpawn.setYRot(randomSource.nextFloat() * 360F);	
				entityToSpawn.setHealth(randomSource.nextInt(minHealth, maxHealth));			
				
				var target = creeperGirl.getLastHurtByMob();
				
				if (target instanceof Player playerTarget && !PlayerHelper.isInvencible(playerTarget)) {
					entityToSpawn.setTarget(target);
				}
			}	
			else {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, creeperGirl.getX(), creeperGirl.getY(), creeperGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}	
	}

	private static void spawnFetusZombies(ServerLevel serverLevel, float p, int numOfBabies, @NonNull AbstractZombieGirl zombieGirl) {
		var randomSource = zombieGirl.getRandom();
		for (int i = 0; i < numOfBabies; ++i) {	
			if (randomSource.nextFloat() < p) {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, zombieGirl.getX(), zombieGirl.getY(), zombieGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}
	}
	
	private static void spawnFetusCreepers(ServerLevel serverLevel, float p, int numOfBabies, @NonNull AbstractCreeperGirl creeperGirl) {
		var randomSource = creeperGirl.getRandom();
		for (int i = 0; i < numOfBabies; ++i) {	
			if (randomSource.nextFloat() < p) {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, creeperGirl.getX(), creeperGirl.getY(), creeperGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}
	}
		
	public static void spawnBabyAndFetusZombies(@NonNull AbstractTamablePregnantZombieGirl zombieGirl, boolean discardedBabies) {		
		if (!(zombieGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		
		final var pregnancyData = zombieGirl.getPregnancyData();
		final var pregnancyStage = pregnancyData.getCurrentPregnancyPhase();

		if (pregnancyStage.compareTo(PregnancyPhase.P3) >= 0) {
			final var alive = getSpawnProbabilityBasedPregnancy(zombieGirl, 0.6F, 0.3F, 0.1F, 0.15F);
			PregnancySystemHelper.spawnBabiesOrFetuses(serverLevel, zombieGirl.position(), alive, 0.3f, pregnancyData.getWomb(), zombieGirl.getRandom());
		}	
		else {		
			final var fetusSpawn = getSpawnProbabilityBasedPregnancy(zombieGirl, 0.3F, 0.3F, 0.5F, 0.9F);
			PregnancySystemHelper.spawnFetuses(serverLevel, zombieGirl.position(), fetusSpawn, pregnancyData.getWomb(), zombieGirl.getRandom());
		}
	}
	
	public static void spawnBabyAndFetusCreepers(@NonNull AbstractTamablePregnantCreeperGirl creeperGirl, boolean discardedBabies) {
		if (!(creeperGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		
		final var pregnancyData = creeperGirl.getPregnancyData();
		final var pregnancyStage = pregnancyData.getCurrentPregnancyPhase();
		
		if (pregnancyStage.compareTo(PregnancyPhase.P3) >= 0) {
			final var alive = getSpawnProbabilityBasedPregnancy(creeperGirl, 0.6F, 0.3F, 0.1F, 0.15F);
			PregnancySystemHelper.spawnBabiesOrFetuses(serverLevel, creeperGirl.position(), alive, 0.3f, pregnancyData.getWomb(), creeperGirl.getRandom());
		}	
		else {		
			final var fetusSpawn = getSpawnProbabilityBasedPregnancy(creeperGirl, 0.3F, 0.3F, 0.5F, 0.9F);
			PregnancySystemHelper.spawnFetuses(serverLevel, creeperGirl.position(), fetusSpawn, pregnancyData.getWomb(), creeperGirl.getRandom());
		}
	}
		
	public static void spawnBabyAndFetusCreepers(@NonNull AbstractMonsterPregnantCreeperGirl creeperGirl) {			
		if (!(creeperGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		
		final var pregnancyData = creeperGirl.getPregnancyData();
		final var currentPregnancyStage = pregnancyData.getCurrentPregnancyPhase();	
		final var numOfBabies = pregnancyData.getNumOfBabies();
		final var totalDaysPassed = creeperGirl.getPregnancyData().getTotalDaysElapsed();
		final var t = Mth.clamp(totalDaysPassed / (float) PregnancySystemHelper.DEFAULT_TOTAL_PREGNANCY_DAYS, 0, 1);
		float p;
		
		if (currentPregnancyStage.compareTo(PregnancyPhase.P3) >= 0) {
			p = MathHelper.sigmoid(0.1F, 0.15F, 0.3F, t, 0.6F);
			spawnBabyOrFetusCreepers(serverLevel, p, numOfBabies, creeperGirl);
		}
		else {
			p = MathHelper.sigmoid(0.5F, 0.9F, 0.3F, t, 0.3F);
			spawnFetusCreepers(serverLevel, p, numOfBabies, creeperGirl);
		}
		
		MinepreggoMod.LOGGER.debug("SPAWN BABY AND FETUS CREEPERS: id={}, class={}, currentPregnancytStage={}, maxPregnancyStage={}, totalDaysPassed={}, t={}",
				creeperGirl.getId(), creeperGirl.getClass().getSimpleName(), currentPregnancyStage, pregnancyData.getLastPregnancyPhase(), totalDaysPassed, t);
	}
	
	public static void spawnBabyAndFetusZombies(@NonNull AbstractMonsterPregnantZombieGirl zombieGirl) {	
		if (!(zombieGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		
		final var pregnancyData = zombieGirl.getPregnancyData();
		final var currentPregnancyStage = pregnancyData.getCurrentPregnancyPhase();		
		final var numOfBabies = pregnancyData.getNumOfBabies();
		
		final var totalDaysPassed = pregnancyData.getTotalDaysElapsed();
		final var t = Mth.clamp(totalDaysPassed / (float) PregnancySystemHelper.DEFAULT_TOTAL_PREGNANCY_DAYS, 0, 1);
		float p;
		
		if (currentPregnancyStage.compareTo(PregnancyPhase.P3) >= 0) {
			p = MathHelper.sigmoid(0.1F, 0.15F, 0.3F, t, 0.7F);
			spawnBabyOrFetusZombies(serverLevel, p, numOfBabies, zombieGirl);
		}
		else {
			p = MathHelper.sigmoid(0.5F, 0.9F, 0.3F, t, 0.3F);
			spawnFetusZombies(serverLevel, p, numOfBabies, zombieGirl);
		}
		
		MinepreggoMod.LOGGER.debug("SPAWN BABY AND FETUS ZOMBIES: id={}, class={}, currentPregnancytStage={}, maxPregnancyStage={}, totalDaysPassed={}, t={}",
				zombieGirl.getId(), zombieGirl.getClass().getSimpleName(), currentPregnancyStage, pregnancyData.getLastPregnancyPhase(), totalDaysPassed, t);
	}	
	// SPAWN BABIES AND FETUSES - END
	

	// JIGGLE PHYSICS - START
	public static void removeJigglePhysics(PreggoMob preggoMob) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> preggoMob), 
				new RemovePreggoMobJigglePhysicsS2CPacket(preggoMob.getId()));
	}	
	// JIGGLE PHYSICS - END
}

