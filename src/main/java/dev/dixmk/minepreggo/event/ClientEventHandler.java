package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.player.ArmAnimationManager;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import dev.dixmk.minepreggo.client.screens.effect.SexOverlayManager;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, value = Dist.CLIENT)
public class ClientEventHandler {
	
	private ClientEventHandler() {}

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();        
        var player = mc.player;  
        var level = mc.level;
        if (player == null || level == null) return;
    	
    	if (event.phase == TickEvent.Phase.END) {
        	SexOverlayManager.getInstance().tick();      	       
            ArmAnimationManager.getInstance().tick();

            for (Player ply : mc.level.players()) {
                PlayerAnimationManager.getInstance().get(ply).tick();
            }
        }
    } 
}
