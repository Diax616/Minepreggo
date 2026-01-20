package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.UUID;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record SyncJigglePhysicsS2CPacket(UUID playerId, SkinType skinType, @Nullable PregnancyPhase pregnancyPhase) {

	public static SyncJigglePhysicsS2CPacket decode(FriendlyByteBuf buffer) {	
		UUID uuid = buffer.readUUID();
		SkinType skinType = buffer.readEnum(SkinType.class);
		PregnancyPhase phase = null;
		if (buffer.readBoolean()) {
			phase = buffer.readEnum(PregnancyPhase.class);
		}
		return new SyncJigglePhysicsS2CPacket(uuid, skinType, phase);
	}
	
	public static void encode(SyncJigglePhysicsS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);
		buffer.writeEnum(message.skinType);
		buffer.writeBoolean(message.pregnancyPhase != null);
		if (message.pregnancyPhase != null) {
			buffer.writeEnum(message.pregnancyPhase);
		}		
	}
	
	public static void handler(SyncJigglePhysicsS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
	        if (context.getDirection().getReceptionSide().isClient()) {
				var local = Minecraft.getInstance().player;
				if (local == null) return;
				var player = local.level().getPlayerByUUID(message.playerId);
				
				if (player != null) {
					JigglePhysicsManager.getInstance().updateJigglePhysics(player, message.skinType, message.pregnancyPhase);
				}
	        }	
		});
		context.setPacketHandled(true);
	}
	
}
