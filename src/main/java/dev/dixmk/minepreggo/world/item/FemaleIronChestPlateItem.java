package dev.dixmk.minepreggo.world.item;

import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.minecraft.client.model.HumanoidModel;

import java.util.function.Consumer;

import dev.dixmk.minepreggo.client.model.armor.ArmorModelHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;

public abstract class FemaleIronChestPlateItem extends ArmorItem implements IFemaleArmor {
	protected FemaleIronChestPlateItem() {
		super(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties());
	}

	public abstract static class MaternityFemaleIronChestPlateItem extends FemaleIronChestPlateItem implements IMaternityArmor{
		final PregnancyPhase maxPregnancyPhase;
			
		protected MaternityFemaleIronChestPlateItem(PregnancyPhase maxPregnancyPhase) {
			this.maxPregnancyPhase = maxPregnancyPhase;
		}
		
		@Override
		public PregnancyPhase getMinPregnancyPhaseAllowed() {
			return maxPregnancyPhase;
		}
	}
	
	
	public static class ChestplateP0 extends FemaleIronChestPlateItem {
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return ArmorModelHelper.createFemaleP0HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/iron_p0_layer_1.png";
		}
	}
	
	public static class MaternityChestplateP1 extends MaternityFemaleIronChestPlateItem {
		public MaternityChestplateP1() {
			super(PregnancyPhase.P1);
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return ArmorModelHelper.createMaternalP1HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/iron_p1_layer_1.png";
		}
	}
	
	public static class MaternityChestplateP2 extends MaternityFemaleIronChestPlateItem {
		public MaternityChestplateP2() {
			super(PregnancyPhase.P2);
		}
		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return ArmorModelHelper.createMaternalP2HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/iron_p2_layer_1.png";
		}
	}
	
	public static class MaternityChestplateP3 extends MaternityFemaleIronChestPlateItem {
		public MaternityChestplateP3() {
			super(PregnancyPhase.P3);
		}
		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return ArmorModelHelper.createMaternalP3HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/iron_p3_layer_1.png";
		}
	}
	
	public static class MaternityChestplateP4 extends MaternityFemaleIronChestPlateItem {
		public MaternityChestplateP4() {
			super(PregnancyPhase.P4);
		}
		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return ArmorModelHelper.createMaternalP4HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/iron_p4_layer_1.png";
		}
	}
}
