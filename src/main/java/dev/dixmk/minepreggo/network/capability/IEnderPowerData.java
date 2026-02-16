package dev.dixmk.minepreggo.network.capability;

import javax.annotation.Nonnegative;

public interface IEnderPowerData {
	
	static final int MAX_ENDER_POWER_LEVEL = 20;
	
	int getEnderPowerTimer();
	void setEnderPowerTimer(int timer);
	void incrementEnderPowerTimer();
	void resetEnderPowerTimer();
	
	int getEnderPowerLevel();
	void setEnderPowerLevel(int level);	
	void incrementEnderPowerLevel(int amount);
	void decrementEnderPowerLevel(@Nonnegative int amount);
	
	void reset();
	
	enum EnderPower {
		TELEPORT(2),
		FIREBALL(4);
		
		public final int baseLevelCost;
		
		EnderPower(int levelCost) {
			this.baseLevelCost = levelCost;
		}	
	}
}
