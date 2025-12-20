package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.common.animation.PlayerAnimationRegistry;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestBellyRubbingAnimationC2SPacket(UUID target, PregnancyPhase phase) {
	
	public static RequestBellyRubbingAnimationC2SPacket decode(FriendlyByteBuf buffer) {	
		return new RequestBellyRubbingAnimationC2SPacket(buffer.readUUID(), buffer.readEnum(PregnancyPhase.class));
		}
		
	public static void encode(RequestBellyRubbingAnimationC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.target);
		buffer.writeEnum(message.phase);
	}
	 	 
    public static void handler(RequestBellyRubbingAnimationC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {					
            if (context.getDirection().getReceptionSide().isServer()) {
                var source = context.getSender();
                var level = source.level();         		
                if (level.getPlayerByUUID(message.target) instanceof ServerPlayer target) {        	
        	        ServerPlayerAnimationManager.getInstance().triggerAnimation(target, PlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(message.phase));  	    
                } 
            }			
		});
		context.setPacketHandled(true);
    }
    
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestBellyRubbingAnimationC2SPacket.class, RequestBellyRubbingAnimationC2SPacket::encode, RequestBellyRubbingAnimationC2SPacket::decode, RequestBellyRubbingAnimationC2SPacket::handler);
	}
}
