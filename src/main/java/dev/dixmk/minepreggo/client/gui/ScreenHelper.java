package dev.dixmk.minepreggo.client.gui;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScreenHelper {

	private ScreenHelper() {}
	
	public static final ResourceLocation MINECRAFT_ICONS_TEXTURE = MinepreggoHelper.withDefaultNamespace("textures/gui/icons.png");
	public static final ResourceLocation MINEPREGGO_ICONS_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/icons.png");
}
