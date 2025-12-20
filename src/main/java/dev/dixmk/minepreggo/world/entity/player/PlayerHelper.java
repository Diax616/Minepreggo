package dev.dixmk.minepreggo.world.entity.player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.IFemaleArmor;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMaps;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PlayerHelper {

	private PlayerHelper() {}
	
	private static final ImmutableMap<Craving, List<ResourceLocation>> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/salty_pickle.png")), 
			Craving.SWEET, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chocolate_bar.png")), 
			Craving.SOUR, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_popsicles.png"),
					ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_cream.png")),
			Craving.SPICY, List.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/spicy_chicken.png")));
	
	private static final ImmutableMap<String, ImmutablePair<ResourceLocation, ResourceLocation>> PREDEFINED_SKINS = ImmutableMap.of(
			"player1", ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_extra.png"))		
			);
	
	private static final ImmutableMap<String, ImmutableMap<PregnancyPhase, ImmutablePair<ResourceLocation, ResourceLocation>>> MATERNITY_PREDEFINED_SKINS = ImmutableMap.of(
			"player1", ImmutableMap.of(
					PregnancyPhase.P0, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p0.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p0_extra.png")),
					PregnancyPhase.P1, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p1.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p1_extra.png")),
					PregnancyPhase.P2, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p2.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p2_extra.png")),
					PregnancyPhase.P3, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p3.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p3_extra.png")),
					PregnancyPhase.P4, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p4.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p4_extra.png")),
					PregnancyPhase.P5, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p5.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p5_extra.png")),
					PregnancyPhase.P6, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p6.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p6_extra.png")),
					PregnancyPhase.P7, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p7.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p7_extra.png")),
					PregnancyPhase.P8, ImmutablePair.of(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p8.png"), ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p8_extra.png"))
			));

	
	private static final Object2IntMap<PregnancyPhase> MAX_JUMPS = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P3, 30,
			PregnancyPhase.P4, 25,
			PregnancyPhase.P5, 20,
			PregnancyPhase.P6, 15,
			PregnancyPhase.P7, 10,
			PregnancyPhase.P8, 5
			)));
	
	private static final Object2FloatMap<PregnancyPhase> MAX_JUMP_STRENGTH = Object2FloatMaps.unmodifiable(new Object2FloatOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P3,0.01F,
			PregnancyPhase.P4, 0.015F,
			PregnancyPhase.P5, 0.02F,
			PregnancyPhase.P6, 0.025F,
			PregnancyPhase.P7, 0.03F,
			PregnancyPhase.P8, 0.035F
			)));
	
	private static final Object2IntMap<PregnancyPhase> SPRINTING_TIMERS = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P3, 4000,
			PregnancyPhase.P4, 3800,
			PregnancyPhase.P5, 3600,
			PregnancyPhase.P6, 3400,
			PregnancyPhase.P7, 3200,
			PregnancyPhase.P8, 3000
			)));
	
	private static final Object2IntMap<PregnancyPhase> SNEAKING_TIMERS = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P4, 3000,
			PregnancyPhase.P5, 2800,
			PregnancyPhase.P6, 2600,
			PregnancyPhase.P7, 2400,
			PregnancyPhase.P8, 2200
			)));
	
	
	public static int maxJumps(PregnancyPhase phase) {
		return MAX_JUMPS.getInt(phase.compareTo(PregnancyPhase.P3) <= -1 ? PregnancyPhase.P3 : phase);
	}
	
	public static float maxJumpStrength(PregnancyPhase phase) {
		return MAX_JUMP_STRENGTH.getFloat(phase.compareTo(PregnancyPhase.P4) <= -1 ? PregnancyPhase.P4 : phase);
	}

	public static int sprintingTimer(PregnancyPhase phase) {
		return SPRINTING_TIMERS.getInt(phase.compareTo(PregnancyPhase.P3) <= -1 ? PregnancyPhase.P3 : phase);
	}
	
	public static int sneakingTimer(PregnancyPhase phase) {
		return SNEAKING_TIMERS.getInt(phase.compareTo(PregnancyPhase.P4) <= -1 ? PregnancyPhase.P4 : phase);
	}
	
	@CheckForNull
	public static ImmutablePair<ResourceLocation, ResourceLocation> getPredefinedPlayerTextures(String name, PregnancyPhase phase) {
		var textures = MATERNITY_PREDEFINED_SKINS.get(name);
		if (textures != null) {
			return textures.get(phase);			
		}	
		return null;
	}

	@CheckForNull
	public static ImmutablePair<ResourceLocation, ResourceLocation> getPredefinedPlayerTextures(String name) {
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
				final var daysByStage = new MapPregnancyPhase(totalDays, lastPregnancyStage);
				final var womb = new Womb(
						ImmutableTriple.of(player.getUUID(), Species.HUMAN, Creature.HUMANOID),
						ImmutableTriple.of(Optional.ofNullable(prePregnancyData.fatherId()), prePregnancyData.typeOfSpeciesOfFather(), prePregnancyData.typeOfCreatureOfFather()),
						player.getRandom(),
						prePregnancyData.fertilizedEggs());
				
				
				femaleData.getPregnancySystem().setMapPregnancyPhase(daysByStage);
				femaleData.getPregnancySystem().setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
				femaleData.getPregnancySystem().setLastPregnancyStage(lastPregnancyStage);
				femaleData.getPregnancySystem().setDaysToGiveBirth(totalDays);			
				femaleData.getPregnancySystem().setWomb(womb);	
				femaleData.getPregnancySystem().setCurrentPregnancyStage(PregnancyPhase.P0);	
				
				femaleData.sync(player);
				femaleData.getPregnancySystem().sync(player);	
			
				player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_P0.get(), -1, 0, false, false, true));				
					
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
		else {
			MinepreggoMod.LOGGER.error("Player {} is already pregnant or has no FEMALE_DATA={} capability.", player.getName().getString(), femaleData.isEmpty());
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
		return craving != null ? CRAVING_ICONS.get(craving) : null;	
	}
	
	public static boolean isPlayerValid(LivingEntity entity) {
		return entity instanceof ServerPlayer s && s.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> cap.getFemaleData().isPresent()).isPresent();
	}
	
	public static boolean canUseChestplate(Item armor) {
		if (!ItemHelper.isChest(armor)) {
			return false;
		}		
		return armor instanceof IFemaleArmor;
	}
	
	public static boolean canUseChestPlateInLactation(LivingEntity target, Item armor) {	
		if (!target.hasEffect(MinepreggoModMobEffects.LACTATION.get())) {
			return true;
		}
		return armor instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed();
	}
	
	public static boolean canUseChestplate(LivingEntity target, Item armor, PregnancyPhase pregnancyPhase, boolean considerBoobs) {	
		return PregnancySystemHelper.canUseChestplate(armor, pregnancyPhase, considerBoobs) && !target.hasEffect(MinepreggoModMobEffects.LACTATION.get());
	}
	
	public static boolean canUseChestplate(LivingEntity target, Item armor, PregnancyPhase pregnancyPhase) {	
		return PregnancySystemHelper.canUseChestplate(armor, pregnancyPhase, true) && !target.hasEffect(MinepreggoModMobEffects.LACTATION.get());
	}
	
	
	public static void removeAndDropItemStackFromEquipmentSlot(Player player, EquipmentSlot slotId) {
		if (PreggoMobHelper.dropItemStack(player, player.getItemBySlot(slotId))) {
			player.setItemSlot(slotId, ItemStack.EMPTY);
		}
 	}
	
	public static void replaceAndDropItemstackInHand(ServerPlayer player, InteractionHand hand, ItemStack itemStack) {			
		var current = player.getItemInHand(hand);		
		if (!current.isEmpty()) {
			PreggoMobHelper.dropItemStack(player, current);		
		}		
		player.setItemInHand(hand, itemStack);
	}
	
	@OnlyIn(Dist.CLIENT)
    public static boolean isPlayingBirthAnimation(Player player) {
    	var anim = PlayerAnimationManager.getInstance().get(player).getCurrentAnimationName();
        return anim != null && anim.equals("birth");
    }
}
