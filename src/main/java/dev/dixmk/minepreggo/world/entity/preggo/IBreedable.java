package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.network.capability.Gender;
import net.minecraft.util.Mth;

public interface IBreedable {

	public static final int MAX_NUMBER_OF_BABIES = 5;
	public static final float MAX_FERTILITY_RATE = 1.0f;
	public static final int MAX_SEXUAL_APPETIVE = 20;
	
    int getFertilityRateTimer();
    void setFertilityRateTimer(int ticks);
    void incrementFertilityRateTimer();
    void resetFertilityRateTimer();
    
    float getFertilityRate();
    void setFertilityRate(float rate);
    void incrementFertilityRate(float rate);
    void resetFertilityRate();
  
    int getSexualAppetite();
    void setSexualAppetite(@Nonnegative int sexualAppetite);
    void reduceSexualAppetite(@Nonnegative int amount);
    void incrementSexualAppetite(@Nonnegative int amount);
    
    int getSexualAppetiteTimer();
    void setSexualAppetiteTimer(int timer);
    void incrementSexualAppetiteTimer();
    
	Gender getGender();
    
    boolean canFuck();
	
    public static int calculateNumOfBabiesByPotion(@Nonnegative int amplifier) {
    	switch (Math.abs(amplifier)) {
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 3;
		case 3:
			return 4;
		default:
			return MAX_NUMBER_OF_BABIES;		
    	}
    }
    
    public static int calculateNumOfBabiesByFertility(@Nonnegative float maleFertility, @Nonnegative float femaleFertility) {
    	float averageFertility = (maleFertility + femaleFertility) / 2.0f;
    	int numOfBabies = Math.round(averageFertility / MAX_FERTILITY_RATE * MAX_NUMBER_OF_BABIES);
    	if (numOfBabies < 1) {
			return 0;
		}
		else if (numOfBabies > MAX_NUMBER_OF_BABIES) {
			numOfBabies = MAX_NUMBER_OF_BABIES;
		}
    	return numOfBabies;
    }
    
    public static PregnancyPhase calculateMaxPregnancyPhaseByTotalNumOfBabies(@Nonnegative int numOfBabies) {	
    	switch (Mth.clamp(numOfBabies, 1, MAX_NUMBER_OF_BABIES)) {
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
    
    public static int calculateNumOfBabiesByMaxPregnancyStage(@NonNull PregnancyPhase lastPregnancyPhase) {	
    	switch (lastPregnancyPhase) {
    		case P4:
    			return 1;
    		case P5:
    			return 2;
    		case P6:
    			return 3;
    		case P7:
    			return 4;
    		case P8:
    			return MAX_NUMBER_OF_BABIES;
    		default:
    			return 1;		
    	}
	}
}
