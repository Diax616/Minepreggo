package dev.dixmk.minepreggo.client.entity.animation.preggo;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class HumanoidGirlAnimation {
	
	private HumanoidGirlAnimation() {}
	
	public static final AnimationDefinition LOW_BELLY_INFLATION = AnimationDefinition.Builder.withLength(4.0F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.03F, 1.0F, 1.03F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
	
	public static final AnimationDefinition MEDIUM_BELLY_INFLATION = AnimationDefinition.Builder.withLength(4.0F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.03F, 1.0F, 1.05F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
	
	public static final AnimationDefinition HIGH_BELLY_INFLATION = AnimationDefinition.Builder.withLength(4.0F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.03F, 1.0F, 1.07F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
}
