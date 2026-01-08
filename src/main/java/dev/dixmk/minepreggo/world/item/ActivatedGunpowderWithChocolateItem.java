package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;

public class ActivatedGunpowderWithChocolateItem extends ActivatedGunpowderItem implements ICravingItem {
	public ActivatedGunpowderWithChocolateItem() {
		super(6);
	}

	@Override
	@Nonnegative
	public int getGratification() {
		return 20;
	}

	@Override
	public Craving getCravingType() {
		return Craving.SWEET;
	}

	@Override
	public Species getSpeciesType() {
		return Species.CREEPER;
	}

	@Override
	public @Nonnegative float getPenalty() {
		return 0.1f;
	}
}
