package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JigglePhysics {
    private float velocity = 0.0f;
    private float position = 0.0f;
    private float previousPlayerY = 0.0f;
    
    private final float springStrength;
    private final float damping;
    private final float gravity;
    private final float maxDisplacement;
    
	protected JigglePhysics(Builder builder) {
		this.springStrength = builder.springStrength;
		this.damping = builder.damping;
		this.gravity = builder.gravity;
		this.maxDisplacement = builder.maxDisplacement;	
	}
    
	public static Builder builder() {
		return new JigglePhysics.Builder();
	}
	
	public JigglePhysics copy() {
		return builder()
				.springStrength(this.springStrength)
				.damping(this.damping)
				.gravity(this.gravity)
				.maxDisplacement(this.maxDisplacement)
				.build();
	}
	
    public void update(float playerY, float deltaTime) {
        // Calculate player velocity on Y-axis
        float playerVelocity = (playerY - previousPlayerY) / deltaTime;
        previousPlayerY = playerY;
        
        // Apply forces
        float springForce = -position * springStrength;
        float gravityForce = gravity * Math.signum(velocity);
        
        // Update velocity with player movement influence
        velocity += springForce - (playerVelocity * 0.1f) - gravityForce;
        velocity *= damping;
        
        // Update position
        position += velocity * deltaTime;
        
        position = Mth.clamp(position, -maxDisplacement, maxDisplacement);   
    }
    
    public float getOffset() {
        return position;
    }
    
    public void reset() {
        velocity = 0.0f;
        position = 0.0f;
    }
    
    
    
    public static class Builder {
    	float springStrength = 0.15f;
    	float damping = 0.85f;
    	float gravity = 0.02f;
    	float maxDisplacement = 0.3f;
    	
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
    	
    	public JigglePhysics build() {
    		return new JigglePhysics(this);
    	}
    }
}
