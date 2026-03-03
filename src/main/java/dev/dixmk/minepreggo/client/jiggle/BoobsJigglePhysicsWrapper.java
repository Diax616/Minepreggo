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
        this.leftBoobJiggle = new BoobJigglePhysics(originalParentYPos, BoobJigglePhysicsConfig.builder(0.0f, true).build()); // 0 degrees phase offset
        this.rightBoobJiggle = new BoobJigglePhysics(originalParentYPos, BoobJigglePhysicsConfig.builder(Mth.PI, false).build()); // 180 degrees phase offset
	}
	
	public BoobsJigglePhysicsWrapper(float originalParentYPos, BoobJigglePhysicsConfig leftBoobJiggle, BoobJigglePhysicsConfig rightBoobJiggle, boolean axisX, boolean axisZ) {
		this.leftBoobJiggle = new BoobJigglePhysics(originalParentYPos, leftBoobJiggle);
		this.rightBoobJiggle = new BoobJigglePhysics(originalParentYPos, rightBoobJiggle);
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
