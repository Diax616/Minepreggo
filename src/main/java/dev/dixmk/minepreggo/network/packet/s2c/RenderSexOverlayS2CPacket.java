package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.client.screens.effect.SexOverlayManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public record RenderSexOverlayS2CPacket(int totalOverlayTicks, int totalPauseTicks) {

	public static RenderSexOverlayS2CPacket decode(FriendlyByteBuf buffer) {	
		return new RenderSexOverlayS2CPacket(
				buffer.readInt(),
				buffer.readInt());
	}
	
	public static void encode(RenderSexOverlayS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.totalOverlayTicks);
		buffer.writeInt(message.totalPauseTicks);
	}
	
	public static void handler(RenderSexOverlayS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> 
	        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {       
	        	var manager = SexOverlayManager.getInstance();
	        	if (!manager.isActive()) {
	        		manager.trigger(message.totalOverlayTicks, message.totalPauseTicks);	
	        	}
	        })			
		);
		context.setPacketHandled(true);
	}
}
