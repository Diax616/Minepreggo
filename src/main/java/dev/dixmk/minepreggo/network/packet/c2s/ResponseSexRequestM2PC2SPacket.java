package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.SexHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public record ResponseSexRequestM2PC2SPacket(int preggoMobId, int playerId, boolean accept) {

	public static ResponseSexRequestM2PC2SPacket decode(FriendlyByteBuf buffer) {	
		return new ResponseSexRequestM2PC2SPacket(
				buffer.readVarInt(),
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(ResponseSexRequestM2PC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeVarInt(message.playerId);
		buffer.writeBoolean(message.accept);
	}
	
	public static void handler(ResponseSexRequestM2PC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getDirection().getReceptionSide().isServer()) {	
				var sender = context.getSender();
				var level = sender.level();

				final PreggoMob source = level.getEntity(message.preggoMobId) instanceof PreggoMob s ? s : null;
				final ServerPlayer target = level.getEntity(message.playerId) instanceof ServerPlayer t ? t : null;
					
				if (source != null && target != null) {
					// TODO: Sex event requested by a PreggoMob can only happen if the PreggoMob is pregnant, but She can't request if she's not pregnant. It should allow even if not pregnant.  
					if (message.accept) {																
						if (SexHelper.canActivateSexEvent(level, target, source)) {			
							if (source instanceof AbstractTamablePregnantZombieGirl zombieGirl) {
								SexHelper.acceptSexRequest(target, zombieGirl);
							}
							else if (source instanceof AbstractTamablePregnantCreeperGirl creeperGirl) {
								SexHelper.acceptSexRequest(target, creeperGirl);
							}
						}	
					}
					else {
						SexHelper.rejectSexRequest(target, source);
					}
				}
			}
		});
		context.setPacketHandled(true);
	}
}
