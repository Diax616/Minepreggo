package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP6;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6MainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlP6MainScreen extends AbstractCreeperGirlMainScreen<TamableHumanoidCreeperGirlP6, CreeperGirlP6MainMenu> {

	public CreeperGirlP6MainScreen(CreeperGirlP6MainMenu container, Inventory inventory, Component text) {
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
