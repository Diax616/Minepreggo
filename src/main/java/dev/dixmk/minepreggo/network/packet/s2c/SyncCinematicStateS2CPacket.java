package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public record SyncCinematicStateS2CPacket(UUID playerId, boolean cinematic) {

	public static SyncCinematicStateS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SyncCinematicStateS2CPacket(buffer.readUUID(), buffer.readBoolean());
	}
	
	public static void encode(SyncCinematicStateS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);
		buffer.writeBoolean(message.cinematic);
	}
	
	public static void handler(SyncCinematicStateS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isClient()) {			
				final Player target = Minecraft.getInstance().player.level().getPlayerByUUID(message.playerId);
				if (target == null) return;
				target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.setCinematic(message.cinematic));
			}
		});
		context.setPacketHandled(true);
	}
}
