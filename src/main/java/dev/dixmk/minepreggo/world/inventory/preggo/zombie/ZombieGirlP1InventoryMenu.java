package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP1InventoryMenu extends AbstractZombieGirlInventoryMenu<TamableZombieGirlP1> {
	public ZombieGirlP1InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P1_INVENTORY_MENU.get(), id, inv, extraData, TamableZombieGirlP1.class);
	
	}
}

