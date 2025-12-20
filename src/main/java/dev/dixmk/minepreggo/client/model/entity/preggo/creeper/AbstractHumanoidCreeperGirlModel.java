package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantFemaleHumanoidModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.HierarchicalModel;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidCreeperGirlModel<E extends AbstractCreeperGirl> extends PregnantFemaleHumanoidModel<E> {

	public static final ModelLayerLocation LAYER_INNER_ARMOR_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_inner_model"), "inner");
	public static final ModelLayerLocation LAYER_OUTER_ARMOR_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_outer_model"), "outer");
	
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P0_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p0_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P1_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p1_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P2_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p2_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P3_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p3_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P4_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p4_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P5_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p5_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P6_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p6_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P7_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p7_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P8_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p8_model"), "armor");

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P0 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p0_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P1 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p1_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P2 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p2_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P3 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p3_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P4 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p4_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P5 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p5_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P6 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p6_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P7 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p7_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P8 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p8_model"), "main");

	protected final ModelPart root;
	protected final HierarchicalModel<E> animator;
	
	protected AbstractHumanoidCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.tryHideBoobs(entity, PregnancySystemHelper::shouldBoobsBeHidden);
	}

	protected static void createBasicBodyLayer(PartDefinition partdefinition, float extraLeftArmRotationZ, float extraRightArmRotationZ) {	
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
		.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create()
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0F, 0F, extraRightArmRotationZ));		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0F, 0F, extraLeftArmRotationZ));
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);
		PartDefinition boobs = partdefinition.getChild("body").addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.7F, -1.9F, 0.1134F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 1.0F, 2.0F));
		rightBoob.addOrReplaceChild("right_boob_aux", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, 2.0F));
		leftBoob.addOrReplaceChild("left_boob_aux", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.0F, 0.3491F, -0.1309F, -0.0436F));	
        partdefinition.getChild("body").addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP0BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.7F, -1.9F, 0.1134F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 1.0F, 2.0F));
		rightBoob.addOrReplaceChild("right_boob_aux", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, 2.0F));
		leftBoob.addOrReplaceChild("left_boob_aux", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.0F, 0.3491F, -0.1309F, -0.0436F));	
        partdefinition.getChild("body").addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(20, 26).addBox(-3.0F, -0.25F, -1.15F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.75F, -1.5F));
        return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP1BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);	
		PartDefinition body = partdefinition.getChild("body");
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(20, 24).addBox(-3.0F, -3.0F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 8.75F, -2.5F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -1.9F, 0.1134F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.2F, 2.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.2F, 2.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.0F, 0.3491F, -0.1309F, -0.0436F));      
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP2BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);	
		PartDefinition body = partdefinition.getChild("body");
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(18, 82).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 8.5F, -3.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.25F, -0.3491F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.6F, 2.35F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.9F, 0.0F, -2.0F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.6F, 2.35F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(-0.9F, 0.0F, -2.0F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP3BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.0872665F, 0.0872665F);	     
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.55F, -0.3491F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.6F, 2.35F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-0.0336F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.6F, 2.35F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.1326F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(17, 82).addBox(-4.0F, -3.0F, -7.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.3F))
		.texOffs(24, 91).addBox(-0.5F, 1.1993F, -7.7305F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 1.0F, 0.0436F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(32, 66).addBox(-1.9F, -0.35F, 0.85F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(32, 66).addBox(-2.1F, -0.35F, 0.85F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(1.9F, 12.0F, 0.0F));	
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
    	
	public static LayerDefinition createP4BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.0972665F, 0.0972665F);	
		PartDefinition body = partdefinition.getChild("body");	
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.55F, -0.3491F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.6F, 2.35F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-0.0336F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.6F, 2.35F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.1326F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(15, 80).addBox(-4.0F, -3.0F, -9.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.35F))
		.texOffs(24, 91).addBox(-0.5F, 1.1993F, -10.2305F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 85).addBox(-4.0F, -3.1F, -9.9F, 8.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 8.5F, 1.0F, 0.0436F, 0.0F, 0.0F));	
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(32, 66).addBox(-1.9F, -0.35F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(32, 66).addBox(-2.1F, -0.35F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(1.9F, 12.0F, 0.0F));		
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
		
	public static LayerDefinition createP5BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.1309F, 0.1309F);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.3F, -2.55F, -0.3491F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.2F, 2.35F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(16, 65).addBox(-2.5F, -1.5F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-0.0336F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.2F, 2.35F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(16, 65).mirror().addBox(-1.5F, -1.5F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.1326F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(16, 80).addBox(-4.0F, -3.3F, -10.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.75F))
			.texOffs(48, 80).addBox(-3.5F, -2.9F, -10.8F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.7F))
			.texOffs(25, 91).addBox(-0.5F, 0.6F, -11.9F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 8.5F, 0.7F, 0.0436F, 0.0F, 0.0F));   
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
			.texOffs(32, 66).addBox(-1.9F, -0.35F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
			.texOffs(32, 66).addBox(-2.1F, -0.35F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP6BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, -0.174533F, 0.174533F);	
        
		PartDefinition body = partdefinition.getChild("body");
		
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.35F, -2.55F, -0.3316F, 0.0F, 0.0F));

		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.2F, 2.35F));

		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(46, 78).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.1664F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.2F, 2.35F));

		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(46, 78).mirror().addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(-0.0674F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));

		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 80).addBox(-4.0F, -6.5F, -9.0F, 8.0F, 7.0F, 9.0F, new CubeDeformation(0.6F))
		.texOffs(8, 88).addBox(-3.5F, -6.0F, -9.5F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.6F))
		.texOffs(0, 78).addBox(-0.5F, -2.5F, -10.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 11.75F, -1.5F, 0.0873F, 0.0F, 0.0F));

		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(8, 71).addBox(-4.0F, -3.5F, -0.5F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -6.5F, -4.5F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(26, 80).addBox(-4.0F, -3.5F, -0.5F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(0.0F, 0.5F, -4.5F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(35, 89).mirror().addBox(-4.0F, -3.0F, -0.5F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -3.0F, -4.5F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube3_r1", CubeListBuilder.create().texOffs(35, 89).addBox(-4.0F, -3.0F, -0.5F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(4.0F, -3.0F, -4.5F, 0.0F, 1.5708F, 0.0F));
        
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(44, 73).addBox(-1.2F, 0.25F, 2.45F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(52, 71).addBox(-1.85F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(52, 71).addBox(-2.15F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(44, 73).addBox(-1.8F, 0.25F, 2.45F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		
		return LayerDefinition.create(meshdefinition, 64, 96);
	}

	public static LayerDefinition createP7BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, -0.2181662F, 0.2181662F);	
        
		PartDefinition body = partdefinition.getChild("body");
		
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-1.0F, -6.75F, -3.0F, 2.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 11.75F, 0.5F, 0.0F, 0.3927F, 0.0F));

		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-1.0F, -6.75F, -3.0F, 2.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 11.75F, 0.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.4F, -2.45F, -0.2793F, 0.0F, 0.0F));

		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.1F, 0.2F, 2.35F));

		rightBoob.addOrReplaceChild("extraRightBoobCube_r1", CubeListBuilder.create().texOffs(40, 71).addBox(-2.5189F, -2.4982F, -4.5196F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(37, 68).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.1664F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.1F, 0.2F, 2.35F));

		leftBoob.addOrReplaceChild("extraLetfBoobCube_r1", CubeListBuilder.create().texOffs(40, 71).mirror().addBox(-1.4811F, -2.4982F, -4.5196F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false)
		.texOffs(37, 68).mirror().addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(-0.0674F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));

		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 78).addBox(-5.0F, -6.25F, -10.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.7F))
		.texOffs(9, 87).addBox(-4.45F, -5.75F, -10.55F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F))
		.texOffs(9, 87).addBox(-4.5F, -5.8409F, -11.3735F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.3F))
		.texOffs(30, 74).addBox(-0.35F, -1.75F, -12.4F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 11.75F, -1.5F, 0.1309F, 0.0F, 0.0F));

		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(31, 78).addBox(-4.5F, -3.9739F, -0.3017F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(0.0F, -6.25F, -4.75F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(9, 86).addBox(-4.4096F, -4.3F, -0.4531F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-0.25F, 1.5F, -5.25F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(10, 88).addBox(-4.2713F, -3.2374F, -0.4F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-4.75F, -2.5F, -5.25F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(10, 88).addBox(-4.5F, -3.5F, -0.5F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(4.75F, -2.2244F, -4.9222F, 0.0F, -1.5708F, 0.0F));
        
		partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(13, 66).addBox(-1.95F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(13, 73).addBox(-1.4F, 0.25F, 2.45F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.05F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(13, 66).addBox(-2.05F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(13, 73).addBox(-1.7F, 0.25F, 2.45F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.05F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP8BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, -0.261799F, 0.261799F);	
        
		PartDefinition body = partdefinition.getChild("body");
		
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(33, 70).mirror().addBox(-1.0F, -7.5F, -3.0F, 2.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 12.0F, 0.5F, 0.0F, 0.3927F, 0.0F));

		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(33, 70).addBox(-1.0F, -7.75F, -3.0F, 2.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 12.0F, 0.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.1F, -2.65F, -0.384F, 0.0F, 0.0F));

		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.1F, 0.2F, 2.35F));

		rightBoob.addOrReplaceChild("secondExtraRightBoobCube_r1", CubeListBuilder.create().texOffs(51, 71).addBox(-0.7677F, -0.7209F, -4.7947F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(48, 90).addBox(-2.5189F, -2.4982F, -4.5196F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(45, 87).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(0.1664F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.1F, 0.2F, 2.35F));

		leftBoob.addOrReplaceChild("secondExtraRightBoobCube_r2", CubeListBuilder.create().texOffs(51, 71).mirror().addBox(0.1388F, -0.657F, -4.7254F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(false)
		.texOffs(48, 90).mirror().addBox(-1.4811F, -2.4982F, -4.5196F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false)
		.texOffs(45, 87).mirror().addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(-0.0674F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));

		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 74).addBox(-5.0F, -6.5F, -12.5F, 10.0F, 10.0F, 12.0F, new CubeDeformation(1.0F))
		.texOffs(12, 86).addBox(-4.45F, -6.0576F, -13.1235F, 9.0F, 9.0F, 1.0F, new CubeDeformation(1.2F))
		.texOffs(12, 86).addBox(-4.4F, -6.0223F, -13.8117F, 9.0F, 9.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(12, 86).addBox(-3.95F, -5.4754F, -14.3814F, 8.0F, 8.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(46, 72).addBox(-0.5F, -0.05F, -15.85F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 11.75F, -1.75F, 0.0873F, 0.0F, 0.0F));

		belly.addOrReplaceChild("bellyCube8_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.5F, -4.5F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, -6.25F, -6.5F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.5F, -4.8F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, 3.25F, -6.25F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.6731F, -4.8163F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-4.75F, -1.25F, -6.25F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(12, 86).mirror().addBox(-5.6992F, -4.5174F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(4.75F, -1.5F, -6.25F, 0.0F, -1.5708F, 0.0F));
        
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(46, 75).addBox(-2.25F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.1F))
		.texOffs(46, 81).addBox(-1.7F, 0.25F, 2.55F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(46, 75).addBox(-1.75F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.1F))
		.texOffs(46, 81).addBox(-1.3F, 0.25F, 2.55F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		
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
	
    // Inner armor (leggings layer)
    public static LayerDefinition createInnerLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.35F), 0.0F);
        PartDefinition partdefinition = mesh.getRoot();
        partdefinition.getChild("body").addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.getChild("body").addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }
    
    
}
