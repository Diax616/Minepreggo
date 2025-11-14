package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
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
	    
	private final Map<UUID, ArmAnimation> activeAnimations = new HashMap<>();
    
	public void startAnimation(UUID playerUUID, ArmAnimationType type) {
		activeAnimations.put(playerUUID, new ArmAnimation(type, System.currentTimeMillis()));
	}
	    
	public void stopAnimation(UUID playerUUID) {
		activeAnimations.remove(playerUUID);
	}
	    
	public boolean isAnimating(UUID playerUUID) {
		ArmAnimation anim = activeAnimations.get(playerUUID);
		if (anim != null && anim.isExpired()) {
			activeAnimations.remove(playerUUID);
			return false;
		}
		return anim != null;
	}
	    
	public void applyAnimation(UUID playerUUID, HumanoidArm arm, PoseStack poseStack, float partialTicks) {
		ArmAnimation anim = activeAnimations.get(playerUUID);
		if (anim != null) {
			if (anim.isExpired()) {
				activeAnimations.remove(playerUUID);
				return;
			}
			float progress = anim.getProgress();
			anim.apply(poseStack, arm, partialTicks);
			MinepreggoMod.LOGGER.debug("Applied arm animation for player {}: type={}, progress={}", playerUUID, anim.type.name(), progress);
		}
	}
	    
	public void tick() {
		activeAnimations.entrySet().removeIf(entry -> entry.getValue().isExpired());
	}
  
    
    public static class ArmAnimation {
        private final ArmAnimationType type;
        private final long startTime;
        private final long duration;
        
        public ArmAnimation(ArmAnimationType type, long startTime) {
            this.type = type;
            this.startTime = startTime;
            this.duration = type.getDuration();
        }
        
        public boolean isExpired() {
            return System.currentTimeMillis() - startTime > duration;
        }
        
        public float getProgress() {
            return Math.min(1.0f, (System.currentTimeMillis() - startTime) / (float) duration);
        }
        
        public void apply(PoseStack poseStack, HumanoidArm arm, float partialTicks) {
            float progress = getProgress();
            type.apply(poseStack, arm, progress);
        }
    }
    
    public enum ArmAnimationType {
        WAVE(2000) {
            @Override
            public void apply(PoseStack poseStack, HumanoidArm arm, float progress) {
                float wave = (float) Math.sin(progress * Math.PI * 4);
                
                // Translate the hand position for more visible movement
                poseStack.translate(0, wave * 0.3, 0);
                
                // Rotate around X axis (up/down motion)
                poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(-30 + wave * 30));
                // Slight Z rotation for tilt
                poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(wave * 20));
            }
        };
    
        private final long duration;
        
        ArmAnimationType(long duration) {
            this.duration = duration;
        }
        
        public long getDuration() {
            return duration;
        }
        
        public abstract void apply(PoseStack poseStack, HumanoidArm arm, float progress);
    }
}