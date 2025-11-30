package dev.dixmk.minepreggo.network.capability;

import java.util.Set;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;

public interface IPlayerPregnancySystemHandler extends IPregnancySystemHandler {

	Set<Species> getBabiesBySpecies();
	int getNumOfBabiesBySpecies(Species species);
	
	public void incrementSprintingTimer();
	public void resetSprintingTimer();
	public int getSprintingTimer();
	
	public void incrementNumOfJumps();
	public void resetNumOfJumps();
	public int getNumOfJumps();
	
	public int getSneakingTimer();
	public void resetSneakingTimer();
	public void incrementSneakingTimer();
}

