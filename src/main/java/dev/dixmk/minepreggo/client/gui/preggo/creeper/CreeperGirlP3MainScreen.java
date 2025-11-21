package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.network.chat.Component;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3MainMenu;
import net.minecraft.client.gui.GuiGraphics;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP3MainScreen extends AbstractCreeperGirlMainScreen<TamableCreeperGirlP3, CreeperGirlP3MainMenu> {

	public CreeperGirlP3MainScreen(CreeperGirlP3MainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 178;
		this.imageHeight = 130;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(DEFAULT_P3_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP3MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
			
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP3LabelMainGUI(guiGraphics, this.font, creeperGirl));
	}
}