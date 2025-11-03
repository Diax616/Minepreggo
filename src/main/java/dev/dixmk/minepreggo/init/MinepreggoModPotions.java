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
	public static final RegistryObject<Potion> IMPREGNATION_POTION_0 = REGISTRY.register("impregnation_potion_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION_POTION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_1 = REGISTRY.register("impregnation_potion_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION_POTION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_2 = REGISTRY.register("impregnation_potion_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION_POTION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> IMPREGNATION_POTION_3 = REGISTRY.register("impregnation_potion_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.IMPREGNANTION_POTION.get(), 200, 3, false, true)));
	
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_0 = REGISTRY.register("zombie_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_1 = REGISTRY.register("zombie_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_2 = REGISTRY.register("zombie_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> ZOMBIE_IMPREGNATION_3 = REGISTRY.register("zombie_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.ZOMBIE_IMPREGNATION.get(), 200, 3, false, true)));
	
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_0 = REGISTRY.register("creeper_impregnation_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 0, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_1 = REGISTRY.register("creeper_impregnation_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 1, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_2 = REGISTRY.register("creeper_impregnation_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 2, false, true)));
	public static final RegistryObject<Potion> CREEPER_IMPREGNATION_3 = REGISTRY.register("creeper_impregnation_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.CREEPER_IMPREGNATION.get(), 200, 3, false, true)));

	public static final RegistryObject<Potion> PREGNANCY_DELAY_0 = REGISTRY.register("pregnancy_delay_0", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 0, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_1 = REGISTRY.register("pregnancy_delay_1", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 1, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_2 = REGISTRY.register("pregnancy_delay_2", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 2, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_DELAY_3 = REGISTRY.register("pregnancy_delay_3", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_DELAY.get(), 20, 3, false, true)));
	
	public static final RegistryObject<Potion> PREGNANCY_ACCELERATION = REGISTRY.register("pregnancy_acceleration", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_ACCELERATION.get(), 20, 0, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_RESISTANCE = REGISTRY.register("pregnancy_resistance", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get(), 9600, 0, false, true)));
	public static final RegistryObject<Potion> PREGNANCY_HEALING = REGISTRY.register("pregnancy_healing", () -> new Potion(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_HEALING.get(), 20, 0, false, true)));	
}
