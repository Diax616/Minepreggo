package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import dev.dixmk.minepreggo.world.pregnancy.SexHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

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
				final ServerPlayer target = level.getEntity(message.playerId) instanceof ServerPlayer t ? t : null;
								
				if (source != null && target != null) {
					if (source instanceof AbstractTamableZombieGirl zombieGirl && SexHelper.canFuck(target, zombieGirl)) {
						RequestSexM2PMenu.create(target, zombieGirl);
					}
					else if (source instanceof AbstractTamableCreeperGirl creeperGirl && SexHelper.canFuck(target, creeperGirl)) {
						RequestSexM2PMenu.create(target, creeperGirl);
					}			
				}
			}
		});
		context.setPacketHandled(true);
	}
}
