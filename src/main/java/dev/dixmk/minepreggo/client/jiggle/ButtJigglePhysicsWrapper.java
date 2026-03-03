package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButtJigglePhysicsWrapper {
	private final ButtJigglePhysics leftButtockJiggle;
	private final ButtJigglePhysics rightButtockJiggle;
	public final float originalParentYPos;
	
	public ButtJigglePhysicsWrapper(float originalParentYPos) {
		this.originalParentYPos = originalParentYPos;
		this.leftButtockJiggle = new ButtJigglePhysics(originalParentYPos, ButtJigglePhysicsConfig.builder().build());
		this.rightButtockJiggle = new ButtJigglePhysics(originalParentYPos, ButtJigglePhysicsConfig.builder().build());
	}
	
	public ButtJigglePhysicsWrapper(float originalParentYPos, ButtJigglePhysicsConfig leftButtockJiggle, ButtJigglePhysicsConfig rightButtockJiggle) {
		this.originalParentYPos = originalParentYPos;
		this.leftButtockJiggle = new ButtJigglePhysics(originalParentYPos, leftButtockJiggle);
		this.rightButtockJiggle = new ButtJigglePhysics(originalParentYPos, rightButtockJiggle);
	}
	
    public void setupAnim(LivingEntity entity, ModelPart leftButt, ModelPart rightButt) {       
    	ButtJigglePhysics.setupAnim(entity, leftButtockJiggle, rightButtockJiggle, leftButt, rightButt);
    }
    
    public void reset() {
    	leftButtockJiggle.reset();
    	rightButtockJiggle.reset();
    }
}
