package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsFactory;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
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
public class CustomPregnantBodyP6Model extends AbstractHeavyPregnantBodyModel {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "custom_pregnant_body_p6_model"), "main");
	
	public CustomPregnantBodyP6Model(ModelPart root) {
		super(root,
				BellyInflation.HIGH,
				FetalMovementIntensity.P6,
				JigglePhysicsFactory.createHeavyweightBoobs(0.7F, false, false),
				JigglePhysicsFactory.createBelly(9.0F, PregnancyPhase.P6),
				JigglePhysicsFactory.createHeavyweightButt(0.0F));
		milkingBoobsYPos = -0.6F;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(17, 22).addBox(-4.0F, -2.8215F, -9.5733F, 8.0F, 7.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(16, 21).addBox(-4.0F, -2.8215F, -4.5733F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.99F))
		.texOffs(19, 25).addBox(-3.5F, -2.3215F, -10.4733F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.9F))
		.texOffs(22, 29).addBox(-0.6F, 1.8778F, -12.0038F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.45F, -2.0F, 0.0873F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(27, 25).addBox(-1.5F, -3.5F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.99F)), PartPose.offsetAndRotation(4.05F, 1.1785F, -2.6733F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(27, 25).addBox(-1.5F, -3.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(4.05F, 1.1785F, -7.5733F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r2", CubeListBuilder.create().texOffs(15, 25).addBox(-2.5F, -3.5F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.99F)), PartPose.offsetAndRotation(-4.05F, 1.1785F, -2.6733F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(16, 25).addBox(-1.5F, -3.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-4.05F, 1.1785F, -7.5733F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r3", CubeListBuilder.create().texOffs(21, 22).addBox(-3.5F, -2.5F, -0.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.99F)), PartPose.offsetAndRotation(0.0F, -2.8715F, -2.7733F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r2", CubeListBuilder.create().texOffs(20, 22).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -2.8715F, -7.6733F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(18, 26).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -0.95F, -3.5F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(18, 26).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.05F, -5.5F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(18, 26).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -0.95F, -5.5F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(18, 26).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.05F, -4.5F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(18, 26).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -1.95F, -6.5F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.4F, -2.2F, -0.2618F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-1.5F, -0.2F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 21).addBox(-1.5F, -2.4F, -1.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-0.6336F, 3.3279F, -0.6635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(1.5F, -0.2F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.5F, -2.3956F, -1.313F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.7326F, 3.3236F, -0.6505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition rightButt = rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create(), PartPose.offset(0.1F, 0.0F, 2.0F));
		rightButt.addOrReplaceChild("rightAssCube_r1", CubeListBuilder.create().texOffs(2, 18).mirror().addBox(-1.85F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)).mirror(false), PartPose.offsetAndRotation(0.05F, 1.65F, 0.55F, 0.0F, 3.1416F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition leftButt = leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create(), PartPose.offset(-0.1F, 0.0F, 2.0F));
		leftButt.addOrReplaceChild("leftAssCube_r1", CubeListBuilder.create().texOffs(18, 50).mirror().addBox(-2.15F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)).mirror(false), PartPose.offsetAndRotation(-0.05F, 1.65F, 0.55F, 0.0F, 3.1416F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
