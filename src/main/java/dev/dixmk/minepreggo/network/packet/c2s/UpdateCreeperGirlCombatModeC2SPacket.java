package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record UpdateCreeperGirlCombatModeC2SPacket(int x, int y, int z, CombatMode combatMode, int creeperGirlId) {
		
	public static UpdateCreeperGirlCombatModeC2SPacket decode(FriendlyByteBuf buffer) {	
		return new UpdateCreeperGirlCombatModeC2SPacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readEnum(CombatMode.class),
				buffer.readVarInt());
	}
	
	public static void encode(UpdateCreeperGirlCombatModeC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeEnum(message.combatMode);
		buffer.writeVarInt(message.creeperGirlId);
	}

	@SuppressWarnings("deprecation")
	public static void handler(UpdateCreeperGirlCombatModeC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {	
			if (context.getDirection().getReceptionSide().isServer()) {
				var serverPlayer = context.getSender();					
				var world = serverPlayer.level();
				
				// security measure to prevent arbitrary chunk generation
				if (!world.hasChunkAt(new BlockPos(message.x, message.y, message.z))) return;
					
				if (!world.isClientSide() && world.getEntity(message.creeperGirlId) instanceof AbstractTamableCreeperGirl creeperGirl) {
					final var oldCombatMode = creeperGirl.getCombatMode();		
					creeperGirl.setCombatMode(message.combatMode);
					MinepreggoMod.LOGGER.debug("CHANGING CREEPER GIRL COMBAT MODE: id={}, class={}, oldCombatMode={}, newCombatMode={}",
							creeperGirl.getId(), creeperGirl.getClass().getSimpleName(), oldCombatMode, creeperGirl.getCombatMode());
				}	
			}
		});
		context.setPacketHandled(true);
	}
}
