package dev.dixmk.minepreggo;

import org.apache.logging.log4j.Logger;

import dev.dixmk.minepreggo.client.gui.preggo.PlayerJoinsWorldScreen;
import dev.dixmk.minepreggo.client.gui.preggo.PlayerMedicalCheckUpScreen;
import dev.dixmk.minepreggo.client.gui.preggo.PreggoMobMedicalCheckUpScreen;
import dev.dixmk.minepreggo.client.gui.preggo.SelectPregnantEntityForMedicalCheckUpScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP0InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP0MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP1InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP1MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP2InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP2MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP3InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP3MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP4InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP4MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP5InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP5MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP6InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP6MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP7InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP7MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP8InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP8MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP0InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP0MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP1InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP1MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP2InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP2MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP3InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP3MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP4InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP4MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP5InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP5MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP6InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP6MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP7InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP7MainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP8InventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP8MainScreen;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP5Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP6Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP7Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP8Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP0Model;
import dev.dixmk.minepreggo.client.model.armor.MaternityChestPlateP1Model;
import dev.dixmk.minepreggo.client.model.armor.MaternityChestPlateP2Model;
import dev.dixmk.minepreggo.client.model.armor.MaternityChestPlateP3Model;
import dev.dixmk.minepreggo.client.model.armor.MaternityChestPlateP4Model;
import dev.dixmk.minepreggo.client.model.armor.KneeBraceModel;
import dev.dixmk.minepreggo.client.model.entity.player.BoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP1Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP2Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP3Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP5Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP6Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP7Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP8Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractQueadrupedCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.ScientificIllagerRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP1Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP2Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP4Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP6Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP8Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.quadruped.IllQuadrupedCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.quadruped.MonsterQuadrupedCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.IllEnderGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.MonsterlEnderGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.IllZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP1Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP2Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP4Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP6Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP8Renderer;
import dev.dixmk.minepreggo.init.MinepreggoModBlocks;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.init.MinepreggoModTabs;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterQuadrupedCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllQuadrupedCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP8;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractMonsterEnderGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.IllZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP8;

import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;

@Mod(MinepreggoMod.MODID)
public class MinepreggoMod {
	public static final Logger LOGGER = LogManager.getLogger(MinepreggoMod.class);
	
	public static final String MODID = "minepreggo";

	public MinepreggoMod(FMLJavaModLoadingContext context) {
		MinecraftForge.EVENT_BUS.register(this);
	
		IEventBus modEventBus = context.getModEventBus();	
		MinepreggoModItems.REGISTRY.register(modEventBus);
		MinepreggoModEntities.REGISTRY.register(modEventBus);
		MinepreggoModBlocks.REGISTRY.register(modEventBus);
		MinepreggoModTabs.REGISTRY.register(modEventBus);
		MinepreggoModSounds.REGISTRY.register(modEventBus);
		MinepreggoModTabs.REGISTRY.register(modEventBus);
		MinepreggoModMenus.REGISTRY.register(modEventBus);
		MinepreggoModMobEffects.REGISTRY.register(modEventBus);
		MinepreggoModPotions.REGISTRY.register(modEventBus);
		MinepreggoModVillagerProfessions.REGISTRY.register(modEventBus);
		MinepreggoModEntityDataSerializers.register();
       	
		modEventBus.addListener(this::registerLayerDefinitions);	
		modEventBus.addListener(this::registerAttributes);
		modEventBus.addListener(this::onSpawnPlacementRegister);
		modEventBus.addListener(this::clientLoad);
		modEventBus.addListener(this::registerEntityRenderers);
		modEventBus.addListener(this::registerCapabilities);
		
		context.registerConfig(ModConfig.Type.CLIENT, MinepreggoModConfig.CLIENT_SPEC);
		context.registerConfig(ModConfig.Type.COMMON, MinepreggoModConfig.COMMON_SPEC);
		context.registerConfig(ModConfig.Type.SERVER, MinepreggoModConfig.SERVER_SPEC);
	}

	private void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkMonsterZombieGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkMonsterZombieGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkMonsterZombieGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkMonsterZombieGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);			
		event.register(
        		MinepreggoModEntities.MONSTER_ENDER_GIRL_P0.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterEnderGirl::checkMonsterEnderGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);			
		event.register(
        		MinepreggoModEntities.MONSTER_QUADRUPED_CREEPER_GIRL_P0.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);	
    }
	
	private void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get(), MonsterZombieGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(), MonsterZombieGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(), MonsterZombieGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(), MonsterZombieGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamableZombieGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamableZombieGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamableZombieGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamableZombieGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamableZombieGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamableZombieGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamableZombieGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), TamableZombieGirlP8.createAttributes().build());
	
		
		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(), MonsterCreeperGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P3.get(), MonsterCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P5.get(), MonsterCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P7.get(), MonsterCreeperGirlP7.createAttributes().build());	
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), TamableCreeperGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get(), TamableCreeperGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P2.get(), TamableCreeperGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P3.get(), TamableCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P4.get(), TamableCreeperGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P5.get(), TamableCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P6.get(), TamableCreeperGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P7.get(), TamableCreeperGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P8.get(), TamableCreeperGirlP8.createAttributes().build());

		event.put(MinepreggoModEntities.MONSTER_ENDER_GIRL_P0.get(), AbstractMonsterEnderGirl.createDefaultAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_QUADRUPED_CREEPER_GIRL_P0.get(), AbstractMonsterQuadrupedCreeperGirl.createDefaultAttributes().build());
		
		event.put(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), ScientificIllager.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_ZOMBIE_GIRL.get(), IllZombieGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), IllCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_QUADRUPED_CREEPER_GIRL.get(), IllQuadrupedCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_ENDER_GIRL.get(), IllEnderGirl.createAttributes().build());
	}
	
	
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		
		/*Zombie Girl*/
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P0, AbstractZombieGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P1, AbstractZombieGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P2, AbstractZombieGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P3, AbstractZombieGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P4, AbstractZombieGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P5, AbstractZombieGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P6, AbstractZombieGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P7, AbstractZombieGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P8, AbstractZombieGirlModel::createP8BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel::createInnerLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractZombieGirlModel::createOuterLayer);
		
		/*Creeper Girl*/
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P0, AbstractHumanoidCreeperGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P1, AbstractHumanoidCreeperGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P2, AbstractHumanoidCreeperGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P3, AbstractHumanoidCreeperGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P4, AbstractHumanoidCreeperGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P5, AbstractHumanoidCreeperGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P6, AbstractHumanoidCreeperGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P7, AbstractHumanoidCreeperGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P8, AbstractHumanoidCreeperGirlModel::createP8BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel::createInnerLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel::createOuterLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION, AbstractHumanoidCreeperGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P1_LOCATION, AbstractHumanoidCreeperGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P2_LOCATION, AbstractHumanoidCreeperGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION, AbstractHumanoidCreeperGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P4_LOCATION, AbstractHumanoidCreeperGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION, AbstractHumanoidCreeperGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P6_LOCATION, AbstractHumanoidCreeperGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION, AbstractHumanoidCreeperGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P8_LOCATION, AbstractHumanoidCreeperGirlModel::createP8BodyLayer);
			
		/*Quadruped*/	
		event.registerLayerDefinition(AbstractQueadrupedCreeperGirlModel.LAYER_LOCATION_P0, AbstractQueadrupedCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(AbstractQueadrupedCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractQueadrupedCreeperGirlModel::createOuterLayer);
		event.registerLayerDefinition(AbstractQueadrupedCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION, AbstractQueadrupedCreeperGirlModel::createP0BodyLayer);		
		
		/*Ender*/
		event.registerLayerDefinition(AbstractEnderGirlModel.LAYER_LOCATION_P0, AbstractEnderGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractEnderGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractEnderGirlModel::createOuterLayer);
		event.registerLayerDefinition(AbstractEnderGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderGirlModel::createInnerLayer);
		
		/*Maternal Armors*/
		event.registerLayerDefinition(BellyShieldP5Model.LAYER_LOCATION, BellyShieldP5Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP6Model.LAYER_LOCATION, BellyShieldP6Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP7Model.LAYER_LOCATION, BellyShieldP7Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP8Model.LAYER_LOCATION, BellyShieldP8Model::createBodyLayer);
		event.registerLayerDefinition(FemaleChestPlateP0Model.LAYER_LOCATION, FemaleChestPlateP0Model::createBodyLayer);
		event.registerLayerDefinition(MaternityChestPlateP1Model.LAYER_LOCATION, MaternityChestPlateP1Model::createBodyLayer);
		event.registerLayerDefinition(MaternityChestPlateP2Model.LAYER_LOCATION, MaternityChestPlateP2Model::createBodyLayer);
		event.registerLayerDefinition(MaternityChestPlateP3Model.LAYER_LOCATION, MaternityChestPlateP3Model::createBodyLayer);
		event.registerLayerDefinition(MaternityChestPlateP4Model.LAYER_LOCATION, MaternityChestPlateP4Model::createBodyLayer);
		event.registerLayerDefinition(KneeBraceModel.LAYER_LOCATION, KneeBraceModel::createBodyLayer);
	
		// Boobs
		event.registerLayerDefinition(BoobsModel.LAYER_LOCATION, BoobsModel::createBodyLayer);
		event.registerLayerDefinition(PregnantBodyP1Model.LAYER_LOCATION, PregnantBodyP1Model::createBodyLayer);
		event.registerLayerDefinition(PregnantBodyP2Model.LAYER_LOCATION, PregnantBodyP2Model::createBodyLayer);
		event.registerLayerDefinition(PregnantBodyP3Model.LAYER_LOCATION, PregnantBodyP3Model::createBodyLayer);
		event.registerLayerDefinition(PregnantBodyP4Model.LAYER_LOCATION, PregnantBodyP4Model::createBodyLayer);
		event.registerLayerDefinition(PregnantBodyP5Model.LAYER_LOCATION, PregnantBodyP5Model::createBodyLayer);
		event.registerLayerDefinition(PregnantBodyP6Model.LAYER_LOCATION, PregnantBodyP6Model::createBodyLayer);
		event.registerLayerDefinition(PregnantBodyP7Model.LAYER_LOCATION, PregnantBodyP7Model::createBodyLayer);
		event.registerLayerDefinition(PregnantBodyP8Model.LAYER_LOCATION, PregnantBodyP8Model::createBodyLayer);
	}
	
	private void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			/*Zombie Girl*/
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P0_MAIN_MENU.get(), ZombieGirlP0MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P0_INVENTORY_MENU.get(), ZombieGirlP0InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P1_MAIN_MENU.get(), ZombieGirlP1MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P1_INVENTORY_MENU.get(), ZombieGirlP1InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P2_MAIN_MENU.get(), ZombieGirlP2MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P2_INVENTORY_MENU.get(), ZombieGirlP2InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P3_MAIN_MENU.get(), ZombieGirlP3MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P3_INVENTORY_MENU.get(), ZombieGirlP3InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P4_MAIN_MENU.get(), ZombieGirlP4MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P4_INVENTORY_MENU.get(), ZombieGirlP4InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P5_MAIN_MENU.get(), ZombieGirlP5MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P5_INVENTORY_MENU.get(), ZombieGirlP5InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P6_MAIN_MENU.get(), ZombieGirlP6MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P6_INVENTORY_MENU.get(), ZombieGirlP6InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P7_MAIN_MENU.get(), ZombieGirlP7MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P7_INVENTORY_MENU.get(), ZombieGirlP7InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P8_MAIN_MENU.get(), ZombieGirlP8MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P8_INVENTORY_MENU.get(), ZombieGirlP8InventaryScreen::new);
			
			/*Creeper Girl*/
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_MAIN_MENU.get(), CreeperGirlP0MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_INVENTORY_MENU.get(), CreeperGirlP0InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P1_MAIN_MENUI.get(), CreeperGirlP1MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P1_INVENTORY_MENU.get(), CreeperGirlP1InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P2_MAIN_MENU.get(), CreeperGirlP2MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P2_INVENTORY_MENU.get(), CreeperGirlP2InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P3_MAIN_MENU.get(), CreeperGirlP3MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P3_INVENTORY_MENU.get(), CreeperGirlP3InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P4_MAIN_MENU.get(), CreeperGirlP4MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P4_INVENTORY_MENU.get(), CreeperGirlP4InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P5_MAIN_MENUI.get(), CreeperGirlP5MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P5_INVENTORY_MENU.get(), CreeperGirlP5InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P6_MAIN_MENU.get(), CreeperGirlP6MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P6_INVENTORY_MENU.get(), CreeperGirlP6InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P7_MAIN_MENU.get(), CreeperGirlP7MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P7_INVENTORY_MENUI.get(), CreeperGirlP7InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P8_MAIN_MENU.get(), CreeperGirlP8MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P8_INVENTORY_MENU.get(), CreeperGirlP8InventaryScreen::new);
		
			MenuScreens.register(MinepreggoModMenus.PREGGO_MOB_MEDICAL_CHECKUP_MENU.get(), PreggoMobMedicalCheckUpScreen::new);
			MenuScreens.register(MinepreggoModMenus.SELECT_PREGNANT_ENTITY_FOR_MEDICAL_CHECKUP_MENU.get(), SelectPregnantEntityForMedicalCheckUpScreen::new);
		
			MenuScreens.register(MinepreggoModMenus.PLAYER_MEDICAL_CHECKUP_BY_ILLAGER_MENU.get(), PlayerMedicalCheckUpScreen.Illager::new);
			MenuScreens.register(MinepreggoModMenus.PLAYER_MEDICAL_CHECKUP_BY_VILLAGER_MENU.get(), PlayerMedicalCheckUpScreen.DoctorVillager::new);

			MenuScreens.register(MinepreggoModMenus.PLAYER_JOINS_WORLD_MENU.get(), PlayerJoinsWorldScreen::new);
		});
	}
	
	private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get(), MonsterZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(), MonsterZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(), MonsterZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(), MonsterZombieGirlP7Renderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamableZombieGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamableZombieGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamableZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamableZombieGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamableZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamableZombieGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamableZombieGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), TamableZombieGirlP8Renderer::new);
		
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(), MonsterCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P3.get(), MonsterCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P5.get(), MonsterCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P7.get(), MonsterCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), TamableCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get(), TamableCreeperGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P2.get(), TamableCreeperGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P3.get(), TamableCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P4.get(), TamableCreeperGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P5.get(), TamableCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P6.get(), TamableCreeperGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P7.get(), TamableCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P8.get(), TamableCreeperGirlP8Renderer::new);
				
		event.registerEntityRenderer(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), IllCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_ZOMBIE_GIRL.get(), IllZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_ENDER_GIRL.get(), IllEnderGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_QUADRUPED_CREEPER_GIRL.get(), IllQuadrupedCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), ScientificIllagerRenderer::new);
		
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ENDER_GIRL_P0.get(), MonsterlEnderGirlP0Renderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_QUADRUPED_CREEPER_GIRL_P0.get(), MonsterQuadrupedCreeperGirlP0Renderer::new);
	}
	
	private void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(PlayerPregnancySystemImpl.class);
		event.register(PlayerDataImpl.class);
		event.register(PregnancyEffectsImpl.class);
	}
}
