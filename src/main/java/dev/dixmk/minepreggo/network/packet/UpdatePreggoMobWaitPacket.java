package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record UpdatePreggoMobWaitPacket(int preggoMobId, boolean isWaiting) {

	public static UpdatePreggoMobWaitPacket decode(FriendlyByteBuf buffer) {	
		return new UpdatePreggoMobWaitPacket(
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(UpdatePreggoMobWaitPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeBoolean(message.isWaiting);
	}
	
	public static void handler(UpdatePreggoMobWaitPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();			
    			var world = serverPlayer.level();
    			
    			if (world.getEntity(message.preggoMobId) instanceof ITamablePreggoMob mob) {
    				mob.setWaiting(message.isWaiting);	
    			}
            }
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdatePreggoMobWaitPacket.class, UpdatePreggoMobWaitPacket::encode, UpdatePreggoMobWaitPacket::decode, UpdatePreggoMobWaitPacket::handler);
	}
}
