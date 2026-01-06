package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record ResetPregnancyS2CPacket(UUID playerId) {
	public static ResetPregnancyS2CPacket decode(FriendlyByteBuf buffer) {	
		return new ResetPregnancyS2CPacket(
				buffer.readUUID());
	}
	
	public static void encode(ResetPregnancyS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);
	}
	
	public static void handler(ResetPregnancyS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isClient()) {	
				var player = Minecraft.getInstance().player;
				if (player.getUUID().equals(message.playerId)) {	
					player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
					cap.getFemaleData().ifPresent(FemalePlayerImpl::resetPregnancy));				
				}
			}
		});
		context.setPacketHandled(true);
	}
}
