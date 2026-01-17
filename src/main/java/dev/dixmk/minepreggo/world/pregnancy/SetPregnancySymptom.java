package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Set;

import javax.annotation.CheckForNull;

import net.minecraft.nbt.CompoundTag;

public class SetPregnancySymptom {
	protected byte bitmask = 0;

	public Set<PregnancySymptom> toSet() {
		return PregnancySymptom.fromBitMask(bitmask);
	}

	public boolean addPregnancySymptom(PregnancySymptom symptom) {	
		if ((bitmask & symptom.flag) == 0) {
			bitmask |= symptom.flag;
			return true;
		}
		
		return false;
	}

	public void setPregnancySymptoms(Set<PregnancySymptom> symptoms) {
		bitmask = PregnancySymptom.toBitMask(symptoms);
	}

	public void setBitMask(byte bitmask) {
		this.bitmask = bitmask;
	}
	
	public boolean removePregnancySymptom(PregnancySymptom symptom) {	
		if ((bitmask & symptom.flag) != 0) {
			bitmask &= ~symptom.flag;
			return true;
		}
		return false;
	}
	
	public boolean containsPregnancySymptom(PregnancySymptom symptom) {
		return (bitmask & symptom.flag) != 0;
	}
	
	public boolean containsAllPregnancySymptoms(Set<PregnancySymptom> symptoms) {
		for (var symptom : symptoms) {
			if (!containsPregnancySymptom(symptom)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isEmpty() {
		return bitmask == 0;
	}
	
	public void clearPregnancySymptoms() {
		bitmask = 0;
	}

	public byte getBitmask() {
		return bitmask;
	}
	
    public CompoundTag toNBT() {
    	CompoundTag nbt = new CompoundTag();
		nbt.putByte(PregnancySymptom.NBT_KEY, this.bitmask);
		return nbt;
    }
    
    @CheckForNull
    public static SetPregnancySymptom fromNBT(CompoundTag nbt) {
    	if (nbt.contains(PregnancySymptom.NBT_KEY)) {
			SetPregnancySymptom symptomSet = new SetPregnancySymptom();
			symptomSet.bitmask = nbt.getByte(PregnancySymptom.NBT_KEY);
			return symptomSet;
		}
		return null;	
    }
}
