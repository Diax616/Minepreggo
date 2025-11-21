package dev.dixmk.minepreggo.event;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.gui.ScreenHelper;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.AbstractCreeperGirlMainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.AbstractZombieGirlMainScreen;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyEffectsImpl;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class ScreenEventHandler {

	private ScreenEventHandler() {}
	
	private static int ticks = 0;
	private static ResourceLocation cravingIcon = null;
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void playerPregnancySymptonsHandler(RenderGuiEvent.Pre event) {	
		var player = Minecraft.getInstance().player;
			
		final var playerData = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();

		if (playerData.isEmpty()) {
			MinepreggoMod.LOGGER.error("Failed to get player capabilities for pregnancy symptons rendering.");	
			return;
		}
			
		playerData.ifPresent(cap -> 			
			cap.getFemaleData().ifPresent(femaleData -> {
				
				if (!femaleData.isPregnant()) return;			
				
				RenderSystem.disableDepthTest();
				RenderSystem.depthMask(false);
				RenderSystem.enableBlend();
				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				RenderSystem.setShaderColor(1, 1, 1, 1);
				final int w = event.getWindow().getGuiScaledWidth();
				final var gui = event.getGuiGraphics();
				
				final var pregnancySystem = femaleData.getPregnancySystem();
				final var pregnancyEffects = femaleData.getPregnancyEffects();
				
				Runnable cravingOverlay = () -> {
					if (pregnancySystem.getPregnancySymptom() == PregnancySymptom.CRAVING ) {
						renderCravingChoosenScreen(gui, w, player.getSkinTextureLocation(), pregnancyEffects);
					}
				};
								
				if (player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P1.get())) {				
					renderCravingScreen(gui, 4, 4, 11, pregnancyEffects);	
					cravingOverlay.run();
				}	
				else if (player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P2.get())) {	
					renderCravingScreen(gui, 4, 4, 11, pregnancyEffects);		
					cravingOverlay.run();	
					renderMilkingScreen(gui, 14, 4, 11, pregnancyEffects);
				}
				else if (player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P3.get())) {	
					renderCravingScreen(gui, 4, 4, 11, pregnancyEffects);		
					cravingOverlay.run();	
					renderMilkingScreen(gui, 14, 4, 11, pregnancyEffects);
					renderBellyRubsScreen(gui, 24, 4, 11, pregnancyEffects);
				}
				else if (player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P4.get())
						|| player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P5.get())
						|| player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P6.get())
						|| player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P7.get())
						|| player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P8.get())) {	
					renderCravingScreen(gui, 4, 4, 11, pregnancyEffects);		
					cravingOverlay.run();		
					renderMilkingScreen(gui, 14, 4, 11, pregnancyEffects);
					renderBellyRubsScreen(gui, 24, 4, 11, pregnancyEffects);
					renderHornyScreen(gui, 34, 4, 11, pregnancyEffects);
				}
					
				RenderSystem.depthMask(true);
				RenderSystem.defaultBlendFunc();
				RenderSystem.enableDepthTest();
				RenderSystem.disableBlend();
				RenderSystem.setShaderColor(1, 1, 1, 1);	
			})
		);
	}
	
	private static void renderCravingScreen(GuiGraphics gui, int top, int init, int diff, @NonNull PlayerPregnancyEffectsImpl pregnancyEffects) {
		int pos;
		final int craving = pregnancyEffects.getCraving();	
		for (int i = 0, oddValue = 1, evenValue = 2 ; i < 10; i++, oddValue +=2, evenValue += 2) {
			pos = init + (i * diff);
			gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 9, 9, 0, 35, 19, 19, 256, 256);
			if (craving >= evenValue) 
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 9, 9, 38, 35, 19, 19, 256, 256);
			else if (craving >= oddValue)	
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 9, 9, 19, 35, 19, 19, 256, 256);
		}	
	}
	
	private static void renderCravingChoosenScreen(GuiGraphics gui, int w, ResourceLocation playerIcon, @NonNull PlayerPregnancyEffectsImpl pregnancyEffects) {		
		final var pair = pregnancyEffects.getTypeOfCravingBySpecies();	
		if (pair == null) {
			return;
		}		
			
		final var craving = pair.getKey();
		final var species = pair.getValue();	
		gui.blit(playerIcon, w - 60, 2, 16, 16, 8, 8, 8, 8, 64, 64);	
		gui.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, w - 42, 1, 18, 18, 16, 45, 9, 9, 256, 256);
		gui.blit(ScreenHelper.MINECRAFT_ICONS_TEXTURE, w - 42, 1, 18, 18, 52, 0, 9, 9, 256, 256);

		if (species == Species.HUMAN) {		
			final var list = PlayerHelper.getCravingIcon(craving);			
			if (list != null) {
				if (list.size() > 1) {
					if (ticks <= 0) {
						cravingIcon = list.get(Minecraft.getInstance().player.getRandom().nextInt(0, list.size()));
						ticks = 100;
					}
					else {
						--ticks;			
					}				
				}
				else {
					cravingIcon = list.get(0);
				}
			}								
		}	
		else if (species == Species.CREEPER) {
			cravingIcon = AbstractCreeperGirlMainScreen.getCravingIcon(craving);
		}
		else if (species == Species.ZOMBIE) {
			cravingIcon = AbstractZombieGirlMainScreen.getCravingIcon(craving);
		}
		else {
			cravingIcon = null;
		}
			
		if (cravingIcon != null) {
			gui.blit(cravingIcon, w - 24, -1, 24, 24, 0F, 0F, 16, 16, 16, 16);
		}
	}
	
	private static void renderMilkingScreen(GuiGraphics gui, int top, int init, int diff, @NonNull PlayerPregnancyEffectsImpl pregnancyEffects) {
		int pos;
		final int milking = pregnancyEffects.getMilking();	
		for (int i = 0, oddValue = 1, evenValue = 2 ; i < 10; i++, oddValue +=2, evenValue += 2) {
			pos = init + (i * diff);
			gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 0, 24, 9, 9, 256, 256);
			
			if (milking >= evenValue) 
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 18, 24, 9, 9, 256, 256);
			else if (milking >= oddValue)	
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 9, 24, 9, 9, 256, 256);	
		}
	}
	
	private static void renderBellyRubsScreen(GuiGraphics gui, int top, int init, int diff,  @NonNull PlayerPregnancyEffectsImpl pregnancyEffects) {
		int pos;
		final int bellyRubs = pregnancyEffects.getBellyRubs();	
		for (int i = 0, oddValue = 1, evenValue = 2 ; i < 10; i++, oddValue +=2, evenValue += 2) {
			pos = init + (i * diff);
			gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 0, 0, 9, 9, 256, 256);		
			if (bellyRubs >= evenValue) 
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 18, 0, 9, 9, 256, 256);
			else if (bellyRubs >= oddValue)	
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 9, 0, 9, 9, 256, 256);	
		}
	}
	
	private static void renderHornyScreen(GuiGraphics gui, int top, int init, int diff, @NonNull PlayerPregnancyEffectsImpl pregnancyEffects) {
		int pos; 
		final int horny = pregnancyEffects.getHorny();
		for (int i = 0, oddValue = 1, evenValue = 2 ; i < 10; i++, oddValue +=2, evenValue += 2) {
			pos = init + (i * diff);		
			gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 9, 9, 0, 11, 16, 11, 256, 256);					
			if (horny >= evenValue) 
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 9, 9, 32, 11, 16, 11, 256, 256);
			else if (horny >= oddValue)	
				gui.blit(ScreenHelper.MINEPREGGO_ICONS_TEXTURE, pos, top, 9, 9, 16, 11, 16, 11, 256, 256);	
		}	
	}
}
