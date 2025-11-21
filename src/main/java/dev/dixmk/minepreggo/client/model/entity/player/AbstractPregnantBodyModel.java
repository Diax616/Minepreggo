package dev.dixmk.minepreggo.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.jiggle.AdvancedJigglePhysics;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractPregnantBodyModel extends HierarchicalModel<AbstractClientPlayer> {
	private final ModelPart root;
	public final ModelPart body;
	public final ModelPart boobs;
	public final ModelPart belly;
	public final ModelPart rightBoob;
	public final ModelPart leftBoob;

	protected final AnimationState loopAnimationState = new AnimationState();
	
	protected final AdvancedJigglePhysics boobsJiggleData;
	protected final AdvancedJigglePhysics bellyJiggleData;

	protected float milkingBoobsXScale = 1.4F;
	protected float milkingBoobsYScale = 1.2F;
	protected float milkingBoobsZScale = 1.3F;
	protected float milkingBoobsYPos = -0.42F;
	
	protected float additionalJiggleBoobYPos = 2F;
	protected float additionalJiggleBellyYPos = 6F;
	
	protected boolean milkingFlag = false;
	protected boolean simpleJiggleInBelly = true;
	
	protected AbstractPregnantBodyModel(ModelPart root, AdvancedJigglePhysics boobsJiggleData, AdvancedJigglePhysics bellyJiggleData) {
		this.root = root;
		this.body = root.getChild("body");
		this.boobs = this.body.getChild("boobs");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
		this.belly = this.body.getChild("belly");
		this.boobsJiggleData = boobsJiggleData;
		this.bellyJiggleData = bellyJiggleData;
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {		
		this.root().getAllParts().forEach(ModelPart::resetPose);
		
		if (entity.level().isClientSide && !this.loopAnimationState.isStarted()) {
			this.loopAnimationState.start(entity.tickCount);
		}
			
		updateBoobs((float)entity.getY(), entity.getX(), entity.getZ(), 0.05F, entity.walkAnimation.isMoving());
		
		if (entity.hasEffect(MinepreggoModMobEffects.LACTATION.get())) {
			this.boobs.y += milkingBoobsYPos;		
			this.boobs.xScale = milkingBoobsXScale;
			this.boobs.yScale = milkingBoobsYScale;
			this.boobs.zScale = milkingBoobsZScale;	
		} 
		
		if (simpleJiggleInBelly) {
			updateBelly((float)entity.getY(), 0.05F);
		}
		else {
			updateBelly((float)entity.getY(), entity.getX(), entity.getZ(), 0.05F, entity.walkAnimation.isMoving());
		}
		
		animBelly(entity, ageInTicks);
	}
	
	protected void animBelly(AbstractClientPlayer entity, float ageInTicks) {
		
	}
	
	protected void updateBoobs(float playerY, double playerX, double playerZ, float deltaTime, boolean isMoving) {
        boobsJiggleData.update(playerY, playerX, playerZ, deltaTime, isMoving);
        boobs.y = additionalJiggleBoobYPos + boobsJiggleData.getOffset();
            
        // Apply rotation around Y-axis (yRot for vertical axis rotation)
        float rotation = boobsJiggleData.getRotation();
        boobs.yRot = rotation;
        
        // Add slight individual boob rotation for more realism
        float movementIntensity = boobsJiggleData.getMovementIntensity();
        leftBoob.yRot = rotation * 0.3f + (movementIntensity * 0.05f);
        rightBoob.yRot = -rotation * 0.3f - (movementIntensity * 0.05f);
	}
		
	protected void updateBelly(float playerY, double playerX, double playerZ, float deltaTime, boolean isMoving) {
        bellyJiggleData.update(playerY, playerX, playerZ, deltaTime, isMoving);
        belly.y = additionalJiggleBellyYPos + bellyJiggleData.getOffset();	
        
        float rotation = bellyJiggleData.getRotation();
        belly.yRot = rotation;
	}
	
	protected void updateBelly(float playerY, float deltaTime) {
        bellyJiggleData.update(playerY, deltaTime);
        belly.y = additionalJiggleBellyYPos + bellyJiggleData.getOffset();	
	}
	
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	@Override
	public ModelPart root() {
		return this.root;
	}
}
