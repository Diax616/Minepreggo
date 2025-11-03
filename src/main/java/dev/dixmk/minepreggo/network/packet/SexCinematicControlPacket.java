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
public record SexCinematicControlPacket(boolean start, int mobEntityId) {
	
	public static SexCinematicControlPacket decode(FriendlyByteBuf buffer) {	
		return new SexCinematicControlPacket(
				buffer.readBoolean(),
				buffer.readVarInt());
	}
	
	public static void encode(SexCinematicControlPacket message, FriendlyByteBuf buffer) {
		buffer.writeBoolean(message.start);
		buffer.writeVarInt(message.mobEntityId);
	}
	
	public static void handler(SexCinematicControlPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		MinepreggoModPacketHandler.addNetworkMessage(SexCinematicControlPacket.class, SexCinematicControlPacket::encode, SexCinematicControlPacket::decode, SexCinematicControlPacket::handler);
	}
}
