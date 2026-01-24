package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;

public class TagHelper {
	
	private TagHelper() {}
	
	public static final TagKey<Item> ZOMBIE_FOOD =
	        TagKey.create(Registries.ITEM, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "zombie_food"));
	
	public static final TagKey<Item> CREEPER_FOOD =
	        TagKey.create(Registries.ITEM, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "creeper_food"));
	
	public static final TagKey<Item> ENDER_FOOD =
	        TagKey.create(Registries.ITEM, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "ender_food"));
	
	public static final TagKey<MobEffect> PREGNANCY_EFFECTS =
	        TagKey.create(Registries.MOB_EFFECT, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_effects"));
	
	public static final TagKey<MobEffect> SECONDARY_PREGNANCY_EFFECTS =
	        TagKey.create(Registries.MOB_EFFECT, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "secondary_pregnancy_effects"));
	
	public static final TagKey<MobEffect> FEMALE_EFFECTS =
	        TagKey.create(Registries.MOB_EFFECT, MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "female_effects"));
	
}
