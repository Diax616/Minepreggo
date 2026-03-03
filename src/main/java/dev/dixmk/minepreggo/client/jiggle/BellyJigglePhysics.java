package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BellyJigglePhysics extends AbstractJigglePhysics<BellyJigglePhysicsConfig> {
    private float rotationVelocity = 0.0f;
    private float rotation = 0.0f;	
    private double previousPlayerX = 0.0;
    private double previousPlayerZ = 0.0;
    private float movementIntensity = 0.0f;
    private float oscillationTime = 0.0f;
    private Cache cache = new Cache(3);
	
	public BellyJigglePhysics(float originalYPos, BellyJigglePhysicsConfig physicsConfig) {
		super(new JigglePhysicsConfig<>(originalYPos, physicsConfig));
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
            movementIntensity = Math.min(1.0f, movementIntensity + horizontalMovement * physicsConfig.config.movementMultiplier);
            oscillationTime += physicsConfig.config.oscillationSpeed * Math.max(movementIntensity, Math.abs(playerVelocityY) * 2.0f);
        } else {
            movementIntensity *= physicsConfig.config.movementDecay;
        }
        
        float phaseTime = oscillationTime * physicsConfig.config.asymmetricFrequency + physicsConfig.config.phaseOffset;
        
        cache.tick();
        if (cache.shouldUpdate()) {
            cache.refreshTrigCache(phaseTime);
        }
        
        // Apply forces to vertical position
        float springForce = -position * physicsConfig.config.springStrength;
        float gravityForce = physicsConfig.config.gravity * Math.signum(velocity);
        float movementForce = movementIntensity * 0.05f * cache.sinTime;
        
        // Update velocity with player movement influence
        velocity += springForce - (playerVelocityY * 0.1f) - gravityForce + movementForce;
        velocity *= physicsConfig.config.damping;
        
        // Update position
        position += velocity * deltaTime;
        position = Math.max(-physicsConfig.config.maxDisplacement, Math.min(physicsConfig.config.maxDisplacement, position));
        
        // Calculate rotation based on velocity and movement
        float targetRotation = velocity * 0.5f + (movementIntensity * 0.2f * cache.cosTime);
        float rotationSpringForce = (targetRotation - rotation) * physicsConfig.config.rotationSpring;
        
        rotationVelocity += rotationSpringForce;
        rotationVelocity *= physicsConfig.config.rotationDamping;
        
        rotation += rotationVelocity * deltaTime;
        rotation = Math.max(-physicsConfig.config.maxRotation, Math.min(physicsConfig.config.maxRotation, rotation));
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
			bellyModel.y = physicsConfig.originalYPos + this.getOffset();	
			bellyModel.yRot = this.getRotation();
		}
		else {
			this.update((float) entity.getY(), deltaTime);
			bellyModel.y = physicsConfig.originalYPos + this.getOffset();	
		}
	}
    
    @OnlyIn(Dist.CLIENT)
    private class Cache extends JigglePhysicsCache {
        float sinTime;   // Math.sin(currentTimeMillis * 0.01)
        float cosTime;   // Math.cos(currentTimeMillis * 0.008)
        
        private float lastPhaseTime = Float.NaN;

        public Cache(int ticksForUpdate) {
            super(ticksForUpdate);
        }

        @Override
        public void refreshTrigCache() {
            refreshTrigCache(lastPhaseTime);
        }

        public void refreshTrigCache(float phaseTime) {
            this.lastPhaseTime = phaseTime;
            sinTime = (float) Math.sin(phaseTime);
            cosTime = (float) Math.cos(phaseTime * 0.8);
        }
    }
}
