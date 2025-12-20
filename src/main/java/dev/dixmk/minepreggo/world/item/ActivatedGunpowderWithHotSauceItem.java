package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class ActivatedGunpowderWithHotSauceItem extends ActivatedGunpowderItem implements IItemCraving {
	public ActivatedGunpowderWithHotSauceItem() {
		super();
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 5;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SPICY;
	}
}
