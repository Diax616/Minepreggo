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
		this.leftButtockJiggle = ButtJigglePhysics.builder().originalYPos(originalParentYPos).build();
		this.rightButtockJiggle = ButtJigglePhysics.builder().originalYPos(originalParentYPos).build();	
	}
	
	ButtJigglePhysicsWrapper(float originalParentYPos, ButtJigglePhysics.Builder leftButtockJiggle, ButtJigglePhysics.Builder rightButtockJiggle) {
		this.originalParentYPos = originalParentYPos;
		this.leftButtockJiggle = leftButtockJiggle.originalYPos(originalParentYPos).build();
		this.rightButtockJiggle = rightButtockJiggle.originalYPos(originalParentYPos).build();	
	}
	
    public void setupAnim(LivingEntity entity, ModelPart leftButt, ModelPart rightButt) {       
    	ButtJigglePhysics.setupAnim(entity, leftButtockJiggle, rightButtockJiggle, leftButt, rightButt);
    }
    
    public void reset() {
    	leftButtockJiggle.reset();
    	rightButtockJiggle.reset();
    }
}
