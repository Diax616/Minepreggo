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
import dev.dixmk.minepreggo.client.gui.preggo.creeper.PregnantHumanoidCreeperGirInventoryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.PregnantHumanoidCreeperGirlMainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.PregnantMonsterCreeperGirInventoryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.PregnantMonsterCreeperGirlMainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.MonsterCreeperGirlInventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.MonsterCreeperGirlMainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.ender.MonsterEnderWomanInventoryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.ender.MonsterEnderWomanMainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.ender.MonsterPregnantEnderWomanInventoryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.ender.MonsterPregnantEnderWomanMainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.PregnantZombieGirlInventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.PregnantZombieGirlMainScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlInventaryScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlMainScreen;
import dev.dixmk.minepreggo.init.MinepreggoLootModifier;
import dev.dixmk.minepreggo.init.MinepreggoModAdvancements;
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
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostileCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostileHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostileMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilePregnantMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostileEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostilePregnantMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostileMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamablePregnantMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostileZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.IllZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostilePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostileZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.item.alchemy.CreeperImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.EnderDragonImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.EnderImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.EnderPowerfulImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.ImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.PregnancyHealingBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.PregnancyResistancePotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.VillagerImpregnationPotionBrewingRecipe;
import dev.dixmk.minepreggo.world.item.alchemy.ZeroGravityBellyPotionBrewingRecipe;
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
        		MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractHostileCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantHumanoidCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantHumanoidCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantHumanoidCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractHostileZombieGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantZombieGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantZombieGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantZombieGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);			
		event.register(
        		MinepreggoModEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(),
        		SpawnPlacements.Type.NO_RESTRICTIONS,
        		Heightmap.Types.WORLD_SURFACE,
        		AbstractHostileEnderWoman::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.REPLACE);	
		event.register(
        		MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P3.get(),
        		SpawnPlacements.Type.NO_RESTRICTIONS,
        		Heightmap.Types.WORLD_SURFACE,
        		HostilePregnantMonsterEnderWoman::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.REPLACE);	
		event.register(
        		MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P5.get(),
        		SpawnPlacements.Type.NO_RESTRICTIONS,
        		Heightmap.Types.WORLD_SURFACE,
        		HostilePregnantMonsterEnderWoman::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.REPLACE);	
		event.register(
        		MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P7.get(),
        		SpawnPlacements.Type.NO_RESTRICTIONS,
        		Heightmap.Types.WORLD_SURFACE,
        		HostilePregnantMonsterEnderWoman::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.REPLACE);		
		event.register(
        		MinepreggoModEntities.HOSTILE_MONSTER_CREEPER_GIRL.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractHostileCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);	
		event.register(
        		MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantMonsterCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);	
		event.register(
        		MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantMonsterCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);	
		event.register(
        		MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		HostilePregnantMonsterCreeperGirl::checkSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);	
		event.register(
        		MinepreggoModEntities.FERTILITY_WITCH.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		Monster::checkMonsterSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
    }
	
	private void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL.get(), HostileZombieGirl.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P3.get(), HostilePregnantZombieGirl.MonsterZombieGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P5.get(), HostilePregnantZombieGirl.MonsterZombieGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P7.get(), HostilePregnantZombieGirl.MonsterZombieGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get(), TamableZombieGirl.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamablePregnantZombieGirl.TamableZombieGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamablePregnantZombieGirl.TamableZombieGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamablePregnantZombieGirl.TamableZombieGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamablePregnantZombieGirl.TamableZombieGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamablePregnantZombieGirl.TamableZombieGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamablePregnantZombieGirl.TamableZombieGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamablePregnantZombieGirl.TamableZombieGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamablePregnantZombieGirl.TamableZombieGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), TamablePregnantZombieGirl.TamableZombieGirlP8.createAttributes().build());
	
		event.put(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL.get(), HostileHumanoidCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P3.get(), HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P5.get(), HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P7.get(), HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7.createAttributes().build());	
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get(), TamableHumanoidCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8.get(), TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8.createAttributes().build());

		event.put(MinepreggoModEntities.HOSTILE_MONSTER_CREEPER_GIRL.get(), HostileMonsterCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P3.get(), HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P5.get(), HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P7.get(), HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL.get(), TamableMonsterCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P0.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P1.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P2.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P3.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P4.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P5.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P6.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P7.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P8.get(), TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8.createAttributes().build());

		event.put(MinepreggoModEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(), HostileMonsterEnderWoman.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P3.get(), HostilePregnantMonsterEnderWoman.MonsterEnderWomanP3.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P5.get(), HostilePregnantMonsterEnderWoman.MonsterEnderWomanP5.createAttributes().build());
		event.put(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P7.get(), HostilePregnantMonsterEnderWoman.MonsterEnderWomanP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN.get(), TamableMonsterEnderWoman.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P1.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P2.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P3.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P4.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P5.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P6.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P7.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P8.get(), TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8.createAttributes().build());
	
		event.put(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), ScientificIllager.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_ZOMBIE_GIRL.get(), IllZombieGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_HUMANOID_CREEPER_GIRL.get(), IllHumanoidCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), IllMonsterCreeperGirl.createAttributes().build());
		event.put(MinepreggoModEntities.ILL_ENDER_WOMAN.get(), IllMonsterEnderWoman.createAttributes().build());
		event.put(MinepreggoModEntities.FERTILITY_WITCH.get(), Witch.createAttributes().build());
	}
	
	private void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_MAIN_MENU.get(), ZombieGirlMainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_INVENTORY_MENU.get(), ZombieGirlInventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P0_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP0MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P0_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP0InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P1_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP1MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P1_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP1InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P2_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP2MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P2_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP2InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P3_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP3MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P3_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP3InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P4_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP4MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P4_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP4InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P5_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP5MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P5_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP5InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P6_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP6MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P6_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP6InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P7_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP7MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P7_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP7InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P8_MAIN_MENU.get(), PregnantZombieGirlMainScreen.ZombieGirlP8MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P8_INVENTORY_MENU.get(), PregnantZombieGirlInventaryScreen.ZombieGirlP8InventaryScreen::new);
			
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_MAIN_MENU.get(), CreeperGirlMainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_INVENTORY_MENU.get(), CreeperGirlInventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_MAIN_MENU.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP0MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP0InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P1_MAIN_MENUI.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP1MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P1_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP1InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P2_MAIN_MENU.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP2MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P2_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP2InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P3_MAIN_MENU.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP3MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P3_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP3InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P4_MAIN_MENU.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP4MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P4_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP4InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P5_MAIN_MENU.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP5MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P5_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP5InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P6_MAIN_MENU.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP6MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P6_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP6InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P7_MAIN_MENU.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP7MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P7_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP7InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P8_MAIN_MENU.get(), PregnantHumanoidCreeperGirlMainScreen.CreeperGirlP8MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P8_INVENTORY_MENU.get(), PregnantHumanoidCreeperGirInventoryScreen.CreeperGirlP8InventaryScreen::new);
				
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_INVENTORY_MENU.get(), MonsterEnderWomanInventoryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_MAIN_MENU.get(), MonsterEnderWomanMainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P0_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP0MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P0_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP0InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P1_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP1MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P1_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP1InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P2_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP2MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P2_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP2InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P3_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP3MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P3_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP3InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P4_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP4MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P4_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP4InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P5_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP5MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P5_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP5InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P6_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP6MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P6_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP6InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P7_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP7MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P7_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP7InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P8_MAIN_MENU.get(), MonsterPregnantEnderWomanMainScreen.MonsterEnderWomanP8MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_ENDER_WOMAN_P8_INVENTORY_MENU.get(), MonsterPregnantEnderWomanInventoryScreen.MonsterEnderWomanP8InventaryScreen::new);
			
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_INVENTORY_MENU.get(), MonsterCreeperGirlInventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_MAIN_MENU.get(), MonsterCreeperGirlMainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P0_MAIN_MENU.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP0MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P0_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP0InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P1_MAIN_MENUI.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP1MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P1_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP1InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P2_MAIN_MENU.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP2MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P2_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP2InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P3_MAIN_MENU.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP3MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P3_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP3InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P4_MAIN_MENU.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP4MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P4_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP4InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P5_MAIN_MENU.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP5MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P5_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP5InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P6_MAIN_MENU.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP6MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P6_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP6InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P7_MAIN_MENU.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP7MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P7_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP7InventaryScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P8_MAIN_MENU.get(), PregnantMonsterCreeperGirlMainScreen.MonsterCreeperGirlP8MainScreen::new);
			MenuScreens.register(MinepreggoModMenus.MONSTER_CREEPER_GIRL_P8_INVENTORY_MENU.get(), PregnantMonsterCreeperGirInventoryScreen.MonsterCreeperGirlP8InventaryScreen::new);
						
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
        	BrewingRecipeRegistry.addRecipe(new VillagerImpregnationPotionBrewingRecipe.A0());
        	BrewingRecipeRegistry.addRecipe(new VillagerImpregnationPotionBrewingRecipe.A1());
        	BrewingRecipeRegistry.addRecipe(new VillagerImpregnationPotionBrewingRecipe.A2());
        	BrewingRecipeRegistry.addRecipe(new VillagerImpregnationPotionBrewingRecipe.A3());
        	BrewingRecipeRegistry.addRecipe(new VillagerImpregnationPotionBrewingRecipe.A4());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A0());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A1());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A2());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A3());
        	BrewingRecipeRegistry.addRecipe(new ImpregnationPotionBrewingRecipe.A4());
        	BrewingRecipeRegistry.addRecipe(new PregnancyHealingBrewingRecipe());
        	BrewingRecipeRegistry.addRecipe(new PregnancyResistancePotionBrewingRecipe());
        	BrewingRecipeRegistry.addRecipe(new ZeroGravityBellyPotionBrewingRecipe());
        	BrewingRecipeRegistry.addRecipe(new EnderPowerfulImpregnationPotionBrewingRecipe());
        	BrewingRecipeRegistry.addRecipe(new EnderDragonImpregnationPotionBrewingRecipe());
    	
            ComposterBlock.COMPOSTABLES.put(MinepreggoModItems.CHILI_PEPPER.get(), 0.6f);
            ComposterBlock.COMPOSTABLES.put(MinepreggoModItems.CUCUMBER.get(), 0.7f);
            ComposterBlock.COMPOSTABLES.put(MinepreggoModItems.LEMON.get(), 0.6f);
                    
            MinepreggoModPacketHandler.registerMessages();
            
            MinepreggoModAdvancements.register();
        });	
    }  
}
