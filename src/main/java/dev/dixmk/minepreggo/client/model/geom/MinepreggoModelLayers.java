package dev.dixmk.minepreggo.client.model.geom;

import java.util.Set;

import com.google.common.collect.Sets;

import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MinepreggoModelLayers {
	private MinepreggoModelLayers() {}
	
	private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();
	
	public static final ModelLayerLocation FEMALE_CHESTPLATE = register("female_chestplate");
	public static final ModelLayerLocation FEMALE_MATERNITY_CHESTPLATE_P1 = register("maternity_chestplate_p1");
	public static final ModelLayerLocation FEMALE_MATERNITY_CHESTPLATE_P2 = register("maternity_chestplate_p2");
	public static final ModelLayerLocation FEMALE_MATERNITY_CHESTPLATE_P3 = register("maternity_chestplate_p3");
	public static final ModelLayerLocation FEMALE_MATERNITY_CHESTPLATE_P4 = register("maternity_chestplate_p4");
	public static final ModelLayerLocation BELLY_SHIELD_P5 = register("belly_shield_p5");
	public static final ModelLayerLocation BELLY_SHIELD_P6 = register("belly_shield_p6");
	public static final ModelLayerLocation BELLY_SHIELD_P7 = register("belly_shield_p7");
	public static final ModelLayerLocation BELLY_SHIELD_P8 = register("belly_shield_p8");
	public static final ModelLayerLocation BELLY_SHIELD_P9 = register("belly_shield_p9");
	public static final ModelLayerLocation KNEE_BRACES = register("knee_braces");
	public static final ModelLayerLocation CUSTOM_BOOBS = register("custom_boobs"); 
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P0 = register("custom_pregnant_body_p0"); 
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P1 = register("custom_pregnant_body_p1");
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P2 = register("custom_pregnant_body_p2");
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P3 = register("custom_pregnant_body_p3");
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P4 = register("custom_pregnant_body_p4");
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P5 = register("custom_pregnant_body_p5");
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P6 = register("custom_pregnant_body_p6");
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P7 = register("custom_pregnant_body_p7");
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P8 = register("custom_pregnant_body_p8");
	public static final ModelLayerLocation CUSTOM_PREGNANT_BODY_P9 = register("custom_pregnant_body_p9");
	public static final ModelLayerLocation PREDEFINED_BOOBS = register("predefined_boobs");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P0 = register("predefined_pregnant_body_p0");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P1 = register("predefined_pregnant_body_p1");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P2 = register("predefined_pregnant_body_p2");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P3 = register("predefined_pregnant_body_p3");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P4 = register("predefined_pregnant_body_p4");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P5 = register("predefined_pregnant_body_p5");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P6 = register("predefined_pregnant_body_p6");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P7 = register("predefined_pregnant_body_p7");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P8 = register("predefined_pregnant_body_p8");
	public static final ModelLayerLocation PREDEFINED_PREGNANT_BODY_P9 = register("predefined_pregnant_body_p9");
	public static final ModelLayerLocation MONSTER_ENDER_WOMAN = register("monster_ender_woman");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P0 = register("pregnant_monster_ender_woman_p0");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P1 = register("pregnant_monster_ender_woman_p1");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P2 = register("pregnant_monster_ender_woman_p2");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P3 = register("pregnant_monster_ender_woman_p3");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P4 = register("pregnant_monster_ender_woman_p4");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P5 = register("pregnant_monster_ender_woman_p5");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P6 = register("pregnant_monster_ender_woman_p6");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P7 = register("pregnant_monster_ender_woman_p7");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P8 = register("pregnant_monster_ender_woman_p8");
	public static final ModelLayerLocation PREGNANT_MONSTER_ENDER_WOMAN_P9 = register("pregnant_monster_ender_woman_p9");
	public static final ModelLayerLocation MONSTER_ENDER_WOMAN_INNER_ARMOR = registerInnerArmor("monster_ender_woman_inner_armor");
	public static final ModelLayerLocation MONSTER_ENDER_WOMAN_OUTER_ARMOR = registerOuterArmor("monster_ender_woman_outer_armor");
	public static final ModelLayerLocation MONSTER_CREEPER_GIRL = register("monster_creeper_girl");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P0 = register("pregnant_monster_creeper_girl_p0");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P1 = register("pregnant_monster_creeper_girl_p1");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P2 = register("pregnant_monster_creeper_girl_p2");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P3 = register("pregnant_monster_creeper_girl_p3");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P4 = register("pregnant_monster_creeper_girl_p4");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P5 = register("pregnant_monster_creeper_girl_p5");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P6 = register("pregnant_monster_creeper_girl_p6");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P7 = register("pregnant_monster_creeper_girl_p7");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P8 = register("pregnant_monster_creeper_girl_p8");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_P9 = register("pregnant_monster_creeper_girl_p9");
	public static final ModelLayerLocation MONSTER_CREEPER_GIRL_ENERGY_ARMOR = registerCustomArmor("monster_creeper_girl_energy_armor");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P0 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p0");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P1 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p1");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P2 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p2");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P3 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p3");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P4 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p4");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P5 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p5");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P6 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p6");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P7 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p7");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P8 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p8");
	public static final ModelLayerLocation PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P9 = registerCustomArmor("pregnant_monster_creeper_girl_energy_armor_p9");
	public static final ModelLayerLocation MONSTER_CREEPER_GIRL_OUTER_ARMOR = registerOuterArmor("monster_creeper_girl_outer_armor");
	public static final ModelLayerLocation HUMANOID_CREEPER_GIRL = register("humanoid_creeper_girl");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P0 = register("pregnant_humanoid_creeper_girl_p0");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P1 = register("pregnant_humanoid_creeper_girl_p1");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P2 = register("pregnant_humanoid_creeper_girl_p2");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P3 = register("pregnant_humanoid_creeper_girl_p3");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P4 = register("pregnant_humanoid_creeper_girl_p4");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P5 = register("pregnant_humanoid_creeper_girl_p5");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P6 = register("pregnant_humanoid_creeper_girl_p6");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P7 = register("pregnant_humanoid_creeper_girl_p7");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P8 = register("pregnant_humanoid_creeper_girl_p8");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_P9 = register("pregnant_humanoid_creeper_girl_p9");
	public static final ModelLayerLocation HUMANOID_CREEPER_GIRL_ENERGY_ARMOR = registerCustomArmor("humanoid_creeper_girl_energy_armor");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P0 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p0");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P1 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p1");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P2 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p2");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P3 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p3");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P4 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p4");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P5 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p5");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P6 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p6");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P7 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p7");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P8 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p8");
	public static final ModelLayerLocation PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P9 = registerCustomArmor("pregnant_humanoid_creeper_girl_energy_armor_p9");
	public static final ModelLayerLocation HUMANOID_CREEPER_GIRL_OUTER_ARMOR = registerOuterArmor("humanoid_creeper_girl_outer_armor");
	public static final ModelLayerLocation HUMANOID_CREEPER_GIRL_INNER_ARMOR = registerInnerArmor("humanoid_creeper_girl_inner_armor");
	public static final ModelLayerLocation ZOMBIE_GIRL = register("zombie_girl");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P0 = register("pregnant_zombie_girl_p0");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P1 = register("pregnant_zombie_girl_p1");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P2 = register("pregnant_zombie_girl_p2");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P3 = register("pregnant_zombie_girl_p3");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P4 = register("pregnant_zombie_girl_p4");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P5 = register("pregnant_zombie_girl_p5");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P6 = register("pregnant_zombie_girl_p6");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P7 = register("pregnant_zombie_girl_p7");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P8 = register("pregnant_zombie_girl_p8");
	public static final ModelLayerLocation PREGNANT_ZOMBIE_GIRL_P9 = register("pregnant_zombie_girl_p9");
	public static final ModelLayerLocation ZOMBIE_GIRL_OUTER_ARMOR = registerOuterArmor("zombie_girl_outer_armor");
	public static final ModelLayerLocation ZOMBIE_GIRL_INNER_ARMOR = registerInnerArmor("zombie_girl_inner_armor");

	private static ModelLayerLocation register(String modelPath) {
		return register(modelPath, "main");
	}
	
	private static ModelLayerLocation register(String modelPath, String layer) {
		ModelLayerLocation modellayerlocation = createLocation(modelPath, layer);
		if (!ALL_MODELS.add(modellayerlocation)) {
			throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
		} else {
			return modellayerlocation;
		}
	}

	private static ModelLayerLocation registerInnerArmor(String modelPath) {
		return register(modelPath, "inner_armor");
	}

	private static ModelLayerLocation registerOuterArmor(String modelPath) {
		return register(modelPath, "outer_armor");
	}
	
	private static ModelLayerLocation registerCustomArmor(String modelPath) {
		return register(modelPath, "armor");
	}
	
	private static ModelLayerLocation createLocation(String modelPath, String layer) {
		return new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath(modelPath), layer);
	}
}
