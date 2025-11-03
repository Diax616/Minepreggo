package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import net.minecraft.server.level.ServerLevel;

public abstract class PregnancySystemP7<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystem & IPregnancyP7> extends PregnancySystemP6<E> {

	protected PregnancySystemP7(@Nonnull E preggoMob) {
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
				PregnancySystemConstants.TOTAL_TICKS_PREBIRTH_P7,
				PregnancySystemConstants.TOTAL_TICKS_BIRTH_P7) == Result.SUCCESS) {
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
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP7());
		this.evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP7());
		this.evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP7());
		this.evaluateHornyTimer(MinepreggoModConfig.getTotalTicksOfHornyP7());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.HIGH_ANGER_PROBABILITY);
		
		this.evaluatePregnancySymptoms();
		this.evaluatePregnancyPains(
				PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY,
				PregnancySystemConstants.HIGH_PREGNANCY_PAIN_PROBABILITY,
				PregnancySystemConstants.TOTAL_TICKS_KICKING_P7,
				PregnancySystemConstants.TOTAL_TICKS_CONTRACTION_P7);
	}
}
