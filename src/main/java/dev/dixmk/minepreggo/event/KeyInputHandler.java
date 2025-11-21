package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoModKeyMappings;
import dev.dixmk.minepreggo.network.packet.ForceSexRequestM2PC2SPacket;
import dev.dixmk.minepreggo.network.packet.RequestArmAnimationC2SPacket;
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
        if (MinepreggoModKeyMappings.TEST_KEY.consumeClick()) {    	
        	MinepreggoModPacketHandler.INSTANCE.sendToServer(new ForceSexRequestM2PC2SPacket(player.getId()));	
        }
        else if (MinepreggoModKeyMappings.ANIMATION_KEY.consumeClick()) {
            MinepreggoModPacketHandler.INSTANCE.sendToServer(new RequestArmAnimationC2SPacket("rubbing_belly_p3"));          
        }
    }	
}
