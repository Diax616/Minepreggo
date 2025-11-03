package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class CreeperImpregnation extends Impregnantion {

	public CreeperImpregnation() {
		super(-13369549);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		// PreggoMobHelper.startPregnancy(entity, amplifier, BabyType.HUMANOID_CREEPER);
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
