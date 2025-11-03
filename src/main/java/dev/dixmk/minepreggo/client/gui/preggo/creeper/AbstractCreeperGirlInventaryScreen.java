package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobInventaryScreen;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractCreeperGirlInventaryScreen<E extends AbstractTamableCreeperGirl<?>,
	C extends AbstractCreeperGirlInventoryMenu<E>> extends AbstractPreggoMobInventaryScreen<E, C> {

	protected AbstractCreeperGirlInventaryScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text, ScreenHelper.CREEPER_GIRL_INVENTARY_TEXTURE);
	}
}
