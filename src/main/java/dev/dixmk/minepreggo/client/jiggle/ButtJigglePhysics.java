package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButtJigglePhysics extends AbstractJigglePhysics<ButtJigglePhysicsConfig> {

	public ButtJigglePhysics(JigglePhysicsConfig<ButtJigglePhysicsConfig> jigglePhysicsConfig) {
		super(jigglePhysicsConfig);
	}
	
	public ButtJigglePhysics(float originalYPos, ButtJigglePhysicsConfig physicsConfig) {
		this(new JigglePhysicsConfig<>(originalYPos, physicsConfig));
	}
	
    public static void setupAnim(LivingEntity entity, ButtJigglePhysics leftButtJiggle, ButtJigglePhysics rightButtJiggle, ModelPart leftButt, ModelPart rightButt) {       
        float deltaTime = 0.05f; 
        leftButtJiggle.update((float)entity.getY(), deltaTime);
        
        leftButt.y = leftButtJiggle.physicsConfig.originalYPos + leftButtJiggle.getOffset();      
        
        rightButtJiggle.update((float)entity.getY(), deltaTime);
        rightButt.y = rightButtJiggle.getOffset();
    
        rightButt.y = rightButtJiggle.physicsConfig.originalYPos + rightButtJiggle.getOffset();
    }
}
