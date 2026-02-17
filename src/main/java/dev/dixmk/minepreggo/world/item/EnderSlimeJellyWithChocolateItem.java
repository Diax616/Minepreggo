package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class EnderSlimeJellyWithChocolateItem extends EnderSlimeJellyItem implements ICravingItem {

	public EnderSlimeJellyWithChocolateItem() {
		super(14);
	}
	
	@Override
	public int getGratification() {
		return 20;
	}

	@Override
	public Craving getCravingType() {
		return Craving.SWEET;
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
