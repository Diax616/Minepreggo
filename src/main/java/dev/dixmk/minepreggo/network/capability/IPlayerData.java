package dev.dixmk.minepreggo.network.capability;

public interface IPlayerData {
	void setGender(Gender gender);
	Gender getGender();
	
	boolean canGetPregnant();	
	boolean isUsingCustomSkin();	
	void setCustomSkin(boolean value);
	boolean isPregnant();	
	boolean canShowMainMenu();
	void setShowMainMenu(boolean value);
	
	void setPregnant(boolean value);
	void startPregnancy();
	
	float getFertilityPercentage();
	void setFertilityPercentage(float percentage);
	void incrementFertilityPercentage();
	void reduceFertilityPercentage();	
	void incrementFertilityTimer();
}