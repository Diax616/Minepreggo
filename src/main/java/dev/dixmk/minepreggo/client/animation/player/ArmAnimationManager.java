package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.common.animation.ArmAnimation;
import dev.dixmk.minepreggo.common.animation.ArmAnimationRegistry;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArmAnimationManager {
	
	private ArmAnimationManager() {}
	
    private static class Holder {
        private static final ArmAnimationManager INSTANCE = new ArmAnimationManager();
    }
       	    
    public static ArmAnimationManager getInstance() {
        return Holder.INSTANCE;
    }
	    
	private final Map<UUID, ArmAnimationCache> activeAnimations = new HashMap<>();
    
	public void startAnimation(UUID playerUUID, String animationName) {
		var anim = ArmAnimationRegistry.getInstance().getAnimation(animationName);
		
		if (anim != null) {
			activeAnimations.put(playerUUID, new ArmAnimationCache(anim, System.currentTimeMillis()));

		}	
	}
	    
	public void stopAnimation(UUID playerUUID) {
		activeAnimations.remove(playerUUID);
	}
	    
	public boolean isAnimating(UUID playerUUID) {
		var anim = activeAnimations.get(playerUUID);
		if (anim != null && anim.isExpired()) {
			activeAnimations.remove(playerUUID);
			return false;
		}
		return anim != null;
	}
	    
	public void applyAnimation(UUID playerUUID, HumanoidArm arm, PoseStack poseStack, float partialTicks) {
		var anim = activeAnimations.get(playerUUID);
		if (anim != null) {
			if (anim.isExpired()) {
				activeAnimations.remove(playerUUID);
				return;
			}
			anim.animation.apply(poseStack, arm, anim.getProgress(partialTicks));
		}
	}
	    
	public void tick() {
		activeAnimations.entrySet().removeIf(entry -> entry.getValue().isExpired());
	}
	
	public static class ArmAnimationCache {
		final long startTime;
		final ArmAnimation animation;
		
		ArmAnimationCache(ArmAnimation animation, long startTime) {
			this.animation = animation;
			this.startTime = startTime;
		}
		
	    public boolean isExpired() {
	        return System.currentTimeMillis() - startTime > animation.getDuration();
	    }
        
	    public float getProgress(float partialTicks) {
            long currentTime = System.currentTimeMillis();
            float elapsedMillis = (currentTime - startTime) + (partialTicks * 50); // 50ms per tick (20 TPS)
            return Math.min(1.0f, elapsedMillis / animation.getDuration());	    
	    }
	}
}