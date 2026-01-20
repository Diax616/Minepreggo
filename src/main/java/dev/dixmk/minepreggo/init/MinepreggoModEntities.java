package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.monster.FertilityWitch;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP8;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.MonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.IllZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP8;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModEntities {
	
	private MinepreggoModEntities() {}
	
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinepreggoMod.MODID);
	
	public static final RegistryObject<EntityType<MonsterZombieGirl>> MONSTER_ZOMBIE_GIRL = register("monster_zombie_girl",
			EntityType.Builder.<MonsterZombieGirl>of(MonsterZombieGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterZombieGirl::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<MonsterZombieGirlP3>> MONSTER_ZOMBIE_GIRL_P3 = register("monster_zombie_girl_p3",
			EntityType.Builder.<MonsterZombieGirlP3>of(MonsterZombieGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterZombieGirlP3::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterZombieGirlP5>> MONSTER_ZOMBIE_GIRL_P5 = register("monster_zombie_girl_p5",
			EntityType.Builder.<MonsterZombieGirlP5>of(MonsterZombieGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterZombieGirlP5::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterZombieGirlP7>> MONSTER_ZOMBIE_GIRL_P7 = register("monster_zombie_girl_p7",
			EntityType.Builder.<MonsterZombieGirlP7>of(MonsterZombieGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterZombieGirlP7::new).sized(1.0f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirl>> TAMABLE_ZOMBIE_GIRL = register("tamable_zombie_girl",
			EntityType.Builder.<TamableZombieGirl>of(TamableZombieGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP0>> TAMABLE_ZOMBIE_GIRL_P0 = register("tamable_zombie_girl_p0",
			EntityType.Builder.<TamableZombieGirlP0>of(TamableZombieGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP0::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<TamableZombieGirlP1>> TAMABLE_ZOMBIE_GIRL_P1 = register("tamable_zombie_girl_p1",
			EntityType.Builder.<TamableZombieGirlP1>of(TamableZombieGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP2>> TAMABLE_ZOMBIE_GIRL_P2 = register("tamable_zombie_girl_p2",
			EntityType.Builder.<TamableZombieGirlP2>of(TamableZombieGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP2::new).sized(0.65f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP3>> TAMABLE_ZOMBIE_GIRL_P3 = register("tamable_zombie_girl_p3",
			EntityType.Builder.<TamableZombieGirlP3>of(TamableZombieGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP3::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP4>> TAMABLE_ZOMBIE_GIRL_P4 = register("tamable_zombie_girl_p4",
			EntityType.Builder.<TamableZombieGirlP4>of(TamableZombieGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP4::new).sized(0.75f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP5>> TAMABLE_ZOMBIE_GIRL_P5 = register("tamable_zombie_girl_p5",
			EntityType.Builder.<TamableZombieGirlP5>of(TamableZombieGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP5::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP6>> TAMABLE_ZOMBIE_GIRL_P6 = register("tamable_zombie_girl_p6",
			EntityType.Builder.<TamableZombieGirlP6>of(TamableZombieGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP6::new).sized(0.9f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP7>> TAMABLE_ZOMBIE_GIRL_P7 = register("tamable_zombie_girl_p7",
			EntityType.Builder.<TamableZombieGirlP7>of(TamableZombieGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP7::new).sized(1.0f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP8>> TAMABLE_ZOMBIE_GIRL_P8 = register("tamable_zombie_girl_p8",
			EntityType.Builder.<TamableZombieGirlP8>of(TamableZombieGirlP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP8::new).sized(1.1f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterHumanoidCreeperGirl>> MONSTER_HUMANOID_CREEPER_GIRL = register("monster_humanoid_creeper_girl",
			EntityType.Builder.<MonsterHumanoidCreeperGirl>of(MonsterHumanoidCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterHumanoidCreeperGirl::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<MonsterHumanoidCreeperGirlP3>> MONSTER_HUMANOID_CREEPER_GIRL_P3 = register("monster_humanoid_creeper_girl_p3",
			EntityType.Builder.<MonsterHumanoidCreeperGirlP3>of(MonsterHumanoidCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterHumanoidCreeperGirlP3::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterHumanoidCreeperGirlP5>> MONSTER_HUMANOID_CREEPER_GIRL_P5 = register("monster_humanoid_creeper_girl_p5",
			EntityType.Builder.<MonsterHumanoidCreeperGirlP5>of(MonsterHumanoidCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterHumanoidCreeperGirlP5::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterHumanoidCreeperGirlP7>> MONSTER_HUMANOID_CREEPER_GIRL_P7 = register("monster_humanoid_creeper_girl_p7",
			EntityType.Builder.<MonsterHumanoidCreeperGirlP7>of(MonsterHumanoidCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterHumanoidCreeperGirlP7::new).sized(1.0f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirl>> TAMABLE_HUMANOID_CREEPER_GIRL = register("tamable_humanoid_creeper_girl",
			EntityType.Builder.<TamableHumanoidCreeperGirl>of(TamableHumanoidCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP0>> TAMABLE_HUMANOID_CREEPER_GIRL_P0 = register("tamable_humanoid_creeper_girl_p0",
			EntityType.Builder.<TamableHumanoidCreeperGirlP0>of(TamableHumanoidCreeperGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP0::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP1>> TAMABLE_HUMANOID_CREEPER_GIRL_P1 = register("tamable_humanoid_creeper_girl_p1",
			EntityType.Builder.<TamableHumanoidCreeperGirlP1>of(TamableHumanoidCreeperGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP2>> TAMABLE_HUMANOID_CREEPER_GIRL_P2 = register("tamable_humanoid_creeper_girl_p2",
			EntityType.Builder.<TamableHumanoidCreeperGirlP2>of(TamableHumanoidCreeperGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP2::new).sized(0.65f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP3>> TAMABLE_HUMANOID_CREEPER_GIRL_P3 = register("tamable_humanoid_creeper_girl_p3",
			EntityType.Builder.<TamableHumanoidCreeperGirlP3>of(TamableHumanoidCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP3::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP4>> TAMABLE_HUMANOID_CREEPER_GIRL_P4 = register("tamable_humanoid_creeper_girl_p4",
			EntityType.Builder.<TamableHumanoidCreeperGirlP4>of(TamableHumanoidCreeperGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP4::new).sized(0.75f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP5>> TAMABLE_HUMANOID_CREEPER_GIRL_P5 = register("tamable_humanoid_creeper_girl_p5",
			EntityType.Builder.<TamableHumanoidCreeperGirlP5>of(TamableHumanoidCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP5::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP6>> TAMABLE_HUMANOID_CREEPER_GIRL_P6 = register("tamable_humanoid_creeper_girl_p6",
			EntityType.Builder.<TamableHumanoidCreeperGirlP6>of(TamableHumanoidCreeperGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP6::new).sized(0.9f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP7>> TAMABLE_HUMANOID_CREEPER_GIRL_P7 = register("tamable_humanoid_creeper_girl_p7",
			EntityType.Builder.<TamableHumanoidCreeperGirlP7>of(TamableHumanoidCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP7::new).sized(1.0f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirlP8>> TAMABLE_HUMANOID_CREEPER_GIRL_P8 = register("tamable_humanoid_creeper_girl_p8",
			EntityType.Builder.<TamableHumanoidCreeperGirlP8>of(TamableHumanoidCreeperGirlP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirlP8::new).sized(1.1f, 1.8f));
	
	
	public static final RegistryObject<EntityType<MonsterCreeperGirl>> MONSTER_CREEPER_GIRL = register("monster_creeper_girl",
			EntityType.Builder.<MonsterCreeperGirl>of(MonsterCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterCreeperGirl::new).sized(0.6f, 1.5f));
	
	public static final RegistryObject<EntityType<ScientificIllager>> SCIENTIFIC_ILLAGER = register("scientific_illager",
			EntityType.Builder.<ScientificIllager>of(ScientificIllager::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ScientificIllager::new).sized(0.6f, 1.95f));
	
	public static final RegistryObject<EntityType<IllEnderWoman>> ILL_ENDER_WOMAN = register("ill_ender_woman",
			EntityType.Builder.<IllEnderWoman>of(IllEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllEnderWoman::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<IllCreeperGirl>> ILL_CREEPER_GIRL = register("ill_creeper_girl",
			EntityType.Builder.<IllCreeperGirl>of(IllCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllCreeperGirl::new).sized(0.6f, 1.5f));
	
	public static final RegistryObject<EntityType<IllHumanoidCreeperGirl>> ILL_HUMANOID_CREEPER_GIRL = register("ill_humanoid_creeper_girl",
			EntityType.Builder.<IllHumanoidCreeperGirl>of(IllHumanoidCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllHumanoidCreeperGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<IllZombieGirl>> ILL_ZOMBIE_GIRL = register("ill_zombie_girl",
			EntityType.Builder.<IllZombieGirl>of(IllZombieGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllZombieGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<FertilityWitch>> FERTILITY_WITCH = register("fertility_witch",
			EntityType.Builder.<FertilityWitch>of(FertilityWitch::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(FertilityWitch::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterEnderWoman>> MONSTER_ENDER_WOMAN = register("monster_ender_woman",
			EntityType.Builder.<MonsterEnderWoman>of(MonsterEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterEnderWoman::new).sized(0.6f, 2.9f));
	
	/*
	public static final RegistryObject<EntityType<TamableMonsterEnderWoman>> TAMABLE_MONSTER_ENDER_WOMAN = register("tamable_monster_ender_woman",
			EntityType.Builder.<TamableMonsterEnderWoman>of(TamableMonsterEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableMonsterEnderWoman::new).sized(0.6f, 2.9f));
	*/
	
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}
}
