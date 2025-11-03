package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP8;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP8InventoryMenu extends AbstractZombieGirlInventoryMenu<TamableZombieGirlP8> {
	public ZombieGirlP8InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P8_INVENTORY_MENU.get(), id, inv, extraData, TamableZombieGirlP8.class);
	
	}
}
