package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.TamablePregnantZombieGirlMainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PregnantZombieGirlMainScreen {

	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP0MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP0, TamablePregnantZombieGirlMainMenu.ZombieGirlP0MainMenu> {

		public ZombieGirlP0MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP0MainMenu container, Inventory inventory, Component text) {
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
		
			this.preggoMob.ifPresent(zombieGirl -> renderScreenP0(guiGraphics, this.leftPos, this.topPos, zombieGirl));
			
			RenderSystem.disableBlend();
		}
		
		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP0(guiGraphics, this.font, zombieGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP1MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP1, TamablePregnantZombieGirlMainMenu.ZombieGirlP1MainMenu> {

		public ZombieGirlP1MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP1MainMenu container, Inventory inventory, Component text) {
			super(container, inventory, text);
			this.imageWidth = 183;
			this.imageHeight = 112;
		}


		@Override
		protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
			RenderSystem.setShaderColor(1, 1, 1, 1);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
					
			guiGraphics.blit(DEFAULT_P1_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		
			this.preggoMob.ifPresent(zombieGirl -> renderScreenP1(guiGraphics, this.leftPos, this.topPos, zombieGirl, AbstractZombieGirlMainScreen::getCravingIcon));
		
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP1(guiGraphics, this.font, zombieGirl));
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP2MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP2, TamablePregnantZombieGirlMainMenu.ZombieGirlP2MainMenu> {

		public ZombieGirlP2MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP2MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(zombieGirl -> renderScreenP2(guiGraphics, this.leftPos, this.topPos, zombieGirl, AbstractZombieGirlMainScreen::getCravingIcon));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) { 
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP2(guiGraphics, this.font, zombieGirl));	
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP3MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP3, TamablePregnantZombieGirlMainMenu.ZombieGirlP3MainMenu> {

		public ZombieGirlP3MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP3MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(zombieGirl -> renderScreenP3(guiGraphics, this.leftPos, this.topPos, zombieGirl, AbstractZombieGirlMainScreen::getCravingIcon));
			
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP3(guiGraphics, this.font, zombieGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP4MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP4, TamablePregnantZombieGirlMainMenu.ZombieGirlP4MainMenu> {

		public ZombieGirlP4MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP4MainMenu container, Inventory inventory, Component text) {
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
			
			this.preggoMob.ifPresent(zombieGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, zombieGirl, AbstractZombieGirlMainScreen::getCravingIcon));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP4(guiGraphics, this.font, zombieGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP5MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP5, TamablePregnantZombieGirlMainMenu.ZombieGirlP5MainMenu> {

		public ZombieGirlP5MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP5MainMenu container, Inventory inventory, Component text) {
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
			
			this.preggoMob.ifPresent(zombieGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, zombieGirl, AbstractZombieGirlMainScreen::getCravingIcon));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP4(guiGraphics, this.font, zombieGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP6MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP6, TamablePregnantZombieGirlMainMenu.ZombieGirlP6MainMenu> {

		public ZombieGirlP6MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP6MainMenu container, Inventory inventory, Component text) {
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
			
			this.preggoMob.ifPresent(zombieGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, zombieGirl, AbstractZombieGirlMainScreen::getCravingIcon));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP4(guiGraphics, this.font, zombieGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP7MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP7, TamablePregnantZombieGirlMainMenu.ZombieGirlP7MainMenu> {

		public ZombieGirlP7MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP7MainMenu container, Inventory inventory, Component text) {
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
			
			this.preggoMob.ifPresent(zombieGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, zombieGirl, AbstractZombieGirlMainScreen::getCravingIcon));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP4(guiGraphics, this.font, zombieGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class ZombieGirlP8MainScreen extends AbstractZombieGirlMainScreen<TamablePregnantZombieGirl.TamableZombieGirlP8, TamablePregnantZombieGirlMainMenu.ZombieGirlP8MainMenu> {

		public ZombieGirlP8MainScreen(TamablePregnantZombieGirlMainMenu.ZombieGirlP8MainMenu container, Inventory inventory, Component text) {
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
			
			this.preggoMob.ifPresent(zombieGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, zombieGirl, AbstractZombieGirlMainScreen::getCravingIcon));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
			this.preggoMob.ifPresent(zombieGirl -> renderLabelsP4(guiGraphics, this.font, zombieGirl));
		}
	}
}
