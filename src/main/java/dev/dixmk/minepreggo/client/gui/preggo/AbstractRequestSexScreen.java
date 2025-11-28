package dev.dixmk.minepreggo.client.gui.preggo;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractRequestSexMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractRequestSexScreen 
	<S extends LivingEntity, T extends LivingEntity, M extends AbstractRequestSexMenu<S, T>> extends AbstractContainerScreen<M> {
	
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/request_sex.png");
	
	protected final Level level;
	protected final Player player;
	protected final Optional<S> source;
	protected final Optional<T> target;
	protected final boolean isValid;

	protected AbstractRequestSexScreen(M container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.level = container.level;
		this.player = container.player;
		this.source = container.source;
		this.target = container.target;
		this.imageWidth = 176;
		this.imageHeight = 106;
		this.isValid = source.isPresent() && target.isPresent();		
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}
	
	@Override
	protected final void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		renderComponents(guiGraphics, partialTicks, gx, gy);

		RenderSystem.disableBlend();
	}

	protected abstract void renderComponents(GuiGraphics guiGraphics, float partialTicks, int gx, int gy);
	
	protected abstract Pair<OnPress, OnPress> createActions();

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.sex_request.label.message"), 67, 34, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.sex_request.label.requestor", source.isPresent() ? source.get().getName().getString() : "?"), 22, 3, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		
		var actions = createActions();

		var yeahButton = Button.builder(Component.translatable("gui.minepreggo.sex_request.button.yeah"), actions.getLeft())
				.bounds(this.leftPos + 24, this.topPos + 76, 46, 20).build();
		this.addRenderableWidget(yeahButton);
		
		var nopeButton = Button.builder(Component.translatable("gui.minepreggo.sex_request.button.nope"), actions.getRight())
				.bounds(this.leftPos + 105, this.topPos + 76, 46, 20).build();
		this.addRenderableWidget(nopeButton);
	}
}
