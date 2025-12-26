package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import net.minecraft.server.level.ServerPlayer;

public class Birth extends AbstractPlayerPregnancyPain {
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "birth attack speed nerf", -1D, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "birth speed nerf", -1D, AttributeModifier.Operation.MULTIPLY_TOTAL);

	public Birth() {
		super(-6750208);
	}
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {		
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (entity.level().isClientSide) {
	        entity.playSound(MinepreggoModSounds.PLAYER_BIRTH.get(), 0.8F, 0.8F + entity.getRandom().nextFloat() * 0.3F);
		}
		
		if (!entity.level().isClientSide) {			
			entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, -1, 3, false, false));	
			
	        ServerPlayerAnimationManager.getInstance().triggerAnimation((ServerPlayer) entity, "birth");

			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);

			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) == null) {
			    speedAttr.addTransientModifier(SPEED_MODIFIER);
			}			
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) == null) {
			    attackSpeedAttr.addTransientModifier(ATTACK_SPEED_MODIFIER);
			}				
		}	
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!PlayerHelper.isPlayerValid(entity)) return;
	
		if (!entity.level().isClientSide) {	
			
			ServerPlayerAnimationManager.getInstance().stopAnimation((ServerPlayer) entity);
			
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);
			
			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) != null) {
				speedAttr.removeModifier(SPEED_MODIFIER);
			}	
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) != null) {
				attackSpeedAttr.removeModifier(ATTACK_SPEED_MODIFIER);
			}		
			
			entity.removeEffect(MobEffects.WEAKNESS);	
		}
	}
}
