package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.SexCinematicManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SexCinematicControlP2PC2SPacket(boolean start) {

	public static SexCinematicControlP2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new SexCinematicControlP2PC2SPacket(
				buffer.readBoolean());
	}
	
	public static void encode(SexCinematicControlP2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeBoolean(message.start);
	}
	
	public static void handler(SexCinematicControlP2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isClient()) {
                Minecraft mc = Minecraft.getInstance();
                if (message.start) {
                	SexCinematicManager.getInstance().startCinematicWithPlayer(mc.player);                   	
                } else {
                	SexCinematicManager.getInstance().endCinematic();
                }                    	
            }	  
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SexCinematicControlP2PC2SPacket.class, SexCinematicControlP2PC2SPacket::encode, SexCinematicControlP2PC2SPacket::decode, SexCinematicControlP2PC2SPacket::handler);
	}
}
