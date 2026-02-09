package dev.dixmk.minepreggo.world.effect;

import java.util.UUID;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilPregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ZeroGravityBelly extends MobEffect {
	private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("0f7f79f6-2e81-4413-967b-581af31608ce");
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "pregnancy speed buf", 0.25, AttributeModifier.Operation.MULTIPLY_BASE);

	public ZeroGravityBelly() {
		super(MobEffectCategory.BENEFICIAL, -16724737);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (entity.level().isClientSide) {
			return;
		}
		
		if (entity instanceof ServerPlayer serverPlayer) {		
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
				cap.getFemaleData().ifPresent(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
						AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_SPEED);
						MobEffect pregnancyEffect = PlayerHelper.getPregnancyEffects(femaleData.getPregnancyData().getCurrentPregnancyPhase());
						MobEffectInstance instance = serverPlayer.getEffect(pregnancyEffect);
						
						if (instance != null && instance.getEffect() instanceof AbstractPlayerPregnancy<?> playerPregnancyEffect) {
							serverPlayer.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);							
							if (speedAttr != null && speedAttr.getModifier(AbstractPlayerPregnancy.SPEED_MODIFIER_UUID) != null) {	
								playerPregnancyEffect.getSpeedModifier().ifPresent(speedAttr::removeModifier);
							}
							if (attackAttr != null && attackAttr.getModifier(AbstractPlayerPregnancy.ATTACK_SPEED_MODIFIER_UUID) != null) {	
								playerPregnancyEffect.getAttackSpeedModifier().ifPresent(attackAttr::removeModifier);
							}
						}
						
						PregnancySystemHelper.removeGravityModifier(entity);
						PregnancySystemHelper.removeKnockbackResistanceModifier(entity);
					}
				})
			);		
		}
		else if (entity instanceof ITamablePregnantPreggoMob || entity instanceof IHostilPregnantPreggoMob) {
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) == null) {
			    speedAttr.addPermanentModifier(SPEED_MODIFIER);
			}
			PregnancySystemHelper.removeGravityModifier(entity);
			PregnancySystemHelper.removeKnockbackResistanceModifier(entity);
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (entity.level().isClientSide) {
			return;
		}
		
		if (entity instanceof ServerPlayer serverPlayer) {		
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
				cap.getFemaleData().ifPresent(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
						AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_SPEED);
						MobEffect pregnancyEffect = PlayerHelper.getPregnancyEffects(femaleData.getPregnancyData().getCurrentPregnancyPhase());
						MobEffectInstance instance = serverPlayer.getEffect(pregnancyEffect);
						
						if (instance != null && instance.getEffect() instanceof AbstractPlayerPregnancy<?> playerPregnancyEffect) {
							playerPregnancyEffect.getMovementSpeedNerfAmplifier().ifPresent(amp -> serverPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, -1, amp, false, false)));					
							if (speedAttr != null && speedAttr.getModifier(AbstractPlayerPregnancy.SPEED_MODIFIER_UUID) == null) {	
								playerPregnancyEffect.getSpeedModifier().ifPresent(speedAttr::addPermanentModifier);
							}
							if (attackAttr != null && attackAttr.getModifier(AbstractPlayerPregnancy.ATTACK_SPEED_MODIFIER_UUID) == null) {	
								playerPregnancyEffect.getAttackSpeedModifier().ifPresent(attackAttr::addPermanentModifier);
							}
						}	
						var pregnancyPhase = femaleData.getPregnancyData().getCurrentPregnancyPhase();
						PregnancySystemHelper.applyGravityModifier(entity, pregnancyPhase);
						PregnancySystemHelper.applyKnockbackResistanceModifier(entity, pregnancyPhase);
					}
				})
			);		
		}
		else if (entity instanceof ITamablePregnantPreggoMob || entity instanceof IHostilPregnantPreggoMob) {
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) != null) {
				speedAttr.removeModifier(SPEED_MODIFIER);
			}
			
			if (entity instanceof ITamablePregnantPreggoMob tamablePreggo) {
				PregnancySystemHelper.applyGravityModifier(entity, tamablePreggo.getPregnancyData().getCurrentPregnancyPhase());
				PregnancySystemHelper.applyKnockbackResistanceModifier(entity, tamablePreggo.getPregnancyData().getCurrentPregnancyPhase());
			}
			else if (entity instanceof IHostilPregnantPreggoMob monsterPreggo) {
				PregnancySystemHelper.applyGravityModifier(entity, monsterPreggo.getPregnancyData().getCurrentPregnancyPhase());
				PregnancySystemHelper.applyKnockbackResistanceModifier(entity, monsterPreggo.getPregnancyData().getCurrentPregnancyPhase());
			}
		}
	}
}
