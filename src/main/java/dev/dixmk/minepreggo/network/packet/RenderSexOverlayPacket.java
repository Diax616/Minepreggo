package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.screen.effect.SexOverlayManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RenderSexOverlayPacket(boolean start) {

	public static RenderSexOverlayPacket decode(FriendlyByteBuf buffer) {	
		return new RenderSexOverlayPacket(
				buffer.readBoolean());
	}
	
	public static void encode(RenderSexOverlayPacket message, FriendlyByteBuf buffer) {
		buffer.writeBoolean(message.start);
	}
	
	public static void handler(RenderSexOverlayPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> 
	        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {       
	        	if (!SexOverlayManager.isActive()) {
	                SexOverlayManager.trigger();	
	        	}
	        })			
		);
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RenderSexOverlayPacket.class, RenderSexOverlayPacket::encode, RenderSexOverlayPacket::decode, RenderSexOverlayPacket::handler);
	}
}
