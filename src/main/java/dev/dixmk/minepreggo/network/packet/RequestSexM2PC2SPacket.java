package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestSexM2PC2SPacket(int preggoMobId, int playerId) {

	public static RequestSexM2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new RequestSexM2PC2SPacket(
				buffer.readVarInt(),
				buffer.readVarInt());
	}
	
	public static void encode(RequestSexM2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeVarInt(message.playerId);
	}
	
	public static void handler(RequestSexM2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {	
				var sender = context.getSender();
				var level = sender.level();
				
				final PreggoMob source = level.getEntity(message.preggoMobId) instanceof PreggoMob s ? s : null;
				final Player target = level.getEntity(message.playerId) instanceof Player t ? t : null;
				
				if (source != null && target != null && target.getUUID().equals(sender.getUUID()) && source.isOwnedBy(target)) {
					RequestSexM2PMenu.create(sender, source);			
				}
			}
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestSexM2PC2SPacket.class, RequestSexM2PC2SPacket::encode, RequestSexM2PC2SPacket::decode, RequestSexM2PC2SPacket::handler);
	}
}
