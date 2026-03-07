package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptomSet;
import dev.dixmk.minepreggo.world.pregnancy.SyncedPregnancySymptomSet;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ITamablePregnantPreggoMobData extends IPregnancyData, INBTSerializable<CompoundTag> {
    SyncedPregnancySymptomSet getSyncedPregnancySymptoms();
    
    @Override
    default PregnancySymptomSet getPregnancySymptoms() {
        return getSyncedPregnancySymptoms();
    }
	
    @Override
    default void setPregnancySymptoms(PregnancySymptomSet symptoms) {
        getSyncedPregnancySymptoms().from(symptoms);
    }
}
