package dev.dixmk.minepreggo.network.packet;

import java.util.UUID;
import java.util.function.Supplier;

import com.google.common.annotations.VisibleForTesting;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@VisibleForTesting
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record ForceSexAnimP2PC2SPacket(UUID playerId) {

	public static ForceSexAnimP2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ForceSexAnimP2PC2SPacket(buffer.readUUID());
	}
	
	public static void encode(ForceSexAnimP2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeUUID(message.playerId);
	}
	
	public static void handler(ForceSexAnimP2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {	
				var sender = context.getSender();
				var level = sender.level();				
	
				final ServerPlayer target = level.getPlayerByUUID(message.playerId) instanceof ServerPlayer serverPlayer ? serverPlayer : null;
				if (target == null) return;
				
				target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.setCinematic(true));
				sender.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.setCinematic(true));
	
				MinepreggoModPacketHandler.INSTANCE.send(
						PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> target),
						new SyncCinematicStateS2CPacket(target.getUUID(), true));
				
				MinepreggoModPacketHandler.INSTANCE.send(
						PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> sender),
						new SyncCinematicStateS2CPacket(sender.getUUID(), true));
			}
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(ForceSexAnimP2PC2SPacket.class, ForceSexAnimP2PC2SPacket::encode, ForceSexAnimP2PC2SPacket::decode, ForceSexAnimP2PC2SPacket::handler);
	}
}
