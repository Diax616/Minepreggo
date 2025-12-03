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

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.world.entity.preggo.FemaleFertilitySystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public class TamableCreeperGirl extends AbstractTamableHumanoidCreeperGirl<PreggoMobSystem<TamableCreeperGirl>> {
	
	private static final UUID SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("fa6a4626-c325-4835-8259-69577a99c9c8");
	private static final AttributeModifier SPEED_MODIFIER_TIRENESS = new AttributeModifier(SPEED_MODIFIER_TIRENESS_UUID, "Tireness speed boost", -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final UUID MAX_HEALTH_MODIFIER_TIRENESS_UUID = UUID.fromString("94d78c8b-0983-4ae4-af65-8e477ee52f2e");
	private static final AttributeModifier MAX_HEALTH_MODIFIER_TIRENESS = new AttributeModifier(MAX_HEALTH_MODIFIER_TIRENESS_UUID, "Tireness max health", -0.3D, AttributeModifier.Operation.MULTIPLY_BASE);
	
	// These both EntityDataAccessor works like bringing data from server to client using FemaleEntityImpl field as source of truth
	private static final EntityDataAccessor<Optional<PostPregnancy>> DATA_POST_PREGNANCY = SynchedEntityData.defineId(TamableCreeperGirl.class, MinepreggoModEntityDataSerializers.OPTIONAL_POST_PREGNANCY);
	protected static final EntityDataAccessor<Boolean> DATA_PREGNANT = SynchedEntityData.defineId(TamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	
	private final FemaleFertilitySystem<TamableCreeperGirl> fertilitySystem;
	
	public TamableCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL.get(), world);
	}

	public TamableCreeperGirl(EntityType<TamableCreeperGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		fertilitySystem = new FemaleFertilitySystem<>(this) {
			@Override
			protected void startPregnancy() {		
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyRotation(preggoMob, creeperGirl);
					PreggoMobHelper.copyOwner(preggoMob, creeperGirl);
					PreggoMobHelper.copyHealth(preggoMob, creeperGirl);
					PreggoMobHelper.copyName(preggoMob, creeperGirl);
					PreggoMobHelper.copyTamableData(preggoMob, creeperGirl);				
					PreggoMobHelper.transferInventory(preggoMob, creeperGirl);					
					PreggoMobHelper.transferAttackTarget(preggoMob, creeperGirl);
				}			
			}
		};
	}
	
	@Override
	@Nonnull
	protected PreggoMobSystem<TamableCreeperGirl> createPreggoMobSystem() {
		return new PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP0(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
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
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      
      if (this.level().isClientSide) {
    	  return;
      }
      
      fertilitySystem.onServerTick();
      
      if (getPostPregnancyPhase() != null) {	  
    	  if (getPostPregnancyTimer() > 7000) {
    		  setPostPregnancyTimer(0);
    		  tryRemovePostPregnancyPhase();
    		  removePostPregnancyAttibutes(this);
    	  }
    	  else {
    		  incrementPostPregnancyTimer();
    	  }   	  
      } 
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.24);
	}
	
	@Override
	public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
		var result = this.defaultFemaleEntityImpl.tryActivatePostPregnancyPhase(postPregnancy);
		if (result) {
			this.entityData.set(DATA_POST_PREGNANCY, Optional.ofNullable(postPregnancy));
		}
		return result;
	}
	
	@Override
	public boolean tryRemovePostPregnancyPhase() {
		var result = this.defaultFemaleEntityImpl.tryRemovePostPregnancyPhase();	
		if (result) {
			this.entityData.set(DATA_POST_PREGNANCY, Optional.empty());
		}
		return result;
	}
	
	@Override
	public @Nullable PostPregnancy getPostPregnancyPhase() {
		return this.entityData.get(DATA_POST_PREGNANCY).orElse(null);
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?>> void onPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			TamableCreeperGirl creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			PreggoMobHelper.copyRotation(source, creeperGirl);
			PreggoMobHelper.copyOwner(source, creeperGirl);
			PreggoMobHelper.copyHealth(source, creeperGirl);
			PreggoMobHelper.copyName(source, creeperGirl);
			PreggoMobHelper.copyTamableData(source, creeperGirl);		
			PreggoMobHelper.transferInventory(source, creeperGirl);
			PreggoMobHelper.transferAttackTarget(source, creeperGirl);	
			creeperGirl.tryActivatePostPregnancyPhase(PostPregnancy.PARTUM);
			addPostPregnancyAttibutes(creeperGirl);
		}
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?>> void onPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			TamableCreeperGirl creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			PreggoMobHelper.copyRotation(source, creeperGirl);
			PreggoMobHelper.copyOwner(source, creeperGirl);
			PreggoMobHelper.copyHealth(source, creeperGirl);
			PreggoMobHelper.copyName(source, creeperGirl);
			PreggoMobHelper.copyTamableData(source, creeperGirl);		
			PreggoMobHelper.transferInventory(source, creeperGirl);
			PreggoMobHelper.transferAttackTarget(source, creeperGirl);
			creeperGirl.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
			addPostPregnancyAttibutes(creeperGirl);
		}
	}
	
	private static void addPostPregnancyAttibutes(TamableCreeperGirl creeperGirl) {
		AttributeInstance speed = creeperGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = creeperGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.addTransientModifier(TamableCreeperGirl.SPEED_MODIFIER_TIRENESS);	
		maxHealth.addTransientModifier(TamableCreeperGirl.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
	
	private void removePostPregnancyAttibutes(TamableCreeperGirl creeperGirl) {
		AttributeInstance speed = creeperGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = creeperGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.removeModifier(TamableCreeperGirl.SPEED_MODIFIER_TIRENESS);	
		maxHealth.removeModifier(TamableCreeperGirl.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
}
