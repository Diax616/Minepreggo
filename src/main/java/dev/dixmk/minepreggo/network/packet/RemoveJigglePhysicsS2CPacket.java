package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record RemoveJigglePhysicsS2CPacket(UUID playerId) {

	public static RemoveJigglePhysicsS2CPacket decode(FriendlyByteBuf buffer) {	
		return new RemoveJigglePhysicsS2CPacket(
				buffer.readUUID());
	}
	
	public static void encode(RemoveJigglePhysicsS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);
	}
	
	public static void handler(RemoveJigglePhysicsS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
	        if (context.getDirection().getReceptionSide().isClient()) {
				var local = Minecraft.getInstance().player;
				if (local == null) return;
				var player = local.level().getPlayerByUUID(message.playerId);
	
				if (player != null) {
					JigglePhysicsManager.getInstance().remove(player.getUUID());
				}
	        }	
		});
		context.setPacketHandled(true);
	}
}