package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class PreggoMobHelper {
			
	private PreggoMobHelper() {}
	
	public static<E extends PreggoMob & ITamablePreggoMob> void transferBasicData(@NonNull E source, @NonNull E target) {		
		target.setYRot(source.getYRot());
		target.setYBodyRot(source.getYRot());
		target.setYHeadRot(source.getYRot());
		target.setXRot(source.getXRot());
		target.setHealth(source.getHealth());
			
		if (source.hasCustomName()) target.setCustomName(source.getCustomName());
		if (source.isTame() && source.getOwner() != null) target.tame((Player) source.getOwner());
				
		target.setHungry(source.getHungry());
		target.setHungryTimer(source.getHungryTimer());
		target.setWaiting(source.isWaiting());
		target.setAngry(source.isAngry());
		target.setSavage(source.isSavage());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP1> void transferPregnancyP1Data(E source, E target) {
		transferBasicData(source, target);
		target.setCraving(source.getCraving());
		target.setCravingTimer(source.getCravingTimer());
		target.setTypeOfCraving(source.getTypeOfCraving());
		
		target.setDaysByStage(source.getDaysByStage());
		target.setDaysPassed(0);
		target.setDaysToGiveBirth(source.getDaysToGiveBirth());
		target.setPregnancyHealth(source.getPregnancyHealth());
		target.setPregnancyPain(source.getPregnancyPain());
		target.setPregnancySymptom(source.getPregnancySymptom());
		target.setPregnancyPainTimer(source.getPregnancyPainTimer());
		target.setPregnancyTimer(0);
		target.setLastPregnancyStage(source.getLastPregnancyStage());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP2> void transferPregnancyP2Data(@NonNull E source, @NonNull E target) {
		transferPregnancyP1Data(source, target);
		target.setMilking(source.getMilking());
		target.setMilkingTimer(source.getMilkingTimer());
	}

	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP3> void transferPregnancyP3Data(@NonNull E source, @NonNull E target) {
		transferPregnancyP2Data(source, target);
		target.setBellyRubs(source.getBellyRubs());
		target.setBellyRubsTimer(source.getBellyRubsTimer());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP4> void transferPregnancyP4Data(@NonNull E source, @NonNull E target) {
		transferPregnancyP3Data(source, target);
		target.setHorny(source.getHorny());
		target.setHornyTimer(source.getHornyTimer());
	}
		
	public static boolean hasValidTarget(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive() && entity.canAttack(target);
	}
	
	public static boolean isTargetStillValid(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive();
	}

	public static void putItemStackInOffHand(LivingEntity entity, Item item, int num) {
		if (entity.getOffhandItem() != ItemStack.EMPTY
				&& entity.level() instanceof ServerLevel level) {
			ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), entity.getOffhandItem());
			entityToSpawn.setPickUpDelay(10);
			level.addFreshEntity(entityToSpawn);
		}
		entity.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(item, num));
	}

	public static <E extends PreggoMob & ITamablePreggoMob> void transferInventary(@NonNull E source, @NonNull E target) {
		transferSlots(source, target);
	
	    source.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(sourceHandler -> {
	        target.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(targetHandler -> {
	            if (targetHandler instanceof IItemHandlerModifiable modifiableTarget) {
	                int slots = Math.min(sourceHandler.getSlots(), modifiableTarget.getSlots());
	                for (int slot = 0; slot < slots; slot++) {
	                    modifiableTarget.setStackInSlot(slot, sourceHandler.getStackInSlot(slot));
	                }
	            }
	        });
	    });
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob> void addItemToInventory(@NonNull E preggoMob, @NonNull ItemEntity itemEntity) {		
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(sourceHandler -> {		
			var originalItemStack = itemEntity.getItem();
			var remainder = ItemHandlerHelper.insertItemStacked(sourceHandler, originalItemStack, false);		
			
	        if (remainder.getCount() != originalItemStack.getCount()) {
	            // Some (or all) was inserted
	            if (remainder.isEmpty()) {
	            	itemEntity.discard();
	            } else {
	            	itemEntity.setItem(remainder);
	            }
	        }		
		});
	}
	
	public static<T extends PreggoMob & ITamablePreggoMob> void syncFromEquipmentSlotToInventory(@NonNull T preggoEntity) {
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
	

	public static<T extends PreggoMob & ITamablePreggoMob> void syncFromInventoryToEquipmentSlot(@NonNull T preggoEntity) {
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
	
	public static<E extends PreggoMob & ITamablePreggoMob> void storeItemInSpecificRange(@NonNull E preggoMob, @NonNull ItemEntity itemEntity, int minStorageSlot, int maxStorageSlot) {
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(sourceHandler -> {		
			var originalItemStack = itemEntity.getItem();
			var originalCount = originalItemStack.getCount();
			
		    for (int slot = minStorageSlot; slot <= maxStorageSlot && !originalItemStack.isEmpty(); slot++) {
		    	originalItemStack = sourceHandler.insertItem(slot, originalItemStack, false);
		    }		
				    
		    if (originalItemStack.getCount() != originalCount) {		    	
	            
		    	if (!preggoMob.level().isClientSide()) {
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
	
	private static <E extends PreggoMob> void transferSlots(@NonNull E source, @NonNull E target) {
		target.setItemInHand(InteractionHand.MAIN_HAND, source.getMainHandItem());
		target.setItemInHand(InteractionHand.OFF_HAND, source.getOffhandItem());
		target.setItemSlot(EquipmentSlot.HEAD, source.getItemBySlot(EquipmentSlot.HEAD));
		target.setItemSlot(EquipmentSlot.CHEST, source.getItemBySlot(EquipmentSlot.CHEST));
		target.setItemSlot(EquipmentSlot.LEGS, source.getItemBySlot(EquipmentSlot.LEGS));
		target.setItemSlot(EquipmentSlot.FEET, source.getItemBySlot(EquipmentSlot.FEET));
	}
	
	public static void transferAttackTarget(@NonNull Mob source, @NonNull Mob target) {
		final Vec3 center = new Vec3(source.getX(), source.getY(), source.getZ());
		List<LivingEntity> entfound = Collections.unmodifiableList(source.level().getEntitiesOfClass(LivingEntity.class, new AABB(center, center).inflate(16)).stream()
				.sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(center)))
				.toList());
		
		for (var ent : entfound) {
			if (ent instanceof Mob mob && mob.getTarget() == source) {
				mob.setTarget(target);
			}
			if (source.getTarget() == ent) {
				target.setTarget(ent);
			}	
		}
	}

	@Nonnegative
	public static int getNumberOfChildrens(PregnancyStage maxPregnancyStage) {			
		switch (maxPregnancyStage) {
		case P5: {		
			return 2;
		}
		case P6: {		
			return 3;
		}
		case P7: {		
			return 4;
		}
		case P8: {		
			return 5;
		}
		default:
			return 1;
		}
	}
		
	@Nonnegative
	public static int getNumberOfDaysByMaxPregnancyStage(PregnancyStage maxPregnancyStage) {
		return PregnancySystemConstants.TOTAL_PREGNANCY_DAYS / maxPregnancyStage.ordinal();
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem> void startPregnancy(@NonNull E preggoMob, PregnancyStage maxPregnancyStage) {	
		final int daysByStage = getNumberOfDaysByMaxPregnancyStage(maxPregnancyStage);
		final int daysToBirth = (maxPregnancyStage.ordinal() - preggoMob.getCurrentPregnancyStage().ordinal() + 1) * daysByStage;
		
		preggoMob.setDaysPassed(0);
		preggoMob.setLastPregnancyStage(maxPregnancyStage);
		preggoMob.setDaysByStage(daysByStage);
		preggoMob.setDaysToGiveBirth(daysToBirth);
		preggoMob.setPregnancyHealth(100);
		
		MinepreggoMod.LOGGER.debug("START PREGNANCY: id={}, class={}, currentPregnancyStage={}, maxPregnancyStage={}, daysByStage={}, daysToBirth={}",
				preggoMob.getId(), preggoMob.getClass().getSimpleName(),preggoMob.getCurrentPregnancyStage(), maxPregnancyStage, daysByStage, daysToBirth);
	}
		
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem> void startPregnancy(@NonNull E preggoMob) {		
		startPregnancy(preggoMob, PregnancyStage.getRandomStageFrom(preggoMob.getCurrentPregnancyStage()));
	}
	
	private static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem> float getSpawnProbabilityBasedPregnancy(@NonNull E preggoMob, float t0, float k, float pMin, float pMax) {
		final int totalDays = preggoMob.getLastPregnancyStage().ordinal() * preggoMob.getDaysByStage();
		final int totalDaysPassed = Math.max(0, preggoMob.getCurrentPregnancyStage().ordinal() - 1) * preggoMob.getDaysByStage() + preggoMob.getDaysPassed();  				
		final float t = Mth.clamp(totalDaysPassed / (float) totalDays, 0, 1);	
		final float p = MathHelper.sigmoid(pMin, pMax, k, t, t0);
		
		MinepreggoMod.LOGGER.debug("SPAWN PROBABILITY BASED IN PREGNANCY: class={}, totalDays={}, totalDaysPassed={}, t={}, p={}",
				preggoMob.getClass().getSimpleName(), totalDays, totalDaysPassed, t, p);

		return p;
	}
	
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
					entityToSpawn = MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(zombieGirl.getX(), zombieGirl.getY() + zombieGirl.getBbHeight() / 2F, zombieGirl.getZ()), MobSpawnType.MOB_SUMMONED);
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
				var entityToSpawn = MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(creeperGirl.getX(), creeperGirl.getY() + creeperGirl.getBbHeight() / 2F, creeperGirl.getZ()), MobSpawnType.MOB_SUMMONED);
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

		final var numOfBabies = getNumberOfChildrens(zombieGirl.getLastPregnancyStage());
		
		if (zombieGirl instanceof IPregnancyP3) {
			final var alive = getSpawnProbabilityBasedPregnancy(zombieGirl, 0.6F, 0.1F, 0.2F, 0.95F);
			spawnBabyOrFetusZombies(alive, numOfBabies, zombieGirl);
		}	
		else {		
			final var fetusSpawn = getSpawnProbabilityBasedPregnancy(zombieGirl, 0.3F, 0.1F, 0.5F, 0.9F);
			spawnFetusZombies(fetusSpawn, numOfBabies, zombieGirl);
		}
	}
	
	public static void spawnBabyAndFetusCreepers(@NonNull AbstractTamablePregnantCreeperGirl<?,?> creeperGirl) {
		
		final var numOfBabies = getNumberOfChildrens(creeperGirl.getLastPregnancyStage());
	
		if (creeperGirl instanceof IPregnancyP3) {
			final var alive = getSpawnProbabilityBasedPregnancy(creeperGirl, 0.6F, 0.15F, 0.15F, 0.8F);
			spawnBabyOrFetusCreepers(alive, numOfBabies, creeperGirl);
		}	
		else {		
			final var fetusSpawn = getSpawnProbabilityBasedPregnancy(creeperGirl, 0.3F, 0.15F, 0.5F, 0.9F);
			spawnFetusCreepers(fetusSpawn, numOfBabies, creeperGirl);
		}
	}
	
	
	public static void spawnBabyAndFetusCreepers(@NonNull AbstractMonsterPregnantCreeperGirl creeperGirl) {	
		
		final var currentPregnancyStage = creeperGirl.getCurrentPregnancyStage();
		
		if (currentPregnancyStage == PregnancyStage.getNonPregnancyStage()) {
			return;
		}
			
		final var numOfBabies = getNumberOfChildrens(creeperGirl.getCurrentPregnancyStage());
		final var totalDaysPassed = creeperGirl.getTotalDaysPassed();
		final var t = Mth.clamp(totalDaysPassed / (float) PregnancySystemConstants.TOTAL_PREGNANCY_DAYS, 0, 1);
		float p;
		
		if (currentPregnancyStage.ordinal() > 2) {
			p = MathHelper.sigmoid(0.2F, 0.8F, 0.15F, t, 0.6F);
			spawnBabyOrFetusCreepers(p, numOfBabies, creeperGirl);
		}
		else {
			p = MathHelper.sigmoid(0.5F, 0.9F, 0.15F, t, 0.3F);
			spawnFetusCreepers(p, numOfBabies, creeperGirl);
		}
		
		MinepreggoMod.LOGGER.debug("SPAWN BABY AND FETUS CREEPERS: id={}, class={}, currentPregnancytStage={}, maxPregnancyStage={}, totalDaysPassed={}, t={}",
				creeperGirl.getId(), creeperGirl.getClass().getSimpleName(), currentPregnancyStage, creeperGirl.getLastPregnancyStage(), totalDaysPassed, t);
	}
	
	public static void spawnBabyAndFetusZombies(@NonNull AbstractMonsterPregnantZombieGirl zombie) {	
		
		final var currentPregnancyStage = zombie.getCurrentPregnancyStage();
		
		if (currentPregnancyStage == PregnancyStage.getNonPregnancyStage()) {
			return;
		}
		
		final var numOfBabies = getNumberOfChildrens(zombie.getCurrentPregnancyStage()); 
		final var totalDaysPassed = zombie.getTotalDaysPassed();
		final var t = Mth.clamp(totalDaysPassed / (float) PregnancySystemConstants.TOTAL_PREGNANCY_DAYS, 0, 1);
		float p;
		
		if (currentPregnancyStage.ordinal() > 2) {
			p = MathHelper.sigmoid(0.2F, 0.9F, 0.1F, t, 0.7F);
			spawnBabyOrFetusZombies(p, numOfBabies, zombie);
		}
		else {
			p = MathHelper.sigmoid(0.2F, 0.9F, 0.1F, t, 0.3F);
			spawnFetusZombies(p, numOfBabies, zombie);
		}
		
		MinepreggoMod.LOGGER.debug("SPAWN BABY AND FETUS ZOMBIES: id={}, class={}, currentPregnancytStage={}, maxPregnancyStage={}, totalDaysPassed={}, t={}",
				zombie.getId(), zombie.getClass().getSimpleName(), currentPregnancyStage, zombie.getLastPregnancyStage(), totalDaysPassed, t);
	}
		
	public static<E extends PreggoMob & ITamablePreggoMob> void tryToDamageItemOnMainHand(@NonNull E preggoMob) {	    
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
	
	public static <E extends PreggoMob & ITamablePreggoMob> void tryToDamageArmor(@NonNull E preggoMob, DamageSource damageSource) {
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
	
	public static boolean isPlayerInCreativeOrSpectator(LivingEntity entity) {
	    if (entity instanceof Player player) {
	        return player.isCreative() || player.isSpectator();
	    }
	    return false;
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

	private static<E extends PreggoMob & ITamablePreggoMob> void updateItemHandler(E preggoMob, int slotId, @Nonnull ItemStack stack) {
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
	        if (handler instanceof IItemHandlerModifiable modHandler) {
	            modHandler.setStackInSlot(slotId, stack);
	        }
	    });
	}

	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem> void setItemstackOnOffHand(@Nonnull E preggoMob, @Nonnull ItemStack itemStack) {			
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {			    	
			if (handler instanceof IItemHandlerModifiable modHandler) {
				
				var oldItemstack = modHandler.getStackInSlot(ITamablePreggoMob.OFFHAND_INVENTORY_SLOT);
				
				if (!oldItemstack.isEmpty()) {
					removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.OFFHAND);			
				}
						
	            modHandler.setStackInSlot(ITamablePreggoMob.OFFHAND_INVENTORY_SLOT, itemStack);
	            preggoMob.setItemSlot(EquipmentSlot.OFFHAND, itemStack);
	        }	
		});
	}
	
	
	public static<E extends PreggoMob & ITamablePreggoMob> void removeAndDropItemStackFromEquipmentSlot(E preggoMob, EquipmentSlot slotId) {			
		if (dropItemStack(preggoMob, preggoMob.getItemBySlot(slotId))) {		
			preggoMob.setItemSlot(slotId, ItemStack.EMPTY);
			preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {			    
		        if (handler instanceof IItemHandlerModifiable modHandler) {
		            modHandler.setStackInSlot(slotId.getFilterFlag(), ItemStack.EMPTY);
		        }	
			});			
		}	
	}
	
	public static void removeAndDropItemStackFromEquipmentSlot(Player player, EquipmentSlot slotId) {
		if (dropItemStack(player, player.getItemBySlot(slotId))) {
			player.setItemSlot(slotId, ItemStack.EMPTY);
		}
 	}
		
	private static boolean dropItemStack(LivingEntity entity, ItemStack stack) {
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
	
	public static boolean canUseChestplate(ItemStack armor, PregnancyStage stage) {
		switch (stage) {
		case P0: {
			return armor.is(TagHelper.MIN_P0_ARMOR);
		}
		case P1: {
			return armor.is(TagHelper.MIN_P1_ARMOR);
		}
		case P2: {
			return armor.is(TagHelper.MIN_P2_ARMOR);
		}
		case P3: {
			return armor.is(TagHelper.MIN_P3_ARMOR);
		}
		case P4: {
			return armor.is(TagHelper.MIN_P4_ARMOR);
		}
		case P5: {
			return armor.is(TagHelper.MIN_P5_ARMOR);
		}
		case P6: {
			return armor.is(TagHelper.MIN_P6_ARMOR);
		}
		case P7: {
			return armor.is(TagHelper.MIN_P7_ARMOR);
		}
		case P8: {
			return armor.is(TagHelper.MIN_P8_ARMOR);
		}
		default:
			return false;
		}	
	}
	
	public static boolean canUseLegging(ItemStack armor, PregnancyStage stage) {	
		if (stage == PregnancyStage.P0) {
			return true;
		}
		else {
			return armor.is(TagHelper.KNEE_BRACE);
		}
	}
}

