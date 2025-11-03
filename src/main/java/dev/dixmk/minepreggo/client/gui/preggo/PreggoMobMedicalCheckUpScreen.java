package dev.dixmk.minepreggo.client.gui.preggo;

import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobMedicalCheckUpMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class PreggoMobMedicalCheckUpScreen 
	extends AbstractMedicalCheckUpScreen<PreggoMob, ScientificIllager, PreggoMobMedicalCheckUpMenu> {

	public PreggoMobMedicalCheckUpScreen(PreggoMobMedicalCheckUpMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
