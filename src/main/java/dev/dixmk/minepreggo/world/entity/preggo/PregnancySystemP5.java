package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import net.minecraft.server.level.ServerLevel;

public abstract class PregnancySystemP5<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PregnancySystemP4<E> {

	protected PregnancySystemP5(@Nonnull E preggoMob) {
		super(preggoMob);
	}

	@Override
	public void onServerTick() {
		final var level = preggoMob.level();	
		if (level.isClientSide()) {			
			return;
		}
		
		if (isInLabor()) {		
			if (level instanceof ServerLevel serverLevel) {
				evaluateBirth(serverLevel, PregnancySystemConstants.TOTAL_TICKS_PREBIRTH_P5, PregnancySystemConstants.TOTAL_TICKS_BIRTH_P5);
			}		
			return;
		}
		
		if (isMiscarriageActive()) {
			if (level instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE);
			}		
			return;
		}
		
		evaluatePregnancyTimer();
		if (canAdvanceNextPregnancyPhase()) {			
			if (hasToGiveBirth()) {
				startLabor();
			}
			else {
				advanceToNextPregnancyPhase();
				preggoMob.discard();
			}
			return;
		}

		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP5());
		evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP5());
		evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP5());
		evaluateHornyTimer(MinepreggoModConfig.getTotalTicksOfHornyP5());
		
		if (!hasPregnancyPain()) {
			tryInitRandomPregnancyPain();	
		}
		else {
			evaluatePregnancyPains();
		}
		
		if (!hasPregnancySymptom()) {
			tryInitPregnancySymptom();
		}
		else {
			evaluatePregnancySymptoms();
		}
			
		evaluateAngry(level, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemConstants.MEDIUM_ANGER_PROBABILITY);		
	}
}
