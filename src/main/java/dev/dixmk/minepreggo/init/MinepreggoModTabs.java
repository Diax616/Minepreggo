package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModTabs {
	
	private MinepreggoModTabs() {}
	
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinepreggoMod.MODID);

    public static final RegistryObject<CreativeModeTab> MINEPREGGO_TAB = REGISTRY.register("minepreggo_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.literal("Minepreggo"))
            .icon(() -> MinepreggoModItems.LEMON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(MinepreggoModItems.LEMON.get());
                output.accept(MinepreggoModItems.LEMON_ICE_CREAM.get());
                output.accept(MinepreggoModItems.CHOCOLATE_BAR.get());
                output.accept(MinepreggoModItems.CHILI_PEPPER.get());
                output.accept(MinepreggoModItems.CUCUMBER.get());
                output.accept(MinepreggoModItems.LEMON_ICE_POPSICLES.get());
                output.accept(MinepreggoModItems.SALT.get());
                output.accept(MinepreggoModItems.PICKLE.get());
                output.accept(MinepreggoModItems.SALTY_WATER_BOTTLE.get());
                output.accept(MinepreggoModItems.HOT_CHICKEN.get());
                output.accept(MinepreggoModItems.HOT_SAUCE.get());
                
                output.accept(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get());
                output.accept(MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get());
                output.accept(MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get());

                output.accept(MinepreggoModItems.VILLAGER_BRAIN.get());
                output.accept(MinepreggoModItems.PILLAGER_BRAIN.get());
                output.accept(MinepreggoModItems.WITCH_BRAIN.get());
                output.accept(MinepreggoModItems.BRAIN_WITH_CHOCOLATE.get());
                output.accept(MinepreggoModItems.BRAIN_WITH_SALT.get());
                output.accept(MinepreggoModItems.BRAIN_WITH_HOT_SAUCE.get());
                output.accept(MinepreggoModItems.SOUR_BRAIN.get());
               
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER.get());
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_CHOCOLATE.get());
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_SALT.get());
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_HOT_SAUCE.get());
                output.accept(MinepreggoModItems.SOUR_ACTIVATED_GUNPOWDER.get());
               
                output.accept(MinepreggoModItems.MEDICAL_TABLE.get());

                
                output.accept(MinepreggoModItems.FEMALE_CHAINMAIL_P0_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_IRON_P0_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_IRON_P0_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_DIAMOND_P0_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_NETHERITE_P0_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_LEATHER_P0_CHESTPLATE.get());
                
                output.accept(MinepreggoModItems.MATERNITY_CHAINMAIL_P1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_IRON_P1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_GOLDEN_P1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_DIAMOND_P1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_NETHERITE_P1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_LEATHER_P1_CHESTPLATE.get());
                
                output.accept(MinepreggoModItems.MATERNITY_CHAINMAIL_P2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_IRON_P2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_GOLDEN_P2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_DIAMOND_P2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_NETHERITE_P2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_LEATHER_P2_CHESTPLATE.get());
                
                output.accept(MinepreggoModItems.MATERNITY_CHAINMAIL_P3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_IRON_P3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_GOLDEN_P3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_DIAMOND_P3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_NETHERITE_P3_CHESTPLATE.get());  
                output.accept(MinepreggoModItems.MATERNITY_LEATHER_P3_CHESTPLATE.get());
                
                output.accept(MinepreggoModItems.MATERNITY_CHAINMAIL_P4_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_IRON_P4_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_GOLDEN_P4_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_DIAMOND_P4_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_NETHERITE_P4_CHESTPLATE.get());
                output.accept(MinepreggoModItems.MATERNITY_LEATHER_P4_CHESTPLATE.get());
                         
                output.accept(MinepreggoModItems.IRON_KNEE_BRACE.get());
                output.accept(MinepreggoModItems.GOLD_KNEE_BRACE.get());
                output.accept(MinepreggoModItems.DIAMOND_KNEE_BRACE.get());
                output.accept(MinepreggoModItems.NETHERITE_KNEE_BRACE.get());
                output.accept(MinepreggoModItems.LEATHER_KNEE_BRACE.get());
                
                output.accept(MinepreggoModItems.BELLY_SHIELD_P5.get());
                output.accept(MinepreggoModItems.BELLY_SHIELD_P6.get());
                output.accept(MinepreggoModItems.BELLY_SHIELD_P7.get());
                output.accept(MinepreggoModItems.BELLY_SHIELD_P8.get());
                
                output.accept(MinepreggoModItems.ZOMBIE_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoModItems.CREEPER_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoModItems.ENDER_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoModItems.SPECIMEN_TUBE.get());
                output.accept(MinepreggoModItems.CUM_SPECIMEN_TUBE.get());
               
                output.accept(MinepreggoModItems.BABY_HUMAN.get());
                output.accept(MinepreggoModItems.BABY_ZOMBIE.get());
                output.accept(MinepreggoModItems.BABY_HUMANOID_CREEPER.get());
                output.accept(MinepreggoModItems.BABY_CREEPER.get());
                output.accept(MinepreggoModItems.BABY_HUMANOID_ENDER.get());
                output.accept(MinepreggoModItems.BABY_ENDER.get());
                output.accept(MinepreggoModItems.DEAD_HUMAN_FETUS.get());
                output.accept(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get());
                output.accept(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get());
                output.accept(MinepreggoModItems.DEAD_CREEPER_FETUS.get());
                output.accept(MinepreggoModItems.DEAD_HUMANOID_ENDER_FETUS.get());
                output.accept(MinepreggoModItems.DEAD_ENDER_FETUS.get());
                
                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P1_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P2_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P4_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P6_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P8_SPAWN_EGG.get());

                output.accept(MinepreggoModItems.MONSTER_HUMANOID_CREEPER_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_HUMANOID_CREEPER_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_HUMANOID_CREEPER_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_HUMANOID_CREEPER_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P1_SPAWN_EGG.get());        
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P2_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P4_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P6_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_HUMANOID_CREEPER_GIRL_P8_SPAWN_EGG.get());
     
                output.accept(MinepreggoModItems.MONSTER_QUADRUPED_CREEPER_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_ENDER_WOMAN_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.SCIENTIFIC_ILLAGER_SPAWN_EGG.get());
                
                
                
            }).build());
}
