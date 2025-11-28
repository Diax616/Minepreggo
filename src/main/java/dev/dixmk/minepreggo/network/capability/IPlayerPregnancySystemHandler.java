package dev.dixmk.minepreggo.network.capability;

import java.util.Set;

import dev.dixmk.minepreggo.world.entity.preggo.Species;

public interface IPlayerPregnancySystemHandler extends IPregnancySystemHandler {

	Set<Species> getTypesOfBabies();
	int getNumOfBabiesByType(Species babyType);
}

