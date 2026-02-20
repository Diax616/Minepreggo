package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BellyAnimationManager {
	
    private final Map<UUID, AnimationState> animationStates;
    private final Map<UUID, AnimationDefinition> currentAnimations;
	
	private BellyAnimationManager() {
	    animationStates = new HashMap<>();
	    currentAnimations = new HashMap<>();
	}
	
    private static class Holder {
        private static final BellyAnimationManager INSTANCE = new BellyAnimationManager();
    }
       	    
    public static BellyAnimationManager getInstance() {
        return Holder.INSTANCE;
    }

    private void startAnimation(LivingEntity entity, AnimationDefinition definition) {
        UUID id = entity.getUUID();
        AnimationState state = animationStates.computeIfAbsent(id, k -> new AnimationState());   
        state.stop();
        state.start(entity.tickCount);
        currentAnimations.put(id, definition);
    } 
    
    public void startAnimation(Player player, AnimationDefinition definition) {
    	startAnimation((LivingEntity) player, definition);
    }
    
    public void startAnimation(AbstractTamablePregnantCreeperGirl creeperGirl, AnimationDefinition definition) {
    	startAnimation((LivingEntity) creeperGirl, definition);
    }
    
    public void startAnimation(AbstractTamablePregnantZombieGirl zombieGirl, AnimationDefinition definition) {
    	startAnimation((LivingEntity) zombieGirl, definition);
    }
    
    public void startAnimation(AbstractTamablePregnantEnderWoman enderWoman, AnimationDefinition definition) {
    	startAnimation((LivingEntity) enderWoman, definition);
    }
       
    @Nullable
    public AnimationState getAnimationState(UUID playerId) {
        return animationStates.get(playerId);
    }
    
    @Nullable
    public AnimationDefinition getCurrentAnimation(UUID playerId) {
        return currentAnimations.get(playerId);
    }
    
    public boolean isAnimating(UUID playerId) {
        AnimationState state = animationStates.get(playerId);
        AnimationDefinition def = currentAnimations.get(playerId);
        
        if (state == null || def == null || !state.isStarted()) {
            return false;
        }
        
        long elapsed = state.getAccumulatedTime();
        long duration = (long) (def.lengthInSeconds() * 1000f);
        
        if (elapsed > duration) {
            state.stop();
            currentAnimations.remove(playerId);
            return false;
        }
        
        return true;
    }
    
    public void cleanup(UUID playerId) {
    	animationStates.remove(playerId);
    	currentAnimations.remove(playerId);
    }
}
