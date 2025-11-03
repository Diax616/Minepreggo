package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP7InventoryMenu extends AbstractZombieGirlInventoryMenu<TamableZombieGirlP7> {
	public ZombieGirlP7InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P7_INVENTORY_MENU.get(), id, inv, extraData, TamableZombieGirlP7.class);
	
	}
}
