package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;

public interface IPregnancyP4 
	<E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> {

	PreggoMobPregnancySystemP4<E> getPregnancySystemP4();
}
