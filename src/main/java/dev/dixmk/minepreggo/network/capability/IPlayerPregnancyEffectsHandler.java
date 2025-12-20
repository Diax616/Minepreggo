package dev.dixmk.minepreggo.network.capability;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;

public interface IPlayerPregnancyEffectsHandler extends IPregnancyEffectsHandler {
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
