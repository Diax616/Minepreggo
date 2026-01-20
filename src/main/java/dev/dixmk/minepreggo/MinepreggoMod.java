package dev.dixmk.minepreggo;

import org.apache.logging.log4j.Logger;

import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationRegistry;
import dev.dixmk.minepreggo.client.gui.preggo.PlayerJoinsWorldScreen;
import dev.dixmk.minepreggo.client.gui.preggo.PlayerPrenatalCheckUpScreen;
import dev.dixmk.minepreggo.client.gui.preggo.PreggoMobPrenatalCheckUpScreen;
import dev.dixmk.minepreggo.client.gui.preggo.RequestSexM2PScreen;
import dev.dixmk.minepreggo.client.gui.preggo.RequestSexP2PScreen;
import dev.dixmk.minepreggo.client.gui.preggo.SelectPregnantEntityForPrenatalCheckUpScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlInventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlMainScreen;
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
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlInventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlMainScreen;
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
import dev.dixmk.minepreggo.client.model.entity.player.CustomBoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP0Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP1Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP2Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP3Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP5Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP6Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP7Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP8Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedBoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP0Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP1Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP2Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP3Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP5Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP6Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP7Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP8Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantFemaleHumanoidModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.particle.NauseaParticle;
import dev.dixmk.minepreggo.client.renderer.entity.FertilityWitchRenderer;
import dev.dixmk.minepreggo.client.renderer.entity.ScientificIllagerRenderer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.CustomPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.PredefinedPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP1Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP2Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP4Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP6Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP8Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.IllEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.MonsterlEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.IllZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlRenderer;
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
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlRenderer;
import dev.dixmk.minepreggo.init.MinepreggoLootModifier;
import dev.dixmk.minepreggo.init.MinepreggoModBlocks;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModParticles;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.init.MinepreggoModTabs;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.VillagerDataImpl;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterQuadrupedCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP7;
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
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterZombieGirl;
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
import dev.dixmk.minepreggo.world.item.alchemy.CreeperImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.EnderImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.ImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.PregnancyHealingBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.ZombieImpregnationPotionBrewingRecipe;

import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
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
		MinepreggoLootModifier.REGISTRY.register(modEventBus);
		MinepreggoModEntityDataSerializers.register();
		MinepreggoModDamageSources.REGISTRY.register(modEventBus);
		MinepreggoModParticles.REGISTRY.register(modEventBus); 	
		
		modEventBus.addListener(this::registerLayerDefinitions);	
		modEventBus.addListener(this::registerAttributes);
		modEventBus.addListener(this::onSpawnPlacementRegister);
		modEventBus.addListener(this::clientLoad);
		modEventBus.addListener(this::registerEntityRenderers);
		modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onRegisterRenderers);
        modEventBus.addListener(this::registerItemColors);
        modEventBus.addListener(this::registerParticleFactories);
        
        context.registerConfig(ModConfig.Type.CLIENT, MinepreggoModConfig.CLIENT_SPEC);
        context.registerConfig(ModConfig.Type.SERVER, MinepreggoModConfig.SERVER_SPEC);
	}

	private void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {
		event.register(
        		MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);			
		event.register(
        		MinepreggoModEntities.MONSTER_ENDER_WOMAN.get(),
        		SpawnPlacements.Type.NO_RESTRICTIONS,
        		Heightmap.Types.WORLD_SURFACE,
        		AbstractMonsterEnderWoman::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.REPLACE);			
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);	
		event.register(
        		MinepreggoModEntities.FERTILITY_WITCH.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		Monster::checkMonsterSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
    }
	
	private void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL.get(), MonsterZombieGirl.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(), MonsterZombieGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(), MonsterZombieGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(), MonsterZombieGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get(), TamableZombieGirl.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamableZombieGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamableZombieGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamableZombieGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamableZombieGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamableZombieGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamableZombieGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamableZombieGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), TamableZombieGirlP8.createAttributes().build());
	
		
		event.put(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL.get(), MonsterHumanoidCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P3.get(), MonsterHumanoidCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P5.get(), MonsterHumanoidCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P7.get(), MonsterHumanoidCreeperGirlP7.createAttributes().build());	
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get(), TamableHumanoidCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get(), TamableHumanoidCreeperGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get(), TamableHumanoidCreeperGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get(), TamableHumanoidCreeperGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get(), TamableHumanoidCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get(), TamableHumanoidCreeperGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5.get(), TamableHumanoidCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6.get(), TamableHumanoidCreeperGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7.get(), TamableHumanoidCreeperGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8.get(), TamableHumanoidCreeperGirlP8.createAttributes().build());

		event.put(MinepreggoModEntities.MONSTER_ENDER_WOMAN.get(), AbstractMonsterEnderWoman.createDefaultAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL.get(), AbstractMonsterQuadrupedCreeperGirl.createDefaultAttributes().build());
		
		event.put(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), ScientificIllager.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_ZOMBIE_GIRL.get(), IllZombieGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_HUMANOID_CREEPER_GIRL.get(), IllHumanoidCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), IllCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_ENDER_WOMAN.get(), IllEnderWoman.createAttributes().build());
		event.put(MinepreggoModEntities.FERTILITY_WITCH.get(), Witch.createAttributes().build());
	}
	
	
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION, AbstractZombieGirlModel::createBodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P0, AbstractZombieGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P1, AbstractZombieGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P2, AbstractZombieGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P3, AbstractZombieGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P4, AbstractZombieGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P5, AbstractZombieGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P6, AbstractZombieGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P7, AbstractZombieGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P8, AbstractZombieGirlModel::createP8BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, PregnantFemaleHumanoidModel::createInnerLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION, PregnantFemaleHumanoidModel::createOuterLayer);
		
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION, AbstractHumanoidCreeperGirlModel::createBodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P0, AbstractHumanoidCreeperGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P1, AbstractHumanoidCreeperGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P2, AbstractHumanoidCreeperGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P3, AbstractHumanoidCreeperGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P4, AbstractHumanoidCreeperGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P5, AbstractHumanoidCreeperGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P6, AbstractHumanoidCreeperGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P7, AbstractHumanoidCreeperGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P8, AbstractHumanoidCreeperGirlModel::createP8BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, PregnantFemaleHumanoidModel::createInnerLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, PregnantFemaleHumanoidModel::createOuterLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel::createBodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION, AbstractHumanoidCreeperGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P1_LOCATION, AbstractHumanoidCreeperGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P2_LOCATION, AbstractHumanoidCreeperGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION, AbstractHumanoidCreeperGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P4_LOCATION, AbstractHumanoidCreeperGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION, AbstractHumanoidCreeperGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P6_LOCATION, AbstractHumanoidCreeperGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION, AbstractHumanoidCreeperGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P8_LOCATION, AbstractHumanoidCreeperGirlModel::createP8BodyLayer);
				
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION, AbstractCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractCreeperGirlModel::createOuterLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION, AbstractCreeperGirlModel::createP0BodyLayer);		
		
		event.registerLayerDefinition(AbstractEnderWomanModel.LAYER_LOCATION, AbstractEnderWomanModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION, AbstractEnderWomanModel::createOuterLayer);
		event.registerLayerDefinition(AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel::createInnerLayer);
		
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
	
		event.registerLayerDefinition(CustomBoobsModel.LAYER_LOCATION, CustomBoobsModel::createBodyLayer);
		event.registerLayerDefinition(PredefinedBoobsModel.LAYER_LOCATION, PredefinedBoobsModel::createBodyLayer);

		event.registerLayerDefinition(CustomPregnantBodyP0Model.LAYER_LOCATION, CustomPregnantBodyP0Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP1Model.LAYER_LOCATION, CustomPregnantBodyP1Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP2Model.LAYER_LOCATION, CustomPregnantBodyP2Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP3Model.LAYER_LOCATION, CustomPregnantBodyP3Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP4Model.LAYER_LOCATION, CustomPregnantBodyP4Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP5Model.LAYER_LOCATION, CustomPregnantBodyP5Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP6Model.LAYER_LOCATION, CustomPregnantBodyP6Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP7Model.LAYER_LOCATION, CustomPregnantBodyP7Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP8Model.LAYER_LOCATION, CustomPregnantBodyP8Model::createBodyLayer);
	
		event.registerLayerDefinition(PredefinedPregnantBodyP0Model.LAYER_LOCATION, PredefinedPregnantBodyP0Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP1Model.LAYER_LOCATION, PredefinedPregnantBodyP1Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP2Model.LAYER_LOCATION, PredefinedPregnantBodyP2Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP3Model.LAYER_LOCATION, PredefinedPregnantBodyP3Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP4Model.LAYER_LOCATION, PredefinedPregnantBodyP4Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP5Model.LAYER_LOCATION, PredefinedPregnantBodyP5Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP6Model.LAYER_LOCATION, PredefinedPregnantBodyP6Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP7Model.LAYER_LOCATION, PredefinedPregnantBodyP7Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP8Model.LAYER_LOCATION, PredefinedPregnantBodyP8Model::createBodyLayer);
	}
	
	private void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_MAIN_MENU.get(), ZombieGirlMainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_INVENTORY_MENU.get(), ZombieGirlInventaryScreen::new);
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
			
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_MAIN_MENU.get(), CreeperGirlMainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_INVENTORY_MENU.get(), CreeperGirlInventaryScreen::new);
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
		
			MenuScreens.register(MinepreggoModMenus.SELECT_PREGNANT_ENTITY_FOR_PRENATAL_CHECKUP_MENU.get(), SelectPregnantEntityForPrenatalCheckUpScreen::new);
			
			MenuScreens.register(MinepreggoModMenus.PREGGO_MOB_PRENATAL_CHECKUP_MENU.get(), PreggoMobPrenatalCheckUpScreen::new);
			MenuScreens.register(MinepreggoModMenus.PLAYER_PRENATAL_CHECKUP_BY_ILLAGER_MENU.get(), PlayerPrenatalCheckUpScreen.IllagerScreen::new);
			MenuScreens.register(MinepreggoModMenus.PLAYER_PRENATAL_CHECKUP_BY_VILLAGER_MENU.get(), PlayerPrenatalCheckUpScreen.VillagerScreen::new);

			MenuScreens.register(MinepreggoModMenus.PLAYER_JOINS_WORLD_MENU.get(), PlayerJoinsWorldScreen::new);
		
			MenuScreens.register(MinepreggoModMenus.REQUEST_SEX_M2P_MENU.get(), RequestSexM2PScreen::new);
			MenuScreens.register(MinepreggoModMenus.REQUEST_SEX_P2P_MENU.get(), RequestSexP2PScreen::new);
		
			PlayerAnimationRegistry.getInstance().init();
		});
	}
	
	private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL.get(), MonsterZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(), MonsterZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(), MonsterZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(), MonsterZombieGirlP7Renderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get(), TamableZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamableZombieGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamableZombieGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamableZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamableZombieGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamableZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamableZombieGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamableZombieGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), TamableZombieGirlP8Renderer::new);
		
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL.get(), MonsterHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P3.get(), MonsterHumanoidCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P5.get(), MonsterHumanoidCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P7.get(), MonsterHumanoidCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get(), TamableHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get(), TamableHumanoidCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get(), TamableHumanoidCreeperGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get(), TamableHumanoidCreeperGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get(), TamableHumanoidCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get(), TamableHumanoidCreeperGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5.get(), TamableHumanoidCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6.get(), TamableHumanoidCreeperGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7.get(), TamableHumanoidCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8.get(), TamableHumanoidCreeperGirlP8Renderer::new);
				
		event.registerEntityRenderer(MinepreggoModEntities.ILL_HUMANOID_CREEPER_GIRL.get(), IllHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_ZOMBIE_GIRL.get(), IllZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_ENDER_WOMAN.get(), IllEnderWomanRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), IllCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), ScientificIllagerRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.FERTILITY_WITCH.get(), FertilityWitchRenderer::new);

		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ENDER_WOMAN.get(), MonsterlEnderWomanRenderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL.get(), MonsterCreeperGirlRenderer::new);
	}
	
	private void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(PlayerDataImpl.class);
		event.register(VillagerDataImpl.class);
	}
	
    private void commonSetup(FMLCommonSetupEvent event) {		
        event.enqueueWork(() -> {
        	BrewingRecipeRegistry.addRecipe(new CreeperImpregnationPotionBrewingRecipe.A0());
        	BrewingRecipeRegistry.addRecipe(new CreeperImpregnationPotionBrewingRecipe.A1());
        	BrewingRecipeRegistry.addRecipe(new CreeperImpregnationPotionBrewingRecipe.A2());
        	BrewingRecipeRegistry.addRecipe(new CreeperImpregnationPotionBrewingRecipe.A3());
        	BrewingRecipeRegistry.addRecipe(new CreeperImpregnationPotionBrewingRecipe.A4());
        	BrewingRecipeRegistry.addRecipe(new EnderImpregnationPotionBrewingRecipe.A0());
        	BrewingRecipeRegistry.addRecipe(new EnderImpregnationPotionBrewingRecipe.A1());
        	BrewingRecipeRegistry.addRecipe(new EnderImpregnationPotionBrewingRecipe.A2());
        	BrewingRecipeRegistry.addRecipe(new EnderImpregnationPotionBrewingRecipe.A3());
        	BrewingRecipeRegistry.addRecipe(new EnderImpregnationPotionBrewingRecipe.A4());
        	BrewingRecipeRegistry.addRecipe(new ZombieImpregnationPotionBrewingRecipe.A0());
        	BrewingRecipeRegistry.addRecipe(new ZombieImpregnationPotionBrewingRecipe.A1());
        	BrewingRecipeRegistry.addRecipe(new ZombieImpregnationPotionBrewingRecipe.A2());
        	BrewingRecipeRegistry.addRecipe(new ZombieImpregnationPotionBrewingRecipe.A3());
        	BrewingRecipeRegistry.addRecipe(new ZombieImpregnationPotionBrewingRecipe.A4());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A0());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A1());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A2());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A3());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A4());
        	BrewingRecipeRegistry.addRecipe(new PregnancyHealingBrewingRecipe());
            ComposterBlock.COMPOSTABLES.put(MinepreggoModItems.CHILI_PEPPER.get(), 0.6f);
            ComposterBlock.COMPOSTABLES.put(MinepreggoModItems.CUCUMBER.get(), 0.7f);
            ComposterBlock.COMPOSTABLES.put(MinepreggoModItems.LEMON.get(), 0.6f);
                    
            MinepreggoModPacketHandler.registerMessages();
        });	
    }
    
    private void onRegisterRenderers(EntityRenderersEvent.AddLayers event) {
        addBoobsLayerToSkin(event, "default");
        addBoobsLayerToSkin(event, "slim");
    }

    private void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack),     		
        MinepreggoModItems.FEMALE_LEATHER_P0_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P1_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P2_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P3_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P4_CHESTPLATE.get(),
        MinepreggoModItems.LEATHER_KNEE_BRACE.get());    
    }
    
    private void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MinepreggoModParticles.NAUSEA.get(), NauseaParticle::provider);
    }
    
	private void addBoobsLayerToSkin(EntityRenderersEvent.AddLayers event, String skinName) {
        EntityRenderer<? extends Player> renderer = event.getPlayerSkin(skinName);
        if (renderer instanceof PlayerRenderer playerRenderer) {
            playerRenderer.addLayer(new CustomPregnantBodyLayer(playerRenderer, event.getEntityModels()));
            playerRenderer.addLayer(new PredefinedPregnantBodyLayer(playerRenderer, event.getEntityModels()));
        }
    }	
}
