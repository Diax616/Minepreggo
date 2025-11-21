package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP2;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class PregnancyP2 extends AbstractPlayerPregnancy<PlayerPregnancySystemP2> {
	
	@Override
    protected void ensurePregnancySystemInitialized(ServerPlayer serverPlayer) {
        if (pregnancySystem == null) {
            this.pregnancySystem = new PlayerPregnancySystemP2(serverPlayer);
            MinepreggoMod.LOGGER.info("Initialized PlayerPregnancySystemP2 for player: {}", serverPlayer.getName().getString());
        }
        else if (!pregnancySystem.isPlayerValid(serverPlayer)) {
            MinepreggoMod.LOGGER.info("Player reference is stale, reinitializing PlayerPregnancySystemP2");
            this.pregnancySystem = new PlayerPregnancySystemP2(serverPlayer);
        }
    } 
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_19479_, int p_19480_) {
		super.addAttributeModifiers(entity, p_19479_, p_19480_);
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {
			entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, -1, 0, false, false));			
		}
	}

	
    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_19470_, int p_19471_) {
    	super.removeAttributeModifiers(entity, p_19470_, p_19471_);
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {
			entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
		}
    }
}
