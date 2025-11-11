package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class FetalMovement extends AbstractPregnancyPain {
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "fetal movement speed nerf", -0.2, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "fetal movement attack nerf", -0.1, AttributeModifier.Operation.MULTIPLY_BASE);

	public FetalMovement() {
		super(-3407872);

	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (!(entity instanceof Player player)) return;
		
		if (!player.level().isClientSide) {	
			player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS, 0, false, false));			
		
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
		if (!(entity instanceof Player player)) return;
		
		if (!player.level().isClientSide) {		
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
