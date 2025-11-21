package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP6InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableCreeperGirlP6, CreeperGirlP6InventoryMenu> {
	public CreeperGirlP6InventaryScreen(CreeperGirlP6InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
