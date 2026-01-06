package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.common.animation.CommonPlayerAnimationRegistry;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

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
        	        ServerPlayerAnimationManager.getInstance().triggerAnimation(target, CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(message.phase));  	    
                } 
            }			
		});
		context.setPacketHandled(true);
    }
}
