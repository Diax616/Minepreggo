package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;

public abstract class PreggoMobPregnancySystemP7<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP6<E> {

	protected PreggoMobPregnancySystemP7(@Nonnull E preggoMob) {
		super(preggoMob);
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP7();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP7();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP7();
		totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP7();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P7;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P7;
	}
	
}
