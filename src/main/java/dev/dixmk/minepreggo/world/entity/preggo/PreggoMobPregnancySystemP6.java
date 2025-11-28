package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;

public abstract class PreggoMobPregnancySystemP6<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP5<E> {

	protected PreggoMobPregnancySystemP6(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	@Override
	protected void initPregnancySymptomsTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP6();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP6();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP6();
		totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP6();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P6;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P6;
	}
}
