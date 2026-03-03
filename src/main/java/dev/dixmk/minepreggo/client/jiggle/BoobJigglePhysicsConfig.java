package dev.dixmk.minepreggo.client.jiggle;

import javax.annotation.concurrent.Immutable;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@Immutable
public class BoobJigglePhysicsConfig extends AbstractJigglePhysics.AbstractJigglePhysicsConfig {
	public final float rotationSpringY;
    public final float rotationSpringX;
    public final float rotationSpringZ;
    public final float rotationDamping;
    public final float rotationReturnSpring;
    public final float maxRotationY;
    public final float maxRotationX;
    public final float maxRotationZ;
    public final float movementMultiplier;
    public final float movementDecay;
    public final float oscillationSpeed;
    public final float phaseInfluence;
    public final float sideInfluence;
    public final float asymetricDelay;
    public final float phaseOffset;
    public final float sideMultiplier;
    public final float asymmetricFrequency;
    public final float jumpElevationMin;
    public final float jumpElevationMax;
    public final float jumpElevationSpring;
    public final float jumpElevationDamping;

    public BoobJigglePhysicsConfig(Builder builder) {
        super(builder.springStrength, builder.damping, builder.gravity, builder.maxDisplacement);
        this.rotationSpringY = builder.rotationSpringY;
        this.rotationSpringX = builder.rotationSpringX;
        this.rotationSpringZ = builder.rotationSpringZ;
        this.rotationDamping = builder.rotationDamping;
        this.rotationReturnSpring = builder.rotationReturnSpring;
        this.maxRotationY = builder.maxRotationY;
        this.maxRotationX = builder.maxRotationX;
        this.maxRotationZ = builder.maxRotationZ;
        this.movementMultiplier = builder.movementMultiplier;
        this.movementDecay = builder.movementDecay;
        this.oscillationSpeed = builder.oscillationSpeed;
        this.phaseInfluence = builder.phaseInfluence;
        this.sideInfluence = builder.sideInfluence;
        this.asymetricDelay = builder.asymetricDelay;
        this.phaseOffset = builder.phaseOffset;
        this.sideMultiplier = builder.sideMultiplier;
        this.asymmetricFrequency = builder.asymmetricFrequency;
        this.jumpElevationMin = builder.jumpElevationMin;
        this.jumpElevationMax = builder.jumpElevationMax;
        this.jumpElevationSpring = builder.jumpElevationSpring;
        this.jumpElevationDamping = builder.jumpElevationDamping;
    }
	
    public static Builder builder(float phaseOffset, boolean isLeft) {
		return new Builder(phaseOffset, isLeft);
	}
    
    @OnlyIn(Dist.CLIENT)
    public static class Builder {
    	private float springStrength = 1.5f;
    	private float damping = 0.5f;
    	private float gravity = 0.02f;
    	private float maxDisplacement = 0.3f; 	
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
    	private float jumpElevationMin = 0.4f;
    	private float jumpElevationMax = 0.8f;
    	private float jumpElevationSpring = 0.15f;
    	private float jumpElevationDamping = 0.85f;	          
    	private final float phaseOffset;
    	private final float sideMultiplier;
    	private final float asymmetricFrequency;
	          
    	public Builder(float phaseOffset, boolean isLeft) {
    		this.phaseOffset = phaseOffset;
    		this.sideMultiplier = isLeft ? -1.0f : 1.0f;
    		this.asymmetricFrequency = isLeft ? 1.0f : 1.25f;
    	}
      
    	public BoobJigglePhysicsConfig build() {
    		return new BoobJigglePhysicsConfig(this);
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
      
    	public Builder jumpElevationMin(float jumpElevationMin) {
			this.jumpElevationMin = jumpElevationMin;
			return this;
		}
      
    	public Builder jumpElevationMax(float jumpElevationMax) {			
    		this.jumpElevationMax = jumpElevationMax;
    		return this;
    	}
      
    	public Builder jumpElevationSpring(float jumpElevationSpring) {
    		this.jumpElevationSpring = jumpElevationSpring;
    		return this;
    	}
      
    	public Builder jumpElevationDamping(float jumpElevationDamping) {
    		this.jumpElevationDamping = jumpElevationDamping;
    		return this;
    	}   
	}
}
