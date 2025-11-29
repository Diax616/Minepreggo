package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record UpdatePlayerDataC2SPacket(UUID source, Gender gender, boolean customSkin) {
	public static UpdatePlayerDataC2SPacket decode(FriendlyByteBuf buffer) {	
		return new UpdatePlayerDataC2SPacket(
				buffer.readUUID(),
				buffer.readEnum(Gender.class),
				buffer.readBoolean());
	}
	
	public static void encode(UpdatePlayerDataC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.source);
		buffer.writeEnum(message.gender);
		buffer.writeBoolean(message.customSkin);
	}
	
	public static void handler(UpdatePlayerDataC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isServer()) {				
				var serverPlayer = context.getSender();
				
				serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
					cap.setGender(message.gender);
					cap.setCustomSkin(message.customSkin);
				});
				
				MinepreggoMod.LOGGER.info("Updating player data for {}: gender={}, customSkin={}",
						serverPlayer.getDisplayName().getString(), message.gender, message.customSkin);
						
        		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), new SyncPlayerDataS2CPacket(message.source, message.gender, message.customSkin));
			}			
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdatePlayerDataC2SPacket.class, UpdatePlayerDataC2SPacket::encode, UpdatePlayerDataC2SPacket::decode, UpdatePlayerDataC2SPacket::handler);
	}
}