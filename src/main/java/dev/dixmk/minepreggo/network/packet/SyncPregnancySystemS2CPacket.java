package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SyncPregnancySystemS2CPacket(UUID targetId, PlayerPregnancySystemImpl.ClientData data) {

	public static SyncPregnancySystemS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SyncPregnancySystemS2CPacket(
				buffer.readUUID(),
				PlayerPregnancySystemImpl.ClientData.decode(buffer));
	}
	
	public static void encode(SyncPregnancySystemS2CPacket message, FriendlyByteBuf buffer) {	
		buffer.writeUUID(message.targetId);
		message.data.encode(buffer);
	}
	
	public static void handler(SyncPregnancySystemS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isClient()) {
				final var target = Minecraft.getInstance().player.level().getPlayerByUUID(message.targetId);
				if (target != null) {
					target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
						cap.getFemaleData().ifPresent(femaleData -> {
							femaleData.getPregnancySystem().setPregnancySymptom(message.data.pregnancySymptom());
							femaleData.getPregnancySystem().setPregnancyPain(message.data.pregnancyPain());
							femaleData.getPregnancySystem().setCurrentPregnancyStage(message.data.currentPregnancyPhase());											
						})
					);
				}	
			}			
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SyncPregnancySystemS2CPacket.class, SyncPregnancySystemS2CPacket::encode, SyncPregnancySystemS2CPacket::decode, SyncPregnancySystemS2CPacket::handler);
	}	
}
