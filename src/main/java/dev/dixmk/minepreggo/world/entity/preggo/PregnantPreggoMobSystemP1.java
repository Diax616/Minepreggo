package dev.dixmk.minepreggo.world.entity.preggo;

public class PregnantPreggoMobSystemP1
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PregnantPreggoMobSystemP0<E> {

	public PregnantPreggoMobSystemP1(E preggoMob, int totalTicksOfHungry, int totalTicksOfSexualAppetitve) {
		super(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve);
	}
}
