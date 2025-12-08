package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.world.pregnancy.IMaleEntity;

public interface IMalePlayer extends IMaleEntity {
	
	public static final int MAX_FAP = 20;
	
	int getFapTimer();
	void setFapTimer(int ticks);
	void incrementFapTimer();
	void resetFapTimer();
	boolean canFap();
	int getFap();
	void setFap(int amount);
	void incrementFap(int amount);
	void decrementFap(int amount);	
}
