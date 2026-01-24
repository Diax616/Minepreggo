package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record UpdatePreggoMobBreakBlocksC2SPacket(int preggoMobId, boolean canBreakBlocks) {
	
	public static UpdatePreggoMobBreakBlocksC2SPacket decode(FriendlyByteBuf buffer) {	
		return new UpdatePreggoMobBreakBlocksC2SPacket(
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(UpdatePreggoMobBreakBlocksC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeBoolean(message.canBreakBlocks);
	}
	
	public static void handler(UpdatePreggoMobBreakBlocksC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();			
    			var world = serverPlayer.level();
    			
    			if (world.getEntity(message.preggoMobId) instanceof ITamablePreggoMob<?> mob) {
    				mob.setBreakBlocks(message.canBreakBlocks);
    			}
            }
		});
		context.setPacketHandled(true);
	}
}
