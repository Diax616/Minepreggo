package dev.dixmk.minepreggo.network.capability;

import java.util.Set;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Baby;

public interface IPlayerPregnancySystemHandler extends IPregnancySystemHandler {

	Set<Baby> getTypesOfBabies();
	int getNumOfBabiesByType(Baby babyType);
	void addBaby(Baby babyType, @Nonnegative int num);
	
}

