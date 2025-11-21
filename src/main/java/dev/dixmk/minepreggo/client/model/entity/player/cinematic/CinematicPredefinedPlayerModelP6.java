package dev.dixmk.minepreggo.client.model.entity.player.cinematic;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.preggo.HumanoidFemaleAnimation;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.client.model.HierarchicalModel;
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
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CinematicPredefinedPlayerModelP6 extends HierarchicalModel<AbstractClientPlayer> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "cinematic_predefined_pregnant_body_p6_model"), "main");
	public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/cinematic/cinematic_player_1_p6.png");

	private final ModelPart root;
	protected final ModelPart head;
	protected final ModelPart hat;
	protected final ModelPart body;
	protected final ModelPart rightArm;
	protected final ModelPart leftArm;
	protected final ModelPart rightLeg;
	protected final ModelPart leftLeg;
	
	protected final AnimationState loopAnimationState = new AnimationState();

	public CinematicPredefinedPlayerModelP6(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
		this.hat = root.getChild("hat");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.2618F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, -0.3F, 0.0F));
		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(35, 87).addBox(-2.5F, -2.5F, -3.4F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3664F, 2.5279F, -0.8635F, 0.3491F, 0.1745F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, -0.3F, 0.0F));
		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(35, 87).mirror().addBox(-1.5F, -2.5F, -3.413F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2674F, 2.5236F, -0.8505F, 0.3491F, -0.1745F, -0.0436F));
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 80).addBox(-4.0F, 0.25F, -9.5F, 8.0F, 7.0F, 9.0F, new CubeDeformation(0.6F))
		.texOffs(9, 77).addBox(-3.5F, 0.75F, -10.0F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.6F))
		.texOffs(10, 92).addBox(-0.5F, 4.25F, -11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 0.0873F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(8, 75).addBox(-4.0F, -4.0F, -0.5F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.25F, -5.0F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(9, 81).addBox(-4.0F, -3.5F, -0.5F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(0.0F, 7.25F, -5.0F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(8, 75).mirror().addBox(-4.0F, -3.0F, -0.5F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 3.75F, -5.0F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube3_r1", CubeListBuilder.create().texOffs(8, 75).addBox(-4.0F, -3.0F, -0.5F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(4.0F, 3.75F, -5.0F, 0.0F, 1.5708F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(7, 90).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 2.5F, -4.5F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(7, 90).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 4.5F, -6.5F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(7, 90).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 2.5F, -6.5F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(7, 90).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 4.5F, -5.5F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(7, 90).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.5F, -7.5F));
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		rightLeg.addOrReplaceChild("right_butt", CubeListBuilder.create().texOffs(38, 80).addBox(-2.105F, -0.35F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.15F)), PartPose.offset(0.1F, 0.0F, 1.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		leftLeg.addOrReplaceChild("left_butt", CubeListBuilder.create().texOffs(38, 80).mirror().addBox(-1.95F, -0.35F, -0.05F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.15F)).mirror(false), PartPose.offset(-0.1F, 0.0F, 1.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}

	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		
		if (entity.level().isClientSide && !this.loopAnimationState.isStarted()) {
			this.loopAnimationState.start(entity.tickCount);
		}
		
		entity.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
			if (cap.isCinamatic()) {
				this.animate(this.loopAnimationState, HumanoidFemaleAnimation.Cinematic.SEX_COW_GIRL, ageInTicks);
			}
		});
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
