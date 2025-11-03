package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;

public class ActivatedGunpowderWithHotSauceItem extends ActivatedGunpowderItem implements ICraving {
	public ActivatedGunpowderWithHotSauceItem() {
		super();
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 5;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SPICY;
	}
}
