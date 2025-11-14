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
public record SexCinematicAbortC2SPacket(int mobId) {
	
	public static SexCinematicAbortC2SPacket decode(FriendlyByteBuf buffer) {	
		return new SexCinematicAbortC2SPacket(buffer.readVarInt());
	}
	
	public static void encode(SexCinematicAbortC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.mobId);
	}
	
	public static void handler(SexCinematicAbortC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		MinepreggoModPacketHandler.addNetworkMessage(SexCinematicAbortC2SPacket.class, SexCinematicAbortC2SPacket::encode, SexCinematicAbortC2SPacket::decode, SexCinematicAbortC2SPacket::handler);
	}
}
