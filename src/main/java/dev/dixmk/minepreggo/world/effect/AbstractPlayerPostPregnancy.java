package dev.dixmk.minepreggo.world.effect;

import java.util.UUID;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public abstract class AbstractPlayerPostPregnancy extends MobEffect {
	protected static final UUID SPEED_MODIFIER_UUID = UUID.fromString("a0bf6ac9-4354-4977-86fc-5dea9108665d");
	protected static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("57a3938d-b55a-47b5-93ee-737724ba9d2e");

	
	protected AbstractPlayerPostPregnancy(int color) {
		super(MobEffectCategory.NEUTRAL, color);
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
