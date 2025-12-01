package dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractQueadrupedCreeperGirlModel<E extends AbstractCreeperGirl> extends CreeperModel<E> {	
	protected final ModelPart hat;
	protected final ModelPart head;
	protected final ModelPart belly;
	protected final ModelPart boobs;
	
	public static final ModelLayerLocation LAYER_OUTER_ARMOR_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_outer_model"), "outer");
	
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P0_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_energy_armor_p0_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P1_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_energy_armor_p1_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P2_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_energy_armor_p2_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P3_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_energy_armor_p3_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P4_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_energy_armor_p4_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P5_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_energy_armor_p5_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P6_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_energy_armor_p6_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P7_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_energy_armor_p7_model"), "armor");

	public static final ModelLayerLocation LAYER_LOCATION_P0 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_p0_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P1 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_p1_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P2 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_p2_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P3 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_p3_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P4 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_p4_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P5 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_p5_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P6 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_p6_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P7 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "quadruped_creeper_girl_p7_model"), "main");
	
	protected AbstractQueadrupedCreeperGirlModel(ModelPart root) {
		super(root);
		this.hat = root().getChild("hat");
		this.head = root().getChild("head");	
		var body = root().getChild("body");	
		this.belly = body.getChild("belly");
		this.boobs = body.getChild("boobs");
	}
	
	protected static void createBasicBodyLayer(PartDefinition partdefinition) {	
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 3.0F, 0.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 1.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(18, 21).addBox(-3.5F, 5.0F, -1.5F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 25).addBox(-4.0F, 7.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));
		PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(1, 18).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 50).addBox(-1.9F, 0.3686F, 0.2378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(-3.0F, 13.0F, -3.0F));
		rightFrontLeg.addOrReplaceChild("extraCube2_r1", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.6F, 1.1686F, 1.0378F, 1.405F, 0.0F, 0.0F));
		rightFrontLeg.addOrReplaceChild("extraCube1_r1", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.6F, 2.6686F, 0.8378F, -0.5236F, 0.0F, 0.0F));
		PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(17, 50).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 51).addBox(-2.1F, 0.3686F, 0.2378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(3.0F, 13.0F, -3.0F));
		leftFrontLeg.addOrReplaceChild("extraCube2_r2", CubeListBuilder.create().texOffs(18, 51).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.4F, 1.1686F, 1.0378F, 1.405F, 0.0F, 0.0F));
		leftFrontLeg.addOrReplaceChild("extraCube1_r2", CubeListBuilder.create().texOffs(18, 51).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.4F, 2.6686F, 0.8378F, -0.5236F, 0.0F, 0.0F));
		PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(1, 18).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 51).addBox(-1.9F, 0.3686F, 0.5378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(-3.0F, 13.0F, 3.0F));
		rightHindLeg.addOrReplaceChild("extraCube2_r3", CubeListBuilder.create().texOffs(18, 51).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.6F, 1.1686F, 1.3378F, 1.405F, 0.0F, 0.0F));
		rightHindLeg.addOrReplaceChild("extraCube1_r3", CubeListBuilder.create().texOffs(18, 51).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.6F, 2.6686F, 1.1378F, -0.5236F, 0.0F, 0.0F));
		PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(17, 50).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 51).addBox(-2.1F, 0.3686F, 0.5378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(3.0F, 13.0F, 3.0F));
		leftHindLeg.addOrReplaceChild("extraCube2_r4", CubeListBuilder.create().texOffs(18, 51).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.4F, 1.1686F, 1.3378F, 1.405F, 0.0F, 0.0F));
		leftHindLeg.addOrReplaceChild("extraCube1_r4", CubeListBuilder.create().texOffs(18, 51).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.4F, 2.6686F, 1.1378F, -0.5236F, 0.0F, 0.0F));
	}
	
	public static LayerDefinition createP0BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);		
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.2F, -2.0F, -0.0262F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.2664F, 2.7279F, -1.0635F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-1.5F, -0.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(-0.1674F, 2.7235F, -1.0505F, 0.3491F, -0.1309F, -0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
    // Outer armor (chestplate, helmet, boots)
    public static LayerDefinition createOuterLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.85F), 0.0F);
        PartDefinition partdefinition = mesh.getRoot();
        partdefinition.getChild("body").addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.getChild("body").addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }
    
	protected void moveHead(E entity, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * (Mth.PI / 180F);
		this.head.xRot = headPitch * (Mth.PI / 180F);
		this.hat.copyFrom(this.head);
	}
}

