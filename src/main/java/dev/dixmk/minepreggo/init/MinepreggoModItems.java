package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.item.ActivatedGunpowderItem;
import dev.dixmk.minepreggo.world.item.ActivatedGunpowderWithChocolateItem;
import dev.dixmk.minepreggo.world.item.ActivatedGunpowderWithHotSauceItem;
import dev.dixmk.minepreggo.world.item.ActivatedGunpowderWithSaltItem;
import dev.dixmk.minepreggo.world.item.BabyEnderItem;
import dev.dixmk.minepreggo.world.item.BabyHumanItem;
import dev.dixmk.minepreggo.world.item.BabyHumanoidCreeperItem;
import dev.dixmk.minepreggo.world.item.BabyHumanoidEnderItem;
import dev.dixmk.minepreggo.world.item.BabyVillagerItem;
import dev.dixmk.minepreggo.world.item.BabyCreeperItem;
import dev.dixmk.minepreggo.world.item.BabyZombieItem;
import dev.dixmk.minepreggo.world.item.BellyShieldChestPlateItem;
import dev.dixmk.minepreggo.world.item.BrainWithChocolateItem;
import dev.dixmk.minepreggo.world.item.BrainWithHotSauceItem;
import dev.dixmk.minepreggo.world.item.BrainWithSaltItem;
import dev.dixmk.minepreggo.world.item.CandyAppleItem;
import dev.dixmk.minepreggo.world.item.ChiliPoppersItem;
import dev.dixmk.minepreggo.world.item.ChocolateBarItem;
import dev.dixmk.minepreggo.world.item.CreeperBreastMilkBottleItem;
import dev.dixmk.minepreggo.world.item.CumSpecimenTubeItem;
import dev.dixmk.minepreggo.world.item.DeadEnderFetusItem;
import dev.dixmk.minepreggo.world.item.DeadEnderHumanoidFetusItem;
import dev.dixmk.minepreggo.world.item.DeadHumanFetusItem;
import dev.dixmk.minepreggo.world.item.DeadHumanoidCreeperFetusItem;
import dev.dixmk.minepreggo.world.item.DeadVillagerFetusItem;
import dev.dixmk.minepreggo.world.item.DeadCreeperFetusItem;
import dev.dixmk.minepreggo.world.item.DeadZombieFetusItem;
import dev.dixmk.minepreggo.world.item.EnderBreastMilkBottleItem;
import dev.dixmk.minepreggo.world.item.EnderLifeSubstanceItem;
import dev.dixmk.minepreggo.world.item.EnderSlimeJellyItem;
import dev.dixmk.minepreggo.world.item.EnderSlimeJellyWithChocolateItem;
import dev.dixmk.minepreggo.world.item.EnderSlimeJellyWithHotSauceItem;
import dev.dixmk.minepreggo.world.item.EnderSlimeJellyWithSaltItem;
import dev.dixmk.minepreggo.world.item.FemaleChainmailChestPlateItem;
import dev.dixmk.minepreggo.world.item.FemaleDiamondChestPlateItem;
import dev.dixmk.minepreggo.world.item.FemaleGoldenChestPlateItem;
import dev.dixmk.minepreggo.world.item.FemaleIronChestPlateItem;
import dev.dixmk.minepreggo.world.item.FemaleLeatherChestPlateItem;
import dev.dixmk.minepreggo.world.item.FemaleNetheriteChestPlateItem;
import dev.dixmk.minepreggo.world.item.FrenchFriesItem;
import dev.dixmk.minepreggo.world.item.HotChickenItem;
import dev.dixmk.minepreggo.world.item.HotSauceItem;
import dev.dixmk.minepreggo.world.item.HumanBreastMilkBottleItem;
import dev.dixmk.minepreggo.world.item.CreeperLifeSubstanceItem;
import dev.dixmk.minepreggo.world.item.KneeBraceItem;
import dev.dixmk.minepreggo.world.item.LemonDropItem;
import dev.dixmk.minepreggo.world.item.LemonIceCreamItem;
import dev.dixmk.minepreggo.world.item.LemonIcePopsiclesItem;
import dev.dixmk.minepreggo.world.item.LemonItem;
import dev.dixmk.minepreggo.world.item.PickleItem;
import dev.dixmk.minepreggo.world.item.PillagerBrainItem;
import dev.dixmk.minepreggo.world.item.RefinedChorusShardsItem;
import dev.dixmk.minepreggo.world.item.RopesItem;
import dev.dixmk.minepreggo.world.item.SaltItem;
import dev.dixmk.minepreggo.world.item.SaltyWaterBottleItem;
import dev.dixmk.minepreggo.world.item.SourActivatedGunpowderItem;
import dev.dixmk.minepreggo.world.item.SourBrainItem;
import dev.dixmk.minepreggo.world.item.SourEnderSlimeJellyItem;
import dev.dixmk.minepreggo.world.item.SpecimenTubeItem;
import dev.dixmk.minepreggo.world.item.VillagerBrainItem;
import dev.dixmk.minepreggo.world.item.VillagerLifeSubstanceItem;
import dev.dixmk.minepreggo.world.item.WitchBrainItem;
import dev.dixmk.minepreggo.world.item.ZombieBreastMilkBottleItem;
import dev.dixmk.minepreggo.world.item.ZombieLifeSubstanceItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModItems {
	
	private MinepreggoModItems() {}
	
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MinepreggoMod.MODID);

	public static final RegistryObject<Item> SCIENTIFIC_ILLAGER_SPAWN_EGG = REGISTRY.register("scientific_illager_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.SCIENTIFIC_ILLAGER, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> FERTILITY_WITCH_SPAWN_EGG = REGISTRY.register("fertility_witch_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.FERTILITY_WITCH, -1, -1, new Item.Properties()));
	
	public static final RegistryObject<Item> MONSTER_ZOMBIE_GIRL_SPAWN_EGG = REGISTRY.register("hostile_zombie_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> MONSTER_ZOMBIE_GIRL_P3_SPAWN_EGG = REGISTRY.register("hostile_pregnant_zombie_girl_p3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P3, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> MONSTER_ZOMBIE_GIRL_P5_SPAWN_EGG = REGISTRY.register("hostile_pregnant_zombie_girl_p5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P5, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> MONSTER_ZOMBIE_GIRL_P7_SPAWN_EGG = REGISTRY.register("hostile_pregnant_zombie_girl_p7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P7, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_SPAWN_EGG = REGISTRY.register("tamable_zombie_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P0_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P1_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p1_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P2_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p2_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P3_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P4_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p4_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P5_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P6_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p6_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P7_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_P8_SPAWN_EGG = REGISTRY.register("tamable_pregnant_zombie_girl_p8_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8, -1, -1, new Item.Properties()));

	public static final RegistryObject<Item> MONSTER_HUMANOID_CREEPER_GIRL_SPAWN_EGG = REGISTRY.register("hostile_humanoid_creeper_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> MONSTER_HUMANOID_CREEPER_GIRL_P3_SPAWN_EGG = REGISTRY.register("hostile_pregnant_humanoid_creeper_girl_p3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P3, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> MONSTER_HUMANOID_CREEPER_GIRL_P5_SPAWN_EGG = REGISTRY.register("hostile_pregnant_humanoid_creeper_girl_p5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P5, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> MONSTER_HUMANOID_CREEPER_GIRL_P7_SPAWN_EGG = REGISTRY.register("hostile_pregnant_humanoid_creeper_girl_p7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P7, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_SPAWN_EGG = REGISTRY.register("tamable_humanoid_creeper_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P0_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P1_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p1_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P2_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p2_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P3_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P4_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p4_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P5_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P6_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p6_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P7_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_HUMANOID_CREEPER_GIRL_P8_SPAWN_EGG = REGISTRY.register("tamable_pregnant_humanoid_creeper_girl_p8_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8, -1, -1, new Item.Properties()));
	
	public static final RegistryObject<Item> MONSTER_ENDER_WOMAN_SPAWN_EGG = REGISTRY.register("hostile_monster_ender_woman_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_MONSTER_ENDER_WOMAN, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> HOSTIL_MONSTER_ENDER_WOMAN_P3_SPAWN_EGG = REGISTRY.register("hostile_pregnant_monster_ender_woman_p3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P3, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> HOSTIL_MONSTER_ENDER_WOMAN_P5_SPAWN_EGG = REGISTRY.register("hostile_pregnant_monster_ender_woman_p5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P5, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> HOSTIL_MONSTER_ENDER_WOMAN_P7_SPAWN_EGG = REGISTRY.register("hostile_pregnant_monster_ender_woman_p7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P7, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_SPAWN_EGG = REGISTRY.register("tamable_monster_ender_woman_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P0_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P1_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p1_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P1, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P2_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p2_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P2, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P3_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P3, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P4_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p4_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P4, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P5_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P5, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P6_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p6_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P6, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P7_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P7, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_ENDER_WOMAN_P8_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_ender_woman_p8_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P8, -1, -1, new Item.Properties()));
	
	public static final RegistryObject<Item> MONSTER_CREEPER_GIRL_SPAWN_EGG = REGISTRY.register("hostile_monster_creeper_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_MONSTER_CREEPER_GIRL, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> HOSTIL_MONSTER_CREEPER_GIRL_P3_SPAWN_EGG = REGISTRY.register("hostile_pregnant_monster_creeper_girl_p3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P3, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> HOSTIL_MONSTER_CREEPER_GIRL_P5_SPAWN_EGG = REGISTRY.register("hostile_pregnant_monster_creeper_girl_p5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P5, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> HOSTIL_MONSTER_CREEPER_GIRL_P7_SPAWN_EGG = REGISTRY.register("hostile_pregnant_monster_creeper_girl_p7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P7, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_SPAWN_EGG = REGISTRY.register("tamable_monster_creeper_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P0_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P0, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P1_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p1_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P1, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P2_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p2_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P2, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P3_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P3, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P4_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p4_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P4, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P5_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P5, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P6_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p6_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P6, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P7_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P7, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_MONSTER_CREEPER_GIRL_P8_SPAWN_EGG = REGISTRY.register("tamable_pregnant_monster_creeper_girl_p8_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P8, -1, -1, new Item.Properties()));
		
	public static final RegistryObject<Item> LEMON = REGISTRY.register("lemon", LemonItem::new);
	public static final RegistryObject<Item> LEMON_ICE_CREAM = REGISTRY.register("lemon_ice_cream", LemonIceCreamItem::new);
	public static final RegistryObject<Item> CHOCOLATE_BAR = REGISTRY.register("chocolate_bar", ChocolateBarItem::new);
	public static final RegistryObject<Item> CHILI_PEPPER = REGISTRY.register("chili_pepper", () -> new ItemNameBlockItem(MinepreggoModBlocks.CHILIPEPPERS.get(), (new Item.Properties().stacksTo(64).rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> CUCUMBER = REGISTRY.register("cucumber", () -> new ItemNameBlockItem(MinepreggoModBlocks.CUCUMBERS.get(), (new Item.Properties().stacksTo(64).rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> LEMON_ICE_POPSICLES = REGISTRY.register("lemon_ice_popsicles", LemonIcePopsiclesItem::new);
	public static final RegistryObject<Item> SALT = REGISTRY.register("salt", SaltItem::new);
	public static final RegistryObject<Item> HOT_CHICKEN = REGISTRY.register("hot_chicken", HotChickenItem::new);
	public static final RegistryObject<Item> HOT_SAUCE = REGISTRY.register("hot_sauce", HotSauceItem::new);
	public static final RegistryObject<Item> PICKLE = REGISTRY.register("pickle", PickleItem::new);
	public static final RegistryObject<Item> SALTY_WATER_BOTTLE = REGISTRY.register("salty_water_bottle", SaltyWaterBottleItem::new);
	public static final RegistryObject<Item> LEMON_DROP = REGISTRY.register("lemon_drop", LemonDropItem::new);
	public static final RegistryObject<Item> CHILI_POPPERS = REGISTRY.register("chili_poppers", ChiliPoppersItem::new);
	public static final RegistryObject<Item> FRENCH_FRIES = REGISTRY.register("french_fries", FrenchFriesItem::new);
	public static final RegistryObject<Item> CANDY_APPLE = REGISTRY.register("candy_apple", CandyAppleItem::new);

	public static final RegistryObject<Item> HUMAN_BREAST_MILK_BOTTLE = REGISTRY.register("human_breast_milk_bottle", HumanBreastMilkBottleItem::new);
	public static final RegistryObject<Item> ZOMBIE_BREAST_MILK_BOTTLE = REGISTRY.register("zombie_breast_milk_bottle", ZombieBreastMilkBottleItem::new);
	public static final RegistryObject<Item> CREEPER_BREAST_MILK_BOTTLE = REGISTRY.register("creeper_breast_milk_bottle", CreeperBreastMilkBottleItem::new);
	public static final RegistryObject<Item> ENDER_BREAST_MILK_BOTTLE = REGISTRY.register("ender_breast_milk_bottle", EnderBreastMilkBottleItem::new);

	public static final RegistryObject<Item> VILLAGER_BRAIN = REGISTRY.register("villager_brain", VillagerBrainItem::new);
	public static final RegistryObject<Item> PILLAGER_BRAIN = REGISTRY.register("pillager_brain", PillagerBrainItem::new);
	public static final RegistryObject<Item> WITCH_BRAIN = REGISTRY.register("witch_brain", WitchBrainItem::new);
	
	public static final RegistryObject<Item> BRAIN_WITH_CHOCOLATE = REGISTRY.register("brain_with_chocolate", BrainWithChocolateItem::new);
	public static final RegistryObject<Item> BRAIN_WITH_SALT = REGISTRY.register("brain_with_salt", BrainWithSaltItem::new);
	public static final RegistryObject<Item> BRAIN_WITH_HOT_SAUCE = REGISTRY.register("brain_with_hot_sauce", BrainWithHotSauceItem::new);
	public static final RegistryObject<Item> SOUR_BRAIN = REGISTRY.register("sour_brain", SourBrainItem::new);
	
	public static final RegistryObject<Item> ACTIVATED_GUNPOWDER = REGISTRY.register("activated_gunpowder", ActivatedGunpowderItem::new);
	public static final RegistryObject<Item> ACTIVATED_GUNPOWDER_WITH_CHOCOLATE = REGISTRY.register("activated_gunpowder_with_chocolate", ActivatedGunpowderWithChocolateItem::new);
	public static final RegistryObject<Item> ACTIVATED_GUNPOWDER_WITH_SALT = REGISTRY.register("activated_gunpowder_with_salt", ActivatedGunpowderWithSaltItem::new);
	public static final RegistryObject<Item> ACTIVATED_GUNPOWDER_WITH_HOT_SAUCE = REGISTRY.register("activated_gunpowder_with_hot_sauce", ActivatedGunpowderWithHotSauceItem::new);
	public static final RegistryObject<Item> SOUR_ACTIVATED_GUNPOWDER = REGISTRY.register("sour_activated_gunpowder", SourActivatedGunpowderItem::new);
	
	public static final RegistryObject<Item> ENDER_SLIME_JELLY = REGISTRY.register("ender_slime_jelly", EnderSlimeJellyItem::new);
	public static final RegistryObject<Item> ENDER_SLIME_JELLY_WITH_CHOCOLATE = REGISTRY.register("ender_slime_jelly_with_chocolate", EnderSlimeJellyWithChocolateItem::new);
	public static final RegistryObject<Item> ENDER_SLIME_JELLY_WITH_HOT_SAUCE = REGISTRY.register("ender_slime_jelly_with_hot_sauce", EnderSlimeJellyWithHotSauceItem::new);
	public static final RegistryObject<Item> ENDER_SLIME_JELLY_WITH_SALT = REGISTRY.register("ender_slime_jelly_with_salt", EnderSlimeJellyWithSaltItem::new);
	public static final RegistryObject<Item> SOUR_ENDER_SLIME_JELLY = REGISTRY.register("sour_ender_slime_jelly", SourEnderSlimeJellyItem::new);
	public static final RegistryObject<Item> REFINED_CHORUS_SHARDS = REGISTRY.register("refined_chorus_shards", RefinedChorusShardsItem::new);

	public static final RegistryObject<Item> FEMALE_CHAINMAIL_P0_CHESTPLATE = REGISTRY.register("female_chainmail_p0_chestplate", FemaleChainmailChestPlateItem.ChestplateP0::new);
	public static final RegistryObject<Item> FEMALE_IRON_P0_CHESTPLATE = REGISTRY.register("female_iron_p0_chestplate", FemaleIronChestPlateItem.ChestplateP0::new);
	public static final RegistryObject<Item> FEMALE_GOLDEN_P0_CHESTPLATE = REGISTRY.register("female_golden_p0_chestplate", FemaleGoldenChestPlateItem.ChestplateP0::new);
	public static final RegistryObject<Item> FEMALE_DIAMOND_P0_CHESTPLATE = REGISTRY.register("female_diamond_p0_chestplate", FemaleDiamondChestPlateItem.ChestplateP0::new);
	public static final RegistryObject<Item> FEMALE_NETHERITE_P0_CHESTPLATE = REGISTRY.register("female_netherite_p0_chestplate", FemaleNetheriteChestPlateItem.ChestplateP0::new);
	public static final RegistryObject<Item> FEMALE_LEATHER_P0_CHESTPLATE = REGISTRY.register("female_leather_p0_chestplate", FemaleLeatherChestPlateItem.ChestplateP0::new);
	
	public static final RegistryObject<Item> MATERNITY_CHAINMAIL_P1_CHESTPLATE = REGISTRY.register("maternity_chainmail_p1_chestplate", FemaleChainmailChestPlateItem.MaternityChestplateP1::new);
	public static final RegistryObject<Item> MATERNITY_IRON_P1_CHESTPLATE = REGISTRY.register("maternity_iron_p1_chestplate", FemaleIronChestPlateItem.MaternityChestplateP1::new);
	public static final RegistryObject<Item> MATERNITY_GOLDEN_P1_CHESTPLATE = REGISTRY.register("maternity_golden_p1_chestplate", FemaleGoldenChestPlateItem.MaternityChestplateP1::new);
	public static final RegistryObject<Item> MATERNITY_DIAMOND_P1_CHESTPLATE = REGISTRY.register("maternity_diamond_p1_chestplate", FemaleDiamondChestPlateItem.MaternityChestplateP1::new);
	public static final RegistryObject<Item> MATERNITY_NETHERITE_P1_CHESTPLATE = REGISTRY.register("maternity_netherite_p1_chestplate", FemaleNetheriteChestPlateItem.MaternityChestplateP1::new);
	public static final RegistryObject<Item> MATERNITY_LEATHER_P1_CHESTPLATE = REGISTRY.register("maternity_leather_p1_chestplate", FemaleLeatherChestPlateItem.MaternityChestplateP1::new);

	public static final RegistryObject<Item> MATERNITY_CHAINMAIL_P2_CHESTPLATE = REGISTRY.register("maternity_chainmail_p2_chestplate", FemaleChainmailChestPlateItem.MaternityChestplateP2::new);
	public static final RegistryObject<Item> MATERNITY_IRON_P2_CHESTPLATE = REGISTRY.register("maternity_iron_p2_chestplate", FemaleIronChestPlateItem.MaternityChestplateP2::new);
	public static final RegistryObject<Item> MATERNITY_GOLDEN_P2_CHESTPLATE = REGISTRY.register("maternity_golden_p2_chestplate", FemaleGoldenChestPlateItem.MaternityChestplateP2::new);
	public static final RegistryObject<Item> MATERNITY_DIAMOND_P2_CHESTPLATE = REGISTRY.register("maternity_diamond_p2_chestplate", FemaleDiamondChestPlateItem.MaternityChestplateP2::new);
	public static final RegistryObject<Item> MATERNITY_NETHERITE_P2_CHESTPLATE = REGISTRY.register("maternity_netherite_p2_chestplate", FemaleNetheriteChestPlateItem.MaternityChestplateP2::new);
	public static final RegistryObject<Item> MATERNITY_LEATHER_P2_CHESTPLATE = REGISTRY.register("maternity_leather_p2_chestplate", FemaleLeatherChestPlateItem.MaternityChestplateP2::new);

	public static final RegistryObject<Item> MATERNITY_CHAINMAIL_P3_CHESTPLATE = REGISTRY.register("maternity_chainmail_p3_chestplate", FemaleChainmailChestPlateItem.MaternityChestplateP3::new);
	public static final RegistryObject<Item> MATERNITY_IRON_P3_CHESTPLATE = REGISTRY.register("maternity_iron_p3_chestplate", FemaleIronChestPlateItem.MaternityChestplateP3::new);
	public static final RegistryObject<Item> MATERNITY_GOLDEN_P3_CHESTPLATE = REGISTRY.register("maternity_golden_p3_chestplate", FemaleGoldenChestPlateItem.MaternityChestplateP3::new);
	public static final RegistryObject<Item> MATERNITY_DIAMOND_P3_CHESTPLATE = REGISTRY.register("maternity_diamond_p3_chestplate", FemaleDiamondChestPlateItem.MaternityChestplateP3::new);
	public static final RegistryObject<Item> MATERNITY_NETHERITE_P3_CHESTPLATE = REGISTRY.register("maternity_netherite_p3_chestplate", FemaleNetheriteChestPlateItem.MaternityChestplateP3::new);
	public static final RegistryObject<Item> MATERNITY_LEATHER_P3_CHESTPLATE = REGISTRY.register("maternity_leather_p3_chestplate", FemaleLeatherChestPlateItem.MaternityChestplateP3::new);
	
	public static final RegistryObject<Item> MATERNITY_CHAINMAIL_P4_CHESTPLATE = REGISTRY.register("maternity_chainmail_p4_chestplate", FemaleChainmailChestPlateItem.MaternityChestplateP4::new);
	public static final RegistryObject<Item> MATERNITY_IRON_P4_CHESTPLATE = REGISTRY.register("maternity_iron_p4_chestplate", FemaleIronChestPlateItem.MaternityChestplateP4::new);
	public static final RegistryObject<Item> MATERNITY_GOLDEN_P4_CHESTPLATE = REGISTRY.register("maternity_golden_p4_chestplate", FemaleGoldenChestPlateItem.MaternityChestplateP4::new);
	public static final RegistryObject<Item> MATERNITY_DIAMOND_P4_CHESTPLATE = REGISTRY.register("maternity_diamond_p4_chestplate", FemaleDiamondChestPlateItem.MaternityChestplateP4::new);
	public static final RegistryObject<Item> MATERNITY_NETHERITE_P4_CHESTPLATE = REGISTRY.register("maternity_netherite_p4_chestplate", FemaleNetheriteChestPlateItem.MaternityChestplateP4::new);
	public static final RegistryObject<Item> MATERNITY_LEATHER_P4_CHESTPLATE = REGISTRY.register("maternity_leather_p4_chestplate", FemaleLeatherChestPlateItem.MaternityChestplateP4::new);

	public static final RegistryObject<Item> BELLY_SHIELD_P5 = REGISTRY.register("belly_shield_p5", BellyShieldChestPlateItem.MaternityChestplateP5::new);
	public static final RegistryObject<Item> BELLY_SHIELD_P6 = REGISTRY.register("belly_shield_p6", BellyShieldChestPlateItem.MaternityChestplateP6::new);
	public static final RegistryObject<Item> BELLY_SHIELD_P7 = REGISTRY.register("belly_shield_p7", BellyShieldChestPlateItem.MaternityChestplateP7::new);
	public static final RegistryObject<Item> BELLY_SHIELD_P8 = REGISTRY.register("belly_shield_p8", BellyShieldChestPlateItem.MaternityChestplateP8::new);
	
	public static final RegistryObject<Item> ROPES = REGISTRY.register("ropes", RopesItem::new);
	public static final RegistryObject<Item> LEATHER_KNEE_BRACE = REGISTRY.register("leather_knee_brace", KneeBraceItem.LeatherKneeBrace::new);
	public static final RegistryObject<Item> IRON_KNEE_BRACE = REGISTRY.register("iron_knee_brace", KneeBraceItem.IronKneeBrace::new);
	public static final RegistryObject<Item> GOLD_KNEE_BRACE = REGISTRY.register("golden_knee_brace", KneeBraceItem.GoldenKneeBrace::new);
	public static final RegistryObject<Item> DIAMOND_KNEE_BRACE = REGISTRY.register("diamond_knee_brace", KneeBraceItem.DiamondKneeBrace::new);
	public static final RegistryObject<Item> NETHERITE_KNEE_BRACE = REGISTRY.register("netherite_knee_brace", KneeBraceItem.NetheriteKneeBrace::new);
	
	public static final RegistryObject<Item> BABY_HUMAN = REGISTRY.register("baby_human", BabyHumanItem::new);
	public static final RegistryObject<Item> BABY_ZOMBIE = REGISTRY.register("baby_zombie", BabyZombieItem::new);
	public static final RegistryObject<Item> BABY_HUMANOID_CREEPER = REGISTRY.register("baby_humanoid_creeper", BabyHumanoidCreeperItem::new);
	public static final RegistryObject<Item> BABY_CREEPER = REGISTRY.register("baby_creeper", BabyCreeperItem::new);	
	public static final RegistryObject<Item> BABY_HUMANOID_ENDER = REGISTRY.register("baby_humanoid_ender", BabyHumanoidEnderItem::new);
	public static final RegistryObject<Item> BABY_ENDER = REGISTRY.register("baby_ender", BabyEnderItem::new);
	public static final RegistryObject<Item> BABY_VILLAGER = REGISTRY.register("baby_villager", BabyVillagerItem::new);

	public static final RegistryObject<Item> DEAD_HUMAN_FETUS = REGISTRY.register("dead_human_fetus", DeadHumanFetusItem::new);
	public static final RegistryObject<Item> DEAD_ZOMBIE_FETUS = REGISTRY.register("dead_zombie_fetus", DeadZombieFetusItem::new);
	public static final RegistryObject<Item> DEAD_HUMANOID_CREEPER_FETUS = REGISTRY.register("dead_humanoid_creeper_fetus", DeadHumanoidCreeperFetusItem::new);
	public static final RegistryObject<Item> DEAD_CREEPER_FETUS = REGISTRY.register("dead_creeper_fetus", DeadCreeperFetusItem::new);	
	public static final RegistryObject<Item> DEAD_HUMANOID_ENDER_FETUS = REGISTRY.register("dead_humanoid_ender_fetus", DeadEnderHumanoidFetusItem::new);
	public static final RegistryObject<Item> DEAD_ENDER_FETUS = REGISTRY.register("dead_ender_fetus", DeadEnderFetusItem::new);
	public static final RegistryObject<Item> DEAD_VILLAGER_FETUS = REGISTRY.register("dead_villager_fetus", DeadVillagerFetusItem::new);
	
	public static final RegistryObject<Item> ZOMBIE_LIFE_SUBSTANCE = REGISTRY.register("zombie_life_substance", ZombieLifeSubstanceItem::new);
	public static final RegistryObject<Item> CREEPER_LIFE_SUBSTANCE = REGISTRY.register("creeper_life_substance", CreeperLifeSubstanceItem::new);
	public static final RegistryObject<Item> ENDER_LIFE_SUBSTANCE = REGISTRY.register("ender_life_substance", EnderLifeSubstanceItem::new);
	public static final RegistryObject<Item> VILLAGER_LIFE_SUBSTANCE = REGISTRY.register("villager_life_substance", VillagerLifeSubstanceItem::new);
		
	public static final RegistryObject<Item> SPECIMEN_TUBE = REGISTRY.register("specimen_tube", SpecimenTubeItem::new);
	public static final RegistryObject<Item> CUM_SPECIMEN_TUBE = REGISTRY.register("cum_specimen_tube", CumSpecimenTubeItem::new);

	public static final RegistryObject<Item> MEDICAL_TABLE = block(MinepreggoModBlocks.MEDICAL_TABLE);

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
