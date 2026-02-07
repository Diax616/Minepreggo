package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobInventaryScreen;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobInventaryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractCreeperGirlInventaryScreen<E extends AbstractTamableCreeperGirl,
	C extends AbstractPreggoMobInventaryMenu<E>> extends AbstractPreggoMobInventaryScreen<E, C> {

	protected AbstractCreeperGirlInventaryScreen(C container, Inventory inventory, Component text, ResourceLocation inventoryTexture) {
		super(container, inventory, text, inventoryTexture);
	}
}
