package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.food.FoodProperties;

public class PickleItem extends Item implements ICravingItem {
	public PickleItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1f).build()));
	}

	@Override
	public @Nonnegative int getGratification() {
		return 7;
	}

	@Override
	public Craving getCravingType() {
		return Craving.SALTY;
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
