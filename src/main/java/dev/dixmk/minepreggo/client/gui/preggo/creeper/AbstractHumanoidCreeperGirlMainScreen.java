package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlMainMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidCreeperGirlMainScreen
	<E extends AbstractTamableCreeperGirl, M extends AbstractCreeperGirlMainMenu<E>>extends AbstractCreeperGirlMainScreen<E, M> {

	protected AbstractHumanoidCreeperGirlMainScreen(M container, Inventory inventory, Component text) throws IllegalArgumentException {
		super(container, inventory, text, 1, 89);
		container.getPreggoMob().ifPresent(p -> {
			if (p.getTypeOfCreature() != Creature.HUMANOID) {
				throw new IllegalArgumentException("The provided container's mob is not a humanoid creeper girl");
			}	
		});
	}
	
	@Override
	public void init() {
		super.init();					
		this.addBreakBlocksCheckBox();
		this.addPickUpLootCheckBox();
	}	
}
