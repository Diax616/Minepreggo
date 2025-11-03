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

import java.util.UUID;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.world.entity.preggo.FertilitySystem;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;

public class TamableZombieGirlP0 extends AbstractTamableZombieGirl<PreggoMobSystem<TamableZombieGirlP0>> implements IBreedable {

	private static final UUID SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("c80701e3-c90d-420e-97c2-4f232fcbaffe");
	private static final AttributeModifier SPEED_MODIFIER_TIRENESS = new AttributeModifier(SPEED_MODIFIER_TIRENESS_UUID, "Tireness speed", -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);		
	private static final UUID MAX_HEALTH_MODIFIER_TIRENESS_UUID = UUID.fromString("598672e2-1413-4f1e-8e77-3166716d0bd9");
	private static final AttributeModifier MAX_HEALTH_MODIFIER_TIRENESS = new AttributeModifier(MAX_HEALTH_MODIFIER_TIRENESS_UUID, "Tireness max health", -0.3D, AttributeModifier.Operation.MULTIPLY_BASE);	
	private static final EntityDataAccessor<PostPregnancy> DATA_POST_PREGNANCY = SynchedEntityData.defineId(TamableZombieGirlP0.class, MinepreggoModEntityDataSerializers.POST_PREGNANCY);
	protected static final EntityDataAccessor<Boolean> DATA_PREGNANT = SynchedEntityData.defineId(TamableZombieGirlP0.class, EntityDataSerializers.BOOLEAN);

	
	private int postPregnancyTimer = 0;
	private int pregnancyInitializerTimer = 0;
	
	private final FertilitySystem<TamableZombieGirlP0> fertilitySystem;
	
	public TamableZombieGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), world);
	}
	
	public TamableZombieGirlP0(EntityType<TamableZombieGirlP0> type, Level world) {
		super(type, world);	
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		fertilitySystem = new FertilitySystem<>(this) {
			@Override
			protected void startPregnancy() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferBasicData(preggoMob, zombieGirl);			
					PreggoMobHelper.transferInventary(preggoMob, zombieGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, zombieGirl);
				}
			}
		};
	}
	
	@Override
	protected PreggoMobSystem<TamableZombieGirlP0> createPreggoMobSystem() {
		return new PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP0());
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_POST_PREGNANCY, PostPregnancy.NONE);
		this.entityData.define(DATA_PREGNANT, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("DataPostPregnancy", this.entityData.get(DATA_POST_PREGNANCY).ordinal());
		compound.putInt("DataPregnancyInitializerTimer", pregnancyInitializerTimer);
		compound.putInt("DataPostPregnancyTimer", postPregnancyTimer);
		compound.putBoolean("DataPregnant", this.entityData.get(DATA_PREGNANT));
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.entityData.set(DATA_POST_PREGNANCY, PostPregnancy.values()[compound.getInt("DataPostPregnancy")]);
		this.pregnancyInitializerTimer = compound.getInt("DataPregnancyInitializerTimer");
		this.postPregnancyTimer = compound.getInt("DataPostPregnancyTimer");
		this.entityData.set(DATA_PREGNANT, compound.getBoolean("DataPregnant"));
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      
      if (this.level().isClientSide()) {
    	  return;
      }
      
      this.fertilitySystem.onTick();
      
      if (this.entityData.get(DATA_POST_PREGNANCY) != PostPregnancy.NONE) {	  
    	  if (postPregnancyTimer > 6000) {
    		  postPregnancyTimer = 0;
    		  this.entityData.set(DATA_POST_PREGNANCY, PostPregnancy.NONE);
    		  removePostPregnancyAttibutes(this);
    	  }
    	  else {
    		  ++postPregnancyTimer;
    	  }   	  
      } 
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableZombieGirl.getBasicAttributes(0.235);
	}
	
	@Override
	public int getPregnancyInitializerTimer() {
		return this.pregnancyInitializerTimer;
	}

	@Override
	public void setPregnancyInitializerTimer(int ticks) {
		this.pregnancyInitializerTimer = ticks;		
	}
	
	@Override
	public boolean isPregnant() {
	    return this.entityData.get(DATA_PREGNANT);
	}
	
	@Override
	public void impregnate() {
		this.entityData.set(DATA_PREGNANT, true);		
	}
	
	@Override
	public boolean canGetPregnant() {
		return this.entityData.get(DATA_POST_PREGNANCY) == PostPregnancy.NONE;
	}
	
	static TamableZombieGirlP0 spawnPostMiscarriage(ServerLevel serverLevel, double x, double y, double z) {
		var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);	
		zombieGirl.getEntityData().set(DATA_POST_PREGNANCY, PostPregnancy.MISCARRIAGE);
		addPostPregnancyAttibutes(zombieGirl);
		return zombieGirl;
	}
	
	static TamableZombieGirlP0 spawnPostPartum(ServerLevel serverLevel, double x, double y, double z) {
		var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);	
		zombieGirl.getEntityData().set(DATA_POST_PREGNANCY, PostPregnancy.PARTUM);
		addPostPregnancyAttibutes(zombieGirl);
		return zombieGirl;
	}
	
	static<E extends AbstractTamablePregnantZombieGirl<?,?>> void onPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var zombieGirl = TamableZombieGirlP0.spawnPostMiscarriage(serverLevel, source.getX(), source.getY(), source.getZ());
			PreggoMobHelper.transferBasicData(source, zombieGirl);
			PreggoMobHelper.transferInventary(source, zombieGirl);
			PreggoMobHelper.transferAttackTarget(source, zombieGirl);
		}
	}
	
	static<E extends AbstractTamablePregnantZombieGirl<?,?>> void onPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var zombieGirl = TamableZombieGirlP0.spawnPostPartum(serverLevel, source.getX(), source.getY(), source.getZ());
			PreggoMobHelper.transferBasicData(source, zombieGirl);
			PreggoMobHelper.transferInventary(source, zombieGirl);
			PreggoMobHelper.transferAttackTarget(source, zombieGirl);
		}
	}
	
	private static void addPostPregnancyAttibutes(TamableZombieGirlP0 zombieGirl) {
		AttributeInstance speed = zombieGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = zombieGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.addTransientModifier(TamableZombieGirlP0.SPEED_MODIFIER_TIRENESS);	
		maxHealth.addTransientModifier(TamableZombieGirlP0.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
	
	private static void removePostPregnancyAttibutes(TamableZombieGirlP0 zombieGirl) {
		AttributeInstance speed = zombieGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = zombieGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.removeModifier(TamableZombieGirlP0.SPEED_MODIFIER_TIRENESS);	
		maxHealth.removeModifier(TamableZombieGirlP0.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
}
