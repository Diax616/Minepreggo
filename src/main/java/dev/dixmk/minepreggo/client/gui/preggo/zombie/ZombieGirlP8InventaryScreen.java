package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP8;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP8InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlP8InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP8, ZombieGirlP8InventoryMenu> {
	public ZombieGirlP8InventaryScreen(ZombieGirlP8InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
