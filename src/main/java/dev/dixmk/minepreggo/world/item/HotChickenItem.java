package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.food.FoodProperties;

public class HotChickenItem extends Item implements IItemCraving {
	public HotChickenItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.2f).meat().build()));
	}

	@Override
	public @NonNegative int getGratification() {
		return 6;
	}

	@Override
	public Craving getTypeOfCraving() {
		return Craving.SPICY;
	}
}
