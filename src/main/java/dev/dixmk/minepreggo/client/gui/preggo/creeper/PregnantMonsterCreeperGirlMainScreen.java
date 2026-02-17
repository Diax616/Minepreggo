package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.PregnantMonsterCreeperGirlMainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PregnantMonsterCreeperGirlMainScreen {

	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP0MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP0MainMenu> {

		public MonsterCreeperGirlP0MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP0MainMenu container, Inventory inventory, Component text) {
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
	public static class MonsterCreeperGirlP1MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP1MainMenu> {

		public MonsterCreeperGirlP1MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP1MainMenu container, Inventory inventory, Component text) {
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
	public static class MonsterCreeperGirlP2MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP2MainMenu> {

		public MonsterCreeperGirlP2MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP2MainMenu container, Inventory inventory, Component text) {
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
	public static class MonsterCreeperGirlP3MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP3MainMenu> {

		public MonsterCreeperGirlP3MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP3MainMenu container, Inventory inventory, Component text) {
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
	public static class MonsterCreeperGirlP4MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP4MainMenu> {

		public MonsterCreeperGirlP4MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP4MainMenu container, Inventory inventory, Component text) {
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
	public static class MonsterCreeperGirlP5MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP5MainMenu> {

		public MonsterCreeperGirlP5MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP5MainMenu container, Inventory inventory, Component text) {
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
	public static class MonsterCreeperGirlP6MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP6MainMenu> {

		public MonsterCreeperGirlP6MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP6MainMenu container, Inventory inventory, Component text) {
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
	public static class MonsterCreeperGirlP7MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP7MainMenu> {

		public MonsterCreeperGirlP7MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP7MainMenu container, Inventory inventory, Component text) {
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
	public static class MonsterCreeperGirlP8MainScreen extends AbstractMonsterCreeperGirlMainScreen<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8, PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP8MainMenu> {

		public MonsterCreeperGirlP8MainScreen(PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP8MainMenu container, Inventory inventory, Component text) {
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
