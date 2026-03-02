package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractHumanoidCreeperGirlInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidCreeperGirlInventaryScreen  
	<E extends AbstractTamableCreeperGirl, M extends AbstractHumanoidCreeperGirlInventoryMenu<E>>extends AbstractCreeperGirlInventaryScreen<E, M> {
	
	private static final ResourceLocation HUMANOID_CREEPER_GIRL_INVENTARY_TEXTURE = MinepreggoHelper.fromThisNamespaceAndPath("textures/screens/humanoid_creeper_girl_inventory.png");	
	
	protected AbstractHumanoidCreeperGirlInventaryScreen(M container, Inventory inventory, Component text) {
		super(container, inventory, text, HUMANOID_CREEPER_GIRL_INVENTARY_TEXTURE);	
	}
}
