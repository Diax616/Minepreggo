package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record RemovePreggoMobJigglePhysicsS2CPacket(int entityId) {

	public static RemovePreggoMobJigglePhysicsS2CPacket decode(FriendlyByteBuf buffer) {	
		return new RemovePreggoMobJigglePhysicsS2CPacket(buffer.readVarInt());
	}
	
	public static void encode(RemovePreggoMobJigglePhysicsS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.entityId);
	}
	
	public static void handler(RemovePreggoMobJigglePhysicsS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
	        if (context.getDirection().getReceptionSide().isClient()) {
				var local = Minecraft.getInstance().player;
				if (local == null) return;

				if (local.level().getEntity(message.entityId) instanceof PreggoMob preggoMob) {
					JigglePhysicsManager.getInstance().removeJigglePhysics(preggoMob);
				}
	        }	
		});
		context.setPacketHandled(true);
	}
	
}
