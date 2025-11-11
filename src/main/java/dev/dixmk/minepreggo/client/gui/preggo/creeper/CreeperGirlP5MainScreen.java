package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5MainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP5MainScreen extends AbstractCreeperGirlMainScreen<TamableCreeperGirlP5, CreeperGirlP5MainMenu> {

	public CreeperGirlP5MainScreen(CreeperGirlP5MainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 178;
		this.imageHeight = 139;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(DEFAULT_P4_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
				
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP4LabelMainGUI(guiGraphics, this.font, creeperGirl));
	}
}
