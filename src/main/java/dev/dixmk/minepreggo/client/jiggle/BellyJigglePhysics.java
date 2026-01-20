package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BellyJigglePhysics extends AbstractJigglePhysics<BellyJigglePhysics.JigglePhysicsConfig> {
    private float rotationVelocity = 0.0f;
    private float rotation = 0.0f;
	
    private double previousPlayerX = 0.0;
    private double previousPlayerZ = 0.0;
    private float movementIntensity = 0.0f;
	
	protected BellyJigglePhysics(JigglePhysicsConfig config) {
		super(config);
	}
	
	public BellyJigglePhysics(Builder builder) {
		this(new JigglePhysicsConfig(builder));
	}

	public static BellyJigglePhysics.Builder builder() {
		return new BellyJigglePhysics.Builder();
	}
	
    public void update(float playerY, double playerX, double playerZ, float deltaTime, boolean isMoving) {
        // Calculate player velocity on Y-axis
        float playerVelocityY = (playerY - previousPlayerY) / deltaTime;
        previousPlayerY = playerY;
        
        // Calculate horizontal movement
        double deltaX = playerX - previousPlayerX;
        double deltaZ = playerZ - previousPlayerZ;
        float horizontalMovement = (float) Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        previousPlayerX = playerX;
        previousPlayerZ = playerZ;
        
        // Update movement intensity with decay
        if (isMoving && horizontalMovement > 0.001f) {
            movementIntensity = Math.min(1.0f, movementIntensity + horizontalMovement * config.movementMultiplier);
        } else {
            movementIntensity *= config.movementDecay;
        }
        
        // Apply forces to vertical position
        float springForce = -position * config.springStrength;
        float gravityForce = config.gravity * Math.signum(velocity);
        float movementForce = movementIntensity * 0.05f * (float) Math.sin(System.currentTimeMillis() * 0.01);
        
        // Update velocity with player movement influence
        velocity += springForce - (playerVelocityY * 0.1f) - gravityForce + movementForce;
        velocity *= config.damping;
        
        // Update position
        position += velocity * deltaTime;
        position = Math.max(-config.maxDisplacement, Math.min(config.maxDisplacement, position));
        
        // Calculate rotation based on velocity and movement
        float targetRotation = velocity * 0.5f + (movementIntensity * 0.2f * (float) Math.cos(System.currentTimeMillis() * 0.008));
        float rotationSpringForce = (targetRotation - rotation) * config.rotationSpring;
        
        rotationVelocity += rotationSpringForce;
        rotationVelocity *= config.rotationDamping;
        
        rotation += rotationVelocity * deltaTime;
        rotation = Math.max(-config.maxRotation, Math.min(config.maxRotation, rotation));
    }
    
    public float getRotation() {
        return rotation;
    }
    
    public float getMovementIntensity() {
        return movementIntensity;
    }
    
    @Override
    public void reset() {
    	super.reset();
        rotationVelocity = 0.0f;
        rotation = 0.0f;
        movementIntensity = 0.0f;
    }
    
    public void setupAnim(LivingEntity entity, ModelPart bellyModel, boolean simple) {			
		// Update jiggle physics with movement data
		float deltaTime = 0.05f; // Approximate frame time
				
		if (!simple) {
			this.update((float) entity.getY(), entity.getX(), entity.getZ(), deltaTime, entity.walkAnimation.isMoving());
			bellyModel.y = config.originalYPos + this.getOffset();	
			bellyModel.yRot = this.getRotation();
		}
		else {
			this.update((float) entity.getY(), deltaTime);
			bellyModel.y = config.originalYPos + this.getOffset();	
		}
	}
    
    
    @OnlyIn(Dist.CLIENT)
    public static class Builder {
    	private float springStrength = 0.15f;
    	private float damping = 0.85f;
    	private float gravity = 0.02f;
    	private float maxDisplacement = 0.3f;
        protected float originalYPos = 2.0f;
        
        private float rotationSpring = 0.2f;
        private float rotationDamping = 0.88f;
        private float maxRotation = 0.125f;
        
        private float movementMultiplier = 0.3f;
        private float movementDecay = 0.92f;
    	  
    	
    	public BellyJigglePhysics build() {
    		return new BellyJigglePhysics(this);
    	}
        
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
    	
    	public Builder originalYPos(float originalYPos) {
    		this.originalYPos = originalYPos;
    		return this;
    	}
	
    	public Builder maxDisplacement(float maxDisplacement) {
    		this.maxDisplacement = maxDisplacement;
    		return this;
    	}

        public Builder rotationSpring(float rotationSpring) {
        	this.rotationSpring = rotationSpring;
        	return this;
        }
        
        public Builder rotationDamping(float rotationDamping) {
        	this.rotationDamping = rotationDamping;
        	return this;
        }
        
        public Builder maxRotation(float maxRotation) {
        	this.maxRotation = maxRotation;
        	return this;
        }
        
        public Builder movementMultiplier(float movementMultiplier) {
        	this.movementMultiplier = movementMultiplier;
        	return this;
        }
        
        public Builder movementDecay(float movementDecay) {
        	this.movementDecay = movementDecay;
        	return this;
        }       
    }
    
    @OnlyIn(Dist.CLIENT)
    public static class JigglePhysicsConfig extends AbstractJigglePhysics.AbstractJigglePhysicsConfig {
        public final float rotationSpring;
        public final float rotationDamping;
        public final float maxRotation;
        public final float movementMultiplier;
        public final float movementDecay;

        public JigglePhysicsConfig(Builder builder) {
            super(builder.springStrength, builder.damping, builder.gravity, builder.maxDisplacement, builder.originalYPos);
            this.rotationSpring = builder.rotationSpring;
            this.rotationDamping = builder.rotationDamping;
            this.maxRotation = builder.maxRotation;
            this.movementMultiplier = builder.movementMultiplier;
            this.movementDecay = builder.movementDecay;
        }
    }
}
