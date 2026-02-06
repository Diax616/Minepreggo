package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerJoinsWorldMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerPrenatalCheckUpMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobPrenatalCheckUpMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexP2PMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.SelectPregnantEntityForPrenatalCheckUpMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.HumanoidTamablePregnantCreeperGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.HumanoidTamablePregnantCreeperGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.MonsterCreeperGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.MonsterCreeperGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.MonsterEnderWomanInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.MonsterEnderWomanMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.TamablePregnantZombieGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.TamablePregnantZombieGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlMainMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModMenus {

	private MinepreggoModMenus() {}
	

	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MinepreggoMod.MODID);
	
	public static final RegistryObject<MenuType<ZombieGirlMainMenu>> ZOMBIE_GIRL_MAIN_MENU = REGISTRY.register("zombie_girl_main_menu", () -> IForgeMenuType.create(ZombieGirlMainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlInventoryMenu>> ZOMBIE_GIRL_INVENTORY_MENU = REGISTRY.register("zombie_girl_inventary_menu", () -> IForgeMenuType.create(ZombieGirlInventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP0MainMenu>> ZOMBIE_GIRL_P0_MAIN_MENU = REGISTRY.register("zombie_girl_p0_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP0MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP0InventoryMenu>> ZOMBIE_GIRL_P0_INVENTORY_MENU = REGISTRY.register("zombie_girl_p0_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP0InventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP1MainMenu>> ZOMBIE_GIRL_P1_MAIN_MENU = REGISTRY.register("zombie_girl_p1_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP1MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP1InventoryMenu>> ZOMBIE_GIRL_P1_INVENTORY_MENU = REGISTRY.register("zombie_girl_p1_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP1InventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP2MainMenu>> ZOMBIE_GIRL_P2_MAIN_MENU = REGISTRY.register("zombie_girl_p2_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP2MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP2InventoryMenu>> ZOMBIE_GIRL_P2_INVENTORY_MENU = REGISTRY.register("zombie_girl_p2_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP2InventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP3MainMenu>> ZOMBIE_GIRL_P3_MAIN_MENU = REGISTRY.register("zombie_girl_p3_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP3MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP3InventoryMenu>> ZOMBIE_GIRL_P3_INVENTORY_MENU = REGISTRY.register("zombie_girl_p3_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP3InventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP4MainMenu>> ZOMBIE_GIRL_P4_MAIN_MENU = REGISTRY.register("zombie_girl_p4_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP4MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP4InventoryMenu>> ZOMBIE_GIRL_P4_INVENTORY_MENU = REGISTRY.register("zombie_girl_p4_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP4InventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP5MainMenu>> ZOMBIE_GIRL_P5_MAIN_MENU = REGISTRY.register("zombie_girl_p5_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP5MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP5InventoryMenu>> ZOMBIE_GIRL_P5_INVENTORY_MENU = REGISTRY.register("zombie_girl_p5_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP5InventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP6MainMenu>> ZOMBIE_GIRL_P6_MAIN_MENU = REGISTRY.register("zombie_girl_p6_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP6MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP6InventoryMenu>> ZOMBIE_GIRL_P6_INVENTORY_MENU = REGISTRY.register("zombie_girl_p6_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP6InventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP7MainMenu>> ZOMBIE_GIRL_P7_MAIN_MENU = REGISTRY.register("zombie_girl_p7_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP7MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP7InventoryMenu>> ZOMBIE_GIRL_P7_INVENTORY_MENU = REGISTRY.register("zombie_girl_p7_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP7InventoryMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlMainMenu.ZombieGirlP8MainMenu>> ZOMBIE_GIRL_P8_MAIN_MENU = REGISTRY.register("zombie_girl_p8_main_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlMainMenu.ZombieGirlP8MainMenu::new));
	public static final RegistryObject<MenuType<TamablePregnantZombieGirlInventoryMenu.ZombieGirlP8InventoryMenu>> ZOMBIE_GIRL_P8_INVENTORY_MENU = REGISTRY.register("zombie_girl_p8_inventary_menu", () -> IForgeMenuType.create(TamablePregnantZombieGirlInventoryMenu.ZombieGirlP8InventoryMenu::new));
	
	public static final RegistryObject<MenuType<CreeperGirlMainMenu>> CREEPER_GIRL_MAIN_MENU = REGISTRY.register("creeper_girl_main_menu", () -> IForgeMenuType.create(CreeperGirlMainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlInventoryMenu>> CREEPER_GIRL_INVENTORY_MENU = REGISTRY.register("creeper_girl_inventary_menu", () -> IForgeMenuType.create(CreeperGirlInventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP0MainMenu>> CREEPER_GIRL_P0_MAIN_MENU = REGISTRY.register("humanoid_creeper_girl_p0_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP0MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP0InventoryMenu>> CREEPER_GIRL_P0_INVENTORY_MENU = REGISTRY.register("humanoid_creeper_girl_p0_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP0InventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP1MainMenu>> CREEPER_GIRL_P1_MAIN_MENUI = REGISTRY.register("humanoid_creeper_girl_p1_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP1MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP1InventoryMenu>> CREEPER_GIRL_P1_INVENTORY_MENU = REGISTRY.register("humanoid_creeper_girl_p1_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP1InventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP2MainMenu>> CREEPER_GIRL_P2_MAIN_MENU = REGISTRY.register("humanoid_creeper_girl_p2_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP2MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP2InventoryMenu>> CREEPER_GIRL_P2_INVENTORY_MENU = REGISTRY.register("humanoid_creeper_girl_p2_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP2InventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP3MainMenu>> CREEPER_GIRL_P3_MAIN_MENU = REGISTRY.register("humanoid_creeper_girl_p3_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP3MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP3InventoryMenu>> CREEPER_GIRL_P3_INVENTORY_MENU = REGISTRY.register("humanoid_creeper_girl_p3_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP3InventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP4MainMenu>> CREEPER_GIRL_P4_MAIN_MENU = REGISTRY.register("humanoid_creeper_girl_p4_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP4MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP4InventoryMenu>> CREEPER_GIRL_P4_INVENTORY_MENU = REGISTRY.register("humanoid_creeper_girl_p4_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP4InventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP5MainMenu>> CREEPER_GIRL_P5_MAIN_MENUI = REGISTRY.register("humanoid_creeper_girl_p5_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP5MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP5InventoryMenu>> CREEPER_GIRL_P5_INVENTORY_MENU = REGISTRY.register("humanoid_creeper_girl_p5_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP5InventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP6MainMenu>> CREEPER_GIRL_P6_MAIN_MENU = REGISTRY.register("humanoid_creeper_girl_p6_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP6MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP6InventoryMenu>> CREEPER_GIRL_P6_INVENTORY_MENU = REGISTRY.register("humanoid_creeper_girl_p6_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP6InventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP7MainMenu>> CREEPER_GIRL_P7_MAIN_MENU = REGISTRY.register("humanoid_creeper_girl_p7_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP7MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP7InventoryMenu>> CREEPER_GIRL_P7_INVENTORY_MENUI = REGISTRY.register("humanoid_creeper_girl_p7_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP7InventoryMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP8MainMenu>> CREEPER_GIRL_P8_MAIN_MENU = REGISTRY.register("humanoid_creeper_girl_p8_main_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlMainMenu.CreeperGirlP8MainMenu::new));
	public static final RegistryObject<MenuType<HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP8InventoryMenu>> CREEPER_GIRL_P8_INVENTORY_MENU = REGISTRY.register("humanoid_creeper_girl_p8_inventary_menu", () -> IForgeMenuType.create(HumanoidTamablePregnantCreeperGirlInventoryMenu.CreeperGirlP8InventoryMenu::new));

	public static final RegistryObject<MenuType<MonsterCreeperGirlMainMenu>> MONSTER_CREEPER_GIRL_MAIN_MENU = REGISTRY.register("monster_creeper_girl_main_menu", () -> IForgeMenuType.create(MonsterCreeperGirlMainMenu::new));
	public static final RegistryObject<MenuType<MonsterCreeperGirlInventoryMenu>> MONSTER_CREEPER_GIRL_INVENTORY_MENU = REGISTRY.register("monster_creeper_girl_inventary_menu", () -> IForgeMenuType.create(MonsterCreeperGirlInventoryMenu::new));
	
	
	
	public static final RegistryObject<MenuType<MonsterEnderWomanMainMenu>> MONSTER_ENDER_WOMAN_MAIN_MENU = REGISTRY.register("monster_ender_woman_main_menu", () -> IForgeMenuType.create(MonsterEnderWomanMainMenu::new));
	public static final RegistryObject<MenuType<MonsterEnderWomanInventoryMenu>> MONSTER_ENDER_WOMAN_INVENTORY_MENU = REGISTRY.register("monster_ender_woman_inventary_menu", () -> IForgeMenuType.create(MonsterEnderWomanInventoryMenu::new));

	public static final RegistryObject<MenuType<SelectPregnantEntityForPrenatalCheckUpMenu>> SELECT_PREGNANT_ENTITY_FOR_PRENATAL_CHECKUP_MENU = REGISTRY.register("select_pregnant_entity_for_prenatal_checkup_menu", () -> IForgeMenuType.create(SelectPregnantEntityForPrenatalCheckUpMenu::new));
	public static final RegistryObject<MenuType<PreggoMobPrenatalCheckUpMenu>> PREGGO_MOB_PRENATAL_CHECKUP_MENU = REGISTRY.register("preggo_mob_prenatal_checkup_menu", () -> IForgeMenuType.create(PreggoMobPrenatalCheckUpMenu::new));
	
	public static final RegistryObject<MenuType<PlayerPrenatalCheckUpMenu.VillagerMenu>> PLAYER_PRENATAL_CHECKUP_BY_VILLAGER_MENU = REGISTRY.register("player_prenatal_checkup_by_villager_menu", () -> IForgeMenuType.create(PlayerPrenatalCheckUpMenu.VillagerMenu::new));
	public static final RegistryObject<MenuType<PlayerPrenatalCheckUpMenu.IllagerMenu>> PLAYER_PRENATAL_CHECKUP_BY_ILLAGER_MENU = REGISTRY.register("player_prenatal_checkup_by_illager_menu", () -> IForgeMenuType.create(PlayerPrenatalCheckUpMenu.IllagerMenu::new));

	public static final RegistryObject<MenuType<PlayerJoinsWorldMenu>> PLAYER_JOINS_WORLD_MENU = REGISTRY.register("player_joins_world_menu", () -> IForgeMenuType.create(PlayerJoinsWorldMenu::new));

	public static final RegistryObject<MenuType<RequestSexM2PMenu>> REQUEST_SEX_M2P_MENU = REGISTRY.register("request_sex_m2p_menu", () -> IForgeMenuType.create(RequestSexM2PMenu::new));
	public static final RegistryObject<MenuType<RequestSexP2PMenu>> REQUEST_SEX_P2P_MENU = REGISTRY.register("request_sex_p2p_menu", () -> IForgeMenuType.create(RequestSexP2PMenu::new));
}
