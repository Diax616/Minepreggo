package dev.dixmk.minepreggo.client.model.armor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArmorModelHelper {
	
	private ArmorModelHelper() {}
	
	private static final Map<String, HumanoidModel<LivingEntity>> MODEL_CACHE = new HashMap<>();
	
	private static HumanoidModel<LivingEntity> getOrCreateModel(String key, Supplier<HumanoidModel<LivingEntity>> supplier) {
		return MODEL_CACHE.computeIfAbsent(key, k -> supplier.get());
	}
	
	public static HumanoidModel<LivingEntity> createKneeBracesModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("knee_braces", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("left_leg", new KneeBracesModel(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.KNEE_BRACES)).leftLeg,
						"right_leg", new KneeBracesModel(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.KNEE_BRACES)).rightLeg,
						"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createFemaleChestplateModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("female_chestplate", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_CHESTPLATE)).body,
				"left_arm", new FemaleChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_CHESTPLATE)).leftArm,
				"right_arm", new FemaleChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_CHESTPLATE)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternityChestplateP1Model (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("maternity_chestplate_p1", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P1)).body,
				"left_arm", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P1)).leftArm,
				"right_arm", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P1)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();	
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternityChestplateP2Model (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("maternity_chestplate_p2", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P2)).body,
				"left_arm", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P2)).leftArm,
				"right_arm", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P2)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternityChestplateP3Model (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("maternity_chestplate_p3", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P3)).body,
				"left_arm", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P3)).leftArm,
				"right_arm", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P3)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternityChestplateP4Model (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("maternity_chestplate_p4", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P4)).body,
				"left_arm", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P4)).leftArm,
				"right_arm", new FemaleMaternityChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P4)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createBellyShieldP5Model (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("belly_shield_p5", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.BELLY_SHIELD_P5)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createBellyShieldP6Model (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("belly_shield_p6", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.BELLY_SHIELD_P6)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createBellyShieldP7Model (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("belly_shield_p7", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.BELLY_SHIELD_P7)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));			
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
		
	public static HumanoidModel<LivingEntity> createBellyShieldP8Model (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = getOrCreateModel("belly_shield_p8", () -> new HumanoidModel<>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MinepreggoModelLayers.BELLY_SHIELD_P8)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
}
