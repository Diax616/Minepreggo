package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class BrainWithSaltItem extends AbstractBrain implements ICravingItem {
	public BrainWithSaltItem() {
		super();
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 7;
	}
	
	@Override
	public Craving getCravingType() {
		return Craving.SALTY;
	}
	
	@Override
	public Species getSpeciesType() {
		return Species.ZOMBIE;
	}

	@Override
	public @Nonnegative float getPenalty() {
		return 0.2f;
	}
}
