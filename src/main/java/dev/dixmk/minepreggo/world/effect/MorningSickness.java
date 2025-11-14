package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MorningSickness extends AbstractPlayerPregnancyPain {
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "morning sickness attack speed nerf", -0.25, AttributeModifier.Operation.MULTIPLY_BASE);

	public MorningSickness() {
		super(-16751104);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {	
			entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS, 0, false, false));
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);		
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) == null) {
				attackSpeedAttr.addPermanentModifier(ATTACK_SPEED_MODIFIER);
			}	
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {
			entity.removeEffect(MobEffects.CONFUSION);		
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);		
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) != null) {
				attackSpeedAttr.removeModifier(ATTACK_SPEED_MODIFIER);
			}
		}
	}
}
