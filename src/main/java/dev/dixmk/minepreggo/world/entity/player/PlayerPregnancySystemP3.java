package dev.dixmk.minepreggo.world.entity.player;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;

public class PlayerPregnancySystemP3 extends PlayerPregnancySystemP2 {

	protected @Nonnegative int totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP3();
	protected @Nonnegative float fetalMovementProb = PregnancySystemHelper.LOW_PREGNANCY_PAIN_PROBABILITY;
	protected @Nonnegative int totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P3;

	public PlayerPregnancySystemP3(@NonNull ServerPlayer player) {
		super(player);
		var pregnancyType = PlayerHelper.addInterspeciesPregnancy(player);
		if (pregnancyType != null) {
			switch(pregnancyType) {
				case ZOMBIE:
					fetalMovementProb *= 1.4f;
					break;
				case CREEPER:
					fetalMovementProb *= 1.8f;
					break;
				case ENDER:
					fetalMovementProb *= 2.2f;
					break;
				default:
					break;
			}
		}	
		addNewValidPregnancySymptoms(PregnancySymptom.BELLY_RUBS);
	}

	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP3();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP3();
		morningSicknessProb = PregnancySystemHelper.HIGH_MORNING_SICKNESS_PROBABILITY;
		pregnancyExhaustion = 1.03f;
	}
	
	@Override
	protected void evaluatePregnancySystem() {
		super.evaluatePregnancySystem();
		tryPlayStomachGrowlsSound();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateBellyRubsTimer();
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		super.evaluatePregnancySymptoms();
		if (pregnancySystem.getPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.BELLY_RUBS) && pregnancySystem.getBellyRubs() <= PregnancySystemHelper.DESACTIVATEL_BELLY_RUBS_SYMPTOM) {	
			pregnancySystem.getPregnancySymptoms().removePregnancySymptom(PregnancySymptom.BELLY_RUBS);
			pregnantEntity.removeEffect(MinepreggoModMobEffects.BELLY_RUBS.get());
			pregnancySystem.syncState(pregnantEntity);
			pregnancySystem.syncEffect(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.BELLY_RUBS.name());
		}
	}
	
	protected void evaluateBellyRubsTimer() {   				
		if (pregnancySystem.getBellyRubs() < PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL) {
	        if (pregnancySystem.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	pregnancySystem.incrementBellyRubs();
	        	pregnancySystem.resetBellyRubsTimer();
	        	pregnancySystem.syncEffect(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} belly rubs level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancySystem.getBellyRubs());
	        }
	        else {
	        	pregnancySystem.incrementBellyRubsTimer();
	        }
		}	
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		super.evaluatePregnancyPains();
		if (pregnancySystem.getPregnancyPain() == PregnancyPain.FETAL_MOVEMENT) {
			if (pregnancySystem.getPregnancyPainTimer() >= totalTicksOfFetalMovement) {
				pregnantEntity.removeEffect(MinepreggoModMobEffects.FETAL_MOVEMENT.get());
				pregnancySystem.clearPregnancyPain();
				pregnancySystem.resetPregnancyPainTimer();
				pregnancySystem.syncState(pregnantEntity);
				
				MinepreggoMod.LOGGER.debug("Player {} pregnancy pain cleared: {}",
						pregnantEntity.getGameProfile().getName(), PregnancyPain.FETAL_MOVEMENT.name());
			} else {
				pregnancySystem.incrementPregnancyPainTimer();
				tryHurtByCooldown();
			}
		}
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}		
		
		float newFetalMovementProb = fetalMovementProb;
		
		if (this.pregnantEntity.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) {
			newFetalMovementProb *= 5f;
		}
		if (this.isMovingRidingSaddledHorse()) {		
			newFetalMovementProb *= (pregnancySystem.getCurrentPregnancyPhase().ordinal() + 2);
		}
		
		if (randomSource.nextFloat() < newFetalMovementProb) {
			PlayerHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.getRandomPregnancyPain(randomSource));	
			
			pregnancySystem.setPregnancyPain(PregnancyPain.FETAL_MOVEMENT);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FETAL_MOVEMENT.get(), totalTicksOfFetalMovement, 0, false, false, true));
			pregnancySystem.syncState(pregnantEntity);
			
			PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					pregnantEntity.getGameProfile().getName(), pregnancySystem.getPregnancyPain());
			return true;
		}	

		return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancySystem.getBellyRubs() >= PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.BELLY_RUBS)) {
			pregnancySystem.getPregnancySymptoms().addPregnancySymptom(PregnancySymptom.BELLY_RUBS);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.BELLY_RUBS.get(), -1, 0, false, false, true));
			pregnancySystem.syncState(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.BELLY_RUBS, pregnancySystem.getPregnancySymptoms());
			return true;
		}

		return false;
	}
}
