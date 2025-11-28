package dev.dixmk.minepreggo.network.capability;

import net.minecraft.util.RandomSource;

public enum Gender {
	FEMALE,
	MALE,
	UNKNOWN;
	
	public static final String NBT_KEY = "GenderType";
	
	public static Gender getRandomGender(RandomSource random) {
		return random.nextBoolean() ? FEMALE : MALE;
	}
}
