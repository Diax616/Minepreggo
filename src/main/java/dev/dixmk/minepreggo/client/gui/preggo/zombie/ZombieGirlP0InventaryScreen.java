package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0InventoryMenu;
import net.minecraft.network.chat.Component;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlP0InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP0, ZombieGirlP0InventoryMenu> {

	public ZombieGirlP0InventaryScreen(ZombieGirlP0InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
