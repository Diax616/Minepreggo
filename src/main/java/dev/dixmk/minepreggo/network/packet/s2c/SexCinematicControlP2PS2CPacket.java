package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.client.CinematicManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record SexCinematicControlP2PS2CPacket(boolean start) {

	public static SexCinematicControlP2PS2CPacket decode(FriendlyByteBuf buffer) {	
		return new SexCinematicControlP2PS2CPacket(
				buffer.readBoolean());
	}
	
	public static void encode(SexCinematicControlP2PS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeBoolean(message.start);
	}
	
	public static void handler(SexCinematicControlP2PS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isClient()) {
                Minecraft mc = Minecraft.getInstance();
                if (message.start) {
                	CinematicManager.getInstance().startCinematicWithPlayer(mc.player);                   	
                } else {
                	CinematicManager.getInstance().endCinematic();
                }                    	
            }	  
		});
		context.setPacketHandled(true);
	}
}
