package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobInventaryScreen;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public abstract class AbstractZombieGirlInventaryScreen
	<E extends AbstractTamableZombieGirl<?>, C extends AbstractZombieGirlInventoryMenu<E>> extends AbstractPreggoMobInventaryScreen<E, C> {

	protected AbstractZombieGirlInventaryScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text, ScreenHelper.ZOMBIE_GIRL_INVENTARY_TEXTURE);
	}

}
