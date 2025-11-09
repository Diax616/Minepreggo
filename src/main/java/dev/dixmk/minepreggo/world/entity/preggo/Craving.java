package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.util.RandomSource;

public enum Craving {
	SWEET,
	SOUR,
	SALTY,
	SPICY;
	
	public static final String NBT_KEY = "CravingType";
	
	public static Craving getRandomCraving(RandomSource randomSource) {		
		final var p = randomSource.nextFloat();	
		if (p < 0.05F) {
			return Craving.SWEET;
		}
		else if (p < 0.35F) {
			return Craving.SALTY;
		}
		else if (p < 0.65F) {
			return Craving.SPICY;
		}
		else {
			return Craving.SOUR;
		}
	}
}
