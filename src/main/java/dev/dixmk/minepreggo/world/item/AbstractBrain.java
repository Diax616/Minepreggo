package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public abstract class AbstractBrain extends Item {

    protected AbstractBrain(int nutritionValue) {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(nutritionValue).saturationMod(0.2f).build()));
	}

    protected AbstractBrain() {
    	this(7);
    }
     
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 40;
	}
}
