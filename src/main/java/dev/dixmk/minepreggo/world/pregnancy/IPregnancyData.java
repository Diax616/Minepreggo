package dev.dixmk.minepreggo.world.pregnancy;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.Item;

public interface IPregnancyData extends IPregnant {

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
	
	SetPregnancySymptom getPregnancySymptoms();
	void setPregnancySymptoms(SetPregnancySymptom symptoms);
	
	@Nullable PregnancyPain getPregnancyPain();
	void setPregnancyPain(@Nullable PregnancyPain pain);
	void clearPregnancyPain();
	 
	Womb getWomb();
	void setWomb(@NonNull Womb womb);
	
	@Nullable Craving getTypeOfCraving();	
	void setTypeOfCraving(@Nullable Craving craving);
	boolean isValidCraving(Item itemCraving);
	void clearTypeOfCraving();
		
	int getCraving();
	void setCraving(@NonNegative int craving);
	void incrementCraving();
	void decrementCraving(@NonNegative int amount);
	
	int getCravingTimer();
	void setCravingTimer(@NonNegative int timer);
	void incrementCravingTimer();
	void resetCravingTimer();
	
	public int getMilking();	
	public void setMilking(@NonNegative int milking);
	void incrementMilking();
	void decrementMilking(int amount);
		
	public int getMilkingTimer();	
	public void setMilkingTimer(@NonNegative int timer);
	public void incrementMilkingTimer();
	public void resetMilkingTimer();
	
    int getBellyRubs();
    void setBellyRubs(@NonNegative int bellyRubs);
    void incrementBellyRubs();
    void decrementBellyRubs(int amount);
    
    int getBellyRubsTimer();
    void setBellyRubsTimer(@NonNegative int timer);
    void incrementBellyRubsTimer();
    void resetBellyRubsTimer();
    
	int getHorny();
	void setHorny(@NonNegative int horny);
	void incrementHorny();
	
	int getHornyTimer();
	void setHornyTimer(@NonNegative int timer);
	void incrementHornyTimer();
	void resetHornyTimer();
}
