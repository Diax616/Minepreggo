package dev.dixmk.minepreggo.client.animation.preggo;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum BellyInflation {
	LOW(BellyAnimation.LOW_BELLY_INFLATION),
	MEDIUM(BellyAnimation.MEDIUM_BELLY_INFLATION),
	HIGH(BellyAnimation.HIGH_BELLY_INFLATION);
	
	public final AnimationDefinition animation;
	
	private BellyInflation(AnimationDefinition animation) {
		this.animation = animation;
	}
}
