package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP3InventoryMenu extends AbstractZombieGirlInventoryMenu<TamableZombieGirlP3> {
	public ZombieGirlP3InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P3_INVENTORY_MENU.get(), id, inv, extraData, TamableZombieGirlP3.class);
	
	}
}
