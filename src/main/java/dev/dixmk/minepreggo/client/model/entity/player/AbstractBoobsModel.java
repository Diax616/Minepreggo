package dev.dixmk.minepreggo.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.jiggle.AdvancedJigglePhysics;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractBoobsModel extends EntityModel<AbstractClientPlayer> {
	public final ModelPart body;
	public final ModelPart boobs;
	public final ModelPart rightBoob;
	public final ModelPart leftBoob;
	protected final AdvancedJigglePhysics jiggleData;
	
	protected AbstractBoobsModel(ModelPart root, AdvancedJigglePhysics jiggleData) {
		this.body = root.getChild("body");
		this.boobs = this.body.getChild("boobs");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
		this.jiggleData = jiggleData;
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, 
            float ageInTicks, float netHeadYaw, float headPitch) {	
        
        // Update jiggle physics with movement data
        float deltaTime = 0.05f; // Approximate frame time
        jiggleData.update((float) entity.getY(), entity.getX(), entity.getZ(), deltaTime, entity.walkAnimation.isMoving());
        
        // Apply jiggle offset to parent bone (Y-axis position)
        float offset = jiggleData.getOffset();
        boobs.y = 2.0F + offset;
        
        // Apply rotation around Y-axis (yRot for vertical axis rotation)
        float rotation = jiggleData.getRotation();
        boobs.yRot = rotation;
        
        // Add slight individual boob rotation for more realism
        float movementIntensity = jiggleData.getMovementIntensity();
        leftBoob.yRot = rotation * 0.3f + (movementIntensity * 0.05f);
        rightBoob.yRot = -rotation * 0.3f - (movementIntensity * 0.05f);	
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}

