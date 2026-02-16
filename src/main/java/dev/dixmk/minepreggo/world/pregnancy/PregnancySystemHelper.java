package dev.dixmk.minepreggo.world.pregnancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.network.packet.s2c.RemoveMobEffectS2CPacket;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.BabyItem;
import dev.dixmk.minepreggo.world.item.IFemaleArmor;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.item.KneeBraceItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

public class PregnancySystemHelper {	

	private PregnancySystemHelper() {}
	
	public static final RandomSource RANDOM_SOURCE = RandomSource.create();
	
	// PREGNANCY CONSTANTS - START
	public static final int MAX_PREGNANCY_HEALTH = 100;
	public static final int MAX_PREGNANCY_FERTILITY = 100;
	public static final int MAX_CRAVING_LEVEL = 20;
	public static final int MAX_MILKING_LEVEL = 20;
	public static final int MAX_BELLY_RUBBING_LEVEL = 20;
	public static final int MAX_HORNY_LEVEL = 20;
	
	public static final int DEFAULT_TOTAL_PREGNANCY_DAYS = 70;
	
	public static final int TOTAL_TICKS_MISCARRIAGE = 800;
	public static final int TOTAL_TICKS_MORNING_SICKNESS = 600;
	public static final int TOTAL_TICKS_WATER_BREAKING = 1200;	
	
	public static final int TOTAL_TICKS_PREBIRTH_P4 = 600;
	public static final int TOTAL_TICKS_PREBIRTH_P5 = 800;
	public static final int TOTAL_TICKS_PREBIRTH_P6 = 900;
	public static final int TOTAL_TICKS_PREBIRTH_P7 = 1000;
	public static final int TOTAL_TICKS_PREBIRTH_P8 = 1100;
	
	public static final int TOTAL_TICKS_BIRTH_P4 = 1200;
	public static final int TOTAL_TICKS_BIRTH_P5 = 1300;
	public static final int TOTAL_TICKS_BIRTH_P6 = 1400;
	public static final int TOTAL_TICKS_BIRTH_P7 = 1500;
	public static final int TOTAL_TICKS_BIRTH_P8 = 1600;
	
	public static final int TOTAL_TICKS_KICKING_P3 = 1200;
	public static final int TOTAL_TICKS_KICKING_P4 = 1400;
	public static final int TOTAL_TICKS_KICKING_P5 = 1600;
	public static final int TOTAL_TICKS_KICKING_P6 = 1800;
	public static final int TOTAL_TICKS_KICKING_P7 = 2000;
	public static final int TOTAL_TICKS_KICKING_P8 = 2200;
	
	public static final int TOTAL_TICKS_CONTRACTION_P4 = 800;
	public static final int TOTAL_TICKS_CONTRACTION_P5 = 1000;
	public static final int TOTAL_TICKS_CONTRACTION_P6 = 1200;
	public static final int TOTAL_TICKS_CONTRACTION_P7 = 1400;
	public static final int TOTAL_TICKS_CONTRACTION_P8 = 1600;
	
	public static final int TOTAL_TICKS_FERTILITY_RATE = 4800;
	
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P0 = 2400;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P1 = 2300;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P2 = 2200;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P3 = 2100;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P4 = 2000;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P5 = 1900;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P6 = 1800;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P7 = 1700;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P8 = 1600;
	
	public static final int TOTAL_TICKS_CALM_BELLY_RUGGING_DOWN = 120;
	
	public static final float LOW_PREGNANCY_PAIN_PROBABILITY = 0.001F;
	public static final float MEDIUM_PREGNANCY_PAIN_PROBABILITY = 0.00125F;
	public static final float HIGH_PREGNANCY_PAIN_PROBABILITY = 0.0015F;
	
	public static final float LOW_MORNING_SICKNESS_PROBABILITY = 0.001F;
	public static final float MEDIUM_MORNING_SICKNESS_PROBABILITY = 0.0015F;
	public static final float HIGH_MORNING_SICKNESS_PROBABILITY = 0.002F;
	
	public static final float LOW_ANGER_PROBABILITY = 0.05F;
	public static final float MEDIUM_ANGER_PROBABILITY = 0.075F;
	public static final float HIGH_ANGER_PROBABILITY = 0.1F;
	
	public static final int CRAVING_VALUE = 4;
	public static final int ACTIVATE_CRAVING_SYMPTOM = 16;
	public static final int DESACTIVATE_CRAVING_SYMPTOM = 2;
	
	public static final int MILKING_VALUE = 5;
	public static final int ACTIVATE_MILKING_SYMPTOM = 12;
	public static final int DESACTIVATE_MILKING_SYMPTOM = 8;
	
	public static final int BELLY_RUBBING_VALUE = 4;
	public static final int ACTIVATE_BELLY_RUBS_SYMPTOM = 12;
	public static final int DESACTIVATEL_BELLY_RUBS_SYMPTOM = 8;
	
	public static final int ACTIVATE_HORNY_SYMPTOM = 17;
	// PREGNANCY CONSTANTS - END
	
	
	// POST PREGNANCY ATTRIBUTE NERFS - START
	private static final UUID SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("fa6a4626-c325-4835-8259-69577a99c9c8");
	private static final AttributeModifier SPEED_MODIFIER_TIRENESS = new AttributeModifier(SPEED_MODIFIER_TIRENESS_UUID, "Tireness speed boost", -0.15D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final UUID MAX_HEALTH_MODIFIER_TIRENESS_UUID = UUID.fromString("94d78c8b-0983-4ae4-af65-8e477ee52f2e");
	private static final AttributeModifier MAX_HEALTH_MODIFIER_TIRENESS = new AttributeModifier(MAX_HEALTH_MODIFIER_TIRENESS_UUID, "Tireness max health", -0.25D, AttributeModifier.Operation.MULTIPLY_BASE);

	public static void applyPostPregnancyNerf(LivingEntity entity) {
		AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealthAttr = entity.getAttribute(Attributes.MAX_HEALTH);			

		if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_TIRENESS_UUID) == null) {
		    speedAttr.addPermanentModifier(SPEED_MODIFIER_TIRENESS);
		}			
		if (maxHealthAttr != null && maxHealthAttr.getModifier(MAX_HEALTH_MODIFIER_TIRENESS_UUID) == null) {
			maxHealthAttr.addPermanentModifier(MAX_HEALTH_MODIFIER_TIRENESS);
		}	
	}
	
	public static void removePostPregnancyNeft(LivingEntity entity) {
		AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealthAttr = entity.getAttribute(Attributes.MAX_HEALTH);	

		if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_TIRENESS_UUID) != null) {
			speedAttr.removeModifier(SPEED_MODIFIER_TIRENESS);
		}	
		if (maxHealthAttr != null && maxHealthAttr.getModifier(MAX_HEALTH_MODIFIER_TIRENESS_UUID) != null) {
			maxHealthAttr.removeModifier(MAX_HEALTH_MODIFIER_TIRENESS);
		}
	}
	// POST PREGNANCY ATTRIBUTE NERFS - END
	
	
	// PREGNANCY ATTRIBUTE MODIFIERS - START
	private static final UUID GRAVITY_MODIFIER_UUID = UUID.fromString("b5b377cf-2343-4d56-945f-6ffc5566ee8c");
	private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_UUID = UUID.fromString("c42b03bc-a474-4309-84f6-49e765f278e6");
	
	private static final ImmutableMap<PregnancyPhase, AttributeModifier> GRAVITY_MODIFIER_MAP = ImmutableMap.of(
			PregnancyPhase.P2, new AttributeModifier(GRAVITY_MODIFIER_UUID, "extra weight p2", 0.1D, AttributeModifier.Operation.MULTIPLY_BASE),
			PregnancyPhase.P3, new AttributeModifier(GRAVITY_MODIFIER_UUID, "extra weight p3", 0.125D, AttributeModifier.Operation.MULTIPLY_BASE),
			PregnancyPhase.P4, new AttributeModifier(GRAVITY_MODIFIER_UUID, "extra weight p4", 0.15D, AttributeModifier.Operation.MULTIPLY_BASE),
			PregnancyPhase.P5, new AttributeModifier(GRAVITY_MODIFIER_UUID, "extra weight p5", 0.175D, AttributeModifier.Operation.MULTIPLY_BASE),
			PregnancyPhase.P6, new AttributeModifier(GRAVITY_MODIFIER_UUID, "extra weight p6", 0.2D, AttributeModifier.Operation.MULTIPLY_BASE),
			PregnancyPhase.P7, new AttributeModifier(GRAVITY_MODIFIER_UUID, "extra weight p7", 0.225D, AttributeModifier.Operation.MULTIPLY_BASE),
			PregnancyPhase.P8, new AttributeModifier(GRAVITY_MODIFIER_UUID, "extra weight p8", 0.25D, AttributeModifier.Operation.MULTIPLY_BASE)
	);
	
	private static final ImmutableMap<PregnancyPhase, AttributeModifier> KNOCKBACK_RESISTANCE_MAP = ImmutableMap.of(
			PregnancyPhase.P4, new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "buff knockback resistance p4", 0.05D, AttributeModifier.Operation.ADDITION),
			PregnancyPhase.P5, new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "buff knockback resistance p5", 0.1D, AttributeModifier.Operation.ADDITION),
			PregnancyPhase.P6, new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "buff knockback resistance p6", 0.15D, AttributeModifier.Operation.ADDITION),
			PregnancyPhase.P7, new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "buff knockback resistance p7", 0.2D, AttributeModifier.Operation.ADDITION),
			PregnancyPhase.P8, new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "buff knockback resistance p8", 0.25D, AttributeModifier.Operation.ADDITION)
	);
	
	public static boolean applyGravityModifier(LivingEntity entity, PregnancyPhase pregnancyPhase) {
		AttributeInstance gravityAttr = entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
		if (gravityAttr != null) {
			AttributeModifier gravityModifier = GRAVITY_MODIFIER_MAP.get(pregnancyPhase);
			if (gravityModifier != null && gravityAttr.getModifier(GRAVITY_MODIFIER_UUID) == null) {
			    gravityAttr.addPermanentModifier(gravityModifier);
			    return true;
			}
		}			
		return false;
	}
	
	public static boolean removeGravityModifier(LivingEntity entity) {
		AttributeInstance gravityAttr = entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
		if (gravityAttr != null && gravityAttr.getModifier(GRAVITY_MODIFIER_UUID) != null) {
			gravityAttr.removeModifier(GRAVITY_MODIFIER_UUID);
			return true;
		}	
		return false;
	}
	
	public static boolean applyKnockbackResistanceModifier(LivingEntity entity, PregnancyPhase pregnancyPhase) {
		AttributeInstance knockbackResistAttr = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
		if (knockbackResistAttr != null) {
			AttributeModifier knockbackResistModifier = KNOCKBACK_RESISTANCE_MAP.get(pregnancyPhase);
			if (knockbackResistModifier != null && knockbackResistAttr.getModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID) == null) {
				MinepreggoMod.LOGGER.debug("Applying knockback resistance modifier: {} to entity: {}", knockbackResistModifier.getAmount(), entity.getId());
				knockbackResistAttr.addPermanentModifier(knockbackResistModifier);
			    return true;
			}
		}	
		
		MinepreggoMod.LOGGER.debug("Failed to apply knockback resistance modifier to entity: {}", entity.getId());
		return false;
	}
	
	public static boolean removeKnockbackResistanceModifier(LivingEntity entity) {
		AttributeInstance knockbackResistAttr = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
		if (knockbackResistAttr != null && knockbackResistAttr.getModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID) != null) {
			knockbackResistAttr.removeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID);
			return true;
		}	
		return false;
	}
	// PREGNANCY ATTRIBUTE MODIFIERS - END
	
	
	// ITEMS USED DURING PREGNANCY - START
	private static final ImmutableMap<Species, Item> MILK_ITEM = ImmutableMap.of(
			Species.CREEPER, MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(),
			Species.ZOMBIE, MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(),
			Species.HUMAN, MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get(),
			Species.ENDER, MinepreggoModItems.ENDER_BREAST_MILK_BOTTLE.get()
	);
		
	private static final ImmutableMap<Species, ImmutableMap<Craving, List<Item>>> CRAVING_ITEM = ImmutableMap.of(
			Species.CREEPER, ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_SALT.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_CHOCOLATE.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.SOUR_ACTIVATED_GUNPOWDER.get()),
					Craving.SPICY, List.of(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_HOT_SAUCE.get())),	
			Species.ZOMBIE, ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.BRAIN_WITH_SALT.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.BRAIN_WITH_CHOCOLATE.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.SOUR_BRAIN.get()),
					Craving.SPICY, List.of(MinepreggoModItems.BRAIN_WITH_HOT_SAUCE.get())),
			Species.ENDER, ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.ENDER_SLIME_JELLY_WITH_SALT.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.ENDER_SLIME_JELLY_WITH_CHOCOLATE.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.SOUR_ENDER_SLIME_JELLY.get()),
					Craving.SPICY, List.of(MinepreggoModItems.ENDER_SLIME_JELLY_WITH_HOT_SAUCE.get())),
			Species.DRAGON, ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.CHORUS_FRUIT_WITH_SALT.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.CHORUS_FRUIT_WITH_CHOCOLATE.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.SOUR_CHORUS_FRUIT.get()),
					Craving.SPICY, List.of(MinepreggoModItems.CHORUS_FRUIT_WITH_HOT_SAUCE.get())),
			Species.HUMAN,	ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.PICKLE.get(), MinepreggoModItems.FRENCH_FRIES.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.CHOCOLATE_BAR.get(), MinepreggoModItems.CANDY_APPLE.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.LEMON_ICE_POPSICLES.get(), MinepreggoModItems.LEMON_ICE_CREAM.get(), MinepreggoModItems.LEMON_DROP.get()),
					Craving.SPICY, List.of(MinepreggoModItems.HOT_CHICKEN.get(), MinepreggoModItems.CHILI_POPPERS.get()))	
	);
	
	private static final Table<Species, Creature, Item> ALIVE_BABIES = ImmutableTable.<Species, Creature, Item>builder()
			.put(Species.HUMAN, Creature.HUMANOID, MinepreggoModItems.BABY_HUMAN.get())
			.put(Species.ZOMBIE, Creature.HUMANOID, MinepreggoModItems.BABY_ZOMBIE.get())
			.put(Species.CREEPER, Creature.HUMANOID, MinepreggoModItems.BABY_HUMANOID_CREEPER.get())
			.put(Species.CREEPER, Creature.MONSTER, MinepreggoModItems.BABY_CREEPER.get())
			.put(Species.ENDER, Creature.MONSTER, MinepreggoModItems.BABY_ENDER.get())
			.put(Species.ENDER, Creature.HUMANOID, MinepreggoModItems.BABY_HUMANOID_ENDER.get())
			.put(Species.VILLAGER, Creature.HUMANOID, MinepreggoModItems.BABY_VILLAGER.get())
			.put(Species.DRAGON, Creature.MONSTER, MinepreggoModItems.BABY_ENDER_DRAGON_BLOCK.get())
			.build();

	private static final Table<Species, Creature, Item> DEAD_BABIES = ImmutableTable.<Species, Creature, Item>builder()
			.put(Species.HUMAN, Creature.HUMANOID, MinepreggoModItems.DEAD_HUMAN_FETUS.get())
			.put(Species.ZOMBIE, Creature.HUMANOID, MinepreggoModItems.DEAD_ZOMBIE_FETUS.get())
			.put(Species.CREEPER, Creature.HUMANOID, MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get())
			.put(Species.CREEPER, Creature.MONSTER, MinepreggoModItems.DEAD_CREEPER_FETUS.get())
			.put(Species.ENDER, Creature.MONSTER, MinepreggoModItems.DEAD_ENDER_FETUS.get())
			.put(Species.ENDER, Creature.HUMANOID, MinepreggoModItems.DEAD_HUMANOID_ENDER_FETUS.get())
			.put(Species.VILLAGER, Creature.HUMANOID, MinepreggoModItems.DEAD_VILLAGER_FETUS.get())
			.build();

	private static final ImmutableMap<Craving, Float> CRAVING_WEIGHTS = ImmutableMap.of(		
			Craving.SWEET, 0.075F,
			Craving.SOUR, 0.275F,
			Craving.SPICY, 0.3F,
			Craving.SALTY, 0.35F
			);
	
	@CheckForNull
	public static ImmutableMap<Craving, List<Item>> getCravingMap(Species species) {
		return species != null ? CRAVING_ITEM.get(species) : null;
	}
	
	@CheckForNull
	public static List<Item> getCravingItems(Species species, Craving craving) {
		if (species == null || craving == null) {
			return null;
		}
		final var cravingMap = CRAVING_ITEM.get(species);
		return cravingMap != null ? cravingMap.get(craving) : null;
	}
	
	@CheckForNull
	public static Item getMilkItem(Species species) {
		if (species == null) {
			return null;
		}
		return MILK_ITEM.get(species);
	}

	@CheckForNull
	public static Item getDeadBabyItem(Species species, Creature creature) {
		if (species == null || creature == null) {
			return null;
		}		
		return DEAD_BABIES.get(species, creature);
	}
	
	@CheckForNull
	public static Item getAliveBabyItem(Species species, Creature creature) {
		if (species == null || creature == null) {
			return null;
		}	
		return ALIVE_BABIES.get(species, creature);
	}

    
    /**
     * Returns a list of ItemStacks representing the alive babies in the womb. Each ItemStack is created based on the baby data from the womb, using the getAliveBabyItem method to determine the appropriate item for each baby. If a baby does not have a valid item, it is skipped and not included in the resulting list.
     * @param womb The Womb object containing the baby data to be processed.
     * @return a mutable list of ItemStacks representing the alive babies in the womb. The list will only include ItemStacks for babies that have a valid item associated with their species and creature type. If no babies have valid items, the resulting list will be empty.
     */    
	public static List<ItemStack> getAliveBabies(@NonNull Womb womb) {
		return womb.stream()
				.map(babyData -> {
				Item babyItem = getAliveBabyItem(babyData.typeOfSpecies, babyData.typeOfCreature);
				if (babyItem != null) {				
					return BabyItem.createBabyItemStack(babyData.motherId, babyData.fatherId.orElse(null), babyItem);
				}
				return ItemStack.EMPTY;
				})
				.filter(i -> !i.isEmpty())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Returns a list of ItemStacks representing the dead babies in the womb. Each ItemStack is created based on the baby data from the womb, using the getDeadBabyItem method to determine the appropriate item for each baby. If a baby does not have a valid item, it is skipped and not included in the resulting list.
	 * @param womb The Womb object containing the baby data to be processed.
	 * @return a mutable list of ItemStacks representing the dead babies in the womb. The list will only include ItemStacks for babies that have a valid item associated with their species and creature type. If no babies have valid items, the resulting list will be empty.
	 */
	public static List<ItemStack> getDeadBabies(@NonNull Womb womb) {
		return womb.stream()
				.map(babyData -> {
				Item babyItem = getDeadBabyItem(babyData.typeOfSpecies, babyData.typeOfCreature);
				if (babyItem != null) {
					return new ItemStack(babyItem);
				}
				return ItemStack.EMPTY;
				})
				.filter(i -> !i.isEmpty())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static Craving getRandomCraving(RandomSource randomSource) {
		final float random = randomSource.nextFloat();
		float cumulativeProbability = 0.0F;
		
		// Iterate through weights and select based on cumulative probability
		for (final var entry : CRAVING_WEIGHTS.entrySet()) {
			cumulativeProbability += entry.getValue();
			if (random < cumulativeProbability) {
				return entry.getKey();
			}
		}

		// Fallback: This should never happen if weights sum to 1.0
		MinepreggoMod.LOGGER.warn("PregnancySystemHelper.getRandomCraving: Weights do not sum to 1.0 (sum: {}). Returning fallback craving.", cumulativeProbability);	
		return Craving.SALTY;
	}
	// ITEMS USED DURING PREGNANCY - END
	
	
	// PREGNANCY CHECK HELPERS - START
	public static boolean isPregnantEntityValid(LivingEntity entity) {	
		if (entity instanceof ServerPlayer serverPlayer) {		
			Optional<Boolean> isPregnantOpt = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
					                          .resolve()
					                          .flatMap(cap -> cap.getFemaleData().resolve())
					                          .map(IFemaleEntity::isPregnant);
			if (isPregnantOpt.isPresent()) {
				return isPregnantOpt.get().booleanValue();
			}		
		}

		return entity instanceof PreggoMob p && p instanceof ITamablePregnantPreggoMob;
	}
	
	public static boolean isInPostPregnancyPhase(LivingEntity entity) {	
		
		if (entity instanceof ServerPlayer serverPlayer) {	
			
			Optional<Boolean> isInPostPrenancyPhase = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
										      .resolve()
										      .flatMap(cap -> cap.getFemaleData().resolve())
										      .map(femaleData -> femaleData.getPostPregnancyData().isPresent());		
			
			if (isInPostPrenancyPhase.isPresent()) {
				return isInPostPrenancyPhase.get().booleanValue();
			}
		}
		else if (entity instanceof IFemaleEntity femaleEntity) {
			return femaleEntity.getPostPregnancyData().isPresent();
		}

		return false;
	}
	
	public static boolean canHavePregnancyEffects(LivingEntity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {          	
    		Optional<Boolean> result = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> {	
    			var femaleDataOpt = cap.getFemaleData().resolve();
    			if (femaleDataOpt.isPresent()) {
    				var femaleData = femaleDataOpt.get();				
    				return femaleData.isPregnant() && femaleData.isPregnancyDataInitialized();
    			}
    			return false;
    		});		
    		return result.isPresent() && result.get().booleanValue();
        }
		
		return entity instanceof ITamablePregnantPreggoMob;
	}
	
    
	public static boolean canMountEntity(PregnancyPhase pregnancyPhase) {
		return pregnancyPhase.compareTo(PregnancyPhase.P5) <= 0;
	}	
	// PREGNANCY CHECK HELPERS - END
	
	
	// MOB EFFECT HELPERS - START
    public static boolean isPregnancyEffect(MobEffect effect) { 	
    	Optional<Holder<MobEffect>> holder = ForgeRegistries.MOB_EFFECTS.getHolder(effect);
        return holder.isPresent() && holder.get().is(TagHelper.PREGNANCY_EFFECTS);
    }
    
    public static boolean isSecondaryPregnancyEffect(MobEffect effect) { 	
    	Optional<Holder<MobEffect>> holder = ForgeRegistries.MOB_EFFECTS.getHolder(effect);
    	return holder.isPresent() && holder.get().is(TagHelper.SECONDARY_PREGNANCY_EFFECTS);
    }
    
    public static boolean isFemaleEffect(MobEffect effect) { 	
    	Optional<Holder<MobEffect>> holder = ForgeRegistries.MOB_EFFECTS.getHolder(effect);
    	return holder.isPresent() && holder.get().is(TagHelper.FEMALE_EFFECTS);
    }
    
	public static void syncExpiredPregnancyMobEffectsToTracker(LivingEntity entity, ServerPlayer trackerPlayer) {
		if (!(entity instanceof ServerPlayer trackedPlayer)) {
			return;
		}
		
		// Get all known pregnancy effects from the registry
		StreamSupport.stream(ForgeRegistries.MOB_EFFECTS.spliterator(), false)
			.filter(effect -> isPregnancyEffect(effect))
			.forEach(effect -> {
				// If the tracked player doesn't have this effect, sync its removal
				if (!trackedPlayer.hasEffect(effect)) {
					MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> trackerPlayer),
							new RemoveMobEffectS2CPacket(trackedPlayer.getId(), effect));
				}
			});
	}
	// MOB EFFECT HELPERS - END
	
	
	// ARMOR USAGE DURING PREGNANCY - START
	public static boolean canUseChestplate(Item armor, PregnancyPhase pregnancyPhase) {
		return canUseChestplate(armor, pregnancyPhase, false);
	}
	
	public static boolean canUseChestplate(Item armor, PregnancyPhase pregnancyPhase, boolean considerBoobs) {
		if (!ItemHelper.isChest(armor)) {
			return false;
		}		
		else if (pregnancyPhase == PregnancyPhase.P0) {		
			if (considerBoobs) {
				return armor instanceof IFemaleArmor;
			}		
			return true;
		}		
		return armor instanceof IMaternityArmor maternityArmor && pregnancyPhase.compareTo(maternityArmor.getMinPregnancyPhaseAllowed()) <= 0;
	}

	public static boolean canUseLegging(Item armor, PregnancyPhase pregnancyPhase) {	
		if (!ItemHelper.isLegging(armor)) {
			return false;
		}		
		else if (armor instanceof KneeBraceItem) {
			return true;
		}		

		return pregnancyPhase == PregnancyPhase.P0;
	}
	  
	public static boolean shouldBoobsBeHidden(Item armor) {
		return !(armor instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed());
	}
    
	// ARMOR USAGE DURING PREGNANCY - END
	

	
	// PREGNANCY CALCULATES - START
	public static PregnancyPhase calculateMinPhaseToGiveBirth(PregnancyPhase currentPregnancyStage) {
		if (currentPregnancyStage.compareTo(PregnancyPhase.P5) >= 0) {
			return PregnancyPhase.values()[Math.min(currentPregnancyStage.ordinal(), PregnancyPhase.values().length)];
		}	
		return PregnancyPhase.P4;
	}
	
	public static PregnancyPhase calculateRandomMinPhaseToGiveBirthFrom(PregnancyPhase currentPregnancyStage, RandomSource randomSource) {		
		int ordinal = currentPregnancyStage.ordinal();
		if (currentPregnancyStage.compareTo(PregnancyPhase.P4) < 0) {
			ordinal = PregnancyPhase.P4.ordinal();
		}	
		return PregnancyPhase.values()[randomSource.nextInt(ordinal, PregnancyPhase.values().length)];	
    }
		
    public static int calculateDaysToGiveBirth(@NonNull ITamablePregnantPreggoMobData h) {
        return h.getTotalDaysOfPregnancy() - calculateTotalDaysPassedFromPhaseP0(h);
    }
    
    public static int calculateTotalDaysPassedFromPhaseP0(@NonNull IPregnancyData h) {   	
    	var map = h.getMapPregnancyPhase();
    	final PregnancyPhase currentPhase = h.getCurrentPregnancyPhase();
    	
    	final var totalDaysPassed = StreamSupport.stream(Arrays.spliterator(PregnancyPhase.values()), false)
    			.filter(phase -> phase.compareTo(currentPhase) <= -1)
    			.mapToInt(phase -> {
    				final var days = map.getDaysByPregnancyPhase(phase);
    				if (days == 0) {
    					MinepreggoMod.LOGGER.warn("PregnancySystemHelper.calculateTotalDaysPassedFromPhaseP0: Missing days for pregnancy phase {}", phase.name());
    					return 0;
    				}	
    				return days;
    			})
    			.sum();
    	
    	return totalDaysPassed + h.getDaysPassed();
    } 
    
    public static int calculateDaysToNextPhase(@NonNull IPregnancyData h) {
		return h.getDaysByCurrentStage() - h.getDaysPassed();
    }  
    
    public static OptionalInt calculatePregnancyDamage(LivingEntity pregnantEntity, PregnancyPhase phase, DamageSource damagesource) {
    	if (phase == PregnancyPhase.P0 || damagesource.is(MinepreggoModDamageSources.PREGNANCY_PAIN)) {
    		return OptionalInt.empty();
    	}
    	
    	RandomSource randomSource = pregnantEntity.getRandom();

		if (pregnantEntity.hasEffect(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get())
				|| (!damagesource.is(DamageTypes.FALL) && !pregnantEntity.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && randomSource.nextFloat() < 0.7)) {
			return OptionalInt.empty();
		}
		int damage = 0;
		
		if (pregnantEntity.getHealth() < pregnantEntity.getMaxHealth() * 0.5f) {
			damage = phase.ordinal() + randomSource.nextInt(3);
		} 	
		
		if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
			damage = damage == 0 ? 7 : damage * 2;
		}	
				
		return OptionalInt.of(damage);
    }
    
    public static float calculateSpawnProbabilityBasedPregnancy(IPregnancyData handler, float t0, float k, float pMin, float pMax) {
		final int totalDays = handler.getTotalDaysOfPregnancy();
		final int totalDaysPassed = PregnancySystemHelper.calculateTotalDaysPassedFromPhaseP0(handler);
		
		final float t = Mth.clamp(totalDaysPassed / (float) totalDays, 0, 1);	
		final float p = MathHelper.sigmoid(pMin, pMax, k, t, t0);
		
		MinepreggoMod.LOGGER.debug("SPAWN PROBABILITY BASED IN PREGNANCY: class={}, totalDays={}, totalDaysPassed={}, t={}, p={}",
				handler.getClass().getSimpleName(), totalDays, totalDaysPassed, t, p);
		
		return p;
    }

	public static float calculateExtraFallDamageMultiplier(PregnancyPhase pregnancyPhase) {
		return MathHelper.sigmoid(1f, 1.6f, 1f, pregnancyPhase.ordinal(), PregnancyPhase.values().length * 0.5f);
	}
    // PREGNANCY CALCULATES - END

  
    // BELLY RUBBING AND SLAPPING - START
	public static boolean canTouchBelly(Player source, LivingEntity target) {
		return source.isShiftKeyDown()
				&& LivingEntityHelper.isEyeAboveEntity(source, target, 0.5f)
				&& source.getMainHandItem().isEmpty() 
				&& source.getDirection() == target.getDirection().getOpposite()
				&& target.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
	}
	
	
	public static boolean tryRubBelly(ServerPlayer source, ServerPlayer target, Level level) {
		Optional<Boolean> canRubBellyOpt = target.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getFemaleData().resolve())
				.map(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized() && canTouchBelly(source, target)) {		
						if (!level.isClientSide) {
											
							LivingEntityHelper.playSoundNearTo(target, MinepreggoModSounds.BELLY_TOUCH.get(), 0.6f);
							
							final var pregnancySystem = femaleData.getPregnancyData();
							final var isFather = femaleData.getPrePregnancyData().isPresent() && femaleData.getPrePregnancyData().get().fatherId().equals(source.getUUID());
							
							
							ParticleOptions particle = isFather ? ParticleTypes.HEART : ParticleTypes.SMOKE;

							playSlappingBellyAnimation(source, target);
									
							if (pregnancySystem.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P3) >= 0) {				
								var pregnancyData = femaleData.getPregnancyData();
								
								if (pregnancyData.getBellyRubs() >= BELLY_RUBBING_VALUE) {
									pregnancyData.decrementBellyRubs(isFather ? BELLY_RUBBING_VALUE : 1);
									pregnancyData.syncEffect(target);			
								}
								else {
									particle = ParticleTypes.ANGRY_VILLAGER;
								}
							}
						
							ServerParticleHelper.spawnRandomlyFromServer(target, particle);
						}
						return true;			
					}
					return false;
				});

		return canRubBellyOpt.isPresent() && canRubBellyOpt.get().booleanValue();
	}
      
    public enum HorizontalPosition {
        LEFT, RIGHT, CENTER
    }

    public static HorizontalPosition getHorizontalPosition(LivingEntity reference, LivingEntity target, double tolerance) {
        // Get horizontal look vector (ignoring pitch)
        Vec3 look = reference.getLookAngle();
        Vec3 flatLook = new Vec3(look.x, 0, look.z).normalize();

        // Compute relative offset from reference to target
        Vec3 offset = new Vec3(
            target.getX() - reference.getX(),
            0,
            target.getZ() - reference.getZ()
        ).normalize();

        // Avoid NaNs if offset is zero-length
        if (offset.lengthSqr() < 1e-6) {
            return HorizontalPosition.CENTER;
        }

        // Cross product in 2D: (a.x * b.z - a.z * b.x)
        // This gives signed magnitude: >0 = left, <0 = right
        double cross = flatLook.x * offset.z - flatLook.z * offset.x;

        if (cross > tolerance) {
            return HorizontalPosition.LEFT;
        } else if (cross < -tolerance) {
            return HorizontalPosition.RIGHT;
        } else {
            return HorizontalPosition.CENTER;
        }
    }
    
    public static void playSlappingBellyAnimation(LivingEntity source, LivingEntity target) {
		final HorizontalPosition position = getHorizontalPosition(source, target, 0.1);
		byte id;	
		// TODO: Use contant values for ids, try to link with animation system later
		if (position == HorizontalPosition.LEFT) {
			id = 102;
		}
		else if (position == HorizontalPosition.CENTER) {
			id = 101;
		}
		else {
			id = 100;
		}		
		target.level().broadcastEntityEvent(target, id);
    }
    

    
    // BELLY RUBBING AND SLAPPING - START

 
	// BABY HELPERS - START
	public static final int MAX_NUMBER_OF_BABIES = 8;
	
	private static final ImmutableMap<PregnancyPhase, int[]> PHASE_TO_BABY_COUNT_MAP = ImmutableMap.of(
	   	PregnancyPhase.P4, new int[] {1},
	   	PregnancyPhase.P5, new int[] {2,3},
	   	PregnancyPhase.P6, new int[] {4,5},
	   	PregnancyPhase.P7, new int[] {6,7},
	   	PregnancyPhase.P8, new int[] {8}
	   );

	@CheckForNull
	public static int[] getPossibleNumOfBabiesForPhase(PregnancyPhase phase) {
		return PHASE_TO_BABY_COUNT_MAP.get(phase);
	}
	
	public static PregnancyPhase getMinPhaseForNumOfBabies(int numOfBabies) {
		for (var pair : PHASE_TO_BABY_COUNT_MAP.entrySet()) {
			int[] range = pair.getValue();     
			if ((range.length == 1 && numOfBabies == range[0]) || (range.length == 2 && numOfBabies >= range[0] && numOfBabies <= range[1])) {
				return pair.getKey();
			}
		}
		return PregnancyPhase.P8;
	}

	public static int calculateNumOfBabiesByPotion(@Nonnegative int amplifier) {
		int[] numOfBabies;
		switch (Math.abs(amplifier)) {
			case 0:
				numOfBabies = PHASE_TO_BABY_COUNT_MAP.get(PregnancyPhase.P4); break;
			case 1:
				numOfBabies = PHASE_TO_BABY_COUNT_MAP.get(PregnancyPhase.P5); break;
			case 2:
				numOfBabies = PHASE_TO_BABY_COUNT_MAP.get(PregnancyPhase.P6); break;
			case 3:
              	numOfBabies = PHASE_TO_BABY_COUNT_MAP.get(PregnancyPhase.P7); break;
			default:
				numOfBabies = PHASE_TO_BABY_COUNT_MAP.get(PregnancyPhase.P8); break;
       }
       
       if (numOfBabies == null) {
		   return 1;
	   }
       
       return numOfBabies[RANDOM_SOURCE.nextInt(numOfBabies.length)];
	}

	public static int calculateNumOfBabiesByFertility(@Nonnegative float maleFertility, @Nonnegative float femaleFertility) {
		float averageFertility = (maleFertility + femaleFertility) * 0.5f;
		int numOfBabies = Math.round(averageFertility / IBreedable.MAX_FERTILITY_RATE * MAX_NUMBER_OF_BABIES);
		return Mth.clamp(numOfBabies, 0, MAX_NUMBER_OF_BABIES);
	}
	
	public static int calculateNumOfBabiesByFertility(@Nonnegative float maleFertility, @Nonnegative float femaleFertility, Gender dominantFertilityGender) {
		float dominantWeight = 0.7f;
		float otherWeight = 0.3f;
		
		float weightedFertility;
		if (dominantFertilityGender == Gender.MALE) {
			weightedFertility = (maleFertility * dominantWeight) + (femaleFertility * otherWeight);
		} else {
			weightedFertility = (femaleFertility * dominantWeight) + (maleFertility * otherWeight);
		}
		
		int numOfBabies = Math.round(weightedFertility / IBreedable.MAX_FERTILITY_RATE * MAX_NUMBER_OF_BABIES);
		return Mth.clamp(numOfBabies, 0, MAX_NUMBER_OF_BABIES);
	}

	public static PregnancyPhase calculateMaxPregnancyPhaseByTotalNumOfBabies(@Nonnegative int numOfBabies) {
		return getMinPhaseForNumOfBabies(numOfBabies);
	}

	public static OptionalInt calculateRandomNumOfBabiesByMaxPregnancyPhase(@NonNull PregnancyPhase lastPregnancyPhase, RandomSource random) {
		int[] numOfBabies = PHASE_TO_BABY_COUNT_MAP.get(lastPregnancyPhase);
		if (numOfBabies != null) {
			return OptionalInt.of(numOfBabies[random.nextInt(numOfBabies.length)]);
		}
		return OptionalInt.empty();
	}

	private static boolean spawnBaby(ServerLevel serverLevel, Position pos, Species species, float maxHealthPercentage, RandomSource random) {		
		Mob entity = null;
		BlockPos blockPos = BlockPos.containing(pos);

		if (species == Species.ZOMBIE) {
			if (random.nextBoolean()) {
				entity = MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL.get().spawn(serverLevel, blockPos, MobSpawnType.MOB_SUMMONED);
			}
			else {
				entity = EntityType.ZOMBIE.spawn(serverLevel, blockPos, MobSpawnType.MOB_SUMMONED);
			}
		}
		else if (species == Species.CREEPER) {
			entity = MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL.get().spawn(serverLevel, blockPos, MobSpawnType.MOB_SUMMONED);
		}
		else if (species == Species.VILLAGER) {
			entity = EntityType.VILLAGER.spawn(serverLevel, blockPos, MobSpawnType.MOB_SUMMONED);
		}

		if (entity != null) {
			entity.setBaby(true);
			entity.setYRot(random.nextFloat() * 360F);	
			int maxHealth = (int) (entity.getMaxHealth() * Mth.clamp(maxHealthPercentage, 0.1f, 1.0f));
			entity.setHealth(random.nextInt(1, Math.max(2, maxHealth)));	
			return true;
		}
		return false;
	}
	
	private static boolean spawnFetus(ServerLevel serverLevel, Position pos, Species species, Creature creature) {
		var deadBabyItem = PregnancySystemHelper.getDeadBabyItem(species, creature);
		if (deadBabyItem == null) {
			MinepreggoMod.LOGGER.warn("Failed to spawn fetus item for species {} and creature {}", species, creature);
			return false;
		}

		ItemEntity entityToSpawn = new ItemEntity(serverLevel, pos.x(), pos.y(), pos.z(), new ItemStack(deadBabyItem));
		entityToSpawn.setPickUpDelay(10);
		serverLevel.addFreshEntity(entityToSpawn);
		return true;
	}
	
	public static void spawnBabiesOrFetuses(ServerLevel serverLevel, Position pos, float alive, float maxHealthPercentage, Womb womb, RandomSource random) {	
		womb.forEach(babyData -> {
			boolean spawned = false;
			if (random.nextFloat() < alive) {
				spawned = spawnBaby(serverLevel, pos, babyData.typeOfSpecies, maxHealthPercentage, random);
			}
			if (!spawned) {
				spawnFetus(serverLevel, pos, babyData.typeOfSpecies, babyData.typeOfCreature);
			}
		});
	}
	
	public static void spawnFetuses(ServerLevel serverLevel, Position pos, float prob, Womb womb, RandomSource random) {
		womb.forEach(babyData -> {
			if (random.nextFloat() < prob) {
				spawnFetus(serverLevel, pos, babyData.typeOfSpecies, babyData.typeOfCreature);
			}
		});
	}
	// BABY HELPERS - END
	
	
	// DEATH HELPERS - START
    public static void deathByBellyBurst(LivingEntity entity, ServerLevel serverLevel) {
    	LivingEntityHelper.playSoundNearTo(entity, MinepreggoModSounds.getRandomStomachGrowls(entity.getRandom()));
		serverLevel.explode(entity, entity.getX(), entity.getY(), entity.getZ(), 1, ExplosionInteraction.MOB);
		ServerParticleHelper.startBloodRainAtEntity(serverLevel, entity);
    }
    
	public static void tornWomb(LivingEntity entity) {
		entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(MinepreggoModDamageSources.BELLY_BURST)), Float.MAX_VALUE);
	}
	
	public static void applyDamageByPregnancyPain(LivingEntity entity, int damage) {
		entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(MinepreggoModDamageSources.PREGNANCY_PAIN)), damage);
	}
	// DEATH HELPERS - END
}
