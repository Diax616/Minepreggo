package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobPrenatalCheckUpMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record RequestPreggoMobMedicalCheckUpPacket(int preggoMobId, int scientificIllagerId) {

	public static RequestPreggoMobMedicalCheckUpPacket decode(FriendlyByteBuf buffer) {	
		return new RequestPreggoMobMedicalCheckUpPacket(
				buffer.readVarInt(),
				buffer.readVarInt());
	}
	
	public static void encode(RequestPreggoMobMedicalCheckUpPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeVarInt(message.scientificIllagerId);
	}

	
	public static void handler(RequestPreggoMobMedicalCheckUpPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();	
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();					
    			final PreggoMob preggoMob = serverPlayer.level().getEntity(message.preggoMobId) instanceof PreggoMob preg ? preg : null;
    			final ScientificIllager scientificIllager = serverPlayer.level().getEntity(message.scientificIllagerId) instanceof ScientificIllager sci ? sci : null;
    			if (preggoMob != null && scientificIllager != null && preggoMob instanceof ITamablePregnantPreggoMob) {  					
        			PreggoMobPrenatalCheckUpMenu.showPrenatalCheckUpMenu(serverPlayer, preggoMob, scientificIllager);
    			}
            }	
		});
		context.setPacketHandled(true);
	}
}
