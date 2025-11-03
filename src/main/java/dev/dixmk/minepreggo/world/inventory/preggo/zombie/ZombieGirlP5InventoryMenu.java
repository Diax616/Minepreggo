package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP5;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP5InventoryMenu extends AbstractZombieGirlInventoryMenu<TamableZombieGirlP5> {
	public ZombieGirlP5InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P5_INVENTORY_MENU.get(), id, inv, extraData, TamableZombieGirlP5.class);
	
	}
}
