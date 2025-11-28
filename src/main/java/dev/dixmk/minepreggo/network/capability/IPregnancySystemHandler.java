package dev.dixmk.minepreggo.network.capability;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.Womb;

public interface IPregnancySystemHandler {

	int getDaysByCurrentStage();
	boolean setDaysByStage(@Nonnegative int days, PregnancyPhase phase);
	void setDaysByStage(Map<PregnancyPhase, Integer> map);
	Map<PregnancyPhase, Integer> getDaysByStageMapping();
	
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
	 
	Womb getBabiesInsideWomb();
	void setBabiesInsideWomb(@NonNull Womb babiesInsideWomb);
	
	boolean isIncapacitated();
}

