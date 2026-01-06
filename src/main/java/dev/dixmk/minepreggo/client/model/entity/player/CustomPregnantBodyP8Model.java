package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
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
public class CustomPregnantBodyP8Model extends AbstractHeavyPregnantBodyModel {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "custom_pregnant_body_p8_model"), "main");
	
	public CustomPregnantBodyP8Model(ModelPart root) {
		super(root,
				BellyInflation.HIGH,
				FetalMovementIntensity.P8,
				PregnancyPhase.P8,
				SkinType.CUSTOM);
		milkingBoobsYPos = -0.78F;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(17, 22).addBox(-4.0F, -3.6556F, -13.1737F, 8.0F, 7.0F, 3.0F, new CubeDeformation(1.6F))
		.texOffs(19, 25).addBox(-3.5F, -3.1556F, -13.8737F, 7.0F, 6.0F, 1.0F, new CubeDeformation(1.6F))
		.texOffs(19, 30).addBox(-3.5F, 3.8444F, -13.8737F, 7.0F, 0.0F, 1.0F, new CubeDeformation(1.59F))
		.texOffs(17, 22).addBox(-4.0F, -3.6556F, -8.4737F, 8.0F, 7.0F, 3.0F, new CubeDeformation(1.6F))
		.texOffs(19, 24).addBox(-4.0F, -3.6556F, -2.4737F, 8.0F, 7.0F, 1.0F, new CubeDeformation(1.6F))
		.texOffs(17, 22).addBox(-4.0F, 4.3444F, -8.4737F, 8.0F, 0.0F, 3.0F, new CubeDeformation(1.59F))
		.texOffs(19, 24).addBox(-4.0F, 4.3444F, -2.4737F, 8.0F, 0.0F, 1.0F, new CubeDeformation(1.59F))
		.texOffs(17, 22).addBox(-4.0F, 4.3444F, -13.1737F, 8.0F, 0.0F, 3.0F, new CubeDeformation(1.59F))
		.texOffs(22, 29).addBox(-0.5F, 2.1437F, -16.0042F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 10.25F, -2.0F, 0.1396F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(20, 28).addBox(-3.5F, 1.5F, -0.5F, 7.0F, 0.0F, 1.0F, new CubeDeformation(1.6F)), PartPose.offsetAndRotation(0.0F, 4.2944F, -3.5737F, 1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube8_r1", CubeListBuilder.create().texOffs(27, 25).addBox(-1.5F, 2.5F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(1.59F))
		.texOffs(27, 25).addBox(-1.5F, -4.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(1.6F)), PartPose.offsetAndRotation(4.15F, 1.3444F, -0.4737F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube7_r2", CubeListBuilder.create().texOffs(27, 25).addBox(-1.5F, 2.5F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(1.58F))
		.texOffs(27, 25).addBox(-1.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.59F)), PartPose.offsetAndRotation(4.15F, 1.3444F, -6.4737F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(27, 25).addBox(-1.5F, 2.5F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(1.59F))
		.texOffs(27, 25).addBox(-1.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.6F)), PartPose.offsetAndRotation(4.15F, 1.3444F, -11.1737F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube7_r3", CubeListBuilder.create().texOffs(18, 25).addBox(0.5F, 2.5F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(1.59F))
		.texOffs(18, 25).addBox(0.5F, -4.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(1.6F)), PartPose.offsetAndRotation(-4.15F, 1.3444F, -0.4737F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r2", CubeListBuilder.create().texOffs(16, 25).addBox(-1.5F, 2.5F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(1.58F))
		.texOffs(16, 25).addBox(-1.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.59F)), PartPose.offsetAndRotation(-4.15F, 1.3444F, -6.4737F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(16, 25).addBox(-1.5F, 2.5F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(1.59F))
		.texOffs(16, 25).addBox(-1.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.6F)), PartPose.offsetAndRotation(-4.15F, 1.3444F, -11.1737F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r3", CubeListBuilder.create().texOffs(20, 23).addBox(-3.5F, 0.5F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(1.6F)), PartPose.offsetAndRotation(0.0F, -3.7056F, -0.5737F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r4", CubeListBuilder.create().texOffs(20, 28).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(1.58F)), PartPose.offsetAndRotation(0.0F, 4.2944F, -6.5737F, 1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r2", CubeListBuilder.create().texOffs(20, 23).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(1.59F)), PartPose.offsetAndRotation(0.0F, -3.7056F, -6.5737F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r3", CubeListBuilder.create().texOffs(20, 28).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(1.59F)), PartPose.offsetAndRotation(0.0F, 4.2944F, -11.2737F, 1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(20, 23).addBox(-3.5F, -0.5F, -0.5F, 7.0F, 2.0F, 1.0F, new CubeDeformation(1.6F)), PartPose.offsetAndRotation(0.0F, -3.7056F, -11.2737F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-2.0F, -0.15F, -6.0F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -0.15F, -8.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(2.0F, -1.15F, -7.0F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-1.0F, 1.85F, -8.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(1.0F, -1.15F, -9.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.6F, -2.3F, -0.2618F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-1.5F, -0.2F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(22, 23).addBox(0.5F, -0.4F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.1336F, 3.2279F, -2.0635F, 0.3491F, 0.1745F, 0.0436F));
		rightBoob.addOrReplaceChild("rightBoobCube_r2", CubeListBuilder.create().texOffs(18, 21).addBox(-1.5F, -2.4F, -1.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-1.1336F, 3.5279F, -1.3635F, 0.3491F, 0.1745F, 0.0436F));
		rightBoob.addOrReplaceChild("rightBoobCube_r3", CubeListBuilder.create().texOffs(18, 21).addBox(-1.5F, -2.4F, -1.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-0.9336F, 3.3279F, -0.6635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(1.5F, -0.2F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(22, 23).mirror().addBox(0.5F, -0.3956F, -1.313F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0025F)).mirror(false), PartPose.offsetAndRotation(0.3326F, 3.2236F, -2.3505F, 0.3491F, -0.1745F, -0.0436F));
		leftBoob.addOrReplaceChild("letfBoobCube_r2", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.5F, -2.3956F, -1.313F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(1.1326F, 3.6236F, -1.3505F, 0.3491F, -0.1745F, -0.0436F));
		leftBoob.addOrReplaceChild("letfBoobCube_r3", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.5F, -2.3956F, -1.313F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.7F)).mirror(false), PartPose.offsetAndRotation(1.0326F, 3.3236F, -0.6505F, 0.3491F, -0.1745F, -0.0436F));	
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition rightButt = rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create(), PartPose.offset(0.1F, 0.0F, 2.0F));
		rightButt.addOrReplaceChild("rightAssCube2_r1", CubeListBuilder.create().texOffs(2, 18).mirror().addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.6F)).mirror(false), PartPose.offsetAndRotation(-0.15F, 1.65F, 0.95F, 0.0F, 3.1416F, 0.0F));
		rightButt.addOrReplaceChild("rightAssCube1_r1", CubeListBuilder.create().texOffs(2, 18).mirror().addBox(-1.8F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)).mirror(false), PartPose.offsetAndRotation(0.05F, 1.65F, 0.55F, 0.0F, 3.1416F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition leftButt = leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create(), PartPose.offset(-0.1F, 0.0F, 2.0F));
		leftButt.addOrReplaceChild("leftAssCube2_r1", CubeListBuilder.create().texOffs(18, 50).mirror().addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.6F)).mirror(false), PartPose.offsetAndRotation(0.15F, 1.65F, 0.95F, 0.0F, 3.1416F, 0.0F));
		leftButt.addOrReplaceChild("leftAssCube1_r1", CubeListBuilder.create().texOffs(18, 50).mirror().addBox(-2.2F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)).mirror(false), PartPose.offsetAndRotation(-0.05F, 1.65F, 0.55F, 0.0F, 3.1416F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
