package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

public record RemovePostPregnancyDataS2CPacket(UUID playerId) {
	public static RemovePostPregnancyDataS2CPacket decode(FriendlyByteBuf buffer) {	
		return new RemovePostPregnancyDataS2CPacket(
				buffer.readUUID());
	}
	
	public static void encode(RemovePostPregnancyDataS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);
	}
	
	public static void handler(RemovePostPregnancyDataS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isClient()) {	
				var player = Minecraft.getInstance().player;
				if (player.getUUID().equals(message.playerId)) {	
					player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
					cap.getFemaleData().ifPresent(FemalePlayerImpl::tryRemovePostPregnancyPhase));				
				}
			}
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RemovePostPregnancyDataS2CPacket.class, RemovePostPregnancyDataS2CPacket::encode, RemovePostPregnancyDataS2CPacket::decode, RemovePostPregnancyDataS2CPacket::handler);
	}
}
