package dev.dixmk.minepreggo.world.entity.player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.Womb;
import dev.dixmk.minepreggo.world.item.IFemaleArmor;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

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
	
	public static boolean tryToStartPregnancy(ServerPlayer player) {
		var playerDataCap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();		

		if (playerDataCap.isEmpty()) {
			return false;
		}

		final var femaleDataOpt = playerDataCap.get().getFemaleData().resolve();
		boolean result = femaleDataOpt.isPresent() && femaleDataOpt.get().isPregnant() && femaleDataOpt.get().getPrePregnancyData().isPresent();

		femaleDataOpt.ifPresent(femaleData -> {				
			if (!femaleData.isPregnant()) return;
			
			femaleData.getPrePregnancyData().ifPresent(prePregnancyData -> {
				final var lastPregnancyStage = IBreedable.calculateMaxPregnancyPhaseByTotalNumOfBabies(prePregnancyData.fertilizedEggs());
				final var totalDays = MinepreggoModConfig.getTotalPregnancyDays();
				final var daysByStage = PregnancySystemHelper.createDaysByPregnancyPhase(totalDays, lastPregnancyStage);
				
				femaleData.getPregnancySystem().setDaysByStage(daysByStage);
				femaleData.getPregnancySystem().setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
				femaleData.getPregnancySystem().setLastPregnancyStage(lastPregnancyStage);
				femaleData.getPregnancySystem().setDaysToGiveBirth(totalDays);			
				
				final var womb = Womb.create(
						ImmutableTriple.of(player.getUUID(), Species.HUMAN, Creature.HUMANOID),
						ImmutableTriple.of(Optional.ofNullable(prePregnancyData.fatherId()), prePregnancyData.typeOfSpeciesOfFather(), prePregnancyData.typeOfCreatureOfFather()),
						player.getRandom(),
						totalDays);
				
				femaleData.getPregnancySystem().setBabiesInsideWomb(womb);	
								
				femaleData.sync(player);
				femaleData.getPregnancySystem().sync(player);
		
				femaleData.getPregnancySystem().setCurrentPregnancyStage(PregnancyPhase.P0);
				player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_P0.get(), -1, 0, false, false, true));				
				femaleData.getPregnancySystem().sync(player);
						
				MinepreggoMod.LOGGER.debug("Player {} has become pregnant with {} babies, total days to give birth: {}, pregnancy phases days: {}, womb: {}", 
						player.getName().getString(), 
						prePregnancyData.fertilizedEggs(), 
						totalDays,
						daysByStage,
						womb);
			});
		});
		
		return result;
	}
	
	private static boolean tryToStartPrePregnancy(ServerPlayer player, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, @Nonnegative int numOfBabies) {
		var playerDataCap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();		

		if (playerDataCap.isEmpty()) {
			MinepreggoMod.LOGGER.error("Player {} has no PLAYER_DATA={} capability.", player.getName().getString(), playerDataCap.isEmpty());
			return false;
		}

		final var femaleData = playerDataCap.get().getFemaleData().resolve();
	
		if (femaleData.isPresent() && !femaleData.get().isPregnant()) {			
			return femaleData.get().tryImpregnate(numOfBabies, father);
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
		
		final var numOfBabies = IBreedable.calculateNumOfBabiesByFertility(malePlayerData.get().getFertilityRate(), femalePlayerData.get().getFertilityRate());
		
		if (numOfBabies == 0) {
			return false;
		}
			
		return tryToStartPrePregnancy(female, ImmutableTriple.of(Optional.of(male.getUUID()), Species.HUMAN, Creature.HUMANOID), numOfBabies);
	}
	
	public static boolean tryStartPregnancyByPotion(ServerPlayer player, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, int amplifier) {			
		final var numOfbabies = IBreedable.calculateNumOfBabiesByPotion(amplifier);
		if (tryToStartPrePregnancy(player, father, numOfbabies)) {
			return tryToStartPregnancy(player);
		}
		return false;
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
	
	public static boolean canUseChestplate(Item armor) {
		if (!ItemHelper.isChest(armor)) {
			return false;
		}		
		return armor instanceof IFemaleArmor;
	}
}
