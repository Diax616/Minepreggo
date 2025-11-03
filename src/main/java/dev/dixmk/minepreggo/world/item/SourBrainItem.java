package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;

public class SourBrainItem extends AbstractBrain implements ICraving {
	public SourBrainItem() {
		super();
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 6;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SOUR;
	}
}
