package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;

public interface IPregnancyP6
	<E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> {

	PreggoMobPregnancySystemP6<E> getPregnancySystemP6();
}
