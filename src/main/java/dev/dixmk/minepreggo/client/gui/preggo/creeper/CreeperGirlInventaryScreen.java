package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlInventoryMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlInventaryScreen extends AbstractHumanoidCreeperGirlInventaryScreen<TamableHumanoidCreeperGirl, CreeperGirlInventoryMenu> {
	public CreeperGirlInventaryScreen(CreeperGirlInventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
