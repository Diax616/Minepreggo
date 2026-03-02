package dev.dixmk.minepreggo.client.animation.preggo;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum FetalMovementIntensity {
	P3(BellyAnimation.FETAL_MOVEMENT_P3),
	P4(BellyAnimation.FETAL_MOVEMENT_P4),
	P5(BellyAnimation.FETAL_MOVEMENT_P5),
	P6(BellyAnimation.FETAL_MOVEMENT_P6),
	P7(BellyAnimation.FETAL_MOVEMENT_P7),
	P8(BellyAnimation.FETAL_MOVEMENT_P8);	
	
	public final AnimationDefinition animation;
	
	private FetalMovementIntensity(AnimationDefinition animation) {
		this.animation = animation;
	}
}
