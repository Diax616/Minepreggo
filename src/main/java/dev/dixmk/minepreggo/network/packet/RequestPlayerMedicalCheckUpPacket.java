package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerMedicalCheckUpMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestPlayerMedicalCheckUpPacket(int targetId) {

	public static RequestPlayerMedicalCheckUpPacket decode(FriendlyByteBuf buffer) {	
		return new RequestPlayerMedicalCheckUpPacket(
				buffer.readVarInt());
	}
	
	public static void encode(RequestPlayerMedicalCheckUpPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.targetId);
	}
	
	public static void handler(RequestPlayerMedicalCheckUpPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();		
    			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).ifPresent(cap -> {
        			final var data = cap.createDataGUI();   	 
        			
        			var target = serverPlayer.level().getEntity(message.targetId);
        			
        			if (target instanceof ScientificIllager scientificIllager) {  				
        				PlayerMedicalCheckUpMenu.Illager.showMedicalCheckUpMenu(serverPlayer, scientificIllager, data);
        			}
        			else if (target instanceof Villager villager && villager.getVillagerData().getProfession() == MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get()) {
        				PlayerMedicalCheckUpMenu.DoctorVillager.showMedicalCheckUpMenu(serverPlayer, villager, data);
        			}   			
    			});       	
            }		
		});
		
		context.setPacketHandled(true);
	}
	
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestPlayerMedicalCheckUpPacket.class, RequestPlayerMedicalCheckUpPacket::encode, RequestPlayerMedicalCheckUpPacket::decode, RequestPlayerMedicalCheckUpPacket::handler);
	}
}
