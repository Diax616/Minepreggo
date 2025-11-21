package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.preggo.HumanoidFemaleAnimation;
import dev.dixmk.minepreggo.client.jiggle.AdvancedJigglePhysics;
import dev.dixmk.minepreggo.client.jiggle.SimpleJigglePhysics;
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
		super(root, AdvancedJigglePhysics.advancedBuilder().build(), AdvancedJigglePhysics.advancedBuilder().build(), SimpleJigglePhysics.simpleBuilder().build());
		this.additionalJiggleBoobYPos = 1.5F;
		this.additionalJiggleBellyYPos = 5.25F;
		this.simpleJiggleInBelly = false;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.2F, -2.2F, -0.3316F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.4F, -0.4F, -0.2F));
		rightBoob.addOrReplaceChild("rightBoobCube3_r1", CubeListBuilder.create().texOffs(20, 25).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.6093F, 3.9468F, -3.2258F, 0.3491F, 0.1745F, 0.0436F));
		rightBoob.addOrReplaceChild("rightBoobCube2_r1", CubeListBuilder.create().texOffs(20, 23).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-0.4115F, 3.3625F, -2.6889F, 0.3491F, 0.1745F, 0.0436F));
		rightBoob.addOrReplaceChild("rightBoobCube1_r1", CubeListBuilder.create().texOffs(18, 21).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(-0.1336F, 2.7279F, -1.1635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.1F, -0.4F, -0.2F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 21).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.8F)).mirror(false), PartPose.offsetAndRotation(0.5326F, 2.7235F, -1.1505F, 0.3491F, -0.1745F, -0.0436F));
		leftBoob.addOrReplaceChild("letfBoobCube3_r1", CubeListBuilder.create().texOffs(20, 23).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(0.9444F, 3.3625F, -2.6889F, 0.3491F, -0.1745F, -0.0436F));
		leftBoob.addOrReplaceChild("letfBoobCube4_r1", CubeListBuilder.create().texOffs(20, 25).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(1.1099F, 3.8609F, -3.2785F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(16, 22).addBox(-4.0F, 0.5F, -4.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.3F))
		.texOffs(19, 25).addBox(-3.5F, 1.0F, -4.6F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.4F))
		.texOffs(19, 25).addBox(-3.5F, 1.0F, -5.0F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.2F))
		.texOffs(22, 29).addBox(-0.6F, 4.1993F, -5.6305F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.25F, -2.0F, 0.0436F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(27, 26).addBox(-1.5F, -2.5F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(3.75F, 3.5F, -2.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(16, 26).addBox(-1.5F, -2.5F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-3.75F, 3.5F, -2.0F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r2", CubeListBuilder.create().texOffs(19, 25).addBox(-3.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(0.0F, 0.75F, -2.1F, -1.5708F, 0.0F, 0.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition rightButt = rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create(), PartPose.offset(0.1F, 0.0F, 1.95F));
		rightButt.addOrReplaceChild("rightAssCube2_r1", CubeListBuilder.create().texOffs(2, 18).mirror().addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(-0.15F, 1.65F, 0.4F, 0.0F, 3.1416F, 0.0F));
		rightButt.addOrReplaceChild("rightAssCube1_r1", CubeListBuilder.create().texOffs(2, 18).mirror().addBox(-1.8F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(0.05F, 1.65F, 0.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition leftButt = leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create(), PartPose.offset(-0.1F, 0.0F, 1.95F));
		leftButt.addOrReplaceChild("leftAssCube2_r1", CubeListBuilder.create().texOffs(18, 50).mirror().addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(0.15F, 1.65F, 0.4F, 0.0F, 3.1416F, 0.0F));
		leftButt.addOrReplaceChild("leftAssCube1_r1", CubeListBuilder.create().texOffs(18, 50).mirror().addBox(-2.2F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(-0.05F, 1.65F, 0.0F, 0.0F, 3.1416F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	
	@Override
	protected void animBelly(AbstractClientPlayer entity, float ageInTicks) {
		super.animBelly(entity, ageInTicks);
		this.animate(this.loopAnimationState, HumanoidFemaleAnimation.HIGH_BELLY_INFLATION, ageInTicks, 1f);
	}
}
