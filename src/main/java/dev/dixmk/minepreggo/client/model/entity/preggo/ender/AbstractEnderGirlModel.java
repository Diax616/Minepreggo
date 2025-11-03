package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantFemaleHumanoidModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderGirl;
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
public abstract class AbstractEnderGirlModel<E extends AbstractEnderGirl> extends PregnantFemaleHumanoidModel<E>{
	public static final ModelLayerLocation LAYER_INNER_ARMOR_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "ender_girl_inner_model"), "inner");
	public static final ModelLayerLocation LAYER_OUTER_ARMOR_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "ender_girl_outer_model"), "outer");	
	
	public static final ModelLayerLocation LAYER_LOCATION_P0 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "ender_girl_p0_model"), "main");
	protected final ModelPart root;
	protected final HierarchicalModel<E> animator;
	
	protected AbstractEnderGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
	}
	
	protected static void createBasicBodyLayer(PartDefinition partdefinition, float extraLeftArmRotationZ, float extraRightArmRotationZ) {
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
		.texOffs(0, 48).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -15.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, -15.0F, 0.0F));	
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 16).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(34, 26).addBox(-3.35F, 4.0F, -1.45F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(33, 32).addBox(-4.0F, 7.0F, -1.4F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -13.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-1.0F, -3.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -12.0F, 0.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(56, 13).addBox(-1.0F, 12.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(-1.0F, 1.5F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.25F))
		.texOffs(56, 11).mirror().addBox(-1.0F, -0.5F, 0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(-2.0F, -4.0F, 0.0F));
		rightLeg.addOrReplaceChild("extraCube2_r1", CubeListBuilder.create().texOffs(56, 13).mirror().addBox(-1.0F, -3.5F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.05F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.8993F, 0.248F, -0.0873F, 0.0F, 0.0F));
		rightLeg.addOrReplaceChild("extraCube3_r1", CubeListBuilder.create().texOffs(57, 1).mirror().addBox(-1.0F, -6.5F, -0.5F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0343F, 1.6212F, -0.0611F, 0.0F, 0.0F));
		rightLeg.addOrReplaceChild("extraCube1_r1", CubeListBuilder.create().texOffs(56, 13).mirror().addBox(-1.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.05F)).mirror(false), PartPose.offsetAndRotation(0.0F, 18.1066F, 0.8006F, -0.0698F, 0.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-1.0F, 1.5F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.35F)).mirror(false)
		.texOffs(56, 13).mirror().addBox(-1.0F, 12.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 11).mirror().addBox(-1.0F, -0.5F, 0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(2.0F, -4.0F, 0.0F));
		leftLeg.addOrReplaceChild("extraCube3_r2", CubeListBuilder.create().texOffs(57, 1).mirror().addBox(-1.0F, -8.5F, 0.0F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 10.0F, 1.0F, -0.0611F, 0.0F, 0.0F));
		leftLeg.addOrReplaceChild("extraCube2_r2", CubeListBuilder.create().texOffs(56, 13).mirror().addBox(-1.0F, -3.5F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.05F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.8993F, 0.248F, -0.0873F, 0.0F, 0.0F));
		leftLeg.addOrReplaceChild("extraCube1_r2", CubeListBuilder.create().texOffs(56, 13).mirror().addBox(-1.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.05F)).mirror(false), PartPose.offsetAndRotation(0.0F, 21.5981F, 0.5564F, -0.0698F, 0.0F, 0.0F));
	}
	
	public static LayerDefinition createP0BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -1.4F, -0.1134F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.4859F, -1.9865F, -1.8856F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.223F, 0.6825F, -1.2889F, 0.3491F, -0.1309F, -0.0436F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5141F, -1.9865F, -1.8856F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.7746F, 0.6934F, -1.3216F, 0.3491F, 0.1309F, 0.0436F));	
		body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));     
		return LayerDefinition.create(meshdefinition, 64, 64);
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
    
    @Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		this.moveHeadWithHat(entity, netHeadYaw, headPitch);	
		if (entity.isCreepy()) {
			this.head.y -= 5F;
		} 
	}
}
