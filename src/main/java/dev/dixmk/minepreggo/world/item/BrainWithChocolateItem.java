package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class BrainWithChocolateItem extends AbstractBrain implements IItemCraving {
	public BrainWithChocolateItem() {
		super(8);
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 10;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SWEET;
	}
}
