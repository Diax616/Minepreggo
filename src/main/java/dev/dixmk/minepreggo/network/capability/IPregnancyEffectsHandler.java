package dev.dixmk.minepreggo.network.capability;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import net.minecraft.world.item.Item;

public interface IPregnancyEffectsHandler {
	Craving getTypeOfCraving();
	void setTypeOfCraving(Craving craving);
	boolean isValidCraving(Craving craving, Item item);
	
	int getCraving();
	void setCraving(int craving);
	void incrementCraving();
	
	int getCravingTimer();
	void setCravingTimer(@NonNegative int timer);
	void incrementCravingTimer();
	
	public int getMilking();	
	public void setMilking(@NonNegative int milking);
	void incrementMilking();
	
	public int getMilkingTimer();	
	public void setMilkingTimer(@NonNegative int timer);
	public void incrementMilkingTimer();
	
    int getBellyRubs();
    void setBellyRubs(@NonNegative int bellyRubs);
    void incrementBellyRubs();
    
    int getBellyRubsTimer();
    void setBellyRubsTimer(@NonNegative int timer);
    void incrementBellyRubsTimer();
    
	int getHorny();
	void setHorny(@NonNegative int horny);
	void incrementHorny();
	
	int getHornyTimer();
	void setHornyTimer(@NonNegative int timer);
	void incrementHornyTimer();
}
