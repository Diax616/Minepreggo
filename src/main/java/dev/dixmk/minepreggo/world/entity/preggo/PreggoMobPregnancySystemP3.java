package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class PreggoMobPregnancySystemP3 
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobPregnancySystemP2<E> {

	protected @Nonnegative int totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP3();
	protected @Nonnegative float fetalMovementProb = PregnancySystemHelper.LOW_PREGNANCY_PAIN_PROBABILITY;
	protected @Nonnegative int totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P3;

	protected PreggoMobPregnancySystemP3(@Nonnull E preggoMob) {
		super(preggoMob);
		addNewValidPregnancySymptom(PregnancySymptom.BELLY_RUBS);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP3();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP3();
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
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (pregnancyData.getBellyRubs() < PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL) {
	        if (pregnancyData.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	pregnancyData.incrementBellyRubs();
	        	pregnancyData.resetBellyRubsTimer();
	        }
	        else {
	        	pregnancyData.incrementBellyRubsTimer();
	        }
		}	
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		final var pregnancyData = pregnantEntity.getPregnancyData();	
		if (pregnancyData.getBellyRubs() >= PregnancySystemHelper.ACTIVATE_BELLY_RUBS_SYMPTOM
				&& !pregnancyData.getPregnancySymptoms().contains(PregnancySymptom.BELLY_RUBS)) {
			initPregnancySymptom(PregnancySymptom.BELLY_RUBS);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}	

		float newFetalMovementProb = fetalMovementProb;	
		if (this.pregnantEntity.hasEffect(MinepreggoMobEffects.ETERNAL_PREGNANCY.get())) {
			newFetalMovementProb *= 5f;
		}
		
		if (pregnantEntity.getRandom().nextFloat() < newFetalMovementProb) {
			initCommonPregnancyPain(PregnancyPain.FETAL_MOVEMENT, totalTicksOfFetalMovement, 0);
			return true;
		}     
	    return false;
	}
	
	@Override
	protected Result evaluateBellyRubs(Level level, Player source) {		
		super.evaluateBellyRubs(level, source);	
		final var pregnancyData = pregnantEntity.getPregnancyData();

		if (pregnancyData.getBellyRubs() > 0) {			
			if (!level.isClientSide) { 
				pregnancyData.decrementBellyRubs(PregnancySystemHelper.BELLY_RUBBING_VALUE);
				
				var pregnancySymptoms = pregnancyData.getPregnancySymptoms();				
				if (pregnancySymptoms.contains(PregnancySymptom.BELLY_RUBS)
						&& pregnancyData.getBellyRubs() <= PregnancySystemHelper.DESACTIVATEL_BELLY_RUBS_SYMPTOM) {									
					pregnancySymptoms.remove(PregnancySymptom.BELLY_RUBS);							
				}	
			}						
			return Result.SUCCESS;
		}		
			
		return Result.ANGRY;
	}
}
