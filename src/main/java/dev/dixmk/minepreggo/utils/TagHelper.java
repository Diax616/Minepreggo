package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;

public class TagHelper {
	
	private TagHelper() {}
	
	public static final TagKey<Item> ZOMBIE_GIRL_FOOD =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "zombie_girl_food"));
	
	
	public static final TagKey<Item> CREEPER_GIRL_FOOD =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "creeper_girl_food"));
	

	public static final TagKey<MobEffect> PREGNANCY_PHASE_EFFECTS =
	        TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_phase_effects"));
	

	public static final TagKey<MobEffect> LABOR_EFFECTS =
	        TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "labor_effects"));
	
	public static final TagKey<Item> KNEE_BRACE =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "knee_brace"));
	
	public static final TagKey<Item> MIN_P0_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p0_armor"));
	
	public static final TagKey<Item> MIN_P1_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p1_armor"));
	
	public static final TagKey<Item> MIN_P2_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p2_armor"));

	public static final TagKey<Item> MIN_P3_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p3_armor"));

	public static final TagKey<Item> MIN_P4_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p4_armor"));
	
	public static final TagKey<Item> MIN_P5_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p5_armor"));
	
	public static final TagKey<Item> MIN_P6_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p6_armor"));
	
	public static final TagKey<Item> MIN_P7_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p7_armor"));

	public static final TagKey<Item> MIN_P8_ARMOR =
	        TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "min_p8_armor"));
}
