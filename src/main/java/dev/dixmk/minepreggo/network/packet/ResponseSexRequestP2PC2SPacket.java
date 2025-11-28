package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.particle.ParticleHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record ResponseSexRequestP2PC2SPacket(int sourcePlayerId, int targetPlayerId, boolean accept) {

	public static ResponseSexRequestP2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ResponseSexRequestP2PC2SPacket(
				buffer.readVarInt(),
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(ResponseSexRequestP2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.sourcePlayerId);
		buffer.writeVarInt(message.targetPlayerId);
		buffer.writeBoolean(message.accept);
	}
	
	public static void handler(ResponseSexRequestP2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {	
				var sender = context.getSender();
				var level = sender.level();
				final ServerPlayer target = level.getEntity(message.sourcePlayerId) instanceof ServerPlayer s ? s : null;
				final ServerPlayer source = level.getEntity(message.targetPlayerId) instanceof ServerPlayer t ? t : null;
		
				if (source == null || target == null || !target.getUUID().equals(sender.getUUID())) return;
				
				if (message.accept) {	
					MinepreggoModPacketHandler.queueServerWork(20, () -> {			
						ParticleHelper.spawnRandomlyFromServer(source, ParticleTypes.HEART);
						ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART);
					});
					
					MinepreggoModPacketHandler.queueServerWork(40, () -> {			
						ParticleHelper.spawnRandomlyFromServer(source, ParticleTypes.HEART);
						ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART);
					});
					
					MinepreggoModPacketHandler.queueServerWork(60, () -> {			
						ParticleHelper.spawnRandomlyFromServer(source, ParticleTypes.HEART);
						ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART);
					});
					return;
				}
				ParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.SMOKE);			
			}
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(ResponseSexRequestP2PC2SPacket.class, ResponseSexRequestP2PC2SPacket::encode, ResponseSexRequestP2PC2SPacket::decode, ResponseSexRequestP2PC2SPacket::handler);
	}
	
}
