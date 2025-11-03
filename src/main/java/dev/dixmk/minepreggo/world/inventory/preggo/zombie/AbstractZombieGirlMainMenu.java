package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobMainMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public abstract class AbstractZombieGirlMainMenu<E extends AbstractTamableZombieGirl<?>> extends AbstractPreggoMobMainMenu<E> {	
	
	protected AbstractZombieGirlMainMenu(MenuType<?> menuType, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> zombieGirlClass) {
		super(menuType, id, inv, extraData, zombieGirlClass);	
	}
}
