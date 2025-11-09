package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;

public interface IPlayerData extends IBreedable {
	void setGender(Gender gender);
	Gender getGender();
	
	boolean isUsingCustomSkin();	
	void setCustomSkin(boolean value);

	boolean canShowMainMenu();
	void setShowMainMenu(boolean value);
	
	void setPregnant(boolean value);
}