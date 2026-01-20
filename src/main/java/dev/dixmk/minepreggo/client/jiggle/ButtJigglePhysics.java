package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButtJigglePhysics extends AbstractJigglePhysics<ButtJigglePhysics.JigglePhysicsConfig> {

	public ButtJigglePhysics(JigglePhysicsConfig jigglePhysicsConfig) {
		super(jigglePhysicsConfig);
	}
	
	public ButtJigglePhysics(Builder builder) {
		this(new JigglePhysicsConfig(builder));
	}
	
	public static ButtJigglePhysics.Builder builder() {
		return new ButtJigglePhysics.Builder();
	}
	
    public static void setupAnim(LivingEntity entity, ButtJigglePhysics leftButtJiggle, ButtJigglePhysics rightButtJiggle, ModelPart leftButt, ModelPart rightButt) {       
        float deltaTime = 0.05f; 
        leftButtJiggle.update((float)entity.getY(), deltaTime);
        
        leftButt.y = leftButtJiggle.config.originalYPos + leftButtJiggle.getOffset();      
        
        rightButtJiggle.update((float)entity.getY(), deltaTime);
        rightButt.y = rightButtJiggle.getOffset();
    
        rightButt.y = rightButtJiggle.config.originalYPos + rightButtJiggle.getOffset();
    }
	
    @OnlyIn(Dist.CLIENT)
    public static class Builder {
    	private float springStrength = 0.15f;
    	private float damping = 0.85f;
    	private float gravity = 0.02f;
    	private float maxDisplacement = 0.3f;
    	private float originalYPos = 2.0f;
        
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
    	
    	public Builder originalYPos(float originalYPos) {
    		this.originalYPos = originalYPos;
    		return this;
    	}
    	
    	public ButtJigglePhysics build() {
    		return new ButtJigglePhysics(this);
    	}
    }
    
    @OnlyIn(Dist.CLIENT)
    public static class JigglePhysicsConfig extends AbstractJigglePhysics.AbstractJigglePhysicsConfig {
		public JigglePhysicsConfig(Builder builder) {
			super(builder.springStrength, builder.damping, builder.gravity, builder.maxDisplacement, builder.originalYPos);
		}
	}
}
