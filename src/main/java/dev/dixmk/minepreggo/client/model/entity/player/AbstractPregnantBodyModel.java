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

	protected float milkingBoobsXScale = 1.3F;
	protected float milkingBoobsYScale = 1.15F;
	protected float milkingBoobsZScale = 1.2F;
	protected float milkingBoobsYPos = -0.42F;
	
	protected float additionalJiggleBoobYPos = 2F;
	protected float additionalJiggleBellyYPos = 6F;
	
	protected boolean milkingFlag = false;
	
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
			this.boobs.y += milkingBoobsYPos;			
			if (!milkingFlag) {
				this.boobs.xScale = milkingBoobsXScale;
				this.boobs.yScale = milkingBoobsYScale;
				this.boobs.zScale = milkingBoobsZScale;	
				milkingFlag = true;
			}
		} else if (milkingFlag) {
			this.boobs.xScale = ModelPart.DEFAULT_SCALE;
			this.boobs.yScale = ModelPart.DEFAULT_SCALE;
			this.boobs.zScale = ModelPart.DEFAULT_SCALE;
			milkingFlag = false;
		}	
	}
	
	protected void updateBoobs(float yPos) {
        boobsJiggleData.update(yPos, 0.05f);
        boobs.y = additionalJiggleBoobYPos + boobsJiggleData.getOffset();	
	}
		
	protected void updateBelly(float yPos) {
        bellyJiggleData.update(yPos,  0.05f);
        belly.y = additionalJiggleBellyYPos + bellyJiggleData.getOffset();	
	}
	
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
