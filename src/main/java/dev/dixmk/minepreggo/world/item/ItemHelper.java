package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
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
}
