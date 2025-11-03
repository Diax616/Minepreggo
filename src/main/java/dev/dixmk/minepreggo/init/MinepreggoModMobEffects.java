package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.effect.CreeperImpregnation;
import dev.dixmk.minepreggo.world.effect.FullOfCreepers;
import dev.dixmk.minepreggo.world.effect.FullOfZombies;
import dev.dixmk.minepreggo.world.effect.Impregnantion;
import dev.dixmk.minepreggo.world.effect.PregnancyAcceleration;
import dev.dixmk.minepreggo.world.effect.PregnancyDelay;
import dev.dixmk.minepreggo.world.effect.PregnancyHealing;
import dev.dixmk.minepreggo.world.effect.PregnancyResistance;
import dev.dixmk.minepreggo.world.effect.ZombieImpregnation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModMobEffects {
	
	private MinepreggoModMobEffects() {}
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MinepreggoMod.MODID);

	public static final RegistryObject<MobEffect> PREGNANCY_RESISTANCE = REGISTRY.register("pregnancy_resistance", PregnancyResistance::new);
	public static final RegistryObject<MobEffect> IMPREGNANTION_POTION = REGISTRY.register("impregnantion", Impregnantion::new);
	public static final RegistryObject<MobEffect> ZOMBIE_IMPREGNATION = REGISTRY.register("zombie_impregnation", ZombieImpregnation::new);
	public static final RegistryObject<MobEffect> CREEPER_IMPREGNATION = REGISTRY.register("creeper_impregnation", CreeperImpregnation::new);
	public static final RegistryObject<MobEffect> PREGNANCY_HEALING = REGISTRY.register("pregnancy_healing", PregnancyHealing::new);
	public static final RegistryObject<MobEffect> PREGNANCY_DELAY = REGISTRY.register("pregnancy_delay", PregnancyDelay::new);
	public static final RegistryObject<MobEffect> PREGNANCY_ACCELERATION = REGISTRY.register("pregnancy_acceleration", PregnancyAcceleration::new);

	public static final RegistryObject<MobEffect> FULL_OF_ZOMBIES = REGISTRY.register("full_of_zombies", FullOfZombies::new);
	public static final RegistryObject<MobEffect> FULL_OF_CREEPERS = REGISTRY.register("full_of_creepers", FullOfCreepers::new);

}
