package dev.dixmk.minepreggo.network.capability;

import java.util.Set;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Baby;

public interface IPlayerPregnancySystemHandler extends IPregnancySystemHandler {

	Set<Baby> getTypesOfBabies();
	int getNumOfBabiesByType(Baby babyType);
	void addBaby(Baby babyType, @NonNegative int num);
	
}

