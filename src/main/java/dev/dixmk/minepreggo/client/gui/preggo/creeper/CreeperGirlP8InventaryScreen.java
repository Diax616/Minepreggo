package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP8;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP8InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP8InventaryScreen extends AbstractCreeperGirlInventaryScreen<TamableHumanoidCreeperGirlP8, CreeperGirlP8InventoryMenu> {
	public CreeperGirlP8InventaryScreen(CreeperGirlP8InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
