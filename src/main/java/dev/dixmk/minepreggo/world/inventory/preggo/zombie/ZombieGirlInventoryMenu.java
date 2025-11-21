package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlInventoryMenu extends AbstractZombieGirlInventoryMenu<TamableZombieGirl> {
	public ZombieGirlInventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_INVENTORY_MENU.get(), id, inv, extraData, TamableZombieGirl.class);
	
	}
}
