package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.jiggle.EntityJiggleData;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlModel<E extends AbstractCreeperGirl> extends CreeperModel<E> implements HeadedModel {	
	public static final ModelLayerLocation LAYER_OUTER_ARMOR_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_outer_model"), "outer");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P0_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p0_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P1_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p1_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P2_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p2_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P3_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p3_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P4_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p4_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P5_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p5_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P6_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p6_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P7_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p7_armor_model"), "armor");
	public static final ModelLayerLocation LAYER_ENERGY_ARMOR_P8_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_energy_p8_armor_model"), "armor");
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_model"), "main");
	
	public static final ModelLayerLocation LAYER_LOCATION_P0 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p0_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P1 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p1_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P2 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p2_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P3 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p3_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P4 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p4_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P5 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p5_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P6 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p6_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P7 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p7_model"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_P8 = new ModelLayerLocation(MinepreggoHelper.fromThisNamespaceAndPath("monster_creeper_girl_p8_model"), "main");
	
	public final ModelPart hat;
	public final ModelPart head;
	public final ModelPart belly;
	public final ModelPart boobs;
	public final ModelPart leftBoob;
	public final ModelPart rightBoob;
	protected final EntityJiggleDataFactory.JigglePositionConfig jiggleConfig;
	protected final @Nullable ImmutablePair<PregnancyPhase, Boolean> pregnancyPhaseAndSimpleBellyJiggle;
	
	protected AbstractMonsterCreeperGirlModel(ModelPart root, @Nullable ImmutablePair<PregnancyPhase, Boolean> pregnancyPhaseAndSimpleBellyJiggle) {
		super(root);
		this.hat = root().getChild("hat");
		this.head = root().getChild("head");	
		var body = root().getChild("body");	
		this.belly = body.getChild("belly");
		this.boobs = body.getChild("boobs");
		this.leftBoob = boobs.getChild("left_boob");
		this.rightBoob = boobs.getChild("right_boob");
		this.pregnancyPhaseAndSimpleBellyJiggle = pregnancyPhaseAndSimpleBellyJiggle;
		this.jiggleConfig = createJiggleConfig();
	}
	
	protected void updateJiggle(E entity) {
		if (entity.isBaby()) {
			return;
		}
		
		final PregnancyPhase pregnancyPhase;
		final boolean simpleBellyJiggle;
		
		if (pregnancyPhaseAndSimpleBellyJiggle != null) {
			pregnancyPhase = pregnancyPhaseAndSimpleBellyJiggle.getLeft();
			simpleBellyJiggle = pregnancyPhaseAndSimpleBellyJiggle.getRight();
		}
		else {
			pregnancyPhase = null;
			simpleBellyJiggle = false;
		}

		final var armor = entity.getItemBySlot(EquipmentSlot.CHEST);
		EntityJiggleData jiggleData = JigglePhysicsManager.getInstance().getOrCreate(entity, () -> EntityJiggleDataFactory.create(jiggleConfig, pregnancyPhase));
		if (armor.isEmpty()) {
			jiggleData.getBoobsJiggle().setupAnim(entity, boobs, leftBoob, rightBoob);			
			jiggleData.getBellyJiggle().ifPresent(jiggle -> jiggle.setupAnim(entity, belly, simpleBellyJiggle));
		}
		else { 		
			if (armor.getItem() instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed()) {
				jiggleData.getBoobsJiggle().setupAnim(entity, boobs, leftBoob, rightBoob);
			}
		}
	}
	
	protected abstract @Nonnull EntityJiggleDataFactory.JigglePositionConfig createJiggleConfig();
	
	protected static void createBasicBodyLayer(PartDefinition partdefinition) {	
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 3.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 1.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(18, 21).addBox(-3.5F, 5.0F, -1.5F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 26).addBox(-4.0F, 8.0F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(16, 24).addBox(-4.0F, 6.7F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 3.0F, 0.0F));
		body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.8F, -2.0F, -0.0262F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.57F, 1.6687F, -0.7244F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(-0.671F, 1.6643F, -0.7114F, 0.3491F, -0.1309F, -0.0436F));
		partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(1, 24).addBox(-1.9F, 6.0F, -2.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(1, 18).addBox(-1.9F, 0.2F, -2.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(-3.0F, 13.0F, -2.9F));
		partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(17, 56).addBox(-2.1F, 6.0F, -2.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(17, 50).addBox(-2.1F, 0.2F, -2.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(3.0F, 13.0F, -2.9F));
		partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(1, 24).addBox(-1.9F, 6.0F, -1.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(1, 18).addBox(-1.9F, 0.2F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(-3.0F, 13.0F, 2.9F));
		partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(17, 56).addBox(-2.1F, 6.0F, -1.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(17, 50).addBox(-2.1F, 0.2F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(3.0F, 13.0F, 2.9F));
	}
	
	public static LayerDefinition createBasicBodyLayer() {	
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP0BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(34, 59).addBox(-2.5F, -0.8F, -2.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP1BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(33, 58).addBox(-2.5F, -0.6F, -3.6F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP2BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, -1.0F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(0, 75).addBox(-3.0F, 0.0F, -3.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -0.4825F, -0.1999F, 0.0175F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP3BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 4.2F, -1.0F));
		belly.addOrReplaceChild("button_r1", CubeListBuilder.create().texOffs(24, 81).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0696F, -6.1732F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(0, 73).addBox(-4.5F, 0.0F, -5.5F, 7.0F, 5.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.0F, 0.5F, -0.2F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(6, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(2.0F, 2.3F, -3.5F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(6, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 2.8F, -3.1F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(6, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 2.7F, -3.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.7F, -2.0F, -0.3316F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(1, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.4701F, 1.6687F, -0.7244F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(1, 65).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(-0.571F, 1.6643F, -0.7114F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP4BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.2F, -1.0F, 0.0349F, 0.0F, 0.0F));
		belly.addOrReplaceChild("button_r1", CubeListBuilder.create().texOffs(26, 81).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0696F, -7.8732F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(5, 77).addBox(-4.5F, -3.0F, -5.5F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 3.4F, -2.2F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(0, 72).addBox(-4.5F, -3.0F, -5.5F, 7.0F, 5.0F, 6.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(1.0F, 3.4F, -1.2F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(7, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.1F, 1.9F, -3.5F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(7, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 2.8F, -3.1F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(7, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.9F, 2.4F, -3.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.0F, -0.2967F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(1, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4701F, 1.6687F, -0.7244F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(1, 65).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.571F, 1.6643F, -0.7114F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	
	public static LayerDefinition createP5BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.2F, -1.0F, 0.0349F, 0.0F, 0.0F));
		belly.addOrReplaceChild("button_r1", CubeListBuilder.create().texOffs(28, 81).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0696F, -8.8732F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(6, 77).addBox(-4.5F, -3.0F, -5.5F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 3.4F, -3.2F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(0, 71).addBox(-4.5F, -3.0F, -6.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(1.0F, 3.4F, -1.2F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(8, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.15F)), PartPose.offset(0.5F, 2.3128F, -4.9981F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(8, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.15F)), PartPose.offset(0.5F, 2.7128F, -5.0981F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(8, 77).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.0F, 2.5128F, -4.4981F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.0F, -0.2967F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(1, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4701F, 1.6687F, -0.7244F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(1, 65).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.571F, 1.6643F, -0.7114F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP6BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.2F, -2.0F, 0.0349F, 0.0F, 0.0F));
		belly.addOrReplaceChild("button_r1", CubeListBuilder.create().texOffs(30, 83).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5696F, -8.8732F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(6, 78).addBox(-4.5F, -3.0F, -5.5F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.4F, -3.2F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra2_r1", CubeListBuilder.create().texOffs(7, 72).mirror().addBox(-0.5F, -3.0F, -4.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(1, 72).mirror().addBox(-7.7F, -3.0F, -4.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.6F, 2.5611F, -3.7995F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra4_r1", CubeListBuilder.create().texOffs(3, 74).mirror().addBox(-4.5F, 2.0F, -7.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 3.1F, -0.3F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra5_r1", CubeListBuilder.create().texOffs(1, 72).mirror().addBox(-4.5F, -3.0F, -7.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.9F, -0.3F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(0, 71).addBox(-4.5F, -3.0F, -7.5F, 7.0F, 6.0F, 8.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(1.0F, 2.5F, -0.3F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(1.5F, 2.3F, -6.5F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(-0.5F, 1.3F, -4.5F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(2.1F, 3.3F, -5.5F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.5F, 1.3F, -5.5F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(-1.7F, 2.9F, -3.5F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.2F, -0.3491F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(1, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4701F, 1.6687F, -0.7244F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(1, 65).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.571F, 1.6643F, -0.7114F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP7BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.2F, -2.0F, 0.0349F, 0.0F, 0.0F));
		belly.addOrReplaceChild("button_r1", CubeListBuilder.create().texOffs(33, 83).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5696F, -9.8732F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(8, 80).addBox(-4.5F, -3.0F, -5.5F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.4F, -4.2F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra2_r1", CubeListBuilder.create().texOffs(8, 73).mirror().addBox(-0.5F, -3.0F, -5.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 73).mirror().addBox(-7.7F, -3.0F, -5.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.6F, 0.5611F, -3.7995F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra4_r1", CubeListBuilder.create().texOffs(4, 75).mirror().addBox(-4.5F, 2.0F, -8.5F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.1F, -0.3F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra5_r1", CubeListBuilder.create().texOffs(2, 73).mirror().addBox(-4.5F, -3.0F, -8.5F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -0.1F, -0.3F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(1, 72).addBox(-4.5F, -3.0F, -8.5F, 7.0F, 6.0F, 9.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(1.0F, 0.5F, -0.3F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(-2.6F, -0.1F, -5.6F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(1.6F, -0.1F, -6.8F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(1.2F, 0.3F, -6.3F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(-1.0F, 1.9F, -6.3F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(9, 79).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(1.0F, 1.6F, -7.6F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.2F, -0.3491F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(1, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4701F, 1.6687F, -0.7244F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(1, 65).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.571F, 1.6643F, -0.7114F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
	public static LayerDefinition createP8BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.2F, -2.0F, 0.0349F, 0.0F, 0.0F));
		belly.addOrReplaceChild("button_r1", CubeListBuilder.create().texOffs(37, 87).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 1.7696F, -11.2732F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra6_r1", CubeListBuilder.create().texOffs(10, 82).addBox(-4.0F, -3.0F, -0.5F, 8.0F, 6.0F, 1.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 0.6873F, -10.9992F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra1_r1", CubeListBuilder.create().texOffs(10, 82).addBox(-4.0F, -3.0F, -0.5F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6873F, -10.2992F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra2_r1", CubeListBuilder.create().texOffs(9, 74).mirror().addBox(-0.5F, -3.0F, -6.0F, 1.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.1F, 0.5611F, -3.7995F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra3_r1", CubeListBuilder.create().texOffs(3, 74).mirror().addBox(-0.5F, -3.0F, -4.5F, 1.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.1F, 0.5873F, -5.2992F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra4_r1", CubeListBuilder.create().texOffs(4, 76).mirror().addBox(-5.5F, 2.0F, -9.5F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 1.1F, -0.3F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("extra5_r1", CubeListBuilder.create().texOffs(2, 74).mirror().addBox(-5.5F, -3.0F, -9.5F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, -0.1F, -0.3F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(1, 73).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 6.0F, 10.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(0.0F, 0.5785F, -4.7993F, 0.0175F, 0.0F, 0.0F));
		belly.addOrReplaceChild("front_kick", CubeListBuilder.create().texOffs(10, 81).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(2.0F, -0.1F, -3.2F));
		belly.addOrReplaceChild("left_kick", CubeListBuilder.create().texOffs(10, 81).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(-3.1F, 0.4F, -5.7F));
		belly.addOrReplaceChild("right_kick", CubeListBuilder.create().texOffs(10, 81).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(1.8F, 0.9F, -6.9F));
		belly.addOrReplaceChild("bottom_kick", CubeListBuilder.create().texOffs(10, 81).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(-1.0F, 1.9F, -8.0F));
		belly.addOrReplaceChild("top_kick", CubeListBuilder.create().texOffs(10, 81).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)), PartPose.offset(1.0F, 1.4F, -7.4F));
		body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.2F, -0.3491F, 0.0F, 0.0F));
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.2F, -0.3491F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(1, 65).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4701F, 1.6687F, -0.7244F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(1, 65).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.571F, 1.6643F, -0.7114F, 0.3491F, -0.1309F, -0.0436F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	
    // Outer armor (chestplate, helmet, boots)
    public static LayerDefinition createOuterLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		CubeDeformation cubeDeformation = new CubeDeformation(0.85F);
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation.extend(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F)); 
        body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }
    
	protected void moveHead(float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * (Mth.PI / 180F);
		this.head.xRot = headPitch * (Mth.PI / 180F);
		this.hat.copyFrom(this.head);
	}
	
	@Override
	public ModelPart getHead() {
		return this.head;
	}
	
	public void setAllVisible(boolean visible) {
		this.root().getAllParts().forEach(part -> part.visible = visible);
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		
		this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		
		this.updateJiggle(entity);
		
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHead(netHeadYaw, headPitch);
		}	
	}
	
	protected abstract void animate(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch);	
}

