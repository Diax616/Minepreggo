package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.SexCinematicManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SexCinematicControlS2CPacket(boolean start, int mobEntityId) {
	
	public static SexCinematicControlS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SexCinematicControlS2CPacket(
				buffer.readBoolean(),
				buffer.readVarInt());
	}
	
	public static void encode(SexCinematicControlS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeBoolean(message.start);
		buffer.writeVarInt(message.mobEntityId);
	}
	
	public static void handler(SexCinematicControlS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isClient()) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    if (message.start) {
                    	SexCinematicManager.startCinematic(mc.player, message.mobEntityId);                   	
                    } else {
                    	SexCinematicManager.endCinematic();
                    }                 
                    MinepreggoMod.LOGGER.debug("SEX CINEMATIC CONTROL: player={}, id={}, mobId={}, start={}",
                    		mc.player.getName().getString(), mc.player.getId(), message.mobEntityId, message.start);
                }       	
            }	  
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SexCinematicControlS2CPacket.class, SexCinematicControlS2CPacket::encode, SexCinematicControlS2CPacket::decode, SexCinematicControlS2CPacket::handler);
	}
}
