package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.screens.effect.SexOverlayManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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
	        	if (!SexOverlayManager.getInstance().isActive()) {
	                SexOverlayManager.getInstance().trigger(message.totalOverlayTicks, message.totalPauseTicks);	
	        	}
	        })			
		);
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RenderSexOverlayS2CPacket.class, RenderSexOverlayS2CPacket::encode, RenderSexOverlayS2CPacket::decode, RenderSexOverlayS2CPacket::handler);
	}
}
