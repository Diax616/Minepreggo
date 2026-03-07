package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Set;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.LivingEntity;

public class SyncedPregnancySymptomSet extends PregnancySymptomSet {
    private final LivingEntity entity;
    private final EntityDataAccessor<Byte> dataAccessor;

    public SyncedPregnancySymptomSet(LivingEntity entity, EntityDataAccessor<Byte> dataAccessor) {
        super();
        this.entity = entity;
        this.dataAccessor = dataAccessor;
    }

    @Override
    public boolean add(PregnancySymptom symptom) {
        boolean result = super.add(symptom);
        if (result) sync();
        return result;
    }

    @Override
    public boolean remove(Object symptom) {
        boolean result = super.remove(symptom);
        if (result) sync();
        return result;
    }

    @Override
    public void clear() {
        super.clear();
        sync();
    }

    @Override
    public void from(byte bitmask) {
        super.from(bitmask);
        sync();
    }

    @Override
    public void from(Set<PregnancySymptom> symptoms) {
        super.from(symptoms);
        sync();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
        sync();
    }
    
    public byte getSyncedSymptoms() {
		return this.entity.getEntityData().get(dataAccessor);
	}
    
    private void sync() {
        entity.getEntityData().set(dataAccessor, PregnancySymptom.toBitMask(this.getSymptoms()));
    }
}
