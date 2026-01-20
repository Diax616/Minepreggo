package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoobsJigglePhysicsWrapper {
	private final BoobJigglePhysics leftBoobJiggle;
	private final BoobJigglePhysics rightBoobJiggle;
	public final float originalParentYPos;

	public BoobsJigglePhysicsWrapper(float originalParentYPos) {
		this.originalParentYPos = originalParentYPos;
        this.leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true).originalYPos(originalParentYPos).build();
        this.rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false).originalYPos(originalParentYPos).build(); // 180 degrees out of phase
	}
	
	BoobsJigglePhysicsWrapper(float originalParentYPos, BoobJigglePhysics.Builder leftBoobJiggle, BoobJigglePhysics.Builder rightBoobJiggle, boolean axisX, boolean axisZ) {
		this.leftBoobJiggle = leftBoobJiggle.originalYPos(originalParentYPos).build();
		this.rightBoobJiggle = rightBoobJiggle.originalYPos(originalParentYPos).build();
		this.originalParentYPos = originalParentYPos;
		useAxisX(axisX);
		useAxisZ(axisZ);
	}
	
    public void setupAnim(LivingEntity entity, ModelPart boobParent, ModelPart leftBoob, ModelPart rightBoob) {	
    	BoobJigglePhysics.setupAnim(entity, leftBoobJiggle, rightBoobJiggle, boobParent, leftBoob, rightBoob);
    }
    
    public void useAxisX(boolean axisX) {
    	leftBoobJiggle.useAxisX(axisX);
    	rightBoobJiggle.useAxisX(axisX);
    }
    
    public void useAxisZ(boolean axisZ) {
    	leftBoobJiggle.useAxisZ(axisZ);
    	rightBoobJiggle.useAxisZ(axisZ);
    }
    
    public void reset() {
    	leftBoobJiggle.reset();
    	rightBoobJiggle.reset();
    }
}
