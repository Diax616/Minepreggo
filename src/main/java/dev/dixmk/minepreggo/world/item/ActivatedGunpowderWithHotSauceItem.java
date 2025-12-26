package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class ActivatedGunpowderWithHotSauceItem extends ActivatedGunpowderItem implements IItemCraving {
	public ActivatedGunpowderWithHotSauceItem() {
		super();
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 6;
	}
	
	@Override
	public Craving getCravingType() {
		return Craving.SPICY;
	}
	
	@Override
	public Species getSpeciesType() {
		return Species.CREEPER;
	}

	@Override
	public @Nonnegative float getPenalty() {
		return 0.3f;
	}
}
