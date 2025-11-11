package dev.dixmk.minepreggo.world.entity.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.common.capabilities.Capability;

public class PlayerPregnancySystemP1 {
	protected final RandomSource randomSource;
	protected final ServerPlayer player;
	protected PlayerDataImpl playerData;
	protected PlayerPregnancySystemImpl pregnancySystem;
	protected PlayerPregnancyEffectsImpl pregnancyEffects;
	protected int pregnancyPainTicks = 0;
	protected int pregnancysymptonsTicks = 0;
	
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
	
	public boolean isPlayerValid(ServerPlayer currentPlayer) {
	    // Check if the stored player is the same instance and has a valid connection
	    return this.player == currentPlayer && 
	           this.player.connection != null && 
	           this.player.connection.connection.isConnected();
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
		if (player.level().isClientSide) {
			return;
		}
		
		if (!isValid) {
			MinepreggoMod.LOGGER.warn("PlayerPregnancySystemP1 is not valid for player: {}. Aborting onServerTick.",
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
			if (pregnancyPainTicks > 40) {
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
			if (pregnancysymptonsTicks > 40) {
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
		if (pregnancyEffects.getCraving() < PregnancySystemHelper.MAX_CRAVING_LEVEL
				&& pregnancySystem.getPregnancySymptom() != PregnancySymptom.CRAVING) {
	        if (pregnancyEffects.getCravingTimer() >= totalTicksOfCraving) {
	        	pregnancyEffects.incrementCraving();
	        	pregnancyEffects.resetCravingTimer();
	        	pregnancyEffects.sync(player);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} craving level increased to: {}", 
	        			player.getGameProfile().getName(), pregnancyEffects.getCraving());
	        }
	        else {
	        	pregnancyEffects.incrementCravingTimer();
	        }
		}	
	}
	
	protected void evaluateMiscarriageTimer() {		
		if (pregnancySystem.getPregnancyPainTimer() > PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE) {
			pregnancySystem.resetPregnancyPainTimer();
			pregnancySystem.clearPregnancyPain();				
			playerData.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
			pregnancySystem.sync(player);
			playerData.sync(player);
		} else {
			pregnancySystem.incrementPregnancyPainTimer();
		}
	}
	
	protected void evaluatePregnancySymptoms() {	
		if (pregnancySystem.getPregnancySymptom() == PregnancySymptom.CRAVING && pregnancyEffects.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
			pregnancySystem.clearPregnancySymptom();
			pregnancyEffects.clearTypeOfCravingBySpecies();
			player.removeEffect(MinepreggoModMobEffects.CRAVING.get());
			pregnancySystem.sync(player);
			pregnancyEffects.sync(player);
			
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					player.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
		}
	}
	
	protected void evaluatePregnancyPains() {
		if (pregnancySystem.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (pregnancySystem.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS) {
				pregnancySystem.clearPregnancyPain();
				pregnancySystem.resetPregnancyPainTimer();
				pregnancySystem.sync(player);
				
				MinepreggoMod.LOGGER.debug("Player {} pregnancy pain cleared: {}",
						player.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());
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
		if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
			pregnancySystem.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
			pregnancySystem.sync(player);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					player.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());

			return true;
		}	
		return false;
	}
	
	protected boolean tryInitPregnancySymptom() {
		if (pregnancyEffects.getCraving() >= PregnancySystemHelper.MAX_CRAVING_LEVEL) {
			pregnancySystem.setPregnancySymptom(PregnancySymptom.CRAVING);
			pregnancyEffects.setTypeOfCraving(PregnancySystemHelper.getRandomCraving(randomSource));
			player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.CRAVING.get(), -1, 0, true, true));
			pregnancySystem.sync(player);
			pregnancyEffects.sync(player);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}",
					player.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
			return true;
		}
		return false;
	}
}

