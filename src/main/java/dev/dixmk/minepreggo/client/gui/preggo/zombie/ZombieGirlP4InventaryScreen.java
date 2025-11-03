package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP4InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP4InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP4, ZombieGirlP4InventoryMenu> {
	public ZombieGirlP4InventaryScreen(ZombieGirlP4InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
