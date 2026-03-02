package dev.dixmk.minepreggo.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;

public class TagHelper {
	
	private TagHelper() {}
	
	public static final TagKey<Item> ZOMBIE_FOOD =
	        TagKey.create(Registries.ITEM, MinepreggoHelper.fromThisNamespaceAndPath("zombie_food"));
	
	public static final TagKey<Item> CREEPER_FOOD =
	        TagKey.create(Registries.ITEM, MinepreggoHelper.fromThisNamespaceAndPath("creeper_food"));
	
	public static final TagKey<Item> ENDER_FOOD =
	        TagKey.create(Registries.ITEM, MinepreggoHelper.fromThisNamespaceAndPath("ender_food"));
	
	public static final TagKey<MobEffect> PREGNANCY_EFFECTS =
	        TagKey.create(Registries.MOB_EFFECT, MinepreggoHelper.fromThisNamespaceAndPath("pregnancy_effects"));
	
	public static final TagKey<MobEffect> SECONDARY_PREGNANCY_EFFECTS =
	        TagKey.create(Registries.MOB_EFFECT, MinepreggoHelper.fromThisNamespaceAndPath("secondary_pregnancy_effects"));
	
	public static final TagKey<MobEffect> FEMALE_EFFECTS =
	        TagKey.create(Registries.MOB_EFFECT, MinepreggoHelper.fromThisNamespaceAndPath("female_effects"));
}
