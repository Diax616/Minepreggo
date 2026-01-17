package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.SetPregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.SyncedSetPregnancySymptom;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ITamablePregnantPreggoMobData extends IPregnancyData, INBTSerializable<CompoundTag> {
	
    SyncedSetPregnancySymptom getSyncedPregnancySymptoms();
    
    @Override
    default SetPregnancySymptom getPregnancySymptoms() {
        return getSyncedPregnancySymptoms();
    }
    
    @Override
    default void setPregnancySymptoms(SetPregnancySymptom symptoms) {
        getSyncedPregnancySymptoms().setPregnancySymptoms(symptoms.toSet());
    }
}
