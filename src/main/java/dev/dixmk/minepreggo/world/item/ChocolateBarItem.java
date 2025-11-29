package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.food.FoodProperties;

public class ChocolateBarItem extends Item implements IItemCraving {
	public ChocolateBarItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.2f).build()));
	}

	@Override
	public @NonNegative int getGratification() {
		return 8;
	}

	@Override
	public Craving getTypeOfCraving() {
		return Craving.SWEET;
	}
}
