package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexP2PMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestSexP2PC2SPacket(int sourcePlayerId, int targetPlayerId) {
	
	public static RequestSexP2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new RequestSexP2PC2SPacket(
				buffer.readVarInt(),
				buffer.readVarInt());
	}
	
	public static void encode(RequestSexP2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.sourcePlayerId);
		buffer.writeVarInt(message.targetPlayerId);
	}
	
	public static void handler(RequestSexP2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {	
				var sender = context.getSender();
				var level = sender.level();
	
				final ServerPlayer source = level.getEntity(message.sourcePlayerId) instanceof ServerPlayer s ? s : null;
				final ServerPlayer target = level.getEntity(message.targetPlayerId) instanceof ServerPlayer t ? t : null;
						
				if (source != null && target != null) {
					RequestSexP2PMenu.create(target, sender);
				}
			}
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestSexP2PC2SPacket.class, RequestSexP2PC2SPacket::encode, RequestSexP2PC2SPacket::decode, RequestSexP2PC2SPacket::handler);
	}
}
