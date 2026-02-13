package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public class PregnantPreggoMobSystemP3 
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PregnantPreggoMobSystemP2<E> {

	protected PregnantPreggoMobSystemP3(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve, float angerProbability) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve, angerProbability);
	}
	
	public PregnantPreggoMobSystemP3(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve);
	}

	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getPregnancyData().getBellyRubs() >= PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL;
	}
}
