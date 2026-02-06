package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableMonsterEnderWoman;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record TeleportWithEnderWomanC2SPacket(int enderWomanId, BlockPos targetPos) {
	
	public static TeleportWithEnderWomanC2SPacket decode(FriendlyByteBuf buffer) {	
		return new TeleportWithEnderWomanC2SPacket(
				buffer.readVarInt(),
				buffer.readBlockPos());
	}
	
	public static void encode(TeleportWithEnderWomanC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.enderWomanId);
		buffer.writeBlockPos(message.targetPos);
	}
	
	public static void handler(TeleportWithEnderWomanC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();		 			
    			if (serverPlayer.level().getEntity(message.enderWomanId) instanceof AbstractTamableMonsterEnderWoman enderWoman) {
    				enderWoman.teleportWithOwner(message.targetPos);    			
    			}	
            }		
		});
		context.setPacketHandled(true);
	}
}
