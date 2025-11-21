package dev.dixmk.minepreggo.common.animation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.CheckForNull;

import net.minecraft.util.Mth;

public class ArmAnimationRegistry {

	private ArmAnimationRegistry() {}
	
    private static class Holder {
        private static final ArmAnimationRegistry INSTANCE = new ArmAnimationRegistry();
    }
       	    
	public static ArmAnimationRegistry getInstance() {
        return Holder.INSTANCE;
    }
	
	private final Map<String, ArmAnimation> animations = new HashMap<>();
	    
	public void register(ArmAnimation animation) {
		animations.put(animation.getName(), animation);
	}
	    
	public boolean isPresent(String name) {
		return animations.containsKey(name);
	}
	
	@CheckForNull
	public ArmAnimation getAnimation(String name) {
		return animations.get(name);
	} 
	
	public Collection<String> getAllAnimationNames() {
		return animations.keySet();
	}
	    
	public void init() { 
		var rubbingBellyP1 = new ArmAnimation("rubbing_belly_p1", 2000, (poseStack, humanoidArm, partialTicks) -> {		
            final float rub = Mth.sin(partialTicks * Mth.PI);          
            // Translate the hand position for more visible movement
            poseStack.translate(0, rub * -0.3, 0);         
            // Rotate around X axis (up/down motion)
            poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(rub * -20));
            // Slight Z rotation for tilt
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(-rub * 3));
		});		
		register(rubbingBellyP1);
		
		var rubbingBellyP3 = new ArmAnimation("rubbing_belly_p3", 3000, (poseStack, humanoidArm, partialTicks) -> {		
			final float rub = Mth.sin(partialTicks * Mth.PI);          
            poseStack.translate(rub * 0.5, rub * -0.3, 0);         
            poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(rub * -20));
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(-rub * 5));
		});
		register(rubbingBellyP3);
		
		var rubbingBellyP5 = new ArmAnimation("rubbing_belly_p5", 3000, (poseStack, humanoidArm, partialTicks) -> {		
			final float rub = Mth.sin(partialTicks * Mth.PI);          
            poseStack.translate(rub * 0.8, rub * -0.3, 0);         
            poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(rub * -20));
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(-rub * 7));
		});
		register(rubbingBellyP5);
	}
}
