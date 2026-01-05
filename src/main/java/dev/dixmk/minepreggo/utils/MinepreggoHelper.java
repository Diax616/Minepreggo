package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;

public class MinepreggoHelper {

	private MinepreggoHelper() {}

	
	/**
	 * Helper method to create a ResourceLocation from separate namespace and path components.
	 * <p>
	 * This method exists because {@code ResourceLocation.fromNamespaceAndPath(String, String)}
	 * was introduced in Minecraft 1.21+ and does not exist in Minecraft 1.20.1.
	 * This helper provides the same functionality by using the constructor that is available
	 * in 1.20.1, allowing code to be more easily portable between versions.
	 * </p>
	 * @param namespace the namespace (e.g., "minecraft" or "minepreggo")
	 * @param path the path within that namespace (e.g., "stone" or "custom_item")
	 * @return a new ResourceLocation with the specified namespace and path
	 * 
	 * @see ResourceLocation#ResourceLocation(String, String)
	 */
	@SuppressWarnings("removal")
	public static ResourceLocation fromNamespaceAndPath(String namespace, String path) {
		return new ResourceLocation(namespace, path);
	}
	
	
	/**
	 * Helper method to create a ResourceLocation with the default "minecraft" namespace.
	 * <p>
	 * This method exists because {@code MinepreggoHelper.withDefaultNamespace(String)}
	 * was introduced in Minecraft 1.21+ and may not be available in all Forge 1.20.1
	 * distributions. This helper provides the same functionality by using the constructor
	 * with the explicit "minecraft" namespace.
	 * </p>
	 *
	 * @param path the path within the minecraft namespace (e.g., "stone")
	 * @return a new ResourceLocation with namespace "minecraft" and the specified path
	 * 
	 * @see ResourceLocation#ResourceLocation(String, String)
	 */
	@SuppressWarnings("removal")
	public static ResourceLocation withDefaultNamespace(String path) {
	    return new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, path);
	}

	public static boolean isFromMinepreggo(MobEffect effect) {
		ResourceLocation id = ForgeRegistries.MOB_EFFECTS.getKey(effect);
		return id != null && id.getNamespace().equals(MinepreggoMod.MODID);
	}
}
