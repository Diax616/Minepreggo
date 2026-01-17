package dev.dixmk.minepreggo.network.capability;

import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyData;

public interface IPlayerPregnancyData extends IPregnancyData {

	Set<Species> getBabiesBySpecies();
	int getNumOfBabiesBySpecies(Species species);
	
	@Nullable ImmutablePair<Craving, Species> getTypeOfCravingBySpecies();	
	void setTypeOfCravingBySpecies(@Nullable ImmutablePair<Craving, Species> craving);
	void clearTypeOfCravingBySpecies();
	
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

