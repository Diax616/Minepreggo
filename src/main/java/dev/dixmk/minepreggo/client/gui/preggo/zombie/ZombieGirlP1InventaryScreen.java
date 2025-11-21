package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1InventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlP1InventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirlP1, ZombieGirlP1InventoryMenu> {
	public ZombieGirlP1InventaryScreen(ZombieGirlP1InventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}