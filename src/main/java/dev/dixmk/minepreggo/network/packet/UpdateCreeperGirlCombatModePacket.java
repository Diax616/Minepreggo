package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record UpdateCreeperGirlCombatModePacket(int x, int y, int z, CombatMode combatMode, int creeperGirlId) {
		
	public static UpdateCreeperGirlCombatModePacket decode(FriendlyByteBuf buffer) {	
		return new UpdateCreeperGirlCombatModePacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readEnum(CombatMode.class),
				buffer.readVarInt());
	}
	
	public static void encode(UpdateCreeperGirlCombatModePacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeEnum(message.combatMode);
		buffer.writeVarInt(message.creeperGirlId);
	}

	@SuppressWarnings("deprecation")
	public static void handler(UpdateCreeperGirlCombatModePacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var serverPlayer = context.getSender();		
			if (serverPlayer == null) return;
			
			var world = serverPlayer.level();
			
			// security measure to prevent arbitrary chunk generation
			if (!world.hasChunkAt(new BlockPos(message.x, message.y, message.z))) return;
				
			if (!world.isClientSide() && world.getEntity(message.creeperGirlId) instanceof AbstractTamableCreeperGirl<?> creeperGirl) {
				final var oldCombatMode = creeperGirl.getcombatMode();		
				creeperGirl.setcombatMode(message.combatMode);
				MinepreggoMod.LOGGER.debug("CHANGING CREEPER GIRL COMBAT MODE: id={}, class={}, oldCombatMode={}, newCombatMode={}",
						creeperGirl.getId(), creeperGirl.getClass().getSimpleName(), oldCombatMode, creeperGirl.getcombatMode());
			}
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdateCreeperGirlCombatModePacket.class, UpdateCreeperGirlCombatModePacket::encode, UpdateCreeperGirlCombatModePacket::decode, UpdateCreeperGirlCombatModePacket::handler);
	}
}
