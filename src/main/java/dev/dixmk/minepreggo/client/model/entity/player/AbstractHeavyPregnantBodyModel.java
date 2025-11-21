package dev.dixmk.minepreggo.client.model.entity.player;

import java.util.UUID;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.jiggle.AdvancedJigglePhysics;
import dev.dixmk.minepreggo.client.jiggle.SimpleJigglePhysics;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHeavyPregnantBodyModel extends AbstractPregnantBodyModel {
	public final ModelPart leftLeg;
	public final ModelPart rightLeg;
	public final ModelPart leftbutt;
	public final ModelPart rightbutt;
	
	protected final SimpleJigglePhysics leftButtJiggle;
	protected final SimpleJigglePhysics rightButtJiggle;
	
	protected float additionalJiggleButtYPos = 0.25F;
	
	protected AbstractHeavyPregnantBodyModel(ModelPart root, AdvancedJigglePhysics boobsJiggle, AdvancedJigglePhysics bellyJiggle, SimpleJigglePhysics buttJiggle) {
		super(root, boobsJiggle, bellyJiggle);
		this.leftLeg = root.getChild("left_leg");
		this.rightLeg = root.getChild("right_leg");
		this.leftbutt = leftLeg.getChild("left_butt");
		this.rightbutt = rightLeg.getChild("right_butt");	
		this.leftButtJiggle = buttJiggle;
		this.rightButtJiggle = this.leftButtJiggle.copy();	
	}
	
	protected void updateButt(float yPos) {
		leftButtJiggle.update(yPos, 0.05f);
		leftbutt.y = additionalJiggleButtYPos + leftButtJiggle.getOffset();	 
		rightButtJiggle.update(yPos, 0.05f);
		rightbutt.y = additionalJiggleButtYPos + rightButtJiggle.getOffset();	
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		super.setupAnim(entity, p_102619_, p_102620_, p_102621_, p_102622_, p_102623_);
		updateButt((float)entity.getY());
	}
	
	@Override
	protected void animBelly(AbstractClientPlayer entity, float ageInTicks) {      
        UUID playerId = entity.getUUID();
        
        if (!BellyAnimationManager.getInstance().isAnimating(playerId)) {
            return;
        }
		
		AnimationState state = BellyAnimationManager.getInstance().getAnimationState(playerId);
        AnimationDefinition animation = BellyAnimationManager.getInstance().getCurrentAnimation(playerId);
        
        if (state != null && animation != null) {
            this.animate(state, animation, ageInTicks);
        }
	}
	
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
