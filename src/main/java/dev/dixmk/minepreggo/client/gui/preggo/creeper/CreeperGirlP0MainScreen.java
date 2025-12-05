package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0MainMenu;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP0MainScreen extends AbstractCreeperGirlMainScreen<TamableCreeperGirlP0, CreeperGirlP0MainMenu> {

	public CreeperGirlP0MainScreen(CreeperGirlP0MainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 187;
		this.imageHeight = 103;
	}


	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		
		guiGraphics.blit(DEFAULT_P0_MAIN_GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
		
		this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlMainScreen(guiGraphics, this.leftPos, this.topPos, creeperGirl));
	
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
		this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP0LabelMainGUI(guiGraphics, this.font, creeperGirl));
	}
}
