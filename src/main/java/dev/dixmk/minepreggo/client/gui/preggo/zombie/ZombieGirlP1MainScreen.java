package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1MainMenu;

public class ZombieGirlP1MainScreen extends AbstractZombieGirlMainScreen<TamableZombieGirlP1, ZombieGirlP1MainMenu> {

	public ZombieGirlP1MainScreen(ZombieGirlP1MainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 183;
		this.imageHeight = 112;
	}


	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
				
		guiGraphics.blit(ScreenHelper.DEFAULT_P1_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	
		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderZombieGirlP1MainGUI(guiGraphics, this.leftPos, this.topPos, zombieGirl));
	
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderP1LabelMainGUI(guiGraphics, this.font, zombieGirl));
	}
}
