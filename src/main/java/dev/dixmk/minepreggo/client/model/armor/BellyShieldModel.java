package dev.dixmk.minepreggo.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BellyShieldModel extends EntityModel<LivingEntity> {
	public final ModelPart body;

	public BellyShieldModel(ModelPart root) {
		this.body = root.getChild("body");
	}
	
	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {		}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public static LayerDefinition createBellyShieldP5Layer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(39, 16).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -6.8F, -1.1F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(9.9F, -0.5F, -1.0F, 1.0F, 1.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-0.9F, -0.5F, -1.0F, 1.0F, 1.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 10.4F, -11.3F, 0.0873F, 0.0F, 0.0F));
		belly.addOrReplaceChild("back_lead_r1", CubeListBuilder.create().texOffs(26, 1).addBox(-1.1F, -0.5F, 0.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(10.5F, 0.0F, 14.4F, 0.0F, -1.5708F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
	
	public static LayerDefinition createBellyShieldP6Layer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(39, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(1, 14).addBox(4.9F, -5.5F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(1, 14).addBox(-5.9F, -5.5F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.4F, -11.9F, 0.1047F, 0.0F, 0.0F));
		belly.addOrReplaceChild("back_lead_r1", CubeListBuilder.create().texOffs(34, 0).addBox(-1.1F, -0.5F, 0.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -5.0F, 15.4F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("shield_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -11.8F, -1.3F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.0175F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
	
	public static LayerDefinition createBellyShieldP7Layer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(39, 16).addBox(-4.0F, 0.0F, -2.75F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.75F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 13).addBox(5.3F, -5.5F, 0.0F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-6.3F, -5.5F, 0.0F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.4F, -14.15F, 0.1658F, 0.0F, 0.0F));
		belly.addOrReplaceChild("back_lead_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-1.1F, -0.5F, 0.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -5.0F, 17.4F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("shield_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.8F, -1.3F, 14.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.7F, -0.0175F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
	
	public static LayerDefinition createBellyShieldP8Layer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.75F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.75F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(25, 3).addBox(6.1F, -6.5666F, -0.5006F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 3).addBox(-7.1F, -6.5666F, -0.5006F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.9F, -17.55F, 0.1396F, 0.0F, 0.0F));
		belly.addOrReplaceChild("back_lead_r1", CubeListBuilder.create().texOffs(31, 9).addBox(-1.1F, -0.5F, 0.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -6.2666F, 20.8994F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("left_lead_r1", CubeListBuilder.create().texOffs(37, 15).addBox(-0.4F, -0.5F, 13.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -6.1666F, 3.4994F, 0.0F, 0.2182F, 0.0F));
		belly.addOrReplaceChild("left_lead_r2", CubeListBuilder.create().texOffs(37, 15).addBox(-0.4F, -0.5F, 13.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3F, -6.1666F, 3.4994F, 0.0F, -0.2182F, 0.0F));
		belly.addOrReplaceChild("shield_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.8F, -1.3F, 14.0F, 12.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-1.0F, -0.4666F, 0.5994F, -0.0524F, 0.0F, 0.0F));		
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}
