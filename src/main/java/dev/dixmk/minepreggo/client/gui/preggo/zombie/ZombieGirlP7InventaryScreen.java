package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlP7InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP7, ZombieGirlP7InventoryMenu> {
	public ZombieGirlP7InventaryScreen(ZombieGirlP7InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
