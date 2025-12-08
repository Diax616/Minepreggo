package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP1;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP1InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableHumanoidCreeperGirlP1, CreeperGirlP1InventoryMenu> {
	public CreeperGirlP1InventaryScreen(CreeperGirlP1InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
