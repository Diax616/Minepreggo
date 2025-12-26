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
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;

public class Miscarriage extends AbstractPlayerPregnancyPain {

	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "miscarriage attack speed nerf", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "miscarriage speed nerf", -0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL);

	public Miscarriage() {
		super(-6750208);
	}
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {			
		if (!PlayerHelper.isPlayerValid(entity)) return;
	
		if (entity.level().isClientSide) {
			entity.playSound(MinepreggoModSounds.PLAYER_MISCARRIAGE.get(), 0.8F, 0.8F + entity.getRandom().nextFloat() * 0.3F);
		}
		
		if (!entity.level().isClientSide) {
	        ServerPlayerAnimationManager.getInstance().triggerAnimation((ServerPlayer) entity, "miscarriage");
	
			entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);

			entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, -1, 1, false, false));	

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
			
			entity.removeEffect(MobEffects.WEAKNESS);	
			
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
