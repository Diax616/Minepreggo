package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP6InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP6InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP6, ZombieGirlP6InventoryMenu> {
	public ZombieGirlP6InventaryScreen(ZombieGirlP6InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
