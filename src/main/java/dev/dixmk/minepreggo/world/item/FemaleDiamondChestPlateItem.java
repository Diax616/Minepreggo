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

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;

public abstract class FemaleDiamondChestPlateItem extends FemaleChestPlateItem {
	protected FemaleDiamondChestPlateItem() {
		super(ArmorMaterials.DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties());
	}

	public static class Chestplate extends FemaleDiamondChestPlateItem {
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					defaultModel.setAllVisible(false);
					return defaultModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/diamond_layer_1.png";
		}
	}
	
	public abstract static class MaternityFemaleDiamondChestPlateItem extends FemaleDiamondChestPlateItem implements IMaternityArmor {
		private final PregnancyPhase current;
			
		protected MaternityFemaleDiamondChestPlateItem(PregnancyPhase current) {
			this.current = current;
		}
		
		@Override
		public PregnancyPhase getCurrentPregnancyPhase() {
			return current;
		}
	}
	
	public static class MaternityChestplateP1 extends MaternityFemaleDiamondChestPlateItem {
		public MaternityChestplateP1() {
			super(PregnancyPhase.P1);
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					defaultModel.setAllVisible(false);
					return defaultModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/diamond_p1_layer_1.png";
		}
	}
	
	public static class MaternityChestplateP2 extends MaternityFemaleDiamondChestPlateItem {
		public MaternityChestplateP2() {
			super(PregnancyPhase.P2);
		}
		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					defaultModel.setAllVisible(false);
					return defaultModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/diamond_p2_layer_1.png";
		}
	}
	
	public static class MaternityChestplateP3 extends MaternityFemaleDiamondChestPlateItem {
		public MaternityChestplateP3() {
			super(PregnancyPhase.P3);
		}
		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					defaultModel.setAllVisible(false);
					return defaultModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/diamond_p3_layer_1.png";
		}
	}
	
	public static class MaternityChestplateP4 extends MaternityFemaleDiamondChestPlateItem {
		public MaternityChestplateP4() {
			super(PregnancyPhase.P4);
		}
		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					defaultModel.setAllVisible(false);
					return defaultModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/diamond_p4_layer_1.png";
		}
	}
}
