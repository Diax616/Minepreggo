package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.SyncedSetPregnancySymptom;
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
		SyncedSetPregnancySymptom pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();	
		if (pregnancyData.getBellyRubs() >= PregnancySystemHelper.ACTIVATE_BELLY_RUBS_SYMPTOM
				&& !pregnancySymptoms.containsPregnancySymptom(PregnancySymptom.BELLY_RUBS)) {
			pregnancySymptoms.addPregnancySymptom(PregnancySymptom.BELLY_RUBS);
	    	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getSimpleNameOrCustom(), PregnancySymptom.BELLY_RUBS, pregnancySymptoms.toSet());	
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}	
		
		final var pregnancyData = pregnantEntity.getPregnancyData();
		float newFetalMovementProb = fetalMovementProb;	
		if (this.pregnantEntity.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) {
			newFetalMovementProb *= 5f;
		}
		
		if (randomSource.nextFloat() < newFetalMovementProb) {
			pregnancyData.setPregnancyPain(PregnancyPain.FETAL_MOVEMENT);
			pregnancyData.resetPregnancyPainTimer();
			LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.getRandomStomachGrowls(randomSource));
			PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, InventorySlot.CHEST);				
			return true;
		}     
	    return false;
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		super.evaluatePregnancyPains();
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (pregnancyData.getPregnancyPain() == PregnancyPain.FETAL_MOVEMENT) {
			if (pregnancyData.getPregnancyPainTimer() >= totalTicksOfFetalMovement) {
				pregnancyData.clearPregnancyPain();	
				pregnancyData.resetPregnancyPainTimer();
			}
			else {
				pregnancyData.incrementPregnancyPainTimer();
				tryHurtByCooldown();
			}
		}
	}
	
	@Override
	protected Result evaluateBellyRubs(Level level, Player source) {		
		super.evaluateBellyRubs(level, source);	
		final var pregnancyData = pregnantEntity.getPregnancyData();

		if (pregnancyData.getBellyRubs() > 0) {			
			if (!level.isClientSide) { 
				pregnancyData.decrementBellyRubs(PregnancySystemHelper.BELLY_RUBBING_VALUE);
				
				SyncedSetPregnancySymptom pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();				
				if (pregnancySymptoms.containsPregnancySymptom(PregnancySymptom.BELLY_RUBS)
						&& pregnancyData.getBellyRubs() <= PregnancySystemHelper.DESACTIVATEL_BELLY_RUBS_SYMPTOM) {									
					pregnancySymptoms.removePregnancySymptom(PregnancySymptom.BELLY_RUBS);							
				}	
			}						
			return Result.SUCCESS;
		}		
			
		return Result.ANGRY;
	}
}
