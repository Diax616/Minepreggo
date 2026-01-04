package dev.dixmk.minepreggo.world.entity.player;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class PlayerPregnancySystemP2 extends PlayerPregnancySystemP1 {

	protected @Nonnegative int totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP2();
	public PlayerPregnancySystemP2(@NonNull ServerPlayer player) {
		super(player);		
		addNewValidPregnancySymptoms(PregnancySymptom.MILKING);
	}

	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP2();
		morningSicknessProb = PregnancySystemHelper.MEDIUM_MORNING_SICKNESS_PROBABILITY;
		pregnancyExhaustion = 1.02f;
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateMilkingTimer();
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
						
			if (flag) {
				pregnancySystem.removePregnancySymptom(symptom);
				pregnancySystem.sync(pregnantEntity);
				pregnancyEffects.sync(pregnantEntity);
				MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
						pregnantEntity.getGameProfile().getName(), symptom);
			}
		});			
	}
	
	protected void evaluateMilkingTimer() {   				
		if (pregnancyEffects.getMilking() < PregnancySystemHelper.MAX_MILKING_LEVEL) {
	        if (pregnancyEffects.getMilkingTimer() >= totalTicksOfMilking) {
	        	pregnancyEffects.incrementMilking();
	        	pregnancyEffects.resetMilkingTimer();
	        	pregnancyEffects.sync(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} milking level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancyEffects.getMilking());
	        }
	        else {
	        	pregnancyEffects.incrementMilkingTimer();
	        }
		}	
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancyEffects.getMilking() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.MILKING)) {
			pregnancySystem.addPregnancySymptom(PregnancySymptom.MILKING);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.LACTATION.get(), -1, 0, false, false, true));
			pregnancySystem.sync(pregnantEntity);
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.MILKING, pregnancySystem.getPregnancySymptoms());
			return true;
		}

		return false;
	}
}
