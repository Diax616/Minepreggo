package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class SourBrainItem extends AbstractBrain implements ICravingItem {
	public SourBrainItem() {
		super(14);
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 6;
	}
	
	@Override
	public Craving getCravingType() {
		return Craving.SOUR;
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
