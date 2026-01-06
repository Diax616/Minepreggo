package dev.dixmk.minepreggo.client.jiggle;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WrapperBoobsJiggle {
	private final BoobJigglePhysics leftBoobJiggle;
	private final BoobJigglePhysics rightBoobJiggle;
	public final float additionalYPos;

	public WrapperBoobsJiggle(float additionalYPos) {
		this.additionalYPos = additionalYPos;
        this.leftBoobJiggle = BoobJigglePhysics.builder(0.0f, true).additionalYPos(additionalYPos).build();
        this.rightBoobJiggle = BoobJigglePhysics.builder(Mth.PI, false).additionalYPos(additionalYPos).build(); // 180 degrees out of phase
	}
	
	WrapperBoobsJiggle(float additionalYPos, BoobJigglePhysics.Builder leftBoobJiggle, BoobJigglePhysics.Builder rightBoobJiggle, boolean axisX, boolean axisZ) {
		this.leftBoobJiggle = leftBoobJiggle.additionalYPos(additionalYPos).build();
		this.rightBoobJiggle = rightBoobJiggle.additionalYPos(additionalYPos).build();
		this.additionalYPos = additionalYPos;
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
