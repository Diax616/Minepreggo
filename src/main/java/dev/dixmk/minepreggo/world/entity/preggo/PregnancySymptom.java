package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.EnumSet;
import java.util.Set;

public enum PregnancySymptom {
	
	CRAVING(1 << 0),
	MILKING(1 << 1),
	BELLY_RUBS(1 << 2),
	HORNY(1 << 3);
	
    public final byte flag;

    PregnancySymptom(int flag) {
        this.flag = (byte) flag;
    }
	
	public static final String NBT_KEY = "PregnancySymptomType";
	
	public static Set<PregnancySymptom> fromBitMask(byte mask) {
	    Set<PregnancySymptom> set = EnumSet.noneOf(PregnancySymptom.class);
	    for (final var pregnancySymptom : PregnancySymptom.values()) {
	        if ((mask & pregnancySymptom.flag) != 0) {
	            set.add(pregnancySymptom);
	        }
	    }
	    return set;
	}
	
	public static byte toBitMask(Set<PregnancySymptom> pregnancySymptoms) {
	    byte mask = 0;
	    for (final var p : pregnancySymptoms) {
	        mask |= p.flag;
	    }
	    return mask;
	}
}
