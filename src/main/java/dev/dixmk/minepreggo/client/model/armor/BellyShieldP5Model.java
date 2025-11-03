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

public class BellyShieldP5Model extends AbstractBellyShield<LivingEntity> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_shield_p5_model"), "main");

	public BellyShieldP5Model(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(39, 16).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -6.8F, -0.1F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 14).addBox(9.9F, -0.5F, 0.0F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(1, 14).addBox(-0.9F, -0.5F, 0.0F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 10.4F, -11.3F, 0.0873F, 0.0F, 0.0F));
		belly.addOrReplaceChild("back_lead_r1", CubeListBuilder.create().texOffs(26, 1).addBox(-1.1F, -0.5F, 0.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(10.5F, 0.0F, 14.4F, 0.0F, -1.5708F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}