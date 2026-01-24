package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;

public interface ITamablePregnantPreggoMob extends ITamablePreggoMob<IFemaleEntity> {

	ITamablePregnantPreggoMobData getPregnancyData();
	
}
