package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.SexCinematicManager;
import dev.dixmk.minepreggo.client.screens.effect.SexOverlayManager;
import dev.dixmk.minepreggo.network.packet.SexCinematicAbortPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, value = Dist.CLIENT)
public class ClientEventHandler {
	
	private ClientEventHandler() {}

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
        	SexOverlayManager.tick();
        }
        
        if (event.phase == TickEvent.Phase.START) {
            Minecraft mc = Minecraft.getInstance();        
            var player = mc.player;        
            if (player == null) return;
                       
            if (SexCinematicManager.isInCinematic()) {
                Entity mob = mc.level.getEntity(SexCinematicManager.getActiveMobId());
                if (mob == null || mob.isRemoved() || player.distanceToSqr(mob) > 25.0) {
                    SexCinematicManager.endCinematic();
                    MinepreggoModPacketHandler.INSTANCE.sendToServer(new SexCinematicAbortPacket(-1));
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
