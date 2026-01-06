package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record UpdateJigglePhysicsS2CPacket(UUID playerId, @Nullable PregnancyPhase pregnancyPhase, SkinType skinType) {

	public static UpdateJigglePhysicsS2CPacket decode(FriendlyByteBuf buffer) {	
		UUID uuid = buffer.readUUID();
		PregnancyPhase phase = null;
		if (buffer.readBoolean()) {
			phase = buffer.readEnum(PregnancyPhase.class);
		}
		SkinType skinType = buffer.readEnum(SkinType.class);
		
		return new UpdateJigglePhysicsS2CPacket(uuid, phase, skinType);
	}
	
	public static void encode(UpdateJigglePhysicsS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);		
		buffer.writeBoolean(message.pregnancyPhase != null);
		if (message.pregnancyPhase != null) {
			buffer.writeEnum(message.pregnancyPhase);
		}		
		buffer.writeEnum(message.skinType);
	}
	
	public static void handler(UpdateJigglePhysicsS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
	        if (context.getDirection().getReceptionSide().isClient()) {
				var local = Minecraft.getInstance().player;
				if (local == null) return;
				var player = local.level().getPlayerByUUID(message.playerId);
				if (player != null) {		
					if (message.pregnancyPhase != null) {
						JigglePhysicsManager.getInstance().updateForPhase(message.playerId, message.pregnancyPhase, message.skinType);
						MinepreggoMod.LOGGER.debug("Received jiggle physics update for player {} with pregnancy phase {} and skin type {}", message.playerId, message.pregnancyPhase, message.skinType);
					}
					else {
						JigglePhysicsManager.getInstance().updateForNonPregnancy(message.playerId);
						MinepreggoMod.LOGGER.debug("Received jiggle physics update for player {} with no pregnancy", message.playerId);
					}
				}
	        }	
		});
		context.setPacketHandled(true);
	}
	
}
