package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SyncPlayerDataS2CPacket(UUID source, Gender gender, boolean customSKin) {
	public static SyncPlayerDataS2CPacket decode(FriendlyByteBuf buffer) {			
		return new SyncPlayerDataS2CPacket(
				buffer.readUUID(),
				buffer.readEnum(Gender.class),
				buffer.readBoolean());
	}
	
	public static void encode(SyncPlayerDataS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.source);
		buffer.writeEnum(message.gender);
		buffer.writeBoolean(message.customSKin);
	}
	
	public static void handler(SyncPlayerDataS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isClient()) {    	
            	var target = Minecraft.getInstance().player.level().getPlayerByUUID(message.source);
            	            	
            	if (target != null) {
            		target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> {
                        c.setGender(message.gender);
                        c.setCustomSkin(message.customSKin);
                        
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
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SyncPlayerDataS2CPacket.class, SyncPlayerDataS2CPacket::encode, SyncPlayerDataS2CPacket::decode, SyncPlayerDataS2CPacket::handler);
	}
}
