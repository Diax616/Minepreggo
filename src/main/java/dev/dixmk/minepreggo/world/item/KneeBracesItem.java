package dev.dixmk.minepreggo.world.item;

import java.util.function.Consumer;

import dev.dixmk.minepreggo.client.model.armor.ArmorModelHelper;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public abstract class KneeBracesItem extends ArmorItem {
	protected KneeBracesItem(int durability, int defense, int enchantmentValue, Item repairIngredient) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * durability;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{2, defense, 6, 2}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return enchantmentValue;
			}

			@Override
			public SoundEvent getEquipSound() {
				return SoundEvents.ARMOR_EQUIP_LEATHER;
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(repairIngredient));
			}

			@Override
			public String getName() {
				return "knee_brace";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, ArmorItem.Type.LEGGINGS, new Item.Properties());				
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			@OnlyIn(Dist.CLIENT)
			public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
				return ArmorModelHelper.createKneeBracesModel(living, stack, slot, defaultModel);
			}
		});
	}

	public static class LeatherKneeBraces extends KneeBracesItem implements DyeableLeatherItem {
		public LeatherKneeBraces() {
			super(1, 1, 10, Items.LEATHER);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {		
		    if ("overlay".equals(type)) {
		        return "minepreggo:textures/models/armor/leather_knee_braces_layer_1_overlay.png";
		    }
		    return "minepreggo:textures/models/armor/leather_knee_braces_layer_1.png";
			
		}
	}
	
	public static class ChainmailKneeBraces extends KneeBracesItem {
		public ChainmailKneeBraces() {
			super(4, 2, 12, Items.IRON_INGOT);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {		
		    return "minepreggo:textures/models/armor/chainmail_knee_braces_layer_1.png";		
		}
	}
	
	public static class IronKneeBraces extends KneeBracesItem {
		public IronKneeBraces() {
			super(5, 3, 9, Items.IRON_INGOT);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/iron_knee_braces_layer_1.png";
		}
	}
	
	public static class GoldenKneeBraces extends KneeBracesItem {
		public GoldenKneeBraces() {
			super(3, 1, 25, Items.GOLD_INGOT);
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/golden_knee_braces_layer_1.png";
		}
	}
	
	public static class DiamondKneeBraces extends KneeBracesItem {
		public DiamondKneeBraces() {
			super(7, 5, 10, Items.DIAMOND);
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/diamond_knee_braces_layer_1.png";
		}
	}
	
	public static class NetheriteKneeBraces extends KneeBracesItem {
		public NetheriteKneeBraces() {
			super(9, 7, 15, Items.NETHERITE_INGOT);
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/netherite_knee_braces_layer_1.png";
		}
	}
}
