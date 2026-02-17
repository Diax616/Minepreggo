package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public class PregnantPreggoMobSystemP4 
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PregnantPreggoMobSystemP3<E> {

	protected PregnantPreggoMobSystemP4(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve, float angerProbability) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve, angerProbability);
	}
	
	public PregnantPreggoMobSystemP4(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve, PregnancySystemHelper.HIGH_ANGER_PROBABILITY);
	}

	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getPregnancyData().getHorny() >= PregnancySystemHelper.MAX_HORNY_LEVEL;
	}
}
