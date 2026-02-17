package dev.dixmk.minepreggo.world.item;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import net.minecraft.world.item.Rarity;

public class BrainWithHotSauceItem extends BrainItem implements ICravingItem {
	public BrainWithHotSauceItem() {
		super(15, Rarity.COMMON);
	}
	
	@Override
	@Nonnegative
	public int getGratification() {
		return 9;
	}
	
	@Override
	public Craving getCravingType() {
		return Craving.SPICY;
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
