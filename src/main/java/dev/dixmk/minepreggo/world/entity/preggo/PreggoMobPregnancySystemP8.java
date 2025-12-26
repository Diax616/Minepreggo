package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public abstract class PreggoMobPregnancySystemP8<E extends PreggoMob
	& ITamablePreggoMob<FemaleEntityImpl> & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP7<E> {

	protected PreggoMobPregnancySystemP8(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP8();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP8();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP8();
		totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP8();
		totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P8;
		totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P8;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P8;
	}
}
