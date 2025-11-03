package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;

import java.util.UUID;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.world.entity.preggo.FertilitySystem;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;

public class TamableCreeperGirlP0 extends AbstractTamableHumanoidCreeperGirl<PreggoMobSystem<TamableCreeperGirlP0>> implements IBreedable {
	
	private static final UUID SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("fa6a4626-c325-4835-8259-69577a99c9c8");
	private static final AttributeModifier SPEED_MODIFIER_TIRENESS = new AttributeModifier(SPEED_MODIFIER_TIRENESS_UUID, "Tireness speed boost", -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final UUID MAX_HEALTH_MODIFIER_TIRENESS_UUID = UUID.fromString("94d78c8b-0983-4ae4-af65-8e477ee52f2e");
	private static final AttributeModifier MAX_HEALTH_MODIFIER_TIRENESS = new AttributeModifier(MAX_HEALTH_MODIFIER_TIRENESS_UUID, "Tireness max health", -0.3D, AttributeModifier.Operation.MULTIPLY_BASE);
	
	private static final EntityDataAccessor<PostPregnancy> DATA_POST_PREGNANCY = SynchedEntityData.defineId(TamableCreeperGirlP0.class, MinepreggoModEntityDataSerializers.POST_PREGNANCY);
	protected static final EntityDataAccessor<Boolean> DATA_PREGNANT = SynchedEntityData.defineId(TamableCreeperGirlP0.class, EntityDataSerializers.BOOLEAN);

	private int postPregnancyTimer = 0;
	private int pregnancyInitializerTimer = 0;
	
	private final FertilitySystem<TamableCreeperGirlP0> fertilitySystem;
	
	public TamableCreeperGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), world);
	}

	public TamableCreeperGirlP0(EntityType<TamableCreeperGirlP0> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		fertilitySystem = new FertilitySystem<>(this) {
			@Override
			protected void startPregnancy() {		
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferBasicData(preggoMob, creeperGirl);			
					PreggoMobHelper.transferInventary(preggoMob, creeperGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, creeperGirl);
				}			
			}
		};
	}
	
	@Override
	@Nonnull
	protected PreggoMobSystem<TamableCreeperGirlP0> createPreggoMobSystem() {
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
		compound.putBoolean("DataPregnant", this.entityData.get(DATA_PREGNANT));
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.entityData.set(DATA_POST_PREGNANCY, PostPregnancy.values()[compound.getInt("DataPostPregnancy")]);
		this.entityData.set(DATA_PREGNANT, compound.getBoolean("DataPregnant"));
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      
      if (this.level().isClientSide()) {
    	  return;
      }
      
      fertilitySystem.onTick();
      
      if (this.entityData.get(DATA_POST_PREGNANCY) != PostPregnancy.NONE) {	  
    	  if (postPregnancyTimer > 7000) {
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
		return getBasicAttributes(0.24);
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
	
	public static TamableCreeperGirlP0 spawnPostMiscarriage(ServerLevel serverLevel, double x, double y, double z) {
		var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
		creeperGirl.getEntityData().set(DATA_POST_PREGNANCY, PostPregnancy.MISCARRIAGE);
		addPostPregnancyAttibutes(creeperGirl);
		return creeperGirl;
	}
	
	public static TamableCreeperGirlP0 spawnPostPartum(ServerLevel serverLevel, double x, double y, double z) {
		var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0	.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);	
		creeperGirl.getEntityData().set(DATA_POST_PREGNANCY, PostPregnancy.PARTUM);
		addPostPregnancyAttibutes(creeperGirl);
		return creeperGirl;
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?>> void onPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var creeperGirl = TamableCreeperGirlP0.spawnPostPartum(serverLevel, source.getX(), source.getY(), source.getZ());
			PreggoMobHelper.transferBasicData(source, creeperGirl);
			PreggoMobHelper.transferInventary(source, creeperGirl);
			PreggoMobHelper.transferAttackTarget(source, creeperGirl);
		}
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?>> void onPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var creeperGirl = TamableCreeperGirlP0.spawnPostMiscarriage(serverLevel, source.getX(), source.getY(), source.getZ());
			PreggoMobHelper.transferBasicData(source, creeperGirl);
			PreggoMobHelper.transferInventary(source, creeperGirl);
			PreggoMobHelper.transferAttackTarget(source, creeperGirl);
		}
	}
	
	private static void addPostPregnancyAttibutes(TamableCreeperGirlP0 creeperGirl) {
		AttributeInstance speed = creeperGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = creeperGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.addTransientModifier(TamableCreeperGirlP0.SPEED_MODIFIER_TIRENESS);	
		maxHealth.addTransientModifier(TamableCreeperGirlP0.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
	
	private static void removePostPregnancyAttibutes(TamableCreeperGirlP0 creeperGirl) {
		AttributeInstance speed = creeperGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = creeperGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.removeModifier(TamableCreeperGirlP0.SPEED_MODIFIER_TIRENESS);	
		maxHealth.removeModifier(TamableCreeperGirlP0.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
}
