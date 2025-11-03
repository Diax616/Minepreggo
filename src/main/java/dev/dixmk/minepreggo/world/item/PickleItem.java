package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import net.minecraft.world.food.FoodProperties;

public class PickleItem extends Item implements ICraving {
	public PickleItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1f).build()));
	}

	@Override
	public @NonNegative int getGratification() {
		return 7;
	}

	@Override
	public Craving getTypeOfCraving() {
		return Craving.SALTY;
	}
}
