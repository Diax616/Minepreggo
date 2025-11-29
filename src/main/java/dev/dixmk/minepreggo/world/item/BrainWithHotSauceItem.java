package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class BrainWithHotSauceItem extends AbstractBrain implements IItemCraving {
	public BrainWithHotSauceItem() {
		super(7);
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 8;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SPICY;
	}
}
