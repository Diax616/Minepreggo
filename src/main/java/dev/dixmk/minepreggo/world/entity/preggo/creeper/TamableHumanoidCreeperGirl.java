package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.FemaleFertilitySystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public class TamableHumanoidCreeperGirl extends AbstractTamableHumanoidCreeperGirl<PreggoMobSystem<TamableHumanoidCreeperGirl>> {	
	// These both EntityDataAccessor works like bringing data from server to client using FemaleEntityImpl field as source of truth
	private static final EntityDataAccessor<Optional<PostPregnancy>> DATA_POST_PREGNANCY = SynchedEntityData.defineId(TamableHumanoidCreeperGirl.class, MinepreggoModEntityDataSerializers.OPTIONAL_POST_PREGNANCY);
	private static final EntityDataAccessor<Boolean> DATA_PREGNANT = SynchedEntityData.defineId(TamableHumanoidCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> DATA_POST_PARTUM_LACTATION = SynchedEntityData.defineId(TamableHumanoidCreeperGirl.class, EntityDataSerializers.INT);
	
	private final FemaleFertilitySystem<TamableHumanoidCreeperGirl> fertilitySystem;
	
	public TamableHumanoidCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get(), world);
	}

	public TamableHumanoidCreeperGirl(EntityType<TamableHumanoidCreeperGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		fertilitySystem = new FemaleFertilitySystem<>(this) {
			@Override
			protected void startPregnancy() {		
				if (preggoMob.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.copyRotation(preggoMob, creeperGirl);
					PreggoMobHelper.copyOwner(preggoMob, creeperGirl);
					PreggoMobHelper.copyHealth(preggoMob, creeperGirl);
					PreggoMobHelper.copyName(preggoMob, creeperGirl);
					PreggoMobHelper.copyTamableData(preggoMob, creeperGirl);				
					PreggoMobHelper.transferInventory(preggoMob, creeperGirl);					
					PreggoMobHelper.transferAttackTarget(preggoMob, creeperGirl);
					PreggoMobHelper.initPregnancy(creeperGirl);
				}			
			}

			@Override
			protected void afterPostPregnancy() {
				PregnancySystemHelper.removePostPregnancyNeft(preggoMob);			
			}
		};
	}
	
	@Override
	@Nonnull
	protected PreggoMobSystem<TamableHumanoidCreeperGirl> createPreggoMobSystem() {
		return new PreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP0(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_POST_PREGNANCY, Optional.empty());
		this.entityData.define(DATA_PREGNANT, false);
		this.entityData.define(DATA_POST_PARTUM_LACTATION, 0);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.entityData.set(DATA_PREGNANT, this.defaultFemaleEntityImpl.isPregnant());
        this.entityData.set(DATA_POST_PREGNANCY, Optional.ofNullable(this.defaultFemaleEntityImpl.getPostPregnancyPhase()));
        this.entityData.set(DATA_POST_PARTUM_LACTATION, this.defaultFemaleEntityImpl.getPostPregnancyData().map(PostPregnancyData::getPostPartumLactation).orElse(0));
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      fertilitySystem.onServerTick();  
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {				
		var retval = fertilitySystem.onRightClick(sourceentity);			
		if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
			return retval;
		}			
		return super.mobInteract(sourceentity, hand);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.24);
	}
	
	/*
	 * START WARNING: These methods use EntityDataAccessor as source of truth for FemaleEntityImpl fields that need to be synched to client
	 * they will be removed from FemaleEntityImpl in future versions and replaced with similar methods.
	 * */
	
	@Override
	public boolean tryImpregnate(@Nonnegative int fertilizedEggs, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father) {
		boolean result = this.defaultFemaleEntityImpl.tryImpregnate(fertilizedEggs, father);
		if (result) {
			this.entityData.set(DATA_PREGNANT, true);
		}
		return result;
	}
	
	@Override
	public boolean isPregnant() {
		return this.entityData.get(DATA_PREGNANT);
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
	
	@Override
	public int getPostPartumLactation() {
		return this.entityData.get(DATA_POST_PARTUM_LACTATION);
	}
	
	@Override
	public void setPostPartumLactation(int amount) {
		super.setPostPartumLactation(amount);
		this.entityData.set(DATA_POST_PARTUM_LACTATION, amount);
	}
	
	// END WARNING
	
	
	
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?>> void onPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			TamableHumanoidCreeperGirl creeperGirl = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			PreggoMobHelper.copyRotation(source, creeperGirl);
			PreggoMobHelper.copyOwner(source, creeperGirl);
			PreggoMobHelper.copyHealth(source, creeperGirl);
			PreggoMobHelper.copyName(source, creeperGirl);
			PreggoMobHelper.copyTamableData(source, creeperGirl);		
			PreggoMobHelper.transferInventory(source, creeperGirl);
			PreggoMobHelper.transferAttackTarget(source, creeperGirl);	
			
			if (!creeperGirl.tryActivatePostPregnancyPhase(PostPregnancy.PARTUM)) {
                creeperGirl.discard();
				throw new IllegalStateException("Failed to activate PostPregnancy.PARTUM phase on TamableHumanoidCreeperGirl after giving birth");
			}
			
			PregnancySystemHelper.applyPostPregnancyNerf(creeperGirl);
		}
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?>> void onPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			TamableHumanoidCreeperGirl creeperGirl = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			PreggoMobHelper.copyRotation(source, creeperGirl);
			PreggoMobHelper.copyOwner(source, creeperGirl);
			PreggoMobHelper.copyHealth(source, creeperGirl);
			PreggoMobHelper.copyName(source, creeperGirl);
			PreggoMobHelper.copyTamableData(source, creeperGirl);		
			PreggoMobHelper.transferInventory(source, creeperGirl);
			PreggoMobHelper.transferAttackTarget(source, creeperGirl);
			
			if (!creeperGirl.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE)) {
                creeperGirl.discard();
                throw new IllegalStateException("Failed to activate PostPregnancy.MISCARRIAGE phase on TamableHumanoidCreeperGirl after miscarriage");
			}
			
			PregnancySystemHelper.applyPostPregnancyNerf(creeperGirl);
		}
	}
}
