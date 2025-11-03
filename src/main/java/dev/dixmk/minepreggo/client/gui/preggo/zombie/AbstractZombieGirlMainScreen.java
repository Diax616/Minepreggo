package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobMainScreen;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlMainMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public abstract class AbstractZombieGirlMainScreen
	<E extends AbstractTamableZombieGirl<?>, G extends AbstractZombieGirlMainMenu<E>> extends AbstractPreggoMobMainScreen<E, G> {
	
	protected AbstractZombieGirlMainScreen(G container, Inventory inventory, Component text) {
		super(container, inventory, text, 1, 121);
	}
}
