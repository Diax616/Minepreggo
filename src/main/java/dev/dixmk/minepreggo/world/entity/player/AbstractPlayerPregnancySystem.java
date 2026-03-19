package dev.dixmk.minepreggo.world.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyDataImpl;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import net.minecraft.server.level.ServerPlayer;

public abstract class AbstractPlayerPregnancySystem extends AbstractPregnancySystem<ServerPlayer> {
	protected PlayerDataImpl playerData = null;
	protected FemalePlayerImpl femaleData = null;
	protected PlayerPregnancyDataImpl pregnancySystem = null;
	private final boolean isValid;
	
	protected AbstractPlayerPregnancySystem(ServerPlayer player) {
		super(player);
		
		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {			
			this.playerData = cap;				
			cap.getFemaleData().ifPresent(f -> {
				this.femaleData = f;
				this.pregnancySystem = f.getPregnancyData();
			});	
		});	
		
		this.isValid = this.playerData != null && this.pregnancySystem != null;
	}

	public boolean isPlayerValid(ServerPlayer currentPlayer) {
	    if (this.pregnantEntity == null || currentPlayer == null) {
	        return false;
	    }
	    
	    if (this.pregnantEntity.isRemoved() || currentPlayer.isRemoved()) {
	        return false;
	    }	   
	    
	    if (!this.pregnantEntity.getUUID().equals(currentPlayer.getUUID())) {
	        return false;
	    }

	    if (this.pregnantEntity != currentPlayer) {
	        refresh(currentPlayer);
	    }
	    
	    return true;
	}
	
	@Override
	public final void onServerTick() {			
		if (pregnantEntity.level().isClientSide) {
			return;
		}
		if (!isValid) {
			MinepreggoMod.LOGGER.warn("PlayerPregnancySystem is not valid for player: {}. Aborting onServerTick. playerData: {}, femaleData: {}, pregnancySystem: {}",
					pregnantEntity.getGameProfile().getName(), this.playerData != null, this.femaleData != null, this.pregnancySystem != null);		
			return;
		}	
		evaluatePregnancySystem();
	}
	
	public void invalidate() {
	    this.pregnantEntity = null;
	    this.playerData = null;
	    this.femaleData = null;
	    this.pregnancySystem = null;
	}
	
	public boolean refresh(ServerPlayer currentPlayer) {
	    // Update the player reference
	    this.pregnantEntity = currentPlayer;
	    
	    // Re-fetch capabilities to ensure we have the latest references
	    currentPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {			
			this.playerData = cap;				
			cap.getFemaleData().ifPresent(f -> {
				this.femaleData = f;
				this.pregnancySystem = f.getPregnancyData();
			});	
		});	
		
	    boolean result = this.playerData != null && this.pregnancySystem != null;
	    
	    if (!result) {
	        MinepreggoMod.LOGGER.warn("Failed to refresh PlayerPregnancySystem for player: {}. playerData: {}, femaleData: {}, pregnancySystem: {}",
	                currentPlayer.getGameProfile().getName(), this.playerData != null, this.femaleData != null, this.pregnancySystem != null);
	    }
	    
	    return result;
	}
}
