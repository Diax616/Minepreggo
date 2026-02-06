package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record MountEntityC2SPacket(int targetId) {

	public static MountEntityC2SPacket decode(FriendlyByteBuf buffer) {	
		return new MountEntityC2SPacket(
				buffer.readVarInt());
	}
	
	public static void encode(MountEntityC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.targetId);
	}
	
	public static void handler(MountEntityC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();		
    			var target = serverPlayer.level().getEntity(message.targetId);
    			if (target != null) {
    				serverPlayer.startRiding(target, true);
    			}
            }		
		});
		context.setPacketHandled(true);
	}
}
