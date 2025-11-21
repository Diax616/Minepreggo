package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.preggo.HumanoidFemaleAnimation;
import dev.dixmk.minepreggo.client.jiggle.AdvancedJigglePhysics;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomPregnantBodyP1Model extends AbstractPregnantBodyModel {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "custom_pregnant_body_p1_model"), "main");
	
	public CustomPregnantBodyP1Model(ModelPart root) {
		super(root, AdvancedJigglePhysics.advancedBuilder().build(),AdvancedJigglePhysics.advancedBuilder().build());
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(20, 25).addBox(-3.0F, -0.25F, -1.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, -2.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -1.9F, 0.1134F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(18, 21).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.5F, 0.2F, 0.0F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 0.2F, 0.0F, 0.3491F, -0.1309F, -0.0436F));	
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.animate(this.loopAnimationState, HumanoidFemaleAnimation.LOW_BELLY_INFLATION, ageInTicks, 1f);
	}
}
