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

@VisibleForTesting
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record ForceTaskC2SPacket(int playerId) {

	public static ForceTaskC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ForceTaskC2SPacket(buffer.readVarInt());
	}
	
	public static void encode(ForceTaskC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.playerId);
	}
	
	public static void handler(ForceTaskC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {	
				var sender = context.getSender();
				var instance = new MobEffectInstance(MinepreggoModMobEffects.FETAL_MOVEMENT.get(), 1200, 0, true, false, true);
				sender.addEffect(instance);
			}
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(ForceTaskC2SPacket.class, ForceTaskC2SPacket::encode, ForceTaskC2SPacket::decode, ForceTaskC2SPacket::handler);
	}
}
