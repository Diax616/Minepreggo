package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP4InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableCreeperGirlP4, CreeperGirlP4InventoryMenu> {
	public CreeperGirlP4InventaryScreen(CreeperGirlP4InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
