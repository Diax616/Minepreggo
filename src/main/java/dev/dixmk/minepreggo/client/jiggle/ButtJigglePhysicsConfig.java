package dev.dixmk.minepreggo.client.jiggle;

import javax.annotation.concurrent.Immutable;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@Immutable
public class ButtJigglePhysicsConfig extends AbstractJigglePhysics.AbstractJigglePhysicsConfig {
	public ButtJigglePhysicsConfig(Builder builder) {
		super(builder.springStrength, builder.damping, builder.gravity, builder.maxDisplacement);
	}
	
	public static ButtJigglePhysicsConfig.Builder builder() {
		return new ButtJigglePhysicsConfig.Builder();
	}
	
    @OnlyIn(Dist.CLIENT)
    public static class Builder {
    	private float springStrength = 0.15f;
    	private float damping = 0.85f;
    	private float gravity = 0.02f;
    	private float maxDisplacement = 0.3f;
        
    	public Builder springStrength(float springStrength) {
    		this.springStrength = springStrength;
    		return this;
    	}
    	
    	public Builder damping(float damping) {
    		this.damping = damping;
    		return this;
    	}
    	
    	public Builder gravity(float gravity) {
    		this.gravity = gravity;
    		return this;
    	}
    	
    	public Builder maxDisplacement(float maxDisplacement) {
    		this.maxDisplacement = maxDisplacement;
    		return this;
    	}
    	
    	public ButtJigglePhysicsConfig build() {
    		return new ButtJigglePhysicsConfig(this);
    	}
    }
}
