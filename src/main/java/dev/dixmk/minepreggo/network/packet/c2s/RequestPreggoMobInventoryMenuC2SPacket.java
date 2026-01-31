package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlMenuHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlMenuHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraftforge.network.NetworkEvent;

public record RequestPreggoMobInventoryMenuC2SPacket(int preggoMobId) {

	public static RequestPreggoMobInventoryMenuC2SPacket decode(FriendlyByteBuf buffer) {	
		return new RequestPreggoMobInventoryMenuC2SPacket(
				buffer.readVarInt());
	}
	
	public static void encode(RequestPreggoMobInventoryMenuC2SPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
	}
	
	public static void handler(RequestPreggoMobInventoryMenuC2SPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            if (context.getDirection().getReceptionSide().isServer()) {
    			var serverPlayer = context.getSender();			
    			var level = serverPlayer.level();  			
		
    			if (level.getEntity(message.preggoMobId) instanceof TamableAnimal tamableAnimal) {			
    				if (tamableAnimal instanceof AbstractTamableCreeperGirl creeperGirl) {
    					CreeperGirlMenuHelper.showInventoryMenu(serverPlayer, creeperGirl);
    					MinepreggoMod.LOGGER.debug("INVENTARY CREEPER GIRL: id={}, class={}", creeperGirl.getId(), creeperGirl.getClass().getSimpleName());
    				}	
    				else if (tamableAnimal instanceof AbstractTamableZombieGirl zombieGirl) {
    					ZombieGirlMenuHelper.showInventoryMenu(serverPlayer, zombieGirl);
    					MinepreggoMod.LOGGER.debug("INVENTARY ZOMBIE GIRL: id={}, class={}", zombieGirl.getId(), zombieGirl.getClass().getSimpleName());
    				}
    				else {
    					MinepreggoMod.LOGGER.error("PREGGO MOB CLASS COULD NOT BE RESOLVED");
    				}
    			}
            }	
		});
		
		context.setPacketHandled(true);
	}
}
