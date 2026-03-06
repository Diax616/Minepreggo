package dev.dixmk.minepreggo.server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.common.animation.CommonPlayerAnimationRegistry;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPlayerAnimationS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.ServerLifecycleHooks;

public class ServerPlayerAnimationManager {   
	private static final int SYNC_INTERVAL = 20;
    private final Map<UUID, AnimationState> lastSyncedState;
    private int tickCounter = 0;
    
	private ServerPlayerAnimationManager() {
		lastSyncedState = new HashMap<>();
	}
	
    private static class Holder {
        private static final ServerPlayerAnimationManager INSTANCE = new ServerPlayerAnimationManager();
    }
       	    
    public static ServerPlayerAnimationManager getInstance() {
        return Holder.INSTANCE;
    }

    public void onServerTick() {      
    	var instance = ServerPlayerAnimationManager.getInstance();
    	instance.tickCounter++;
        if (instance.tickCounter >= ServerPlayerAnimationManager.SYNC_INTERVAL) {     	
        	instance.tickCounter = 0;
        	instance.syncAnimations();
        }
    }

    private void syncAnimations() {
        // Periodic sync to ensure all clients stay synchronized
        // Only sends to nearby players who can see the animated player
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;
        
        for (Map.Entry<UUID, AnimationState> entry : lastSyncedState.entrySet()) {
            AnimationState state = entry.getValue();
           
            if (!state.isPlaying) continue;
            
            // Find the player who is animating
            ServerPlayer animatingPlayer = server.getPlayerList().getPlayer(entry.getKey());
            if (animatingPlayer == null) continue;
            
            // Increment tick and sync
            state.tick++;
            
            MinepreggoModPacketHandler.INSTANCE.send(
            		PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> animatingPlayer),
            		new SyncPlayerAnimationS2CPacket(entry.getKey(), state.animationName, state.tick, true));
        }
    }
    
    public void triggerAnimation(ServerPlayer player, String animationName) {
    	if (!CommonPlayerAnimationRegistry.getInstance().containsAnimation(animationName)) {
    		MinepreggoMod.LOGGER.warn("Attempted to trigger unknown animation: {}", animationName);
    		return;
    	}
    	
    	UUID playerId = player.getUUID();
        AnimationState state = new AnimationState(animationName, 0, true);
        lastSyncedState.put(playerId, state);
		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.setAnimation(animationName));

        // Broadcast to all clients
        MinepreggoModPacketHandler.INSTANCE.send(
        		PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
        		new SyncPlayerAnimationS2CPacket(playerId, animationName, 0, true));
    }
    
    public void stopAnimation(ServerPlayer player, boolean refreshState) {
        UUID playerId = player.getUUID();
        lastSyncedState.remove(playerId); 
        if (refreshState) {
			player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.setAnimation(null));
		} 
        MinepreggoModPacketHandler.INSTANCE.send(
        		PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
        		new SyncPlayerAnimationS2CPacket(playerId, null, 0, false));
    }
    
    public void stopAnimation(ServerPlayer player) {
		stopAnimation(player, true);
	}
    
    private class AnimationState {
        private String animationName;
        private int tick;
        private boolean isPlaying;
        
        AnimationState(String name, int tick, boolean playing) {
            this.animationName = name;
            this.tick = tick;
            this.isPlaying = playing;
        }
    }
}