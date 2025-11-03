package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;

public class BrainWithChocolateItem extends AbstractBrain implements ICraving {
	public BrainWithChocolateItem() {
		super(8);
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 10;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SWEET;
	}
}
