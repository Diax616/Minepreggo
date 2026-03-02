package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;

public class MinepreggoHelper {

	private MinepreggoHelper() {}

	
	/*
	 * Helper method to create a ResourceLocation from separate namespace and path components.
	 *
	 * This exists because ResourceLocation.fromNamespaceAndPath(String, String) was
	 * introduced in Minecraft 1.21+ and is not available in 1.20.1. This helper provides
	 * the same functionality by using the constructor available in 1.20.1 for compatibility.
	 */
	@SuppressWarnings("removal")
	public static ResourceLocation fromNamespaceAndPath(String namespace, String path) {
		return new ResourceLocation(namespace, path);
	}
	
	
	/*
	 * Helper method to create a ResourceLocation with the default "minecraft" namespace.
	 *
	 * This provides the same behavior as newer helper methods by explicitly using the
	 * constructor with the "minecraft" namespace for compatibility with older versions.
	 */
	@SuppressWarnings("removal")
	public static ResourceLocation fromVanillaNamespaceAndPath(String path) {
	    return new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, path);
	}

	public static ResourceLocation fromThisNamespaceAndPath(String path) {
		return fromNamespaceAndPath(MinepreggoMod.MODID, path);
	}
	
	public static boolean isFromThisMod(MobEffect effect) {
		ResourceLocation id = ForgeRegistries.MOB_EFFECTS.getKey(effect);
		return id != null && id.getNamespace().equals(MinepreggoMod.MODID);
	}
}
