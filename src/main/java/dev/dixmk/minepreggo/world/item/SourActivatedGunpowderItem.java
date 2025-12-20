package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class SourActivatedGunpowderItem extends ActivatedGunpowderItem implements IItemCraving {
	public SourActivatedGunpowderItem() {
		super();
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 4;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SOUR;
	}
}

