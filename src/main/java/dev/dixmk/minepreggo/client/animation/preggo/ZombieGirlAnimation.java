package dev.dixmk.minepreggo.client.animation.preggo;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlAnimation {
	
	private ZombieGirlAnimation() {}	
	
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(4.0F).looping()
		.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1.2F).looping()
		.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3F, KeyframeAnimations.degreeVec(-6.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.9F, KeyframeAnimations.degreeVec(-6.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition AGGRESSION = AnimationDefinition.Builder.withLength(1.2F).looping()
		.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(-12.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(-12.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3F, KeyframeAnimations.degreeVec(-6.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.9F, KeyframeAnimations.degreeVec(-6.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.5417F)
		.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(26.9078F, -10.0523F, -7.4683F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-22.5703F, 4.6978F, -1.7139F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(26.9078F, 10.0523F, 7.4683F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-17.5703F, -4.6978F, 1.7139F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition WAIT1 = AnimationDefinition.Builder.withLength(6.0F).looping()
		.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(10.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.5F, KeyframeAnimations.degreeVec(10.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(10.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.5F, KeyframeAnimations.degreeVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(10.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition MORNING_SICKNESS = AnimationDefinition.Builder.withLength(3.0F).looping()
		.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.7102F, -35.5386F, 11.5345F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-8.2102F, -35.5386F, 11.5345F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-5.7102F, -35.5386F, 11.5345F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(47.0311F, 6.5835F, 24.1207F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(46.7197F, 3.1644F, 20.4587F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(47.0311F, 6.5835F, 24.1207F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.9096F, 19.9801F, 2.6602F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.4407F, -9.9904F, -2.5385F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("rightBoob", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-2.5024F, -2.4976F, -7.3909F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition RIDING = AnimationDefinition.Builder.withLength(6.0F).looping()
		.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(2.5F, 6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(7.5F, 6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.5F, KeyframeAnimations.degreeVec(2.5F, 6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(7.5F, 6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -8.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, -6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(7.5F, -6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(2.5F, -6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.5F, KeyframeAnimations.degreeVec(7.5F, -6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(2.5F, -6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -8.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -8.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-81.89F, 22.2971F, 3.0947F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-81.89F, -22.2971F, -3.0947F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -8.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();
	
	public static final AnimationDefinition MISCARRIAGE = AnimationDefinition.Builder.withLength(3.0F).looping()
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, 12.5F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(90.0F, 12.5F, 15.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(90.0F, 12.5F, 12.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(90.0F, -12.5F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(90.0F, -12.5F, -15.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(90.0F, -12.5F, -12.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.25F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.25F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
	
	public static final AnimationDefinition KICKING1 = AnimationDefinition.Builder.withLength(11.0F).looping()
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(32.5927F, 0.3472F, 1.6275F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(42.3434F, -3.1738F, 6.7602F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(32.5837F, 0.8858F, 2.4701F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(98.7156F, 18.4581F, 24.6859F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(88.5712F, 18.508F, 18.3059F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.75F, KeyframeAnimations.degreeVec(98.7156F, 18.4581F, 24.6859F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.0F, KeyframeAnimations.degreeVec(88.5712F, 18.508F, 18.3059F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.25F, KeyframeAnimations.degreeVec(98.7156F, 18.4581F, 24.6859F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(88.5712F, 18.508F, 18.3059F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(98.7156F, 18.4581F, 24.6859F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(88.5712F, 18.508F, 18.3059F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.25F, KeyframeAnimations.degreeVec(98.7156F, 18.4581F, 24.6859F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.5F, KeyframeAnimations.degreeVec(88.5712F, 18.508F, 18.3059F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.75F, KeyframeAnimations.degreeVec(98.7156F, 18.4581F, 24.6859F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(88.5712F, 18.508F, 18.3059F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.5F, KeyframeAnimations.degreeVec(32.5927F, 0.3472F, 1.6275F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(11.0F, KeyframeAnimations.degreeVec(32.5927F, 0.3472F, 1.6275F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(52.3418F, -0.1255F, -3.1659F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(34.7547F, 3.0704F, -6.6776F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(52.3363F, -0.9171F, -3.7769F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(89.796F, -6.8066F, -19.1021F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(80.5244F, -6.0138F, -13.3945F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.75F, KeyframeAnimations.degreeVec(89.796F, -6.8066F, -19.1021F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.0F, KeyframeAnimations.degreeVec(80.5244F, -6.0138F, -13.3945F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.25F, KeyframeAnimations.degreeVec(89.796F, -6.8066F, -19.1021F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(80.5244F, -6.0138F, -13.3945F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(89.796F, -6.8066F, -19.1021F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(80.5244F, -6.0138F, -13.3945F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.25F, KeyframeAnimations.degreeVec(89.796F, -6.8066F, -19.1021F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.5F, KeyframeAnimations.degreeVec(80.5244F, -6.0138F, -13.3945F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.75F, KeyframeAnimations.degreeVec(89.796F, -6.8066F, -19.1021F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(80.5244F, -6.0138F, -13.3945F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.5F, KeyframeAnimations.degreeVec(52.3418F, -0.1255F, -3.1659F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(11.0F, KeyframeAnimations.degreeVec(52.3418F, -0.1255F, -3.1659F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 3.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.5F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.5F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(11.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_eg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -3.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(0.0F, 15.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 15.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(0.0F, 15.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 15.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.5F, KeyframeAnimations.degreeVec(0.0F, 15.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(0.0F, 15.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.5F, KeyframeAnimations.degreeVec(0.0F, 15.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(11.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.75F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.25F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.25F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.5F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(11.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.001F, 1.001F, 1.03F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.scaleVec(1.001F, 1.001F, 1.03F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.scaleVec(1.075F, 1.075F, 1.075F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.75F, KeyframeAnimations.scaleVec(1.075F, 1.075F, 1.075F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.25F, KeyframeAnimations.scaleVec(1.075F, 1.075F, 1.075F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.scaleVec(1.075F, 1.075F, 1.075F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.25F, KeyframeAnimations.scaleVec(1.075F, 1.075F, 1.075F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.75F, KeyframeAnimations.scaleVec(1.075F, 1.075F, 1.075F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(11.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.75F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.25F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.25F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.75F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.5F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(11.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

		public static final AnimationDefinition KICKING2 = AnimationDefinition.Builder.withLength(11.5F).looping()
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -10.0F, 7.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Boob", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.25F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.75F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(95.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(92.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(92.54F, 9.99F, -0.44F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.0F, KeyframeAnimations.degreeVec(95.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -12.0F, 7.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(95.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(92.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(95.24F, -17.43F, 1.57F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.0F, KeyframeAnimations.degreeVec(95.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -12.0F, 7.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-105.4658F, 37.6459F, -32.1212F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.625F, KeyframeAnimations.degreeVec(-84.92F, 40.93F, 0.12F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(-93.2103F, 22.6987F, -20.5978F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(-93.2103F, 22.6987F, -20.5978F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.0F, KeyframeAnimations.degreeVec(-85.0F, 40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_eg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-85.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-84.584F, -44.9794F, -0.6153F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-85.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(-84.584F, -44.9794F, -0.6153F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.625F, KeyframeAnimations.degreeVec(-84.92F, -40.93F, -0.12F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(-91.1093F, -22.8872F, 15.1778F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(-91.1093F, -22.8872F, 15.1778F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.0F, KeyframeAnimations.degreeVec(-85.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_eg", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.5F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.75F, KeyframeAnimations.degreeVec(7.5462F, 4.8812F, 1.0848F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.25F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.5F, KeyframeAnimations.degreeVec(7.6044F, -7.3212F, -1.6322F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.75F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.25F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.15F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.75F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.15F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.25F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.5F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.15F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.75F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.scaleVec(1.0F, 1.105F, 1.18F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.scaleVec(1.125F, 1.075F, 1.375F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.25F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(4.75F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.3F, -0.2F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-44.3128F, -8.8035F, -8.9093F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-12.3511F, 1.7279F, 9.8511F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(-17.35F, 1.73F, 9.85F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(6.0F, KeyframeAnimations.degreeVec(-12.3511F, 1.7279F, 9.8511F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.625F, KeyframeAnimations.degreeVec(-14.85F, 1.73F, 9.85F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(7.75F, KeyframeAnimations.degreeVec(-20.9633F, -0.695F, -20.2702F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(2.3F, 2.44F, 1.58F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(9.0F, KeyframeAnimations.degreeVec(2.3F, 2.44F, 1.58F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(10.0F, KeyframeAnimations.degreeVec(-44.3128F, -8.8035F, -8.9093F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -10.0F, 7.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

		
		public static final AnimationDefinition CONTRACTION1 = AnimationDefinition.Builder.withLength(4.0F).looping()
				.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(56.058F, -5.4889F, -0.9854F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(57.1598F, -11.1611F, 0.6214F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -8.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();

			public static final AnimationDefinition BIRTH = AnimationDefinition.Builder.withLength(9.0F).looping()
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -21.5F, 3.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.6673F, 12.5408F, -0.7194F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(4.8844F, 17.5235F, -0.2501F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(4.6673F, 12.5408F, -0.7194F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.5F, KeyframeAnimations.degreeVec(4.8844F, 17.5235F, -0.2501F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(4.6673F, 12.5408F, -0.7194F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(7.5F, KeyframeAnimations.degreeVec(4.8844F, 17.5235F, -0.2501F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(9.0F, KeyframeAnimations.degreeVec(4.6673F, 12.5408F, -0.7194F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -19.0F, 3.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, -19.0F, 3.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.posVec(0.0F, -19.0F, 3.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-33.3355F, 10.0F, 6.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(-38.3355F, 10.0F, 6.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-33.3355F, 10.0F, 6.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.5F, KeyframeAnimations.degreeVec(-38.3355F, 10.0F, 6.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-33.3355F, 10.0F, 6.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(7.5F, KeyframeAnimations.degreeVec(-38.3355F, 10.0F, 6.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(9.0F, KeyframeAnimations.degreeVec(-33.3355F, 10.0F, 6.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -19.0F, 3.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, -19.0F, 3.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.posVec(0.0F, -19.0F, 3.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-90.0F, 40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(-90.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-90.0F, 40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.5F, KeyframeAnimations.degreeVec(-90.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-90.0F, 40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-90.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.degreeVec(-90.0F, 40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -9.0F, -7.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, -9.0F, -7.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.posVec(0.0F, -9.0F, -7.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-90.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(-90.0F, -42.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-90.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.5F, KeyframeAnimations.degreeVec(-90.0F, -42.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-90.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-90.0F, -42.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.degreeVec(-90.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -9.0F, -7.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, -9.0F, -7.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.posVec(0.0F, -9.0F, -7.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, -1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.5F, KeyframeAnimations.degreeVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, -1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.degreeVec(0.0F, -1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.5F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.25F, KeyframeAnimations.scaleVec(1.05F, 1.0F, 1.15F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.75F, KeyframeAnimations.scaleVec(1.125F, 1.0F, 1.2F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(5.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.25F, KeyframeAnimations.scaleVec(1.05F, 1.0F, 1.275F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(7.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(8.75F, KeyframeAnimations.scaleVec(1.05F, 1.0F, 1.15F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-109.7198F, -3.4049F, -9.408F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.5F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.5F, KeyframeAnimations.degreeVec(-109.5623F, 4.2453F, 11.7678F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0833F, KeyframeAnimations.degreeVec(-109.7198F, -3.4049F, -9.408F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.7083F, KeyframeAnimations.degreeVec(-109.5623F, 4.2453F, 11.7678F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-109.7198F, -3.4049F, -9.408F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -21.0F, 3.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();
			
			
			
			public static final AnimationDefinition CONTRACTION2 = AnimationDefinition.Builder.withLength(4.0F).looping()
					.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(56.058F, -5.4889F, -0.9854F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(57.1598F, -11.1611F, 0.6214F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -8.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.4F, -2.5F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(4.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
					))
					.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
						new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.1F), AnimationChannel.Interpolations.LINEAR)
					))
					.build();
}