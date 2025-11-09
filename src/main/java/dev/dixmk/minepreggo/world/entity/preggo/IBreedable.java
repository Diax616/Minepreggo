package dev.dixmk.minepreggo.world.entity.preggo;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModConfig;

public interface IBreedable {
	public static final int NATURAL_PREGNANCY_INITIALIZER_TIME = 10000;
	public static final int MAX_NUMBER_OF_OFFSPRING = 5;
	public static final float MAX_FERTILITY_RATE = 1.0f;

    int getPregnancyInitializerTimer();
    void setPregnancyInitializerTimer(int ticks);
    void incrementPregnancyInitializerTimer();
    
    boolean isPregnant();
    boolean canGetPregnant();
    
    int getFertilityRateTimer();
    void setFertilityRateTimer(int ticks);
    void incrementFertilityRateTimer();
    
    float getFertilityRate();
    void setFertilityRate(float rate);
    void incrementFertilityRate(float rate);
   
    void impregnate();
    
    @Nullable
    PostPregnancy getPostPregnancyPhase();
    
    boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy);
    
    
    @NonNegative
	public static int calculateDaysByStage(PregnancyStage lastPregnancyStage) {
		return MinepreggoModConfig.getTotalPregnancyDays() / lastPregnancyStage.ordinal();
	}
    
    
    public static int calculateNumOfOffspringByPotion(@NonNegative int amplifier) {
    	switch (amplifier) {
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 3;
		case 3:
			return 4;
		default:
			return MAX_NUMBER_OF_OFFSPRING;		
    	}
    }
    
    public static int calculateNumOfOffspringByFertility(float maleFertility, float femaleFertility) {
    	float averageFertility = (maleFertility + femaleFertility) / 2.0f;
    	int numOfOffspring = Math.round(averageFertility / MAX_FERTILITY_RATE * MAX_NUMBER_OF_OFFSPRING);
    	if (numOfOffspring < 1) {
			numOfOffspring = 1;
		}
		else if (numOfOffspring > MAX_NUMBER_OF_OFFSPRING) {
			numOfOffspring = MAX_NUMBER_OF_OFFSPRING;
		}
    	return numOfOffspring;
    }
    
    public static PregnancyStage getMaxPregnancyStageByOffsprings(@NonNegative int numOfOffsprings) {	
    	switch (numOfOffsprings) {
    		case 1:
    			return PregnancyStage.P4;
    		case 2:
    			return PregnancyStage.P5;
    		case 3:
    			return PregnancyStage.P6;
    		case 4:
    			return PregnancyStage.P7;
    		default:
    			return PregnancyStage.P8;		
    	}
	}
    
    public static int getOffspringsByMaxPregnancyStage(@NonNull PregnancyStage currenPregnancyStage) {	
    	switch (currenPregnancyStage) {
    		case P4:
    			return 1;
    		case P5:
    			return 2;
    		case P6:
    			return 3;
    		case P7:
    			return 4;
    		case P8:
    			return MAX_NUMBER_OF_OFFSPRING;
    		default:
    			return 1;		
    	}
	}
}
