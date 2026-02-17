package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public class PregnantPreggoMobSystemP1
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PregnantPreggoMobSystemP0<E> {

	protected PregnantPreggoMobSystemP1(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve, float angerProbability) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve, angerProbability);
	}
	
	public PregnantPreggoMobSystemP1(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve);
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getPregnancyData().getCraving() >= PregnancySystemHelper.MAX_CRAVING_LEVEL;
	}
}
