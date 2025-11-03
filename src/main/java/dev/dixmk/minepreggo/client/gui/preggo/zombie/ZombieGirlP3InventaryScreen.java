package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP3InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP3, ZombieGirlP3InventoryMenu> {
	public ZombieGirlP3InventaryScreen(ZombieGirlP3InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
