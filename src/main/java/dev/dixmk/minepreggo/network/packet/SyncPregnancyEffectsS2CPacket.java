package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyEffectsImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SyncPregnancyEffectsS2CPacket(UUID targetId, PlayerPregnancyEffectsImpl.ClientData data) {		
	
	public static SyncPregnancyEffectsS2CPacket decode(FriendlyByteBuf buffer) {		
		return new SyncPregnancyEffectsS2CPacket(buffer.readUUID(), PlayerPregnancyEffectsImpl.ClientData.decode(buffer));
	}
	
	public static void encode(SyncPregnancyEffectsS2CPacket message, FriendlyByteBuf buffer) {	
		buffer.writeUUID(message.targetId);
		message.data.encode(buffer);
	}
	
	public static void handler(SyncPregnancyEffectsS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isClient()) {							
				var player = Minecraft.getInstance().player;
				if (player.getUUID().equals(message.targetId)) {					
					player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
						cap.getFemaleData().ifPresent(femaleData -> {
							var pregnancyEffects = femaleData.getPregnancyEffects();
							pregnancyEffects.setCraving(message.data.craving());
							pregnancyEffects.setMilking(message.data.milking());
							pregnancyEffects.setBellyRubs(message.data.bellyRubs());
							pregnancyEffects.setHorny(message.data.horny());
							pregnancyEffects.setTypeOfCravingBySpecies(message.data.typeOfCravingBySpecies());
						})
					);
				}
				else {
					MinepreggoMod.LOGGER.warn("SyncPregnancyEffectsS2CPacket: Packet target UUID {} does not match local player or player is null", message.targetId);
				}
			}			
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SyncPregnancyEffectsS2CPacket.class, SyncPregnancyEffectsS2CPacket::encode, SyncPregnancyEffectsS2CPacket::decode, SyncPregnancyEffectsS2CPacket::handler);
	}
}