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
public class BellyShieldP6Model extends AbstractBellyShield<LivingEntity> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_shield_p6_model"), "main");

	public BellyShieldP6Model(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(39, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(1, 14).addBox(4.9F, -5.5F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(1, 14).addBox(-5.9F, -5.5F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.4F, -11.9F, 0.1047F, 0.0F, 0.0F));
		belly.addOrReplaceChild("back_lead_r1", CubeListBuilder.create().texOffs(34, 0).addBox(-1.1F, -0.5F, 0.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -5.0F, 15.4F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("shield_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -11.8F, -1.3F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -0.4F, -0.0175F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}