package dev.dixmk.minepreggo.world.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;

public interface ICraving {

	@NonNegative
	int getGratification();
	
	Craving getTypeOfCraving();
	
}
