package dev.dixmk.minepreggo.world.entity.preggo.creeper;

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

public class TamableHumanoidCreeperGirl extends AbstractTamableHumanoidCreeperGirl implements IPostPregnancyEntity {	

	private static final SyncedPostPregnancyData.DataAccessor<TamableHumanoidCreeperGirl> DATA_HOLDER = new SyncedPostPregnancyData.DataAccessor<>(TamableHumanoidCreeperGirl.class);
	
	private final FemaleFertilitySystem<TamableHumanoidCreeperGirl> fertilitySystem = new FemaleFertilitySystem<>(this) {
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
				LivingEntityHelper.copyMobEffects(preggoMob, creeperGirl);
				creeperGirl.setCombatMode(preggoMob.getCombatMode());
				PreggoMobHelper.initPregnancy(creeperGirl);
			}			
		}

		@Override
		protected void afterPostPregnancy() {
			PregnancySystemHelper.removePostPregnancyNeft(preggoMob);			
		}
	};
	
	public TamableHumanoidCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get(), world);
	}

	public TamableHumanoidCreeperGirl(EntityType<TamableHumanoidCreeperGirl> type, Level world) {
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
		return createBasicAttributes(0.24);
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl> void onPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			TamableHumanoidCreeperGirl creeperGirl = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			PreggoMobHelper.copyRotation(source, creeperGirl);
			PreggoMobHelper.copyOwner(source, creeperGirl);
			PreggoMobHelper.copyHealth(source, creeperGirl);
			PreggoMobHelper.copyName(source, creeperGirl);
			PreggoMobHelper.copyTamableData(source, creeperGirl);	
			LivingEntityHelper.copyMobEffects(source, creeperGirl);
			PreggoMobHelper.transferInventory(source, creeperGirl);
			PreggoMobHelper.transferAttackTarget(source, creeperGirl);	
			creeperGirl.setCombatMode(source.getCombatMode());
			creeperGirl.getTamableData().setBodyState(null);
			
			if (!creeperGirl.getGenderedData().tryActivatePostPregnancyPhase(PostPregnancy.PARTUM)) {
				source.discard();
				creeperGirl.discard();
				throw new IllegalStateException("Failed to activate PostPregnancy.PARTUM phase on TamableHumanoidCreeperGirl after giving birth");
			}
			
			PregnancySystemHelper.applyPostPregnancyNerf(creeperGirl);
		}
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl> void onPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			TamableHumanoidCreeperGirl creeperGirl = MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get().spawn(serverLevel, BlockPos.containing(source.getX(), source.getY(), source.getZ()), MobSpawnType.CONVERSION);	
			PreggoMobHelper.copyRotation(source, creeperGirl);
			PreggoMobHelper.copyOwner(source, creeperGirl);
			PreggoMobHelper.copyHealth(source, creeperGirl);
			PreggoMobHelper.copyName(source, creeperGirl);
			PreggoMobHelper.copyTamableData(source, creeperGirl);	
			LivingEntityHelper.copyMobEffects(source, creeperGirl);
			PreggoMobHelper.transferInventory(source, creeperGirl);
			PreggoMobHelper.transferAttackTarget(source, creeperGirl);
			creeperGirl.setCombatMode(source.getCombatMode());
			
			if (!creeperGirl.getGenderedData().tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE)) {
				source.discard();
				creeperGirl.discard();
                throw new IllegalStateException("Failed to activate PostPregnancy.MISCARRIAGE phase on TamableHumanoidCreeperGirl after miscarriage");
			}
			
			PregnancySystemHelper.applyPostPregnancyNerf(creeperGirl);
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
