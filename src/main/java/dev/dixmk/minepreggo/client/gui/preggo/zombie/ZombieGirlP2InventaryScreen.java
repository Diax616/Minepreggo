package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP2InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP2, ZombieGirlP2InventoryMenu> {
	public ZombieGirlP2InventaryScreen(ZombieGirlP2InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
