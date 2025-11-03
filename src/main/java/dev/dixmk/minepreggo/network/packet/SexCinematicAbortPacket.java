package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.server.ServerSexCinematicManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SexCinematicAbortPacket(int mobId) {
	
	public static SexCinematicAbortPacket decode(FriendlyByteBuf buffer) {	
		return new SexCinematicAbortPacket(buffer.readVarInt());
	}
	
	public static void encode(SexCinematicAbortPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.mobId);
	}
	
	public static void handler(SexCinematicAbortPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
                var player = context.getSender();
                if (player != null) {
                    ServerSexCinematicManager.end(player);
                }          	          	
            }		
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SexCinematicAbortPacket.class, SexCinematicAbortPacket::encode, SexCinematicAbortPacket::decode, SexCinematicAbortPacket::handler);
	}
}
