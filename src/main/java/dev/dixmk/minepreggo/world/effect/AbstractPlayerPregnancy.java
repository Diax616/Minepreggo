package dev.dixmk.minepreggo.world.effect;

import java.util.UUID;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP0;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public abstract class AbstractPlayerPregnancy<S extends PlayerPregnancySystemP0> extends MobEffect  {
	protected static final UUID SPEED_MODIFIER_UUID = UUID.fromString("a0bf6ac9-4354-4977-86fc-5dea9108665d");
	protected static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("57a3938d-b55a-47b5-93ee-737724ba9d2e");
	
	protected @Nullable S pregnancySystem = null;
	
	protected AbstractPlayerPregnancy() {
		super(MobEffectCategory.NEUTRAL, -6750055);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_19479_, int p_19480_) {
    	if (entity instanceof ServerPlayer serverPlayer) {
    		ensurePregnancySystemInitialized(serverPlayer);
    	}
	}
		
	/*If player logs back, pregnancySystem might not be null but have a stale player reference and packets does not reach new player
		So we need to ensure the player reference is valid			
		* Recreate if null OR if the player UUID doesn't match (shouldn't happen)
		* OR if we need to refresh the reference
		* Check if the stored player reference is stale
	*/
    protected abstract void ensurePregnancySystemInitialized(ServerPlayer serverPlayer);
	  
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {  	
    	if (entity.level().isClientSide) {
    		return;
    	}
    	
    	if (entity instanceof ServerPlayer serverPlayer) {
            // Ensure the system is initialized before trying to use it
            ensurePregnancySystemInitialized(serverPlayer);
    	}
        
        if (pregnancySystem != null) {
            pregnancySystem.onServerTick();
        }
        else if (entity instanceof ServerPlayer) {
			// This should not happen, but log a warning if it does
			MinepreggoMod.LOGGER.warn("Pregnancy system is not initialized for entity: {}", entity.getName().getString());
		}
    }
    
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
	
    @Override
    public void removeAttributeModifiers(LivingEntity p_19469_, AttributeMap p_19470_, int p_19471_) {
    	pregnancySystem = null;
    }
}
