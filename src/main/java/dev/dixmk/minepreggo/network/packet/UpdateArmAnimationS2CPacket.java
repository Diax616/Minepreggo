package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
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
public record UpdateArmAnimationS2CPacket(UUID playerUUID, int animationType) {
	
	public static UpdateArmAnimationS2CPacket decode(FriendlyByteBuf buffer) {	
		return new UpdateArmAnimationS2CPacket(buffer.readUUID(), buffer.readInt());
	}
	
	public static void encode(UpdateArmAnimationS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerUUID);
		buffer.writeInt(message.animationType);
	}
	
    public static void handler(UpdateArmAnimationS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> {	
            // Client side handling
			MinepreggoMod.LOGGER.debug("Received ArmAnimationResponsePacket on client for player {} with animationType: {}", msg.playerUUID, msg.animationType);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                ArmAnimationManager.ArmAnimationType type = ArmAnimationManager.ArmAnimationType.values()[msg.animationType];
                ArmAnimationManager.getInstance().startAnimation(msg.playerUUID, type);
            });
		});
		context.setPacketHandled(true);
    }
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdateArmAnimationS2CPacket.class, UpdateArmAnimationS2CPacket::encode, UpdateArmAnimationS2CPacket::decode, UpdateArmAnimationS2CPacket::handler);
	}
}
