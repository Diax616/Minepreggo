package dev.dixmk.minepreggo.client.gui.preggo;

import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobPrenatalCheckUpMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PreggoMobPrenatalCheckUpScreen 
	extends AbstractPrenatalCheckUpScreen<PreggoMob, ScientificIllager, PreggoMobPrenatalCheckUpMenu> {

	public PreggoMobPrenatalCheckUpScreen(PreggoMobPrenatalCheckUpMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
