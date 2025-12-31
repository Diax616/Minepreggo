package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager.PlayerAnimationCache;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationRegistry;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.ClientPlayerHelper;
import dev.dixmk.minepreggo.client.screens.effect.SexOverlayManager;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.packet.UpdateBellyRubbingStateC2SPacket;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.ViewportEvent;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, value = Dist.CLIENT)
public class ClientEventHandler {
 
	private ClientEventHandler() {}
	
	/**
	 * Persistent camera offset storage - survives across ticks
	 * This prevents the position from being reset by server synchronization
	 */
	private static double cameraOffsetX = 0.0;
	private static double cameraOffsetY = 0.0;
	private static double cameraOffsetZ = 0.0;
	   
    public static void setCameraOffset(double offsetX, double offsetY, double offsetZ) {
        cameraOffsetX = offsetX;
        cameraOffsetY = offsetY;
        cameraOffsetZ = offsetZ;
    }
    
    public static void addCameraOffset(double offsetX, double offsetY, double offsetZ) {
        cameraOffsetX += offsetX;
        cameraOffsetY += offsetY;
        cameraOffsetZ += offsetZ;
    }
    
    /**
     * Reset camera offset to zero (default position)
     */
    public static void resetCameraOffset() {
        cameraOffsetX = 0.0;
        cameraOffsetY = 0.0;
        cameraOffsetZ = 0.0;
    }
    
    public static double getCameraOffsetX() {
        return cameraOffsetX;
    }

    public static double getCameraOffsetY() {
        return cameraOffsetY;
    }
    
    public static double getCameraOffsetZ() {
        return cameraOffsetZ;
    }
	
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();        
        var player = mc.player;  
        var level = mc.level;
        if (player == null || level == null) return;
    	
    	if (event.phase == TickEvent.Phase.END) {
        	SexOverlayManager.getInstance().tick();      	       

            for (Player ply : mc.level.players()) {
            	final var cache = PlayerAnimationManager.getInstance().get(ply);       	
            	cache.tick();   
            	evaluateBellyRubbingLogic(player, cache);
            }             
        }
    }
       
    private static void evaluateBellyRubbingLogic(Player player, PlayerAnimationCache cache) {
    	if (PlayerAnimationRegistry.getInstance().isBellyRubbingAnimation(cache.getCurrentAnimationName())) {
    		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
    			cap.getFemaleData().ifPresent(femaleData -> {
    				if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized()) {
    					var phase = femaleData.getPregnancySystem().getCurrentPregnancyStage();
    					if (phase.compareTo(PregnancyPhase.P2) > 0
    							&& cache.getContinuousAnimationTick() % PregnancySystemHelper.TOTAL_TICKS_CALM_BELLY_RUGGING_DOWN == 0) {					
    						MinepreggoModPacketHandler.INSTANCE.sendToServer(new UpdateBellyRubbingStateC2SPacket(player.getUUID()));
    					}
    				}
    			})
    		);
    	}
    }
    
    
    /**
     * This handler modifies the camera position in real-time WITHOUT affecting
     * the actual player entity position. This prevents server sync from resetting
     * the camera position each tick.
     * 
     * The key insight: We store offsets and apply them during rendering,
     * NOT by modifying the player's actual position.
     */
    @SubscribeEvent
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        
        if (player == null) return;      
        
        // Apply the persistent camera offset without modifying player position
        // This ensures the offset survives across ticks without server resync
        
        if (ClientPlayerHelper.isPlayingBirthAnimation(player)) {
            // Only update offset once when animation starts (if offset is 0)
            if (cameraOffsetY == 0.0) {
            	cameraOffsetY = -player.getEyeHeight(Pose.SLEEPING) * 4;
            }
        } else {
        	cameraOffsetY = 0;
        }     
    }
}

