package dev.dixmk.minepreggo.world.entity.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemConstants;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraftforge.common.capabilities.Capability;

public class PlayerPregnancySystemP1 {
	protected final RandomSource randomSource;
	protected final ServerPlayer player;
	protected PlayerDataImpl playerData;
	protected PlayerPregnancySystemImpl pregnancySystem;
	protected PregnancyEffectsImpl pregnancyEffects;
	protected boolean isValid;
	
	public PlayerPregnancySystemP1(@NonNull ServerPlayer player) {
		this.randomSource = player.getRandom();
		this.player = player;

		this.player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> this.playerData = cap);	
		this.player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(cap -> this.pregnancySystem = cap);
		this.player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(cap -> this.pregnancyEffects = cap);
	
		this.isValid = checkCapability(MinepreggoCapabilities.PLAYER_DATA)
				&& checkCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM)
				&& checkCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS);
	}
	
	private boolean checkCapability(Capability<?> capability) {
		if (this.player.getCapability(capability).isPresent()) {
			MinepreggoMod.LOGGER.debug("Capability {} initialized for player: {}",
					capability.getName(), this.player.getGameProfile().getName());
			return true;
		}
		else {
			MinepreggoMod.LOGGER.error("Failed to initialize capability {} for player: {}",
					capability.getName(), this.player.getGameProfile().getName());
			return false;
		}
	}
	
	
	public void onServerTick() {
		if (player.level().isClientSide() || !isValid) {
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
			tryInitRandomPregnancyPain();	
		}
		else {
			evaluatePregnancyPains();
		}
		
		if (!hasPregnancySymptom()) {
			tryInitPregnancySymptom();
		}
		else {
			evaluatePregnancySymptoms();
		}
		
		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP1());
	}
	
	protected void evaluatePregnancyTimer() {
		if (pregnancySystem.getPregnancyTimer() > MinepreggoModConfig.getTotalTicksByPregnancyDay()) {
			pregnancySystem.resetPregnancyTimer();
			pregnancySystem.incrementDaysPassed();
			pregnancySystem.reduceDaysToGiveBirth();
		} else {
			pregnancySystem.incrementPregnancyTimer();
		}
	}
	
	protected void evaluateCravingTimer(final int totalTicksOfCraving) {   				
		if (pregnancyEffects.getCraving() < PregnancySystemConstants.MAX_CRAVING_LEVEL) {
	        if (pregnancyEffects.getCravingTimer() >= totalTicksOfCraving) {
	        	pregnancyEffects.incrementCraving();
	        	pregnancyEffects.resetCravingTimer();
	        }
	        else {
	        	pregnancyEffects.incrementCravingTimer();
	        }
		}	
	}
	
	protected void evaluateMiscarriageTimer() {		
		if (pregnancySystem.getPregnancyPainTimer() > PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) {
			pregnancySystem.resetPregnancyPainTimer();
			pregnancySystem.clearPregnancyPain();				
			playerData.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
		} else {
			pregnancySystem.incrementPregnancyPainTimer();
		}
	}
	
	protected void evaluatePregnancySymptoms() {	
		if (pregnancySystem.getPregnancySymptom() == PregnancySymptom.CRAVING && pregnancyEffects.getCraving() <= PregnancySystemConstants.DESACTIVATE_CRAVING_SYMPTOM) {
			pregnancySystem.clearPregnancySymptom();
			pregnancyEffects.clearTypeOfCraving();
		}
	}
	
	protected void evaluatePregnancyPains() {
		if (pregnancySystem.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (pregnancySystem.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS) {
				pregnancySystem.clearPregnancyPain();
				pregnancySystem.resetPregnancyPainTimer();
			} else {
				pregnancySystem.incrementPregnancyPainTimer();
			}
		}
	}
	
	public boolean isMiscarriageActive() {
		return pregnancySystem.getPregnancyPain() == PregnancyPain.MISCARRIAGE;	
	}
	
	public boolean canAdvanceNextPregnancyPhase() {
		return pregnancySystem.getDaysPassed() >= pregnancySystem.getDaysByStage();
	}
	
	public boolean canTriggerMiscarriage() {
		return pregnancySystem.getPregnancyHealth() <= 0;
	}
	
	public boolean hasPregnancyPain() {
		return pregnancySystem.getPregnancyPain() != null;
	}
	
	public boolean hasPregnancySymptom() {
		return pregnancySystem.getPregnancySymptom() != null;
	}
	
	protected void advanceNextPregnancyPhase() {	
		var currentStage = pregnancySystem.getCurrentPregnancyStage();		
		pregnancySystem.setCurrentPregnancyStage(PregnancyStage.values()[Math.max(currentStage.ordinal() + 1, PregnancyStage.values().length - 1)]);
		pregnancySystem.resetPregnancyTimer();
		pregnancySystem.resetDaysPassed();
		
		MinepreggoMod.LOGGER.debug("Player {} advanced to next pregnancy phase: {}",
				player.getGameProfile().getName(), pregnancySystem.getCurrentPregnancyStage().name());	
	}
	

	protected boolean tryInitRandomPregnancyPain() {
		if (randomSource.nextFloat() < PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY) {
			pregnancySystem.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					player.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());
			
			return true;
		}	
		return false;
	}
	
	protected boolean tryInitPregnancySymptom() {
		if (pregnancyEffects.getCraving() >= PregnancySystemConstants.MAX_CRAVING_LEVEL) {
			pregnancySystem.setPregnancySymptom(PregnancySymptom.CRAVING);
			pregnancyEffects.setTypeOfCraving(Craving.getRandomCraving(randomSource));
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}",
					player.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
		}
		return false;
	}
}

