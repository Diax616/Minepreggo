package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class ActivatedGunpowderWithSaltItem extends ActivatedGunpowderItem implements IItemCraving {
	public ActivatedGunpowderWithSaltItem() {
		super();
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 4;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SALTY;
	}
}
