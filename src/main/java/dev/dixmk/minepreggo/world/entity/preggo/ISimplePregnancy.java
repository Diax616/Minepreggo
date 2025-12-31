package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IPregnant;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.util.RandomSource;

public interface ISimplePregnancy extends IPregnant {

	PregnancyPhase getCurrentPregnancyStage();
	
	PregnancyPhase getLastPregnancyStage();
	
	int getTotalDaysPassed();
	
	void setPregnancyPain(boolean value);
	
	int getPregnancyPainTimer();
	void setPregnancyPainTimer(int tick);
	
	static int getRandomTotalDaysPassed(PregnancyPhase currentPregnancyStage, PregnancyPhase maxPregnancyStage, RandomSource randomSource) {	
		int max = maxPregnancyStage.ordinal();
		int current = currentPregnancyStage.ordinal();
		
		if (max < PregnancyPhase.P4.ordinal()) {
			max = PregnancyPhase.P4.ordinal();
		} 
		
		if (current > max) {
			current = max;
		} else if (current == PregnancyPhase.P0.ordinal()) {
			current = PregnancyPhase.P1.ordinal();
		}
		
		final int daysByStage = PregnancySystemHelper.TOTAL_PREGNANCY_DAYS / max;	
		return daysByStage * (current - 1) + randomSource.nextInt(0, daysByStage);
	}
}
