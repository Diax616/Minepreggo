package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
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
public class CustomPregnantBodyP7Model extends AbstractHeavyPregnantBodyModel {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "custom_pregnant_body_p7_model"), "main");
	
	public CustomPregnantBodyP7Model(ModelPart root) {
		super(root,
				JigglePhysicsFactory.createHeavyweightBoobs(0.6F, false, false),
				JigglePhysicsFactory.createBelly(9.0F, PregnancyPhase.P7),
				JigglePhysicsFactory.createHeavyweightButt(0.0F));
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(22, 29).addBox(-0.6F, 1.6213F, -13.0175F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 25).addBox(-3.5F, -2.578F, -11.087F, 7.0F, 6.0F, 1.0F, new CubeDeformation(1.25F)), PartPose.offsetAndRotation(0.0F, 8.75F, -2.0F, 0.1309F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(27, 25).addBox(-1.5F, -3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(4.15F, 0.922F, -0.487F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(27, 25).addBox(-1.5F, -3.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.19F)), PartPose.offsetAndRotation(4.15F, 0.922F, -4.787F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(27, 25).addBox(-1.5F, -3.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(4.15F, 0.922F, -9.087F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r2", CubeListBuilder.create().texOffs(18, 25).addBox(0.5F, -3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(-4.15F, 0.922F, -0.587F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r2", CubeListBuilder.create().texOffs(16, 25).addBox(-1.5F, -3.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.19F)), PartPose.offsetAndRotation(-4.15F, 0.922F, -4.787F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(16, 25).addBox(-1.5F, -3.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(-4.15F, 0.922F, -9.087F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube7_r2", CubeListBuilder.create().texOffs(20, 29).addBox(-3.5F, -0.5F, -0.5F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(0.0F, 4.072F, -0.787F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r3", CubeListBuilder.create().texOffs(20, 28).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.74F)), PartPose.offsetAndRotation(0.0F, 4.072F, -4.987F, 1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r3", CubeListBuilder.create().texOffs(20, 28).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(0.0F, 4.072F, -9.187F, 1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r4", CubeListBuilder.create().texOffs(20, 23).addBox(-3.5F, -0.5F, -0.5F, 7.0F, 2.0F, 1.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(0.0F, -2.728F, -0.787F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r4", CubeListBuilder.create().texOffs(20, 23).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(1.19F)), PartPose.offsetAndRotation(0.0F, -2.728F, -4.987F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r2", CubeListBuilder.create().texOffs(20, 23).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(0.0F, -2.728F, -9.187F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-2.0F, 1.35F, -6.0F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 1.35F, -8.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(2.0F, 0.35F, -7.0F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-1.0F, 3.35F, -8.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(1.0F, 0.35F, -9.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.6F, -2.3F, -0.2618F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-1.5F, -0.2F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube1_r1", CubeListBuilder.create().texOffs(18, 21).addBox(-1.5F, -2.4F, -1.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-0.8336F, 3.6279F, -1.2635F, 0.3491F, 0.1745F, 0.0436F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 21).addBox(-1.5F, -2.4F, -1.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-0.7336F, 3.3279F, -0.6635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(1.5F, -0.2F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.5F, -2.3956F, -1.313F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(0.9326F, 3.6236F, -1.2505F, 0.3491F, -0.1745F, -0.0436F));
		leftBoob.addOrReplaceChild("letfBoobCube_r2", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.5F, -2.3956F, -1.313F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.8326F, 3.3236F, -0.6505F, 0.3491F, -0.1745F, -0.0436F));
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
	
	@Override
	protected void animBelly(AbstractClientPlayer entity, float ageInTicks) {
		super.animBelly(entity, ageInTicks);
		if (entity.hasEffect(MinepreggoModMobEffects.FETAL_MOVEMENT.get())) {
			this.animate(this.loopAnimationState, BellyAnimation.FETAL_MOVEMENT_P7, ageInTicks);
		}
		else {
			this.animate(this.loopAnimationState, BellyAnimation.HIGH_BELLY_INFLATION, ageInTicks);
		}
	}
}
