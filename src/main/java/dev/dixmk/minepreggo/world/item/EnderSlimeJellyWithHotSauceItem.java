package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class EnderSlimeJellyWithHotSauceItem extends EnderSlimeJellyItem implements ICravingItem {

	public EnderSlimeJellyWithHotSauceItem() {
		super(13);
	}
	
	@Override
	public int getGratification() {
		return 12;
	}

	@Override
	public Craving getCravingType() {
		return Craving.SPICY;
	}

	@Override
	public Species getSpeciesType() {
		return Species.ENDER;
	}

	@Override
	public float getPenalty() {
		return 0.1f;
	}
}
