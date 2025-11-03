package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP2;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP2InventoryMenu extends AbstractZombieGirlInventoryMenu<TamableZombieGirlP2> {
	public ZombieGirlP2InventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P2_INVENTORY_MENU.get(), id, inv, extraData, TamableZombieGirlP2.class);
	
	}
}
