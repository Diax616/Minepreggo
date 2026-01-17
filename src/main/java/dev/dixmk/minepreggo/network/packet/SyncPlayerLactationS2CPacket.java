package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public record SyncPlayerLactationS2CPacket(UUID source, int postPartumLactation) {

	public static SyncPlayerLactationS2CPacket decode(FriendlyByteBuf buffer) {				
		return new SyncPlayerLactationS2CPacket(buffer.readUUID(),buffer.readInt());
	}
	
	public static void encode(SyncPlayerLactationS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.source);
		buffer.writeInt(message.postPartumLactation);
	}
	
	public static void handler(SyncPlayerLactationS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isClient()) {	
				final Player target = Minecraft.getInstance().player.level().getPlayerByUUID(message.source);
				if (target == null) return;
				target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
					cap.getFemaleData().ifPresent(femaleData -> femaleData.getPostPregnancyData().ifPresent(post -> post.setPostPartumLactation(message.postPartumLactation)))
				);			
			}			
		});
		context.setPacketHandled(true);
	}
}
