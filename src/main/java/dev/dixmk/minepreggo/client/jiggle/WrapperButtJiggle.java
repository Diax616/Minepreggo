package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WrapperButtJiggle {
	private final ButtJigglePhysics leftButtockJiggle;
	private final ButtJigglePhysics rightButtockJiggle;
	public final float additionalYPos;
	
	public WrapperButtJiggle(float additionalYPos) {
		this.additionalYPos = additionalYPos;
		this.leftButtockJiggle = ButtJigglePhysics.builder().additionalYPos(additionalYPos).build();
		this.rightButtockJiggle = ButtJigglePhysics.builder().additionalYPos(additionalYPos).build();	
	}
	
	WrapperButtJiggle(float additionalYPos, ButtJigglePhysics.Builder leftButtockJiggle, ButtJigglePhysics.Builder rightButtockJiggle) {
		this.additionalYPos = additionalYPos;
		this.leftButtockJiggle = leftButtockJiggle.additionalYPos(additionalYPos).build();
		this.rightButtockJiggle = rightButtockJiggle.additionalYPos(additionalYPos).build();	
	}
	
    public void setupAnim(LivingEntity entity, ModelPart leftButt, ModelPart rightButt) {       
    	ButtJigglePhysics.setupAnim(entity, leftButtockJiggle, rightButtockJiggle, leftButt, rightButt);
    }
    
    public void reset() {
    	leftButtockJiggle.reset();
    	rightButtockJiggle.reset();
    }
}
