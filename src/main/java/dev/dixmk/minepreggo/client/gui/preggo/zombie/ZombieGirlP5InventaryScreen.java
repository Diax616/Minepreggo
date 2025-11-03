package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP5InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP5InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP5, ZombieGirlP5InventoryMenu> {
	public ZombieGirlP5InventaryScreen(ZombieGirlP5InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
