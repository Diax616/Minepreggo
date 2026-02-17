package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraftforge.common.util.LazyOptional;

public interface IPlayerData {
	boolean canShowMainMenu();
	void setShowMainMenu(boolean value);
	
	void setGender(Gender gender);
	Gender getGender();
	
	boolean isFemale();
	boolean isMale();
	
	boolean isCinamatic();
	void setCinematic(boolean value);
	
	SkinType getSkinType();
	void setSKinType(SkinType skinType);
	
	LazyOptional<MalePlayerImpl> getMaleData();	
	LazyOptional<FemalePlayerImpl> getFemaleData();
	
	IPlayerStatistic getPlayerStatistic();
}