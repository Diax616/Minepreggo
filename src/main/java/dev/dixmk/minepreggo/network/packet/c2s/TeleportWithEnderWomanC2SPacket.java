package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkEvent;

public record TeleportWithEnderWomanC2SPacket(BlockPos targetPos) {
	
	public static TeleportWithEnderWomanC2SPacket decode(FriendlyByteBuf buffer) {	
		return new TeleportWithEnderWomanC2SPacket(
				buffer.readBlockPos());
	}
	
	public static void encode(TeleportWithEnderWomanC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeBlockPos(message.targetPos);
	}
	
	public static void handler(TeleportWithEnderWomanC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();		
        		AbstractTamableEnderWoman enderWoman = null;
        		
        		if (serverPlayer.getVehicle() instanceof AbstractTamableEnderWoman tamable) {
        			enderWoman = tamable;
        		}
        		else {
        			double searchRadius = 4.0;
        			TargetingConditions conditions = TargetingConditions.forNonCombat()
        				    .range(searchRadius)
        				    .selector(entity -> entity instanceof AbstractTamableEnderWoman tamable && tamable.isOwnedBy(serverPlayer));
        			double x = serverPlayer.getX();
        			double y = serverPlayer.getY();
        			double z = serverPlayer.getZ();
        			AABB searchBox = new AABB(x - searchRadius, y - searchRadius, z - searchRadius, x + searchRadius, y + searchRadius, z + searchRadius);
        			enderWoman = serverPlayer.level().getNearestEntity(AbstractTamableEnderWoman.class, conditions, serverPlayer, x, y, z, searchBox);
        		}
    			
        		if (enderWoman != null) {
        			enderWoman.teleportWithOwner(message.targetPos); 
        		}
            }		
		});
		context.setPacketHandled(true);
	}
}
