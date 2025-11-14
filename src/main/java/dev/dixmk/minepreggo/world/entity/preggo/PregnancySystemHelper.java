package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.List;

import javax.annotation.CheckForNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

public class PregnancySystemHelper {
	
	private PregnancySystemHelper() {}
	
	public static final int MAX_PREGNANCY_HEALTH = 100;
	
	public static final int MAX_PREGNANCY_FERTILITY = 100;
	
	public static final int MAX_CRAVING_LEVEL = 20;
	public static final int MAX_MILKING_LEVEL = 20;
	public static final int MAX_BELLY_RUBBING_LEVEL = 20;
	public static final int MAX_HORNY_LEVEL = 20;
	
	// Days
	public static final int TOTAL_PREGNANCY_DAYS = 70;
	
	// Ticks
	public static final int TOTAL_TICKS_MISCARRIAGE = 400;
	public static final int TOTAL_TICKS_MORNING_SICKNESS = 200;
	
	public static final int TOTAL_TICKS_PREBIRTH_P4 = 300;
	public static final int TOTAL_TICKS_PREBIRTH_P5 = 400;
	public static final int TOTAL_TICKS_PREBIRTH_P6 = 500;
	public static final int TOTAL_TICKS_PREBIRTH_P7 = 600;
	public static final int TOTAL_TICKS_PREBIRTH_P8 = 700;
	
	public static final int TOTAL_TICKS_BIRTH_P4 = 900;
	public static final int TOTAL_TICKS_BIRTH_P5 = 1000;
	public static final int TOTAL_TICKS_BIRTH_P6 = 1100;
	public static final int TOTAL_TICKS_BIRTH_P7 = 1200;
	public static final int TOTAL_TICKS_BIRTH_P8 = 1300;
	
	public static final int TOTAL_TICKS_KICKING_P3 = 400;
	public static final int TOTAL_TICKS_KICKING_P4 = 500;
	public static final int TOTAL_TICKS_KICKING_P5 = 600;
	public static final int TOTAL_TICKS_KICKING_P6 = 700;
	public static final int TOTAL_TICKS_KICKING_P7 = 800;
	public static final int TOTAL_TICKS_KICKING_P8 = 900;
	
	public static final int TOTAL_TICKS_CONTRACTION_P4 = 600;
	public static final int TOTAL_TICKS_CONTRACTION_P5 = 700;
	public static final int TOTAL_TICKS_CONTRACTION_P6 = 800;
	public static final int TOTAL_TICKS_CONTRACTION_P7 = 900;
	public static final int TOTAL_TICKS_CONTRACTION_P8 = 1000;
	
	// Probabilities
	public static final float LOW_PREGNANCY_PAIN_PROBABILITY = 0.000075F;
	public static final float MEDIUM_PREGNANCY_PAIN_PROBABILITY = 0.000125F;
	public static final float HIGH_PREGNANCY_PAIN_PROBABILITY = 0.000175F;
	
	public static final float LOW_MORNING_SICKNESS_PROBABILITY = 0.0001F;
	public static final float MEDIUM_MORNING_SICKNESS_PROBABILITY = 0.00015F;
	public static final float HIGH_MORNING_SICKNESS_PROBABILITY = 0.0002F;
	
	public static final float LOW_ANGER_PROBABILITY = 0.005F;
	public static final float MEDIUM_ANGER_PROBABILITY = 0.0075F;
	public static final float HIGH_ANGER_PROBABILITY = 0.01F;
	
	// Pregnancy Symptoms
	public static final int CRAVING_VALUE = 4;
	public static final int ACTIVATE_CRAVING_SYMPTOM = 16;
	public static final int DESACTIVATE_CRAVING_SYMPTOM = 2;
	
	public static final int MILKING_VALUE = 5;
	public static final int ACTIVATE_MILKING_SYMPTOM = 16;
	public static final int DESACTIVATE_MILKING_SYMPTOM = 8;
	
	public static final int BELLY_RUBBING_VALUE = 3;
	public static final int ACTIVATE_BELLY_RUBS_SYMPTOM = 12;
	public static final int DESACTIVATE_FULL_BELLY_RUBS_STAGE = 4;
	
	public static final int ACTIVATE_HORNY_SYMPTOM = 17;
		
	protected static final ImmutableMap<Species, Item> MILK_ITEM = ImmutableMap.of(
			Species.CREEPER, MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(),
			Species.ZOMBIE, MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(),
			Species.HUMAN, MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get()			
	);
	
	
	protected static final ImmutableMap<Species, ImmutableMap<Craving, List<Item>>> CRAVING_ITEM = ImmutableMap.of(
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
			Species.HUMAN,	ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.PICKLE.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.CHOCOLATE_BAR.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.LEMON_ICE_POPSICLES.get(), MinepreggoModItems.LEMON_ICE_CREAM.get()),
					Craving.SPICY, List.of(MinepreggoModItems.HOT_CHICKEN.get()))	
	);

	@CheckForNull
	public static ImmutableMap<Craving, List<Item>> getCravingMap(Species species) {
		if (species == null) {
			return null;
		}
		return CRAVING_ITEM.get(species);
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
	
	
	public static Craving getRandomCraving(RandomSource randomSource) {		
		final var p = randomSource.nextFloat();	
		if (p < 0.05F) {
			return Craving.SWEET;
		}
		else if (p < 0.35F) {
			return Craving.SALTY;
		}
		else if (p < 0.65F) {
			return Craving.SPICY;
		}
		else {
			return Craving.SOUR;
		}
	}
	
	protected static final ImmutableMap<Baby, Item> ALIVE_BABIES = ImmutableMap.of(
			Baby.HUMAN, MinepreggoModItems.BABY_HUMAN.get(), 
			Baby.ZOMBIE, MinepreggoModItems.BABY_ZOMBIE.get(), 
			Baby.HUMANOID_CREEPER, MinepreggoModItems.BABY_HUMANOID_CREEPER.get(),
			Baby.CREEPER, MinepreggoModItems.BABY_QUADRUPED_CREEPER.get());

	protected static final ImmutableMap<Baby, Item> DEAD_BABIES = ImmutableMap.of(
			Baby.HUMAN, MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 
			Baby.ZOMBIE, MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 
			Baby.HUMANOID_CREEPER, MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(),
			Baby.CREEPER, MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get());

	@CheckForNull
	public static Item getDeadBabyItem(Baby babyType) {
		if (babyType == null) {
			return null;
		}		
		return DEAD_BABIES.get(babyType);
	}
	
	@CheckForNull
	public static Item getAliveBabyItem(Baby babyType) {
		if (babyType == null) {
			return null;
		}	
		return ALIVE_BABIES.get(babyType);
	}
	
	public static boolean isPregnantEntityValid(LivingEntity entity) {
		return (entity instanceof ServerPlayer serverPlayer && PlayerHelper.isFemaleAndPregnant(serverPlayer))
		|| (entity instanceof PreggoMob p && p instanceof IPregnancySystemHandler);
	}
}
