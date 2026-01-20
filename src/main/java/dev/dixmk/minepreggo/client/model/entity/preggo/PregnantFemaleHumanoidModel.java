package dev.dixmk.minepreggo.client.model.entity.preggo;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.jiggle.EntityJiggleData;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PregnantFemaleHumanoidModel<E extends PreggoMob> extends HumanoidModel<E> {
	public final ModelPart boobs;
	public final ModelPart belly;
	public final ModelPart rightBoob;
	public final ModelPart leftBoob;
	
	public PregnantFemaleHumanoidModel(ModelPart root) {
		super(root);
		this.boobs = this.body.getChild("boobs");
		this.belly = this.body.getChild("belly");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
	}
	
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	protected void moveHead(E entity, float netHeadYaw, float headPitch) {
		boolean flag = entity.getFallFlyingTicks() > 4;
		boolean flag1 = entity.isVisuallySwimming();
		this.head.yRot = netHeadYaw * (Mth.PI / 180F);
		if (flag) {
			this.head.xRot = (-Mth.PI / 4F);
		} else if (this.swimAmount > 0.0F) {
			if (flag1) {
				this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, (-Mth.PI / 4F));
			} else {
				this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, headPitch * (Mth.PI / 180F));
			}
		} else {
			this.head.xRot = headPitch * (Mth.PI / 180F);
		}
	}
	
	protected void moveHeadWithHat(E entity, float netHeadYaw, float headPitch) {
		this.moveHead(entity, netHeadYaw, headPitch);
		this.hat.copyFrom(this.head);
	}
	
	protected void tryHideBoobs(E entity, @Nullable Predicate<Item> hideBoobs) {
    	var armor = entity.getItemBySlot(EquipmentSlot.CHEST);
    	if (!armor.isEmpty()) {
    		boolean flag = true;
    		if (hideBoobs != null) {
    			flag = hideBoobs.test(armor.getItem());
    		}
    		if (boobs.visible && flag) boobs.visible = false;
    	}
    	else if (!boobs.visible) {
    		boobs.visible = true;
    	}
    }
	
	protected void updateJiggle(E entity, @Nullable PregnancyPhase pregnancyPhase, EntityJiggleDataFactory.JigglePositionConfig jiggleConfig, boolean simpleBellyJiggle) {
		if (entity.isBaby()) {
			return;
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
	
	protected void updateJiggle(E entity, @Nullable ImmutablePair<PregnancyPhase, Boolean> pregnancyPhaseAndSimpleBellyJiggle, EntityJiggleDataFactory.JigglePositionConfig jiggleConfig) {
		PregnancyPhase pregnancyPhase = null;
		boolean simpleBellyJiggle = false;
		if (pregnancyPhaseAndSimpleBellyJiggle != null) {
			pregnancyPhase = pregnancyPhaseAndSimpleBellyJiggle.getLeft();
			simpleBellyJiggle = pregnancyPhaseAndSimpleBellyJiggle.getRight();
		}
		this.updateJiggle(entity, pregnancyPhase, jiggleConfig, simpleBellyJiggle);
	}

    // Inner armor (leggings layer)
    public static LayerDefinition createInnerLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.51F), 0.0F);
        PartDefinition partdefinition = mesh.getRoot();
        PartDefinition body = partdefinition.getChild("body");
        body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition boobs = body.getChild("boobs");
        boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F)); 
        boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));
      
        body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));       
        return LayerDefinition.create(mesh, 64, 32);
    }

    // Outer armor (chestplate, helmet, boots)
    public static LayerDefinition createOuterLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.85F), 0.0F);
        PartDefinition partdefinition = mesh.getRoot();
        PartDefinition body = partdefinition.getChild("body");
        body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition boobs = body.getChild("boobs");
        boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F)); 
        boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));       
        return LayerDefinition.create(mesh, 64, 32);
    }
}
