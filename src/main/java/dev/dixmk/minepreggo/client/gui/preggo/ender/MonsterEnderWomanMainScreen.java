package dev.dixmk.minepreggo.client.gui.preggo.ender;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.MonsterEnderWomanMainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterEnderWomanMainScreen extends AbstractEnderWomanMainScreen<TamableMonsterEnderWoman, MonsterEnderWomanMainMenu> {
	
	protected final boolean pregnant;

	public MonsterEnderWomanMainScreen(MonsterEnderWomanMainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 187;
		this.imageHeight = 103;
		this.pregnant = container.pregnant.orElse(false);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		
		guiGraphics.blit(DEFAULT_P0_MAIN_GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
		
		this.preggoMob.ifPresent(enderWoman -> renderScreenNonPreg(guiGraphics, this.leftPos, this.topPos, enderWoman));
	
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
		this.preggoMob.ifPresent(enderWoman -> renderLabelsNonPreg(guiGraphics, this.font, enderWoman, this.pregnant));
	}
}
