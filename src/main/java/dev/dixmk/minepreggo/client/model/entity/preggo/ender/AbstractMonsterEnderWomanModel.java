package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantFemaleHumanoidModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterEnderWomanModel<E extends AbstractEnderWoman> extends PregnantFemaleHumanoidModel<E>{
	public static final ModelLayerLocation LAYER_INNER_ARMOR_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_inner_model"), "inner");
	public static final ModelLayerLocation LAYER_OUTER_ARMOR_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_outer_model"), "outer");	
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P0 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p0"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P1 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p1"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P2 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p2"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P3 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p3"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P4 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p4"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P5 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p5"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P6 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p6"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P7 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p7"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P8 = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "monster_ender_woman_model_p8"), "main");

	protected final ModelPart root;
	protected final MonsterEnderWomanAnimator<E> animator;
	protected final EntityJiggleDataFactory.JigglePositionConfig jiggleConfig;
	protected final @Nullable ImmutablePair<PregnancyPhase, Boolean> pregnancyPhaseAndSimpleBellyJiggle;

	protected AbstractMonsterEnderWomanModel(ModelPart root, MonsterEnderWomanAnimator<E> animator, @Nullable ImmutablePair<PregnancyPhase, Boolean> pregnancyPhaseAndSimpleBellyJiggle) {
		super(root);
		this.root = root;
		this.animator = animator;
		this.pregnancyPhaseAndSimpleBellyJiggle = pregnancyPhaseAndSimpleBellyJiggle;
		this.jiggleConfig = this.createJiggleConfig();
	}
	
	protected abstract @Nonnull EntityJiggleDataFactory.JigglePositionConfig createJiggleConfig();
	
	protected static void createBasicBodyLayer(PartDefinition partdefinition, float extraLeftArmRotationZ, float extraRightArmRotationZ) {
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
		.texOffs(0, 48).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -15.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, -15.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 16).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(34, 26).addBox(-3.95F, 3.7F, -1.45F, 8.0F, 4.0F, 3.0F, new CubeDeformation(-0.3F))
		.texOffs(33, 33).addBox(-4.0F, 8.0F, -1.4F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.1F))
		.texOffs(33, 32).addBox(-4.0F, 7.1F, -1.45F, 8.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -13.0F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, -1.8F, -0.0262F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, -2.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.123F, 2.1858F, -1.3455F, 0.3491F, -0.1309F, -0.0436F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, -2.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.8746F, 2.1966F, -1.3782F, 0.3491F, 0.1309F, 0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -12.0F, 0.0F, 0F, 0F, extraRightArmRotationZ));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-1.0F, -3.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -12.0F, 0.0F, 0F, 0F, extraLeftArmRotationZ));
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
	
	protected static void createEmptyBody(PartDefinition partdefinition) {
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));
	}
	
	public static LayerDefinition createBasicBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP0BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP0(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	protected static void addBellyP0(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, -1.8F, -0.0262F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, -2.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.123F, 2.1858F, -1.3455F, 0.3491F, -0.1309F, -0.0436F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, -2.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.8746F, 2.1966F, -1.3782F, 0.3491F, 0.1309F, 0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(10, 70).addBox(-2.4F, 0.0F, -1.2F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 4.0F, -1.0F));
	}
	
	public static LayerDefinition createBellyP0Layer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP0(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP1BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP1(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	protected static void addBellyP1(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, -1.9F, -0.0262F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(0.023F, 2.1858F, -1.3455F, 0.3491F, -0.1309F, -0.0436F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-1.2F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(-0.6254F, 2.1966F, -1.3782F, 0.3491F, 0.1309F, 0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(10, 70).addBox(-3.0F, 0.0F, -1.6F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.3F)), PartPose.offset(0.1F, 4.3F, -0.8F));
	}
	
	public static LayerDefinition createBellyP1Layer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP1(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP2BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP2(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}

	protected static void addBellyP2(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, -1.9F, -0.0262F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(0.023F, 2.1858F, -1.3455F, 0.3491F, -0.1309F, -0.0436F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.7746F, 2.1966F, -1.3782F, 0.3491F, 0.1309F, 0.0436F));
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(8, 68).addBox(-2.9F, -0.2F, -3.2F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 4.0F, -1.0F));
	}
	
	public static LayerDefinition createBellyP2Layer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP2(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP3BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.021816625F, 0.021816625F);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP3(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	protected static void addBellyP3(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.3F, -2.0F, -0.288F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(0.123F, 2.1858F, -1.3455F, 0.3491F, -0.1309F, -0.0436F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.6746F, 2.1966F, -1.3782F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -1.8F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(8, 71).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.4F)), PartPose.offset(0.5F, 4.0F, -2.3F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(8, 71).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.4F)), PartPose.offset(1.5F, 2.8F, -2.7F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(8, 71).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.4F)), PartPose.offset(-0.5F, 2.9F, -2.2F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(29, 75).addBox(1.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, 2.1F, -0.2F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r2", CubeListBuilder.create().texOffs(6, 66).addBox(-4.0F, -3.0F, -5.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(1.1F, 3.3F, 0.5F, 0.0524F, 0.0F, 0.0F));
	}
	
	public static LayerDefinition createBellyP3Layer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP3(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP4BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.024316625F, 0.024316625F);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP4(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	protected static void addBellyP4(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.3F, -2.0F, -0.2967F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.223F, 2.1858F, -1.3455F, 0.3491F, -0.1309F, -0.0436F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.5746F, 2.1966F, -1.3782F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -0.8F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(8, 72).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 4.8F, -2.7F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(8, 72).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 6.0F, -2.3F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(8, 72).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 4.9F, -2.2F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(29, 75).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 5.8321F, -7.263F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(9, 70).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.2F, 4.0872F, -6.9663F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r2", CubeListBuilder.create().texOffs(4, 65).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.2F, 3.457F, -3.4959F, 0.0524F, 0.0F, 0.0F));
	}
	
	public static LayerDefinition createBellyP4Layer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP4(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP5BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.032725F, 0.032725F);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP5(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	protected static void addBellyP5(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.5F, -2.1F, -0.2967F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.35F)).mirror(false), PartPose.offsetAndRotation(0.323F, 2.1858F, -1.3455F, 0.3491F, -0.1309F, -0.0436F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(0.4746F, 2.1966F, -1.3782F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -1.6F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(7, 72).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 3.8128F, -4.3981F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(7, 72).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 4.9128F, -4.4981F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(7, 72).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.5F, 3.7128F, -4.8981F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(29, 75).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 6.0321F, -7.763F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(8, 70).addBox(-4.5F, -3.5F, -0.5F, 8.0F, 7.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.7F, 4.2872F, -7.4663F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r2", CubeListBuilder.create().texOffs(2, 64).addBox(-4.5F, -3.0F, -4.0F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.6F, 3.457F, -2.7959F, 0.0524F, 0.0F, 0.0F));
	}
	
	public static LayerDefinition createBellyP5Layer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP5(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP6BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.04363325F, 0.04363325F);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP6(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	protected static void addBellyP6(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.9F, -2.1F, -0.2967F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.323F, 2.1858F, -1.3455F, 0.3514F, -0.1719F, -0.0588F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(0.4746F, 2.1966F, -1.3782F, 0.3514F, 0.1719F, 0.0588F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.0F, -2.0F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(7, 76).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(-0.5F, 6.502F, -4.2731F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(7, 76).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, 3.502F, -6.2731F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(7, 76).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.5F, 4.502F, -5.2731F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(7, 76).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.5F, 6.502F, -5.2731F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(7, 76).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(-2.0F, 4.502F, -3.2731F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(29, 75).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 7.1321F, -9.463F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra5_r1", CubeListBuilder.create().texOffs(0, 65).addBox(-4.0F, -4.0F, -4.5F, 8.0F, 1.0F, 9.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.1F, 3.6341F, -3.9415F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra4_r1", CubeListBuilder.create().texOffs(0, 65).addBox(-4.0F, -0.5F, -4.5F, 8.0F, 1.0F, 9.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.1F, 8.8294F, -3.7583F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra3_r1", CubeListBuilder.create().texOffs(7, 65).addBox(-0.5F, -4.0F, -4.5F, 1.0F, 8.0F, 9.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 65).addBox(-9.6F, -4.0F, -4.5F, 1.0F, 8.0F, 9.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 65).addBox(-8.6F, -4.0F, -4.5F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(4.7F, 4.5341F, -3.9415F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(8, 73).addBox(-4.0F, -4.0F, -0.5F, 8.0F, 8.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.2F, 4.7865F, -9.1401F, 0.0524F, 0.0F, 0.0F));
	}
	
	public static LayerDefinition createBellyP6Layer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP6(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
		
	public static LayerDefinition createP7BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.05454155F, 0.05454155F);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP7(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	protected static void addBellyP7(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.1F, -2.3F, -0.2618F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("leftBoobCube3_r1", CubeListBuilder.create().texOffs(42, 45).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(0.9737F, 3.3742F, -3.7523F, 0.3514F, -0.1719F, -0.0588F));
		leftBoob.addOrReplaceChild("leftBoobCube2_r1", CubeListBuilder.create().texOffs(40, 41).mirror().addBox(-1.4995F, -1.5999F, -1.9951F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(0.623F, 2.4858F, -1.9455F, 0.3514F, -0.1719F, -0.0588F));
		leftBoob.addOrReplaceChild("leftBoobCube1_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.523F, 2.1858F, -1.3455F, 0.3514F, -0.1719F, -0.0588F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("rightCubeBoob3_r1", CubeListBuilder.create().texOffs(42, 45).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.209F, 3.4008F, -3.827F, 0.3514F, 0.1719F, 0.0588F));
		rightBoob.addOrReplaceChild("rightCubeBoob2_r1", CubeListBuilder.create().texOffs(40, 41).addBox(-1.5005F, -1.5999F, -1.9951F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.1746F, 2.4966F, -2.0782F, 0.3514F, 0.1719F, 0.0588F));
		rightBoob.addOrReplaceChild("rightCubeBoob1_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.2746F, 2.1966F, -1.3782F, 0.3514F, 0.1719F, 0.0588F));	
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.0F, -1.7F, 0.0262F, 0.0F, 0.0F));	
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(41, 86).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 7.4321F, -11.663F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra5_r1", CubeListBuilder.create().texOffs(5, 69).addBox(-4.0F, -4.0F, -5.5F, 8.0F, 1.0F, 10.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.1F, 3.6341F, -4.4415F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra4_r1", CubeListBuilder.create().texOffs(5, 69).addBox(-4.0F, -0.5F, -5.5F, 8.0F, 1.0F, 10.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.1F, 9.9293F, -4.2583F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra3_r1", CubeListBuilder.create().texOffs(12, 69).addBox(-0.5F, -4.0F, -5.5F, 1.0F, 9.0F, 10.0F, new CubeDeformation(-0.1F))
		.texOffs(5, 69).addBox(-9.6F, -4.0F, -5.5F, 1.0F, 9.0F, 10.0F, new CubeDeformation(-0.1F))
		.texOffs(5, 69).addBox(-8.6F, -4.0F, -5.5F, 8.0F, 9.0F, 10.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(4.7F, 4.5341F, -4.4415F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra2_r1", CubeListBuilder.create().texOffs(14, 78).addBox(-4.0F, -4.0F, -0.5F, 8.0F, 9.0F, 1.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.2F, 4.9865F, -11.3401F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(14, 78).addBox(-4.0F, -4.0F, -0.5F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.2F, 4.9865F, -10.5401F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(11, 80).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offset(-2.0F, 4.1055F, -6.1812F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(11, 80).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 4.1055F, -8.1812F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(11, 80).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offset(2.0F, 3.1055F, -7.1812F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(11, 80).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offset(-1.0F, 6.1055F, -8.1812F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(11, 80).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offset(1.0F, 3.1055F, -9.1812F));
	}
	
	public static LayerDefinition createBellyP7Layer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP7(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP8BodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, -0.06544975F, 0.06544975F);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP8(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	protected static void addBellyP8(PartDefinition body) {
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.1F, -2.4F, -0.2793F, 0.0F, 0.0F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.2F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("extra2_r1", CubeListBuilder.create().texOffs(42, 45).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(0.8737F, 3.4742F, -3.7523F, 0.3514F, -0.1719F, -0.0588F));
		leftBoob.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(40, 41).mirror().addBox(-1.4995F, -1.5999F, -1.9951F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(0.523F, 2.5858F, -1.9455F, 0.3514F, -0.1719F, -0.0588F));
		leftBoob.addOrReplaceChild("leftBoobCube_r1", CubeListBuilder.create().texOffs(37, 38).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offsetAndRotation(0.423F, 2.1858F, -1.3455F, 0.3514F, -0.1719F, -0.0588F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.7F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("extra2_r2", CubeListBuilder.create().texOffs(42, 45).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.109F, 3.5008F, -3.827F, 0.3514F, 0.1719F, 0.0588F));
		rightBoob.addOrReplaceChild("extra1_r2", CubeListBuilder.create().texOffs(40, 41).addBox(-1.5005F, -1.5999F, -1.9951F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.2746F, 2.5966F, -2.0782F, 0.3514F, 0.1719F, 0.0588F));
		rightBoob.addOrReplaceChild("rightCubeBoob_r1", CubeListBuilder.create().texOffs(37, 38).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(0.3746F, 2.1966F, -1.3782F, 0.3514F, 0.1719F, 0.0588F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.0F, -1.4F, 0.0262F, 0.0F, 0.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(1.0F, 5.1048F, -9.2335F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(-1.0F, 8.1048F, -8.2335F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(2.0F, 5.1048F, -7.2335F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(-2.0F, 6.1048F, -6.2335F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(12, 88).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 6.1048F, -8.2335F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(41, 86).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 8.9321F, -14.163F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra5_r1", CubeListBuilder.create().texOffs(3, 67).addBox(-4.0F, -4.0F, -7.5F, 8.0F, 1.0F, 12.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.6F, 3.6341F, -4.5415F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra4_r1", CubeListBuilder.create().texOffs(2, 67).addBox(-5.0F, -0.5F, -7.5F, 9.0F, 1.0F, 12.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.6F, 10.9293F, -4.3583F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra3_r1", CubeListBuilder.create().texOffs(10, 67).addBox(-0.5F, -4.0F, -7.5F, 1.0F, 10.0F, 12.0F, new CubeDeformation(-0.1F))
		.texOffs(3, 67).addBox(-10.6F, -4.0F, -7.5F, 1.0F, 10.0F, 12.0F, new CubeDeformation(-0.1F))
		.texOffs(2, 67).addBox(-9.6F, -4.0F, -7.5F, 9.0F, 10.0F, 12.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(5.2F, 4.5341F, -4.5415F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra2_r3", CubeListBuilder.create().texOffs(13, 78).addBox(-5.0F, -4.0F, -0.5F, 9.0F, 10.0F, 1.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.7F, 4.9865F, -13.8401F, 0.0524F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r3", CubeListBuilder.create().texOffs(13, 78).addBox(-5.0F, -4.0F, -0.5F, 9.0F, 10.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.7F, 4.9865F, -13.0401F, 0.0524F, 0.0F, 0.0F));
	}
	
	public static LayerDefinition createBellyP8Layer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createEmptyBody(partdefinition);
		PartDefinition body = partdefinition.getChild("body");
		addBellyP8(body);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
    @Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);				
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHeadWithHat(entity, netHeadYaw, headPitch);
		}
		
		if (entity.isCreepy()) {
			this.head.y -= 5F;
		} 

		updateJiggle(entity, pregnancyPhaseAndSimpleBellyJiggle, jiggleConfig);
	}
    
    @Override
	public void translateToHand(HumanoidArm p_102854_, PoseStack p_102855_) {
    	super.translateToHand(p_102854_, p_102855_);
    	p_102855_.translate(p_102854_ == HumanoidArm.LEFT ? -0.05f : 0.05f, 0.85f, 0f);
	}
}
