package dev.dixmk.minepreggo.network.capability;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;

public interface IPregnancySystemHandler {

	int getDaysByStage();
	void setDaysByStage(@NonNegative int days);
	
	int getPregnancyHealth();
	void setPregnancyHealth(@NonNegative int health);
	void reducePregnancyHealth(int amount);
	void resetPregnancyHealth();
	
	int getDaysPassed();
	void setDaysPassed(@NonNegative int days);
	void incrementDaysPassed();
	void resetDaysPassed();
	
	int getDaysToGiveBirth();
	void setDaysToGiveBirth(@NonNegative int days);
	void reduceDaysToGiveBirth();
	
    int getPregnancyTimer();
    void setPregnancyTimer(@NonNegative int ticks);
    void incrementPregnancyTimer();
	void resetPregnancyTimer();
    
    int getPregnancyPainTimer();
    void setPregnancyPainTimer(@NonNegative int ticks);
    void incrementPregnancyPainTimer();
    void resetPregnancyPainTimer();
    
	PregnancyStage getLastPregnancyStage();
	void setLastPregnancyStage(PregnancyStage stage);

	PregnancyStage getCurrentPregnancyStage();
	void setCurrentPregnancyStage(PregnancyStage stage);
    
	@Nullable
	PregnancySymptom getPregnancySymptom();
	void setPregnancySymptom(@Nullable PregnancySymptom symptom);
	void clearPregnancySymptom();
	
	@Nullable
	PregnancyPain getPregnancyPain();
	void setPregnancyPain(@Nullable PregnancyPain pain);
	void clearPregnancyPain();
	 
	Baby getDefaultTypeOfBaby();
	int getTotalNumOfBabies();
	
	boolean isIncapacitated();
	
    public static int calculateDaysToGiveBirth(@NonNull IPregnancySystemHandler h) {
        PregnancyStage currentStage = h.getCurrentPregnancyStage();
        PregnancyStage lastStage = h.getLastPregnancyStage();
        
        int stagesRemaining = Math.max(1, lastStage.ordinal() - currentStage.ordinal() + 1);

        // Base total days for remaining stages
        int baseTotalDays = h.getDaysByStage() * stagesRemaining;

        // Remaining days before modifiers
        return baseTotalDays - h.getDaysPassed();
    }

    public static int calculateTotalDaysPassedFromP1(@NonNull IPregnancySystemHandler h) {
        // Number of full stages completed since P1 (P1 -> 0, P2 -> 1, ...)
        int stagesSinceP1 = Math.max(0, h.getCurrentPregnancyStage().ordinal() - PregnancyStage.P1.ordinal());

        return h.getDaysByStage() * stagesSinceP1 + h.getDaysPassed();
    }
}