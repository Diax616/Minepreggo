package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class PreggoMobPregnancySystemP0
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends AbstractPregnancySystem<E> implements IPreggoMobPregnancySystem {
	
	protected PreggoMobPregnancySystemP0(E pregnantEntity) {
		super(pregnantEntity);
	}

	// It has to be executed on server side
	protected void evaluatePregnancySystem() {	
		this.evaluatePregnancyTimer();
		if (canAdvanceNextPregnancyPhase() && !hasToGiveBirth()) {			
			var chestplate = pregnantEntity.getItemBySlot(EquipmentSlot.CHEST);
			var leggings = pregnantEntity.getItemBySlot(EquipmentSlot.LEGS);
			var phases = PregnancyPhase.values();
			var pregnancyData = pregnantEntity.getPregnancyData();
			var current = pregnancyData.getCurrentPregnancyPhase();
			var next = phases[Math.min(current.ordinal() + 1, phases.length - 1)];
			
			
			if (!chestplate.isEmpty()
					&& (!PregnancySystemHelper.canUseChestplate(chestplate.getItem(), next) || pregnancyData.getSyncedPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.MILKING))) {
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.CHEST);
			}
			
			if (!leggings.isEmpty()
					&& !PregnancySystemHelper.canUseLegging(leggings.getItem(), next)) {
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.LEGS);
			}
			
			advanceToNextPregnancyPhase();
			
			pregnantEntity.discard();
			MinepreggoMod.LOGGER.debug("Pregnancy phase advanced from {} for entity {}", pregnancyData.getCurrentPregnancyPhase(), pregnantEntity.getSimpleNameOrCustom());
			return;
		}	
	}
	
	protected void evaluatePregnancyPains() {	
		// This pregnancy phase does not support pregnancy pains yet
	}
	
	protected void evaluatePregnancySymptoms() {
		// This pregnancy phase does not support pregnancy symptoms yet			
	}
	
	protected void evaluatePregnancyNeeds() {
		// This pregnancy phase does not support pregnancy needs yet
	}
	
	public final void onServerTick() {
		if (pregnantEntity.level().isClientSide) {			
			return;
		}	
		
		evaluatePregnancySystem();
	}

	
	protected void evaluatePregnancyTimer() {
		if (this.pregnantEntity.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) {
			return;
		}
		final var pregnancyData = pregnantEntity.getPregnancyData();
        if (pregnancyData.getPregnancyTimer() >= MinepreggoModConfig.SERVER.getTotalTicksByPregnancyDay()) {
        	pregnancyData.resetPregnancyTimer();
        	pregnancyData.incrementDaysPassed();
        	pregnancyData.reduceDaysToGiveBirth();
        	MinepreggoMod.LOGGER.debug("Pregnancy day advanced to {} for entity {}", pregnancyData.getDaysPassed(), pregnantEntity.getSimpleNameOrCustom());
        } else {
        	pregnancyData.incrementPregnancyTimer();
        }
	}

	public boolean isMiscarriageActive() {
	    return false;
	}
	
	public boolean canAdvanceNextPregnancyPhase() {
	    return pregnantEntity.getPregnancyData().getDaysPassed() >= pregnantEntity.getPregnancyData().getDaysByCurrentStage();
	}
	
	public boolean hasPregnancyPain() {
	    return false;
	}
	
	public boolean hasAllPregnancySymptoms() {
	    return false;
	}
	
	@Override
	protected void evaluateMiscarriage(ServerLevel serverLevel) {
		// This pregnancy phase does not support water breaking yet
		
	}
	
	@Override
	protected void initPostMiscarriage() {
		// This pregnancy phase does not support miscarriage yet		
	}

	@Override
	protected boolean tryInitRandomPregnancyPain() {
		return false;
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		return false;
	}
	
	// RIGHT CLICK	
	public InteractionResult onRightClick(Player source) {	
		if (!isRightClickValid(source)) {
			return InteractionResult.FAIL;
		}				
		var level = pregnantEntity.level();

		// Belly rubs has priority over other right click actions
		if (PregnancySystemHelper.canTouchBelly(source, pregnantEntity)) {		
			if (level instanceof ServerLevel serverLevel && !serverLevel.isClientSide) {		
				PreggoMobSystem.spawnParticles(pregnantEntity, evaluateBellyRubs(serverLevel, source));
			}	
			
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
		return InteractionResult.PASS;
	}
	
	public boolean isRightClickValid(Player source) {
		return pregnantEntity.isOwnedBy(source);
	}
	
	// ON HURT	
	public void evaluateOnSuccessfulHurt(DamageSource damagesource) {	
		
	}
	
	
	protected Result evaluateBellyRubs(Level level, Player source) {
		// In this pregnancy phase, the belly is not large enough to do some action
		return Result.FAIL;
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return false;
	}
	
	@Override
	protected boolean isInLabor() {
		return false;
 	}
	
	@Override
	protected boolean isWaterBroken() {
		return false;
 	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		// This pregnancy phase does not support water breaking yet
	}
	
	@Override
	protected void breakWater() {
		// This pregnancy phase does not support water breaking yet
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {	
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void startLabor() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void initPostPartum() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void startMiscarriage() {
		// This pregnancy phase does not support birth yet	
	}
}
