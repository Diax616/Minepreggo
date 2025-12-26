package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class SourActivatedGunpowderItem extends ActivatedGunpowderItem implements IItemCraving {
	public SourActivatedGunpowderItem() {
		super();
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 4;
	}
	
	@Override
	public Craving getCravingType() {
		return Craving.SOUR;
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

