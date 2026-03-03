package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractJigglePhysics<E extends AbstractJigglePhysics.AbstractJigglePhysicsConfig> {
    protected float velocity = 0.0f;
    protected float position = 0.0f;
    protected float previousPlayerY = 0.0f;
    
    protected final JigglePhysicsConfig<E> physicsConfig;
	
	protected AbstractJigglePhysics(JigglePhysicsConfig<E> physicsConfig) {
		this.physicsConfig = physicsConfig;
	}
	
	public void update(float playerY, float deltaTime) {
        // Calculate player velocity on Y-axis
        float playerVelocity = (playerY - previousPlayerY) / deltaTime;
        previousPlayerY = playerY;
        
        // Apply forces
        float springForce = -position * physicsConfig.config.springStrength;
        float gravityForce = physicsConfig.config.gravity * Math.signum(velocity);
        
        // Update velocity with player movement influence
        velocity += springForce - (playerVelocity * 0.1f) - gravityForce;
        velocity *= physicsConfig.config.damping;
        
        // Update position
        position += velocity * deltaTime;
        
        position = Mth.clamp(position, -physicsConfig.config.maxDisplacement, physicsConfig.config.maxDisplacement);   
    }
    
    public float getOffset() {
        return position;
    }
    
    public void reset() {
        velocity = 0.0f;
        position = 0.0f;
    }
    
    @OnlyIn(Dist.CLIENT)
    public abstract static class AbstractJigglePhysicsConfig {
		public final float springStrength;
		public final float damping;
		public final float gravity;
		public final float maxDisplacement;
		
		protected AbstractJigglePhysicsConfig(float springStrength, float damping, float gravity, float maxDisplacement) {
			this.springStrength = springStrength;
			this.damping = damping;
			this.gravity = gravity;
			this.maxDisplacement = maxDisplacement;
		}
	}
}
