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

	protected @Nonnegative int totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP2();
	public PlayerPregnancySystemP2(@NonNull ServerPlayer player) {
		super(player);		
		addNewValidPregnancySymptoms(PregnancySymptom.MILKING);
	}

	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP2();
		morningSicknessProb = PregnancySystemHelper.MEDIUM_MORNING_SICKNESS_PROBABILITY;
		pregnancyExhaustion = 0.02f;
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateMilkingTimer();
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		super.evaluatePregnancySymptoms();
		if (pregnancySystem.getPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.MILKING) && pregnancySystem.getMilking() <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {	
			pregnancySystem.getPregnancySymptoms().removePregnancySymptom(PregnancySymptom.MILKING);
			pregnantEntity.removeEffect(MinepreggoModMobEffects.LACTATION.get());
			pregnancySystem.syncState(pregnantEntity);
			pregnancySystem.syncEffect(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.MILKING.name());
		}	
	}
	
	protected void evaluateMilkingTimer() {   				
		if (pregnancySystem.getMilking() < PregnancySystemHelper.MAX_MILKING_LEVEL) {
	        if (pregnancySystem.getMilkingTimer() >= totalTicksOfMilking) {
	        	pregnancySystem.incrementMilking();
	        	pregnancySystem.resetMilkingTimer();
	        	pregnancySystem.syncEffect(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} milking level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancySystem.getMilking());
	        }
	        else {
	        	pregnancySystem.incrementMilkingTimer();
	        }
		}	
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancySystem.getMilking() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM
				&& !pregnancySystem.getPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.MILKING)) {
			pregnancySystem.getPregnancySymptoms().addPregnancySymptom(PregnancySymptom.MILKING);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.LACTATION.get(), -1, 0, false, false, true));
			pregnancySystem.syncState(pregnantEntity);
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.MILKING, pregnancySystem.getPregnancySymptoms());
			return true;
		}

		return false;
	}
}
