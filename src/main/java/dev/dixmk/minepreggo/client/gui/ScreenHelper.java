package dev.dixmk.minepreggo.client.gui;

import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScreenHelper {

	private ScreenHelper() {}
	
	public static final ResourceLocation MINECRAFT_ICONS_TEXTURE = MinepreggoHelper.fromVanillaNamespaceAndPath("textures/gui/icons.png");
	public static final ResourceLocation MINEPREGGO_ICONS_TEXTURE = MinepreggoHelper.fromThisNamespaceAndPath("textures/screens/icons.png");
}
