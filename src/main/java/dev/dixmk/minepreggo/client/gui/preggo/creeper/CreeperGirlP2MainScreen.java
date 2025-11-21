package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.network.chat.Component;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2MainMenu;
import net.minecraft.client.gui.GuiGraphics;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP2MainScreen extends AbstractCreeperGirlMainScreen<TamableCreeperGirlP2, CreeperGirlP2MainMenu> {

	public CreeperGirlP2MainScreen(CreeperGirlP2MainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 185;
		this.imageHeight = 120;
	}
	
	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(DEFAULT_P2_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP2MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
		
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP2LabelMainGUI(guiGraphics, this.font, creeperGirl));	
	}
}
