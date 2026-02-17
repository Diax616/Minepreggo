package dev.dixmk.minepreggo.client.gui.preggo;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractEnderDragonMainScreen {

	private static final ImmutableMap<Craving, ResourceLocation> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chorus_fruit_with_salt.png"), 
			Craving.SWEET, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chorus_fruit_with_chocolate.png"), 
			Craving.SOUR, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_chorus_fruit.png"),
			Craving.SPICY, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chorus_fruit_with_hot_sauce.png"));
	
	
	@CheckForNull
	public static ResourceLocation getCravingIcon(Craving craving) {
		if (craving == null) {
			return null;
		}		
		return CRAVING_ICONS.get(craving);
	}
}
