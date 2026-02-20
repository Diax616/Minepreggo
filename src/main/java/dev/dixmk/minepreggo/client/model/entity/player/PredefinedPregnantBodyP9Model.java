package dev.dixmk.minepreggo.client.model.entity.player;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PredefinedPregnantBodyP9Model {

	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(50, 2).addBox(-1.0F, -5.5F, -2.0F, 3.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.2827F, 9.95F, -1.9239F, 0.0F, -0.9163F, 0.0F));
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(50, 17).mirror().addBox(-2.0F, -5.5F, -2.0F, 3.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.1827F, 10.0F, -1.9239F, 0.0F, 0.9163F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.2967F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("secondExtraRightBoobCube_r1", CubeListBuilder.create().texOffs(0, 12).addBox(-0.7677F, -0.7209F, -4.7947F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(3, 3).addBox(-2.4559F, -2.5044F, -4.3052F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(0, 0).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.2664F, 2.1279F, -1.1635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("secondExtraRightBoobCube_r2", CubeListBuilder.create().texOffs(0, 9).addBox(0.1388F, -0.657F, -4.7254F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 3).addBox(-1.3473F, -2.5081F, -4.3407F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(18, 0).addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(-0.1674F, 2.1236F, -1.1505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 9).addBox(-6.0F, -3.65F, -14.25F, 12.0F, 10.0F, 13.0F, new CubeDeformation(1.0F))
		.texOffs(13, 22).addBox(-5.45F, -3.2076F, -15.0735F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.2F))
		.texOffs(13, 22).addBox(-5.4F, -3.1723F, -15.8617F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(13, 22).addBox(-4.95F, -2.6254F, -16.6314F, 10.0F, 8.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(16, 30).addBox(-0.5F, 2.8F, -18.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 9.0F, -1.0F, 0.0873F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube8_r1", CubeListBuilder.create().texOffs(11, 20).addBox(-6.5F, -5.5F, -0.5F, 12.0F, 11.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, -3.4F, -7.25F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(11, 22).addBox(-6.5F, -4.8F, -0.5F, 12.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, 6.1F, -7.4F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(11, 22).addBox(-6.6731F, -4.8163F, -0.5F, 12.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-5.75F, 1.6F, -7.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(13, 22).mirror().addBox(-6.6992F, -4.5174F, -0.5F, 12.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(5.75F, 1.35F, -7.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(7, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(-2.0F, 1.1F, -7.0F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(7, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 1.1F, -9.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(7, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.0F, 0.1F, -8.0F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(7, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.0F, 3.1F, -9.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(7, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.0F, 0.1F, -10.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(37, 7).addBox(-1.95F, -0.35F, -0.45F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(37, 7).addBox(-2.05F, -0.35F, 0.15F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.6F)), PartPose.offset(-0.1F, 0.0F, 2.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(37, 1).addBox(-2.05F, -0.35F, -0.45F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(37, 1).addBox(-2.05F, -0.35F, 0.15F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.6F)), PartPose.offset(0.1F, 0.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}
