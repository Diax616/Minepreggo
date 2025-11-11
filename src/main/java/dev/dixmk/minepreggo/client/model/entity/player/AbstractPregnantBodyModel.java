package dev.dixmk.minepreggo.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.jiggle.JigglePhysics;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractPregnantBodyModel extends EntityModel<AbstractClientPlayer> {
	public final ModelPart body;
	public final ModelPart boobs;
	public final ModelPart belly;
	public final ModelPart rightBoob;
	public final ModelPart leftBoob;

	protected final JigglePhysics boobsJiggleData;
	protected final JigglePhysics bellyJiggleData;

	protected AbstractPregnantBodyModel(ModelPart root, JigglePhysics boobsJiggleData, JigglePhysics bellyJiggleData) {
		this.body = root.getChild("body");
		this.boobs = this.body.getChild("boobs");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
		this.belly = this.body.getChild("belly");
		this.boobsJiggleData = boobsJiggleData;
		this.bellyJiggleData = bellyJiggleData;
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		updateBelly((float)entity.getY());
		updateBoobs((float)entity.getY());
		
		if (entity.hasEffect(MinepreggoModMobEffects.LACTATION.get())) {
			this.boobs.zScale = 1.2F;
			this.boobs.xScale = 1.2F;
			this.boobs.yScale = 1.1F;			
		} 
	}
	
	protected void updateBoobs(float yPos) {
        boobsJiggleData.update(yPos, 0.05f);
        boobs.y = 2F + boobsJiggleData.getOffset();	
	}
	
	protected void updateBelly(float yPos) {
        bellyJiggleData.update(yPos,  0.05f);
        belly.y = 6F + bellyJiggleData.getOffset();	
	}
	
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
