package dev.dixmk.minepreggo.network.packet.c2s;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlMenuHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.EnderWomanMenuHelper;
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
    					if (creeperGirl.getTypeOfCreature() == Creature.HUMANOID) {
        					CreeperGirlMenuHelper.showInventoryMenuForHumanoid(serverPlayer, creeperGirl);
    					}
    					else {
        					CreeperGirlMenuHelper.showInventoryMenuForMonster(serverPlayer, creeperGirl);
    					}			
    				}	
    				else if (tamableAnimal instanceof AbstractTamableZombieGirl zombieGirl) {
    					ZombieGirlMenuHelper.showInventoryMenu(serverPlayer, zombieGirl);
    				}
    				else if (tamableAnimal instanceof AbstractTamableMonsterEnderWoman enderWoman) {
    					EnderWomanMenuHelper.showInventoryMenu(serverPlayer, enderWoman);
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
