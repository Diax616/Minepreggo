package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Depression extends AbstractPlayerPostPregnancy {
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "depression speed nerf", -0.1, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "depression attack speed nerf", -0.3, AttributeModifier.Operation.MULTIPLY_BASE);

	public Depression() {
		super(-456154);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!entity.level().isClientSide) {
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);

			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) == null) {
			    speedAttr.addPermanentModifier(SPEED_MODIFIER);
			}	
			
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) == null) {
			    attackSpeedAttr.addPermanentModifier(ATTACK_SPEED_MODIFIER);
			}	
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!entity.level().isClientSide) {
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);

			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) != null) {
				speedAttr.removeModifier(SPEED_MODIFIER);
			}	
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) != null) {
				attackSpeedAttr.removeModifier(ATTACK_SPEED_MODIFIER);
			}
		}
	}

}
