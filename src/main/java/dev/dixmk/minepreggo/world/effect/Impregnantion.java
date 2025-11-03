package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;


public class Impregnantion extends MobEffect {
	protected Impregnantion(MobEffectCategory neutral, int p_19452_) {
		super(MobEffectCategory.NEUTRAL, p_19452_);
	}
	
	public Impregnantion(int p_19452_) {
		super(MobEffectCategory.NEUTRAL, p_19452_);
	}
	
	public Impregnantion() {
		this(MobEffectCategory.NEUTRAL, -10092442);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		// PreggoMobHelper.startPregnancy(entity, amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}

