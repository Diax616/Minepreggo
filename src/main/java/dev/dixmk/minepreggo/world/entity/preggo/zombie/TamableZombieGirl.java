package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.world.entity.preggo.FertilitySystem;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;

public class TamableZombieGirl extends AbstractTamableZombieGirl<PreggoMobSystem<TamableZombieGirl>> {

	private static final UUID SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("c80701e3-c90d-420e-97c2-4f232fcbaffe");
	private static final AttributeModifier SPEED_MODIFIER_TIRENESS = new AttributeModifier(SPEED_MODIFIER_TIRENESS_UUID, "Tireness speed", -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);		
	private static final UUID MAX_HEALTH_MODIFIER_TIRENESS_UUID = UUID.fromString("598672e2-1413-4f1e-8e77-3166716d0bd9");
	private static final AttributeModifier MAX_HEALTH_MODIFIER_TIRENESS = new AttributeModifier(MAX_HEALTH_MODIFIER_TIRENESS_UUID, "Tireness max health", -0.3D, AttributeModifier.Operation.MULTIPLY_BASE);	
	
	// These both EntityDataAccessor works like bringing data from server to client using FemaleEntityImpl field as source of truth
	private static final EntityDataAccessor<Optional<PostPregnancy>> DATA_POST_PREGNANCY = SynchedEntityData.defineId(TamableZombieGirl.class, MinepreggoModEntityDataSerializers.OPTIONAL_POST_PREGNANCY);
	protected static final EntityDataAccessor<Boolean> DATA_PREGNANT = SynchedEntityData.defineId(TamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	
	private final FertilitySystem<TamableZombieGirl> fertilitySystem;
	
	public TamableZombieGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get(), world);
	}
	
	public TamableZombieGirl(EntityType<TamableZombieGirl> type, Level world) {
		super(type, world);	
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		fertilitySystem = new FertilitySystem<>(this) {
			@Override
			protected void startPregnancy() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyData(preggoMob, zombieGirl);			
					PreggoMobHelper.transferInventary(preggoMob, zombieGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, zombieGirl);
				}
			}
		};
	}
	
	@Override
	protected PreggoMobSystem<TamableZombieGirl> createPreggoMobSystem() {
		return new PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP0());
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_POST_PREGNANCY, Optional.empty());
		this.entityData.define(DATA_PREGNANT, false);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.entityData.set(DATA_PREGNANT, this.defaultFemaleEntityImpl.isPregnant());
        this.entityData.set(DATA_POST_PREGNANCY, Optional.ofNullable(this.defaultFemaleEntityImpl.getPostPregnancyPhase()));
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      
      if (this.level().isClientSide()) {
    	  return;
      }
      
      this.fertilitySystem.onServerTick();
      
      if (getPostPregnancyPhase() != null) {	  
    	  if (getPostPregnancyTimer() > 7000) {
    		  setPostPregnancyTimer(0);
    		  this.entityData.set(DATA_POST_PREGNANCY, Optional.empty());
    		  removePostPregnancyAttibutes(this);
    	  }
    	  else {
    		  incrementPostPregnancyTimer();
    	  }   	  
      } 
	}

	@Override
	public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
		var result = this.defaultFemaleEntityImpl.tryActivatePostPregnancyPhase(postPregnancy);
		this.entityData.set(DATA_POST_PREGNANCY, Optional.ofNullable(this.defaultFemaleEntityImpl.getPostPregnancyPhase()));
		return result;
	}
	
	@Override
	public @Nullable PostPregnancy getPostPregnancyPhase() {
		var optional = this.entityData.get(DATA_POST_PREGNANCY);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableZombieGirl.getBasicAttributes(0.235);
	}
	
	static TamableZombieGirl spawnPostMiscarriage(ServerLevel serverLevel, double x, double y, double z) {
		var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);	
		zombieGirl.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
		addPostPregnancyAttibutes(zombieGirl);
		return zombieGirl;
	}
	
	static TamableZombieGirl spawnPostPartum(ServerLevel serverLevel, double x, double y, double z) {
		var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);	
		zombieGirl.tryActivatePostPregnancyPhase(PostPregnancy.PARTUM);
		addPostPregnancyAttibutes(zombieGirl);
		return zombieGirl;
	}
	
	static<E extends AbstractTamablePregnantZombieGirl<?,?>> void onPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var zombieGirl = TamableZombieGirl.spawnPostMiscarriage(serverLevel, source.getX(), source.getY(), source.getZ());
			PreggoMobHelper.copyData(source, zombieGirl);
			PreggoMobHelper.transferInventary(source, zombieGirl);
			PreggoMobHelper.transferAttackTarget(source, zombieGirl);
		}
	}
	
	static<E extends AbstractTamablePregnantZombieGirl<?,?>> void onPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var zombieGirl = TamableZombieGirl.spawnPostPartum(serverLevel, source.getX(), source.getY(), source.getZ());
			PreggoMobHelper.copyData(source, zombieGirl);
			PreggoMobHelper.transferInventary(source, zombieGirl);
			PreggoMobHelper.transferAttackTarget(source, zombieGirl);
		}
	}
	
	private static void addPostPregnancyAttibutes(TamableZombieGirl zombieGirl) {
		AttributeInstance speed = zombieGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = zombieGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.addTransientModifier(TamableZombieGirl.SPEED_MODIFIER_TIRENESS);	
		maxHealth.addTransientModifier(TamableZombieGirl.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
	
	private static void removePostPregnancyAttibutes(TamableZombieGirl zombieGirl) {
		AttributeInstance speed = zombieGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = zombieGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.removeModifier(TamableZombieGirl.SPEED_MODIFIER_TIRENESS);	
		maxHealth.removeModifier(TamableZombieGirl.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
}
