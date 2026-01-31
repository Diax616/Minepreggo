package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.pregnancy.SexHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public record ResponseSexRequestP2PC2SPacket(int sourcePlayerId, int targetPlayerId, boolean accept) {

	public static ResponseSexRequestP2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ResponseSexRequestP2PC2SPacket(
				buffer.readVarInt(),
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(ResponseSexRequestP2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.sourcePlayerId);
		buffer.writeVarInt(message.targetPlayerId);
		buffer.writeBoolean(message.accept);
	}
	
	public static void handler(ResponseSexRequestP2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {									
				var sender = context.getSender();
				var level = sender.level();
				final ServerPlayer source = level.getEntity(message.sourcePlayerId) instanceof ServerPlayer s ? s : null;
				final ServerPlayer target = level.getEntity(message.targetPlayerId) instanceof ServerPlayer t ? t : null;
		
				if (source != null && target != null) {					
					if (message.accept) {	
						SexHelper.acceptSexRequest(source, target);
					}
					else {
						SexHelper.rejectSexRequest(source, target);
					}
				}
				else {
					MinepreggoMod.LOGGER.warn("Could not find source or target player for ResponseSexRequestP2PC2SPacket: source id {}, target id {}", message.sourcePlayerId, message.targetPlayerId);
				}
			}
		});
		context.setPacketHandled(true);
	}
	

}
