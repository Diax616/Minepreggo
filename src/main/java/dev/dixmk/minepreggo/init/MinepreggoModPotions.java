package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModPotions {

	private MinepreggoModPotions() {}
	
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, MinepreggoMod.MODID);
	public static final RegistryObject<Potion> IMPREGNATION_POTION_0 = REGISTRY.register("impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_1 = REGISTRY.register("impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_2 = REGISTRY.register("impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_3 = REGISTRY.register("impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 200, 3, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_4 = REGISTRY.register("impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION.get(), 200, 4, false, true)));
	
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_0 = REGISTRY.register("zombie_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_1 = REGISTRY.register("zombie_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_2 = REGISTRY.register("zombie_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_3 = REGISTRY.register("zombie_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 3, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_4 = REGISTRY.register("zombie_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 4, false, true)));

	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_0 = REGISTRY.register("creeper_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_1 = REGISTRY.register("creeper_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_2 = REGISTRY.register("creeper_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_3 = REGISTRY.register("creeper_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 3, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_4 = REGISTRY.register("creeper_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 4, false, true)));

	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_0 = REGISTRY.register("humanoid_creeper_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_1 = REGISTRY.register("humanoid_creeper_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_2 = REGISTRY.register("humanoid_creeper_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_3 = REGISTRY.register("humanoid_creeper_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 200, 3, false, true)));
	public static final RegistryObject<Potion> HUMANOID_CREEPER_IMPREGNATION_4 = REGISTRY.register("humanoid_creeper_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_CREEPER_IMPREGNATION.get(), 200, 4, false, true)));

	public static final RegistryObject<Potion> ENDER_IMPREGNATION_0 = REGISTRY.register("ender_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> ENDER_IMPREGNATION_1 = REGISTRY.register("ender_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> ENDER_IMPREGNATION_2 = REGISTRY.register("ender_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> ENDER_IMPREGNATION_3 = REGISTRY.register("ender_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 200, 3, false, true)));
	public static final RegistryObject<Potion> ENDER_IMPREGNATION_4 = REGISTRY.register("ender_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ENDER_IMPREGNATION.get(), 200, 4, false, true)));

	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_0 = REGISTRY.register("humanoid_ender_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_1 = REGISTRY.register("humanoid_ender_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_2 = REGISTRY.register("humanoid_ender_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_3 = REGISTRY.register("humanoid_ender_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 200, 3, false, true)));
	public static final RegistryObject<Potion> HUMANOID_ENDER_IMPREGNATION_4 = REGISTRY.register("humanoid_ender_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.HUMANOID_ENDER_IMPREGNATION.get(), 200, 4, false, true)));
	
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_0 = REGISTRY.register("villager_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_1 = REGISTRY.register("villager_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_2 = REGISTRY.register("villager_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_3 = REGISTRY.register("villager_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 200, 3, false, true)));
	public static final RegistryObject<Potion> VILLAGER_IMPREGNATION_4 = REGISTRY.register("villager_impregnation_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.VILLAGER_IMPREGNATION.get(), 200, 4, false, true)));

	public static final RegistryObject<Potion> PREGNANCY_DELAY_0 = REGISTRY.register("pregnancy_delay_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 0, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_1 = REGISTRY.register("pregnancy_delay_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 1, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_2 = REGISTRY.register("pregnancy_delay_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 2, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_3 = REGISTRY.register("pregnancy_delay_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 3, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_4 = REGISTRY.register("pregnancy_delay_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 4, false, true)));
	
	public static final RegistryObject<Potion> BABY_DUPLICATION_0 = REGISTRY.register("baby_duplication_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 20, 0, false, true)));
	public static final RegistryObject<Potion> BABY_DUPLICATION_1 = REGISTRY.register("baby_duplication_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 20, 1, false, true)));
	public static final RegistryObject<Potion> BABY_DUPLICATION_2 = REGISTRY.register("baby_duplication_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 20, 2, false, true)));
	public static final RegistryObject<Potion> BABY_DUPLICATION_3 = REGISTRY.register("baby_duplication_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 20, 3, false, true)));
	public static final RegistryObject<Potion> BABY_DUPLICATION_4 = REGISTRY.register("baby_duplication_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.BABY_DUPLICATION.get(), 20, 4, false, true)));

	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_0 = REGISTRY.register("pregnancy_acceleration_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 20, 0, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_1 = REGISTRY.register("pregnancy_acceleration_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 20, 1, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_2 = REGISTRY.register("pregnancy_acceleration_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 20, 2, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_3 = REGISTRY.register("pregnancy_acceleration_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 20, 3, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION_4 = REGISTRY.register("pregnancy_acceleration_4", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 20, 4, false, true)));
	
	public static final RegistryObject<Potion> PREGNANCY_RESISTANCE = REGISTRY.register("pregnancy_resistance_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get(), 9600, 0, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_HEALING = REGISTRY.register("pregnancy_healing_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_HEALING.get(), 20, 0, false, true)));	

	public static final RegistryObject<Potion> FERTILITY = REGISTRY.register("fertility", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.FERTILITY.get(), 20, 0, false, true)));
	public static final RegistryObject<Potion> ETERNAL_PREGNANCY = REGISTRY.register("eternal_pregnancy", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get(), -1, 0, false, false, true)));
}
