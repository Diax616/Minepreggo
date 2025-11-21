package dev.dixmk.minepreggo.network.capability;

import net.minecraftforge.common.util.LazyOptional;

public interface IPlayerData {
	boolean isUsingCustomSkin();	
	void setCustomSkin(boolean value);

	boolean canShowMainMenu();
	void setShowMainMenu(boolean value);
	
	void setGender(Gender gender);
	Gender getGender();
	
	boolean isFemale();
	boolean isMale();
	
	boolean isCinamatic();
	void setCinematic(boolean value);

	LazyOptional<MalePlayerImpl> getMaleData();	
	LazyOptional<FemalePlayerImpl> getFemaleData();
}