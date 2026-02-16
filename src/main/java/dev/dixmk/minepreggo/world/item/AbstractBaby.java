package dev.dixmk.minepreggo.world.item;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public abstract class AbstractBaby extends Item {
	private static final String NBT_MOTHER_ID = "MotherUUID";
	private static final String NBT_FATHER_ID = "FatherUUID";
	
	protected AbstractBaby() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}
	
	public static ItemStack createBabyItemStack(UUID motherUUID, @Nullable UUID fatherUUID, Item babyItem) {
		ItemStack babyStack = babyItem.getDefaultInstance();
		CompoundTag nbt = babyStack.getOrCreateTag();	
		nbt.putUUID(NBT_MOTHER_ID, motherUUID);	
		if (fatherUUID != null) {
			nbt.putUUID(NBT_FATHER_ID, fatherUUID);
		}
		return babyStack;
	}
	
	public static boolean isMotherOf(ItemStack babyStack, UUID motherUUID) {
		if (babyStack.hasTag()) {
			CompoundTag nbt = babyStack.getTag();
			if (nbt.contains(NBT_MOTHER_ID)) {
				return nbt.getUUID(NBT_MOTHER_ID).equals(motherUUID);
			}
		}
		return false;
	}
	
	public static boolean isFatherOf(ItemStack babyStack, UUID fatherUUID) {
		if (babyStack.hasTag()) {
			CompoundTag nbt = babyStack.getTag();
			if (nbt.contains(NBT_FATHER_ID)) {
				return nbt.getUUID(NBT_FATHER_ID).equals(fatherUUID);
			}
		}
		return false;
	}
}
