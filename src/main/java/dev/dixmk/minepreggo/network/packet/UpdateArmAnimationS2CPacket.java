package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.animation.player.ArmAnimationManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record UpdateArmAnimationS2CPacket(UUID playerUUID, String animationName) {
	
	public static UpdateArmAnimationS2CPacket decode(FriendlyByteBuf buffer) {	
		return new UpdateArmAnimationS2CPacket(buffer.readUUID(), buffer.readUtf());
	}
	
	public static void encode(UpdateArmAnimationS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerUUID);
		buffer.writeUtf(message.animationName);
	}
	
    public static void handler(UpdateArmAnimationS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> {	
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {                    
            	ArmAnimationManager.getInstance().startAnimation(msg.playerUUID, msg.animationName);
            });
		});
		context.setPacketHandled(true);
    }
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdateArmAnimationS2CPacket.class, UpdateArmAnimationS2CPacket::encode, UpdateArmAnimationS2CPacket::decode, UpdateArmAnimationS2CPacket::handler);
	}
}
