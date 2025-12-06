package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllQuadrupedCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterQuadrupedCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP8;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MinepreggoModEntities {
	
	private MinepreggoModEntities() {}
	
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinepreggoMod.MODID);
	
	/*Zombie Girl*/
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
	

	/*Creeper Girl*/
	public static final RegistryObject<EntityType<MonsterCreeperGirl>> MONSTER_CREEPER_GIRL = register("monster_creeper_girl",
			EntityType.Builder.<MonsterCreeperGirl>of(MonsterCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterCreeperGirl::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<MonsterCreeperGirlP3>> MONSTER_CREEPER_GIRL_P3 = register("monster_creeper_girl_p3",
			EntityType.Builder.<MonsterCreeperGirlP3>of(MonsterCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterCreeperGirlP3::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterCreeperGirlP5>> MONSTER_CREEPER_GIRL_P5 = register("monster_creeper_girl_p5",
			EntityType.Builder.<MonsterCreeperGirlP5>of(MonsterCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterCreeperGirlP5::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<MonsterCreeperGirlP7>> MONSTER_CREEPER_GIRL_P7 = register("monster_creeper_girl_p7",
			EntityType.Builder.<MonsterCreeperGirlP7>of(MonsterCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterCreeperGirlP7::new).sized(1.0f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirl>> TAMABLE_CREEPER_GIRL = register("tamable_creeper_girl",
			EntityType.Builder.<TamableCreeperGirl>of(TamableCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP0>> TAMABLE_CREEPER_GIRL_P0 = register("tamable_creeper_girl_p0",
			EntityType.Builder.<TamableCreeperGirlP0>of(TamableCreeperGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP0::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP1>> TAMABLE_CREEPER_GIRL_P1 = register("tamable_creeper_girl_p1",
			EntityType.Builder.<TamableCreeperGirlP1>of(TamableCreeperGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP2>> TAMABLE_CREEPER_GIRL_P2 = register("tamable_creeper_girl_p2",
			EntityType.Builder.<TamableCreeperGirlP2>of(TamableCreeperGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP2::new).sized(0.65f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP3>> TAMABLE_CREEPER_GIRL_P3 = register("tamable_creeper_girl_p3",
			EntityType.Builder.<TamableCreeperGirlP3>of(TamableCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP3::new).sized(0.7f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP4>> TAMABLE_CREEPER_GIRL_P4 = register("tamable_creeper_girl_p4",
			EntityType.Builder.<TamableCreeperGirlP4>of(TamableCreeperGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP4::new).sized(0.75f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP5>> TAMABLE_CREEPER_GIRL_P5 = register("tamable_creeper_girl_p5",
			EntityType.Builder.<TamableCreeperGirlP5>of(TamableCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP5::new).sized(0.8f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP6>> TAMABLE_CREEPER_GIRL_P6 = register("tamable_creeper_girl_p6",
			EntityType.Builder.<TamableCreeperGirlP6>of(TamableCreeperGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP6::new).sized(0.9f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP7>> TAMABLE_CREEPER_GIRL_P7 = register("tamable_creeper_girl_p7",
			EntityType.Builder.<TamableCreeperGirlP7>of(TamableCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP7::new).sized(1.0f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP8>> TAMABLE_CREEPER_GIRL_P8 = register("tamable_creeper_girl_p8",
			EntityType.Builder.<TamableCreeperGirlP8>of(TamableCreeperGirlP8::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP8::new).sized(1.1f, 1.8f));
	
	
	public static final RegistryObject<EntityType<MonsterQuadrupedCreeperGirlP0>> MONSTER_QUADRUPED_CREEPER_GIRL_P0 = register("monster_quadruped_creeper_girl_p0",
			EntityType.Builder.<MonsterQuadrupedCreeperGirlP0>of(MonsterQuadrupedCreeperGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterQuadrupedCreeperGirlP0::new).sized(0.6f, 1.7f));
	
	
	
	/*Illager*/
	public static final RegistryObject<EntityType<ScientificIllager>> SCIENTIFIC_ILLAGER = register("scientific_illager",
			EntityType.Builder.<ScientificIllager>of(ScientificIllager::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ScientificIllager::new).sized(0.6f, 1.95f));
	
	public static final RegistryObject<EntityType<IllEnderWoman>> ILL_ENDER_WOMAN = register("ill_ender_woman",
			EntityType.Builder.<IllEnderWoman>of(IllEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllEnderWoman::new).sized(0.6f, 2.9f));
	
	public static final RegistryObject<EntityType<IllQuadrupedCreeperGirl>> ILL_QUADRUPED_CREEPER_GIRL = register("ill_queadruped_creeper_girl",
			EntityType.Builder.<IllQuadrupedCreeperGirl>of(IllQuadrupedCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllQuadrupedCreeperGirl::new).sized(0.6f, 1.7f));
	
	public static final RegistryObject<EntityType<IllCreeperGirl>> ILL_CREEPER_GIRL = register("ill_creeper_girl",
			EntityType.Builder.<IllCreeperGirl>of(IllCreeperGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllCreeperGirl::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<IllZombieGirl>> ILL_ZOMBIE_GIRL = register("ill_zombie_girl",
			EntityType.Builder.<IllZombieGirl>of(IllZombieGirl::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(IllZombieGirl::new).sized(0.6f, 1.8f));
	
	/*Ender*/
	public static final RegistryObject<EntityType<MonsterEnderWoman>> MONSTER_ENDER_WOMAN = register("monster_ender_woman",
			EntityType.Builder.<MonsterEnderWoman>of(MonsterEnderWoman::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterEnderWoman::new).sized(0.6f, 2.9f));
	
	
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}
}
