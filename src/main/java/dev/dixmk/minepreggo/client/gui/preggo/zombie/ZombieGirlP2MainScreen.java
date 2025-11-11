package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2MainMenu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

public class ZombieGirlP2MainScreen extends AbstractZombieGirlMainScreen<TamableZombieGirlP2, ZombieGirlP2MainMenu> {

	public ZombieGirlP2MainScreen(ZombieGirlP2MainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 185;
		this.imageHeight = 120;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(DEFAULT_P2_MAIN_GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderZombieGirlP2MainGUI(guiGraphics, this.leftPos, this.topPos, zombieGirl));

		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) { 
		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderP2LabelMainGUI(guiGraphics, this.font, zombieGirl));	
	}
}
