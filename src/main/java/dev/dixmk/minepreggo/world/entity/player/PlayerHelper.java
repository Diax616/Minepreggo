package dev.dixmk.minepreggo.world.entity.player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModAdvancements;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.network.packet.s2c.RemovePlayerJigglePhysicsS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncJigglePhysicsS2CPacket;
import dev.dixmk.minepreggo.world.effect.EnderDragonPregnancy;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyType;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.PacketDistributor;

public class PlayerHelper {

	private PlayerHelper() {}

	// BREEDING ITEMSTACK TAGS - START
	private static final String FEMALE_PLAYER_ID = "femalePlayerUUID";
	private static final String PREGNANT_FEMALE_PLAYER_ID = "pregnantFemalePlayerUUID";
	
	public static final int DEFAULT_ENDER_POWER_COOLDOWN = 200;
	
	public static boolean containsAnyFemalePlayerIdTag(ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		}
		var tag = stack.getTag();
		return tag != null && (tag.hasUUID(FEMALE_PLAYER_ID) || tag.hasUUID(PREGNANT_FEMALE_PLAYER_ID));
	}
	
	@CheckForNull
	public static UUID getFemalePlayerIdTag(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		var tag = stack.getTag();
		return tag != null && tag.hasUUID(FEMALE_PLAYER_ID) ? tag.getUUID(FEMALE_PLAYER_ID) : null;
	}

	@CheckForNull
	public static UUID getPregnantFemalePlayerIdTag(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		var tag = stack.getTag();
		return tag != null && tag.hasUUID(PREGNANT_FEMALE_PLAYER_ID) ? tag.getUUID(PREGNANT_FEMALE_PLAYER_ID) : null;
	}	
	
	public static boolean removeAnyFemalePlayerIdTag(ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		}
		var tag = stack.getTag();
		if (tag != null) {
			boolean removed = false;
			if (tag.hasUUID(FEMALE_PLAYER_ID)) {
				tag.remove(FEMALE_PLAYER_ID);
				removed = true;
			}
			if (tag.hasUUID(PREGNANT_FEMALE_PLAYER_ID)) {
				tag.remove(PREGNANT_FEMALE_PLAYER_ID);
				removed = true;
			}
			return removed;
		}
		
		return false;
	}
	
	public static void addFemalePlayerIdTag(ItemStack stack, UUID femalePlayerId) {
		if (stack.isEmpty()) {
			return;
		}
		var tag = stack.getOrCreateTag();
		tag.putUUID(FEMALE_PLAYER_ID, femalePlayerId);
	}
	
	public static void addPregnantFemalePlayerIdTag(ItemStack stack, UUID pregnantFemalePlayerId) {
		if (stack.isEmpty()) {
			return;
		}
		var tag = stack.getOrCreateTag();
		tag.putUUID(PREGNANT_FEMALE_PLAYER_ID, pregnantFemalePlayerId);
	}
	// BREEDING ITEMSTACK TAGS - END
	

	// MOVEMENT NERFS IN PREGNANCY PHASES - START
	private static final Object2IntMap<PregnancyPhase> MAX_JUMPS = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P3, 45,
			PregnancyPhase.P4, 40,
			PregnancyPhase.P5, 35,
			PregnancyPhase.P6, 30,
			PregnancyPhase.P7, 25,
			PregnancyPhase.P8, 20
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
	
	public static int sprintingTimer(PregnancyPhase phase) {
		return SPRINTING_TIMERS.getInt(phase.compareTo(PregnancyPhase.P3) <= -1 ? PregnancyPhase.P3 : phase);
	}
	
	public static int sneakingTimer(PregnancyPhase phase) {
		return SNEAKING_TIMERS.getInt(phase.compareTo(PregnancyPhase.P4) <= -1 ? PregnancyPhase.P4 : phase);
	}
	// MOVEMENT NERFS IN PREGNANCY PHASES - END
	
	
	// PREGNANCY SYSTEM - START
	public static boolean tryToStartPregnancy(ServerPlayer player, boolean forceFather) {
		Optional<Boolean> gotPlayerPregnant = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().resolve()
				.map(femaleData -> {
					if (femaleData.isPregnant() && femaleData.getPrePregnancyData().isPresent()) {	
						var prePregnancyData = femaleData.getPrePregnancyData().get();
						
						final PregnancyPhase lastPregnancyStage;	
						final Species speciesOfFather = prePregnancyData.typeOfSpeciesOfFather();
						if (speciesOfFather == Species.DRAGON) {
							lastPregnancyStage = PregnancyPhase.P8;
						}
						else {
							lastPregnancyStage = PregnancySystemHelper.calculateMaxPregnancyPhaseByTotalNumOfBabies(prePregnancyData.fertilizedEggs());
						}
						
						final var totalDays = MinepreggoModConfig.SERVER.getTotalPregnancyDays();
						final var daysByStage = new MapPregnancyPhase(totalDays, lastPregnancyStage);
						final var pregnancySystem = femaleData.getPregnancyData();
						
						final Womb womb;
										
						if (forceFather) {
							womb = new Womb(
									player.getUUID(),
									ImmutableTriple.of(Optional.ofNullable(prePregnancyData.fatherId()), prePregnancyData.typeOfSpeciesOfFather(), prePregnancyData.typeOfCreatureOfFather()),
									player.getRandom(),
									prePregnancyData.fertilizedEggs());				
						}
						else {
							womb = new Womb(
									ImmutableTriple.of(player.getUUID(), Species.HUMAN, Creature.HUMANOID),
									ImmutableTriple.of(Optional.ofNullable(prePregnancyData.fatherId()), prePregnancyData.typeOfSpeciesOfFather(), prePregnancyData.typeOfCreatureOfFather()),
									player.getRandom(),
									prePregnancyData.fertilizedEggs());
						}
						
						pregnancySystem.setMapPregnancyPhase(daysByStage);
						pregnancySystem.setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
						pregnancySystem.setLastPregnancyStage(lastPregnancyStage);
						pregnancySystem.setDaysToGiveBirth(totalDays);			
						pregnancySystem.setWomb(womb);	
						pregnancySystem.setCurrentPregnancyPhase(PregnancyPhase.P0);	
									
						if (speciesOfFather == Species.DRAGON) {
							player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ENDER_DRAGON_PREGNANCY.get(), -1, 0, false, false, true));
						}
						
						player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_P0.get(), -1, 0, false, false, true));				
						
						PlayerHelper.updateJigglePhysics(player, cap.getSkinType(), PregnancyPhase.P0);
						
						femaleData.sync(player);
						pregnancySystem.syncState(player);	
									
						MinepreggoMod.LOGGER.debug("Player {} has become pregnant with {} babies, total days to give birth: {}, pregnancy phases days: {}, womb: {}", 
								player.getName().getString(), 
								prePregnancyData.fertilizedEggs(), 
								totalDays,
								daysByStage,
								womb);				
						
						MinepreggoModAdvancements.GET_PREGNANT_TRIGGER.trigger(player);

						return Boolean.TRUE;
					}
					return Boolean.FALSE;
				}));

		return gotPlayerPregnant.isPresent() && gotPlayerPregnant.get().booleanValue();
	}
	
	private static boolean tryToStartPrePregnancy(ServerPlayer player, PregnancyType pregnancyType, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, @Nonnegative int numOfBabies) {	
		Optional<Boolean> gotPlayerPregnant = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().resolve()
				.map(femaleData -> {
					if (!femaleData.isPregnant() && femaleData.getPostPregnancyData().isEmpty()) {						
						return femaleData.tryImpregnate(pregnancyType, numOfBabies, father);
					}
					return false;
				}));

		if (gotPlayerPregnant.isPresent() && gotPlayerPregnant.get().booleanValue()) {
			MinepreggoMod.LOGGER.debug("Player {} is now in pre-pregnancy state with {} babies from father: {}", 
					player.getGameProfile().getName(), 
					numOfBabies, 
					father);
			player.removeEffect(MinepreggoModMobEffects.FERTILE.get());
			return true;
		}

		return false;
	}
	
	public static boolean tryStartPregnancyBySex(ServerPlayer female, ServerPlayer male) {			
		final var femalePlayerData = female.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().resolve());
		
		final var malePlayerData = male.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getMaleData().resolve());
		
		if (femalePlayerData.isEmpty() || malePlayerData.isEmpty()) {
			MinepreggoMod.LOGGER.debug("Either female player {} or male player {} does not have valid player data", 
					female.getGameProfile().getName(), 
					male.getGameProfile().getName());
			return false;
		}
		
		final var numOfBabies = PregnancySystemHelper.calculateNumOfBabiesByFertility(malePlayerData.get().getFertilityRate(), femalePlayerData.get().getFertilityRate());
		
		if (numOfBabies == 0) {
			return false;
		}
			
		return tryToStartPrePregnancy(female, PregnancyType.SEX, ImmutableTriple.of(Optional.of(male.getUUID()), Species.HUMAN, Creature.HUMANOID), numOfBabies);
	}
	
	public static boolean tryStartPregnancyBySex(ServerPlayer female, Villager villager) {		
		Optional<Integer> numOfBabies = female.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().resolve())
				.map(femaleData -> {
					float femaleFertility = femaleData.getFertilityRate();
					float villagerFertility = female.hasEffect(MinepreggoModMobEffects.FERTILE.get()) ? IBreedable.MAX_FERTILITY_RATE : Math.min(0.3f + villager.getRandom().nextFloat(), IBreedable.MAX_FERTILITY_RATE);
					int babies = PregnancySystemHelper.calculateNumOfBabiesByFertility(villagerFertility, femaleFertility, Gender.FEMALE);
					
					MinepreggoMod.LOGGER.debug("Calculating number of babies for player {} and villager {} with fertilities {} and {} respectively, result: {}",
							female.getGameProfile().getName(),
							villager.getDisplayName().getString(),
							femaleFertility,
							villagerFertility,
							babies);
					
					return babies;	
				});
		
		if (numOfBabies.isEmpty() || numOfBabies.get() == 0) {
			MinepreggoMod.LOGGER.debug("Player {} could not get pregnant by villager {}", female.getGameProfile().getName(), villager.getDisplayName().getString());
			return false;
		}
				
		return tryToStartPrePregnancy(female, PregnancyType.SEX, ImmutableTriple.of(Optional.of(villager.getUUID()), Species.VILLAGER, Creature.HUMANOID), numOfBabies.get());
	}
	
	public static boolean tryStartPregnancyByPotion(ServerPlayer player, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, int amplifier) {			
		if (father.middle == Species.DRAGON) {
			return tryStartEnderDragonPregnancy(player);
		}

		if (tryToStartPrePregnancy(player, PregnancyType.POTION, father, PregnancySystemHelper.calculateNumOfBabiesByPotion(amplifier))) {
			return tryToStartPregnancy(player, true);
		}	
		return false;
	}
		
	public static boolean tryStartPregnancyByMobAttack(ServerPlayer female, Species species, Creature creature) {
		return tryToStartPrePregnancy(female, PregnancyType.MOB_ATTACK, ImmutableTriple.of(Optional.empty(), species, creature), female.getRandom().nextInt(1, PregnancySystemHelper.MAX_NUMBER_OF_BABIES + 1));
	}
	
	public static void removeHorny(ServerPlayer source) {
		source.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> {
				if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {								
					var pregnancyData = femaleData.getPregnancyData();					
					if (pregnancyData.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P4) >= 0) {
						pregnancyData.setHorny(0);
						pregnancyData.resetHornyTimer();
						pregnancyData.getPregnancySymptoms().removePregnancySymptom(PregnancySymptom.HORNY);
						source.removeEffect(MinepreggoModMobEffects.HORNY.get());
						pregnancyData.syncState(source);
						pregnancyData.syncEffect(source);
					}
				}
			})			
		);
	}	
	
	public static boolean tryStartEnderDragonPregnancy(ServerPlayer player) {			
		if (tryToStartPrePregnancy(player, PregnancyType.POTION, ImmutableTriple.of(Optional.empty(), Species.DRAGON, Creature.MONSTER), 1)) {
			return tryToStartPregnancy(player, true);
		}		
		return false;
	}
	
	public static @Nonnegative int calculateExplosionLevelByPregnancyPhase(PregnancyPhase phase) {
		int ordinal = phase.ordinal();
		int maxOrdinal = PregnancyPhase.values().length - 1;
		int level = (int) Math.round(ordinal * 6.0 / maxOrdinal);
		return Math.min(level, 6);
	}
	
	@CheckForNull
	public static Species addInterspeciesPregnancy(ServerPlayer serverPlayer) {	
		var result = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> cap.getFemaleData().map(FemalePlayerImpl::getPrePregnancyData)).orElse(Optional.empty()).orElse(Optional.empty());
			
		if (result.isPresent()) {
			var pre = result.get();	
			switch (pre.typeOfSpeciesOfFather()){
				case ZOMBIE: {			
					if (!serverPlayer.hasEffect(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get())) {
						serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get(), -1, 0, false, false, true));
						return Species.ZOMBIE;
					}
					break;
				}
				case CREEPER: {		
					if (!serverPlayer.hasEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get())) {
						serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FULL_OF_CREEPERS.get(), -1, 0, false, false, true));
						return Species.CREEPER;
					}
					break;
				}
				case ENDER: {	
					if (!serverPlayer.hasEffect(MinepreggoModMobEffects.FULL_OF_ENDERS.get())) {
						serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FULL_OF_ENDERS.get(), -1, 0, false, false, true));
						return Species.ENDER;
					}
					break;
				}
				case DRAGON: {
					if (!serverPlayer.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_PREGNANCY.get())) {
						serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ENDER_DRAGON_PREGNANCY.get(), -1, 0, false, false, true));
						return Species.DRAGON;
					}
					break;
				}
			default:
				break;
			}
		}
		
		return null;
	}
	
	public static boolean isValidCravingBySpecies(Species species) {
		return species == Species.HUMAN ||
				species == Species.ZOMBIE ||
				species == Species.CREEPER ||
				species == Species.ENDER ||
				species == Species.DRAGON;
	}	
	// PREGNANCY SYSTEM - END
	

	// ARMOR HELPERS - START
	public static boolean canUseChestPlateInLactation(LivingEntity target, Item armor) {	
		if (!target.hasEffect(MinepreggoModMobEffects.LACTATION.get())) {
			return true;
		}
		return armor instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed();
	}
	
	public static void removeAndDropItemStackFromEquipmentSlot(Player player, EquipmentSlot slotId) {
		if (EntityHelper.dropItemStack(player, player.getItemBySlot(slotId))) {
			player.setItemSlot(slotId, ItemStack.EMPTY);
		}
 	}

	public static void replaceAndDropItemstackInHand(ServerPlayer player, InteractionHand hand, ItemStack itemStack) {			
		var current = player.getItemInHand(hand);		
		if (!current.isEmpty()) {
			EntityHelper.dropItemStack(player, current);		
		}		
		player.setItemInHand(hand, itemStack);
	}
	// ARMOR HELPERS - END
	
	
	// PLAYER MOB EFFECT - START
	public static List<MobEffect> removeEffectsBeingPregnant(Player player, PregnancyPhase phase) {
		Predicate<MobEffect> predicate = phase.compareTo(PregnancyPhase.P2) >= 0
				? effect -> !PregnancySystemHelper.isPregnancyEffect(effect) && !PregnancySystemHelper.isSecondaryPregnancyEffect(effect)
				: effect -> !PregnancySystemHelper.isPregnancyEffect(effect);
					
		return LivingEntityHelper.removeEffects(player, predicate);
	}
	
	public static List<MobEffect> removeEffectsBeingPregnantOfEnderDragon(Player player, PregnancyPhase phase) {
		Predicate<MobEffect> predicate = phase.compareTo(PregnancyPhase.P2) >= 0
				? effect -> !EnderDragonPregnancy.isSecondaryEffect(effect, phase) && !PregnancySystemHelper.isPregnancyEffect(effect) && !PregnancySystemHelper.isSecondaryPregnancyEffect(effect)
				: effect -> !EnderDragonPregnancy.isSecondaryEffect(effect, phase) && !PregnancySystemHelper.isPregnancyEffect(effect);
					
		return LivingEntityHelper.removeEffects(player, predicate);
	}
	
	private static final ImmutableMap<PregnancyPhase, MobEffect> PREGNANCY_EFFECTS = ImmutableMap.of(
			PregnancyPhase.P0, MinepreggoModMobEffects.PREGNANCY_P0.get(),
			PregnancyPhase.P1, MinepreggoModMobEffects.PREGNANCY_P1.get(),
			PregnancyPhase.P2, MinepreggoModMobEffects.PREGNANCY_P2.get(),
			PregnancyPhase.P3, MinepreggoModMobEffects.PREGNANCY_P3.get(),
			PregnancyPhase.P4, MinepreggoModMobEffects.PREGNANCY_P4.get(),
			PregnancyPhase.P5, MinepreggoModMobEffects.PREGNANCY_P5.get(),
			PregnancyPhase.P6, MinepreggoModMobEffects.PREGNANCY_P6.get(),
			PregnancyPhase.P7, MinepreggoModMobEffects.PREGNANCY_P7.get(),
			PregnancyPhase.P8, MinepreggoModMobEffects.PREGNANCY_P8.get()
			);
	
	public static MobEffect getPregnancyEffects(PregnancyPhase phase) {
		return PREGNANCY_EFFECTS.get(phase);
	}	
	
	public static boolean addEnderDragonPregnancyEffects(ServerPlayer player) {	
		Optional<Boolean> added = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().resolve())
				.map(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						var pre = femaleData.getPrePregnancyData().orElse(null);
						if (pre != null && pre.typeOfSpeciesOfFather() == Species.DRAGON && !player.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_PREGNANCY.get())) {
							player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ENDER_DRAGON_PREGNANCY.get(), -1, 0, false, false, true));
							return Boolean.TRUE;
						}			
					}
					return Boolean.FALSE;
				});
		
		return added.orElse(Boolean.FALSE);
	}
	
	// PLAYER MOB EFFECT - END
	
	
	// ENDER DRAGON PREGNANCY - START
	
	private static final Object2IntMap<PregnancyPhase> ENDER_POWER_TIMERS = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P0, 150,
			PregnancyPhase.P1, 140,
			PregnancyPhase.P2, 130,
			PregnancyPhase.P3, 120,
			PregnancyPhase.P4, 100,
			PregnancyPhase.P5, 90,
			PregnancyPhase.P6, 80,
			PregnancyPhase.P7, 70,
			PregnancyPhase.P8, 60
			)));
	
	
	public static int getEnderPowerCoolDownBy(PregnancyPhase phase) {
		return ENDER_POWER_TIMERS.getOrDefault(phase, DEFAULT_ENDER_POWER_COOLDOWN);
	}
		
	// JIGGLE PHYSICS - START
	public static void removeJigglePhysics(ServerPlayer player) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), 
				new RemovePlayerJigglePhysicsS2CPacket(player.getUUID()));
	}
	
	public static void updateJigglePhysics(ServerPlayer player, SkinType skinType, @Nullable PregnancyPhase phase) {		
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), 
				new SyncJigglePhysicsS2CPacket(player.getUUID(), skinType, phase));
	}
	// JIGGLE PHYSICS - END
	
	
	// COMMON - START
	public static boolean isFemale(Player player) {
		Optional<Boolean> result = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.map(cap -> cap.getFemaleData().isPresent());
		return result.orElse(Boolean.FALSE);
	}
	
	public static boolean isMale(Player player) {
		Optional<Boolean> result = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.map(cap -> cap.getMaleData().isPresent());
		return result.orElse(Boolean.FALSE);
	}
	
	public static boolean isPregnant(ServerPlayer player) {
		return player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData()
				.map(femaleData -> femaleData.isPregnant() && femaleData.isPregnancyDataInitialized())).orElse(Boolean.FALSE);
	}
	
	public static boolean hasPregnancyPains(ServerPlayer player) {
		Optional<Boolean> result = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().map(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						return femaleData.getPregnancyData().getPregnancyPain() != null;
					}
					return false;
				}));
		return result.orElse(Boolean.FALSE);
	}
	
	public static boolean isPregnantAndIncapacitated(ServerPlayer player) {
		Optional<Boolean> result = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().map(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						var pregnancyPain = femaleData.getPregnancyData().getPregnancyPain();
						return pregnancyPain != null && pregnancyPain.incapacitate;
					}
					return false;
				}));
		return result.orElse(Boolean.FALSE);
	}
	
	public static boolean isPregnantAndInLabor(ServerPlayer player) {
		return player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().map(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						return PregnancyPain.isLaborPain(femaleData.getPregnancyData().getPregnancyPain());
					}
					return false;
				})).orElse(Boolean.FALSE);
	}
	
	
	public static boolean hasPregnancySymptoms(ServerPlayer player) {
		Optional<Boolean> result = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().map(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						return !femaleData.getPregnancyData().getPregnancySymptoms().isEmpty();
					}
					return false;
				}));
		return result.orElse(Boolean.FALSE);
	}
		
	public static Optional<PregnancyPhase> getCurrentPregnancyPhase(ServerPlayer player) {
		return player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().resolve().flatMap(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						return Optional.of(femaleData.getPregnancyData().getCurrentPregnancyPhase());
					}
					return Optional.empty();
					}));
	}
	
	
	public static boolean isInvencible(Player player) {
		return player.getAbilities().instabuild || player.isSpectator();
	}
	
	// COMMON - END
}
