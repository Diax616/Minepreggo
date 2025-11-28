package dev.dixmk.minepreggo.world.entity.player;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class PlayerPregnancySystemP3 extends PlayerPregnancySystemP2 {

	protected @Nonnegative int totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP3();
	
	public PlayerPregnancySystemP3(@NonNull ServerPlayer player) {
		super(player);
		addNewValidPregnancySymptoms(PregnancySymptom.BELLY_RUBS);
	}

	@Override
	protected void initPregnancySymptomsTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP3();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP3();
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
	protected boolean tryInitRandomPregnancyPain() {
		boolean flag = false;
		
		if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
			pregnancySystem.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
			pregnancySystem.sync(pregnantEntity);		
			flag = true;
		}	
		else if (randomSource.nextFloat() < PregnancySystemHelper.LOW_PREGNANCY_PAIN_PROBABILITY) {
			pregnancySystem.setPregnancyPain(PregnancyPain.FETAL_MOVEMENT);
			pregnancySystem.sync(pregnantEntity);
			flag = true;
		}	
		
		if (flag) {
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					pregnantEntity.getGameProfile().getName(), pregnancySystem.getPregnancyPain());
		}
		
		return flag;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancyEffects.getBellyRubs() >= PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.BELLY_RUBS)) {
			pregnancySystem.addPregnancySymptom(PregnancySymptom.BELLY_RUBS);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.BELLY_RUBS.get(), -1, 0, true, true));
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.BELLY_RUBS, pregnancySystem.getPregnancySymptoms());
			return true;
		}

		return false;
	}
}
