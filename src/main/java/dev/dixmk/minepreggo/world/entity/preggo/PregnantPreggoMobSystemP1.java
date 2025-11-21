package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;

public class PregnantPreggoMobSystemP1
	<E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler> extends PregnantPreggoMobSystemP0<E> {

	public PregnantPreggoMobSystemP1(E preggoMob, int totalTicksOfHungry) {
		super(preggoMob, totalTicksOfHungry);
	}

	@Override
	public boolean canFeedHerself() {
		return super.canFeedHerself() && !preggoMob.isIncapacitated();
	}
}
