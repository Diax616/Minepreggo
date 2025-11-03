package dev.dixmk.minepreggo.client.gui;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP2;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancyP4;
import dev.dixmk.minepreggo.world.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ScreenHelper {

	private ScreenHelper() {}
	
	public static final ResourceLocation CREEPER_GIRL_SWEET_CRAVING = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_chocolate.png");
	public static final ResourceLocation CREEPER_GIRL_SALTY_CRAVING = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_salt.png");
	public static final ResourceLocation CREEPER_GIRL_SOUR_CRAVING = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_activated_gunpowder.png");
	public static final ResourceLocation CREEPER_GIRL_SPICY_CRAVING = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_hot_sauce.png");

	public static final ResourceLocation ZOMBIE_GIRL_SWEET_CRAVING = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_chocolate.png");
	public static final ResourceLocation ZOMBIE_GIRL_SALTY_CRAVING = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_salt.png");
	public static final ResourceLocation ZOMBIE_GIRL_SOUR_CRAVING = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_brain.png");
	public static final ResourceLocation ZOMBIE_GIRL_SPICY_CRAVING = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_hot_sauce.png");
	
	public static final ResourceLocation CREEPER_GIRL_INVENTARY_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/creeper_girl_inventory.png");	
	public static final ResourceLocation ZOMBIE_GIRL_INVENTARY_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/zombie_girl_inventory.png");
	
	public static final ResourceLocation DEFAULT_P0_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p0_main_gui.png");
	public static final ResourceLocation DEFAULT_P1_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p1_main_gui.png");
	public static final ResourceLocation DEFAULT_P2_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p2_main_gui.png");
	public static final ResourceLocation DEFAULT_P3_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p3_main_gui.png");
	public static final ResourceLocation DEFAULT_P4_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p4_main_gui.png");
	
	public static final ResourceLocation DEFAULT_ICON_CREEPER_GIRL_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/creeper_girl_p0.png");
	public static final ResourceLocation DEFAULT_ICON_ZOMBIE_GIRL_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p0.png");
	public static final ResourceLocation DEFAULT_ICON_HUMAN_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/player/wide/alex.png");

	public static final ResourceLocation MINECRAFT_ICONS_TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/icons.png");

	public static final ResourceLocation PICKLE_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/salty_pickle.png");
	public static final ResourceLocation LEMON_POPSICLES_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_popsicles.png");
	public static final ResourceLocation LEMON_ICECREAM_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_cream.png");
	public static final ResourceLocation HOT_CHICKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/spicy_chicken.png");
	public static final ResourceLocation CHOCOLATE_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chocolate_bar.png");

	public static final ResourceLocation ICONS_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/icons.png");
	


	
	private static<E extends AbstractTamablePregnantZombieGirl<?,?> & IPregnancyP1> void renderZombieGirlCravingMainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E zombieGirl) {
		switch (zombieGirl.getTypeOfCraving()) {
		case SWEET: {
			guiGraphics.blit(ZOMBIE_GIRL_SWEET_CRAVING, leftPos + 113, topPos + 43, 0, 0, 24, 24, 24, 24);
			return;
		}
		case SALTY: {
			guiGraphics.blit(ZOMBIE_GIRL_SALTY_CRAVING, leftPos + 113, topPos + 43, 0, 0, 24, 24, 24, 24);
			return;
		}
		case SOUR: {
			guiGraphics.blit(ZOMBIE_GIRL_SOUR_CRAVING, leftPos + 113, topPos + 43, 0, 0, 24, 24, 24, 24);
			return;
		}
		case SPICY: {
			guiGraphics.blit(ZOMBIE_GIRL_SPICY_CRAVING, leftPos + 113, topPos + 43, 0, 0, 24, 24, 24, 24);
			return;
		}
		default:
			return;
		}	
	}
	
	
	private static<E extends AbstractTamablePregnantCreeperGirl<?,?> & IPregnancyP1> void renderCreeperGirlCravingMainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E creeperGirl) {
		switch (creeperGirl.getTypeOfCraving()) {
		case SWEET: {
			guiGraphics.blit(CREEPER_GIRL_SWEET_CRAVING, leftPos + 113, topPos + 43, 0, 0, 24, 24, 24, 24);
			return;
		}
		case SALTY: {
			guiGraphics.blit(CREEPER_GIRL_SALTY_CRAVING, leftPos + 113, topPos + 43, 0, 0, 24, 24, 24, 24);
			return;
		}
		case SOUR: {
			guiGraphics.blit(CREEPER_GIRL_SOUR_CRAVING, leftPos + 113, topPos + 43, 0, 0, 24, 24, 24, 24);
			return;
		}
		case SPICY: {
			guiGraphics.blit(CREEPER_GIRL_SPICY_CRAVING, leftPos + 113, topPos + 43, 0, 0, 24, 24, 24, 24);
			return;
		}
		default:
			return;
		}	
	}
	
	public static void renderZombieGirlMainScreen(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull AbstractTamableZombieGirl<?> zombieGirl) {
		renderDefaultPreggoP0MainGUI(guiGraphics, leftPos, topPos, zombieGirl.getHealth(), zombieGirl, 3);
	}
		
	public static void renderCreeperGirlMainScreen(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull AbstractTamableCreeperGirl<?> creeperGirl) {
		renderDefaultPreggoP0MainGUI(guiGraphics, leftPos, topPos, creeperGirl.getHealth(), creeperGirl, 1);
	}
	
	private static void renderDefaultPreggoP0MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, float health, ITamablePreggoMob p0, int extraHearts) {
		for (int i = 0, pos = 74; i < 10; i++, pos += 10) {
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 16, 27, 9, 9, 256, 256);		
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 16, 45, 9, 9, 256, 256);		
		}	
		
		for (int i = 0, pos = 74; i < extraHearts; i++, pos += 10) {		
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 78, 16, 45, 9, 9, 256, 256);			
		}

		final var hungry = p0.getHungry();
		
		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			if (hungry >= evenValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 52, 27, 9, 9, 256, 256);			
			} else if (hungry >= oddValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 61, 27, 9, 9, 256, 256);
			}

			if (health >= evenValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 52, 0, 9, 9, 256, 256);
			} else if (health >= oddValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 61, 0, 9, 9, 256, 256);
			} else if (health <= 0.75F) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 61, 0, 9, 9, 256, 256);
				break;
			}
		}
		
		for (int i = 0, pos = 74, oddValue = 21, evenValue = 22; i < extraHearts; ++i, pos += 10, oddValue += 2, evenValue += 2) {			
			if (health >= evenValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 78, 52, 0, 9, 9, 256, 256);
			} else if (health >= oddValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 78, 61, 0, 9, 9, 256, 256);
			}		
		}
	}
	
	private static void renderDefaultPreggoP1MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, IPregnancyP1 p1) {	
		for (int i = 0, pos = 74; i < 10; i++, pos += 10) {
			guiGraphics.blit(ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 0, 35, 19, 19, 256, 256);		
		}	
	
		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		

			if (p1.getCraving() >= evenValue) {
				guiGraphics.blit(ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 38, 35, 19, 19, 256, 256);
			} else if (p1.getCraving() >= oddValue) {
				guiGraphics.blit(ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 19, 35, 19, 19, 256, 256);
			}					
		}
	}
	
	private static void renderDefaultPreggoP2MainGUI(GuiGraphics gui, int leftPos, int topPos, IPregnancyP2 p2) {		

		final int milking = p2.getMilking();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 103, 0, 24, 9, 9, 256, 256);		

			if (milking >= evenValue) {
				gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 103, 18, 24, 9, 9, 256, 256);
			} else if (milking >= oddValue) {
				gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 103, 9, 24, 9, 9, 256, 256);
			}			
		}	
	}
	
	private static void renderDefaultPreggoP3MainGUI(GuiGraphics gui, int leftPos, int topPos, IPregnancyP3 p3) {		

		final int bellyRubs = p3.getBellyRubs();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 114, 0, 0, 9, 9, 256, 256);		
			if (bellyRubs >= evenValue) {
				gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 114, 18, 0, 9, 9, 256, 256);
			} else if (bellyRubs >= oddValue) {
				gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 114, 9, 0, 9, 9, 256, 256);
			}	
		}	
	}
	
	private static void renderDefaultPreggoP4MainGUI(GuiGraphics gui, int leftPos, int topPos, IPregnancyP4 p4) {		

		final int horny = p4.getHorny();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 0, 11, 16, 11, 256, 256);	
			if (horny >= evenValue) {
				gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 32, 11, 16, 11, 256, 256);
			} else if (horny >= oddValue) {
				gui.blit(ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 16, 11, 16, 11, 256, 256);
			}	
		}		
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IBreedable> void renderDefaultPreggoP0LabelMainGUI(GuiGraphics guiGraphics, Font font, E p0) {	
		guiGraphics.drawString(font, p0.getSimpleName(), 90, 4, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 78, 21, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 34, -12829636, false);
		guiGraphics.drawString(font, "P0", 107, 34, -12829636, false);
		if (p0.isPregnant()) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_maybe_pregnant"), 109, 21, -12829636, false);
		} else {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_not_pregnant"), 109, 21, -12829636, false);
		}
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP1> void renderP1LabelMainGUI(GuiGraphics guiGraphics, Font font, E p1) {	
		guiGraphics.drawString(font, p1.getSimpleName(), 90, 4, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 107, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 37, -12829636, false);
		guiGraphics.drawString(font, p1.getCurrentPregnancyStage().toString(), 107, 37, -12829636, false);
		
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 75, 51, -12829636, false);
		if (p1.getTypeOfCraving() == Craving.NONE) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 51, -12829636, false);
		}   	
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP2> void renderP2LabelMainGUI(GuiGraphics guiGraphics, Font font, E p2) {
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 77, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 77, 17, -12829636, false);
		guiGraphics.drawString(font, p2.getCurrentPregnancyStage().toString(), 109, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 109, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 77, 45, -12829636, false);
		
		guiGraphics.drawString(font, p2.getSimpleName(), 90, 4, -12829636, false);

		if (p2.getTypeOfCraving() == Craving.NONE) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 45, -12829636, false);
		} 
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP3> void renderP3LabelMainGUI(GuiGraphics guiGraphics, Font font, E p3) {
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 31, -12829636, false);
		guiGraphics.drawString(font, p3.getCurrentPregnancyStage().toString(), 107, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 107, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 75, 45, -12829636, false);

		guiGraphics.drawString(font, p3.getSimpleName(), 90, 4, -12829636, false);

		if (p3.getTypeOfCraving() == Craving.NONE) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 45, -12829636, false);
		} 
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP4> void renderP4LabelMainGUI(GuiGraphics guiGraphics, Font font, E p4) {
		guiGraphics.drawString(font, p4.getSimpleName(), 90, 4, -12829636, false);	
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 74, 35, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 74, 51, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 74, 19, -12829636, false);
		guiGraphics.drawString(font, p4.getCurrentPregnancyStage().toString(), 107, 35, -12829636, false);	
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 106, 19, -12829636, false);
		
		if (p4.getTypeOfCraving() == Craving.NONE) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 51, -12829636, false);
		} 
	}
	
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?> & IPregnancyP1> void renderCreeperGirlP1MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E creeperGirl) {		
		renderCreeperGirlMainScreen(guiGraphics, leftPos, topPos, creeperGirl);	
		renderCreeperGirlCravingMainGUI(guiGraphics, leftPos, topPos, creeperGirl);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, creeperGirl);				
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?> & IPregnancyP2> void renderCreeperGirlP2MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E p2) {
		renderCreeperGirlMainScreen(guiGraphics, leftPos, topPos + 10, p2);
		renderCreeperGirlCravingMainGUI(guiGraphics, leftPos, topPos, p2);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, p2);	
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, p2);		
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?> & IPregnancyP3> void renderCreeperGirlP3MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E p3) {
		renderCreeperGirlMainScreen(guiGraphics, leftPos, topPos + 10, p3);
		renderCreeperGirlCravingMainGUI(guiGraphics, leftPos, topPos, p3);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, p3);		
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, p3);		
		renderDefaultPreggoP3MainGUI(guiGraphics, leftPos, topPos + 4, p3);		
	}
	
	public static<E extends AbstractTamablePregnantCreeperGirl<?,?> & IPregnancyP4> void renderCreeperGirlP4MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E p4) {
		renderCreeperGirlMainScreen(guiGraphics, leftPos, topPos + 10, p4);
		renderCreeperGirlCravingMainGUI(guiGraphics, leftPos, topPos, p4);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, p4);		
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, p4);		
		renderDefaultPreggoP3MainGUI(guiGraphics, leftPos, topPos + 4, p4);	
		renderDefaultPreggoP4MainGUI(guiGraphics, leftPos, topPos, p4);			
	}
	

	public static<E extends AbstractTamablePregnantZombieGirl<?,?> & IPregnancyP1> void renderZombieGirlP1MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E zombieGirl) {
		renderZombieGirlMainScreen(guiGraphics, leftPos, topPos + 10, zombieGirl);
		renderZombieGirlCravingMainGUI(guiGraphics, leftPos, topPos, zombieGirl);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, zombieGirl);				
	}
	
	public static<E extends AbstractTamablePregnantZombieGirl<?,?> & IPregnancyP2> void renderZombieGirlP2MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E p2) {
		renderZombieGirlMainScreen(guiGraphics, leftPos, topPos + 10, p2);
		renderZombieGirlCravingMainGUI(guiGraphics, leftPos, topPos, p2);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, p2);	
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, p2);		
	}
	
	public static<E extends AbstractTamablePregnantZombieGirl<?,?> & IPregnancyP3> void renderZombieGirlP3MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E p3) {
		renderZombieGirlMainScreen(guiGraphics, leftPos, topPos + 10, p3);
		renderZombieGirlCravingMainGUI(guiGraphics, leftPos, topPos, p3);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, p3);		
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, p3);		
		renderDefaultPreggoP3MainGUI(guiGraphics, leftPos, topPos + 4, p3);		
	}
	
	public static<E extends AbstractTamablePregnantZombieGirl<?,?> & IPregnancyP4> void renderZombieGirlP4MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, E p4) {
		renderZombieGirlMainScreen(guiGraphics, leftPos, topPos + 10, p4);
		renderZombieGirlCravingMainGUI(guiGraphics, leftPos, topPos, p4);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, p4);		
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, p4);		
		renderDefaultPreggoP3MainGUI(guiGraphics, leftPos, topPos + 4, p4);	
		renderDefaultPreggoP4MainGUI(guiGraphics, leftPos, topPos, p4);		
	}
}
