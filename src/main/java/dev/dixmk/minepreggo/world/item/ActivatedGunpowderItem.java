package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class ActivatedGunpowderItem extends Item {
	public ActivatedGunpowderItem(int nutritionValue) {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(nutritionValue).saturationMod(0.1f).build()));
	}
	
	public ActivatedGunpowderItem() {
		this(5);
	}
	
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 60;
	}
}
