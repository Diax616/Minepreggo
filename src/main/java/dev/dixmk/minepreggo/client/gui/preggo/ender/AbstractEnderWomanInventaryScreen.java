package dev.dixmk.minepreggo.client.gui.preggo.ender;

import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobInventaryScreen;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.AbstractEnderWomanInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractEnderWomanInventaryScreen 
		<E extends AbstractTamableEnderWoman, C extends AbstractEnderWomanInventoryMenu<E>> extends AbstractPreggoMobInventaryScreen<E, C> {
	
	protected static final ResourceLocation ENDER_WOMAN_INVENTARY_TEXTURE = MinepreggoHelper.fromThisNamespaceAndPath("textures/screens/monster_ender_woman_inventory.png");

	protected AbstractEnderWomanInventaryScreen(C container, Inventory inv, Component label) {
		super(container, inv, label, ENDER_WOMAN_INVENTARY_TEXTURE);
	}
}
