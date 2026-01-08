package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

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
}
