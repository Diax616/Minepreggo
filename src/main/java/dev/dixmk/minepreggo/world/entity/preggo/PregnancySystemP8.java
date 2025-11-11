package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import net.minecraft.server.level.ServerLevel;

public abstract class PregnancySystemP8<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PregnancySystemP7<E> {

	protected PregnancySystemP8(@Nonnull E preggoMob) {
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
				evaluateBirth(serverLevel, PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P8, PregnancySystemHelper.TOTAL_TICKS_BIRTH_P8);
			}		
			return;
		}
		
		if (isMiscarriageActive()) {
			if (level instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE);
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

		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP8());
		evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP8());
		evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP8());
		evaluateHornyTimer(MinepreggoModConfig.getTotalTicksOfHornyP8());
		
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
			
		evaluateAngry(level, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemHelper.HIGH_ANGER_PROBABILITY);		
	}
}
