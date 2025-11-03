package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.util.RandomSource;

public interface ISimplePregnancy {

	PregnancyStage getCurrentPregnancyStage();
	
	PregnancyStage getLastPregnancyStage();
	
	int getTotalDaysPassed();
	
	boolean hasPregnancyPain();
	void setPregnancyPain(boolean value);
	
	int getPregnancyPainTimer();
	void setPregnancyPainTimer(int tick);
	
	static int getRandomTotalDaysPassed(PregnancyStage currentPregnancyStage, PregnancyStage maxPregnancyStage, RandomSource randomSource) {	
		int max = maxPregnancyStage.ordinal();
		int current = currentPregnancyStage.ordinal();
		
		if (max < 4) {
			max = 4;
		} 
		
		if (current > max) {
			current = max;
		} else if (current == 0) {
			current = 1;
		}
		
		final int daysByStage = PregnancySystemConstants.TOTAL_PREGNANCY_DAYS / max;	
		return daysByStage * (current - 1) + randomSource.nextInt(0, daysByStage);
	}
}
