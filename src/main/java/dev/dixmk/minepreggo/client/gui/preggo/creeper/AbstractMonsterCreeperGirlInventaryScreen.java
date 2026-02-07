package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractMonsterCreeperGirlInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlInventaryScreen 
	<E extends AbstractTamableCreeperGirl, M extends AbstractMonsterCreeperGirlInventoryMenu<E>>extends AbstractCreeperGirlInventaryScreen<E, M> {

	private static final ResourceLocation MONSTER_CREEPER_GIRL_INVENTARY_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/monster_creeper_girl_inventory.png");	
	
	protected AbstractMonsterCreeperGirlInventaryScreen(M container, Inventory inventory, Component text) {
		super(container, inventory, text, MONSTER_CREEPER_GIRL_INVENTARY_TEXTURE);
	}
}
