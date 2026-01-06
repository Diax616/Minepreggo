package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
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
public class PredefinedPregnantBodyP4Model extends AbstractHeavyPregnantBodyModel {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "predefined_pregnant_body_p4_model"), "main");
	
	public PredefinedPregnantBodyP4Model(ModelPart root) {
		super(root,
				BellyInflation.MEDIUM,
				FetalMovementIntensity.P4,
				PregnancyPhase.P4,
				SkinType.PREDEFINED);
		milkingBoobsYPos = -0.5F;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.2967F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.5F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(13, 1).addBox(-1.5F, -2.0044F, -1.2F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-0.6336F, 2.3279F, -1.3635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(1.5F, 0.5F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(0, 1).mirror().addBox(-1.5F, -2.0001F, -1.213F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.6326F, 2.3236F, -1.3505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 8).addBox(-4.0F, 0.0F, -7.5F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.35F))
		.texOffs(5, 13).addBox(-4.0F, 0.0F, -8.4F, 8.0F, 7.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(52, 5).addBox(-0.5F, 4.1993F, -8.6305F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -0.5F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(8, 16).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 5.0F, -3.6F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(8, 16).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 3.9F, -3.5F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(8, 16).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.5F, 3.8F, -4.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(39, 1).addBox(-1.7F, -0.35F, -0.15F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)), PartPose.offset(-0.1F, 0.0F, 1.4F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(26, 1).mirror().addBox(-2.3F, -0.35F, -0.15F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)).mirror(false), PartPose.offset(0.1F, 0.0F, 1.5F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}	
}