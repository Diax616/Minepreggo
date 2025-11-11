package dev.dixmk.minepreggo.world.entity.player;

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

	public PlayerPregnancySystemP2(@NonNull ServerPlayer player) {
		super(player);
	}

	@Override
	public void onServerTick() {			
		if (player.level().isClientSide) {
			return;
		}
		
		if (!isValid) {
			MinepreggoMod.LOGGER.warn("PlayerPregnancySystemP2 is not valid for player: {}. Aborting onServerTick.",
					player.getGameProfile().getName());
			return;
		}	
		
		if (isMiscarriageActive()) {
			evaluateMiscarriageTimer();
			return;
		}
		
		evaluatePregnancyTimer();
		
		if (canAdvanceNextPregnancyPhase()) {
			advanceNextPregnancyPhase();
			return;
		}
		
		if (!hasPregnancyPain()) {
			if (pregnancyPainTicks > 30) {
				tryInitRandomPregnancyPain();
				pregnancyPainTicks = 0;
			}
			else {
				++pregnancyPainTicks;
			}
		}
		else {
			evaluatePregnancyPains();
		}
		
		if (!hasPregnancySymptom()) {
			if (pregnancysymptonsTicks > 20) {
				tryInitPregnancySymptom();
				pregnancysymptonsTicks = 0;
			}
			else {
				++pregnancysymptonsTicks;
			}
		}
		else {
			evaluatePregnancySymptoms();
		}
		
		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP2());
		evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP2());
	}
	
	
	@Override
	protected void evaluatePregnancySymptoms() {
		final var pregnancySymptom = pregnancySystem.getPregnancySymptom();			
		
		if ((pregnancySymptom == PregnancySymptom.CRAVING && pregnancyEffects.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM)
				|| (pregnancySymptom == PregnancySymptom.MILKING && pregnancyEffects.getMilking() <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM)) {		
			pregnancySystem.clearPregnancySymptom();	
			pregnancySystem.sync(player);
			pregnancyEffects.sync(player);
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					player.getGameProfile().getName(), pregnancySymptom.name());
		
			if (pregnancySymptom == PregnancySymptom.CRAVING) {
				pregnancyEffects.clearTypeOfCravingBySpecies();
				player.removeEffect(MinepreggoModMobEffects.CRAVING.get());

			}
			else {
				player.removeEffect(MinepreggoModMobEffects.LACTATION.get());
			}
		}
	}
	
	protected void evaluateMilkingTimer(final int totalTicksOfMilking) {   				
		if (pregnancyEffects.getMilking() < PregnancySystemHelper.MAX_MILKING_LEVEL) {
	        if (pregnancyEffects.getMilkingTimer() >= totalTicksOfMilking) {
	        	pregnancyEffects.incrementMilking();
	        	pregnancyEffects.resetMilkingTimer();
	        	pregnancyEffects.sync(player);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} milking level increased to: {}", 
	        			player.getGameProfile().getName(), pregnancyEffects.getMilking());
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
			pregnancySystem.sync(player);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					player.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());

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
			player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.LACTATION.get(), -1, 0, true, true));
			pregnancySystem.sync(player);
			pregnancyEffects.sync(player);	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}",
					player.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
			return true;
		}

		return false;
	}
}
