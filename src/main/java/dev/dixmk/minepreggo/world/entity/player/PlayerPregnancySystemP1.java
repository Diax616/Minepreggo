package dev.dixmk.minepreggo.world.entity.player;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import dev.dixmk.minepreggo.world.entity.preggo.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.common.capabilities.Capability;

public class PlayerPregnancySystemP1 extends AbstractPregnancySystem<ServerPlayer> {
	protected PlayerDataImpl playerData;
	protected PlayerPregnancySystemImpl pregnancySystem;
	protected PlayerPregnancyEffectsImpl pregnancyEffects;
	private int pregnancyPainTicks = 0;
	private int pregnancysymptonsTicks = 0;
	
	protected @Nonnegative int totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP1();

	private final boolean isValid;
	
	public PlayerPregnancySystemP1(@NonNull ServerPlayer player) {
		super(player);
		
		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> this.playerData = cap);	
		player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(cap -> this.pregnancySystem = cap);
		player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(cap -> this.pregnancyEffects = cap);
	
		this.isValid = checkCapability(MinepreggoCapabilities.PLAYER_DATA)
				&& checkCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM)
				&& checkCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS);
	}
	
	public boolean isPlayerValid(ServerPlayer currentPlayer) {
	    // Check if the stored player is the same instance and has a valid connection
	    return this.pregnantEntity == currentPlayer && 
	           this.pregnantEntity.connection != null && 
	           this.pregnantEntity.connection.connection.isConnected();
	}
	
	private boolean checkCapability(Capability<?> capability) {
		if (this.pregnantEntity.getCapability(capability).isPresent()) {
			MinepreggoMod.LOGGER.debug("Capability {} initialized for player: {}",
					capability.getName(), this.pregnantEntity.getGameProfile().getName());
			return true;
		}
		else {
			MinepreggoMod.LOGGER.error("Failed to initialize capability {} for player: {}",
					capability.getName(), this.pregnantEntity.getGameProfile().getName());
			return false;
		}
	}
	
	// It has to be executed on server side
	@Override
	protected void evaluatePregnancySystem() {
		if (isMiscarriageActive()) {
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel);
			}		
			return;
		}
		
		evaluatePregnancyTimer();
		
		if (canAdvanceNextPregnancyPhase()) {
			advanceToNextPregnancyPhase();
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
		
		evaluatePregnancyEffects();
	}
	
	@Override
	protected void evaluatePregnancyEffects() {	
		evaluateCravingTimer();
	}
	
	@Override
	public final void onServerTick() {			
		if (pregnantEntity.level().isClientSide) {
			return;
		}
		
		if (!isValid) {
			MinepreggoMod.LOGGER.warn("PlayerPregnancySystemP1 is not valid for player: {}. Aborting onServerTick.",
					pregnantEntity.getGameProfile().getName());
			return;
		}	

		evaluatePregnancySystem();
	}
	
	@Override
	protected void evaluatePregnancyTimer() {
		if (pregnancySystem.getPregnancyTimer() > MinepreggoModConfig.getTotalTicksByPregnancyDay()) {
			pregnancySystem.resetPregnancyTimer();
			pregnancySystem.incrementDaysPassed();
			pregnancySystem.reduceDaysToGiveBirth();
		} else {
			pregnancySystem.incrementPregnancyTimer();
		}
	}
	
	protected void evaluateCravingTimer() {   				
		if (pregnancyEffects.getCraving() < PregnancySystemHelper.MAX_CRAVING_LEVEL
				&& pregnancySystem.getPregnancySymptom() != PregnancySymptom.CRAVING) {
	        if (pregnancyEffects.getCravingTimer() >= totalTicksOfCraving) {
	        	pregnancyEffects.incrementCraving();
	        	pregnancyEffects.resetCravingTimer();
	        	pregnancyEffects.sync(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} craving level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancyEffects.getCraving());
	        }
	        else {
	        	pregnancyEffects.incrementCravingTimer();
	        }
		}	
	}
	
	@Override
	protected void evaluateMiscarriage(ServerLevel serverLevel) {		
		if (pregnancySystem.getPregnancyPainTimer() > PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE) {
			pregnancySystem.resetPregnancyPainTimer();
			pregnancySystem.clearPregnancyPain();				
			playerData.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
					
			pregnancySystem.sync(pregnantEntity);
			playerData.sync(pregnantEntity);
		} else {
			
    		for (ServerPlayer otherPlayer : serverLevel.getServer().getPlayerList().getPlayers()) {
    		    if (pregnantEntity.distanceToSqr(otherPlayer) <= 512.0) { 
    				serverLevel.sendParticles(otherPlayer, ParticleTypes.FALLING_DRIPSTONE_LAVA, true, pregnantEntity.getX(), (pregnantEntity.getY() + pregnantEntity.getBbHeight() * 0.35), pregnantEntity.getZ(),
    						1, 0, 1, 0, 0.02);
    		    }
    		}
			
			pregnancySystem.incrementPregnancyPainTimer();
		}
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {	
		if (pregnancySystem.getPregnancySymptom() == PregnancySymptom.CRAVING && pregnancyEffects.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
			pregnancySystem.clearPregnancySymptom();
			pregnancyEffects.clearTypeOfCravingBySpecies();
			pregnantEntity.removeEffect(MinepreggoModMobEffects.CRAVING.get());
			
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
		}
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		if (pregnancySystem.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (pregnancySystem.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS) {
				pregnancySystem.clearPregnancyPain();
				pregnancySystem.resetPregnancyPainTimer();
				pregnancySystem.sync(pregnantEntity);
				
				MinepreggoMod.LOGGER.debug("Player {} pregnancy pain cleared: {}",
						pregnantEntity.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());
			} else {
				pregnancySystem.incrementPregnancyPainTimer();
			}
		}
	}
	
	@Override
	public boolean isMiscarriageActive() {
		return pregnancySystem.getPregnancyPain() == PregnancyPain.MISCARRIAGE;	
	}
	
	@Override
	public boolean canAdvanceNextPregnancyPhase() {
		return pregnancySystem.getDaysPassed() >= pregnancySystem.getDaysByStage();
	}
	
	@Override
	public boolean hasPregnancyPain() {
		return pregnancySystem.getPregnancyPain() != null;
	}
	
	@Override
	public boolean hasPregnancySymptom() {
		return pregnancySystem.getPregnancySymptom() != null;
	}
	
	@Override
	protected void advanceToNextPregnancyPhase() {	
		var currentStage = pregnancySystem.getCurrentPregnancyStage();		
		pregnancySystem.setCurrentPregnancyStage(PregnancyStage.values()[Math.max(currentStage.ordinal() + 1, PregnancyStage.values().length - 1)]);
		pregnancySystem.resetPregnancyTimer();
		pregnancySystem.resetDaysPassed();
		
		MinepreggoMod.LOGGER.debug("Player {} advanced to next pregnancy phase: {}",
				pregnantEntity.getGameProfile().getName(), pregnancySystem.getCurrentPregnancyStage().name());	
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
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
		if (pregnancyEffects.getCraving() >= PregnancySystemHelper.MAX_CRAVING_LEVEL) {
			pregnancySystem.setPregnancySymptom(PregnancySymptom.CRAVING);
			pregnancyEffects.setTypeOfCraving(PregnancySystemHelper.getRandomCraving(randomSource));
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.CRAVING.get(), -1, 0, true, true));
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
			return true;
		}
		return false;
	}

	@Override
	protected void initPostMiscarriage() {
		
	}

	@Override
	protected void initPostPartum() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return pregnancySystem.getLastPregnancyStage() == pregnancySystem.getCurrentPregnancyStage();
	}
	
	@Override
	protected boolean isInLabor() {
		final var pain = pregnancySystem.getPregnancyPain();
		return pain == PregnancyPain.PREBIRTH || pain == PregnancyPain.BIRTH;
 	}
	
	@Override
	protected void startLabor() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		// This pregnancy phase does not support birth yet
	}
}
