package dev.dixmk.minepreggo.network.capability;

import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import net.minecraft.world.item.Item;

public interface IPregnancyEffectsHandler {
	
	@Nullable
	Craving getTypeOfCraving();	
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
