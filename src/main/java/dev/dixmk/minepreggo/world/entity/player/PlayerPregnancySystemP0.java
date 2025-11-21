package dev.dixmk.minepreggo.world.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import dev.dixmk.minepreggo.world.entity.preggo.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class PlayerPregnancySystemP0 extends AbstractPregnancySystem<ServerPlayer> {
	
	protected PlayerDataImpl playerData = null;
	protected FemalePlayerImpl femaleData = null;
	protected PlayerPregnancySystemImpl pregnancySystem = null;
	protected PlayerPregnancyEffectsImpl pregnancyEffects = null;
	
	private final boolean isValid;
	
	public PlayerPregnancySystemP0(ServerPlayer player) {
		super(player);
		
		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {			
			this.playerData = cap;				
			cap.getFemaleData().ifPresent(f -> {
				this.femaleData = f;
				this.pregnancySystem = f.getPregnancySystem();
				this.pregnancyEffects = f.getPregnancyEffects();
			});	
		});	
		
		this.isValid = this.playerData != null && this.pregnancySystem != null && this.pregnancyEffects != null;
	}

	public boolean isPlayerValid(ServerPlayer currentPlayer) {
	    // Check if the stored player is the same instance and has a valid connection
	    return this.pregnantEntity == currentPlayer && 
	           this.pregnantEntity.connection != null && 
	           this.pregnantEntity.connection.connection.isConnected();
	}
	
	@Override
	public final void onServerTick() {			
		if (pregnantEntity.level().isClientSide) {
			return;
		}
		
		if (!isValid) {
			MinepreggoMod.LOGGER.warn("PlayerPregnancySystemP0 is not valid for player: {}. Aborting onServerTick.",
					pregnantEntity.getGameProfile().getName());
			return;
		}	

		evaluatePregnancySystem();
	}
	
	@Override
	protected void initPostMiscarriage() {
		// This pregnancy phase does not miscarriage yet
	}

	@Override
	protected void initPostPartum() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return false;
	}
	
	@Override
	protected boolean isInLabor() {
		return false;
 	}
	
	@Override
	protected void startLabor() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected boolean isWaterBroken() {
		return false;
 	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		// This pregnancy phase does not support water breaking yet
	}
	
	@Override
	protected void breakWater() {
		// This pregnancy phase does not support water breaking yet
	}

	@Override
	protected void evaluatePregnancySystem() {
		evaluatePregnancyTimer();
		
		if (canAdvanceNextPregnancyPhase()) {
			advanceToNextPregnancyPhase();
		}	
	}

	@Override
	protected void evaluatePregnancyPains() {
		// This pregnancy phase does not support pregnancy pains yet	
	}

	@Override
	protected void evaluatePregnancySymptoms() {
		// This pregnancy phase does not support pregnancy symptoms yet			
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

	@Override
	protected void evaluateMiscarriage(ServerLevel serverLevel) {
		// This pregnancy phase does not support miscarriage yet	
	}

	@Override
	public boolean isMiscarriageActive() {
		return false;
	}

	@Override
	public boolean hasPregnancyPain() {
		return false;
	}

	@Override
	public boolean canAdvanceNextPregnancyPhase() {
		return pregnancySystem.getDaysPassed() >= pregnancySystem.getDaysByStage();
	}

	@Override
	public boolean hasPregnancySymptom() {
		return false;
	}

	@Override
	protected void advanceToNextPregnancyPhase() {	
		// Current pregnancy Phase should not be null here
		var currentStage = pregnancySystem.getCurrentPregnancyStage();	
		
		pregnancySystem.setCurrentPregnancyStage(PregnancyPhase.values()[Math.min(currentStage.ordinal() + 1, PregnancyPhase.values().length - 1)]);
		pregnancySystem.resetPregnancyTimer();
		pregnancySystem.resetDaysPassed();
		
		MinepreggoMod.LOGGER.debug("Player {} advanced to next pregnancy phase: {}",
				pregnantEntity.getGameProfile().getName(), pregnancySystem.getCurrentPregnancyStage().name());	
	}

	@Override
	protected boolean tryInitRandomPregnancyPain() {
		return false;
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		return false;
	}

	@Override
	protected void evaluatePregnancyNeeds() {
		// This pregnancy phase does not support pregnancy needs yet
	}
}
