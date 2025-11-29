package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerPrenatalCheckUpMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestPlayerMedicalCheckUpC2SPacket(int targetId) {

	public static RequestPlayerMedicalCheckUpC2SPacket decode(FriendlyByteBuf buffer) {	
		return new RequestPlayerMedicalCheckUpC2SPacket(
				buffer.readVarInt());
	}
	
	public static void encode(RequestPlayerMedicalCheckUpC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.targetId);
	}
	
	public static void handler(RequestPlayerMedicalCheckUpC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();		
    			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
    				   cap.getFemaleData().ifPresent(femaleData -> {  	
            			var target = serverPlayer.level().getEntity(message.targetId);
            			
            			if (target instanceof ScientificIllager scientificIllager) { 
            				PlayerPrenatalCheckUpMenu.IllagerMenu.showPrenatalCheckUpMenu(serverPlayer, scientificIllager);
            			}
            			else if (target instanceof Villager villager && villager.getVillagerData().getProfession() == MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get()) {
            				PlayerPrenatalCheckUpMenu.VillagerMenu.showPrenatalCheckUpMenu(serverPlayer, villager);
            			}  
            			else {
							MinepreggoMod.LOGGER.warn("Player {} attempted to request a prenatal check-up with invalid target entity ID {}", serverPlayer.getName().getString(), message.targetId);
						}
    				})
    			);       	
            }		
		});
		
		context.setPacketHandled(true);
	}
	
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestPlayerMedicalCheckUpC2SPacket.class, RequestPlayerMedicalCheckUpC2SPacket::encode, RequestPlayerMedicalCheckUpC2SPacket::decode, RequestPlayerMedicalCheckUpC2SPacket::handler);
	}
}
