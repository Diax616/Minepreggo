package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BellyAnimationManager {
	
	private BellyAnimationManager() {}
	
    private static class Holder {
        private static final BellyAnimationManager INSTANCE = new BellyAnimationManager();
    }
       	    
    public static BellyAnimationManager getInstance() {
        return Holder.INSTANCE;
    }
	
    private final Map<UUID, AnimationState> animationStates = new HashMap<>();
    private final Map<UUID, AnimationDefinition> currentAnimations = new HashMap<>();
    
    public void startAnimation(Player player, AnimationDefinition definition) {
        UUID id = player.getUUID();
        AnimationState state = animationStates.computeIfAbsent(id, k -> new AnimationState());   
        state.stop();
        state.start(player.tickCount);
        currentAnimations.put(id, definition);
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
