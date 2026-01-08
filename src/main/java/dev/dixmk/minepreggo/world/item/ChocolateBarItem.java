package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.food.FoodProperties;

public class ChocolateBarItem extends Item implements ICravingItem {
	public ChocolateBarItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.2f).build()));
	}

	@Override
	public @Nonnegative int getGratification() {
		return 20;
	}

	@Override
	public Craving getCravingType() {
		return Craving.SWEET;
	}
	
	@Override
	public Species getSpeciesType() {
		return Species.HUMAN;
	}

	@Override
	public @Nonnegative float getPenalty() {
		return 0.1f;
	}
}
