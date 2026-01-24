package dev.dixmk.minepreggo.network.packet.s2c;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record RemovePostPregnancyDataS2CPacket(UUID playerId) {
	public static RemovePostPregnancyDataS2CPacket decode(FriendlyByteBuf buffer) {	
		return new RemovePostPregnancyDataS2CPacket(
				buffer.readUUID());
	}
	
	public static void encode(RemovePostPregnancyDataS2CPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);
	}
	
	public static void handler(RemovePostPregnancyDataS2CPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isClient()) {	
				var player = Minecraft.getInstance().player.level().getPlayerByUUID(message.playerId);
				if (player != null) {	
					player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
					cap.getFemaleData().ifPresent(FemalePlayerImpl::tryRemovePostPregnancyPhase));				
				}
			}
		});
		context.setPacketHandled(true);
	}
}
