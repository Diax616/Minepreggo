package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public record UpdatePlayerDataC2SPacket(UUID source, Gender gender, SkinType skinType) {
	public static UpdatePlayerDataC2SPacket decode(FriendlyByteBuf buffer) {	
		return new UpdatePlayerDataC2SPacket(
				buffer.readUUID(),
				buffer.readEnum(Gender.class),
				buffer.readEnum(SkinType.class));
	}
	
	public static void encode(UpdatePlayerDataC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.source);
		buffer.writeEnum(message.gender);
		buffer.writeEnum(message.skinType);
	}
	
	public static void handler(UpdatePlayerDataC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isServer()) {				
				var serverPlayer = context.getSender();
				
				serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
					cap.setGender(message.gender);
					cap.setSKinType(message.skinType);
				});
				
				MinepreggoMod.LOGGER.debug("Updating player data for {}: gender={}, customSkin={}",
						serverPlayer.getDisplayName().getString(), message.gender, message.skinType);
						
        		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
        				new SyncPlayerDataS2CPacket(message.source, message.gender, message.skinType));
			}			
		});
		context.setPacketHandled(true);
	}
}