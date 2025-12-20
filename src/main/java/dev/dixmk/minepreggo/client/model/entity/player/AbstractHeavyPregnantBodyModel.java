package dev.dixmk.minepreggo.client.model.entity.player;

import java.util.UUID;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.jiggle.BellyJigglePhysics;
import dev.dixmk.minepreggo.client.jiggle.WrapperBoobsJiggle;
import dev.dixmk.minepreggo.client.jiggle.WrapperButtJiggle;
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
	
	protected final WrapperButtJiggle buttsJiggle;
	
	protected AbstractHeavyPregnantBodyModel(ModelPart root, WrapperBoobsJiggle boobsJiggle, BellyJigglePhysics bellyJiggle, WrapperButtJiggle buttsJiggle) {
		super(root, boobsJiggle, bellyJiggle, false);
		this.leftLeg = root.getChild("left_leg");
		this.rightLeg = root.getChild("right_leg");
		this.leftbutt = leftLeg.getChild("left_butt");
		this.rightbutt = rightLeg.getChild("right_butt");	
		this.buttsJiggle = buttsJiggle;
	}

	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {		
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		buttsJiggle.setupAnim(entity, leftbutt, rightbutt);
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
