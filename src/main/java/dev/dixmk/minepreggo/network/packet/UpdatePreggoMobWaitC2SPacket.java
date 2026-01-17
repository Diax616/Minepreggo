package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record UpdatePreggoMobWaitC2SPacket(int preggoMobId, boolean isWaiting) {

	public static UpdatePreggoMobWaitC2SPacket decode(FriendlyByteBuf buffer) {	
		return new UpdatePreggoMobWaitC2SPacket(
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(UpdatePreggoMobWaitC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeBoolean(message.isWaiting);
	}
	
	public static void handler(UpdatePreggoMobWaitC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();			
    			var world = serverPlayer.level();
    			
    			if (world.getEntity(message.preggoMobId) instanceof ITamablePreggoMob<?> mob) {
    				mob.getTamableData().setWaiting(message.isWaiting);
    			}
            }
		});
		context.setPacketHandled(true);
	}
}
