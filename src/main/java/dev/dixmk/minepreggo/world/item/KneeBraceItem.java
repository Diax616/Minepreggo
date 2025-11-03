package dev.dixmk.minepreggo.world.item;

import java.util.function.Consumer;

import dev.dixmk.minepreggo.client.model.armor.ArmorModelHelper;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraftforge.registries.ForgeRegistries;

public abstract class KneeBraceItem extends ArmorItem {
	protected KneeBraceItem(int durability, int defense, int enchantmentValue, Item repairIngredient) {
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
				return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("item.armor.equip_leather"));
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
				return ArmorModelHelper.createHumanoidKneeBraceArmorModel(living, stack, slot, defaultModel);
			}
		});
	}

	public static class LeatherKneeBrace extends KneeBraceItem implements DyeableLeatherItem {
		public LeatherKneeBrace() {
			super(1, 1, 10, Items.LEATHER);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {		
		    if ("overlay".equals(type)) {
		        return "minepreggo:textures/models/armor/leather_knee_brace_layer_2_overlay.png";
		    }
		    return "minepreggo:textures/models/armor/leather_knee_brace_layer_2.png";
			
		}
	}
	
	public static class IronKneeBrace extends KneeBraceItem {
		public IronKneeBrace() {
			super(5, 3, 9, Items.IRON_INGOT);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/iron_knee_brace_layer_2.png";
		}
	}
	
	public static class GoldenKneeBrace extends KneeBraceItem {
		public GoldenKneeBrace() {
			super(3, 1, 25, Items.GOLD_INGOT);
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/golden_knee_brace_layer_2.png";
		}
	}
	
	public static class DiamondKneeBrace extends KneeBraceItem {
		public DiamondKneeBrace() {
			super(7, 5, 10, Items.DIAMOND);
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/diamond_knee_brace_layer_2.png";
		}
	}
	
	public static class NetheriteKneeBrace extends KneeBraceItem {
		public NetheriteKneeBrace() {
			super(9, 7, 15, Items.NETHERITE_INGOT);
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/netherite_knee_brace_layer_2.png";
		}
	}
}
