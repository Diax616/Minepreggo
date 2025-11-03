package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Random;

public enum PregnancyStage {
	P0,
    P1,
    P2,
    P3,
    P4,
    P5,
    P6,
    P7,
	P8;

	protected static final Random RANDOM =  new Random();
    
	public static PregnancyStage getNonPregnancyStage() {		
		return PregnancyStage.P0;
	}
	
	public static PregnancyStage getRandomStageFrom(PregnancyStage currentStage) {		
		int c = currentStage.ordinal();
		if (c < 4) {
			c = 4;
		}	

		return PregnancyStage.values()[RANDOM.nextInt(c, PregnancyStage.values().length)];	
    }
}