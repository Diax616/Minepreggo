package dev.dixmk.minepreggo.network.capability;

import java.util.Set;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;

public interface IPlayerPregnancySystemHandler extends IPregnancySystemHandler {

	Set<Species> getBabiesBySpecies();
	int getNumOfBabiesBySpecies(Species species);
	
}

