package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Set;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

public interface IPregnancySystemHandler extends IPregnant {

	int getDaysByCurrentStage();
	boolean setDaysByStage(@Nonnegative int days, PregnancyPhase phase);
	
	void setMapPregnancyPhase(MapPregnancyPhase map);
	MapPregnancyPhase getMapPregnancyPhase();
	
	int getTotalDaysOfPregnancy();
	
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
	
	Set<PregnancySymptom> getPregnancySymptoms();
	boolean addPregnancySymptom(PregnancySymptom symptom);
	void setPregnancySymptoms(Set<PregnancySymptom> symptoms);
	boolean removePregnancySymptom(PregnancySymptom symptom);
	void clearPregnancySymptoms();
	
	
	@Nullable
	PregnancyPain getPregnancyPain();
	void setPregnancyPain(@Nullable PregnancyPain pain);
	void clearPregnancyPain();
	 
	Womb getWomb();
	void setWomb(@NonNull Womb babiesInsideWomb);
}

