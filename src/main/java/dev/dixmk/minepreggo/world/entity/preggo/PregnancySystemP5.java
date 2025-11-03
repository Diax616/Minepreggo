package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import net.minecraft.server.level.ServerLevel;

public abstract class PregnancySystemP5<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystem & IPregnancyP5> extends PregnancySystemP4<E> {

	protected PregnancySystemP5(@Nonnull E preggoMob) {
		super(preggoMob);
	}

	@Override
	public void evaluateOnTick() {
		
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {
			return;
		}
		
		final var x =  preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
		
		if (level instanceof ServerLevel serverLevel
				&& this.evaluteBirth(serverLevel, x, y, z,
				PregnancySystemConstants.TOTAL_TICKS_PREBIRTH_P5,
				PregnancySystemConstants.TOTAL_TICKS_BIRTH_P5) == Result.SUCCESS) {
			return;
		}
		
		if (this.evaluatePregnancyStageChange() == Result.SUCCESS) {
			return;
		}
		
		if (level instanceof ServerLevel serverLevel
				&& this.evaluateMiscarriage(serverLevel, x, y, z, PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) == Result.SUCCESS) {
			return; 
		}
		
		this.evaluatePregnancyTimer();
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP5());
		this.evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP5());
		this.evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP5());
		this.evaluateHornyTimer(MinepreggoModConfig.getTotalTicksOfHornyP5());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.HIGH_ANGER_PROBABILITY);
		
		this.evaluatePregnancySymptoms();
		this.evaluatePregnancyPains(
				PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY,
				PregnancySystemConstants.HIGH_PREGNANCY_PAIN_PROBABILITY,
				PregnancySystemConstants.TOTAL_TICKS_KICKING_P5,
				PregnancySystemConstants.TOTAL_TICKS_CONTRACTION_P5);
	}
}
