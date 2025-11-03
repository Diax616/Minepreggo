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
public record UpdatePreggoMobPickUpItemPacket(int preggoMobId, boolean canPickUpItem) {
	
	public static UpdatePreggoMobPickUpItemPacket decode(FriendlyByteBuf buffer) {	
		return new UpdatePreggoMobPickUpItemPacket(
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(UpdatePreggoMobPickUpItemPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeBoolean(message.canPickUpItem);
	}
	
	public static void handler(UpdatePreggoMobPickUpItemPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();			
    			var world = serverPlayer.level();  			
    			if (world.getEntity(message.preggoMobId) instanceof ITamablePreggoMob mob) {
    				mob.setPickUpItems(message.canPickUpItem);
    			}
            }
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdatePreggoMobPickUpItemPacket.class, UpdatePreggoMobPickUpItemPacket::encode, UpdatePreggoMobPickUpItemPacket::decode, UpdatePreggoMobPickUpItemPacket::handler);
	}
}
