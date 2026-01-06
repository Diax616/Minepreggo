package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record SyncPlayerDataS2CPacket(UUID source, Gender gender, SkinType skinType) {
	public static SyncPlayerDataS2CPacket decode(FriendlyByteBuf buffer) {			
		return new SyncPlayerDataS2CPacket(
				buffer.readUUID(),
				buffer.readEnum(Gender.class),
				buffer.readEnum(SkinType.class));
	}
	
	public static void encode(SyncPlayerDataS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.source);
		buffer.writeEnum(message.gender);
		buffer.writeEnum(message.skinType);
	}
	
	public static void handler(SyncPlayerDataS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isClient()) {    	
            	var target = Minecraft.getInstance().player.level().getPlayerByUUID(message.source);
            	            	
            	if (target != null) {
            		target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> {
                        c.setGender(message.gender);
                        c.setSKinType(message.skinType);
                        
                        MinepreggoMod.LOGGER.debug("Synchronized player data for {} from {}", 
                        		Minecraft.getInstance().player.getName().getString(), target.getDisplayName().getString());
                    });
				}
            	else {
            		MinepreggoMod.LOGGER.warn("SyncPlayerDataS2CPacket: Packet target UUID {} does not match local player or player is null", message.source);
            	}
            }
		});
		context.setPacketHandled(true);
	}
}
