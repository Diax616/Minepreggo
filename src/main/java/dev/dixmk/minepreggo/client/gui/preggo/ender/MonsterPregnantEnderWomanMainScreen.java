package dev.dixmk.minepreggo.client.gui.preggo.ender;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.world.entity.preggo.ender.TamablePregnantMonsterEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.MonsterPregnantEnderWomanMainMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterPregnantEnderWomanMainScreen {

	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP0MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP0MainMenu> {

		public MonsterEnderWomanP0MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP0MainMenu container, Inventory inventory, Component text) {
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
			
			this.preggoMob.ifPresent(enderWoman -> renderScreenP0(guiGraphics, this.leftPos, this.topPos, enderWoman));
		
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {	
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP0(guiGraphics, this.font, enderWoman));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP1MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP1MainMenu> {

		public MonsterEnderWomanP1MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP1MainMenu container, Inventory inventory, Component text) {
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
		
			this.preggoMob.ifPresent(enderWoman -> renderScreenP1(guiGraphics, this.leftPos, this.topPos, enderWoman, AbstractEnderWomanMainScreen::getCravingIcon));

			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP1(guiGraphics, this.font, enderWoman));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP2MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP2MainMenu> {

		public MonsterEnderWomanP2MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP2MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(enderWoman -> renderScreenP2(guiGraphics, this.leftPos, this.topPos, enderWoman, AbstractEnderWomanMainScreen::getCravingIcon));
			
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP2(guiGraphics, this.font, enderWoman));	
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP3MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP3MainMenu> {

		public MonsterEnderWomanP3MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP3MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(enderWoman -> renderScreenP3(guiGraphics, this.leftPos, this.topPos, enderWoman, AbstractEnderWomanMainScreen::getCravingIcon));
				
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP3(guiGraphics, this.font, enderWoman));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP4MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP4MainMenu> {

		public MonsterEnderWomanP4MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP4MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(enderWoman -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, enderWoman, AbstractEnderWomanMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP4(guiGraphics, this.font, enderWoman));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP5MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP5MainMenu> {

		public MonsterEnderWomanP5MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP5MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(enderWoman -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, enderWoman, AbstractEnderWomanMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP4(guiGraphics, this.font, enderWoman));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP6MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP6MainMenu> {

		public MonsterEnderWomanP6MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP6MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(enderWoman -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, enderWoman, AbstractEnderWomanMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP4(guiGraphics, this.font, enderWoman));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP7MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP7MainMenu> {

		public MonsterEnderWomanP7MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP7MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(enderWoman -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, enderWoman, AbstractEnderWomanMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP4(guiGraphics, this.font, enderWoman));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP8MainScreen extends AbstractEnderWomanMainScreen<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8, MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP8MainMenu> {

		public MonsterEnderWomanP8MainScreen(MonsterPregnantEnderWomanMainMenu.MonsterEnderWomanP8MainMenu container, Inventory inventory, Component text) {
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

			this.preggoMob.ifPresent(enderWoman -> renderScreenP4(guiGraphics, this.leftPos, this.topPos, enderWoman, AbstractEnderWomanMainScreen::getCravingIcon));
					
			RenderSystem.disableBlend();
		}

		@Override
		protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
			this.preggoMob.ifPresent(enderWoman -> renderLabelsP4(guiGraphics, this.font, enderWoman));
		}
	}
}
