package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP7InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableCreeperGirlP7, CreeperGirlP7InventoryMenu> {
	public CreeperGirlP7InventaryScreen(CreeperGirlP7InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}