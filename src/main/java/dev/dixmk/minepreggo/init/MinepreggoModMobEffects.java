package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.effect.BellyRubs;
import dev.dixmk.minepreggo.world.effect.Birth;
import dev.dixmk.minepreggo.world.effect.Contraction;
import dev.dixmk.minepreggo.world.effect.Craving;
import dev.dixmk.minepreggo.world.effect.CreeperImpregnation;
import dev.dixmk.minepreggo.world.effect.Depression;
import dev.dixmk.minepreggo.world.effect.EnderImpregnation;
import dev.dixmk.minepreggo.world.effect.EternalPregnancy;
import dev.dixmk.minepreggo.world.effect.Fertility;
import dev.dixmk.minepreggo.world.effect.FetalMovement;
import dev.dixmk.minepreggo.world.effect.FullOfCreepers;
import dev.dixmk.minepreggo.world.effect.FullOfEnders;
import dev.dixmk.minepreggo.world.effect.FullOfZombies;
import dev.dixmk.minepreggo.world.effect.Horny;
import dev.dixmk.minepreggo.world.effect.HumanoidCreeperImpregnation;
import dev.dixmk.minepreggo.world.effect.Impregnantion;
import dev.dixmk.minepreggo.world.effect.Lactation;
import dev.dixmk.minepreggo.world.effect.Maternity;
import dev.dixmk.minepreggo.world.effect.Miscarriage;
import dev.dixmk.minepreggo.world.effect.MorningSickness;
import dev.dixmk.minepreggo.world.effect.PreBirth;
import dev.dixmk.minepreggo.world.effect.PregnancyAcceleration;
import dev.dixmk.minepreggo.world.effect.PregnancyDelay;
import dev.dixmk.minepreggo.world.effect.PregnancyHealing;
import dev.dixmk.minepreggo.world.effect.PregnancyP1;
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
	public static final RegistryObject<MobEffect> IMPREGNANTION = REGISTRY.register("impregnantion", Impregnantion::new);
	public static final RegistryObject<MobEffect> ZOMBIE_IMPREGNATION = REGISTRY.register("zombie_impregnation", ZombieImpregnation::new);
	public static final RegistryObject<MobEffect> CREEPER_IMPREGNATION = REGISTRY.register("creeper_impregnation", CreeperImpregnation::new);
	public static final RegistryObject<MobEffect> ENDER_IMPREGNATION = REGISTRY.register("ender_impregnation", EnderImpregnation::new);

	
	public static final RegistryObject<MobEffect> PREGNANCY_HEALING = REGISTRY.register("pregnancy_healing", PregnancyHealing::new);
	public static final RegistryObject<MobEffect> PREGNANCY_DELAY = REGISTRY.register("pregnancy_delay", PregnancyDelay::new);
	public static final RegistryObject<MobEffect> PREGNANCY_ACCELERATION = REGISTRY.register("pregnancy_acceleration", PregnancyAcceleration::new);

	public static final RegistryObject<MobEffect> FULL_OF_ZOMBIES = REGISTRY.register("full_of_zombies", FullOfZombies::new);
	public static final RegistryObject<MobEffect> FULL_OF_CREEPERS = REGISTRY.register("full_of_creepers", FullOfCreepers::new);
	public static final RegistryObject<MobEffect> FULL_OF_ENDERS = REGISTRY.register("full_of_enders", FullOfEnders::new);

	public static final RegistryObject<MobEffect> PREGNANCY_P1 = REGISTRY.register("pregnancy_p1", PregnancyP1::new);

	public static final RegistryObject<MobEffect> BELLY_RUBS = REGISTRY.register("belly_rubs", BellyRubs::new);
	public static final RegistryObject<MobEffect> BIRTH = REGISTRY.register("birth", Birth::new);
	public static final RegistryObject<MobEffect> CONTRACTION = REGISTRY.register("contraction", Contraction::new);
	public static final RegistryObject<MobEffect> CRAVING = REGISTRY.register("craving", Craving::new);
	public static final RegistryObject<MobEffect> DEPRESSION = REGISTRY.register("depression", Depression::new);

	public static final RegistryObject<MobEffect> ETERNAL_PREGNANCY = REGISTRY.register("eternal_pregnancy", EternalPregnancy::new);
	public static final RegistryObject<MobEffect> FERTILITY = REGISTRY.register("fertility", Fertility::new);
	public static final RegistryObject<MobEffect> FETAL_MOVEMENT = REGISTRY.register("fetal_movement", FetalMovement::new);

	public static final RegistryObject<MobEffect> HORNY = REGISTRY.register("horny", Horny::new);
	public static final RegistryObject<MobEffect> HUMANOID_CREEPER_IMPREGNATION = REGISTRY.register("humanoid_creeper_impregnation", HumanoidCreeperImpregnation::new);
	public static final RegistryObject<MobEffect> LACTATION = REGISTRY.register("lactation", Lactation::new);
	public static final RegistryObject<MobEffect> MATERNITY = REGISTRY.register("maternity", Maternity::new);
	public static final RegistryObject<MobEffect> MISCARRIAGE = REGISTRY.register("miscarriage", Miscarriage::new);
	public static final RegistryObject<MobEffect> MORNING_SICKNESS = REGISTRY.register("morning_sickness", MorningSickness::new);
	public static final RegistryObject<MobEffect> PREBIRTH = REGISTRY.register("prebirth", PreBirth::new);
}
