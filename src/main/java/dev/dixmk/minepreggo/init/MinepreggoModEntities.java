package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.BellyPart;
import dev.dixmk.minepreggo.world.entity.monster.FertilityWitch;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterPregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.MonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.IllZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterPregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
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

	public static final RegistryObject<EntityType<MonsterPregnantZombieGirl.MonsterZombieGirlP3>> MONSTER_ZOMBIE_GIRL_P3 = register("monster_zombie_girl_p3",
			EntityType.Builder.<MonsterPregnantZombieGirl.MonsterZombieGirlP3>of(MonsterPregnantZombieGirl.MonsterZombieGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterPregnantZombieGirl.MonsterZombieGirlP3::new).sized(0.625f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterPregnantZombieGirl.MonsterZombieGirlP5>> MONSTER_ZOMBIE_GIRL_P5 = register("monster_zombie_girl_p5",
			EntityType.Builder.<MonsterPregnantZombieGirl.MonsterZombieGirlP5>of(MonsterPregnantZombieGirl.MonsterZombieGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterPregnantZombieGirl.MonsterZombieGirlP5::new).sized(0.725f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterPregnantZombieGirl.MonsterZombieGirlP7>> MONSTER_ZOMBIE_GIRL_P7 = register("monster_zombie_girl_p7",
			EntityType.Builder.<MonsterPregnantZombieGirl.MonsterZombieGirlP7>of(MonsterPregnantZombieGirl.MonsterZombieGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterPregnantZombieGirl.MonsterZombieGirlP7::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirl>> TAMABLE_ZOMBIE_GIRL = register("tamable_zombie_girl",
			EntityType.Builder.<TamableZombieGirl>of(TamableZombieGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP0>> TAMABLE_ZOMBIE_GIRL_P0 = register("tamable_zombie_girl_p0",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP0>of(TamablePregnantZombieGirl.TamableZombieGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP0::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP1>> TAMABLE_ZOMBIE_GIRL_P1 = register("tamable_zombie_girl_p1",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP1>of(TamablePregnantZombieGirl.TamableZombieGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP2>> TAMABLE_ZOMBIE_GIRL_P2 = register("tamable_zombie_girl_p2",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP2>of(TamablePregnantZombieGirl.TamableZombieGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP2::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP3>> TAMABLE_ZOMBIE_GIRL_P3 = register("tamable_zombie_girl_p3",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP3>of(TamablePregnantZombieGirl.TamableZombieGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP3::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP4>> TAMABLE_ZOMBIE_GIRL_P4 = register("tamable_zombie_girl_p4",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP4>of(TamablePregnantZombieGirl.TamableZombieGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP4::new).sized(0.65f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP5>> TAMABLE_ZOMBIE_GIRL_P5 = register("tamable_zombie_girl_p5",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP5>of(TamablePregnantZombieGirl.TamableZombieGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP5::new).sized(0.675f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP6>> TAMABLE_ZOMBIE_GIRL_P6 = register("tamable_zombie_girl_p6",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP6>of(TamablePregnantZombieGirl.TamableZombieGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP6::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP7>> TAMABLE_ZOMBIE_GIRL_P7 = register("tamable_zombie_girl_p7",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP7>of(TamablePregnantZombieGirl.TamableZombieGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP7::new).sized(0.75f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP8>> TAMABLE_ZOMBIE_GIRL_P8 = register("tamable_zombie_girl_p8",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP8>of(TamablePregnantZombieGirl.TamableZombieGirlP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP8::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterHumanoidCreeperGirl>> MONSTER_HUMANOID_CREEPER_GIRL = register("monster_humanoid_creeper_girl",
			EntityType.Builder.<MonsterHumanoidCreeperGirl>of(MonsterHumanoidCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterHumanoidCreeperGirl::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3>> MONSTER_HUMANOID_CREEPER_GIRL_P3 = register("monster_humanoid_creeper_girl_p3",
			EntityType.Builder.<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3>of(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3::new).sized(0.625f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5>> MONSTER_HUMANOID_CREEPER_GIRL_P5 = register("monster_humanoid_creeper_girl_p5",
			EntityType.Builder.<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5>of(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7>> MONSTER_HUMANOID_CREEPER_GIRL_P7 = register("monster_humanoid_creeper_girl_p7",
			EntityType.Builder.<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7>of(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7::new).sized(0.775f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirl>> TAMABLE_HUMANOID_CREEPER_GIRL = register("tamable_humanoid_creeper_girl",
			EntityType.Builder.<TamableHumanoidCreeperGirl>of(TamableHumanoidCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0>> TAMABLE_HUMANOID_CREEPER_GIRL_P0 = register("tamable_humanoid_creeper_girl_p0",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1>> TAMABLE_HUMANOID_CREEPER_GIRL_P1 = register("tamable_humanoid_creeper_girl_p1",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2>> TAMABLE_HUMANOID_CREEPER_GIRL_P2 = register("tamable_humanoid_creeper_girl_p2",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3>> TAMABLE_HUMANOID_CREEPER_GIRL_P3 = register("tamable_humanoid_creeper_girl_p3",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4>> TAMABLE_HUMANOID_CREEPER_GIRL_P4 = register("tamable_humanoid_creeper_girl_p4",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4::new).sized(0.65f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5>> TAMABLE_HUMANOID_CREEPER_GIRL_P5 = register("tamable_humanoid_creeper_girl_p5",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6>> TAMABLE_HUMANOID_CREEPER_GIRL_P6 = register("tamable_humanoid_creeper_girl_p6",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6::new).sized(0.725f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7>> TAMABLE_HUMANOID_CREEPER_GIRL_P7 = register("tamable_humanoid_creeper_girl_p7",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7::new).sized(0.775f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8>> TAMABLE_HUMANOID_CREEPER_GIRL_P8 = register("tamable_humanoid_creeper_girl_p8",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8::new).sized(0.8f, 1.8f));
	
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
	
	public static final RegistryObject<EntityType<BellyPart>> BELLY_PART = register("belly_part",
			EntityType.Builder.<BellyPart>of(BellyPart::new, MobCategory.MISC).clientTrackingRange(10).setUpdateInterval(1));
	
	public static final RegistryObject<EntityType<TamableMonsterEnderWoman>> TAMABLE_MONSTER_ENDER_WOMAN = register("tamable_monster_ender_woman",
			EntityType.Builder.<TamableMonsterEnderWoman>of(TamableMonsterEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableMonsterEnderWoman::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<TamableMonsterCreeperGirl>> TAMABLE_MONSTER_CREEPER_GIRL = register("tamable_monster_creeper_girl",
			EntityType.Builder.<TamableMonsterCreeperGirl>of(TamableMonsterCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableMonsterCreeperGirl::new).sized(0.6f, 1.8f));
	
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}
}
