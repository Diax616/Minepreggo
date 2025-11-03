package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.world.item.Item;

public interface IPregnancyP1 {
	Craving getTypeOfCraving();
	boolean isValidCraving(Craving craving, Item item);
	void setTypeOfCraving(Craving craving);
	
	int getCraving();
	void setCraving(int craving);
	
	int getCravingTimer();
	void setCravingTimer(int timer);
}
