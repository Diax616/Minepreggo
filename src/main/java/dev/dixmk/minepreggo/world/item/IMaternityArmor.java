package dev.dixmk.minepreggo.world.item;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;

public interface IMaternityArmor extends IFemaleArmor {

	PregnancyPhase getMinPregnancyPhaseAllowed();
	
	boolean areBoobsExposed();
	
}
