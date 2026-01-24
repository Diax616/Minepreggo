package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record RemovePlayerJigglePhysicsS2CPacket(UUID playerId) {

	public static RemovePlayerJigglePhysicsS2CPacket decode(FriendlyByteBuf buffer) {	
		return new RemovePlayerJigglePhysicsS2CPacket(buffer.readUUID());
	}
	
	public static void encode(RemovePlayerJigglePhysicsS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);
	}
	
	public static void handler(RemovePlayerJigglePhysicsS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
	        if (context.getDirection().getReceptionSide().isClient()) {
				var local = Minecraft.getInstance().player;
				if (local == null) return;
				var player = local.level().getPlayerByUUID(message.playerId);
				if (player != null) {
					JigglePhysicsManager.getInstance().removeJigglePhysics(player);
				}
	        }	
		});
		context.setPacketHandled(true);
	}
}