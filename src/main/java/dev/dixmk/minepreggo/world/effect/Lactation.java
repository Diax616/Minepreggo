package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Lactation extends AbstractPlayerPregnancySymptom {
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "lactation speed nerf", -0.1, AttributeModifier.Operation.MULTIPLY_BASE);
		
	public Lactation() {
		super(-1);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (entity.level().isClientSide) {
			return;
		}
			
		if (PregnancySystemHelper.isPregnantEntityValid(entity) || PregnancySystemHelper.isInPostPregnancyPhase(entity)) {
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) == null) {
			    speedAttr.addPermanentModifier(SPEED_MODIFIER);
			}
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (entity.level().isClientSide) {
			return;
		}
		
		if (PregnancySystemHelper.isPregnantEntityValid(entity) || PregnancySystemHelper.isInPostPregnancyPhase(entity)) {
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) != null) {
				speedAttr.removeModifier(SPEED_MODIFIER);
			}
		}
	}
}
