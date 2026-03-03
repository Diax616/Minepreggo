package dev.dixmk.minepreggo.client.jiggle;

import javax.annotation.concurrent.Immutable;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@Immutable
public class BellyJigglePhysicsConfig extends AbstractJigglePhysics.AbstractJigglePhysicsConfig {
    public final float rotationSpring;
    public final float rotationDamping;
    public final float maxRotation;
    public final float movementMultiplier;
    public final float movementDecay;
    public final float oscillationSpeed;
    public final float asymmetricFrequency;
    public final float phaseOffset;
    
    public BellyJigglePhysicsConfig(Builder builder) {
        super(builder.springStrength, builder.damping, builder.gravity, builder.maxDisplacement);
        this.rotationSpring = builder.rotationSpring;
        this.rotationDamping = builder.rotationDamping;
        this.maxRotation = builder.maxRotation;
        this.movementMultiplier = builder.movementMultiplier;
        this.movementDecay = builder.movementDecay;
        this.oscillationSpeed = builder.oscillationSpeed;
        this.asymmetricFrequency = builder.asymmetricFrequency;
        this.phaseOffset = builder.phaseOffset;
    }
    
	public static BellyJigglePhysicsConfig.Builder builder() {
		return new BellyJigglePhysicsConfig.Builder();
	}
    
    @OnlyIn(Dist.CLIENT)
    public static class Builder {
    	private float springStrength = 0.15f;
    	private float damping = 0.85f;
    	private float gravity = 0.02f;
    	private float maxDisplacement = 0.3f;  
        private float rotationSpring = 0.2f;
        private float rotationDamping = 0.88f;
        private float maxRotation = 0.125f;
        private float movementMultiplier = 0.3f;
        private float movementDecay = 0.92f;
    	private float oscillationSpeed = 0.45f;
    	private float asymmetricFrequency = 1.5f;
    	private float phaseOffset = 0.0f;
    	
    	public BellyJigglePhysicsConfig build() {
    		return new BellyJigglePhysicsConfig(this);
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
        
        public Builder oscillationSpeed(float oscillationSpeed) {
        	this.oscillationSpeed = oscillationSpeed;
			return this;
        }
        
        public Builder asymmetricFrequency(float asymmetricFrequency) {
			this.asymmetricFrequency = asymmetricFrequency;
			return this;
		}
		
		public Builder phaseOffset(float phaseOffset) {
			this.phaseOffset = phaseOffset;
			return this;
		}
    }
}
