package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0MainMenu;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlP0MainScreen extends AbstractZombieGirlMainScreen<TamableZombieGirlP0, ZombieGirlP0MainMenu> {

	public ZombieGirlP0MainScreen(ZombieGirlP0MainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 187;
		this.imageHeight = 103;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		
		guiGraphics.blit(DEFAULT_P0_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	
		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderZombieGirlP0MainGUI(guiGraphics, this.leftPos, this.topPos, zombieGirl));
		
		RenderSystem.disableBlend();
	}
	
	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		this.preggoMob.ifPresent(zombieGirl -> ScreenHelper.renderP0LabelMainGUI(guiGraphics, this.font, zombieGirl));
	}
}

