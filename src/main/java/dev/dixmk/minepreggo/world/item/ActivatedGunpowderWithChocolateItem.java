package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class ActivatedGunpowderWithChocolateItem extends ActivatedGunpowderItem implements IItemCraving {
	public ActivatedGunpowderWithChocolateItem() {
		super(6);
	}

	@Override
	@Nonnegative
	public int getGratification() {
		return 6;
	}

	@Override
	public Craving getTypeOfCraving() {
		return Craving.SWEET;
	}
}
