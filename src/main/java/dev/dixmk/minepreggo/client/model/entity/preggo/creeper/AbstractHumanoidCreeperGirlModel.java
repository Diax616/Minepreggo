package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantFemaleHumanoidModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidCreeperGirlModel<E extends AbstractCreeperGirl> extends PregnantFemaleHumanoidModel<E> {

	public static final ModelLayerLocation LAYER_INNER_ARMOR_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_inner_model"), "inner");
	public static final ModelLayerLocation LAYER_OUTER_ARMOR_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_outer_model"), "outer");
	
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P0_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p0_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P1_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p1_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P2_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p2_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P3_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p3_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P4_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p4_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P5_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p5_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P6_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p6_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P7_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p7_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P8_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_energy_armor_p8_model"), "armor");

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P0 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p0_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P1 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p1_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P2 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p2_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P3 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p3_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P4 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p4_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P5 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p5_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P6 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p6_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P7 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p7_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P8 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "humanoid_creeper_girl_p8_model"), "main");

	protected final ModelPart root;
	protected final HumanoidCreeperGirlAnimator<E> animator;
	protected final EntityJiggleDataFactory.JigglePositionConfig jiggleConfig;
	protected final @Nullable PregnancyPhase pregnancyPhase;
	protected final boolean simpleBellyJiggle;
	
	protected AbstractHumanoidCreeperGirlModel(ModelPart root, HumanoidCreeperGirlAnimator<E> animator, @Nullable PregnancyPhase pregnancyPhase, boolean simpleBellyJiggle) {
		super(root);
		this.root = root;
		this.animator = animator;
		this.pregnancyPhase = pregnancyPhase;
		this.simpleBellyJiggle = simpleBellyJiggle;
		this.jiggleConfig = this.createJiggleConfig();
	}
	
	protected abstract @Nonnull EntityJiggleDataFactory.JigglePositionConfig createJiggleConfig();
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.tryHideBoobs(entity, PregnancySystemHelper::shouldBoobsBeHidden);
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		updateJiggle(entity, pregnancyPhase, jiggleConfig, simpleBellyJiggle);
	}

	protected static void createBasicBodyLayer(PartDefinition partdefinition, float extraLeftArmRotationZ, float extraRightArmRotationZ) {	
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
		.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create()
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));			
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0F, 0F, extraRightArmRotationZ));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
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
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -1.8F, 0.1134F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.2F, 0.1F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.2F, 0.1F, 0.3491F, -0.1309F, -0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP0BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -1.8F, 0.1134F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.2F, 0.1F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.2F, 0.1F, 0.3491F, -0.1309F, -0.0436F));        
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(20, 25).addBox(-3.0F, -0.25F, -0.85F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.75F, -1.5F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP1BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);	
		PartDefinition body = partdefinition.getChild("body");
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(20, 24).addBox(-3.0F, -0.25F, -1.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, -2.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -1.8F, 0.1134F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.2F, 0.1F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.2F, 0.1F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP2BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);	
		PartDefinition body = partdefinition.getChild("body");
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(18, 82).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 6.0F, -2.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, -0.3491F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.9F, 0.3F, 0.1F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(-0.9F, 0.3F, 0.1F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP3BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.0872665F, 0.0872665F);	     
		PartDefinition body = partdefinition.getChild("body");	
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, -0.3054F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-0.0336F, 1.9279F, -0.8635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.1326F, 1.9235F, -0.8505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(17, 82).addBox(-4.0F, -3.0F, -7.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.3F))
		.texOffs(24, 91).addBox(-0.5F, 1.1993F, -7.7305F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 1.0F, 0.0436F, 0.0F, 0.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(30, 87).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.5F, 0.3F, -5.5F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(30, 87).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 1.5F, -5.1F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(30, 87).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 0.4F, -5.0F));
		PartDefinition rightLeg = partdefinition.getChild("right_leg");	
		PartDefinition leftLeg = partdefinition.getChild("left_leg");		
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(32, 66).addBox(-2.0F, -0.95F, -0.95F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.1F, 0.4F, 2.0F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(32, 66).addBox(-2.0F, -0.95F, -0.95F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(-0.1F, 0.4F, 2.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
    	
	public static LayerDefinition createP4BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.0972665F, 0.0972665F);	
		PartDefinition body = partdefinition.getChild("body");	
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.25F, -0.3142F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.25F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-0.0336F, 2.3279F, -1.0635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.25F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.1326F, 2.3236F, -1.0505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(15, 80).addBox(-4.0F, -0.5F, -7.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.35F))
		.texOffs(24, 91).addBox(-0.5F, 3.6993F, -8.2305F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 85).addBox(-4.0F, -0.6F, -7.9F, 8.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 6.0F, -1.0F, 0.0436F, 0.0F, 0.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(20, 87).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.5F, 2.8F, -3.5F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(20, 87).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 4.0F, -3.1F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(20, 87).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 2.9F, -3.0F));
		PartDefinition rightLeg = partdefinition.getChild("right_leg");	
		PartDefinition leftLeg = partdefinition.getChild("left_leg");		
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(32, 66).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.1F, 0.3F, 2.0F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(32, 66).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(-0.1F, 0.3F, 2.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
		
	public static LayerDefinition createP5BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.1309F, 0.1309F);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.9F, -1.8F, -0.3229F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(16, 65).addBox(-2.5F, -1.5F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0664F, 2.3279F, -1.1635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(16, 65).mirror().addBox(-1.5F, -1.5F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.0326F, 2.3236F, -1.1505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(16, 80).addBox(-4.0F, -0.8F, -8.3F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.75F))
		.texOffs(48, 80).addBox(-3.5F, -0.4F, -9.1F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.7F))
		.texOffs(25, 91).addBox(-0.5F, 3.1F, -10.2F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 6.0F, -1.0F, 0.0436F, 0.0F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(22, 89).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 2.8128F, -4.9981F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(22, 89).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 3.9128F, -5.0981F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(22, 89).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.5F, 2.7128F, -5.4981F));
		PartDefinition rightLeg = partdefinition.getChild("right_leg");	
		PartDefinition leftLeg = partdefinition.getChild("left_leg");
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(32, 66).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.1F, 0.3F, 2.3F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(32, 66).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(-0.1F, 0.3F, 2.3F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP6BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.174533F, 0.174533F);	  
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.3316F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(46, 78).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3664F, 2.4779F, -0.7635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(46, 78).mirror().addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2674F, 2.4735F, -0.7505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 79).addBox(-4.0F, 0.25F, -10.5F, 8.0F, 7.0F, 10.0F, new CubeDeformation(0.6F))
		.texOffs(9, 88).addBox(-3.5F, 0.75F, -11.2F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.6F))
		.texOffs(0, 78).addBox(-0.5F, 4.25F, -12.2F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 5.0F, -1.2F, 0.0873F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(8, 70).addBox(-4.0F, -3.5F, -0.5F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.25F, -5.0F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(26, 80).addBox(-5.0F, -3.5F, -0.5F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(0.0F, 7.25F, -5.0F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(36, 89).mirror().addBox(-5.0F, -3.0F, -0.5F, 9.0F, 6.0F, 1.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 3.75F, -5.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube3_r1", CubeListBuilder.create().texOffs(37, 89).addBox(-4.0F, -3.0F, -0.5F, 9.0F, 6.0F, 1.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(4.0F, 3.75F, -5.0F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(8, 90).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.5F, -7.3F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(8, 90).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 4.5F, -5.3F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(8, 90).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 2.5F, -6.3F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(8, 90).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 4.5F, -6.3F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(8, 90).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 2.5F, -4.3F));
		PartDefinition rightLeg = partdefinition.getChild("right_leg");	
		PartDefinition leftLeg = partdefinition.getChild("left_leg");
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(52, 71).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.1F, 0.3F, 2.3F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(52, 71).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offset(-0.1F, 0.3F, 2.3F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}

	public static LayerDefinition createP7BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.2181662F, 0.2181662F);	  
		PartDefinition body = partdefinition.getChild("body");
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-1.0F, -6.75F, -3.0F, 2.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 11.75F, 0.5F, 0.0F, 0.3927F, 0.0F));
		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-1.0F, -6.75F, -3.0F, 2.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 11.75F, 0.5F, 0.0F, -0.3927F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.2356F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("extraRightBoobCube_r1", CubeListBuilder.create().texOffs(40, 71).addBox(-2.492F, -2.2F, -4.1091F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(37, 68).addBox(-2.5F, -2.2F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.2664F, 2.3279F, -0.7635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("extraLetfBoobCube_r1", CubeListBuilder.create().texOffs(40, 71).mirror().addBox(-1.3112F, -2.1957F, -4.1447F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false)
		.texOffs(37, 68).mirror().addBox(-1.5F, -2.1957F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-0.1674F, 2.3236F, -0.7505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 77).addBox(-5.0F, 0.5F, -11.5F, 10.0F, 8.0F, 11.0F, new CubeDeformation(0.7F))
		.texOffs(9, 87).addBox(-4.45F, 1.0F, -11.95F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F))
		.texOffs(9, 87).addBox(-4.5F, 0.9091F, -12.8735F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.3F))
		.texOffs(30, 74).addBox(-0.35F, 5.0F, -13.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 5.0F, -1.5F, 0.1309F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(44, 78).addBox(-4.5F, -3.9739F, -0.3017F, 9.0F, 10.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(0.0F, 0.5F, -5.25F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.4096F, -4.3F, -0.4531F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-0.25F, 8.25F, -5.75F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(9, 88).addBox(-5.2713F, -3.2374F, -0.4F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-4.75F, 4.25F, -5.75F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(9, 88).addBox(-5.5F, -3.5F, -0.5F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(4.75F, 4.5256F, -5.4222F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(5, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-2.0F, 5.1F, -6.8F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(5, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 5.1F, -8.8F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(5, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(2.0F, 4.1F, -7.8F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(5, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-1.0F, 7.1F, -8.8F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(5, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(1.0F, 4.1F, -9.8F));
		PartDefinition rightLeg = partdefinition.getChild("right_leg");	
		PartDefinition leftLeg = partdefinition.getChild("left_leg");
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(13, 66).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(25, 66).addBox(-2.0F, -0.85F, -0.35F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F)), PartPose.offset(0.1F, 0.3F, 2.3F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(13, 66).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(25, 66).addBox(-2.0F, -0.85F, -0.35F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F)), PartPose.offset(-0.1F, 0.3F, 2.3F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP8BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.261799F, 0.261799F);	    
		PartDefinition body = partdefinition.getChild("body");
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(33, 70).mirror().addBox(-1.0F, -7.5F, -3.0F, 2.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 12.0F, 0.5F, 0.0F, 0.3927F, 0.0F));
		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(33, 70).addBox(-1.0F, -7.75F, -3.0F, 2.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 12.0F, 0.5F, 0.0F, -0.3927F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.3142F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("secondExtraRightBoobCube_r1", CubeListBuilder.create().texOffs(51, 71).addBox(-0.8031F, -0.7289F, -4.598F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(48, 90).addBox(-2.5543F, -2.5063F, -4.323F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(45, 87).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.2664F, 3.0279F, -0.5635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("secondExtraRightBoobCube_r2", CubeListBuilder.create().texOffs(51, 71).mirror().addBox(0.1742F, -0.6651F, -4.5287F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(false)
		.texOffs(48, 90).mirror().addBox(-1.4457F, -2.5063F, -4.323F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false)
		.texOffs(45, 87).mirror().addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(-0.1674F, 3.0236F, -0.5505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 74).addBox(-5.0F, -4.65F, -13.25F, 10.0F, 10.0F, 12.0F, new CubeDeformation(1.0F))
		.texOffs(12, 86).addBox(-4.45F, -4.2076F, -13.8735F, 9.0F, 9.0F, 1.0F, new CubeDeformation(1.2F))
		.texOffs(12, 86).addBox(-4.4F, -4.1723F, -14.5617F, 9.0F, 9.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(12, 86).addBox(-3.95F, -3.6254F, -15.1314F, 8.0F, 8.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(46, 72).addBox(-0.5F, 1.8F, -16.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 10.0F, -1.0F, 0.0873F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube8_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.5F, -4.5F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, -4.4F, -7.25F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.5F, -4.8F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, 5.1F, -7.0F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.6731F, -4.8163F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-4.75F, 0.6F, -7.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(12, 86).mirror().addBox(-5.6992F, -4.5174F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(4.75F, 0.35F, -7.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 0.1F, -9.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-2.0F, 0.1F, -7.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(2.0F, -0.9F, -8.0F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-1.0F, 2.1F, -9.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(1.0F, -0.9F, -10.0F));
		PartDefinition rightLeg = partdefinition.getChild("right_leg");	
		PartDefinition leftLeg = partdefinition.getChild("left_leg");
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(46, 75).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F))
		.texOffs(46, 81).addBox(-2.0F, -0.85F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F)), PartPose.offset(0.1F, 0.3F, 2.3F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(46, 75).addBox(-2.0F, -0.85F, -0.75F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F))
		.texOffs(46, 81).addBox(-2.0F, -0.85F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F)), PartPose.offset(-0.1F, 0.3F, 2.3F));
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
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.51F), 0.0F);
        PartDefinition partdefinition = mesh.getRoot();
        partdefinition.getChild("body").addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.getChild("body").addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }   
}
