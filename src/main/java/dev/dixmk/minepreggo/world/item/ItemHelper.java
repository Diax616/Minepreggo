package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.init.MinepreggoItems;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;

public class ItemHelper {

	private ItemHelper() {}
		
	public static boolean isHelmet(ItemStack item) {
		if (item == null) return false;
		return isHelmet(item.getItem());
	}
	
	public static boolean isHelmet(Item item) {
		return item instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.HEAD;
	}
	
	public static boolean isBoot(ItemStack item) {
		if (item == null) return false;
		return isBoot(item.getItem());
	}
	
	public static boolean isBoot(Item item) {
		return item instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.FEET;
	}
	
	public static boolean isLegging(ItemStack item) {
		if (item == null) return false;	
		return isLegging(item.getItem());
	}
	
	public static boolean isLegging(Item item) {
		return item instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.LEGS;
	}
	
	public static boolean isChest(ItemStack item) {
		if (item == null) return false;	
		return isChest(item.getItem());
	}
	
	public static boolean isChest(Item item) {
		return item instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.CHEST;
	}
	
	public static boolean isMilk(ItemStack item) {
		if (item == null) return false;	
		return isMilk(item.getItem());
	}
	
	public static boolean isMilk(Item item) {
    	return item == Items.MILK_BUCKET || item instanceof AbstractBreastMilk;
	}
	
	public static Item getRandomHelmet(RandomSource random) {
		return switch (random.nextInt(5)) {
		case 0 -> Items.LEATHER_HELMET;
		case 1 -> Items.IRON_HELMET;
		case 2 -> Items.GOLDEN_HELMET;
		case 3 -> Items.DIAMOND_HELMET;
		default -> Items.CHAINMAIL_HELMET;
		};
	}
	
	public static Item getRandomAxe(RandomSource random) {
		return switch (random.nextInt(5)) {
		case 0 -> Items.WOODEN_AXE;
		case 1 -> Items.STONE_AXE;
		case 2 -> Items.IRON_AXE;
		case 3 -> Items.GOLDEN_AXE;
		default -> Items.DIAMOND_AXE;
		};
	}
	
	public static boolean canPickUp(Player player, ItemStack itemstack) {
	    return PlayerHelper.isInvencible(player) || itemstack.getEnchantmentLevel(Enchantments.BINDING_CURSE) == 0;	
	}
	
	public static ItemStack createMaternityLeatherChesplate(PregnancyPhase phase) {
		return switch (phase) {
			case P0 -> new ItemStack(MinepreggoItems.FEMALE_LEATHER_CHESTPLATE.get());
			case P1 -> new ItemStack(MinepreggoItems.MATERNITY_LEATHER_P1_CHESTPLATE.get());
			case P2 -> new ItemStack(MinepreggoItems.MATERNITY_LEATHER_P2_CHESTPLATE.get());
			case P3 -> new ItemStack(MinepreggoItems.MATERNITY_LEATHER_P3_CHESTPLATE.get());
			case P4 -> new ItemStack(MinepreggoItems.MATERNITY_LEATHER_P4_CHESTPLATE.get());
			default -> ItemStack.EMPTY;
		};
	}
	
	public static ItemStack createMaternityChainmailChesplate(PregnancyPhase phase) {
		return switch (phase) {
			case P0 -> new ItemStack(MinepreggoItems.FEMALE_CHAINMAIL_CHESTPLATE.get());
			case P1 -> new ItemStack(MinepreggoItems.MATERNITY_CHAINMAIL_P1_CHESTPLATE.get());
			case P2 -> new ItemStack(MinepreggoItems.MATERNITY_CHAINMAIL_P2_CHESTPLATE.get());
			case P3 -> new ItemStack(MinepreggoItems.MATERNITY_CHAINMAIL_P3_CHESTPLATE.get());
			case P4 -> new ItemStack(MinepreggoItems.MATERNITY_CHAINMAIL_P4_CHESTPLATE.get());
			default -> ItemStack.EMPTY;
		};
	}
	
	public static ItemStack createMaternityGoldenChesplate(PregnancyPhase phase) {
		return switch (phase) {
			case P0 -> new ItemStack(MinepreggoItems.FEMALE_GOLDEN_CHESTPLATE.get());
			case P1 -> new ItemStack(MinepreggoItems.MATERNITY_GOLDEN_P1_CHESTPLATE.get());
			case P2 -> new ItemStack(MinepreggoItems.MATERNITY_GOLDEN_P2_CHESTPLATE.get());
			case P3 -> new ItemStack(MinepreggoItems.MATERNITY_GOLDEN_P3_CHESTPLATE.get());
			case P4 -> new ItemStack(MinepreggoItems.MATERNITY_GOLDEN_P4_CHESTPLATE.get());
			default -> ItemStack.EMPTY;
		};
	}
	
	public static ItemStack createMaternityIronChesplate(PregnancyPhase phase) {
		return switch (phase) {
			case P0 -> new ItemStack(MinepreggoItems.FEMALE_IRON_CHESTPLATE.get());
			case P1 -> new ItemStack(MinepreggoItems.MATERNITY_IRON_P1_CHESTPLATE.get());
			case P2 -> new ItemStack(MinepreggoItems.MATERNITY_IRON_P2_CHESTPLATE.get());
			case P3 -> new ItemStack(MinepreggoItems.MATERNITY_IRON_P3_CHESTPLATE.get());
			case P4 -> new ItemStack(MinepreggoItems.MATERNITY_IRON_P4_CHESTPLATE.get());
			default -> ItemStack.EMPTY;
		};
	}
	
	public static ItemStack createMaternityDiamondChesplate(PregnancyPhase phase) {
		return switch (phase) {
			case P0 -> new ItemStack(MinepreggoItems.FEMALE_DIAMOND_CHESTPLATE.get());
			case P1 -> new ItemStack(MinepreggoItems.MATERNITY_DIAMOND_P1_CHESTPLATE.get());
			case P2 -> new ItemStack(MinepreggoItems.MATERNITY_DIAMOND_P2_CHESTPLATE.get());
			case P3 -> new ItemStack(MinepreggoItems.MATERNITY_DIAMOND_P3_CHESTPLATE.get());
			case P4 -> new ItemStack(MinepreggoItems.MATERNITY_DIAMOND_P4_CHESTPLATE.get());
			default -> ItemStack.EMPTY;
		};
	}
	
	public static ItemStack createMaternityNetheriteChesplate(PregnancyPhase phase) {
		return switch (phase) {
			case P0 -> new ItemStack(MinepreggoItems.FEMALE_NETHERITE_CHESTPLATE.get());
			case P1 -> new ItemStack(MinepreggoItems.MATERNITY_NETHERITE_P1_CHESTPLATE.get());
			case P2 -> new ItemStack(MinepreggoItems.MATERNITY_NETHERITE_P2_CHESTPLATE.get());
			case P3 -> new ItemStack(MinepreggoItems.MATERNITY_NETHERITE_P3_CHESTPLATE.get());
			case P4 -> new ItemStack(MinepreggoItems.MATERNITY_NETHERITE_P4_CHESTPLATE.get());
			default -> ItemStack.EMPTY;
		};
	}
	
	public static ItemStack createMaternityChestplateByVanillaChestplate(ItemStack chestplate, PregnancyPhase pregnancyPhase) {
		ItemStack maternityChestplate = ItemStack.EMPTY;
		if (chestplate.is(Items.LEATHER_CHESTPLATE)) {
			maternityChestplate = ItemHelper.createMaternityLeatherChesplate(pregnancyPhase);
		}
		if (chestplate.is(Items.CHAINMAIL_CHESTPLATE)) {
			maternityChestplate = ItemHelper.createMaternityChainmailChesplate(pregnancyPhase);
		}
		else if (chestplate.is(Items.IRON_CHESTPLATE)) {
			maternityChestplate = ItemHelper.createMaternityIronChesplate(pregnancyPhase);
		}
		else if (chestplate.is(Items.GOLDEN_CHESTPLATE)) {
			maternityChestplate = ItemHelper.createMaternityGoldenChesplate(pregnancyPhase);
		}
		else if (chestplate.is(Items.DIAMOND_CHESTPLATE)) {
			maternityChestplate = ItemHelper.createMaternityDiamondChesplate(pregnancyPhase);
		}
		else if (chestplate.is(Items.NETHERITE_CHESTPLATE)) {
			maternityChestplate = ItemHelper.createMaternityNetheriteChesplate(pregnancyPhase);
		}
		
		if (maternityChestplate.isEmpty()) {
			return switch (pregnancyPhase) {
				case P5 -> new ItemStack(MinepreggoItems.BELLY_SHIELD_P5.get());
				case P6 -> new ItemStack(MinepreggoItems.BELLY_SHIELD_P6.get());
				case P7 -> new ItemStack(MinepreggoItems.BELLY_SHIELD_P7.get());
				case P8 -> new ItemStack(MinepreggoItems.BELLY_SHIELD_P8.get());
				default -> ItemStack.EMPTY;
			};
		}
		
		return maternityChestplate;
	}
	
	public static ItemStack createFemaleChestplateByVanillaChestplate(ItemStack vanillaChestplate) {
		if (vanillaChestplate.is(Items.LEATHER_CHESTPLATE)) {
			return new ItemStack(MinepreggoItems.FEMALE_LEATHER_CHESTPLATE.get());
		}
		else if (vanillaChestplate.is(Items.CHAINMAIL_CHESTPLATE)) {
			return new ItemStack(MinepreggoItems.FEMALE_CHAINMAIL_CHESTPLATE.get());
		}
		else if (vanillaChestplate.is(Items.GOLDEN_CHESTPLATE)) {
			return new ItemStack(MinepreggoItems.FEMALE_GOLDEN_CHESTPLATE.get());
		}
		else if (vanillaChestplate.is(Items.IRON_CHESTPLATE)) {
			return new ItemStack(MinepreggoItems.FEMALE_IRON_CHESTPLATE.get());
		}
		else if (vanillaChestplate.is(Items.DIAMOND_CHESTPLATE)) {
			return new ItemStack(MinepreggoItems.FEMALE_DIAMOND_CHESTPLATE.get());
		}
		else if (vanillaChestplate.is(Items.NETHERITE_CHESTPLATE)) {
			return new ItemStack(MinepreggoItems.FEMALE_NETHERITE_CHESTPLATE.get());
		}
		
		return ItemStack.EMPTY;	
	}
	
	public static ItemStack createKneeBraceByVanillaLeggings(ItemStack vanillaChestplate) {
		if (vanillaChestplate.is(Items.LEATHER_LEGGINGS)) {
			return new ItemStack(MinepreggoItems.LEATHER_KNEE_BRACES.get());
		}
		else if (vanillaChestplate.is(Items.CHAINMAIL_LEGGINGS)) {
			return new ItemStack(MinepreggoItems.CHAINMAIL_KNEE_BRACES.get());
		}
		else if (vanillaChestplate.is(Items.GOLDEN_LEGGINGS)) {
			return new ItemStack(MinepreggoItems.GOLDEN_KNEE_BRACES.get());
		}
		else if (vanillaChestplate.is(Items.IRON_LEGGINGS)) {
			return new ItemStack(MinepreggoItems.IRON_KNEE_BRACES.get());
		}
		else if (vanillaChestplate.is(Items.DIAMOND_LEGGINGS)) {
			return new ItemStack(MinepreggoItems.DIAMOND_KNEE_BRACES.get());
		}
		else if (vanillaChestplate.is(Items.NETHERITE_LEGGINGS)) {
			return new ItemStack(MinepreggoItems.NETHERITE_KNEE_BRACES.get());
		}
		
		return ItemStack.EMPTY;	
	}
	
	public static void copyEnchantments(ItemStack source, ItemStack target) {
		source.getAllEnchantments().forEach(target::enchant);
	}
}
