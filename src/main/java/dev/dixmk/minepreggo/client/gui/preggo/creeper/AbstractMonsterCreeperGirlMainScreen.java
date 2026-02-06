package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlMainMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlMainScreen 
	<E extends AbstractTamableMonsterCreeperGirl, G extends AbstractCreeperGirlMainMenu<E>> extends AbstractCreeperGirlMainScreen<E, G> {

	protected AbstractMonsterCreeperGirlMainScreen(G container, Inventory inventory, Component text) {
		super(container, inventory, text, 1, 185);
	}	
}
