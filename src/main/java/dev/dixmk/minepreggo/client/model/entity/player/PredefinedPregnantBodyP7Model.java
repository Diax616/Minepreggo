package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PredefinedPregnantBodyP7Model extends AbstractHeavyPregnantBodyModel {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("predefined_pregnant_body_p7_model"), "main");
	
	public PredefinedPregnantBodyP7Model(ModelPart root) {
		super(root, BellyInflation.HIGH, FetalMovementIntensity.P7, PregnancyPhase.P7, SkinType.PREDEFINED);
		milkingBoobsYPos = -0.56F;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(40, 19).addBox(-1.0F, -4.5F, -2.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8827F, 9.5F, -0.4239F, 0.0F, 0.3927F, 0.0F));
		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(52, 19).addBox(-1.0F, -4.5F, -2.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8827F, 9.5F, -0.4239F, 0.0F, -0.3927F, 0.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 14).addBox(-5.0F, -3.5F, -11.1F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.7F))
		.texOffs(9, 23).addBox(-4.45F, -3.0F, -11.65F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F))
		.texOffs(9, 23).addBox(-4.5F, -3.0909F, -12.4735F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.3F))
		.texOffs(12, 28).addBox(-0.35F, 1.0F, -13.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 9.0F, -1.3F, 0.1309F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(10, 14).addBox(-4.5F, -3.9739F, -0.3017F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(0.0F, -3.5F, -5.65F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(9, 22).addBox(-4.4096F, -4.3F, -0.4531F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-0.25F, 4.25F, -6.15F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(10, 24).addBox(-4.2713F, -3.2374F, -0.4F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-4.75F, 0.25F, -6.15F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(10, 24).addBox(-4.5F, -3.5F, -0.5F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(4.75F, 0.5256F, -6.0222F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(-2.0F, 1.1F, -7.0F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 1.1F, -9.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.0F, 0.1F, -8.0F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.0F, 3.1F, -9.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.0F, 0.1F, -10.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.1F, -2.0F, -0.2356F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.3F, 0.0F));
		rightBoob.addOrReplaceChild("extraRightBoobCube1_r1", CubeListBuilder.create().texOffs(6, 10).addBox(-0.2863F, -0.647F, -4.4669F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(-0.9336F, 1.8279F, -0.8635F, 0.3491F, 0.1745F, 0.0436F));
		rightBoob.addOrReplaceChild("extraRightBoobCube_r1", CubeListBuilder.create().texOffs(3, 7).addBox(-2.3937F, -2.5823F, -3.8801F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.1664F, 2.3279F, -1.2635F, 0.3491F, 0.1745F, 0.0436F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(0, 4).addBox(-2.5F, -2.5F, -3.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.2664F, 2.3279F, -1.2635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.3F, 0.0F));
		leftBoob.addOrReplaceChild("extraLetfBoobCube1_r1", CubeListBuilder.create().texOffs(24, 10).addBox(0.2863F, -0.647F, -4.48F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(-0.7674F, 1.8235F, -1.2505F, 0.3491F, -0.1745F, -0.0436F));
		leftBoob.addOrReplaceChild("extraLetfBoobCube_r1", CubeListBuilder.create().texOffs(21, 7).addBox(-1.6063F, -2.5823F, -3.8932F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0326F, 2.3236F, -1.2505F, 0.3491F, -0.1745F, -0.0436F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 4).addBox(-1.5F, -2.5F, -3.013F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.0674F, 2.3236F, -1.2505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(37, 7).addBox(-1.95F, -0.35F, -0.45F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F))
		.texOffs(37, 7).addBox(-1.95F, -0.35F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.6F)), PartPose.offset(-0.1F, 0.0F, 2.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(37, 1).addBox(-2.05F, -0.35F, -0.45F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F))
		.texOffs(37, 1).addBox(-2.05F, -0.35F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.6F)), PartPose.offset(0.1F, 0.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}
