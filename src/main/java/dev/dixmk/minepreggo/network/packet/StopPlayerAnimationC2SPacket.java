package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record StopPlayerAnimationC2SPacket(UUID target) {

	public static StopPlayerAnimationC2SPacket decode(FriendlyByteBuf buffer) {	
		return new StopPlayerAnimationC2SPacket(buffer.readUUID());
		}
		
	public static void encode(StopPlayerAnimationC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.target);
	}
	
    public static void handler(StopPlayerAnimationC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {					
            if (context.getDirection().getReceptionSide().isServer()) {
                var source = context.getSender();
                var level = source.level();        
            	if (level.getPlayerByUUID(message.target) instanceof ServerPlayer target) {
        	        ServerPlayerAnimationManager.getInstance().stopAnimation(target); 	 
                }
            }			
		});
		context.setPacketHandled(true);
    }
    
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(StopPlayerAnimationC2SPacket.class, StopPlayerAnimationC2SPacket::encode, StopPlayerAnimationC2SPacket::decode, StopPlayerAnimationC2SPacket::handler);
	}
}
