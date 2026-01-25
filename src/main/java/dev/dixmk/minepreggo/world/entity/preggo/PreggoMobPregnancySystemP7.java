package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public abstract class PreggoMobPregnancySystemP7
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobPregnancySystemP6<E> {

	protected PreggoMobPregnancySystemP7(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP7();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP7();
		totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP7();
		totalTicksOfHorny = MinepreggoModConfig.SERVER.getTotalTicksOfHornyP7();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P7;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P7;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P7;
	}
}
