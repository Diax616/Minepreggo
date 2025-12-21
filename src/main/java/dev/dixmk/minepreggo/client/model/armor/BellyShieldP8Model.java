package dev.dixmk.minepreggo.client.model.armor;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BellyShieldP8Model extends AbstractBellyShield<LivingEntity> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_shield_p8_model"), "main");

	public BellyShieldP8Model(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.75F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.75F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(25, 3).addBox(6.1F, -6.5666F, -0.5006F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 3).addBox(-7.1F, -6.5666F, -0.5006F, 1.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.9F, -17.55F, 0.1396F, 0.0F, 0.0F));
		belly.addOrReplaceChild("back_lead_r1", CubeListBuilder.create().texOffs(31, 9).addBox(-1.1F, -0.5F, 0.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -6.2666F, 21.8994F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("left_lead_r1", CubeListBuilder.create().texOffs(37, 15).addBox(-0.4F, -0.5F, 13.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -6.1666F, 3.4994F, 0.0F, 0.2182F, 0.0F));
		belly.addOrReplaceChild("left_lead_r2", CubeListBuilder.create().texOffs(37, 15).addBox(-0.4F, -0.5F, 13.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3F, -6.1666F, 3.4994F, 0.0F, -0.2182F, 0.0F));
		belly.addOrReplaceChild("shield_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.8F, -1.3F, 14.0F, 12.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-1.0F, -0.4666F, 0.1994F, 0.0349F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}