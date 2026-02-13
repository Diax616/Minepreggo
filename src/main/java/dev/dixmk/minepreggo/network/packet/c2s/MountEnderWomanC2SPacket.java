package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record MountEnderWomanC2SPacket(int enderWomanId) {

	public static MountEnderWomanC2SPacket decode(FriendlyByteBuf buffer) {	
		return new MountEnderWomanC2SPacket(
				buffer.readVarInt());
	}
	
	public static void encode(MountEnderWomanC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.enderWomanId);
	}
	
	public static void handler(MountEnderWomanC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();		
    			if (serverPlayer.level().getEntity(message.enderWomanId) instanceof AbstractTamableEnderWoman enderWoman && enderWoman.canBeMountedBy(serverPlayer)) {
    				serverPlayer.startRiding(enderWoman, true);
    			}
            }		
		});
		context.setPacketHandled(true);
	}
}
