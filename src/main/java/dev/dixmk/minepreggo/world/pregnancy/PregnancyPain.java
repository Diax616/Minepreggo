package dev.dixmk.minepreggo.world.pregnancy;

import java.util.EnumSet;
import java.util.Set;

public enum PregnancyPain {
	MORNING_SICKNESS(true, Type.COMMON, (byte) (1 << 0)),
	FETAL_MOVEMENT(false, Type.COMMON, (byte) (1 << 1)),
	CONTRACTION(true, Type.COMMON, (byte) (1 << 2)),
	MISCARRIAGE(true, Type.MISBIRTH, (byte) (1 << 3)),
	WATER_BREAKING(false, Type.LABOR, (byte) (1 << 4)),
	BIRTH(true, Type.LABOR, (byte) (1 << 5)),
	PREBIRTH(true, Type.LABOR, (byte) (1 << 6));
	
	public final boolean incapacitate;
	public final Type type;
	public final byte flag;
	
	PregnancyPain(boolean incapacitate, Type type, byte flag) {
		this.incapacitate = incapacitate;
		this.type = type;
		this.flag = flag;
	}
	
	public static final String NBT_KEY = "PregnancyPainType";
		
	public enum Type {
		LABOR(false),
		MISBIRTH(false),
		COMMON(true);
		
		public final boolean duplicatable;
		
		Type(boolean duplicatable) {
			this.duplicatable = duplicatable;
		}
	}
	
	public static Set<PregnancyPain> fromBitMask(byte mask) {
	    Set<PregnancyPain> set = EnumSet.noneOf(PregnancyPain.class);
	    for (final var pregnancyPain : PregnancyPain.values()) {
	        if ((mask & pregnancyPain.flag) != 0) {
	            set.add(pregnancyPain);
	        }
	    }
	    return set;
	}
	
	public static byte toBitMask(Set<PregnancyPain> pregnancyPains) {
	    byte mask = 0;
	    for (final var p : pregnancyPains) {
	        mask |= p.flag;
	    }
	    return mask;
	}
}
