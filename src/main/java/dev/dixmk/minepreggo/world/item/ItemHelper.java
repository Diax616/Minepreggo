package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class ItemHelper {

	private ItemHelper() {}
	
	
	public static boolean isHelmet(ItemStack item) {
		if (item == null) return false;
		return item.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.HEAD;
	}
	
	public static boolean isBoot(ItemStack item) {
		if (item == null) return false;
		return item.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.FEET;
	}
	
	public static boolean isLegging(ItemStack item) {
		if (item == null) return false;	
		return item.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.LEGS;
	}
	
	public static boolean isChest(ItemStack item) {
		if (item == null) return false;	
		return item.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.CHEST;
	}
}
