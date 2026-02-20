package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerAnimationManager {
	
    private final Map<UUID, PlayerAnimationCache> animations;
	
	private PlayerAnimationManager() {
		animations = new HashMap<>();
	}
	
    private static class Holder {
        private static final PlayerAnimationManager INSTANCE = new PlayerAnimationManager();
    }
       	    
    public static PlayerAnimationManager getInstance() {
        return Holder.INSTANCE;
    }
	
    public PlayerAnimationCache get(Player player) {
        return animations.computeIfAbsent(player.getUUID(), PlayerAnimationCache::new);
    }
    
    public void cleanup(UUID playerId) {
    	animations.remove(playerId);
    }
     
    @OnlyIn(Dist.CLIENT)
    public static class PlayerAnimationCache { 	
    	public final UUID playerId;
    	private final Map<String, ModelPart> modelParts = new HashMap<>();
    	private final Map<String, PartState> originalStates = new HashMap<>();
    	    
    	private @Nullable PlayerAnimation currentAnimation;
    	private int linearAnimationTick = 0;
        private int continuousAnimationTick = 0; // Tick since animation started playing (does not reset on loop)
    	private boolean isPlaying = false;
    	private @Nullable String lastAnimationName = null;
    	
    	
    	private PlayerAnimationCache(UUID playerId) {
    		this.playerId = playerId;
    	}
    	    
    	public void registerModelPart(String name, ModelPart part) {
    		modelParts.put(name, part);
    		if (!originalStates.containsKey(name)) {
    			saveOriginalState(name, part);
    		}
    	}
    	    
    	private void saveOriginalState(String name, ModelPart part) {
    		originalStates.put(name, new PartState(
    				part.xRot, part.yRot, part.zRot,
    				part.x, part.y, part.z
    				));
    	}
    	    
    	public void playAnimation(String animationName) {
    		PlayerAnimation anim = PlayerAnimationRegistry.getInstance().getAnimation(animationName);
    		if (anim != null) {
    			this.currentAnimation = anim;
    			this.linearAnimationTick = 0;
    			
                if (!animationName.equals(this.lastAnimationName)) {
                    this.continuousAnimationTick = 0; // Reset continuous counter for new animation
               }
    			
    			this.isPlaying = true;  			
    			this.lastAnimationName = animationName;
    		}
    	}
    	    
    	public void stopAnimation() {
    		this.isPlaying = false;
    		this.currentAnimation = null;
    		this.linearAnimationTick = 0;
    		this.continuousAnimationTick = 0;
    		this.lastAnimationName = null;
    		resetToOriginalPose();
    	}
    	    
    	public void tick() {
    		if (!isPlaying || currentAnimation == null) {
    			return;
    		}
    	        
    		linearAnimationTick++;
    		continuousAnimationTick++;
    		
    		if (linearAnimationTick >= currentAnimation.getDuration()) {
    			if (currentAnimation.isLooping()) {
    				linearAnimationTick = 0;
    			} else {
    				stopAnimation();
    			}
    		}
    	}
    	    
    	public void applyLinearAnimation() {
    		applyAnimation(linearAnimationTick);
    		
    	}
    	
    	public void applyContinuousAnimation() {
    		applyAnimation(continuousAnimationTick);		
    	}
    		
    	private void applyAnimation(int rawTick) {
    		if (!isPlaying || currentAnimation == null) {
    			return;
    		}
    	        
    		// Apply animation to each part
    		for (String partName : currentAnimation.getAnimatedParts()) {
    			ModelPart part = modelParts.get(partName);
    			if (part != null) {
    				// Reset to original state first
                    if (currentAnimation.shouldOverrideVanilla()) {
                        // Reset to original state first
                        PartState original = originalStates.get(partName);
                        if (original != null) {
                            part.xRot = original.xRot;
                            part.yRot = original.yRot;
                            part.zRot = original.zRot;
                            part.x = original.x;
                            part.y = original.y;
                            part.z = original.z;
                        }
                    }
    	                
    				// Apply animation on top
    				currentAnimation.applyAnimation(partName, part, rawTick);
    			}
    		}
    	}
    			    
    	private void resetToOriginalPose() {
    		for (Map.Entry<String, ModelPart> entry : modelParts.entrySet()) {
    			String partName = entry.getKey();
    			ModelPart part = entry.getValue();
    			PartState original = originalStates.get(partName);
    	            
    			if (original != null) {
    				part.xRot = original.xRot;
    				part.yRot = original.yRot;
    				part.zRot = original.zRot;
    				part.x = original.x;
    				part.y = original.y;
    				part.z = original.z;
    			}
    		}
    	}
    	    
    	public boolean isPlaying() {
    		return isPlaying;
    	}
    	    
    	public boolean hasActiveAnimation() {
    		return isPlaying && currentAnimation != null;
    	}
    	   	   
    	@Nullable
    	public String getCurrentAnimationName() {
    		return lastAnimationName;    			
    	}
    	    
    	public int getLinearAnimationTick() {
    		return linearAnimationTick;
    	}
    	
    	public int getContinuousAnimationTick() {
    		return continuousAnimationTick;
    	}
    	    
    	public void setAnimationState(String animationName, int tick) {
    		playAnimation(animationName);
    		this.linearAnimationTick = tick;
    	}
    	    
        public boolean shouldOverrideVanillaAnimations() {
            return hasActiveAnimation() && currentAnimation.shouldOverrideVanilla();
        }
    	
    	private static record PartState(float xRot, float yRot, float zRot, float x, float y, float z) {}
    }
}