package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public abstract class PreggoMobPregnancySystemP6
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobPregnancySystemP5<E> {

	protected PreggoMobPregnancySystemP6(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP6();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP6();
		totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP6();
		totalTicksOfHorny = MinepreggoModConfig.SERVER.getTotalTicksOfHornyP6();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P6;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P6;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P6;
	}
}
