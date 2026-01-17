package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobInventaryScreen;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractCreeperGirlInventaryScreen<E extends AbstractTamableCreeperGirl,
	C extends AbstractCreeperGirlInventoryMenu<E>> extends AbstractPreggoMobInventaryScreen<E, C> {

	protected static final ResourceLocation CREEPER_GIRL_INVENTARY_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/creeper_girl_inventory.png");	

	protected AbstractCreeperGirlInventaryScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text, CREEPER_GIRL_INVENTARY_TEXTURE);
	}
}
