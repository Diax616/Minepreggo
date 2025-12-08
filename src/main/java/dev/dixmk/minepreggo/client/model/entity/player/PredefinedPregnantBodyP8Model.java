package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.preggo.HumanoidFemaleAnimation;
import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsFactory;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
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
public class PredefinedPregnantBodyP8Model extends AbstractHeavyPregnantBodyModel {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "predefined_pregnant_body_p8_model"), "main");
	
	public PredefinedPregnantBodyP8Model(ModelPart root) {
		super(root,
				JigglePhysicsFactory.createLightweightBoobs(2.0F),
				JigglePhysicsFactory.createBelly(2.0F, PregnancyPhase.P8),
				JigglePhysicsFactory.createLightweightButt(2.0F));
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(52, 17).mirror().addBox(-1.0F, -5.5F, -2.0F, 2.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.8827F, 10.0F, -0.4239F, 0.0F, 0.3927F, 0.0F));
		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(52, 2).addBox(-1.0F, -5.5F, -2.0F, 2.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8827F, 9.75F, -0.4239F, 0.0F, -0.3927F, 0.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 10).addBox(-5.0F, -3.65F, -13.25F, 10.0F, 10.0F, 12.0F, new CubeDeformation(1.0F))
		.texOffs(12, 20).addBox(-4.45F, -3.2076F, -13.8735F, 9.0F, 9.0F, 1.0F, new CubeDeformation(1.2F))
		.texOffs(12, 20).addBox(-4.4F, -3.1723F, -14.5617F, 9.0F, 9.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(12, 20).addBox(-3.95F, -2.6254F, -15.1314F, 8.0F, 8.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(14, 28).addBox(-0.5F, 2.8F, -16.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 9.0F, -1.0F, 0.0873F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube8_r1", CubeListBuilder.create().texOffs(10, 22).addBox(-5.5F, -4.5F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, -3.4F, -7.25F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(11, 22).addBox(-5.5F, -4.8F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, 6.1F, -7.0F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(11, 22).addBox(-5.6731F, -4.8163F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-4.75F, 1.6F, -7.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(12, 22).mirror().addBox(-5.6992F, -4.5174F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(4.75F, 1.35F, -7.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(-2.0F, 1.1F, -7.0F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 1.1F, -9.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.0F, 0.1F, -8.0F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.0F, 3.1F, -9.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(5, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.0F, 0.1F, -10.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.2967F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("secondExtraRightBoobCube_r1", CubeListBuilder.create().texOffs(0, 13).addBox(-0.7677F, -0.7209F, -4.7947F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(3, 4).addBox(-2.4559F, -2.5044F, -4.3052F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(0, 1).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.2664F, 2.1279F, -1.1635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("secondExtraRightBoobCube_r2", CubeListBuilder.create().texOffs(0, 10).addBox(0.1388F, -0.657F, -4.7254F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 4).addBox(-1.3473F, -2.5081F, -4.3408F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(18, 1).addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(-0.1674F, 2.1236F, -1.1505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(37, 7).addBox(-1.95F, -0.35F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(37, 7).addBox(-2.05F, -0.35F, 0.55F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.1F, 0.0F, 1.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(37, 1).addBox(-2.05F, -0.35F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(37, 1).addBox(-2.05F, -0.35F, 0.55F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(-0.1F, 0.0F, 1.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
	
	@Override
	protected void animBelly(AbstractClientPlayer entity, float ageInTicks) {
		super.animBelly(entity, ageInTicks);
		if (entity.hasEffect(MinepreggoModMobEffects.FETAL_MOVEMENT.get())) {
			this.animate(this.loopAnimationState, HumanoidFemaleAnimation.FETAL_MOVEMENT_P8, ageInTicks);
		}
		else {
			this.animate(this.loopAnimationState, HumanoidFemaleAnimation.HIGH_BELLY_INFLATION, ageInTicks);
		}
	}
}
