package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.SexCinematicManager;
import dev.dixmk.minepreggo.client.animation.player.ArmAnimationManager;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import dev.dixmk.minepreggo.client.screens.effect.SexOverlayManager;
import dev.dixmk.minepreggo.init.MinepreggoModKeyMappings;
import dev.dixmk.minepreggo.network.packet.RequestArmAnimationC2SPacket;
import dev.dixmk.minepreggo.network.packet.SexCinematicAbortC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, value = Dist.CLIENT)
public class ClientEventHandler {
	
	 private static int armAnimationIndex = 0;
	
	private ClientEventHandler() {}

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();        
        var player = mc.player;        
        if (player == null) return;
    	
    	if (event.phase == TickEvent.Phase.END) {
        	SexOverlayManager.getInstance().tick();
        	       
            ArmAnimationManager.getInstance().tick();
       
            // Handle key press
            if (MinepreggoModKeyMappings.ANIMATION_KEY.consumeClick() && mc.player != null) {
                MinepreggoModPacketHandler.INSTANCE.sendToServer(new RequestArmAnimationC2SPacket(armAnimationIndex));          
            }
            
            if (mc.level != null) {
                for (Player ply : mc.level.players()) {
                    PlayerAnimationManager.getInstance().get(ply).tick();
                }
            }
        }
        
        else if (event.phase == TickEvent.Phase.START) {                    
            if (SexCinematicManager.isInCinematic()) {
                Entity mob = mc.level.getEntity(SexCinematicManager.getActiveMobId());
                if (mob == null || mob.isRemoved() || player.distanceToSqr(mob) > 25.0) {
                    SexCinematicManager.endCinematic();
                    MinepreggoModPacketHandler.INSTANCE.sendToServer(new SexCinematicAbortC2SPacket(-1));
                    return;
                }
                
                player.input.leftImpulse = 0.0F;
                player.input.forwardImpulse = 0.0F;
                player.input.jumping = false;
                player.input.shiftKeyDown = false;

                player.setYRot(SexCinematicManager.getStoredYaw());
                player.setXRot(SexCinematicManager.getStoredPitch());
                player.yRotO = SexCinematicManager.getStoredYaw();
                player.xRotO = SexCinematicManager.getStoredPitch(); 
            }                  
        }
    } 
    

}
