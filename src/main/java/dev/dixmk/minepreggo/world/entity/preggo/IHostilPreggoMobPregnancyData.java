package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.pregnancy.IPregnant;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IHostilPreggoMobPregnancyData extends IPregnant, INBTSerializable<CompoundTag> {
	PregnancyPhase getCurrentPregnancyPhase();	
	void setCurrentPregnancyPhase(PregnancyPhase phase);
	PregnancyPhase getLastPregnancyPhase();
	int getTotalDaysElapsed();
	int getIncapacitatedCooldown();
	void setPregnancyPain(boolean value);
	int getPregnancyPainTimer();
	void setPregnancyPainTimer(int tick);
	float getPregnancyPainProbability();
	int getNumOfBabies();
	void incrementNumOfBabies(@Nonnegative int count);
	void init();
}
