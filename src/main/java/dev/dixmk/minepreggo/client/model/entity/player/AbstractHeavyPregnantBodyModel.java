package dev.dixmk.minepreggo.client.model.entity.player;

import java.util.UUID;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import dev.dixmk.minepreggo.client.jiggle.PlayerJiggleData;
import dev.dixmk.minepreggo.client.jiggle.PlayerJiggleDataFactory;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHeavyPregnantBodyModel extends AbstractPregnantBodyModel {
	public final ModelPart leftLeg;
	public final ModelPart rightLeg;
	public final ModelPart leftbutt;
	public final ModelPart rightbutt;
	
	protected final FetalMovementIntensity fetalMovementIntensity;
	
	protected AbstractHeavyPregnantBodyModel(ModelPart root, BellyInflation bellyInflation, 
			FetalMovementIntensity fetalMovementIntensity, 
			PregnancyPhase pregnancyPhase, 
			SkinType modelType) {
		super(root, bellyInflation, pregnancyPhase, modelType, false);
		this.leftLeg = root.getChild("left_leg");
		this.rightLeg = root.getChild("right_leg");
		this.leftbutt = leftLeg.getChild("left_butt");
		this.rightbutt = rightLeg.getChild("right_butt");	
		this.fetalMovementIntensity = fetalMovementIntensity;
	}

	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {		
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		UUID playerId = entity.getUUID();
		PlayerJiggleData jiggleData = JigglePhysicsManager.getInstance().getOrCreate(playerId, () -> PlayerJiggleDataFactory.createNonPregnancy());
		jiggleData.getButtJiggle().ifPresent(jiggle -> jiggle.setupAnim(entity, leftbutt, rightbutt));
	}
	
	@Override
	protected void animBellyIdle(AbstractClientPlayer entity, float ageInTicks) {      	
		entity.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> {		
				if (entity.hasEffect(MinepreggoModMobEffects.FETAL_MOVEMENT.get()) ) {
					this.animate(femaleData.getPregnancyData().bellyAnimationState, fetalMovementIntensity.animation, ageInTicks);
				}		
				else {
					this.animate(femaleData.getPregnancyData().bellyAnimationState, bellyInflation.animation, ageInTicks);
				}
			})
		);
	}
	
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}