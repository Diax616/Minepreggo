package dev.dixmk.minepreggo.client.gui;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.AbstractCreeperGirlMainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.AbstractZombieGirlMainScreen;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ScreenHelper {

	private ScreenHelper() {}
		
	public static final ResourceLocation MINECRAFT_ICONS_TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/icons.png");
	public static final ResourceLocation MINEPREGGO_ICONS_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/icons.png");
	
	public static void renderZombieGirlMainScreen(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull AbstractTamableZombieGirl<?> zombieGirl) {
		renderDefaultPreggoP0MainGUI(guiGraphics, leftPos, topPos, zombieGirl.getHealth(), zombieGirl, 3);
	}
		
	public static void renderCreeperGirlMainScreen(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull AbstractTamableCreeperGirl<?> creeperGirl) {
		renderDefaultPreggoP0MainGUI(guiGraphics, leftPos, topPos, creeperGirl.getHealth(), creeperGirl, 1);
	}
	
	private static void renderDefaultPreggoP0MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, float health, ITamablePreggoMob<?> p0, int extraHearts) {
		for (int i = 0, pos = 74; i < 10; i++, pos += 10) {
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 16, 27, 9, 9, 256, 256);		
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 16, 45, 9, 9, 256, 256);		
		}	
		
		for (int i = 0, pos = 74; i < extraHearts; i++, pos += 10) {		
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 78, 16, 45, 9, 9, 256, 256);			
		}

		final var hungry = p0.getFullness();
		
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
	
	private static void renderDefaultPreggoP1MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, IPregnancyEffectsHandler p1) {	
		for (int i = 0, pos = 74; i < 10; i++, pos += 10) {
			guiGraphics.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 0, 35, 19, 19, 256, 256);		
		}	
	
		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		

			if (p1.getCraving() >= evenValue) {
				guiGraphics.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 38, 35, 19, 19, 256, 256);
			} else if (p1.getCraving() >= oddValue) {
				guiGraphics.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 98, 9, 9, 19, 35, 19, 19, 256, 256);
			}					
		}
	}
	
	private static void renderDefaultPreggoP2MainGUI(GuiGraphics gui, int leftPos, int topPos, IPregnancyEffectsHandler p2) {		

		final int milking = p2.getMilking();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 103, 0, 24, 9, 9, 256, 256);		

			if (milking >= evenValue) {
				gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 103, 18, 24, 9, 9, 256, 256);
			} else if (milking >= oddValue) {
				gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 103, 9, 24, 9, 9, 256, 256);
			}			
		}	
	}
	
	private static void renderDefaultPreggoP3MainGUI(GuiGraphics gui, int leftPos, int topPos, IPregnancyEffectsHandler p3) {		

		final int bellyRubs = p3.getBellyRubs();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 114, 0, 0, 9, 9, 256, 256);		
			if (bellyRubs >= evenValue) {
				gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 114, 18, 0, 9, 9, 256, 256);
			} else if (bellyRubs >= oddValue) {
				gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 114, 9, 0, 9, 9, 256, 256);
			}	
		}	
	}
	
	private static void renderDefaultPreggoP4MainGUI(GuiGraphics gui, int leftPos, int topPos, IPregnancyEffectsHandler p4) {		

		final int horny = p4.getHorny();

		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 0, 11, 16, 11, 256, 256);	
			if (horny >= evenValue) {
				gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 32, 11, 16, 11, 256, 256);
			} else if (horny >= oddValue) {
				gui.blit(MINEPREGGO_ICONS_TEXTURE, leftPos + pos, topPos + 128, 9, 9, 16, 11, 16, 11, 256, 256);
			}	
		}		
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IFemaleEntity> void renderDefaultPreggoLabelMainGUI(GuiGraphics guiGraphics, Font font, E p0) {	
		guiGraphics.drawString(font, p0.getSimpleName(), 90, 4, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 21, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 34, -12829636, false);
		guiGraphics.drawString(font, "P0", 107, 34, -12829636, false);
		if (p0.isPregnant()) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_maybe_pregnant"), 104, 21, -12829636, false);
		} else {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_not_pregnant"), 104, 21, -12829636, false);
		}
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> void renderP0LabelMainGUI(GuiGraphics guiGraphics, Font font, E p0) {	
		guiGraphics.drawString(font, p0.getSimpleName(), 90, 4, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 107, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 37, -12829636, false);
		guiGraphics.drawString(font, p0.getCurrentPregnancyStage().toString(), 107, 37, -12829636, false);
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> void renderP1LabelMainGUI(GuiGraphics guiGraphics, Font font, E p1) {	
		guiGraphics.drawString(font, p1.getSimpleName(), 90, 4, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 107, 22, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 37, -12829636, false);
		guiGraphics.drawString(font, p1.getCurrentPregnancyStage().toString(), 107, 37, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 75, 51, -12829636, false);
		
		if (p1.getTypeOfCraving() == null) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 51, -12829636, false);
		} 
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> void renderP2LabelMainGUI(GuiGraphics guiGraphics, Font font, E p2) {
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 77, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 77, 17, -12829636, false);
		guiGraphics.drawString(font, p2.getCurrentPregnancyStage().toString(), 109, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 109, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 77, 45, -12829636, false);
		
		guiGraphics.drawString(font, p2.getSimpleName(), 90, 4, -12829636, false);

		if (p2.getTypeOfCraving() == null) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 45, -12829636, false);
		} 
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> void renderP3LabelMainGUI(GuiGraphics guiGraphics, Font font, E p3) {
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 75, 31, -12829636, false);
		guiGraphics.drawString(font, p3.getCurrentPregnancyStage().toString(), 107, 31, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 75, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 107, 17, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 75, 45, -12829636, false);

		guiGraphics.drawString(font, p3.getSimpleName(), 90, 4, -12829636, false);

		if (p3.getTypeOfCraving() == null) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 45, -12829636, false);
		} 
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> void renderP4LabelMainGUI(GuiGraphics guiGraphics, Font font, E p4) {
		guiGraphics.drawString(font, p4.getSimpleName(), 90, 4, -12829636, false);	
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_phase"), 74, 35, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_craving"), 74, 51, -12829636, false);
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_state"), 74, 19, -12829636, false);
		guiGraphics.drawString(font, p4.getCurrentPregnancyStage().toString(), 107, 35, -12829636, false);	
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_pregnant"), 106, 19, -12829636, false);
		
		if (p4.getTypeOfCraving() == null) {
			guiGraphics.drawString(font, Component.translatable("gui.minepreggo.preggo_mob_main.label_none"), 118, 51, -12829636, false);
		} 
	}
	
	public static void renderCravingIcon(GuiGraphics guiGraphics, int leftPos, int topPos, @NonNull ResourceLocation icon) {
		guiGraphics.blit(icon, leftPos + 116, topPos + 45, 0, 0, 16, 16, 16, 16);
	}

	public static void tryRenderCravingIcon(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantCreeperGirl<?,?> creeperGirl) {
		final var icon = AbstractCreeperGirlMainScreen.getCravingIcon(creeperGirl.getTypeOfCraving());
		if (icon != null) {
			renderCravingIcon(guiGraphics, leftPos, topPos, icon);
		}
	}
	
	public static void tryRenderCravingIcon(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantZombieGirl<?,?> zombieGirl) {
		final var icon = AbstractZombieGirlMainScreen.getCravingIcon(zombieGirl.getTypeOfCraving());
		if (icon != null) {
			renderCravingIcon(guiGraphics, leftPos, topPos, icon);
		}
	}	
	
	public static void renderCreeperGirlP1MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantCreeperGirl<?,?> creeperGirl) {		
		renderCreeperGirlMainScreen(guiGraphics, leftPos, topPos + 10, creeperGirl);		
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, creeperGirl);	
		tryRenderCravingIcon(guiGraphics, leftPos, topPos, creeperGirl);
	}
	
	public static void renderCreeperGirlP2MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantCreeperGirl<?,?> creeperGirl) {
		renderCreeperGirlMainScreen(guiGraphics, leftPos, topPos + 10, creeperGirl);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, creeperGirl);	
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, creeperGirl);	
		tryRenderCravingIcon(guiGraphics, leftPos, topPos - 5, creeperGirl);
	}
	
	public static void renderCreeperGirlP3MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantCreeperGirl<?,?> creeperGirl) {
		renderCreeperGirlMainScreen(guiGraphics, leftPos, topPos + 10, creeperGirl);
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, creeperGirl);		
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, creeperGirl);		
		renderDefaultPreggoP3MainGUI(guiGraphics, leftPos, topPos + 4, creeperGirl);		
		tryRenderCravingIcon(guiGraphics, leftPos, topPos - 5, creeperGirl);
	}
	
	public static void renderCreeperGirlP4MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantCreeperGirl<?,?> creeperGirl) {
		renderCreeperGirlMainScreen(guiGraphics, leftPos, topPos + 10, creeperGirl);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, creeperGirl);		
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, creeperGirl);		
		renderDefaultPreggoP3MainGUI(guiGraphics, leftPos, topPos + 4, creeperGirl);	
		renderDefaultPreggoP4MainGUI(guiGraphics, leftPos, topPos, creeperGirl);		
		tryRenderCravingIcon(guiGraphics, leftPos, topPos, creeperGirl);
	}
	

	public static void renderZombieGirlP1MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantZombieGirl<?,?> zombieGirl) {
		renderZombieGirlMainScreen(guiGraphics, leftPos, topPos + 10, zombieGirl);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, zombieGirl);		
		tryRenderCravingIcon(guiGraphics, leftPos, topPos, zombieGirl);
	}
	
	public static void renderZombieGirlP2MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantZombieGirl<?,?> zombieGirl) {
		renderZombieGirlMainScreen(guiGraphics, leftPos, topPos + 10, zombieGirl);	
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, zombieGirl);	
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, zombieGirl);	
		tryRenderCravingIcon(guiGraphics, leftPos, topPos - 5, zombieGirl);
	}
	
	public static void renderZombieGirlP3MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantZombieGirl<?,?> zombieGirl) {
		renderZombieGirlMainScreen(guiGraphics, leftPos, topPos + 10, zombieGirl);
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, zombieGirl);		
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, zombieGirl);		
		renderDefaultPreggoP3MainGUI(guiGraphics, leftPos, topPos + 4, zombieGirl);		
		tryRenderCravingIcon(guiGraphics, leftPos, topPos - 5, zombieGirl);
	}
	
	public static void renderZombieGirlP4MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, AbstractTamablePregnantZombieGirl<?,?> zombieGirl) {
		renderZombieGirlMainScreen(guiGraphics, leftPos, topPos + 10, zombieGirl);
		renderDefaultPreggoP1MainGUI(guiGraphics, leftPos, topPos, zombieGirl);		
		renderDefaultPreggoP2MainGUI(guiGraphics, leftPos, topPos + 5, zombieGirl);		
		renderDefaultPreggoP3MainGUI(guiGraphics, leftPos, topPos + 4, zombieGirl);	
		renderDefaultPreggoP4MainGUI(guiGraphics, leftPos, topPos, zombieGirl);		
		tryRenderCravingIcon(guiGraphics, leftPos, topPos, zombieGirl);
	}
}
