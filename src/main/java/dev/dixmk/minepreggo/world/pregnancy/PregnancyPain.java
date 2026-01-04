package dev.dixmk.minepreggo.world.pregnancy;

public enum PregnancyPain {
	MORNING_SICKNESS(true),
	FETAL_MOVEMENT(false),
	CONTRACTION(true),
	MISCARRIAGE(true),
	WATER_BREAKING(false),
	BIRTH(true),
	PREBIRTH(true);
	
	public final boolean incapacitate;
	
	PregnancyPain(boolean incapacitate) {
		this.incapacitate = incapacitate;
	}
	
	public static final String NBT_KEY = "PregnancyPainType";
	
	public static boolean isLaborPain(PregnancyPain pain) {
		return pain == WATER_BREAKING || pain == BIRTH || pain == PREBIRTH || pain == MISCARRIAGE;
	}
}
