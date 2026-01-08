package dev.dixmk.minepreggo.world.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.food.FoodProperties;

public class HotChickenItem extends Item implements ICravingItem {
	public HotChickenItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(8).saturationMod(0.4f).meat().build()));
	}

	@Override
	public @Nonnegative int getGratification() {
		return 12;
	}

	@Override
	public Craving getCravingType() {
		return Craving.SPICY;
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
