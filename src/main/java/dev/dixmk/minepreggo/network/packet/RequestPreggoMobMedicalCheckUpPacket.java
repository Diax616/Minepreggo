package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobPrenatalCheckUpMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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
    			if (preggoMob != null && scientificIllager != null && preggoMob instanceof IPregnancySystemHandler) {
        			
    				// Index 0, 1, 2 are regular check-up, ultrasound scan, paternity test, they're safe to access here
    				
    				var costs = scientificIllager.getPrenatalCheckUpCosts();
        			PreggoMobPrenatalCheckUpMenu.showPrenatalCheckUpMenu(serverPlayer, preggoMob, scientificIllager, costs.get(0), costs.get(1), costs.get(2));
    			}
            }	
		});
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestPreggoMobMedicalCheckUpPacket.class, RequestPreggoMobMedicalCheckUpPacket::encode, RequestPreggoMobMedicalCheckUpPacket::decode, RequestPreggoMobMedicalCheckUpPacket::handler);
	}
}
