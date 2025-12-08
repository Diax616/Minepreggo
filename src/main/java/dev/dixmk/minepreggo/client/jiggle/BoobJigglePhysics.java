package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoobJigglePhysics extends AbstractJigglePhysics {	
    private float rotationVelocityY = 0.0f;
    private float rotationY = 0.0f;
    private float rotationVelocityX = 0.0f;
    private float rotationX = 0.0f;
    private float rotationVelocityZ = 0.0f;
    private float rotationZ = 0.0f;
	
    private double previousPlayerX = 0.0;
    private double previousPlayerZ = 0.0;
    private float movementIntensity = 0.0f;
    
    private float oscillationTime = 0.0f;
    
	private final float rotationSpringY;
    private final float rotationSpringX;
    private final float rotationSpringZ;
    private final float rotationDamping; 
    private final float rotationReturnSpring; 
    private final float maxRotationY;	
    private final float maxRotationX;
    private final float maxRotationZ;
    
    private final float movementMultiplier;
    private final float movementDecay;
    private final float oscillationSpeed;

    private final float phaseInfluence;
    private final float sideInfluence;
    private final float asymetricDelay;
    
    private boolean wasInAir = false;
    private float jumpTargetRotationX = 0.0f;
    private float jumpTargetRotationZ = 0.0f;
    private boolean jumpTargetsSet = false;
    
    private final float phaseOffset;
    private final float sideMultiplier;
    private final float asymmetricFrequency;
        
    public BoobJigglePhysics(Builder builder) {
        super(builder.springStrength, builder.damping, builder.gravity, builder.maxDisplacement, builder.additionalYPos);
        this.rotationSpringY = builder.rotationSpringY;
        this.rotationSpringX = builder.rotationSpringX;
        this.rotationSpringZ = builder.rotationSpringZ;
        this.rotationDamping = builder.rotationDamping;
        this.maxRotationY = builder.maxRotationY;
        this.maxRotationX = builder.maxRotationX;
        this.maxRotationZ = builder.maxRotationZ;
        this.movementMultiplier = builder.movementMultiplier;
        this.movementDecay = builder.movementDecay;
        this.oscillationSpeed = builder.oscillationSpeed;
        this.phaseInfluence = builder.phaseInfluence;
        this.sideInfluence = builder.sideInfluence;
        this.phaseOffset = builder.phaseOffset;
        this.sideMultiplier = builder.sideMultiplier;
        this.asymmetricFrequency = builder.asymmetricFrequency;
        this.rotationReturnSpring = builder.rotationReturnSpring;
		this.asymetricDelay = builder.asymetricDelay;
    }
    
	public static BoobJigglePhysics.Builder builder(float phaseOffset, boolean isLeft) {
		return new BoobJigglePhysics.Builder(phaseOffset, isLeft);
	}
    
    public void update(float playerY, double playerX, double playerZ, float deltaTime, boolean isMoving, boolean isOnGround) {

        float playerVelocityY = (playerY - previousPlayerY) / deltaTime;
        previousPlayerY = playerY;
        
        double deltaX = playerX - previousPlayerX;
        double deltaZ = playerZ - previousPlayerZ;
        float horizontalMovement = (float) Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        
        previousPlayerX = playerX;
        previousPlayerZ = playerZ;
        
        boolean hasVerticalMovement = Math.abs(playerVelocityY) > 0.05f;
        boolean isInAir = !isOnGround || hasVerticalMovement;
        
        if (isInAir && !wasInAir) {
            // Generate completely random target rotations (can be any value between -MAX and +MAX)
            jumpTargetRotationX = (float) ((Math.random() * 2.0 - 1.0) * maxRotationX);
            jumpTargetRotationZ = (float) ((Math.random() * 2.0 - 1.0) * maxRotationZ);
            jumpTargetsSet = true;
        }
        
        if (!isInAir && wasInAir) {
            jumpTargetsSet = false;
        }
        
        wasInAir = isInAir;
        
        if (isMoving && horizontalMovement > 0.001f) {
            movementIntensity = Math.min(1.0f, movementIntensity + horizontalMovement * movementMultiplier);
        } else {
            movementIntensity *= movementDecay;
        }
        
        if (movementIntensity > 0.01f || hasVerticalMovement) {
            oscillationTime += oscillationSpeed * Math.max(movementIntensity, Math.abs(playerVelocityY) * 2.0f);
        }
        
        float phaseTime = oscillationTime * asymmetricFrequency + phaseOffset;
        
        boolean hasAnyMovement = movementIntensity > 0.05f || hasVerticalMovement;
        
        float springForce = -position * springStrength;
        float gravityForce = gravity * Math.signum(velocity);
        
        float movementForce = movementIntensity * 0.08f * (float) Math.sin(phaseTime);

        float asymmetricBounce = phaseInfluence * (float) Math.cos(phaseTime * 1.3f + sideMultiplier * 0.5f) * movementIntensity;

        asymmetricBounce += sideMultiplier * asymetricDelay * (float) Math.sin(phaseTime * 1.5f) * movementIntensity;
        
        velocity += springForce - (playerVelocityY * 0.1f) - gravityForce + movementForce + asymmetricBounce;
        velocity *= damping;
        
        position += velocity * deltaTime;
        position = Math.max(-maxDisplacement, Math.min(maxDisplacement, position));
        
        float targetRotationY;
        if (hasAnyMovement) {
            targetRotationY = movementIntensity * 0.25f * (float) Math.cos(phaseTime * 0.8);
            targetRotationY += velocity * 0.4f;
            targetRotationY += sideMultiplier * sideInfluence * 1.5f * movementIntensity * (float) Math.sin(phaseTime * 1.1);
            targetRotationY += playerVelocityY * 0.3f;
            targetRotationY += sideMultiplier * 0.05f * movementIntensity;
        } else {
            targetRotationY = 0.0f;
        }
        
        float rotationSpringForceY = (targetRotationY - rotationY) * 
                (hasAnyMovement ? rotationSpringY : rotationReturnSpring); 
        rotationVelocityY += rotationSpringForceY;
        rotationVelocityY *= rotationDamping;
        
        rotationY += rotationVelocityY * deltaTime;
        rotationY = Math.max(-maxRotationY, Math.min(maxRotationY, rotationY));
        
        float targetRotationX;
        if (hasAnyMovement) {
            if (jumpTargetsSet && hasVerticalMovement) {
                targetRotationX = jumpTargetRotationX;
                targetRotationX += (float) Math.sin(phaseTime * 2.5) * Math.abs(playerVelocityY) * 0.15f;
                targetRotationX += sideMultiplier * asymetricDelay * (float) Math.cos(phaseTime * 2.0) * Math.abs(playerVelocityY) * 0.2f;
            } else {
                targetRotationX = movementIntensity * 0.2f * (float) Math.sin(phaseTime * 1.2);
                targetRotationX += playerVelocityY * 0.25f;
                targetRotationX += sideMultiplier * sideInfluence * 0.8f * (float) Math.cos(phaseTime * 0.95);
                
                if (hasVerticalMovement && !jumpTargetsSet) {
                    targetRotationX += (float) Math.sin(phaseTime * 1.5) * Math.abs(playerVelocityY) * 0.4f;
                    targetRotationX += sideMultiplier * asymetricDelay * (float) Math.cos(phaseTime * 1.8) * Math.abs(playerVelocityY);
                }
            }
        } else {
            targetRotationX = 0.0f;
        }
        
        float rotationSpringForceX = (targetRotationX - rotationX) * 
            (hasAnyMovement ? rotationSpringX : rotationReturnSpring);
        rotationVelocityX += rotationSpringForceX;
        rotationVelocityX *= rotationDamping;
        
        rotationX += rotationVelocityX * deltaTime;
        rotationX = Math.max(-maxRotationX, Math.min(maxRotationX, rotationX));
        
        float targetRotationZ;
        if (hasAnyMovement) {
            if (jumpTargetsSet && hasVerticalMovement) {
                targetRotationZ = jumpTargetRotationZ;
                targetRotationZ += sideMultiplier * (float) Math.cos(phaseTime * 2.3) * Math.abs(playerVelocityY) * 0.18f;
                targetRotationZ += sideMultiplier * asymetricDelay * (float) Math.sin(phaseTime * 2.2) * Math.abs(playerVelocityY) * 0.25f;
            } else {
                targetRotationZ = movementIntensity * 0.22f * (float) Math.cos(phaseTime * 1.05);
                targetRotationZ += sideMultiplier * velocity * 0.6f;
                targetRotationZ += sideMultiplier * sideInfluence * 1.5f * (float) Math.sin(phaseTime * 1.15);
                
                if (hasVerticalMovement && !jumpTargetsSet) {
                    targetRotationZ += sideMultiplier * (float) Math.cos(phaseTime * 1.3) * Math.abs(playerVelocityY) * 0.5f;
                    targetRotationZ += sideMultiplier * asymetricDelay * 2.0f * (float) Math.sin(phaseTime * 1.6) * Math.abs(playerVelocityY);
                }
            }
        } else {
            targetRotationZ = 0.0f;
        }
        
        float rotationSpringForceZ = (targetRotationZ - rotationZ) * 
            (hasAnyMovement ? rotationSpringZ : rotationReturnSpring);
        rotationVelocityZ += rotationSpringForceZ;
        rotationVelocityZ *= rotationDamping;
        
        rotationZ += rotationVelocityZ * deltaTime;
        rotationZ = Math.max(-maxRotationZ, Math.min(maxRotationZ, rotationZ));
    }
    
    public float getRotationY() {
        return rotationY;
    }
    
    public float getRotationX() {
        return rotationX;
    }
    
    public float getRotationZ() {
        return rotationZ;
    }
    
    public float getMovementIntensity() {
        return movementIntensity;
    }

    @Override
    public void reset() {
    	super.reset();
        rotationVelocityY = 0.0f;
        rotationY = 0.0f;
        rotationVelocityX = 0.0f;
        rotationX = 0.0f;
        rotationVelocityZ = 0.0f;
        rotationZ = 0.0f;
        movementIntensity = 0.0f;
        oscillationTime = 0f;
        wasInAir = false;
        jumpTargetRotationX = 0.0f;
        jumpTargetRotationZ = 0.0f;
        jumpTargetsSet = false;
    }
	
    public static void setupAnim(LivingEntity entity, BoobJigglePhysics leftJiggle, BoobJigglePhysics rightJiggle,
    		ModelPart boobParent, ModelPart leftBoob, ModelPart rightBoob) {
        
        float deltaTime = 0.05f;
        
        // Update physics for both boobs independently
        leftJiggle.update(
            (float) entity.getY(), 
            entity.getX(), 
            entity.getZ(), 
            deltaTime, 
            entity.walkAnimation.isMoving(),
            entity.onGround()
        );
        
        rightJiggle.update(
            (float) entity.getY(), 
            entity.getX(), 
            entity.getZ(), 
            deltaTime, 
            entity.walkAnimation.isMoving(),
            entity.onGround()
        );
        
        // Calculate average offset for parent bone
        float avgOffset = (leftJiggle.getOffset() + rightJiggle.getOffset()) * 0.5f;
        boobParent.y = leftJiggle.additionalYPos + avgOffset;
        
        // Apply averaged rotations to parent bone
        float avgRotY = (leftJiggle.getRotationY() + rightJiggle.getRotationY()) * 0.5f;
        float avgRotX = (leftJiggle.getRotationX() + rightJiggle.getRotationX()) * 0.5f;
        float avgRotZ = (leftJiggle.getRotationZ() + rightJiggle.getRotationZ()) * 0.5f;
        
        boobParent.yRot = avgRotY;
        boobParent.xRot = avgRotX;
        boobParent.zRot = avgRotZ;
        
        // Apply individual asymmetric rotations to each boob
        // Left boob
        leftBoob.y = leftJiggle.getOffset() * 0.4f; // Increased from 0.3f
        leftBoob.yRot = leftJiggle.getRotationY() * 0.5f; // Increased from 0.4f
        leftBoob.xRot = leftJiggle.getRotationX() * 0.6f; // Increased from 0.5f
        leftBoob.zRot = leftJiggle.getRotationZ() * 0.7f; // Increased from 0.6f
        
        // Right boob - stronger individual influence with opposite emphasis
        rightBoob.y = rightJiggle.getOffset() * 0.4f;
        rightBoob.yRot = rightJiggle.getRotationY() * 0.5f;
        rightBoob.xRot = rightJiggle.getRotationX() * 0.6f;
        rightBoob.zRot = rightJiggle.getRotationZ() * 0.7f;
    }
      
    @OnlyIn(Dist.CLIENT)
    public static class Builder {
    	private float springStrength = 0.15f;
    	private float damping = 0.5f;
    	private float gravity = 0.02f;
    	private float maxDisplacement = 0.3f;
    	private float additionalYPos = 2.0f;
    	
        private float rotationSpringY = 0.25f;
        private float rotationSpringX = 0.23f;
        private float rotationSpringZ = 0.27f;
        private float rotationDamping = 0.88f; 
        private final float rotationReturnSpring = 0.35f; 
        private float maxRotationY = 0.15f;	
        private float maxRotationX = 0.12f;
        private float maxRotationZ = 0.18f;
        
        private float movementMultiplier = 0.5f;
        private float movementDecay = 0.9f;
        private float oscillationSpeed = 0.15f;
        
        private float phaseInfluence = 0.20f;
        private float sideInfluence = 0.15f;
        private float asymetricDelay = 0.08f;
        
        private final float phaseOffset;
        private final float sideMultiplier;
        private final float asymmetricFrequency;
            
        public Builder(float phaseOffset, boolean isLeft) {
        	this.phaseOffset = phaseOffset;
            this.sideMultiplier = isLeft ? -1.0f : 1.0f;
            this.asymmetricFrequency = isLeft ? 1.0f : 1.15f;
        }
        
    	public BoobJigglePhysics build() {
    		return new BoobJigglePhysics(this);
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
    	
    	public Builder additionalYPos(float additionalYPos) {
    		this.additionalYPos = additionalYPos;
    		return this;
    	}
    	
    	public Builder oscillationSpeed(float oscillationSpeed) {
    		this.oscillationSpeed = oscillationSpeed;
    		return this;
    	}

    	public Builder maxDisplacement(float maxDisplacement) {
    		this.maxDisplacement = maxDisplacement;
    		return this;
    	}
    	
        public Builder rotationSpringY(float rotationSpringY) {
        	this.rotationSpringY = rotationSpringY;
        	return this;
        }
        
        public Builder rotationSpringX(float rotationSpringX) {
        	this.rotationSpringX = rotationSpringX;
        	return this;
        }
        
        public Builder rotationSpringZ(float rotationSpringZ) {
        	this.rotationSpringZ = rotationSpringZ;
        	return this;
        }
        
        public Builder rotationDamping(float rotationDamping) {
        	this.rotationDamping = rotationDamping;
        	return this;
        }
        
        public Builder maxRotationY(float maxRotationY) {
        	this.maxRotationY = maxRotationY;
        	return this;
        }
        
        public Builder maxRotationX(float maxRotationX) {
        	this.maxRotationX = maxRotationX;
        	return this;
        }
        
        public Builder maxRotationZ(float maxRotationZ) {
        	this.maxRotationZ = maxRotationZ;
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
        
        public Builder phaseInfluence(float phaseInfluence) {
        	this.phaseInfluence = phaseInfluence;
        	return this;
        } 
        
        public Builder sideInfluence(float sideInfluence) {
        	this.sideInfluence = sideInfluence;
        	return this;
        } 
        
        public Builder asymetricDelay(float asymetricDelay) {
        	this.asymetricDelay = asymetricDelay;
        	return this;
        } 
    }
}