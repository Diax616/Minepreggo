package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import com.google.common.annotations.VisibleForTesting;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@VisibleForTesting
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record ForceSexRequestM2PC2SPacket(int playerId) {

	public static ForceSexRequestM2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ForceSexRequestM2PC2SPacket(buffer.readVarInt());
	}
	
	public static void encode(ForceSexRequestM2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.playerId);
	}
	
	public static void handler(ForceSexRequestM2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {	
				var sender = context.getSender();
				var level = sender.level();
					
				var instance = new MobEffectInstance(MinepreggoModMobEffects.FETAL_MOVEMENT.get(), 1000, 0, true, false, true);
				
				sender.addEffect(instance);

				MinepreggoModPacketHandler.INSTANCE.send(
						PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(sender.blockPosition())), 
						new SyncMobEffectPacket(sender.getId(), instance));
				
				/*
				final Player target = level.getEntity(message.playerId) instanceof Player player ? player : null;			
						
				if (target == null) return;
				
				final var center = new Vec3(sender.getX(), sender.getY(), sender.getZ());
				var preggoMobs = level.getEntitiesOfClass(PreggoMob.class, new AABB(center, center).inflate(16), e -> e.isOwnedBy(target));
				
				if (target.getUUID().equals(sender.getUUID()) && !preggoMobs.isEmpty()) {
					RequestSexM2PMenu.create(sender, preggoMobs.get(0));			
				}
				*/
			}
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(ForceSexRequestM2PC2SPacket.class, ForceSexRequestM2PC2SPacket::encode, ForceSexRequestM2PC2SPacket::decode, ForceSexRequestM2PC2SPacket::handler);
	}
}
