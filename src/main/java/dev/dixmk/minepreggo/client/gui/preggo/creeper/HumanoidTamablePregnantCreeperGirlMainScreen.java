package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.HumanoidTamablePregnantCreeperGirlMainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanoidTamablePregnantCreeperGirlMainScreen {

	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP0MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP0MainMenu> {

		public CreeperGirlP0MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP0MainMenu container, Inventory inventory, Component text) {
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
			
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP0MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
		
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP0LabelMainGUI(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP1MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP1MainMenu> {

		public CreeperGirlP1MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP1MainMenu container, Inventory inventory, Component text) {
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
		
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP1MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP1LabelMainGUI(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP2MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP2MainMenu> {

		public CreeperGirlP2MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP2MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP2MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
			
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP2LabelMainGUI(guiGraphics, this.font, creeperGirl));	
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP3MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP3MainMenu> {

		public CreeperGirlP3MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP3MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP3MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
				
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP3LabelMainGUI(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP4MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP4MainMenu> {

		public CreeperGirlP4MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP4MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP4LabelMainGUI(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP5MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP5MainMenu> {

		public CreeperGirlP5MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP5MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP4LabelMainGUI(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP6MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP6MainMenu> {

		public CreeperGirlP6MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP6MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP4LabelMainGUI(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP7MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP7MainMenu> {

		public CreeperGirlP7MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP7MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP4LabelMainGUI(guiGraphics, this.font, creeperGirl));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class CreeperGirlP8MainScreen extends AbstractHumanoidCreeperGirlMainScreen<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8, HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP8MainMenu> {

		public CreeperGirlP8MainScreen(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP8MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderCreeperGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(creeperGirl -> ScreenHelper.renderP4LabelMainGUI(guiGraphics, this.font, creeperGirl));
		}
	}
}
