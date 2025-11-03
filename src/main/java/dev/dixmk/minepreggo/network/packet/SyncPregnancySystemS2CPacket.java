package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SyncPregnancySystemS2CPacket(int targetId, PregnancySymptom pregnancySymptom, PregnancyPain pregnancyPain) {

	public static SyncPregnancySystemS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SyncPregnancySystemS2CPacket(
				buffer.readVarInt(), 
				buffer.readEnum(PregnancySymptom.class), 
				buffer.readEnum(PregnancyPain.class));
	}
	
	public static void encode(SyncPregnancySystemS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.targetId);
		buffer.writeEnum(message.pregnancySymptom);
		buffer.writeEnum(message.pregnancyPain);
	}
	
	public static void handler(SyncPregnancySystemS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isClient()) {
				Minecraft.getInstance().player.level().getEntity(message.targetId).getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(c -> {										
					c.setPregnancySymptom(message.pregnancySymptom);					
					c.setPregnancyPain(message.pregnancyPain);
				});			
			}			
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SyncPregnancySystemS2CPacket.class, SyncPregnancySystemS2CPacket::encode, SyncPregnancySystemS2CPacket::decode, SyncPregnancySystemS2CPacket::handler);
	}	
}
