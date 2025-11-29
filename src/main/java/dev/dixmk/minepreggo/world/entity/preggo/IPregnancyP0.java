package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;

public interface IPregnancyP0
	<E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> {

	PreggoMobPregnancySystemP0<E> getPregnancySystemP0();

}

