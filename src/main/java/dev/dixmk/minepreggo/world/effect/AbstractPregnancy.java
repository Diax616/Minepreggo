package dev.dixmk.minepreggo.world.effect;

import java.util.UUID;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP1;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public abstract class AbstractPregnancy<S extends PlayerPregnancySystemP1> extends MobEffect {
	protected static final UUID SPEED_MODIFIER_UUID = UUID.fromString("a0bf6ac9-4354-4977-86fc-5dea9108665d");
	protected static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("57a3938d-b55a-47b5-93ee-737724ba9d2e");
	
	protected @Nullable S pregnancySystem = null;
	
	protected AbstractPregnancy() {
		super(MobEffectCategory.NEUTRAL, -6750055);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
