package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SyncPregnancyEffectsS2CPacket(UUID targetId, int craving, int milking, int bellyRubs, int horny, Craving typeOfCraving) {		
	public static SyncPregnancyEffectsS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SyncPregnancyEffectsS2CPacket(
				buffer.readUUID(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readEnum(Craving.class));
	}
	
	public static void encode(SyncPregnancyEffectsS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.targetId);
		buffer.writeInt(message.craving);
		buffer.writeInt(message.milking);
		buffer.writeInt(message.bellyRubs);
		buffer.writeInt(message.horny);
		buffer.writeEnum(message.typeOfCraving);
	}
	
	public static void handler(SyncPregnancyEffectsS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isClient()) {	
				if (Minecraft.getInstance().player == null) return; 
				
				var player = Minecraft.getInstance().player.level().getPlayerByUUID(message.targetId);
				
				if (player != null) {
					player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS).ifPresent(c -> {					
						c.setCraving(message.craving);
						c.setMilking(message.milking);
						c.setBellyRubs(message.bellyRubs);
						c.setHorny(message.horny);
						c.setTypeOfCraving(message.typeOfCraving);
					});	
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