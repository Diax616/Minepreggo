package dev.dixmk.minepreggo.world.entity.player;

import java.util.List;
import java.util.UUID;

import javax.annotation.CheckForNull;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class PlayerHelper {

	private PlayerHelper() {}
	
	protected static final ImmutableMap<Craving, List<ResourceLocation>> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/salty_pickle.png")), 
			Craving.SWEET, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chocolate_bar.png")), 
			Craving.SOUR, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_popsicles.png"),
					ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_cream.png")),
			Craving.SPICY, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/spicy_chicken.png")));
	
	protected static final ImmutableMap<String, Pair<ResourceLocation, ResourceLocation>> PREDEFINED_SKINS = ImmutableMap.of(
			"player1", Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_extra.png"))		
			);
	
	protected static final ImmutableMap<String, ImmutableMap<PregnancyPhase, Pair<ResourceLocation, ResourceLocation>>> MATERNITY_PREDEFINED_SKINS = ImmutableMap.of(
			"player1", ImmutableMap.of(
					PregnancyPhase.P0, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p0.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p0_extra.png")),
					PregnancyPhase.P1, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p1.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p1_extra.png")),
					PregnancyPhase.P2, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p2.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p2_extra.png")),
					PregnancyPhase.P3, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p3.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p3_extra.png")),
					PregnancyPhase.P4, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p4.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p4_extra.png")),
					PregnancyPhase.P5, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p5.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p5_extra.png")),
					PregnancyPhase.P6, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p6.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p6_extra.png")),
					PregnancyPhase.P7, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p7.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p7_extra.png")),
					PregnancyPhase.P8, Pair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p8.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p8_extra.png"))
			));

	
	@CheckForNull
	public static Pair<ResourceLocation, ResourceLocation> getPredefinedPlayerTextures(String name, PregnancyPhase phase) {
		var textures = MATERNITY_PREDEFINED_SKINS.get(name);
		if (textures != null) {
			return textures.get(phase);			
		}	
		return null;
	}

	@CheckForNull
	public static Pair<ResourceLocation, ResourceLocation> getPredefinedPlayerTextures(String name) {
		return PREDEFINED_SKINS.get(name);
	}
	
	
	private static boolean tryToStartPregnancy(ServerPlayer player, Baby typeOfBaby, int numOfBabies, @Nullable UUID fatherId) {
		var playerDataCap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();		

		if (playerDataCap.isEmpty()) {
			MinepreggoMod.LOGGER.error("Player {} has no PLAYER_DATA={} capability.", player.getName().getString(), playerDataCap.isEmpty());
			return false;
		}

		final var femaleData = playerDataCap.get().getFemaleData().resolve();
	
		if (femaleData.isPresent() && !femaleData.get().isPregnant()) {
			
			final var femalePlayerData = femaleData.get();
			
			femalePlayerData.tryImpregnate(fatherId);
			
			femalePlayerData.getPregnancySystem().resetDaysPassed();
						
			final var lastPregnancyStage = PregnancyPhase.P8; //IBreedable.getMaxPregnancyPhaseByOffsprings(numOfBabies);
			final var totalDays = MinepreggoModConfig.getTotalPregnancyDays();
			final var daysByStage = PregnancySystemHelper.createDaysByPregnancyPhase(totalDays, lastPregnancyStage);
			
			femalePlayerData.getPregnancySystem().setDaysByStage(daysByStage);
			femalePlayerData.getPregnancySystem().setCurrentPregnancyStage(PregnancyPhase.P8);
			femalePlayerData.getPregnancySystem().setLastPregnancyStage(lastPregnancyStage);
			femalePlayerData.getPregnancySystem().setDaysToGiveBirth(totalDays);	
			femalePlayerData.getPregnancySystem().addBaby(typeOfBaby, numOfBabies);		
			player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_P8.get(), -1, 0, false, false, true));				
				
			femalePlayerData.sync(player);
			femalePlayerData.getPregnancySystem().sync(player);
				
			MinepreggoMod.LOGGER.debug("Player {} has become pregnant with {} x{}, lastPregnancyStage={}, daysByStage={}",
					player.getName().getString(), numOfBabies, typeOfBaby.name(), lastPregnancyStage, daysByStage);
			
			return true;
		}
		
		return false;
	}
	
	public static boolean tryStartPregnancyBySex(ServerPlayer female, ServerPlayer male) {		
		var femalePlayerCap = female.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
		var malePlayerCap = male.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();

		if (femalePlayerCap.isEmpty() || malePlayerCap.isEmpty()) {
			return false;
		}
		
		final var femalePlayerData = femalePlayerCap.get().getFemaleData().resolve();
		final var malePlayerData = malePlayerCap.get().getMaleData().resolve();
		
		if (femalePlayerData.isEmpty() || malePlayerData.isEmpty()) {
			return false;
		}
		
		final var numOfBabies = IBreedable.calculateNumOfOffspringByFertility(malePlayerData.get().getFertilityRate(), femalePlayerData.get().getFertilityRate());
		
		if (numOfBabies == 0) {
			return false;
		}
			
		return tryToStartPregnancy(female, Baby.HUMAN, numOfBabies, male.getUUID());
	}
	
	public static boolean tryStartPregnancyByPotion(ServerPlayer player, Baby typeOfBaby, int amplifier) {			
		final var numOfbabies = IBreedable.calculateNumOfOffspringByPotion(amplifier);
		return tryToStartPregnancy(player, typeOfBaby, numOfbabies, null);
	}
	
	public static boolean tryStartPregnancyByMobAttack(ServerPlayer female, Species species) {
		return false;
	}
	
	@CheckForNull
	public static List<ResourceLocation> getCravingIcon(Craving craving) {
		if (craving == null) {
			return null;
		}
		return CRAVING_ICONS.get(craving);	
	}
	
	public static boolean isPlayerValid(LivingEntity entity) {
		return entity instanceof Player;
	}
}
