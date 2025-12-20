package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.food.FoodProperties;

public class LemonIceCreamItem extends Item implements IItemCraving {
	public LemonIceCreamItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1f).alwaysEat().build()));
	}

	@Override
	public @Nonnegative int getGratification() {
		return 5;
	}

	@Override
	public Craving getTypeOfCraving() {
		return Craving.SOUR;
	}
}
