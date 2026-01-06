package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.CinematicManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record SexCinematicControlP2MS2CPacket(boolean start, int mobEntityId) {
	
	public static SexCinematicControlP2MS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SexCinematicControlP2MS2CPacket(
				buffer.readBoolean(),
				buffer.readVarInt());
	}
	
	public static void encode(SexCinematicControlP2MS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeBoolean(message.start);
		buffer.writeVarInt(message.mobEntityId);
	}
	
	public static void handler(SexCinematicControlP2MS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isClient()) {
                Minecraft mc = Minecraft.getInstance();
                if (message.start) {
                	CinematicManager.getInstance().startCinematic(mc.player, message.mobEntityId);                   	
                } else {
                	CinematicManager.getInstance().endCinematic();
                }                 
                MinepreggoMod.LOGGER.debug("SEX CINEMATIC CONTROL: player={}, id={}, mobId={}, start={}",
                		mc.player.getName().getString(), mc.player.getId(), message.mobEntityId, message.start);     	
            }	  
		});
		context.setPacketHandled(true);
	}
}
