package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP5InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableCreeperGirlP5, CreeperGirlP5InventoryMenu> {
	public CreeperGirlP5InventaryScreen(CreeperGirlP5InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
