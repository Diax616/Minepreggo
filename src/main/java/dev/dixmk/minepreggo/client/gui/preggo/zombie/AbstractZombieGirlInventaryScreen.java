package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobInventaryScreen;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractZombieGirlInventaryScreen
	<E extends AbstractTamableZombieGirl<?>, C extends AbstractZombieGirlInventoryMenu<E>> extends AbstractPreggoMobInventaryScreen<E, C> {

	protected static final ResourceLocation ZOMBIE_GIRL_INVENTARY_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/zombie_girl_inventory.png");

	protected AbstractZombieGirlInventaryScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text, ZOMBIE_GIRL_INVENTARY_TEXTURE);
	}

}
