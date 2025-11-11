package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdateShowPlayerMainMenuC2SPacket.class, UpdateShowPlayerMainMenuC2SPacket::encode, UpdateShowPlayerMainMenuC2SPacket::decode, UpdateShowPlayerMainMenuC2SPacket::handler);
	}
}
