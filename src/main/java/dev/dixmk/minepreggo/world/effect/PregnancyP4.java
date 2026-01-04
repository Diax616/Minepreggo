package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP4;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PregnancyP4 extends AbstractPlayerPregnancy<PlayerPregnancySystemP4> {
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "pregnancy attack speed nerf", -0.2, AttributeModifier.Operation.MULTIPLY_BASE);

	@Override
    protected void ensurePregnancySystemInitialized(ServerPlayer serverPlayer) {
        PlayerPregnancySystemP4 pregnancySystem = pregnancySystemsCache.get(serverPlayer.getUUID());
        
        if (pregnancySystem == null) {
            pregnancySystem = new PlayerPregnancySystemP4(serverPlayer);
            pregnancySystemsCache.put(serverPlayer.getUUID(), pregnancySystem);
            MinepreggoMod.LOGGER.info("Initialized PlayerPregnancySystemP4 for player: {}", serverPlayer.getName().getString());
        }
        else if (!pregnancySystem.isPlayerValid(serverPlayer)) {
            MinepreggoMod.LOGGER.info("Player {} reference is stale, reinitializing PlayerPregnancySystemP4", serverPlayer.getGameProfile().getName());
            pregnancySystem = new PlayerPregnancySystemP4(serverPlayer);
            pregnancySystemsCache.put(serverPlayer.getUUID(), pregnancySystem);
        }
    } 
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_19479_, int p_19480_) {
		super.addAttributeModifiers(entity, p_19479_, p_19480_);
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {
			entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, -1, 1, false, false));			
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) == null) {
			    attackSpeedAttr.addPermanentModifier(ATTACK_SPEED_MODIFIER);
			}
		}
	}
	
    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_19470_, int p_19471_) {
		super.removeAttributeModifiers(entity, p_19470_, p_19471_);
    	if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {
			entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);		
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) != null) {
				attackSpeedAttr.removeModifier(ATTACK_SPEED_MODIFIER);
			}
		}
    }
}


