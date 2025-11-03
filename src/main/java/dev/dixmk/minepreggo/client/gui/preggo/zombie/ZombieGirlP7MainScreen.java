package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7MainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP7MainScreen extends AbstractZombieGirlMainScreen<TamableZombieGirlP7, ZombieGirlP7MainMenu> {

	public ZombieGirlP7MainScreen(ZombieGirlP7MainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 178;
		this.imageHeight = 139;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(ScreenHelper.DEFAULT_P4_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		
		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderZombieGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, zombieGirl));

		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderP4LabelMainGUI(guiGraphics, this.font, zombieGirl));
	}
}
