package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0InventoryMenu;
import net.minecraft.network.chat.Component;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP0InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableCreeperGirlP0, CreeperGirlP0InventoryMenu> {

	public CreeperGirlP0InventaryScreen(CreeperGirlP0InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}

}
