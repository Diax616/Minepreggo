package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class EnderSlimeJellyWithSaltItem extends EnderSlimeJellyItem implements ICravingItem {

	public EnderSlimeJellyWithSaltItem() {
		super(11);
	}
	
	@Override
	public int getGratification() {
		return 8;
	}

	@Override
	public Craving getCravingType() {
		return Craving.SALTY;
	}

	@Override
	public Species getSpeciesType() {
		return Species.ENDER;
	}

	@Override
	public float getPenalty() {
		return 0.2f;
	}
}
