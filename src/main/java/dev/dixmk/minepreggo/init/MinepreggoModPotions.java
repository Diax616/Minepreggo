package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModPotions {

	private MinepreggoModPotions() {}
	
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, MinepreggoMod.MODID);
	public static final RegistryObject<Potion> IMPREGNATION_POTION_0 = REGISTRY.register("impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 300, 0, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_1 = REGISTRY.register("impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 300, 1, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_2 = REGISTRY.register("impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 300, 2, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_3 = REGISTRY.register("impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 300, 3, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_4 = REGISTRY.register("impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 300, 4, false, true)));
	
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_0 = REGISTRY.register("zombie_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 300, 0, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_1 = REGISTRY.register("zombie_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 300, 1, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_2 = REGISTRY.register("zombie_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 300, 2, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_3 = REGISTRY.register("zombie_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 300, 3, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_4 = REGISTRY.register("zombie_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 300, 4, false, true)));

	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_0 = REGISTRY.register("creeper_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 300, 0, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_1 = REGISTRY.register("creeper_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 300, 1, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_2 = REGISTRY.register("creeper_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 300, 2, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_3 = REGISTRY.register("creeper_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 300, 3, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_4 = REGISTRY.register("creeper_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 300, 4, false, true)));

	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_0 = REGISTRY.register("humanoid_creeper_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 300, 0, false, true)));
	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_1 = REGISTRY.register("humanoid_creeper_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 300, 1, false, true)));
	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_2 = REGISTRY.register("humanoid_creeper_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 300, 2, false, true)));
	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_3 = REGISTRY.register("humanoid_creeper_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 300, 3, false, true)));
	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_4 = REGISTRY.register("humanoid_creeper_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 300, 4, false, true)));

	public static final RegistryObject<Potion> ENDER_IMPREGNATION_0 = REGISTRY.register("ender_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 300, 0, false, true)));
	public static final RegistryObject<Potion> ENDER_IMPREGNATION_1 = REGISTRY.register("ender_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 300, 1, false, true)));
	public static final RegistryObject<Potion> ENDER_IMPREGNATION_2 = REGISTRY.register("ender_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 300, 2, false, true)));
	public static final RegistryObject<Potion> ENDER_IMPREGNATION_3 = REGISTRY.register("ender_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 300, 3, false, true)));
	public static final RegistryObject<Potion> ENDER_IMPREGNATION_4 = REGISTRY.register("ender_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 300, 4, false, true)));

	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_0 = REGISTRY.register("humanoid_ender_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 300, 0, false, true)));
	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_1 = REGISTRY.register("humanoid_ender_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 300, 1, false, true)));
	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_2 = REGISTRY.register("humanoid_ender_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 300, 2, false, true)));
	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_3 = REGISTRY.register("humanoid_ender_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 300, 3, false, true)));
	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_4 = REGISTRY.register("humanoid_ender_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 300, 4, false, true)));
	
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_0 = REGISTRY.register("villager_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 300, 0, false, true)));
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_1 = REGISTRY.register("villager_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 300, 1, false, true)));
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_2 = REGISTRY.register("villager_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 300, 2, false, true)));
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_3 = REGISTRY.register("villager_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 300, 3, false, true)));
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_4 = REGISTRY.register("villager_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 300, 4, false, true)));

	public static final RegistryObject<Potion> PREGNANCY_DELAY_0 = REGISTRY.register("pregnancy_delay_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 1, 0, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_1 = REGISTRY.register("pregnancy_delay_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 1, 1, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_2 = REGISTRY.register("pregnancy_delay_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 1, 2, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_3 = REGISTRY.register("pregnancy_delay_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 1, 3, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_4 = REGISTRY.register("pregnancy_delay_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 1, 4, false, true)));
	
	public static final RegistryObject<Potion> BABY_DUPLICATION_0 = REGISTRY.register("baby_duplication_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 1, 0, false, true)));
	public static final RegistryObject<Potion> BABY_DUPLICATION_1 = REGISTRY.register("baby_duplication_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 1, 1, false, true)));
	public static final RegistryObject<Potion> BABY_DUPLICATION_2 = REGISTRY.register("baby_duplication_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 1, 2, false, true)));
	public static final RegistryObject<Potion> BABY_DUPLICATION_3 = REGISTRY.register("baby_duplication_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 1, 3, false, true)));
	public static final RegistryObject<Potion> BABY_DUPLICATION_4 = REGISTRY.register("baby_duplication_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 1, 4, false, true)));

	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_0 = REGISTRY.register("pregnancy_acceleration_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 1, 0, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_1 = REGISTRY.register("pregnancy_acceleration_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 1, 1, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_2 = REGISTRY.register("pregnancy_acceleration_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 1, 2, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_3 = REGISTRY.register("pregnancy_acceleration_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 1, 3, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_4 = REGISTRY.register("pregnancy_acceleration_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 1, 4, false, true)));
	
	public static final RegistryObject<Potion> PREGNANCY_RESISTANCE = REGISTRY.register("pregnancy_resistance", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get(), 9600, 0, false, true)));
	public static final RegistryObject<Potion> LONG_PREGNANCY_RESISTANCE = REGISTRY.register("long_pregnancy_resistance", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get(), 19200, 0, false, true)));

	public static final RegistryObject<Potion> PREGNANCY_HEALING = REGISTRY.register("pregnancy_healing", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_HEALING.get(), 1, 0, false, true)));	
	public static final RegistryObject<Potion> PREGNANCY_HARMING = REGISTRY.register("pregnancy_harming", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_HARMING.get(), 1, 0, false, true)));	

	public static final RegistryObject<Potion> FERTILITY = REGISTRY.register("fertility", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.FERTILITY.get(), 1, 0, false, true)));
	public static final RegistryObject<Potion> INFERTILITY = REGISTRY.register("infertility", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.INFERTILITY.get(), 1, 0, false, true)));
	public static final RegistryObject<Potion> METABOLISM_CONTROL = REGISTRY.register("metabolism_control", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.METABOLISM_CONTROL.get(), 6000, 0, false, false, true)));
	public static final RegistryObject<Potion> STRONG_METABOLISM_CONTROL = REGISTRY.register("strong_metabolism_control", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.METABOLISM_CONTROL.get(), 6000, 1, false, false, true)));
	public static final RegistryObject<Potion> LONG_METABOLISM_CONTROL = REGISTRY.register("long_metabolism_control", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.METABOLISM_CONTROL.get(), 12000, 0, false, false, true)));

	public static final RegistryObject<Potion> ETERNAL_PREGNANCY = REGISTRY.register("eternal_pregnancy", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get(), -1, 0, false, true, true)));
	public static final RegistryObject<Potion> ZERO_GRAVITY_BELLY = REGISTRY.register("zero_gravity_belly", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZERO_GRAVITY_BELLY.get(), 9600, 0, false, true, true)));
	public static final RegistryObject<Potion> LONG_ZERO_GRAVITY_BELLY = REGISTRY.register("long_zero_gravity_belly", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZERO_GRAVITY_BELLY.get(), 19200, 0, false, true, true)));

	public static final RegistryObject<Potion> ENDER_POWERFUL_IMPREGNATION = REGISTRY.register("ender_powerful_impregnation", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 300, 4, false, true), new MobEffectInstance(MobEffects.REGENERATION, 300, 0, false, true), new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12000, 0, false, true)));
	public static final RegistryObject<Potion> ENDER_DRAGON_IMPREGNATION = REGISTRY.register("ender_dragon_impregnation", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_DRAGON_IMPREGNATION.get(), 300, 0, false, true)));

	public static Potion getRandomImpregnationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> IMPREGNATION_POTION_0.get();
			case 1 -> IMPREGNATION_POTION_1.get();
			case 2 -> IMPREGNATION_POTION_2.get();
			case 3 -> IMPREGNATION_POTION_3.get();
			default -> IMPREGNATION_POTION_4.get();
		};
	}
	
	public static Potion getRandomZombieImpregnationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> ZOMBIE_IMPREGNATION_0.get();
			case 1 -> ZOMBIE_IMPREGNATION_1.get();
			case 2 -> ZOMBIE_IMPREGNATION_2.get();
			case 3 -> ZOMBIE_IMPREGNATION_3.get();
			default -> ZOMBIE_IMPREGNATION_4.get();
		};
	}

	public static Potion getRandomCreeperImpregnationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> CREEPER_IMPREGNATION_0.get();
			case 1 -> CREEPER_IMPREGNATION_1.get();
			case 2 -> CREEPER_IMPREGNATION_2.get();
			case 3 -> CREEPER_IMPREGNATION_3.get();
			default -> CREEPER_IMPREGNATION_4.get();
		};
	}
	
	public static Potion getRandomHumanoidCreeperImpregnationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> HUMANOID_CREEPER_IMPREGNATION_0.get();
			case 1 -> HUMANOID_CREEPER_IMPREGNATION_1.get();
			case 2 -> HUMANOID_CREEPER_IMPREGNATION_2.get();
			case 3 -> HUMANOID_CREEPER_IMPREGNATION_3.get();
			default -> HUMANOID_CREEPER_IMPREGNATION_4.get();
		};
	}
	
	public static Potion getRandomEnderImpregnationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> ENDER_IMPREGNATION_0.get();
			case 1 -> ENDER_IMPREGNATION_1.get();
			case 2 -> ENDER_IMPREGNATION_2.get();
			case 3 -> ENDER_IMPREGNATION_3.get();
			default -> ENDER_IMPREGNATION_4.get();
		};
	}

	public static Potion getRandomHumanoidEnderImpregnationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> HUMANOID_ENDER_IMPREGNATION_0.get();
			case 1 -> HUMANOID_ENDER_IMPREGNATION_1.get();
			case 2 -> HUMANOID_ENDER_IMPREGNATION_2.get();
			case 3 -> HUMANOID_ENDER_IMPREGNATION_3.get();
			default -> HUMANOID_ENDER_IMPREGNATION_4.get();
		};
	}

	public static Potion getRandomVillagerImpregnationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> VILLAGER_IMPREGNATION_0.get();
			case 1 -> VILLAGER_IMPREGNATION_1.get();
			case 2 -> VILLAGER_IMPREGNATION_2.get();
			case 3 -> VILLAGER_IMPREGNATION_3.get();
			default -> VILLAGER_IMPREGNATION_4.get();
		};
	}

	public static Potion getRandomPregnancyDelayPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> PREGNANCY_DELAY_0.get();
			case 1 -> PREGNANCY_DELAY_1.get();
			case 2 -> PREGNANCY_DELAY_2.get();
			case 3 -> PREGNANCY_DELAY_3.get();
			default -> PREGNANCY_DELAY_4.get();
		};
	}
	
	public static Potion getRandomBabyDuplicationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> BABY_DUPLICATION_0.get();
			case 1 -> BABY_DUPLICATION_1.get();
			case 2 -> BABY_DUPLICATION_2.get();
			case 3 -> BABY_DUPLICATION_3.get();
			default -> BABY_DUPLICATION_4.get();
		};
	}
	
	public static Potion getRandomPregnancyAccelerationPotion(RandomSource random) {
		return switch (random.nextInt(5)) {
			case 0 -> PREGNANCY_ACCELERATION_0.get();
			case 1 -> PREGNANCY_ACCELERATION_1.get();
			case 2 -> PREGNANCY_ACCELERATION_2.get();
			case 3 -> PREGNANCY_ACCELERATION_3.get();
			default -> PREGNANCY_ACCELERATION_4.get();
		};
	}
}
