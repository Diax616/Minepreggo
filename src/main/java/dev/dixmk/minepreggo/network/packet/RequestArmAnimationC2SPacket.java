package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestArmAnimationC2SPacket(int animationType) {
    
    public static RequestArmAnimationC2SPacket decode(FriendlyByteBuf buf) {
    	return new RequestArmAnimationC2SPacket(buf.readInt());
    }
    
	public static void encode(RequestArmAnimationC2SPacket message, FriendlyByteBuf buf) {
        buf.writeInt(message.animationType);
    }
    
    public static void handler(RequestArmAnimationC2SPacket msg, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {    
       
            	MinepreggoMod.LOGGER.debug("Received ArmAnimationPacket on server with animationType: {}", msg.animationType());           	
                var sender = context.getSender();     
            	MinepreggoModPacketHandler.INSTANCE.send(
            			PacketDistributor.PLAYER.with(() -> sender),
            			new UpdateArmAnimationS2CPacket(sender.getUUID(), msg.animationType())
            			);                            
            }
        });
		context.setPacketHandled(true);
    }
    
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestArmAnimationC2SPacket.class, RequestArmAnimationC2SPacket::encode, RequestArmAnimationC2SPacket::decode, RequestArmAnimationC2SPacket::handler);
	}
}
