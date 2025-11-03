package dev.dixmk.minepreggo.client.model.armor;

import java.util.Collections;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ArmorModelHelper {
	
	private ArmorModelHelper() {}
	
	public static HumanoidModel<LivingEntity> createHumanoidKneeBraceArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("left_leg", new KneeBraceModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(KneeBraceModel.LAYER_LOCATION)).leftLeg,
						"right_leg", new KneeBraceModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(KneeBraceModel.LAYER_LOCATION)).rightLeg,
						"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createFemaleP0HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleChestPlateP0Model(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP0Model.LAYER_LOCATION)).body,
				"left_arm", new FemaleChestPlateP0Model(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP0Model.LAYER_LOCATION)).leftArm,
				"right_arm", new FemaleChestPlateP0Model(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP0Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternalP1HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new MaternityChestPlateP1Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP1Model.LAYER_LOCATION)).body,
				"left_arm", new MaternityChestPlateP1Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP1Model.LAYER_LOCATION)).leftArm,
				"right_arm", new MaternityChestPlateP1Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP1Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternalP2HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new MaternityChestPlateP2Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP2Model.LAYER_LOCATION)).body,
				"left_arm", new MaternityChestPlateP2Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP2Model.LAYER_LOCATION)).leftArm,
				"right_arm", new MaternityChestPlateP2Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP2Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternalP3HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new MaternityChestPlateP3Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP3Model.LAYER_LOCATION)).body,
				"left_arm", new MaternityChestPlateP3Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP3Model.LAYER_LOCATION)).leftArm,
				"right_arm", new MaternityChestPlateP3Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP3Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternalP4HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new MaternityChestPlateP4Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP4Model.LAYER_LOCATION)).body,
				"left_arm", new MaternityChestPlateP4Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP4Model.LAYER_LOCATION)).leftArm,
				"right_arm", new MaternityChestPlateP4Model(Minecraft.getInstance().getEntityModels().bakeLayer(MaternityChestPlateP4Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternalP5HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldP5Model(Minecraft.getInstance().getEntityModels().bakeLayer(BellyShieldP5Model.LAYER_LOCATION)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternalP6HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldP6Model(Minecraft.getInstance().getEntityModels().bakeLayer(BellyShieldP6Model.LAYER_LOCATION)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> createMaternalP7HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldP7Model(Minecraft.getInstance().getEntityModels().bakeLayer(BellyShieldP7Model.LAYER_LOCATION)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
		
	public static HumanoidModel<LivingEntity> createMaternalP8HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldP8Model(Minecraft.getInstance().getEntityModels().bakeLayer(BellyShieldP8Model.LAYER_LOCATION)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
}
