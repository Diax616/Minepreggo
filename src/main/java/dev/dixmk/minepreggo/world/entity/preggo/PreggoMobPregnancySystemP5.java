package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public abstract class PreggoMobPregnancySystemP5
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobPregnancySystemP4<E> {

	protected PreggoMobPregnancySystemP5(@Nonnull E preggoMob) {
		super(preggoMob);
	}

	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP5();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP5();
		totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP5();
		totalTicksOfHorny = MinepreggoModConfig.SERVER.getTotalTicksOfHornyP5();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P5;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P5;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P5;
	}
}
