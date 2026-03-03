package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoTabs {
	
	private MinepreggoTabs() {}
	
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinepreggoMod.MODID);

    public static final RegistryObject<CreativeModeTab> MINEPREGGO_TAB = REGISTRY.register("minepreggo_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.literal("Minepreggo"))
            .icon(() -> MinepreggoItems.MATERNITY_DIAMOND_P1_CHESTPLATE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(MinepreggoItems.LEMON.get());
                output.accept(MinepreggoItems.LEMON_ICE_CREAM.get());
                output.accept(MinepreggoItems.CHOCOLATE_BAR.get());
                output.accept(MinepreggoItems.CHILI_PEPPER.get());
                output.accept(MinepreggoItems.CUCUMBER.get());
                output.accept(MinepreggoItems.LEMON_ICE_POPSICLES.get());
                output.accept(MinepreggoItems.SALT.get());
                output.accept(MinepreggoItems.PICKLE.get());
                output.accept(MinepreggoItems.SALTY_WATER_BOTTLE.get());
                output.accept(MinepreggoItems.HOT_CHICKEN.get());
                output.accept(MinepreggoItems.HOT_SAUCE.get());
                output.accept(MinepreggoItems.LEMON_DROP.get());
                output.accept(MinepreggoItems.CHILI_POPPERS.get());
                output.accept(MinepreggoItems.FRENCH_FRIES.get());
                output.accept(MinepreggoItems.CANDY_APPLE.get());
                
                output.accept(MinepreggoItems.HUMAN_BREAST_MILK_BOTTLE.get());
                output.accept(MinepreggoItems.ZOMBIE_BREAST_MILK_BOTTLE.get());
                output.accept(MinepreggoItems.CREEPER_BREAST_MILK_BOTTLE.get());
                output.accept(MinepreggoItems.ENDER_BREAST_MILK_BOTTLE.get());

                output.accept(MinepreggoItems.VILLAGER_BRAIN.get());
                output.accept(MinepreggoItems.ILLAGER_BRAIN.get());
                output.accept(MinepreggoItems.WITCH_BRAIN.get());
                output.accept(MinepreggoItems.BRAIN_WITH_CHOCOLATE.get());
                output.accept(MinepreggoItems.BRAIN_WITH_SALT.get());
                output.accept(MinepreggoItems.BRAIN_WITH_HOT_SAUCE.get());
                output.accept(MinepreggoItems.SOUR_BRAIN.get());
               
                output.accept(MinepreggoItems.ACTIVATED_GUNPOWDER.get());
                output.accept(MinepreggoItems.ACTIVATED_GUNPOWDER_WITH_CHOCOLATE.get());
                output.accept(MinepreggoItems.ACTIVATED_GUNPOWDER_WITH_SALT.get());
                output.accept(MinepreggoItems.ACTIVATED_GUNPOWDER_WITH_HOT_SAUCE.get());
                output.accept(MinepreggoItems.SOUR_ACTIVATED_GUNPOWDER.get());
               
                output.accept(MinepreggoItems.ENDER_SLIME_JELLY.get());
                output.accept(MinepreggoItems.REFINED_CHORUS_SHARDS.get());
                output.accept(MinepreggoItems.ENDER_SLIME_JELLY_WITH_CHOCOLATE.get());
                output.accept(MinepreggoItems.ENDER_SLIME_JELLY_WITH_HOT_SAUCE.get());
                output.accept(MinepreggoItems.ENDER_SLIME_JELLY_WITH_SALT.get());
                output.accept(MinepreggoItems.SOUR_ENDER_SLIME_JELLY.get());
                
                output.accept(MinepreggoItems.CHORUS_FRUIT_WITH_CHOCOLATE.get());
                output.accept(MinepreggoItems.CHORUS_FRUIT_WITH_HOT_SAUCE.get());
                output.accept(MinepreggoItems.CHORUS_FRUIT_WITH_SALT.get());
                output.accept(MinepreggoItems.SOUR_CHORUS_FRUIT.get());

                output.accept(MinepreggoItems.MEDICAL_TABLE.get());
        
                output.accept(MinepreggoItems.FEMALE_CHAINMAIL_CHESTPLATE.get());
                output.accept(MinepreggoItems.FEMALE_IRON_CHESTPLATE.get());
                output.accept(MinepreggoItems.FEMALE_GOLDEN_CHESTPLATE.get());
                output.accept(MinepreggoItems.FEMALE_DIAMOND_CHESTPLATE.get());
                output.accept(MinepreggoItems.FEMALE_NETHERITE_CHESTPLATE.get());
                output.accept(MinepreggoItems.FEMALE_LEATHER_CHESTPLATE.get());
                
                output.accept(MinepreggoItems.MATERNITY_CHAINMAIL_P1_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_IRON_P1_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_GOLDEN_P1_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_DIAMOND_P1_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_NETHERITE_P1_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_LEATHER_P1_CHESTPLATE.get());
                
                output.accept(MinepreggoItems.MATERNITY_CHAINMAIL_P2_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_IRON_P2_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_GOLDEN_P2_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_DIAMOND_P2_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_NETHERITE_P2_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_LEATHER_P2_CHESTPLATE.get());
                
                output.accept(MinepreggoItems.MATERNITY_CHAINMAIL_P3_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_IRON_P3_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_GOLDEN_P3_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_DIAMOND_P3_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_NETHERITE_P3_CHESTPLATE.get());  
                output.accept(MinepreggoItems.MATERNITY_LEATHER_P3_CHESTPLATE.get());
                
                output.accept(MinepreggoItems.MATERNITY_CHAINMAIL_P4_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_IRON_P4_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_GOLDEN_P4_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_DIAMOND_P4_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_NETHERITE_P4_CHESTPLATE.get());
                output.accept(MinepreggoItems.MATERNITY_LEATHER_P4_CHESTPLATE.get());

                output.accept(MinepreggoItems.ROPES.get());
                output.accept(MinepreggoItems.LEATHER_KNEE_BRACES.get());
                output.accept(MinepreggoItems.CHAINMAIL_KNEE_BRACES.get());
                output.accept(MinepreggoItems.IRON_KNEE_BRACES.get());
                output.accept(MinepreggoItems.GOLDEN_KNEE_BRACES.get());
                output.accept(MinepreggoItems.DIAMOND_KNEE_BRACES.get());
                output.accept(MinepreggoItems.NETHERITE_KNEE_BRACES.get());
                
                output.accept(MinepreggoItems.BELLY_SHIELD_P5.get());
                output.accept(MinepreggoItems.BELLY_SHIELD_P6.get());
                output.accept(MinepreggoItems.BELLY_SHIELD_P7.get());
                output.accept(MinepreggoItems.BELLY_SHIELD_P8.get());
                
                output.accept(MinepreggoItems.ZOMBIE_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoItems.CREEPER_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoItems.ENDER_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoItems.VILLAGER_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoItems.CONCENTRATED_ENDER_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoItems.SPECIMEN_TUBE.get());
                output.accept(MinepreggoItems.CUM_SPECIMEN_TUBE.get());
                output.accept(MinepreggoItems.POWERED_CUM_SPECIMEN_TUBE.get());

                output.accept(MinepreggoItems.BABY_HUMAN.get());
                output.accept(MinepreggoItems.BABY_ZOMBIE.get());
                output.accept(MinepreggoItems.BABY_HUMANOID_CREEPER.get());
                output.accept(MinepreggoItems.BABY_CREEPER.get());
                output.accept(MinepreggoItems.BABY_HUMANOID_ENDER.get());
                output.accept(MinepreggoItems.BABY_ENDER.get());
                output.accept(MinepreggoItems.BABY_VILLAGER.get());
                output.accept(MinepreggoItems.BABY_ENDER_DRAGON_BLOCK.get());
                output.accept(MinepreggoItems.DEAD_HUMAN_FETUS.get());
                output.accept(MinepreggoItems.DEAD_ZOMBIE_FETUS.get());
                output.accept(MinepreggoItems.DEAD_HUMANOID_CREEPER_FETUS.get());
                output.accept(MinepreggoItems.DEAD_CREEPER_FETUS.get());
                output.accept(MinepreggoItems.DEAD_HUMANOID_ENDER_FETUS.get());
                output.accept(MinepreggoItems.DEAD_ENDER_FETUS.get());
                output.accept(MinepreggoItems.DEAD_VILLAGER_FETUS.get());
        
                output.accept(MinepreggoItems.MONSTER_ZOMBIE_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoItems.MONSTER_ZOMBIE_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoItems.MONSTER_ZOMBIE_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoItems.MONSTER_ZOMBIE_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P1_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P2_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P4_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P6_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_ZOMBIE_GIRL_P8_SPAWN_EGG.get());

                output.accept(MinepreggoItems.MONSTER_HUMANOID_CREEPER_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoItems.MONSTER_HUMANOID_CREEPER_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoItems.MONSTER_HUMANOID_CREEPER_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoItems.MONSTER_HUMANOID_CREEPER_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P1_SPAWN_EGG.get());        
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P2_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P4_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P6_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_HUMANOID_CREEPER_GIRL_P8_SPAWN_EGG.get());
     
                output.accept(MinepreggoItems.MONSTER_CREEPER_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoItems.HOSTIL_MONSTER_CREEPER_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoItems.HOSTIL_MONSTER_CREEPER_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoItems.HOSTIL_MONSTER_CREEPER_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P1_SPAWN_EGG.get());        
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P2_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P4_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P6_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_CREEPER_GIRL_P8_SPAWN_EGG.get());
                
                output.accept(MinepreggoItems.MONSTER_ENDER_WOMAN_SPAWN_EGG.get());
                output.accept(MinepreggoItems.HOSTIL_MONSTER_ENDER_WOMAN_P3_SPAWN_EGG.get());
                output.accept(MinepreggoItems.HOSTIL_MONSTER_ENDER_WOMAN_P5_SPAWN_EGG.get());
                output.accept(MinepreggoItems.HOSTIL_MONSTER_ENDER_WOMAN_P7_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P0_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P1_SPAWN_EGG.get());        
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P2_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P3_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P4_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P5_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P6_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P7_SPAWN_EGG.get());
                output.accept(MinepreggoItems.TAMABLE_MONSTER_ENDER_WOMAN_P8_SPAWN_EGG.get());
                
                output.accept(MinepreggoItems.SCIENTIFIC_ILLAGER_SPAWN_EGG.get());
                output.accept(MinepreggoItems.FERTILITY_WITCH_SPAWN_EGG.get());
 
            }).build());
}
