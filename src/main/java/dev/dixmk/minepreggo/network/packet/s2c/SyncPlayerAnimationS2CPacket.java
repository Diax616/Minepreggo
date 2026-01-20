package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.UUID;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public record SyncPlayerAnimationS2CPacket(UUID playerId, @Nullable String animationName, int animationTick, boolean isPlaying) {

    public static SyncPlayerAnimationS2CPacket decode(FriendlyByteBuf buf) {
    	return new SyncPlayerAnimationS2CPacket(
    			buf.readUUID(),
    			buf.readUtf(),
    			buf.readInt(),
    			buf.readBoolean());
    }
    
	public static void encode(SyncPlayerAnimationS2CPacket message, FriendlyByteBuf buf) {
        buf.writeUUID(message.playerId);
        buf.writeUtf(message.animationName != null ? message.animationName : "");
        buf.writeInt(message.animationTick);
        buf.writeBoolean(message.isPlaying);
    }
    
    public static void handle(SyncPlayerAnimationS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
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
}
