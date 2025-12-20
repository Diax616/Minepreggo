package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.Craving;

public interface IItemCraving {

	@Nonnegative int getGratification();
	
	Craving getTypeOfCraving();
	
}
