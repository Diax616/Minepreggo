package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SyncPregnancyEffectsS2CPacket(UUID targetId, int craving, int milking, int bellyRubs, int horny, @Nullable Pair<Craving, Species> typeOfCravingBySpecies) {		
	public static SyncPregnancyEffectsS2CPacket decode(FriendlyByteBuf buffer) {		
		UUID targetId = buffer.readUUID();
		int craving = buffer.readInt();
		int milking = buffer.readInt();	
		int bellyRubs = buffer.readInt();
		int horny = buffer.readInt();
		Craving typeOfCraving = null;
		Species typeOfSpecies = null;		
		if (buffer.readBoolean()) {
			typeOfCraving = buffer.readEnum(Craving.class);
			typeOfSpecies = buffer.readEnum(Species.class);
		}
		return new SyncPregnancyEffectsS2CPacket(targetId, craving, milking, bellyRubs, horny, Pair.of(typeOfCraving, typeOfSpecies));
	}
	
	public static void encode(SyncPregnancyEffectsS2CPacket message, FriendlyByteBuf buffer) {	
		buffer.writeUUID(message.targetId);
		buffer.writeInt(message.craving);
		buffer.writeInt(message.milking);
		buffer.writeInt(message.bellyRubs);
		buffer.writeInt(message.horny);	
		buffer.writeBoolean(message.typeOfCravingBySpecies != null);	
		if (message.typeOfCravingBySpecies != null) {
			buffer.writeEnum(message.typeOfCravingBySpecies.getKey());
			buffer.writeEnum(message.typeOfCravingBySpecies.getValue());
		}		
	}
	
	public static void handler(SyncPregnancyEffectsS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isClient()) {							
				var player = Minecraft.getInstance().player;
				if (player.getUUID().equals(message.targetId)) {					
					MinepreggoMod.LOGGER.debug("SyncPregnancyEffectsS2CPacket: Received packet to sync pregnancy effects for player with UUID {}: Craving={}, Milking={}, BellyRubs={}, Horny={}, TypeOfCravingBySpecies={}",
							message.targetId, message.craving, message.milking, message.bellyRubs, message.horny, message.typeOfCravingBySpecies);
					
					player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(cap -> {
						cap.setCraving(message.craving);
						cap.setMilking(message.milking);
						cap.setBellyRubs(message.bellyRubs);
						cap.setHorny(message.horny);
						cap.setTypeOfCravingBySpecies(message.typeOfCravingBySpecies);
					});
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