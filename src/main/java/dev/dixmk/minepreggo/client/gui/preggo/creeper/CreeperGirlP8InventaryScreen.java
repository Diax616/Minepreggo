package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP8;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP8InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP8InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableCreeperGirlP8, CreeperGirlP8InventoryMenu> {
	public CreeperGirlP8InventaryScreen(CreeperGirlP8InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
