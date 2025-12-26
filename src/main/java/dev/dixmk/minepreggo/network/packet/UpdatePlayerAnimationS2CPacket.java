package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record UpdatePlayerAnimationS2CPacket(UUID playerId, @Nullable String animationName, int animationTick, boolean isPlaying) {

    public static UpdatePlayerAnimationS2CPacket decode(FriendlyByteBuf buf) {
    	return new UpdatePlayerAnimationS2CPacket(
    			buf.readUUID(),
    			buf.readUtf(),
    			buf.readInt(),
    			buf.readBoolean());
    }
    
	public static void encode(UpdatePlayerAnimationS2CPacket message, FriendlyByteBuf buf) {
        buf.writeUUID(message.playerId);
        buf.writeUtf(message.animationName != null ? message.animationName : "");
        buf.writeInt(message.animationTick);
        buf.writeBoolean(message.isPlaying);
    }
    
    public static void handle(UpdatePlayerAnimationS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                var player = Minecraft.getInstance().level.getPlayerByUUID(msg.playerId);
                if (player != null) {
                    var manager = PlayerAnimationManager.getInstance().get(player);
                    if (msg.isPlaying && msg.animationName != null) {
                        manager.setAnimationState(msg.animationName, msg.animationTick);
                    } else {
                        manager.stopAnimation();
                    }
                }
            });
        });
		context.setPacketHandled(true);
    }
    
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdatePlayerAnimationS2CPacket.class, UpdatePlayerAnimationS2CPacket::encode, UpdatePlayerAnimationS2CPacket::decode, UpdatePlayerAnimationS2CPacket::handle);
	}
}
