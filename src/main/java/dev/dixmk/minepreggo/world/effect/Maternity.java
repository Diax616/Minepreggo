package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Maternity extends AbstractPostPregnancy {
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "depression speed nerf", -0.1, AttributeModifier.Operation.MULTIPLY_BASE);

	public Maternity() {
		super(-879246);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!entity.level().isClientSide()) {
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);

			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) == null) {
			    speedAttr.addPermanentModifier(SPEED_MODIFIER);
			}		
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!entity.level().isClientSide) {
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) != null) {
				speedAttr.removeModifier(SPEED_MODIFIER);
			}	
		}
	}
}
