package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class QuadrupedCreeperImpregnation extends Impregnantion {

	public QuadrupedCreeperImpregnation() {
		super(-53464133);
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
