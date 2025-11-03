package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;

public class ActivatedGunpowderWithChocolateItem extends ActivatedGunpowderItem implements ICraving {
	public ActivatedGunpowderWithChocolateItem() {
		super(6);
	}

	@Override
	@NonNegative
	public int getGratification() {
		return 6;
	}

	@Override
	public Craving getTypeOfCraving() {
		return Craving.SWEET;
	}
}
