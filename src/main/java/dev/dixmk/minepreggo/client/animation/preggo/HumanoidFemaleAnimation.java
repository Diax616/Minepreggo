package dev.dixmk.minepreggo.client.animation.preggo;

import java.util.Map;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanoidFemaleAnimation {
	
	private HumanoidFemaleAnimation() {}
	
	public static final AnimationDefinition LOW_BELLY_INFLATION = AnimationDefinition.Builder.withLength(5.0F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.01F, 1.0F, 1.065F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.build();
	
	public static final AnimationDefinition MEDIUM_BELLY_INFLATION = AnimationDefinition.Builder.withLength(5.0F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0125F, 1.0F, 1.07F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.build();
	
	public static final AnimationDefinition HIGH_BELLY_INFLATION = AnimationDefinition.Builder.withLength(5.0F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.015F, 1.0F, 1.075F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.build();
	
	public static final AnimationDefinition FETAL_MOVEMENT_P3 = AnimationDefinition.Builder.withLength(7.5F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.01F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.4583F, KeyframeAnimations.scaleVec(1.02F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.9583F, KeyframeAnimations.scaleVec(1.0007F, 1.0F, 1.0017F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.25F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.875F, KeyframeAnimations.scaleVec(1.0254F, 1.0F, 0.9927F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.1667F, KeyframeAnimations.scaleVec(1.125F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(2.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.375F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(2.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.375F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.SCALE, 
					new Keyframe(2.875F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("right_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(3.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("right_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(3.7083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.125F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("left_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(4.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.addAnimation("left_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(4.5417F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0833F, KeyframeAnimations.posVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
				))
				.build();
	
	public static final AnimationDefinition FETAL_MOVEMENT_P6 = AnimationDefinition.Builder.withLength(8.0F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.02F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.5F, KeyframeAnimations.scaleVec(1.0969F, 1.0F, 1.02F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(5.2083F, KeyframeAnimations.scaleVec(1.05F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(5.5F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.125F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.4167F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.7917F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.125F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.4167F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(8.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(4.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(5.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(4.7917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(5.5833F, KeyframeAnimations.posVec(-4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.2917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_kick", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(4.7917F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(5.5833F, KeyframeAnimations.scaleVec(1.5F, 1.1F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.2917F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(6.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(6.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.2917F, KeyframeAnimations.posVec(4.7F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(8.0F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_kick", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(6.8333F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.2917F, KeyframeAnimations.scaleVec(1.3F, 1.1F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(8.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(5.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.4167F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(5.7917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.4167F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(5.7917F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.4167F, KeyframeAnimations.scaleVec(1.1F, 1.1F, 1.3F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
	
	public static final AnimationDefinition FETAL_MOVEMENT_P8 = AnimationDefinition.Builder.withLength(15.0F).looping()
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.01F, 1.0F, 1.075F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.5F, KeyframeAnimations.scaleVec(1.075F, 1.0F, 1.01F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.2917F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.5F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.125F, KeyframeAnimations.scaleVec(1.0275F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.4167F, KeyframeAnimations.scaleVec(1.15F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(8.625F, KeyframeAnimations.scaleVec(1.0333F, 1.0F, 0.9947F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(9.0833F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(9.3333F, KeyframeAnimations.scaleVec(1.02F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.5417F, KeyframeAnimations.scaleVec(1.0048F, 1.0F, 1.065F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.75F, KeyframeAnimations.scaleVec(1.0017F, 1.1F, 1.075F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(12.9167F, KeyframeAnimations.scaleVec(1.0F, 1.025F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(15.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(6.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(6.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.4167F, KeyframeAnimations.posVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.9167F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_kick", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(6.875F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.4167F, KeyframeAnimations.scaleVec(1.3F, 1.1F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.9167F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(6.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.4583F, KeyframeAnimations.posVec(-7.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_kick", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(6.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(6.4583F, KeyframeAnimations.scaleVec(1.3F, 1.1F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(7.2083F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(8.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(8.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(9.3333F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(8.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(9.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("front_kick", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(8.6667F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("top_kick", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(10.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.75F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(11.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("top_kick", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(10.125F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.75F, KeyframeAnimations.posVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(11.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("top_kick", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(10.125F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.4583F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(10.75F, KeyframeAnimations.scaleVec(1.1F, 1.1F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(11.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
	
	
	public static final AnimationDefinition RIGHT_SLAP = AnimationDefinition.Builder.withLength(1.4583F)
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 0.95F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.4583F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
	
	
	@OnlyIn(Dist.CLIENT)
	public static class Cinematic {
		
		private Cinematic() {}
		
		public static final AnimationDefinition SEX_COW_GIRL = AnimationDefinition.Builder.withLength(0.5F).looping()
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-29.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-79.0F, 33.0F, 9.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-78.873F, 33.9814F, 9.7301F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(-79.0F, 33.0F, 9.5F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-79.0F, -33.0F, -9.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-78.873F, -33.9814F, -9.7301F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(-79.0F, -33.0F, -9.5F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("boobs", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(18.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(18.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_boob", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_boob", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("belly", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.8861F, -29.9685F, -3.9426F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(2.9635F, -32.4653F, -4.0918F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(2.8861F, -29.9685F, -3.9426F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.8861F, 29.9685F, 3.4426F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(2.9635F, 32.4653F, 3.5918F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(2.8861F, 29.9685F, 3.4426F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();
	}
	
	
	public static final Map<Byte, AnimationDefinition> QUICK_ANIMATIONS = Map.of(
			(byte) 100, RIGHT_SLAP);
}

