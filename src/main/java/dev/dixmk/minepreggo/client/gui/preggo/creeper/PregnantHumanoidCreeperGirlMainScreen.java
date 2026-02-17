package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.PregnantHumanoidCreeperGirlMainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PregnantHumanoidCreeperGirlMainScreen {

	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP0MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP0MainMenu> {

		public CreeperGirlP0MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP0MainMenu container, Inventory inventory, Component text) {
			super(container, inventory, text);
			this.imageWidth = 187;
			this.imageHeight = 103;
		}

		@Override
		protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
			RenderSystem.setShaderColor(1, 1, 1, 1);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			
			guiGraphics.blit(DEFAULT_P0_MAIN_GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
			
			this.preggoMob.ifPresent(creeperGirl -> renderScreenP0(guiGraphics, this.leftPos, this.topPos, creeperGirl));
		
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP0(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP1MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP1MainMenu> {

		public CreeperGirlP1MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP1MainMenu container, Inventory inventory, Component text) {
			super(container, inventory, text);
			this.imageWidth = 183;
			this.imageHeight = 112;
		}
		
		@Override
		protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
			RenderSystem.setShaderColor(1, 1, 1, 1);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();

			guiGraphics.blit(DEFAULT_P1_MAIN_GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
		
			this.preggoMob.ifPresent(creeperGirl -> renderScreenP1(guiGraphics, this.leftPos, this.topPos, creeperGirl, AbstractCreeperGirlMainScreen::getCravingIcon));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP1(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP2MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP2MainMenu> {

		public CreeperGirlP2MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP2MainMenu container, Inventory inventory, Component text) {
			super(container, inventory, text);
			this.imageWidth = 185;
			this.imageHeight = 120;
		}
		
		@Override
		protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
			RenderSystem.setShaderColor(1, 1, 1, 1);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			guiGraphics.blit(DEFAULT_P2_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

			this.preggoMob.ifPresent(creeperGirl -> renderScreenP2(guiGraphics, this.leftPos, this.topPos, creeperGirl, AbstractCreeperGirlMainScreen::getCravingIcon));
			
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP2(guiGraphics, this.font, creeperGirl));	
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP3MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP3MainMenu> {

		public CreeperGirlP3MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP3MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> renderScreenP3(guiGraphics, this.leftPos, this.topPos, creeperGirl, AbstractCreeperGirlMainScreen::getCravingIcon));
				
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP3(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP4MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP4MainMenu> {

		public CreeperGirlP4MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP4MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, creeperGirl, AbstractCreeperGirlMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP4(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP5MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP5MainMenu> {

		public CreeperGirlP5MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP5MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, creeperGirl, AbstractCreeperGirlMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP4(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP6MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP6MainMenu> {

		public CreeperGirlP6MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP6MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, creeperGirl, AbstractCreeperGirlMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP4(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP7MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP7MainMenu> {

		public CreeperGirlP7MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP7MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, creeperGirl, AbstractCreeperGirlMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP4(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP8MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8, PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP8MainMenu> {

		public CreeperGirlP8MainScreen(PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP8MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, creeperGirl, AbstractCreeperGirlMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> renderLabelsP4(guiGraphics, this.font, creeperGirl));
		}
	}
}
