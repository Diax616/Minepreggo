package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class PreggoMobPregnancySystemP3 <E extends PreggoMob
	& ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP2<E> {

	protected @Nonnegative int totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP3();
	protected @Nonnegative float fetalMovementProb = PregnancySystemHelper.LOW_PREGNANCY_PAIN_PROBABILITY;
	protected @Nonnegative int totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P3;

	protected PreggoMobPregnancySystemP3(@Nonnull E preggoMob) {
		super(preggoMob);
		addNewValidPregnancySymptom(PregnancySymptom.BELLY_RUBS);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP3();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP3();
		morningSicknessProb = PregnancySystemHelper.HIGH_MORNING_SICKNESS_PROBABILITY;
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
	
	protected void evaluateBellyRubsTimer() {
		if (pregnantEntity.getBellyRubs() < PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL) {
	        if (pregnantEntity.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	pregnantEntity.incrementBellyRubs();
	        	pregnantEntity.resetBellyRubsTimer();
	        }
	        else {
	        	pregnantEntity.incrementBellyRubsTimer();
	        }
		}	
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {		
		pregnantEntity.getPregnancySymptoms().forEach(symptom -> {
			if (symptom == PregnancySymptom.CRAVING && pregnantEntity.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
				pregnantEntity.removePregnancySymptom(PregnancySymptom.CRAVING);
				pregnantEntity.clearTypeOfCraving();
			}
			else if (symptom == PregnancySymptom.MILKING && pregnantEntity.getMilking() <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {
				pregnantEntity.removePregnancySymptom(PregnancySymptom.MILKING);
				pregnantEntity.removeEffect(MinepreggoModMobEffects.LACTATION.get());
			}
			else if (symptom == PregnancySymptom.BELLY_RUBS && pregnantEntity.getBellyRubs() <= PregnancySystemHelper.DESACTIVATEL_BELLY_RUBS_SYMPTOM) {
				pregnantEntity.removePregnancySymptom(PregnancySymptom.BELLY_RUBS);
			}
		});
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		if (pregnantEntity.getBellyRubs() >= PregnancySystemHelper.ACTIVATE_BELLY_RUBS_SYMPTOM
				&& !pregnantEntity.getPregnancySymptoms().contains(PregnancySymptom.BELLY_RUBS)) {
	    	pregnantEntity.addPregnancySymptom(PregnancySymptom.BELLY_RUBS);
	    	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getSimpleName(), PregnancySymptom.BELLY_RUBS, pregnantEntity.getPregnancySymptoms());
			
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}	
		if (randomSource.nextFloat() < fetalMovementProb) {
			pregnantEntity.setPregnancyPain(PregnancyPain.FETAL_MOVEMENT);
			pregnantEntity.resetPregnancyPainTimer();
			PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);				
			return true;
		}     
	    return false;
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		super.evaluatePregnancyPains();
		if (pregnantEntity.getPregnancyPain() == PregnancyPain.FETAL_MOVEMENT) {
			if (pregnantEntity.getPregnancyPainTimer() >= totalTicksOfFetalMovement) {
				pregnantEntity.clearPregnancyPain();	
				pregnantEntity.resetPregnancyPainTimer();
			}
			else {
				pregnantEntity.incrementPregnancyPainTimer();
			}
		}
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || pregnantEntity.getBellyRubs() >= PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL;
	}
	
	@Override
	protected Result evaluateBellyRubs(Level level, Player source) {		
		super.evaluateBellyRubs(level, source);	
		pregnantEntity.playSound(MinepreggoModSounds.BELLY_TOUCH.get(), 0.8F, 0.8F + pregnantEntity.getRandom().nextFloat() * 0.3F);	
		var currentBellyRubs = pregnantEntity.getBellyRubs();
		if (currentBellyRubs > 0) {			
			if (!level.isClientSide) {   	
				currentBellyRubs = Math.max(0, currentBellyRubs - PregnancySystemHelper.BELLY_RUBBING_VALUE);			
				pregnantEntity.setBellyRubs(currentBellyRubs);
							
				if (pregnantEntity.getPregnancySymptoms().contains(PregnancySymptom.BELLY_RUBS)
						&& currentBellyRubs <= PregnancySystemHelper.DESACTIVATEL_BELLY_RUBS_SYMPTOM) {									
					pregnantEntity.removePregnancySymptom(PregnancySymptom.BELLY_RUBS);							
				}	
			}						
			return Result.SUCCESS;
		}		
			
		return Result.ANGRY;
	}
}
