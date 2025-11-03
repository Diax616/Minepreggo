package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;

public class ActivatedGunpowderWithSaltItem extends ActivatedGunpowderItem implements ICraving {
	public ActivatedGunpowderWithSaltItem() {
		super();
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 4;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SALTY;
	}
}
