package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Set;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.network.syncher.EntityDataAccessor;

public class SyncedSetPregnancySymptom extends SetPregnancySymptom {
	
	private final EntityDataAccessor<Byte> dataAccessor;
	private final PreggoMob preggoMob;
	
	public SyncedSetPregnancySymptom(EntityDataAccessor<Byte> dataAccessor, PreggoMob entity) {
		super();
		this.dataAccessor = dataAccessor;
		this.preggoMob = entity;
	}
	
	@Override
	public boolean addPregnancySymptom(PregnancySymptom symptom) {	
		boolean result = super.addPregnancySymptom(symptom);
		if (result) {
			preggoMob.getEntityData().set(dataAccessor, bitmask);
		}
		return result;
	}
	
	@Override
	public void setPregnancySymptoms(Set<PregnancySymptom> symptoms) {
		super.setPregnancySymptoms(symptoms);
		preggoMob.getEntityData().set(dataAccessor, bitmask);
	}
	
	@Override
	public void setBitMask(byte bitmask) {
		super.setBitMask(bitmask);
		preggoMob.getEntityData().set(dataAccessor, bitmask);
	}
	
	@Override
	public boolean removePregnancySymptom(PregnancySymptom symptom) {	
		boolean result = super.removePregnancySymptom(symptom);
		if (result) {
			preggoMob.getEntityData().set(dataAccessor, bitmask);
		}
		return result;
	}
	
	@Override
	public boolean isEmpty() {
		return preggoMob.getEntityData().get(dataAccessor) == 0;
	}
	
	@Override
	public byte getBitmask() {
		return preggoMob.getEntityData().get(dataAccessor);
	}
	
	@Override
	public void clearPregnancySymptoms() {
		super.clearPregnancySymptoms();
		preggoMob.getEntityData().set(dataAccessor, (byte) 0);
	}
	
	@Override
	public boolean containsPregnancySymptom(PregnancySymptom symptom) {
		return (preggoMob.getEntityData().get(dataAccessor) & symptom.flag) != 0;
	}
	
	@Override
	public Set<PregnancySymptom> toSet() {
		return PregnancySymptom.fromBitMask(preggoMob.getEntityData().get(dataAccessor));
	}
}
