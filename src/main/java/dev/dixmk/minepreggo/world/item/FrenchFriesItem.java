package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class FrenchFriesItem extends Item implements ICravingItem {
	public FrenchFriesItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.1f).alwaysEat().build()));
	}

	@Override
	public @Nonnegative int getGratification() {
		return 6;
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
