package dev.dixmk.minepreggo.client.model.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.preggo.HumanoidFemaleAnimation;
import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsFactory;
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
public class PredefinedPregnantBodyP5Model extends AbstractHeavyPregnantBodyModel {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "predefined_pregnant_body_p5_model"), "main");
	
	public PredefinedPregnantBodyP5Model(ModelPart root) {
		super(root,
				JigglePhysicsFactory.createHeavyweightBoobs(2.0F, false, false),
				JigglePhysicsFactory.createBelly(2.0F, PregnancyPhase.P5),
				JigglePhysicsFactory.createHeavyweightButt(2.0F));
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(1, 9).addBox(-4.0F, 0.1F, -8.4F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.7F))
		.texOffs(8, 16).addBox(-3.5F, 0.6F, -9.2F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.7F))
		.texOffs(56, 5).addBox(-0.5F, 4.9F, -10.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 0.0436F, 0.0F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -1.8F, -0.3054F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(16, 1).addBox(-2.5F, -1.5F, -2.1F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-0.0336F, 2.2279F, -1.3635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(0, 1).mirror().addBox(-1.5F, -1.5F, -2.113F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.1326F, 2.2235F, -1.3505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(44, 2).mirror().addBox(-1.85F, -0.35F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offset(-0.1F, 0.0F, 1.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(32, 2).mirror().addBox(-2.15F, -0.35F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offset(0.1F, 0.0F, 1.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
	
	@Override
	protected void animBelly(AbstractClientPlayer entity, float ageInTicks) {
		super.animBelly(entity, ageInTicks);
		this.animate(this.loopAnimationState, HumanoidFemaleAnimation.MEDIUM_BELLY_INFLATION, ageInTicks, 1f);
	}
	
}