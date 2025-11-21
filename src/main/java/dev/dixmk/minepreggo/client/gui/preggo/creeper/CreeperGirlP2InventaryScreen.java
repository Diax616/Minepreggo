package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP2InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableCreeperGirlP2, CreeperGirlP2InventoryMenu> {
	public CreeperGirlP2InventaryScreen(CreeperGirlP2InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
