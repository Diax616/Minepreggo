package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Random;

public enum PregnancyPhase {
	P0,
    P1,
    P2,
    P3,
    P4,
    P5,
    P6,
    P7,
	P8;
	
	public static final String CURRENT_PHASE_NBT_KEY = "CurrentPregnancyPhase";
	public static final String LAST_PHASE_NBT_KEY = "LastPregnancyPhase";

	

	protected static final Random RANDOM =  new Random();
    
	public static PregnancyPhase getNonPregnancyStage() {		
		return PregnancyPhase.P0;
	}
	
	public static PregnancyPhase getRandomStageFrom(PregnancyPhase currentStage) {		
		int c = currentStage.ordinal();
		if (c < 4) {
			c = 4;
		}	
		return PregnancyPhase.values()[RANDOM.nextInt(c, PregnancyPhase.values().length)];	
    }
	
	public static PregnancyPhase calculateMaxPregnancyStage(PregnancyPhase currentPregnancyStage) {
		if (currentPregnancyStage.ordinal() > PregnancyPhase.P4.ordinal()) {
			return PregnancyPhase.values()[Math.min(currentPregnancyStage.ordinal() + 1, PregnancyPhase.values().length - 1)];
		}	
		return PregnancyPhase.P4;
	}
}