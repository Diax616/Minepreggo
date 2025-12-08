package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;

public interface IFemalePlayer extends IFemaleEntity {

	PlayerPregnancySystemImpl getPregnancySystem();	
	PlayerPregnancyEffectsImpl getPregnancyEffects();
}
