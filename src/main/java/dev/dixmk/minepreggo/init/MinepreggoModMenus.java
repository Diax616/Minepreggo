package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerJoinsWorldMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerMedicalCheckUpMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobMedicalCheckUpMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexM2PMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexP2PMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.SelectPregnantEntityForMedicalCheckUpMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP8InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP8MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP4InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP4MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP5InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP5MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP6InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP6MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7MainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP8InventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP8MainMenu;
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
	public static final RegistryObject<MenuType<ZombieGirlP0MainMenu>> ZOMBIE_GIRL_P0_MAIN_MENU = REGISTRY.register("zombie_girl_p0_main_menu", () -> IForgeMenuType.create(ZombieGirlP0MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP0InventoryMenu>> ZOMBIE_GIRL_P0_INVENTORY_MENU = REGISTRY.register("zombie_girl_p0_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP0InventoryMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP1MainMenu>> ZOMBIE_GIRL_P1_MAIN_MENU = REGISTRY.register("zombie_girl_p1_main_menu", () -> IForgeMenuType.create(ZombieGirlP1MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP1InventoryMenu>> ZOMBIE_GIRL_P1_INVENTORY_MENU = REGISTRY.register("zombie_girl_p1_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP1InventoryMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP2MainMenu>> ZOMBIE_GIRL_P2_MAIN_MENU = REGISTRY.register("zombie_girl_p2_main_menu", () -> IForgeMenuType.create(ZombieGirlP2MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP2InventoryMenu>> ZOMBIE_GIRL_P2_INVENTORY_MENU = REGISTRY.register("zombie_girl_p2_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP2InventoryMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP3MainMenu>> ZOMBIE_GIRL_P3_MAIN_MENU = REGISTRY.register("zombie_girl_p3_main_menu", () -> IForgeMenuType.create(ZombieGirlP3MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP3InventoryMenu>> ZOMBIE_GIRL_P3_INVENTORY_MENU = REGISTRY.register("zombie_girl_p3_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP3InventoryMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP4MainMenu>> ZOMBIE_GIRL_P4_MAIN_MENU = REGISTRY.register("zombie_girl_p4_main_menu", () -> IForgeMenuType.create(ZombieGirlP4MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP4InventoryMenu>> ZOMBIE_GIRL_P4_INVENTORY_MENU = REGISTRY.register("zombie_girl_p4_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP4InventoryMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP5MainMenu>> ZOMBIE_GIRL_P5_MAIN_MENU = REGISTRY.register("zombie_girl_p5_main_menu", () -> IForgeMenuType.create(ZombieGirlP5MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP5InventoryMenu>> ZOMBIE_GIRL_P5_INVENTORY_MENU = REGISTRY.register("zombie_girl_p5_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP5InventoryMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP6MainMenu>> ZOMBIE_GIRL_P6_MAIN_MENU = REGISTRY.register("zombie_girl_p6_main_menu", () -> IForgeMenuType.create(ZombieGirlP6MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP6InventoryMenu>> ZOMBIE_GIRL_P6_INVENTORY_MENU = REGISTRY.register("zombie_girl_p6_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP6InventoryMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP7MainMenu>> ZOMBIE_GIRL_P7_MAIN_MENU = REGISTRY.register("zombie_girl_p7_main_menu", () -> IForgeMenuType.create(ZombieGirlP7MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP7InventoryMenu>> ZOMBIE_GIRL_P7_INVENTORY_MENU = REGISTRY.register("zombie_girl_p7_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP7InventoryMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP8MainMenu>> ZOMBIE_GIRL_P8_MAIN_MENU = REGISTRY.register("zombie_girl_p8_main_menu", () -> IForgeMenuType.create(ZombieGirlP8MainMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP8InventoryMenu>> ZOMBIE_GIRL_P8_INVENTORY_MENU = REGISTRY.register("zombie_girl_p8_inventary_menu", () -> IForgeMenuType.create(ZombieGirlP8InventoryMenu::new));
	
	public static final RegistryObject<MenuType<CreeperGirlMainMenu>> CREEPER_GIRL_MAIN_MENU = REGISTRY.register("creeper_girl_main_menu", () -> IForgeMenuType.create(CreeperGirlMainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlInventoryMenu>> CREEPER_GIRL_INVENTORY_MENU = REGISTRY.register("creeper_girl_inventary_menu", () -> IForgeMenuType.create(CreeperGirlInventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP0MainMenu>> CREEPER_GIRL_P0_MAIN_MENU = REGISTRY.register("creeper_girl_p0_main_menu", () -> IForgeMenuType.create(CreeperGirlP0MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP0InventoryMenu>> CREEPER_GIRL_P0_INVENTORY_MENU = REGISTRY.register("creeper_girl_p0_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP0InventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP1MainMenu>> CREEPER_GIRL_P1_MAIN_MENUI = REGISTRY.register("creeper_girl_p1_main_menu", () -> IForgeMenuType.create(CreeperGirlP1MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP1InventoryMenu>> CREEPER_GIRL_P1_INVENTORY_MENU = REGISTRY.register("creeper_girl_p1_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP1InventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP2MainMenu>> CREEPER_GIRL_P2_MAIN_MENU = REGISTRY.register("creeper_girl_p2_main_menu", () -> IForgeMenuType.create(CreeperGirlP2MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP2InventoryMenu>> CREEPER_GIRL_P2_INVENTORY_MENU = REGISTRY.register("creeper_girl_p2_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP2InventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP3MainMenu>> CREEPER_GIRL_P3_MAIN_MENU = REGISTRY.register("creeper_girl_p3_main_menu", () -> IForgeMenuType.create(CreeperGirlP3MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP3InventoryMenu>> CREEPER_GIRL_P3_INVENTORY_MENU = REGISTRY.register("creeper_girl_p3_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP3InventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP4MainMenu>> CREEPER_GIRL_P4_MAIN_MENU = REGISTRY.register("creeper_girl_p4_main_menu", () -> IForgeMenuType.create(CreeperGirlP4MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP4InventoryMenu>> CREEPER_GIRL_P4_INVENTORY_MENU = REGISTRY.register("creeper_girl_p4_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP4InventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP5MainMenu>> CREEPER_GIRL_P5_MAIN_MENUI = REGISTRY.register("creeper_girl_p5_main_menu", () -> IForgeMenuType.create(CreeperGirlP5MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP5InventoryMenu>> CREEPER_GIRL_P5_INVENTORY_MENU = REGISTRY.register("creeper_girl_p5_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP5InventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP6MainMenu>> CREEPER_GIRL_P6_MAIN_MENU = REGISTRY.register("creeper_girl_p6_main_menu", () -> IForgeMenuType.create(CreeperGirlP6MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP6InventoryMenu>> CREEPER_GIRL_P6_INVENTORY_MENU = REGISTRY.register("creeper_girl_p6_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP6InventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP7MainMenu>> CREEPER_GIRL_P7_MAIN_MENU = REGISTRY.register("creeper_girl_p7_main_menu", () -> IForgeMenuType.create(CreeperGirlP7MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP7InventoryMenu>> CREEPER_GIRL_P7_INVENTORY_MENUI = REGISTRY.register("creeper_girl_p7_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP7InventoryMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP8MainMenu>> CREEPER_GIRL_P8_MAIN_MENU = REGISTRY.register("creeper_girl_p8_main_menu", () -> IForgeMenuType.create(CreeperGirlP8MainMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP8InventoryMenu>> CREEPER_GIRL_P8_INVENTORY_MENU = REGISTRY.register("creeper_girl_p8_inventary_menu", () -> IForgeMenuType.create(CreeperGirlP8InventoryMenu::new));

	public static final RegistryObject<MenuType<SelectPregnantEntityForMedicalCheckUpMenu>> SELECT_PREGNANT_ENTITY_FOR_MEDICAL_CHECKUP_MENU = REGISTRY.register("select_pregnant_entity_for_medical_checkup_menu", () -> IForgeMenuType.create(SelectPregnantEntityForMedicalCheckUpMenu::new));
	public static final RegistryObject<MenuType<PreggoMobMedicalCheckUpMenu>> PREGGO_MOB_MEDICAL_CHECKUP_MENU = REGISTRY.register("preggo_mob_medical_checkup_menu", () -> IForgeMenuType.create(PreggoMobMedicalCheckUpMenu::new));
	public static final RegistryObject<MenuType<PlayerMedicalCheckUpMenu.DoctorVillager>> PLAYER_MEDICAL_CHECKUP_BY_VILLAGER_MENU = REGISTRY.register("player_mob_medical_checkup_by_villager_menu", () -> IForgeMenuType.create(PlayerMedicalCheckUpMenu.DoctorVillager::new));
	public static final RegistryObject<MenuType<PlayerMedicalCheckUpMenu.Illager>> PLAYER_MEDICAL_CHECKUP_BY_ILLAGER_MENU = REGISTRY.register("player_mob_medical_checkup_by_illager_menu", () -> IForgeMenuType.create(PlayerMedicalCheckUpMenu.Illager::new));

	public static final RegistryObject<MenuType<PlayerJoinsWorldMenu>> PLAYER_JOINS_WORLD_MENU = REGISTRY.register("player_joins_world_menu", () -> IForgeMenuType.create(PlayerJoinsWorldMenu::new));

	
	public static final RegistryObject<MenuType<RequestSexM2PMenu>> REQUEST_SEX_M2P_MENU = REGISTRY.register("request_sex_m2p_menu", () -> IForgeMenuType.create(RequestSexM2PMenu::new));
	public static final RegistryObject<MenuType<RequestSexP2PMenu>> REQUEST_SEX_P2P_MENU = REGISTRY.register("request_sex_p2p_menu", () -> IForgeMenuType.create(RequestSexP2PMenu::new));

}
