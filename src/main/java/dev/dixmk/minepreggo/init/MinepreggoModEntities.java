package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.BellyPart;
import dev.dixmk.minepreggo.world.entity.monster.FertilityWitch;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostileHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilePregnantMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostileMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostilePregnantMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostileMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamablePregnantMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.IllZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostilePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostileZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.entity.projectile.ExplosiveDragonFireball;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModEntities {
	
	private MinepreggoModEntities() {}
	
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinepreggoMod.MODID);
	
	public static final RegistryObject<EntityType<HostileZombieGirl>> HOSTILE_ZOMBIE_GIRL = register("hostile_zombie_girl",
			EntityType.Builder.<HostileZombieGirl>of(HostileZombieGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostileZombieGirl::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<HostilePregnantZombieGirl.MonsterZombieGirlP3>> HOSTILE_ZOMBIE_GIRL_P3 = register("hostile_pregnant_zombie_girl_p3",
			EntityType.Builder.<HostilePregnantZombieGirl.MonsterZombieGirlP3>of(HostilePregnantZombieGirl.MonsterZombieGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantZombieGirl.MonsterZombieGirlP3::new).sized(0.625f, 1.8f));
	
	public static final RegistryObject<EntityType<HostilePregnantZombieGirl.MonsterZombieGirlP5>> HOSTILE_ZOMBIE_GIRL_P5 = register("hostile_pregnant_zombie_girl_p5",
			EntityType.Builder.<HostilePregnantZombieGirl.MonsterZombieGirlP5>of(HostilePregnantZombieGirl.MonsterZombieGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantZombieGirl.MonsterZombieGirlP5::new).sized(0.725f, 1.8f));
	
	public static final RegistryObject<EntityType<HostilePregnantZombieGirl.MonsterZombieGirlP7>> HOSTILE_ZOMBIE_GIRL_P7 = register("hostile_pregnant_zombie_girl_p7",
			EntityType.Builder.<HostilePregnantZombieGirl.MonsterZombieGirlP7>of(HostilePregnantZombieGirl.MonsterZombieGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantZombieGirl.MonsterZombieGirlP7::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirl>> TAMABLE_ZOMBIE_GIRL = register("tamable_zombie_girl",
			EntityType.Builder.<TamableZombieGirl>of(TamableZombieGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP0>> TAMABLE_ZOMBIE_GIRL_P0 = register("tamable_pregnant_zombie_girl_p0",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP0>of(TamablePregnantZombieGirl.TamableZombieGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP0::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP1>> TAMABLE_ZOMBIE_GIRL_P1 = register("tamable_pregnant_zombie_girl_p1",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP1>of(TamablePregnantZombieGirl.TamableZombieGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP2>> TAMABLE_ZOMBIE_GIRL_P2 = register("tamable_pregnant_zombie_girl_p2",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP2>of(TamablePregnantZombieGirl.TamableZombieGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP2::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP3>> TAMABLE_ZOMBIE_GIRL_P3 = register("tamable_pregnant_zombie_girl_p3",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP3>of(TamablePregnantZombieGirl.TamableZombieGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP3::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP4>> TAMABLE_ZOMBIE_GIRL_P4 = register("tamable_pregnant_zombie_girl_p4",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP4>of(TamablePregnantZombieGirl.TamableZombieGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP4::new).sized(0.65f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP5>> TAMABLE_ZOMBIE_GIRL_P5 = register("tamable_pregnant_zombie_girl_p5",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP5>of(TamablePregnantZombieGirl.TamableZombieGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP5::new).sized(0.675f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP6>> TAMABLE_ZOMBIE_GIRL_P6 = register("tamable_pregnant_zombie_girl_p6",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP6>of(TamablePregnantZombieGirl.TamableZombieGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP6::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP7>> TAMABLE_ZOMBIE_GIRL_P7 = register("tamable_pregnant_zombie_girl_p7",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP7>of(TamablePregnantZombieGirl.TamableZombieGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP7::new).sized(0.75f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantZombieGirl.TamableZombieGirlP8>> TAMABLE_ZOMBIE_GIRL_P8 = register("tamable_pregnant_zombie_girl_p8",
			EntityType.Builder.<TamablePregnantZombieGirl.TamableZombieGirlP8>of(TamablePregnantZombieGirl.TamableZombieGirlP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantZombieGirl.TamableZombieGirlP8::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<HostileHumanoidCreeperGirl>> HOSTILE_HUMANOID_CREEPER_GIRL = register("hostile_humanoid_creeper_girl",
			EntityType.Builder.<HostileHumanoidCreeperGirl>of(HostileHumanoidCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostileHumanoidCreeperGirl::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3>> HOSTILE_HUMANOID_CREEPER_GIRL_P3 = register("hostile_pregnant_humanoid_creeper_girl_p3",
			EntityType.Builder.<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3>of(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3::new).sized(0.625f, 1.8f));
	
	public static final RegistryObject<EntityType<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5>> HOSTILE_HUMANOID_CREEPER_GIRL_P5 = register("hostile_pregnant_humanoid_creeper_girl_p5",
			EntityType.Builder.<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5>of(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7>> HOSTILE_HUMANOID_CREEPER_GIRL_P7 = register("hostile_pregnant_humanoid_creeper_girl_p7",
			EntityType.Builder.<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7>of(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7::new).sized(0.775f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableHumanoidCreeperGirl>> TAMABLE_HUMANOID_CREEPER_GIRL = register("tamable_humanoid_creeper_girl",
			EntityType.Builder.<TamableHumanoidCreeperGirl>of(TamableHumanoidCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableHumanoidCreeperGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0>> TAMABLE_HUMANOID_CREEPER_GIRL_P0 = register("tamable_pregnant_humanoid_creeper_girl_p0",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1>> TAMABLE_HUMANOID_CREEPER_GIRL_P1 = register("tamable_pregnant_humanoid_creeper_girl_p1",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2>> TAMABLE_HUMANOID_CREEPER_GIRL_P2 = register("tamable_pregnant_humanoid_creeper_girl_p2",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3>> TAMABLE_HUMANOID_CREEPER_GIRL_P3 = register("tamable_pregnant_humanoid_creeper_girl_p3",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4>> TAMABLE_HUMANOID_CREEPER_GIRL_P4 = register("tamable_pregnant_humanoid_creeper_girl_p4",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4::new).sized(0.65f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5>> TAMABLE_HUMANOID_CREEPER_GIRL_P5 = register("tamable_pregnant_humanoid_creeper_girl_p5",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6>> TAMABLE_HUMANOID_CREEPER_GIRL_P6 = register("tamable_pregnant_humanoid_creeper_girl_p6",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6::new).sized(0.725f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7>> TAMABLE_HUMANOID_CREEPER_GIRL_P7 = register("tamable_pregnant_humanoid_creeper_girl_p7",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7::new).sized(0.775f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8>> TAMABLE_HUMANOID_CREEPER_GIRL_P8 = register("tamable_pregnant_humanoid_creeper_girl_p8",
			EntityType.Builder.<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8>of(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<HostileMonsterCreeperGirl>> HOSTILE_MONSTER_CREEPER_GIRL = register("hostile_monster_creeper_girl",
			EntityType.Builder.<HostileMonsterCreeperGirl>of(HostileMonsterCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostileMonsterCreeperGirl::new).sized(0.6f, 1.5f));
	
	public static final RegistryObject<EntityType<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP3>> HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P3 = register("hostile_pregnant_monster_creeper_girl_p3",
			EntityType.Builder.<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP3>of(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP3::new).sized(0.6f, 1.5f));
	
	public static final RegistryObject<EntityType<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP5>> HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P5 = register("hostile_pregnant_monster_creeper_girl_p5",
			EntityType.Builder.<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP5>of(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP5::new).sized(0.7f, 1.5f));
	
	public static final RegistryObject<EntityType<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP7>> HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P7 = register("hostile_pregnant_monster_creeper_girl_p7",
			EntityType.Builder.<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP7>of(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP7::new).sized(0.8f, 1.5f));

	public static final RegistryObject<EntityType<TamableMonsterCreeperGirl>> TAMABLE_MONSTER_CREEPER_GIRL = register("tamable_monster_creeper_girl",
			EntityType.Builder.<TamableMonsterCreeperGirl>of(TamableMonsterCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableMonsterCreeperGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0>> TAMABLE_MONSTER_CREEPER_GIRL_P0 = register("tamable_pregnant_monster_creeper_girl_p0",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1>> TAMABLE_MONSTER_CREEPER_GIRL_P1 = register("tamable_pregnant_monster_creeper_girl_p1",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2>> TAMABLE_MONSTER_CREEPER_GIRL_P2 = register("tamable_pregnant_monster_creeper_girl_p2",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3>> TAMABLE_MONSTER_CREEPER_GIRL_P3 = register("tamable_pregnant_monster_creeper_girl_p3",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4>> TAMABLE_MONSTER_CREEPER_GIRL_P4 = register("tamable_pregnant_monster_creeper_girl_p4",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4::new).sized(0.65f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5>> TAMABLE_MONSTER_CREEPER_GIRL_P5 = register("tamable_pregnant_monster_creeper_girl_p5",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6>> TAMABLE_MONSTER_CREEPER_GIRL_P6 = register("tamable_pregnant_monster_creeper_girl_p6",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6::new).sized(0.725f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7>> TAMABLE_MONSTER_CREEPER_GIRL_P7 = register("tamable_pregnant_monster_creeper_girl_p7",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7::new).sized(0.775f, 1.8f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8>> TAMABLE_MONSTER_CREEPER_GIRL_P8 = register("tamable_pregnant_monster_creeper_girl_p8",
			EntityType.Builder.<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8>of(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<HostileMonsterEnderWoman>> HOSTILE_MONSTER_ENDER_WOMAN = register("hostile_monster_ender_woman",
			EntityType.Builder.<HostileMonsterEnderWoman>of(HostileMonsterEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostileMonsterEnderWoman::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP3>> HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P3 = register("hostile_pregnant_monster_ender_woman_p3",
			EntityType.Builder.<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP3>of(HostilePregnantMonsterEnderWoman.MonsterEnderWomanP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantMonsterEnderWoman.MonsterEnderWomanP3::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP5>> HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P5 = register("hostile_pregnant_monster_ender_woman_p5",
			EntityType.Builder.<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP5>of(HostilePregnantMonsterEnderWoman.MonsterEnderWomanP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantMonsterEnderWoman.MonsterEnderWomanP5::new).sized(0.7f, 2.9f));
	
	public static final RegistryObject<EntityType<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP7>> HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P7 = register("hostile_pregnant_monster_ender_woman_p7",
			EntityType.Builder.<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP7>of(HostilePregnantMonsterEnderWoman.MonsterEnderWomanP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostilePregnantMonsterEnderWoman.MonsterEnderWomanP7::new).sized(0.8f, 2.9f));

	public static final RegistryObject<EntityType<TamableMonsterEnderWoman>> TAMABLE_MONSTER_ENDER_WOMAN = register("tamable_monster_ender_woman",
			EntityType.Builder.<TamableMonsterEnderWoman>of(TamableMonsterEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableMonsterEnderWoman::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0>> TAMABLE_MONSTER_ENDER_WOMAN_P0 = register("tamable_pregnant_monster_ender_woman_p0",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1>> TAMABLE_MONSTER_ENDER_WOMAN_P1 = register("tamable_pregnant_monster_ender_woman_p1",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2>> TAMABLE_MONSTER_ENDER_WOMAN_P2 = register("tamable_pregnant_monster_ender_woman_p2",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3>> TAMABLE_MONSTER_ENDER_WOMAN_P3 = register("tamable_pregnant_monster_ender_woman_p3",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4>> TAMABLE_MONSTER_ENDER_WOMAN_P4 = register("tamable_pregnant_monster_ender_woman_p4",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4::new).sized(0.65f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5>> TAMABLE_MONSTER_ENDER_WOMAN_P5 = register("tamable_pregnant_monster_ender_woman_p5",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5::new).sized(0.7f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6>> TAMABLE_MONSTER_ENDER_WOMAN_P6 = register("tamable_pregnant_monster_ender_woman_p6",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6::new).sized(0.725f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7>> TAMABLE_MONSTER_ENDER_WOMAN_P7 = register("tamable_pregnant_monster_ender_woman_p7",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7::new).sized(0.775f, 2.9f));
	
	public static final RegistryObject<EntityType<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8>> TAMABLE_MONSTER_ENDER_WOMAN_P8 = register("tamable_pregnant_monster_ender_woman_p8",
			EntityType.Builder.<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8>of(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8::new).sized(0.8f, 2.9f));
	
	public static final RegistryObject<EntityType<ScientificIllager>> SCIENTIFIC_ILLAGER = register("scientific_illager",
			EntityType.Builder.<ScientificIllager>of(ScientificIllager::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ScientificIllager::new).sized(0.6f, 1.95f));
	
	public static final RegistryObject<EntityType<IllMonsterEnderWoman>> ILL_ENDER_WOMAN = register("ill_monster_ender_woman",
			EntityType.Builder.<IllMonsterEnderWoman>of(IllMonsterEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllMonsterEnderWoman::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<IllMonsterCreeperGirl>> ILL_CREEPER_GIRL = register("ill_monster_creeper_girl",
			EntityType.Builder.<IllMonsterCreeperGirl>of(IllMonsterCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllMonsterCreeperGirl::new).sized(0.6f, 1.5f));
	
	public static final RegistryObject<EntityType<IllHumanoidCreeperGirl>> ILL_HUMANOID_CREEPER_GIRL = register("ill_humanoid_creeper_girl",
			EntityType.Builder.<IllHumanoidCreeperGirl>of(IllHumanoidCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllHumanoidCreeperGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<IllZombieGirl>> ILL_ZOMBIE_GIRL = register("ill_zombie_girl",
			EntityType.Builder.<IllZombieGirl>of(IllZombieGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllZombieGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<FertilityWitch>> FERTILITY_WITCH = register("fertility_witch",
			EntityType.Builder.<FertilityWitch>of(FertilityWitch::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(FertilityWitch::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<BellyPart>> BELLY_PART = register("belly_part",
			EntityType.Builder.<BellyPart>of(BellyPart::new, MobCategory.MISC).clientTrackingRange(10).setUpdateInterval(1));
	
	public static final RegistryObject<EntityType<ExplosiveDragonFireball>> EXPLOSIVE_DRAGON_FIREBALL = register("explosive_dragon_fireball",
			EntityType.Builder
			.<ExplosiveDragonFireball>of(ExplosiveDragonFireball::new, MobCategory.MISC)
            .sized(1.0F, 1.0F)
            .clientTrackingRange(4)
            .updateInterval(10)
            .fireImmune());
	
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}
}
