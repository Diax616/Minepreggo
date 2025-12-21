package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractJigglePhysics {
    protected float velocity = 0.0f;
    protected float position = 0.0f;
    protected float previousPlayerY = 0.0f;
    
    protected final float springStrength;
    protected final float damping;
    protected final float gravity;
    protected final float maxDisplacement;
    
	// TODO: additionalYPos provokes bugs in Y axis position, it does not respect the original position defined in the model during animations, need to be fixed or removed
    protected final float additionalYPos;
    
	protected AbstractJigglePhysics(float springStrength, float damping, float gravity, float maxDisplacement, float additionalYPos) {
		this.springStrength = springStrength;
		this.damping = damping;
		this.gravity = gravity;
		this.maxDisplacement = maxDisplacement;	
		this.additionalYPos = additionalYPos;
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
}
