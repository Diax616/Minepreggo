package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP1;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class PregnancyP1 extends AbstractPregnancy<PlayerPregnancySystemP1> {

	public PregnancyP1() {
		super();
	}

	@Override
	public void addAttributeModifiers(LivingEntity p_19478_, AttributeMap p_19479_, int p_19480_) {
		ensurePregnancySystemInitialized(p_19478_);
	}
	
    protected void ensurePregnancySystemInitialized(LivingEntity entity) {
        if (pregnancySystem == null && entity instanceof ServerPlayer serverPlayer) {
            this.pregnancySystem = new PlayerPregnancySystemP1(serverPlayer);
            MinepreggoMod.LOGGER.debug("Initialized PregnancyP1 system for player: {}", serverPlayer.getName().getString());
        } 
    }
       
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Ensure the system is initialized before trying to use it
        ensurePregnancySystemInitialized(entity);

        if (pregnancySystem != null) {
            pregnancySystem.onServerTick();
        }
        else {
			// This should not happen, but log a warning if it does
			MinepreggoMod.LOGGER.warn("Pregnancy system is not initialized for entity: {}", entity.getName().getString());
		}
    }
}
