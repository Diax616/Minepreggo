package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import net.minecraft.network.FriendlyByteBuf;

public class ZombieGirlP0InventoryMenu extends AbstractZombieGirlInventoryMenu<TamableZombieGirlP0> {
	public ZombieGirlP0InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P0_INVENTORY_MENU.get(), id, inv, extraData, TamableZombieGirlP0.class);
	
	}
}
