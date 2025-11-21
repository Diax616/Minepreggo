package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlInventaryScreen extends AbstractZombieGirlInventaryScreen<TamableZombieGirl, ZombieGirlInventoryMenu> {

	public ZombieGirlInventaryScreen(ZombieGirlInventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
