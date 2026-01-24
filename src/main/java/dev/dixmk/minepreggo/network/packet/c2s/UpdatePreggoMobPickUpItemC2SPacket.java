package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record UpdatePreggoMobPickUpItemC2SPacket(int preggoMobId, boolean canPickUpItem) {
	
	public static UpdatePreggoMobPickUpItemC2SPacket decode(FriendlyByteBuf buffer) {	
		return new UpdatePreggoMobPickUpItemC2SPacket(
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(UpdatePreggoMobPickUpItemC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeBoolean(message.canPickUpItem);
	}
	
	public static void handler(UpdatePreggoMobPickUpItemC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();			
    			var world = serverPlayer.level();  			
    			if (world.getEntity(message.preggoMobId) instanceof PreggoMob preggoMob && preggoMob instanceof ITamablePreggoMob<?>) {
    				preggoMob.setCanPickUpLoot(message.canPickUpItem);
    			}
            }
		});
		context.setPacketHandled(true);
	}
}
