package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class BrainWithSaltItem extends AbstractBrain implements IItemCraving {
	public BrainWithSaltItem() {
		super();
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 6;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SALTY;
	}
}
