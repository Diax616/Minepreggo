package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;

public interface IItemCraving {

	@NonNegative
	int getGratification();
	
	Craving getTypeOfCraving();
	
}
