package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoobJigglePhysics extends AbstractJigglePhysics<BoobJigglePhysicsConfig> {	
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
    private boolean wasInAir = false;
    private float jumpTargetRotationX = 0.0f;
    private float jumpTargetRotationZ = 0.0f;
    private boolean jumpTargetsSet = false;
    private float jumpElevationTarget = 0.0f;
    private float jumpElevationCurrent = 0.0f;    
    private boolean axisX = true;
    private boolean axisZ = true;           
    private Cache cache = new Cache(3);
    
    public BoobJigglePhysics(float originalYPos, BoobJigglePhysicsConfig physicsConfig) {
    	super(new JigglePhysicsConfig<>(originalYPos, physicsConfig));
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
            jumpTargetRotationX = (float) ((Math.random() * 2.0 - 1.0) * physicsConfig.config.maxRotationX);
            jumpTargetRotationZ = (float) ((Math.random() * 2.0 - 1.0) * physicsConfig.config.maxRotationZ);
            jumpTargetsSet = true;
  
            jumpElevationTarget = physicsConfig.config.jumpElevationMin + (float) (Math.random() * (physicsConfig.config.jumpElevationMax - physicsConfig.config.jumpElevationMin));
        }
        
        if (!isInAir && wasInAir) {
            jumpTargetsSet = false;
            jumpElevationTarget = 0.0f; // Return to zero on landing
        }
        
        wasInAir = isInAir;
        
        if (isMoving && horizontalMovement > 0.001f) {
            movementIntensity = Math.min(1.0f, movementIntensity + horizontalMovement * physicsConfig.config.movementMultiplier);
        } else {
            movementIntensity *= physicsConfig.config.movementDecay;
        }
        
        if (movementIntensity > 0.01f || hasVerticalMovement) {
            oscillationTime += physicsConfig.config.oscillationSpeed * Math.max(movementIntensity, Math.abs(playerVelocityY) * 2.0f);
        }
        
        float elevationSpringForce = (jumpElevationTarget - jumpElevationCurrent) * physicsConfig.config.jumpElevationSpring;
        jumpElevationCurrent += elevationSpringForce;
        jumpElevationCurrent *= physicsConfig.config.jumpElevationDamping;

        float phaseTime = oscillationTime * physicsConfig.config.asymmetricFrequency + physicsConfig.config.phaseOffset;
       
    	cache.tick();
    	if (cache.shouldUpdate()) {
    	    cache.refreshTrigCache(phaseTime);
    	}
           
        boolean hasAnyMovement = movementIntensity > 0.05f || hasVerticalMovement;
        float springForce = -position * physicsConfig.config.springStrength;
        float gravityForce = physicsConfig.config.gravity * Math.signum(velocity);    
        float movementForce = movementIntensity * 0.08f * cache.sinPhase;
        float asymmetricBounce = physicsConfig.config.phaseInfluence * cache.cosPhase13 * movementIntensity;

        asymmetricBounce += physicsConfig.config.sideMultiplier * physicsConfig.config.asymetricDelay * cache.sinPhase15 * movementIntensity;
        
        velocity += springForce - (playerVelocityY * 0.1f) - gravityForce + movementForce + asymmetricBounce;
        velocity *= physicsConfig.config.damping;
        
        position += velocity * deltaTime;
        position = Math.max(-physicsConfig.config.maxDisplacement, Math.min(physicsConfig.config.maxDisplacement, position));
        
        float targetRotationY;
        if (hasAnyMovement) {
            targetRotationY = movementIntensity * 0.25f * cache.cosPhase08;
            targetRotationY += velocity * 0.4f;
            targetRotationY += physicsConfig.config.sideMultiplier * physicsConfig.config.sideInfluence * 1.5f * movementIntensity * cache.sinPhase11;
            targetRotationY += playerVelocityY * 0.3f;
            targetRotationY += physicsConfig.config.sideMultiplier * 0.05f * movementIntensity;
        } else {
            targetRotationY = 0.0f;
        }
        
        float rotationSpringForceY = (targetRotationY - rotationY) * (hasAnyMovement ? physicsConfig.config.rotationSpringY : physicsConfig.config.rotationReturnSpring); 
        rotationVelocityY += rotationSpringForceY;
        rotationVelocityY *= physicsConfig.config.rotationDamping;
        
        rotationY += rotationVelocityY * deltaTime;
        rotationY = Math.max(-physicsConfig.config.maxRotationY, Math.min(physicsConfig.config.maxRotationY, rotationY));
        
        if (axisX) {
        	updateAxisX(deltaTime, playerVelocityY, hasAnyMovement, hasVerticalMovement);
        }
        if (axisZ) {
        	updateAxisZ(deltaTime, playerVelocityY, hasAnyMovement, hasVerticalMovement);
        }
    }
    
    private void updateAxisX(float deltaTime, float playerVelocityY, boolean hasAnyMovement, boolean hasVerticalMovement) {
        float targetRotationX;
        if (hasAnyMovement) {
            if (jumpTargetsSet && hasVerticalMovement) {
                targetRotationX  = jumpTargetRotationX;
                targetRotationX += cache.sinPhase25 * Math.abs(playerVelocityY) * 0.15f;
                targetRotationX += physicsConfig.config.sideMultiplier * physicsConfig.config.asymetricDelay * cache.cosPhase20 * Math.abs(playerVelocityY) * 0.2f;
            } else {
                targetRotationX  = movementIntensity * 0.2f * cache.sinPhase12;
                targetRotationX += playerVelocityY * 0.25f;
                targetRotationX += physicsConfig.config.sideMultiplier * physicsConfig.config.sideInfluence * 0.8f * cache.cosPhase095;
                if (hasVerticalMovement) {
                    targetRotationX += cache.sinPhase15ax * Math.abs(playerVelocityY) * 0.4f;
                    targetRotationX += physicsConfig.config.sideMultiplier * physicsConfig.config.asymetricDelay * cache.cosPhase18 * Math.abs(playerVelocityY);
                }
            }
        } else {
            targetRotationX = 0.0f;
        }
        
        float rotationSpringForceX = (targetRotationX - rotationX) * 
            (hasAnyMovement ? physicsConfig.config.rotationSpringX : physicsConfig.config.rotationReturnSpring);
        rotationVelocityX += rotationSpringForceX;
        rotationVelocityX *= physicsConfig.config.rotationDamping;
        
        rotationX += rotationVelocityX * deltaTime;
        rotationX = Math.max(-physicsConfig.config.maxRotationX, Math.min(physicsConfig.config.maxRotationX, rotationX));
    }
    
    private void updateAxisZ(float deltaTime, float playerVelocityY, boolean hasAnyMovement, boolean hasVerticalMovement) {
        float targetRotationZ;
        if (hasAnyMovement) {
            if (jumpTargetsSet && hasVerticalMovement) {
                targetRotationZ  = jumpTargetRotationZ;
                targetRotationZ += physicsConfig.config.sideMultiplier * cache.cosPhase23 * Math.abs(playerVelocityY) * 0.18f;
                targetRotationZ += physicsConfig.config.sideMultiplier * physicsConfig.config.asymetricDelay * cache.sinPhase22 * Math.abs(playerVelocityY) * 0.25f;
            } else {
                targetRotationZ  = movementIntensity * 0.22f * cache.cosPhase105;
                targetRotationZ += physicsConfig.config.sideMultiplier * velocity * 0.6f;
                targetRotationZ += physicsConfig.config.sideMultiplier * physicsConfig.config.sideInfluence * 1.5f * cache.sinPhase115;
                if (hasVerticalMovement) {
                    targetRotationZ += physicsConfig.config.sideMultiplier * cache.cosPhase13z * Math.abs(playerVelocityY) * 0.5f;
                    targetRotationZ += physicsConfig.config.sideMultiplier * physicsConfig.config.asymetricDelay * 2.0f * cache.sinPhase16 * Math.abs(playerVelocityY);
                }
            }
        } else {
            targetRotationZ = 0.0f;
        }
        
        float rotationSpringForceZ = (targetRotationZ - rotationZ) * 
            (hasAnyMovement ? physicsConfig.config.rotationSpringZ : physicsConfig.config.rotationReturnSpring);
        rotationVelocityZ += rotationSpringForceZ;
        rotationVelocityZ *= physicsConfig.config.rotationDamping;
        
        rotationZ += rotationVelocityZ * deltaTime;
        rotationZ = Math.max(-physicsConfig.config.maxRotationZ, Math.min(physicsConfig.config.maxRotationZ, rotationZ));
    }
    
    @Override
    public float getOffset() {
    	return position - jumpElevationCurrent;
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
	
    public void useAxisX(boolean axisX) {
    	this.axisX = axisX;
    }
    
    public void useAxisZ(boolean axisZ) {
    	this.axisZ = axisZ;
    }
    
    public static void setupAnim(LivingEntity entity, BoobJigglePhysics leftJiggle, BoobJigglePhysics rightJiggle,
    		ModelPart boobParent, ModelPart leftBoob, ModelPart rightBoob) {
        
        float deltaTime = 0.05f;
        boolean axisX = leftJiggle.axisX && rightJiggle.axisX;
        boolean axisZ = leftJiggle.axisZ && rightJiggle.axisZ;
        
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
        boobParent.y = leftJiggle.physicsConfig.originalYPos + avgOffset;
        
        // Apply averaged rotations to parent bone
        float avgRotY = (leftJiggle.getRotationY() + rightJiggle.getRotationY()) * 0.5f;
        
        boobParent.yRot = avgRotY; 
        leftBoob.y = leftJiggle.getOffset() * 0.4f;
        leftBoob.yRot = leftJiggle.getRotationY() * 0.5f;      
        rightBoob.y = rightJiggle.getOffset() * 0.4f;
        rightBoob.yRot = rightJiggle.getRotationY() * 0.5f;
               
        if (axisX) {
            float avgRotX = (leftJiggle.getRotationX() + rightJiggle.getRotationX()) * 0.5f;
            boobParent.xRot = avgRotX;
            leftBoob.xRot = leftJiggle.getRotationX() * 0.6f;
            rightBoob.xRot = rightJiggle.getRotationX() * 0.6f;
        }      
        if (axisZ) {
            float avgRotZ = (leftJiggle.getRotationZ() + rightJiggle.getRotationZ()) * 0.5f;
            boobParent.zRot = avgRotZ;
            leftBoob.zRot = leftJiggle.getRotationZ() * 0.7f;
            rightBoob.zRot = rightJiggle.getRotationZ() * 0.7f;
        }
    }

    @OnlyIn(Dist.CLIENT)
    private class Cache extends JigglePhysicsCache {
        // update() direct
    	private float sinPhase;
    	private float cosPhase13;       // phaseTime * 1.3f + sideMultiplier * 0.5f
    	private float sinPhase15;       // phaseTime * 1.5f
    	private float cosPhase08;       // phaseTime * 0.8
    	private float sinPhase11;       // phaseTime * 1.1
        // updateAxisX
    	private float sinPhase12;       // phaseTime * 1.2
    	private float cosPhase095;      // phaseTime * 0.95
    	private float sinPhase25;       // phaseTime * 2.5  (jumpTargetsSet)
    	private float cosPhase20;       // phaseTime * 2.0  (jumpTargetsSet)
    	private float sinPhase15ax;     // phaseTime * 1.5  (hasVerticalMovement)
    	private float cosPhase18;       // phaseTime * 1.8  (hasVerticalMovement)
        // updateAxisZ
    	private float cosPhase105;      // phaseTime * 1.05
    	private float sinPhase115;      // phaseTime * 1.15
    	private float cosPhase23;       // phaseTime * 2.3  (jumpTargetsSet)
    	private float sinPhase22;       // phaseTime * 2.2  (jumpTargetsSet)
    	private float cosPhase13z;      // phaseTime * 1.3  (hasVerticalMovement)
    	private float sinPhase16;       // phaseTime * 1.6  (hasVerticalMovement)
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
            sinPhase     = (float) Math.sin(phaseTime);
            cosPhase13   = (float) Math.cos(phaseTime * 1.3f + physicsConfig.config.sideMultiplier * 0.5f);
            sinPhase15   = (float) Math.sin(phaseTime * 1.5f);
            cosPhase08   = (float) Math.cos(phaseTime * 0.8);
            sinPhase11   = (float) Math.sin(phaseTime * 1.1);

            if (axisX) {
                sinPhase12   = (float) Math.sin(phaseTime * 1.2);
                cosPhase095  = (float) Math.cos(phaseTime * 0.95);
                sinPhase25   = (float) Math.sin(phaseTime * 2.5);
                cosPhase20   = (float) Math.cos(phaseTime * 2.0);
                sinPhase15ax = (float) Math.sin(phaseTime * 1.5);
                cosPhase18   = (float) Math.cos(phaseTime * 1.8);
			}

            if (axisZ) {
                cosPhase105  = (float) Math.cos(phaseTime * 1.05);
                sinPhase115  = (float) Math.sin(phaseTime * 1.15);
                cosPhase23   = (float) Math.cos(phaseTime * 2.3);
                sinPhase22   = (float) Math.sin(phaseTime * 2.2);
                cosPhase13z  = (float) Math.cos(phaseTime * 1.3);
                sinPhase16   = (float) Math.sin(phaseTime * 1.6);
            }
        }
	}
}