package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobMainScreen;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlMainMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public abstract class AbstractZombieGirlMainScreen
	<E extends AbstractTamableZombieGirl<?>, G extends AbstractZombieGirlMainMenu<E>> extends AbstractPreggoMobMainScreen<E, G> {
	
	protected static final ImmutableMap<Craving, ResourceLocation> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_salt.png"), 
			Craving.SWEET, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_chocolate.png"), 
			Craving.SOUR, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_brain.png"),
			Craving.SPICY, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_hot_sauce.png"));
	
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
