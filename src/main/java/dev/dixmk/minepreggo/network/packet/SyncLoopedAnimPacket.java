package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SyncLoopedAnimPacket(int entityId, boolean active, String type) {
	public static SyncLoopedAnimPacket decode(FriendlyByteBuf buffer) {	
		return new SyncLoopedAnimPacket(
				buffer.readVarInt(),
				buffer.readBoolean(),
				buffer.readUtf());
	}
	
	public static void encode(SyncLoopedAnimPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.entityId);
		buffer.writeBoolean(message.active);
		buffer.writeUtf(message.type);
	}
	
	public static void handler(SyncLoopedAnimPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
	  
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(SyncLoopedAnimPacket.class, SyncLoopedAnimPacket::encode, SyncLoopedAnimPacket::decode, SyncLoopedAnimPacket::handler);
	}
}


