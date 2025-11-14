package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Craving extends AbstractPlayerPregnancySymptom {
	private static final AttributeModifier ATTACK_DAMAGE_MODIFIER = new AttributeModifier(ATTACK_DAMAGE_MODIFIER_UUID, "contraction attack damage nerf", -0.2, AttributeModifier.Operation.MULTIPLY_BASE);

	public Craving() {
		super(-39373);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {			
			AttributeInstance attackDamageAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
			if (attackDamageAttr != null && attackDamageAttr.getModifier(ATTACK_DAMAGE_MODIFIER_UUID) == null) {
				attackDamageAttr.addPermanentModifier(ATTACK_DAMAGE_MODIFIER);
			}
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {
			AttributeInstance attackDamageAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
			if (attackDamageAttr != null && attackDamageAttr.getModifier(ATTACK_DAMAGE_MODIFIER_UUID) != null) {
				attackDamageAttr.removeModifier(ATTACK_DAMAGE_MODIFIER);
			}
		}
	}
}
