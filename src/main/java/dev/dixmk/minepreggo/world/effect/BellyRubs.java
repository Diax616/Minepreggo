package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

public class BellyRubs extends AbstractPregnancySymptom {

	public BellyRubs() {
		super(-39322);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!(entity instanceof Player)) return;
		
		if (!entity.level().isClientSide) {
			entity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, -1, 0, false, false));
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (entity.level().isClientSide) {
			entity.removeEffect(MobEffects.UNLUCK);
		}
	}
}
