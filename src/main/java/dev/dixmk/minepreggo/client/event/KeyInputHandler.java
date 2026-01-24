package dev.dixmk.minepreggo.client.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModKeyMappings;
import dev.dixmk.minepreggo.network.packet.c2s.RequestBellyRubbingAnimationC2SPacket;
import dev.dixmk.minepreggo.network.packet.c2s.StopPlayerAnimationC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyInputHandler {
	
	private KeyInputHandler() {}

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
    	var player = Minecraft.getInstance().player; 
    	
    	if (MinepreggoModKeyMappings.RUB_BELLY_KEY.consumeClick()) {        
        	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
        		cap.getFemaleData().ifPresent(femaleData -> {
        			if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
        				final var pain = femaleData.getPregnancyData().getPregnancyPain();
        				if (pain != null && pain.incapacitate) {
        					return;
        				}
        				       				
        				if (PlayerAnimationManager.getInstance().get(player).hasActiveAnimation()) {
            				MinepreggoModPacketHandler.INSTANCE.sendToServer(new StopPlayerAnimationC2SPacket(player.getUUID()));
        				}
        				else {
            				MinepreggoModPacketHandler.INSTANCE.sendToServer(new RequestBellyRubbingAnimationC2SPacket(player.getUUID(), femaleData.getPregnancyData().getCurrentPregnancyPhase()));
        				}
        			}
        		})
        	);	
        }
    }	
}
