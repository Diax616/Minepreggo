package dev.dixmk.minepreggo.client.gui.preggo;

import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobInventaryMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractPreggoMobInventaryScreen
	<E extends PreggoMob & ITamablePreggoMob, C extends AbstractPreggoMobInventaryMenu<E>> extends AbstractContainerScreen<C> {

	protected final Optional<E> preggoMob;
	protected final ResourceLocation inventoryTexture;
	
	protected AbstractPreggoMobInventaryScreen(C container, Inventory inv, Component label, ResourceLocation inventoryTexture) {
		super(container, inv, label);
		this.imageWidth = 176;
		this.imageHeight = 166;
		this.preggoMob = container.getPreggoMob();
		this.inventoryTexture = inventoryTexture;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);		
		this.preggoMob.ifPresent(mob -> InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + 51, this.topPos + 78, 40, 0f + (float) Math.atan((this.leftPos + 51 - mouseX) / 40.0), (float) Math.atan((this.topPos + 29 - mouseY) / 40.0), mob));	
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(inventoryTexture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {}
	
	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}
	
	@Override
	public void tick() {
		super.tick();	
		this.preggoMob.ifPresent(e -> {
			if (e.isAggressive()) {
				this.minecraft.player.closeContainer();
			}
		});
	}
}
