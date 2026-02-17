package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import java.util.Optional;
import java.util.OptionalInt;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.FemaleFertilitySystem;
import dev.dixmk.minepreggo.world.entity.preggo.SyncedFemaleEntityImpl;
import dev.dixmk.minepreggo.world.entity.preggo.ISyncedFemaleEntity;
import dev.dixmk.minepreggo.world.entity.preggo.IPostPregnancyEntity;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.SyncedPostPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public class TamableZombieGirl extends AbstractTamableZombieGirl implements IPostPregnancyEntity {

	private static final SyncedPostPregnancyData.DataAccessor<TamableZombieGirl> DATA_HOLDER = new SyncedPostPregnancyData.DataAccessor<>(TamableZombieGirl.class);
	
	private final FemaleFertilitySystem<TamableZombieGirl> fertilitySystem = new FemaleFertilitySystem<>(this) {
		@Override
		protected void startPregnancy() {
			if (preggoMob.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide) {
				var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
				LivingEntityHelper.copyRotation(preggoMob, zombieGirl);
				PreggoMobHelper.copyOwner(preggoMob, zombieGirl);
				LivingEntityHelper.copyHealth(preggoMob, zombieGirl);
				EntityHelper.copyName(preggoMob, zombieGirl);
				PreggoMobHelper.copyTamableData(preggoMob, zombieGirl);				
				PreggoMobHelper.transferInventory(preggoMob, zombieGirl);
				LivingEntityHelper.transferAttackTarget(preggoMob, zombieGirl);
				PreggoMobHelper.initPregnancy(zombieGirl);
			}
		}

		@Override
		protected void afterPostPregnancy() {
			PregnancySystemHelper.removePostPregnancyNeft(preggoMob);			
		}
	};
	
	public TamableZombieGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get(), world);
	}
	
	public TamableZombieGirl(EntityType<TamableZombieGirl> type, Level world) {
		super(type, world);	
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
		return new PreggoMobSystem<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP0(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
	}

	@Override
	protected IFemaleEntity createFemaleEntityData() {
		return new SyncedFemaleEntityImpl<>(DATA_HOLDER, this);
	}
		
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		DATA_HOLDER.defineSynchedData(this);
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      if (this.isAlive()) {	 
          this.fertilitySystem.onServerTick();
      }
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
		return AbstractTamableZombieGirl.getBasicAttributes(0.235);
	}
	
	static<E extends AbstractTamablePregnantZombieGirl> void onPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			LivingEntityHelper.copyRotation(source, zombieGirl);
			PreggoMobHelper.copyOwner(source, zombieGirl);
			LivingEntityHelper.copyHealth(source, zombieGirl);
			EntityHelper.copyName(source, zombieGirl);
			PreggoMobHelper.copyTamableData(source, zombieGirl);	
			LivingEntityHelper.copyMobEffects(source, zombieGirl);
			PreggoMobHelper.transferInventory(source, zombieGirl);
			LivingEntityHelper.transferAttackTarget(source, zombieGirl);
							
			if (!zombieGirl.getGenderedData().tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE)) {
				source.discard();
				zombieGirl.discard();
				throw new IllegalStateException("Failed to activate PostPregnancy.MISCARRIAGE phase on TamableZombieGirl after miscarriage");
			}
					
			PregnancySystemHelper.applyPostPregnancyNerf(zombieGirl);
		}
	}
	
	static<E extends AbstractTamablePregnantZombieGirl> void onPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			LivingEntityHelper.copyRotation(source, zombieGirl);
			PreggoMobHelper.copyOwner(source, zombieGirl);
			LivingEntityHelper.copyHealth(source, zombieGirl);
			EntityHelper.copyName(source, zombieGirl);
			PreggoMobHelper.copyTamableData(source, zombieGirl);
			LivingEntityHelper.copyMobEffects(source, zombieGirl);
			PreggoMobHelper.transferInventory(source, zombieGirl);
			LivingEntityHelper.transferAttackTarget(source, zombieGirl);
			zombieGirl.getTamableData().setBodyState(null);
			
			if (!zombieGirl.getGenderedData().tryActivatePostPregnancyPhase(PostPregnancy.PARTUM)) {
				source.discard();
				zombieGirl.discard();
				throw new IllegalStateException("Failed to activate PostPregnancy.PARTUM phase on TamableZombieGirl after postpartum");
			}
				
			PregnancySystemHelper.applyPostPregnancyNerf(zombieGirl);
		}
	}

	@Override
	public Optional<PostPregnancy> getSyncedPostPregnancy() {
		return this.entityData.get(DATA_HOLDER.getDataPostPregnancy());
	}

	@Override
	public OptionalInt getSyncedPostPartumLactation() {
		return this.entityData.get(DATA_HOLDER.getDataLactation());
	}

	@Override
	public ISyncedFemaleEntity<?> getSyncedFemaleEntity() {
		return (SyncedFemaleEntityImpl<?>) this.femaleEntityData;
	}
}
