package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.effect.BabyDuplication;
import dev.dixmk.minepreggo.world.effect.BellyRubs;
import dev.dixmk.minepreggo.world.effect.Birth;
import dev.dixmk.minepreggo.world.effect.Contraction;
import dev.dixmk.minepreggo.world.effect.Craving;
import dev.dixmk.minepreggo.world.effect.CreeperImpregnation;
import dev.dixmk.minepreggo.world.effect.Depression;
import dev.dixmk.minepreggo.world.effect.EnderImpregnation;
import dev.dixmk.minepreggo.world.effect.EternalPregnancy;
import dev.dixmk.minepreggo.world.effect.Fertile;
import dev.dixmk.minepreggo.world.effect.Fertility;
import dev.dixmk.minepreggo.world.effect.FetalMovement;
import dev.dixmk.minepreggo.world.effect.FullOfCreepers;
import dev.dixmk.minepreggo.world.effect.FullOfEnders;
import dev.dixmk.minepreggo.world.effect.FullOfZombies;
import dev.dixmk.minepreggo.world.effect.Horny;
import dev.dixmk.minepreggo.world.effect.HumanoidCreeperImpregnation;
import dev.dixmk.minepreggo.world.effect.HumanoidEnderImpregnation;
import dev.dixmk.minepreggo.world.effect.Impregnantion;
import dev.dixmk.minepreggo.world.effect.Lactation;
import dev.dixmk.minepreggo.world.effect.Maternity;
import dev.dixmk.minepreggo.world.effect.Miscarriage;
import dev.dixmk.minepreggo.world.effect.MorningSickness;
import dev.dixmk.minepreggo.world.effect.PreBirth;
import dev.dixmk.minepreggo.world.effect.PregnancyAcceleration;
import dev.dixmk.minepreggo.world.effect.PregnancyDelay;
import dev.dixmk.minepreggo.world.effect.PregnancyHealing;
import dev.dixmk.minepreggo.world.effect.PregnancyP0;
import dev.dixmk.minepreggo.world.effect.PregnancyP1;
import dev.dixmk.minepreggo.world.effect.PregnancyP2;
import dev.dixmk.minepreggo.world.effect.PregnancyP3;
import dev.dixmk.minepreggo.world.effect.PregnancyP4;
import dev.dixmk.minepreggo.world.effect.PregnancyP5;
import dev.dixmk.minepreggo.world.effect.PregnancyP6;
import dev.dixmk.minepreggo.world.effect.PregnancyP7;
import dev.dixmk.minepreggo.world.effect.PregnancyP8;
import dev.dixmk.minepreggo.world.effect.PregnancyResistance;
import dev.dixmk.minepreggo.world.effect.VillagerImpregnation;
import dev.dixmk.minepreggo.world.effect.WaterBreaking;
import dev.dixmk.minepreggo.world.effect.ZeroGravityBelly;
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
	public static final RegistryObject<MobEffect> HUMANOID_CREEPER_IMPREGNATION = REGISTRY.register("humanoid_creeper_impregnation", HumanoidCreeperImpregnation::new);
	public static final RegistryObject<MobEffect> HUMANOID_ENDER_IMPREGNATION = REGISTRY.register("humanoid_ender_impregnation", HumanoidEnderImpregnation::new);
	public static final RegistryObject<MobEffect> VILLAGER_IMPREGNATION = REGISTRY.register("villager_impregnation", VillagerImpregnation::new);

	public static final RegistryObject<MobEffect> PREGNANCY_HEALING = REGISTRY.register("pregnancy_healing", PregnancyHealing::new);
	public static final RegistryObject<MobEffect> PREGNANCY_DELAY = REGISTRY.register("pregnancy_delay", PregnancyDelay::new);
	public static final RegistryObject<MobEffect> PREGNANCY_ACCELERATION = REGISTRY.register("pregnancy_acceleration", PregnancyAcceleration::new);
	public static final RegistryObject<MobEffect> BABY_DUPLICATION = REGISTRY.register("baby_duplication", BabyDuplication::new);
	
	public static final RegistryObject<MobEffect> FULL_OF_ZOMBIES = REGISTRY.register("full_of_zombies", FullOfZombies::new);
	public static final RegistryObject<MobEffect> FULL_OF_CREEPERS = REGISTRY.register("full_of_creepers", FullOfCreepers::new);
	public static final RegistryObject<MobEffect> FULL_OF_ENDERS = REGISTRY.register("full_of_enders", FullOfEnders::new);

	public static final RegistryObject<MobEffect> PREGNANCY_P0 = REGISTRY.register("pregnancy_p0", PregnancyP0::new);
	public static final RegistryObject<MobEffect> PREGNANCY_P1 = REGISTRY.register("pregnancy_p1", PregnancyP1::new);
	public static final RegistryObject<MobEffect> PREGNANCY_P2 = REGISTRY.register("pregnancy_p2", PregnancyP2::new);
	public static final RegistryObject<MobEffect> PREGNANCY_P3 = REGISTRY.register("pregnancy_p3", PregnancyP3::new);
	public static final RegistryObject<MobEffect> PREGNANCY_P4 = REGISTRY.register("pregnancy_p4", PregnancyP4::new);
	public static final RegistryObject<MobEffect> PREGNANCY_P5 = REGISTRY.register("pregnancy_p5", PregnancyP5::new);
	public static final RegistryObject<MobEffect> PREGNANCY_P6 = REGISTRY.register("pregnancy_p6", PregnancyP6::new);
	public static final RegistryObject<MobEffect> PREGNANCY_P7 = REGISTRY.register("pregnancy_p7", PregnancyP7::new);
	public static final RegistryObject<MobEffect> PREGNANCY_P8 = REGISTRY.register("pregnancy_p8", PregnancyP8::new);
		
	public static final RegistryObject<MobEffect> BELLY_RUBS = REGISTRY.register("belly_rubs", BellyRubs::new);
	public static final RegistryObject<MobEffect> BIRTH = REGISTRY.register("birth", Birth::new);
	public static final RegistryObject<MobEffect> CONTRACTION = REGISTRY.register("contraction", Contraction::new);
	public static final RegistryObject<MobEffect> CRAVING = REGISTRY.register("craving", Craving::new);
	public static final RegistryObject<MobEffect> DEPRESSION = REGISTRY.register("depression", Depression::new);

	public static final RegistryObject<MobEffect> ETERNAL_PREGNANCY = REGISTRY.register("eternal_pregnancy", EternalPregnancy::new);
	public static final RegistryObject<MobEffect> FERTILITY = REGISTRY.register("fertility", Fertility::new);
	public static final RegistryObject<MobEffect> FERTILE = REGISTRY.register("fertile", Fertile::new);
	public static final RegistryObject<MobEffect> FETAL_MOVEMENT = REGISTRY.register("fetal_movement", FetalMovement::new);
	public static final RegistryObject<MobEffect> WATER_BREAKING = REGISTRY.register("water_breaking", WaterBreaking::new);

	public static final RegistryObject<MobEffect> HORNY = REGISTRY.register("horny", Horny::new);
	public static final RegistryObject<MobEffect> LACTATION = REGISTRY.register("lactation", Lactation::new);
	public static final RegistryObject<MobEffect> MATERNITY = REGISTRY.register("maternity", Maternity::new);
	public static final RegistryObject<MobEffect> MISCARRIAGE = REGISTRY.register("miscarriage", Miscarriage::new);
	public static final RegistryObject<MobEffect> MORNING_SICKNESS = REGISTRY.register("morning_sickness", MorningSickness::new);
	public static final RegistryObject<MobEffect> PREBIRTH = REGISTRY.register("prebirth", PreBirth::new);

	public static final RegistryObject<MobEffect> ZERO_GRAVITY_BELLY = REGISTRY.register("zero_gravity_belly", ZeroGravityBelly::new);
}
