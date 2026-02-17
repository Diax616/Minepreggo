package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record SyncEnderPowerDataS2CPacket(UUID source, int enderPowerLevel) {
	
	public static SyncEnderPowerDataS2CPacket decode(FriendlyByteBuf buffer) {			
		return new SyncEnderPowerDataS2CPacket(
				buffer.readUUID(),
				buffer.readInt()
		);
	}
	
	public static void encode(SyncEnderPowerDataS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.source);
		buffer.writeInt(message.enderPowerLevel);
	}
	
	public static void handler(SyncEnderPowerDataS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isClient()) {    	
            	var target = Minecraft.getInstance().player.level().getPlayerByUUID(message.source); 	            	
            	if (target != null) {
            		target.getCapability(MinepreggoCapabilities.ENDER_POWER_DATA).ifPresent(enderPowerData -> 
						enderPowerData.setEnderPowerLevel(message.enderPowerLevel)
					);		
				}
            }
		});
		context.setPacketHandled(true);
	}
}
