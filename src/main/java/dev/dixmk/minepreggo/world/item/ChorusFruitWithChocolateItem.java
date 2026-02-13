package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.item.Item;

public class ChorusFruitWithChocolateItem extends ChorusFruitItem implements ICravingItem {
	
	public ChorusFruitWithChocolateItem() {
		super((new Item.Properties()).food((new FoodProperties.Builder()).nutrition(8).saturationMod(0.3F).alwaysEat().build()));
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 12;
	}
	
	@Override
	public Craving getCravingType() {
		return Craving.SWEET;
	}
	
	@Override
	public Species getSpeciesType() {
		return Species.DRAGON;
	}

	@Override
	public @Nonnegative float getPenalty() {
		return 0.2f;
	}
}