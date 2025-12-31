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

	protected @Nonnegative int totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP3();
	protected @Nonnegative float fetalMovementProb = PregnancySystemHelper.LOW_PREGNANCY_PAIN_PROBABILITY;
	protected @Nonnegative int totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P3;

	public PlayerPregnancySystemP3(@NonNull ServerPlayer player) {
		super(player);
		
		PlayerHelper.addInterspeciesPregnancy(player);
		
		addNewValidPregnancySymptoms(PregnancySymptom.BELLY_RUBS);
	}

	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP3();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP3();
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
		pregnancySystem.getPregnancySymptoms().forEach(symptom -> {					
			boolean flag = false;		
			if (symptom == PregnancySymptom.CRAVING && pregnancyEffects.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
				pregnancyEffects.clearTypeOfCravingBySpecies();
				pregnantEntity.removeEffect(MinepreggoModMobEffects.CRAVING.get());
				flag = true;
			}
			else if (symptom == PregnancySymptom.MILKING && pregnancyEffects.getMilking() <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {
				pregnantEntity.removeEffect(MinepreggoModMobEffects.LACTATION.get());
				flag = true;
			}
			else if (symptom == PregnancySymptom.BELLY_RUBS && pregnancyEffects.getBellyRubs() <= PregnancySystemHelper.DESACTIVATEL_BELLY_RUBS_SYMPTOM) {
				pregnantEntity.removeEffect(MinepreggoModMobEffects.BELLY_RUBS.get());
				flag = true;
			}
					
			if (flag) {
				pregnancySystem.removePregnancySymptom(symptom);
				pregnancySystem.sync(pregnantEntity);
				pregnancyEffects.sync(pregnantEntity);
				MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
						pregnantEntity.getGameProfile().getName(), symptom);
			}
		});	
	}
	
	protected void evaluateBellyRubsTimer() {   				
		if (pregnancyEffects.getBellyRubs() < PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL) {
	        if (pregnancyEffects.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	pregnancyEffects.incrementBellyRubs();
	        	pregnancyEffects.resetBellyRubsTimer();
	        	pregnancyEffects.sync(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} belly rubs level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancyEffects.getBellyRubs());
	        }
	        else {
	        	pregnancyEffects.incrementBellyRubsTimer();
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
				pregnancySystem.sync(pregnantEntity);
				
				MinepreggoMod.LOGGER.debug("Player {} pregnancy pain cleared: {}",
						pregnantEntity.getGameProfile().getName(), PregnancyPain.FETAL_MOVEMENT.name());
			} else {
				pregnancySystem.incrementPregnancyPainTimer();
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
		
		if (randomSource.nextFloat() < newFetalMovementProb) {
			PlayerHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.getRandomPregnancyPain(randomSource));	
			
			pregnancySystem.setPregnancyPain(PregnancyPain.FETAL_MOVEMENT);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FETAL_MOVEMENT.get(), totalTicksOfFetalMovement, 0, false, false, true));
			pregnancySystem.sync(pregnantEntity);
			
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
		if (pregnancyEffects.getBellyRubs() >= PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.BELLY_RUBS)) {
			pregnancySystem.addPregnancySymptom(PregnancySymptom.BELLY_RUBS);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.BELLY_RUBS.get(), -1, 0, false, false, true));
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.BELLY_RUBS, pregnancySystem.getPregnancySymptoms());
			return true;
		}

		return false;
	}
}
