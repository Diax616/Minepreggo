package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP7;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP7InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableHumanoidCreeperGirlP7, CreeperGirlP7InventoryMenu> {
	public CreeperGirlP7InventaryScreen(CreeperGirlP7InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}