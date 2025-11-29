package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class BrainWithSaltItem extends AbstractBrain implements IItemCraving {
	public BrainWithSaltItem() {
		super();
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 6;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SALTY;
	}
}
