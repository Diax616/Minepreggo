package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.Gender;

public interface IBreedable {

	public static final int MAX_NUMBER_OF_OFFSPRING = 5;
	public static final float MAX_FERTILITY_RATE = 1.0f;
	public static final int MAX_SEXUAL_APPETIVE = 20;
	
	
    int getFertilityRateTimer();
    void setFertilityRateTimer(int ticks);
    void incrementFertilityRateTimer();
    
    float getFertilityRate();
    void setFertilityRate(float rate);
    void incrementFertilityRate(float rate);
   
    int getSexualAppetite();
    void setSexualAppetite(@Nonnegative int sexualAppetite);
    void reduceSexualAppetite(@Nonnegative int amount);
    void incrementSexualAppetite(@Nonnegative int amount);
    
    int getSexualAppetiteTimer();
    void setSexualAppetiteTimer(int timer);
    void incrementSexualAppetiteTimer();
    
	Gender getGender();
    
    boolean canFuck();
	
    @NonNegative
	public static int calculateDaysByStage(PregnancyPhase lastPregnancyStage) {
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
			return 0;
		}
		else if (numOfOffspring > MAX_NUMBER_OF_OFFSPRING) {
			numOfOffspring = MAX_NUMBER_OF_OFFSPRING;
		}
    	return numOfOffspring;
    }
    
    public static PregnancyPhase getMaxPregnancyPhaseByOffsprings(@NonNegative int numOfOffsprings) {	
    	switch (numOfOffsprings) {
    		case 1:
    			return PregnancyPhase.P4;
    		case 2:
    			return PregnancyPhase.P5;
    		case 3:
    			return PregnancyPhase.P6;
    		case 4:
    			return PregnancyPhase.P7;
    		default:
    			return PregnancyPhase.P8;		
    	}
	}
    
    public static int getOffspringsByMaxPregnancyStage(@NonNull PregnancyPhase currenPregnancyStage) {	
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
