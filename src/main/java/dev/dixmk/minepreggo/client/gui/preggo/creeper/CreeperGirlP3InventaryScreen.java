package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP3InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableCreeperGirlP3, CreeperGirlP3InventoryMenu> {
	public CreeperGirlP3InventaryScreen(CreeperGirlP3InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
