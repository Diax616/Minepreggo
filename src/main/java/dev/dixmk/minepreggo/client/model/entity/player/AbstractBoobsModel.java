package dev.dixmk.minepreggo.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.jiggle.WrapperBoobsJiggle;
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
	protected final WrapperBoobsJiggle boobsJiggle;
	
	protected AbstractBoobsModel(ModelPart root, WrapperBoobsJiggle boobsJiggle) {
		this.body = root.getChild("body");
		this.boobs = this.body.getChild("boobs");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
		this.boobsJiggle = boobsJiggle;
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, 
            float ageInTicks, float netHeadYaw, float headPitch) {	      
		boobsJiggle.setupAnim(entity, boobs, leftBoob, rightBoob);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}

