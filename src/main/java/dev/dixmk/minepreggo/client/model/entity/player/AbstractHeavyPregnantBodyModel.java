package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.client.jiggle.JigglePhysics;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;

public abstract class AbstractHeavyPregnantBodyModel extends AbstractPregnantBodyModel {
	public final ModelPart leftLeg;
	public final ModelPart rightLeg;
	public final ModelPart leftbutt;
	public final ModelPart rightbutt;
	
	protected final JigglePhysics leftButtJiggle;
	protected final JigglePhysics rightButtJiggle;
	
	protected AbstractHeavyPregnantBodyModel(ModelPart root, JigglePhysics boobsJiggle, JigglePhysics bellyJiggle, JigglePhysics buttJiggle) {
		super(root, boobsJiggle, bellyJiggle);
		this.leftLeg = root.getChild("left_leg");
		this.rightLeg = root.getChild("right_leg");
		this.leftbutt = leftLeg.getChild("left_butt");
		this.rightbutt = rightLeg.getChild("right_butt");	
		this.leftButtJiggle = buttJiggle;
		this.rightButtJiggle = JigglePhysics.copy(buttJiggle);	
	}
	
	protected void updateButt(float yPos) {
		leftButtJiggle.update(yPos, 0.05f);
		leftbutt.y = 2.0F + leftButtJiggle.getOffset();	 
		rightButtJiggle.update(yPos, 0.05f);
		rightbutt.y = 2.0F + rightButtJiggle.getOffset();	
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		super.setupAnim(entity, p_102619_, p_102620_, p_102621_, p_102622_, p_102623_);
		updateButt((float)entity.getY());
	}
}
