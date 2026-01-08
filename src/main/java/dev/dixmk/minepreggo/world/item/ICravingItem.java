package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public interface ICravingItem {

	@Nonnegative int getGratification();
	
	Craving getCravingType();
	
	Species getSpeciesType();
	
	@Nonnegative float getPenalty();
}
