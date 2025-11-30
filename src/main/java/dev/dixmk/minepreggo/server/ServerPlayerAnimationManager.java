package dev.dixmk.minepreggo.server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.UpdatePlayerAnimationS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber
public class ServerPlayerAnimationManager {
    
	private ServerPlayerAnimationManager() {}
	
    private static class Holder {
        private static final ServerPlayerAnimationManager INSTANCE = new ServerPlayerAnimationManager();
    }
       	    
    public static ServerPlayerAnimationManager getInstance() {
        return Holder.INSTANCE;
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
            
        ServerPlayerAnimationManager.getInstance().tickCounter++;
        if (ServerPlayerAnimationManager.getInstance().tickCounter >= ServerPlayerAnimationManager.SYNC_INTERVAL) {     	
        	ServerPlayerAnimationManager.getInstance().tickCounter = 0;
        	ServerPlayerAnimationManager.getInstance().syncAnimations();
        }
    }
    
	private static final int SYNC_INTERVAL = 20; // Sync every 20 ticks (1 second)
    private final Map<UUID, AnimationState> lastSyncedState = new HashMap<>();
    private int tickCounter = 0;


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
            		new UpdatePlayerAnimationS2CPacket(entry.getKey(), state.animationName, state.tick, true));
        }
    }
    
    public void triggerAnimation(ServerPlayer player, String animationName) {
        UUID playerId = player.getUUID();
        AnimationState state = new AnimationState(animationName, 0, true);
        lastSyncedState.put(playerId, state);

        // Broadcast to all clients
        MinepreggoModPacketHandler.INSTANCE.send(
        		PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
        		new UpdatePlayerAnimationS2CPacket(playerId, animationName, 0, true));
    }
    
    public void stopAnimation(ServerPlayer player) {
        UUID playerId = player.getUUID();
        lastSyncedState.remove(playerId); 
        
        MinepreggoModPacketHandler.INSTANCE.send(
        		PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
        		new UpdatePlayerAnimationS2CPacket(playerId, null, 0, false));
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