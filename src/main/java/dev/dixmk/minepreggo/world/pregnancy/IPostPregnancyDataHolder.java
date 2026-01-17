package dev.dixmk.minepreggo.world.pregnancy;

import net.minecraft.nbt.CompoundTag;

public interface IPostPregnancyDataHolder {

	PostPregnancy getPostPregnancy();
	    
	int getPostPregnancyTimer();
	void setPostPregnancyTimer(int ticks);
	void incrementPostPregnancyTimer();
	void resetPostPregnancyTimer();
	    
	int getPostPartumLactation();
	void setPostPartumLactation(int amount);
	void incrementPostPartumLactation();
	void incrementPostPartumLactation(int amount);
	void decrementPostPartumLactation();
	void decrementPostPartumLactation(int amount);
	
	int getPostPartumLactationTimer();
	void setPostPartumLactationTimer(int ticks);
	void incrementPostPartumLactationTimer();
	void resetPostPartumLactationTimer();

	CompoundTag toNBT();
}
