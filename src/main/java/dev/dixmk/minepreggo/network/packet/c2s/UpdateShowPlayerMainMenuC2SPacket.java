package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record UpdateShowPlayerMainMenuC2SPacket(UUID source, boolean showMainMenu) {
	public static UpdateShowPlayerMainMenuC2SPacket decode(FriendlyByteBuf buffer) {	
		return new UpdateShowPlayerMainMenuC2SPacket(
				buffer.readUUID(),
				buffer.readBoolean());
	}
	
	public static void encode(UpdateShowPlayerMainMenuC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.source);
		buffer.writeBoolean(message.showMainMenu);
	}
	
	public static void handler(UpdateShowPlayerMainMenuC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {			
			if (context.getDirection().getReceptionSide().isServer()) {				
				var serverPlayer = context.getSender();
				serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.setShowMainMenu(false));				
			}			
		});
		context.setPacketHandled(true);
	}
}
