package dev.dixmk.minepreggo.world.entity.player;

import java.util.List;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.Gender;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class PlayerHelper {

	private PlayerHelper() {}
	
	protected static final ImmutableMap<Craving, List<ResourceLocation>> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/salty_pickle.png")), 
			Craving.SWEET, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chocolate_bar.png")), 
			Craving.SOUR, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_popsicles.png"),
					ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_cream.png")),
			Craving.SPICY, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/spicy_chicken.png")));
	
	public static boolean tryToStartPregnancy(ServerPlayer player, Baby typeOfBaby, int amplifier) {
		var playerDataCap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();	
		var pregnancySystemCap = player.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM).resolve();	

		if (playerDataCap.isEmpty() || pregnancySystemCap.isEmpty()) {
			MinepreggoMod.LOGGER.error("Player {} has no PLAYER_DATA={} or PLAYER_PREGNANCY_SYSTEM={} capability.", player.getName().getString(), playerDataCap.isEmpty(), pregnancySystemCap.isEmpty());
			return false;
		}
		
		var playerData = playerDataCap.get();
		var pregnancySystem = pregnancySystemCap.get();
		
		if (playerData.getGender() == Gender.FEMALE) {
			playerData.setPregnant(true);
			pregnancySystem.resetDaysPassed();
			
			final var numOfbabies = IBreedable.calculateNumOfOffspringByPotion(amplifier);
			final var lastPregnancyStage = IBreedable.getMaxPregnancyStageByOffsprings(numOfbabies);
			final var daysByStage = IBreedable.calculateDaysByStage(lastPregnancyStage);
			
			pregnancySystem.setDaysByStage(daysByStage);
			pregnancySystem.setLastPregnancyStage(lastPregnancyStage);
			pregnancySystem.setCurrentPregnancyStage(PregnancyStage.P1);
			pregnancySystem.setDaysToGiveBirth(daysByStage * (lastPregnancyStage.ordinal() + 1));
			
			pregnancySystem.addBaby(typeOfBaby, numOfbabies);		
			player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_P1.get(), -1, 0, false, false));				
			
			MinepreggoMod.LOGGER.debug("Player {} has become pregnant with {} x {}, lastPregnancyStage={}, daysByStage={}",
					player.getName().getString(), numOfbabies, typeOfBaby.name(), lastPregnancyStage, daysByStage);
			
			return true;
		}	
		return false;
	}
	
	public static boolean isFemaleAndPregnant(ServerPlayer player) {
		var cap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();	
		if (cap.isEmpty()) {
			MinepreggoMod.LOGGER.error("Player {} has no PLAYER_DATA capability.", player.getName().getString());
			return false;
		}	
		return cap.get().getGender() == Gender.FEMALE && cap.get().isPregnant();
	}
	
	public static boolean isFemale(ServerPlayer player) {
		var cap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();	
		if (cap.isEmpty()) {
			MinepreggoMod.LOGGER.error("Player {} has no PLAYER_DATA capability.", player.getName().getString());
			return false;
		}	
		return cap.get().getGender() == Gender.FEMALE;
	}
	
	@CheckForNull
	public static List<ResourceLocation> getCravingIcon(Craving craving) {
		if (craving == null) {
			return null;
		}
		return CRAVING_ICONS.get(craving);	
	}
}
