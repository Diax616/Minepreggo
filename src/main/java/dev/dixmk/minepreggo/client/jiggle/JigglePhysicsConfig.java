package dev.dixmk.minepreggo.client.jiggle;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JigglePhysicsConfig<E extends AbstractJigglePhysics.AbstractJigglePhysicsConfig> {
	public final float originalYPos;
	public final E config;
	
	public JigglePhysicsConfig(float originalYPos, E config) {
		this.originalYPos = originalYPos;
		this.config = config;
	}
}
