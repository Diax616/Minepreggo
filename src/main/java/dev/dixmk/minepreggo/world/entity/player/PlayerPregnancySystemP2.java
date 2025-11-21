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

public class PlayerPregnancySystemP2 extends PlayerPregnancySystemP1 {

	protected @Nonnegative int totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP2();
	
	public PlayerPregnancySystemP2(@NonNull ServerPlayer player) {
		super(player);
		this.totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP2();
	}

	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateMilkingTimer();
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		final var pregnancySymptom = pregnancySystem.getPregnancySymptom();			
		
		if ((pregnancySymptom == PregnancySymptom.CRAVING && pregnancyEffects.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM)
				|| (pregnancySymptom == PregnancySymptom.MILKING && pregnancyEffects.getMilking() <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM)) {		
			pregnancySystem.clearPregnancySymptom();	
		
			if (pregnancySymptom == PregnancySymptom.CRAVING) {
				pregnancyEffects.clearTypeOfCravingBySpecies();
				pregnantEntity.removeEffect(MinepreggoModMobEffects.CRAVING.get());

			}
			else {
				pregnantEntity.removeEffect(MinepreggoModMobEffects.LACTATION.get());
			}
			
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					pregnantEntity.getGameProfile().getName(), pregnancySymptom.name());
		}
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
	protected boolean tryInitRandomPregnancyPain() {
		if (randomSource.nextFloat() < PregnancySystemHelper.MEDIUM_MORNING_SICKNESS_PROBABILITY) {
			pregnancySystem.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
			pregnancySystem.sync(pregnantEntity);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					pregnantEntity.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());

			return true;
		}	
		
		return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancyEffects.getMilking() >= PregnancySystemHelper.MAX_MILKING_LEVEL) {
			pregnancySystem.setPregnancySymptom(PregnancySymptom.MILKING);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.LACTATION.get(), -1, 0, true, true));
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
			return true;
		}

		return false;
	}
}
