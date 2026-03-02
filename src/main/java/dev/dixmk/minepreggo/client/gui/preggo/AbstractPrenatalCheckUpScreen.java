package dev.dixmk.minepreggo.client.gui.preggo;

import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPrenatalCheckUpMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractPrenatalCheckUpScreen 
	<S extends LivingEntity, T extends Mob, C extends AbstractPrenatalCheckUpMenu<S, T>> extends AbstractContainerScreen<C> {
	
	private static final ResourceLocation EMERALD_TEXTURE = MinepreggoHelper.fromVanillaNamespaceAndPath("textures/item/emerald.png");
	private static final ResourceLocation TEXTURE = MinepreggoHelper.fromThisNamespaceAndPath("textures/screens/prenatal_checkup_menu.png");
	protected final Level level;
	protected final Player player;	
	protected final Optional<T> target;
	protected final Optional<S> source;
	
	protected final int emeraldForRegularCheckUp;
	protected final int emeraldForUltrasoundScan;
	protected final int emeraldForPaternityTest;
	
	protected AbstractPrenatalCheckUpScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.level = container.level;
		this.player = container.player;
		this.imageWidth = 246;
		this.imageHeight = 166;		
		
		this.source = container.getSource();
		this.target = container.getTarget();
		this.emeraldForRegularCheckUp = container.getEmeraldForRegularCheckUp();
		this.emeraldForUltrasoundScan = container.getEmeraldForUltrasoundScan();
		this.emeraldForPaternityTest = container.getEmeraldForPaternityTest();
	}
	
	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.source.ifPresent(s -> InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + 207, this.topPos + 158, 65, 0f + (float) Math.atan((this.leftPos + 262 - mouseX) / 40.0), (float) Math.atan((this.topPos + 109 - mouseY) / 40.0), s));
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		
		if (mouseX > this.leftPos + 98 && mouseX < this.leftPos + 98 + 16 && mouseY > this.topPos + 18 && mouseY < this.topPos + 18 + 16)
			guiGraphics.renderTooltip(font, Component.translatable("gui.minepreggo.medical_checkup.tooltip.regular"), mouseX, mouseY);
		if (mouseX > this.leftPos + 98 && mouseX < this.leftPos + 98 + 16 && mouseY > this.topPos + 38 && mouseY < this.topPos + 38 + 16)
			guiGraphics.renderTooltip(font, Component.translatable("gui.minepreggo.medical_checkup.tooltip.ultrasound_scan"), mouseX, mouseY);
		if (mouseX > this.leftPos + 98 && mouseX < this.leftPos + 98 + 16 && mouseY > this.topPos + 58 && mouseY < this.topPos + 58 + 16)
			guiGraphics.renderTooltip(font, Component.translatable("gui.minepreggo.medical_checkup.tooltip.paternity_test testing"), mouseX, mouseY);
	}
	
	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 246, 166, 0, 0, 246, 166, 256, 256);
		
		guiGraphics.blit(EMERALD_TEXTURE, this.leftPos + 29, this.topPos + 18, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(EMERALD_TEXTURE, this.leftPos + 29, this.topPos + 38, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(EMERALD_TEXTURE, this.leftPos + 29, this.topPos + 58, 0, 0, 16, 16, 16, 16);
			
		guiGraphics.blit(TEXTURE, this.leftPos + 98, this.topPos + 18, 16, 16, 0, 168, 16, 16, 256, 256);
		guiGraphics.blit(TEXTURE, this.leftPos + 98, this.topPos + 38, 16, 16, 0, 185, 16, 16, 256, 256);
		guiGraphics.blit(TEXTURE, this.leftPos + 98, this.topPos + 58, 16, 16, 0, 202, 16, 16, 256, 256);
				
		RenderSystem.disableBlend();
	}

	@Override
	public void tick() {
		super.tick();	
		this.target.ifPresent(e -> {
			if (e.isAggressive()) {
				this.minecraft.player.closeContainer();
			}
		});
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
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.literal(Integer.toString(emeraldForRegularCheckUp)), 35, 27, -1, false);
		guiGraphics.drawString(this.font, Component.literal(Integer.toString(emeraldForUltrasoundScan)), 35, 47, -1, false);
		guiGraphics.drawString(this.font, Component.literal(Integer.toString(emeraldForPaternityTest)), 35, 67, -1, false);		
		guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.medical_checkup.label.prenatal_checkups"), 76, 3, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.medical_checkup.label.inventory"), 14, 73, -12829636, false);
	}	
	
	
}
