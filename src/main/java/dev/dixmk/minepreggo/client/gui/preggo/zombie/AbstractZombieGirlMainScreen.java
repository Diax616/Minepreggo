package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobMainScreen;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlMainMenu;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractZombieGirlMainScreen
	<E extends AbstractTamableZombieGirl, G extends AbstractZombieGirlMainMenu<E>> extends AbstractPreggoMobMainScreen<E, G> {
	
	protected static final ImmutableMap<Craving, ResourceLocation> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_salt.png"), 
			Craving.SWEET, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_chocolate.png"), 
			Craving.SOUR, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_brain.png"),
			Craving.SPICY, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_hot_sauce.png"));
	
	protected AbstractZombieGirlMainScreen(G container, Inventory inventory, Component text) {
		super(container, inventory, text, 1, 121);
	}
	
	@CheckForNull
	public static ResourceLocation getCravingIcon(Craving craving) {
		if (craving == null) {
			return null;
		}		
		return CRAVING_ICONS.get(craving);
	}
}
