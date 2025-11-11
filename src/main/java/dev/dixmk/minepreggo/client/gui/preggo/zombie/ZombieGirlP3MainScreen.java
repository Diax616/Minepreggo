package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3MainMenu;
import net.minecraft.client.gui.GuiGraphics;

public class ZombieGirlP3MainScreen extends AbstractZombieGirlMainScreen<TamableZombieGirlP3, ZombieGirlP3MainMenu> {

	public ZombieGirlP3MainScreen(ZombieGirlP3MainMenu container, Inventory inventory, Component text) {
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

		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderZombieGirlP3MainGUI(guiGraphics, this.leftPos, this.topPos, zombieGirl));
		
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderP3LabelMainGUI(guiGraphics, this.font, zombieGirl));
	}
}

