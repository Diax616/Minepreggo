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
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.levelgen.Heightmap;
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
        context.registerConfig(ModConfig.Type.CLIENT, MinepreggoModConfig.CLIENT_SPEC);
        context.registerConfig(ModConfig.Type.SERVER, MinepreggoModConfig.SERVER_SPEC);
		
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
			
		modEventBus.addListener(this::registerAttributes);
		modEventBus.addListener(this::onSpawnPlacementRegister);
		modEventBus.addListener(this::clientLoad);
		modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::commonSetup);      
        modEventBus.addListener(MinepreggoModConfig::onLoad);     
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
}
