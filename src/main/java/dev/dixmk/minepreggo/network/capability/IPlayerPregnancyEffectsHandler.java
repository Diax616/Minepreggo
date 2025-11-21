package dev.dixmk.minepreggo.network.capability;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.Species;

public interface IPlayerPregnancyEffectsHandler extends IPregnancyEffectsHandler {
	@Nullable Pair<Craving, Species> getTypeOfCravingBySpecies();	
	void setTypeOfCravingBySpecies(@Nullable Pair<Craving, Species> craving);
	void clearTypeOfCravingBySpecies();
}
