package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import net.minecraft.server.level.ServerLevel;

public abstract class PregnancySystemP7<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PregnancySystemP6<E> {

	protected PregnancySystemP7(@Nonnull E preggoMob) {
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
				evaluateBirth(serverLevel, PregnancySystemConstants.TOTAL_TICKS_PREBIRTH_P7, PregnancySystemConstants.TOTAL_TICKS_BIRTH_P7);
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

		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP7());
		evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP7());
		evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP7());
		evaluateHornyTimer(MinepreggoModConfig.getTotalTicksOfHornyP7());
		
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
			
		evaluateAngry(level, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemConstants.HIGH_ANGER_PROBABILITY);		
	}
}
