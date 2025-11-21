package dev.dixmk.minepreggo.network.capability;

import java.util.Map;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;

public interface IPregnancySystemHandler {

	int getDaysByStage();
	boolean setDaysByStage(@Nonnegative int days, PregnancyPhase phase);
	boolean setDaysByStage(Map<PregnancyPhase, Integer> map);
	Map<PregnancyPhase, Integer> getDaysByStageMapping();
	
	
	
	int getPregnancyHealth();
	void setPregnancyHealth(@Nonnegative int health);
	void reducePregnancyHealth(int amount);
	void resetPregnancyHealth();
	
	int getDaysPassed();
	void setDaysPassed(@Nonnegative int days);
	void incrementDaysPassed();
	void resetDaysPassed();
	
	int getDaysToGiveBirth();
	void setDaysToGiveBirth(@Nonnegative int days);
	void reduceDaysToGiveBirth();
	
    int getPregnancyTimer();
    void setPregnancyTimer(@Nonnegative int ticks);
    void incrementPregnancyTimer();
	void resetPregnancyTimer();
    
    int getPregnancyPainTimer();
    void setPregnancyPainTimer(@Nonnegative int ticks);
    void incrementPregnancyPainTimer();
    void resetPregnancyPainTimer();
    
	PregnancyPhase getLastPregnancyStage();
	void setLastPregnancyStage(PregnancyPhase stage);

	PregnancyPhase getCurrentPregnancyStage();
	void setCurrentPregnancyStage(PregnancyPhase stage);
    
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
        PregnancyPhase currentStage = h.getCurrentPregnancyStage();
        PregnancyPhase lastStage = h.getLastPregnancyStage();
        
        int stagesRemaining = Math.max(1, lastStage.ordinal() - currentStage.ordinal() + 1);

        // Base total days for remaining stages
        int baseTotalDays = h.getDaysByStage() * stagesRemaining;

        // Remaining days before modifiers
        return baseTotalDays - h.getDaysPassed();
    }
    
    public static int calculateDaysByStage(PregnancyPhase lastStage) {
    	return MinepreggoModConfig.getTotalPregnancyDays() / lastStage.ordinal();
    }

    public static int calculateInitialDaysToGiveBirth(PregnancyPhase lastStage) {
    	return calculateDaysByStage(lastStage) * lastStage.ordinal();
    }
    
    public static int calculateTotalDaysPassedFromP1(@NonNull IPregnancySystemHandler h) {
        // Number of full stages completed since P1 (P1 -> 0, P2 -> 1, ...)
        int stagesSinceP1 = Math.max(0, h.getCurrentPregnancyStage().ordinal() - PregnancyPhase.P1.ordinal());

        return h.getDaysByStage() * stagesSinceP1 + h.getDaysPassed();
    }
}

