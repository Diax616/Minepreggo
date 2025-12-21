package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import dev.dixmk.minepreggo.world.item.IFemaleArmor;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Helper class for PreggoMob related operations.
 * WARNING: Some fields from PreggoMobs are only available on the server side, but this class does not check for it. That issue will be fixed in future versions.
 */


public class PreggoMobHelper {
			
	private PreggoMobHelper() {}
	
	// Transfer or Copy Data START
	public static void copyRotation(@NonNull LivingEntity source, @NonNull LivingEntity target) {		
		target.setYRot(source.getYRot());
		target.setYBodyRot(source.getYRot());
		target.setYHeadRot(source.getYRot());
		target.setXRot(source.getXRot());
	}

	public static void copyOwner(@NonNull TamableAnimal source, @NonNull TamableAnimal target) {
		if (source.isTame() && source.getOwner() != null)
			target.tame((Player) source.getOwner());
	}
	
	public static void copyHealth(LivingEntity source, LivingEntity target) {
		target.setHealth(Math.min(target.getMaxHealth(), source.getHealth()));
	}
	
	public static void copyName(Entity source, Entity target) {
		if (source.hasCustomName())
			target.setCustomName(source.getCustomName());
	}
	
	public static void copyTamableData(@NonNull ITamablePreggoMob<FemaleEntityImpl> source, @NonNull ITamablePreggoMob<FemaleEntityImpl> target) {
		target.setFullness(source.getFullness());
		target.setHungryTimer(source.getHungryTimer());
		target.setWaiting(source.isWaiting());
		target.setAngry(source.isAngry());
		target.setSavage(source.isSavage());	
		target.setBreakBlocks(source.canBreakBlocks());
		target.setPickUpItems(source.canPickUpItems());		
		target.setFaceState(source.getFaceState());
		target.setBodyState(source.getBodyState());			
		target.getGenderedData().deserializeNBT(source.getGenderedData().serializeNBT());
	}
	
	public static<E extends IPregnancySystemHandler & IPregnancyEffectsHandler> void transferPregnancyData(@NonNull E source, @NonNull E target) {
		target.resetDaysPassed();
		target.resetPregnancyTimer();
		
		target.setMapPregnancyPhase(source.getMapPregnancyPhase());
		target.setWomb(source.getWomb());
		
		target.setDaysToGiveBirth(source.getDaysToGiveBirth());
		target.setPregnancyHealth(source.getPregnancyHealth());
		target.setPregnancyPain(source.getPregnancyPain());
		target.setPregnancySymptoms(source.getPregnancySymptoms());
		target.setPregnancyPainTimer(source.getPregnancyPainTimer());
		target.setLastPregnancyStage(source.getLastPregnancyStage());
		
		target.setCraving(source.getCraving());
		target.setCravingTimer(source.getCravingTimer());
		target.setTypeOfCraving(source.getTypeOfCraving());
		target.setMilking(source.getMilking());
		target.setMilkingTimer(source.getMilkingTimer());
		target.setBellyRubs(source.getBellyRubs());
		target.setBellyRubsTimer(source.getBellyRubsTimer());
		target.setHorny(source.getHorny());
		target.setHornyTimer(source.getHornyTimer());
	}
		
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> void transferAllData(@NonNull E source, @NonNull E target) {
		copyRotation(source, target);
		copyOwner(source, target);
		copyHealth(source, target);
		copyName(source, target);
		copyTamableData(source, target);
		transferInventory(source, target);
		transferPregnancyData(source, target);
	}
	
	// Transfer or Copy Data START
	

	// Inventory START
	public static void transferSlots(@NonNull Mob source, @NonNull Mob target) {
		target.setItemInHand(InteractionHand.MAIN_HAND, source.getMainHandItem());
		target.setItemInHand(InteractionHand.OFF_HAND, source.getOffhandItem());
		target.setItemSlot(EquipmentSlot.HEAD, source.getItemBySlot(EquipmentSlot.HEAD));
		target.setItemSlot(EquipmentSlot.CHEST, source.getItemBySlot(EquipmentSlot.CHEST));
		target.setItemSlot(EquipmentSlot.LEGS, source.getItemBySlot(EquipmentSlot.LEGS));
		target.setItemSlot(EquipmentSlot.FEET, source.getItemBySlot(EquipmentSlot.FEET));
	}	
	
	public static <E extends PreggoMob & ITamablePreggoMob<?>> void transferInventory(@NonNull E source, @NonNull E target) {
		transferSlots(source, target);
	    source.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(sourceHandler -> 
	        target.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(targetHandler -> {
	            if (targetHandler instanceof IItemHandlerModifiable modifiableTarget) {
	                int slots = Math.min(sourceHandler.getSlots(), modifiableTarget.getSlots());
	                for (int slot = 0; slot < slots; slot++) {
	                    modifiableTarget.setStackInSlot(slot, sourceHandler.getStackInSlot(slot));
	                }
	            }
	        })
	    );
	}

	public static<T extends PreggoMob & ITamablePreggoMob<?>> void syncFromEquipmentSlotToInventory(@NonNull T preggoEntity) {
        if (!preggoEntity.level().isClientSide) {
    		preggoEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(capability -> {
                if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot) {
                    modHandlerEntSetSlot.setStackInSlot(ITamablePreggoMob.HEAD_INVENTORY_SLOT, preggoEntity.getItemBySlot(EquipmentSlot.HEAD));
                    modHandlerEntSetSlot.setStackInSlot(ITamablePreggoMob.CHEST_INVENTORY_SLOT, preggoEntity.getItemBySlot(EquipmentSlot.CHEST));
                    modHandlerEntSetSlot.setStackInSlot(ITamablePreggoMob.LEGS_INVENTORY_SLOT, preggoEntity.getItemBySlot(EquipmentSlot.LEGS));
                    modHandlerEntSetSlot.setStackInSlot(ITamablePreggoMob.FEET_INVENTORY_SLOT, preggoEntity.getItemBySlot(EquipmentSlot.FEET));
                    modHandlerEntSetSlot.setStackInSlot(ITamablePreggoMob.MAINHAND_INVENTORY_SLOT, preggoEntity.getMainHandItem());
                    modHandlerEntSetSlot.setStackInSlot(ITamablePreggoMob.OFFHAND_INVENTORY_SLOT, preggoEntity.getOffhandItem());
                }
            });
        }
	}
	
	public static<T extends PreggoMob & ITamablePreggoMob<?>> void syncFromInventoryToEquipmentSlot(@NonNull T preggoEntity) {
	    if (!preggoEntity.level().isClientSide) {
		    preggoEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(capability -> {   	
		        preggoEntity.setItemInHand(InteractionHand.MAIN_HAND, capability.getStackInSlot(ITamablePreggoMob.MAINHAND_INVENTORY_SLOT));
		        preggoEntity.setItemInHand(InteractionHand.OFF_HAND, capability.getStackInSlot(ITamablePreggoMob.OFFHAND_INVENTORY_SLOT));
		        preggoEntity.setItemSlot(EquipmentSlot.HEAD, capability.getStackInSlot(ITamablePreggoMob.HEAD_INVENTORY_SLOT));	   
		        preggoEntity.setItemSlot(EquipmentSlot.CHEST, capability.getStackInSlot(ITamablePreggoMob.CHEST_INVENTORY_SLOT));	   
		        preggoEntity.setItemSlot(EquipmentSlot.LEGS, capability.getStackInSlot(ITamablePreggoMob.LEGS_INVENTORY_SLOT));	   
		        preggoEntity.setItemSlot(EquipmentSlot.FEET, capability.getStackInSlot(ITamablePreggoMob.FEET_INVENTORY_SLOT));		
		    });   
	    }
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?>> void storeItemInSpecificRange(@NonNull E preggoMob, @NonNull ItemEntity itemEntity, int minStorageSlot, int maxStorageSlot) {
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(sourceHandler -> {		
			var originalItemStack = itemEntity.getItem();
			var originalCount = originalItemStack.getCount();
			
		    for (int slot = minStorageSlot; slot <= maxStorageSlot && !originalItemStack.isEmpty(); slot++) {
		    	originalItemStack = sourceHandler.insertItem(slot, originalItemStack, false);
		    }		
				    
		    if (originalItemStack.getCount() != originalCount) {		          
		    	if (!preggoMob.level().isClientSide) {
			    	preggoMob.level().playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.item.pickup")), SoundSource.AMBIENT, 0.75F, 0.75F);	    	
		    	}
		    	
		    	if (originalItemStack.isEmpty()) {
	            	itemEntity.discard();
	            } else {
	            	itemEntity.setItem(originalItemStack);
	            }
		    }		  
		});	
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?>> void storeItemInSpecificRangeOrDrop(@NonNull E preggoMob, @NonNull ItemStack itemStack, int minStorageSlot, int maxStorageSlot) {
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(sourceHandler -> {		
			var originalItemStack = itemStack;	
			var originalCount = originalItemStack.getCount();
			for (int slot = minStorageSlot; slot <= maxStorageSlot && !originalItemStack.isEmpty(); slot++) {
		    	originalItemStack = sourceHandler.insertItem(slot, originalItemStack, false);
		    }			
			
		    if (!originalItemStack.isEmpty() || originalItemStack.getCount() != originalCount) {
            	dropItemStack(preggoMob, originalItemStack);
		    }
		});
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?>> void transferItemFromInventoryToHand(@NonNull E preggoMob, @NonNull Item item, InteractionHand hand, int minStorageSlot, int maxStorageSlot) {
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(sourceHandler -> {		
			ItemStack target = null;
			for (int slot = minStorageSlot; slot <= maxStorageSlot; slot++) {		
				target = sourceHandler.getStackInSlot(slot); 
				if (target.is(item) && sourceHandler instanceof IItemHandlerModifiable modHandlerEntSetSlot) {
					modHandlerEntSetSlot.setStackInSlot(slot, ItemStack.EMPTY);
					break;
				}	
		    }			
			if (target != null) {				
				replaceAndDropItemstackInHand(preggoMob, hand, target);	
			}
		});
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?>> void addItemToInventory(@NonNull E preggoMob, @NonNull ItemEntity itemEntity) {		
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(sourceHandler -> {		
			var originalItemStack = itemEntity.getItem();
			var remainder = ItemHandlerHelper.insertItemStacked(sourceHandler, originalItemStack, false);		
			
	        if (remainder.getCount() != originalItemStack.getCount()) {
	            if (remainder.isEmpty()) {
	            	itemEntity.discard();
	            } else {
	            	itemEntity.setItem(remainder);
	            }
	        }		
		});
	}

	public static<E extends PreggoMob & ITamablePreggoMob<?>> void replaceAndDropItemstackInHand(@Nonnull E preggoMob, InteractionHand hand, @Nonnull ItemStack itemStack) {			
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {			    	
			if (handler instanceof IItemHandlerModifiable modHandler) {	
				int inventorySlot = hand == InteractionHand.MAIN_HAND ? ITamablePreggoMob.MAINHAND_INVENTORY_SLOT : ITamablePreggoMob.OFFHAND_INVENTORY_SLOT;		
				EquipmentSlot equipmentSlot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;	
				
				if (!modHandler.getStackInSlot(inventorySlot).isEmpty()) {
					removeAndDropItemStackFromEquipmentSlot(preggoMob, equipmentSlot);			
				}
						
	            modHandler.setStackInSlot(inventorySlot, itemStack);
	            preggoMob.setItemSlot(equipmentSlot, itemStack);
	        }	
		});
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?>> void removeAndDropItemStackFromEquipmentSlot(E preggoMob, EquipmentSlot slotId) {			
		if (dropItemStack(preggoMob, preggoMob.getItemBySlot(slotId))) {		
			preggoMob.setItemSlot(slotId, ItemStack.EMPTY);
			preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {			    
		        if (handler instanceof IItemHandlerModifiable modHandler) {
		            modHandler.setStackInSlot(slotId.getFilterFlag(), ItemStack.EMPTY);
		        }	
			});			
		}	
	}
	
	public static boolean dropItemStack(LivingEntity entity, ItemStack stack) {
	    if (!stack.isEmpty()) {
	        ItemEntity item = new ItemEntity(
	            entity.level(),
	            entity.getX(), entity.getY(), entity.getZ(),
	            stack
	        );
	        item.setDefaultPickUpDelay();
	        entity.level().addFreshEntity(item);
	        return true;
	    }
	    return false;
	}	
	// Inventory END
	
	public static void transferAttackTarget(@NonNull Mob source, @NonNull Mob target) {
		var entfound = source.level().getEntitiesOfClass(LivingEntity.class, new AABB(source.blockPosition()).inflate(16)).stream()
				.sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(source)))
				.toList();
			
		entfound.forEach(ent -> {
			if (ent instanceof Mob mob && mob.getTarget() == source) {
				mob.setTarget(target);
			}
			if (source.getTarget() == ent) {
				target.setTarget(ent);
			}
		});
	}

	
	
	// Pregnancy START
	public static<E extends PreggoMob & IFemaleEntity & ITamablePreggoMob<?> & IPregnancySystemHandler> boolean initPregnancy(@NonNull E preggoMob) {				
		
		final boolean result = preggoMob.isPregnant() && preggoMob.getPrePregnancyData().isPresent();
		
		if (!result) {
			MinepreggoMod.LOGGER.warn("CANNOT INIT PREGNANCY: class={}, isPregnant={}, hasPrePregnancyData={}",
					preggoMob.getClass().getSimpleName(), preggoMob.isPregnant(), preggoMob.getPrePregnancyData().isPresent());
			return result;
		}
		
		preggoMob.getPrePregnancyData().ifPresent(prePregnancyData -> {
			final var numOfBabies = prePregnancyData.fertilizedEggs();
			final PregnancyPhase lastPregnancyStage = IBreedable.calculateMaxPregnancyPhaseByTotalNumOfBabies(numOfBabies);
			final int totalDays = MinepreggoModConfig.getTotalPregnancyDays();
			final var mother = ImmutableTriple.of(preggoMob.getUUID(), preggoMob.getTypeOfSpecies(), preggoMob.getTypeOfCreature());
			final var father = ImmutableTriple.of(Optional.ofNullable(prePregnancyData.fatherId()), prePregnancyData.typeOfSpeciesOfFather(), prePregnancyData.typeOfCreatureOfFather());
			final var map = new MapPregnancyPhase(totalDays, lastPregnancyStage);
			final var womb = new Womb(mother, father, preggoMob.getRandom(), numOfBabies);

			preggoMob.setLastPregnancyStage(lastPregnancyStage);	
			preggoMob.setMapPregnancyPhase(map);	
			preggoMob.setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);		
			preggoMob.setWomb(womb);
			preggoMob.setDaysToGiveBirth(PregnancySystemHelper.calculateDaysToGiveBirth(preggoMob));	
			
			preggoMob.resetDaysPassed();
			
			MinepreggoMod.LOGGER.debug("INIT PREGNANCY: class={}, lastPregnancyStage={}, totalDays={}, daysByStage={}, womb={}",
					preggoMob.getClass().getSimpleName(), lastPregnancyStage, totalDays, map, womb);
		});
		
		return result;
	}
	
	public static<E extends PreggoMob & IFemaleEntity & ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler> void initDefaultPregnancy(@NonNull E preggoMob) {				
		final var numOfBabies =	IBreedable.calculateNumOfBabiesByMaxPregnancyStage(preggoMob.getLastPregnancyStage());
		preggoMob.tryImpregnate(numOfBabies, ImmutableTriple.of(Optional.empty(), preggoMob.getTypeOfSpecies(), preggoMob.getTypeOfCreature()));	
		initPregnancy(preggoMob);
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IFemaleEntity> boolean initPrePregnancy(@NonNull E preggoMob, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, @Nonnegative int fertilizedEggs) {	
		return preggoMob.tryImpregnate(fertilizedEggs, father);
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IFemaleEntity & IPregnancySystemHandler> boolean initPregnancyByPotion(@NonNull E preggoMob, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, @Nonnegative int amplifier) {	
		final int numOfBabies = IBreedable.calculateNumOfBabiesByPotion(amplifier);	
		final var r1 = initPrePregnancy(preggoMob, father, numOfBabies);	
		final var r2 = initPregnancy(preggoMob);
		
		if (!r1 || !r2) {
			MinepreggoMod.LOGGER.warn("CANNOT INIT PREGNANCY BY POTION: class={}, fatherId={}, speciesOfFather={}, creatureOfFather={}, amplifier={}, numOfBabies={}, initPrePregnancyResult={}, initPregnancyResult={}",
					preggoMob.getClass().getSimpleName(), father.getLeft().orElse(null), father.getMiddle(), father.getRight(), amplifier, numOfBabies, r1, r2);
		}
		
		return r1 && r2;
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IFemaleEntity> boolean initPregnancyBySex(@NonNull E preggoMob, @NonNull ServerPlayer serverPlayer) {	
		if (!preggoMob.isOwnedBy(serverPlayer)) {
			MinepreggoMod.LOGGER.debug("CANNOT INIT PREGNANCY BY SEX : class={}, playerId={}, ownerId={}",
					preggoMob.getClass().getSimpleName(), serverPlayer.getUUID(), preggoMob.getOwnerUUID());
			return false;
		}
		
		final var cap = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
		
		if (cap.isEmpty()) {
			return false;
		}
		
		final var maleData = cap.get().getMaleData().resolve();
		
		if (maleData.isEmpty()) {
			return false;
		}
		
		final var numOfBabies = IBreedable.calculateNumOfBabiesByFertility(preggoMob.getFertilityRate(), maleData.get().getFertilityRate());
	
		if (numOfBabies == 0) {
			return false;
		}
		
		return initPrePregnancy(preggoMob, ImmutableTriple.of(Optional.of(serverPlayer.getUUID()), Species.HUMAN, Creature.HUMANOID), numOfBabies);
	}
	
	private static<E extends PreggoMob & ITamablePreggoMob<?> & IPregnancySystemHandler> float getSpawnProbabilityBasedPregnancy(@NonNull E preggoMob, float t0, float k, float pMin, float pMax) {
		final int totalDays = preggoMob.getTotalDaysOfPregnancy();
		final int totalDaysPassed = PregnancySystemHelper.calculateTotalDaysPassedFromPhaseP0(preggoMob);
		
		final float t = Mth.clamp(totalDaysPassed / (float) totalDays, 0, 1);	
		final float p = MathHelper.sigmoid(pMin, pMax, k, t, t0);
		
		MinepreggoMod.LOGGER.debug("SPAWN PROBABILITY BASED IN PREGNANCY: class={}, totalDays={}, totalDaysPassed={}, t={}, p={}",
				preggoMob.getClass().getSimpleName(), totalDays, totalDaysPassed, t, p);

		return p;
	}
	// Pregnancy END
	
	
	// Armor START
	
	public static boolean canUseChestPlateInLactation(ITamablePreggoMob<FemaleEntityImpl> preggoMob, Item armor) {
		var result = preggoMob.getGenderedData().getPostPregnancyData().map(post -> 
				post.getPostPartumLactation() < PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM
			);	
		if (result.isPresent() && !result.get().booleanValue()) {
			return armor instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed();
		}
		return true;
	}
	
	public static boolean canUseChestPlateInLactation(IPregnancySystemHandler preggoMob, Item armor) {
		if (!preggoMob.getPregnancySymptoms().contains(PregnancySymptom.MILKING)) {
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
	        preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(cap -> {
	            if (cap instanceof IItemHandlerModifiable mod) {
	                mod.setStackInSlot(ITamablePreggoMob.MAINHAND_INVENTORY_SLOT, stack);
	            }
	        });
	    }	
	}
	
	public static <E extends PreggoMob & ITamablePreggoMob<?>> void tryToDamageArmor(@NonNull E preggoMob, DamageSource damageSource) {
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
    	                ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.item.break")),
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
	// Armor END
	
	
	// Spawn Babies START	
	private static void spawnBabyOrFetusZombies(float p, int numOfBabies, @NonNull AbstractZombieGirl zombieGirl) {
		
		if (!(zombieGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		
		final var MIN_HEALTH = (int) Math.floor(zombieGirl.getMaxHealth() * 0.2F);
		final var MAX_HEALTH = (int) Math.floor(zombieGirl.getMaxHealth() * 0.5F);
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
				entityToSpawn.setHealth(randomSource.nextInt(MIN_HEALTH, MAX_HEALTH));				
			
				var target = zombieGirl.getLastHurtByMob();
				
				if (!isPlayerInCreativeOrSpectator(target)) {
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
	
	private static void spawnBabyOrFetusCreepers(float p, int numOfBabies, @NonNull AbstractCreeperGirl creeperGirl) {
		
		if (!(creeperGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		
		final var MIN_HEALTH = (int) Math.floor(creeperGirl.getMaxHealth() * 0.2F);
		final var MAX_HEALTH = (int) Math.floor(creeperGirl.getMaxHealth() * 0.5F);
		var randomSource = creeperGirl.getRandom();
		
		MinepreggoMod.LOGGER.debug("SPAWN CREEPER BABIES OR FETUSES: class={}, p={}, numOfbabies={}",
				creeperGirl.getClass().getSimpleName(), p, numOfBabies);
		
		for (int i = 0; i < numOfBabies; ++i) {				
			if (randomSource.nextFloat() < p) {								
				var entityToSpawn = MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(creeperGirl.getX(), creeperGirl.getY() + creeperGirl.getBbHeight() / 2F, creeperGirl.getZ()), MobSpawnType.MOB_SUMMONED);
				entityToSpawn.setBaby(true);
				entityToSpawn.setYRot(randomSource.nextFloat() * 360F);	
				entityToSpawn.setHealth(randomSource.nextInt(MIN_HEALTH, MAX_HEALTH));			
				
				var target = creeperGirl.getLastHurtByMob();
				
				if (!isPlayerInCreativeOrSpectator(target)) {
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

	private static void spawnFetusZombies(float p, int numOfBabies, @NonNull AbstractZombieGirl zombieGirl) {
		if (!(zombieGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}	
		var randomSource = zombieGirl.getRandom();
		for (int i = 0; i < numOfBabies; ++i) {	
			if (randomSource.nextFloat() < p) {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, zombieGirl.getX(), zombieGirl.getY(), zombieGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}
	}
	
	private static void spawnFetusCreepers(float p, int numOfBabies, @NonNull AbstractCreeperGirl creeperGirl) {
		if (!(creeperGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}	
		var randomSource = creeperGirl.getRandom();
		for (int i = 0; i < numOfBabies; ++i) {	
			if (randomSource.nextFloat() < p) {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, creeperGirl.getX(), creeperGirl.getY(), creeperGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}
	}
		
	public static void spawnBabyAndFetusZombies(@NonNull AbstractTamablePregnantZombieGirl<?,?> zombieGirl) {		

		final var numOfBabies = zombieGirl.getWomb().getNumOfBabies();
		final var pregnancyStage = zombieGirl.getCurrentPregnancyStage();

		if (pregnancyStage.ordinal() >= 3) {
			final var alive = getSpawnProbabilityBasedPregnancy(zombieGirl, 0.6F, 0.3F, 0.2F, 0.95F);
			spawnBabyOrFetusZombies(alive, numOfBabies, zombieGirl);
		}	
		else {		
			final var fetusSpawn = getSpawnProbabilityBasedPregnancy(zombieGirl, 0.3F, 0.3F, 0.1F, 0.6F);
			spawnFetusZombies(fetusSpawn, numOfBabies, zombieGirl);
		}
	}
	
	public static void spawnBabyAndFetusCreepers(@NonNull AbstractTamablePregnantCreeperGirl<?,?> creeperGirl) {
		
		final var numOfBabies = creeperGirl.getWomb().getNumOfBabies();
		final var pregnancyStage = creeperGirl.getCurrentPregnancyStage();
		
		if (pregnancyStage.ordinal() >= 3) {
			final var alive = getSpawnProbabilityBasedPregnancy(creeperGirl, 0.6F, 0.3F, 0.15F, 0.8F);
			spawnBabyOrFetusCreepers(alive, numOfBabies, creeperGirl);
		}	
		else {		
			final var fetusSpawn = getSpawnProbabilityBasedPregnancy(creeperGirl, 0.3F, 0.3F, 0.1F, 0.6F);
			spawnFetusCreepers(fetusSpawn, numOfBabies, creeperGirl);
		}
	}
	
	
	public static void spawnBabyAndFetusCreepers(@NonNull AbstractMonsterPregnantCreeperGirl creeperGirl) {	
		
		final var currentPregnancyStage = creeperGirl.getCurrentPregnancyStage();
		
		final var numOfBabies = IBreedable.calculateNumOfBabiesByMaxPregnancyStage(creeperGirl.getCurrentPregnancyStage());
		final var totalDaysPassed = creeperGirl.getTotalDaysPassed();
		final var t = Mth.clamp(totalDaysPassed / (float) PregnancySystemHelper.TOTAL_PREGNANCY_DAYS, 0, 1);
		float p;
		
		if (currentPregnancyStage.ordinal() > 2) {
			p = MathHelper.sigmoid(0.1F, 0.6F, 0.3F, t, 0.6F);
			spawnBabyOrFetusCreepers(p, numOfBabies, creeperGirl);
		}
		else {
			p = MathHelper.sigmoid(0.5F, 0.9F, 0.3F, t, 0.3F);
			spawnFetusCreepers(p, numOfBabies, creeperGirl);
		}
		
		MinepreggoMod.LOGGER.debug("SPAWN BABY AND FETUS CREEPERS: id={}, class={}, currentPregnancytStage={}, maxPregnancyStage={}, totalDaysPassed={}, t={}",
				creeperGirl.getId(), creeperGirl.getClass().getSimpleName(), currentPregnancyStage, creeperGirl.getLastPregnancyStage(), totalDaysPassed, t);
	}
	
	public static void spawnBabyAndFetusZombies(@NonNull AbstractMonsterPregnantZombieGirl zombie) {	
		
		final var currentPregnancyStage = zombie.getCurrentPregnancyStage();
			
		final var numOfBabies = IBreedable.calculateNumOfBabiesByMaxPregnancyStage(zombie.getCurrentPregnancyStage()); 
		final var totalDaysPassed = zombie.getTotalDaysPassed();
		final var t = Mth.clamp(totalDaysPassed / (float) PregnancySystemHelper.TOTAL_PREGNANCY_DAYS, 0, 1);
		float p;
		
		if (currentPregnancyStage.ordinal() > 2) {
			p = MathHelper.sigmoid(0.1F, 0.6F, 0.3F, t, 0.7F);
			spawnBabyOrFetusZombies(p, numOfBabies, zombie);
		}
		else {
			p = MathHelper.sigmoid(0.5F, 0.9F, 0.3F, t, 0.3F);
			spawnFetusZombies(p, numOfBabies, zombie);
		}
		
		MinepreggoMod.LOGGER.debug("SPAWN BABY AND FETUS ZOMBIES: id={}, class={}, currentPregnancytStage={}, maxPregnancyStage={}, totalDaysPassed={}, t={}",
				zombie.getId(), zombie.getClass().getSimpleName(), currentPregnancyStage, zombie.getLastPregnancyStage(), totalDaysPassed, t);
	}	
	// Spawn Babies END
	
	// Client Data START
	public static PregnancySystemHelper.PrenatalRegularCheckUpData createRegularPrenatalCheckUpData(IPregnancySystemHandler ps) {
		return new PregnancySystemHelper.PrenatalRegularCheckUpData(
				ps.getCurrentPregnancyStage(),
				ps.getLastPregnancyStage(),
				ps.getPregnancyHealth(),
				ps.getWomb().getNumOfBabies(),
				ps.getDaysPassed(),
				PregnancySystemHelper.calculateDaysToNextPhase(ps),
				ps.getDaysToGiveBirth()
				);
	}
	
	public static PregnancySystemHelper.PrenatalUltrasoundScanData createUltrasoundScanPrenatalCheckUpData(Species motherSpecies, IPregnancySystemHandler ps) {
		return new PregnancySystemHelper.PrenatalUltrasoundScanData(
				motherSpecies,
				ps.getWomb()	
				);
	}
	// Client Data END
	
	
	// Sex Event START	
	public static boolean canOwnerActivateSexEvent(ServerPlayer owner, PreggoMob target) {
		if (!(target instanceof ITamablePreggoMob<?> tamableTarget)) {
			return false;
		}
		
		if (PregnancySystemHelper.areHostileMobsNearby(owner, target, 16D)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.mobs"));
			return false;
		}
		else if (!tamableTarget.getGenderedData().canFuck()) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.sexual_appetite", target.getSimpleName()));
			return false;
		}
		else if (owner.distanceToSqr(target) >= 32) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.distance", target.getSimpleName()));
			return false;
		}
		else if (!PregnancySystemHelper.hasEnoughBedsForBreeding(target, 1, 8)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.enough_beds"));
			return false;
		}
		else if (ServerCinematicManager.getInstance().isInCinematic(target)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.active_cinematic", target.getSimpleName()));
			return false;
		}
		
		owner.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
			if (cap.getGender() == tamableTarget.getGenderedData().getGender()) {
				MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.preggo_mob.message.same_sex", target.getSimpleName()));
			}
		});
		
		return true;
	}
	
	public static boolean canActivateSexEvent(ServerPlayer owner, PreggoMob target) {		
		if (!(target instanceof ITamablePreggoMob<?>) && !(target instanceof IPregnancySystemHandler)) {
			return false;
		}
		
		if (PregnancySystemHelper.areHostileMobsNearby(owner, target, 16D)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.mobs"));
		}
		else if (owner.distanceToSqr(target) >= 32) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.distance", target.getSimpleName()));
		}
		else if (ServerCinematicManager.getInstance().isInCinematic(target)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.active_cinematic", target.getSimpleName()));
		}
		
			
		if (!PregnancySystemHelper.hasEnoughBedsForBreeding(target, 1, 8)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.pregnant.preggo_mob.message.without_beds", target.getSimpleName(), owner.getDisplayName().getString()));
		}
		else {
			return true;
		}

		return false;
	}	
	// Sex Event END
	
	
	
	// Common START	
	public static boolean isPlayerInCreativeOrSpectator(LivingEntity entity) {
	    if (entity instanceof Player player) {
	        return player.isCreative() || player.isSpectator();
	    }
	    return false;
	}
	
	public static boolean hasValidTarget(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive() && entity.canAttack(target);
	}
	
	public static boolean isTargetStillValid(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive();
	}
	
	// Common END
}

