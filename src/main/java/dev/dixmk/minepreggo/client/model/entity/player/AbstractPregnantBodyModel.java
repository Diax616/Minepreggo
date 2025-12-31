package dev.dixmk.minepreggo.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.jiggle.BellyJigglePhysics;
import dev.dixmk.minepreggo.client.jiggle.WrapperBoobsJiggle;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
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

	protected final BellyInflation bellyInflation;
		
	protected final WrapperBoobsJiggle boobsJiggle;
	protected final BellyJigglePhysics bellyJiggle;

	protected float milkingBoobsXScale = 1.15F;
	protected float milkingBoobsYScale = 1.05F;
	protected float milkingBoobsZScale = 1.25F;
	protected float milkingBoobsYPos = -0.42F;
	
	private final boolean simpleBellyJiggle;
	
	protected AbstractPregnantBodyModel(ModelPart root, BellyInflation bellyInflation, WrapperBoobsJiggle boobsJiggle, BellyJigglePhysics bellyJiggle, boolean simpleBellyJiggle) {
		this.root = root;
		this.body = root.getChild("body");
		this.boobs = this.body.getChild("boobs");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
		this.belly = this.body.getChild("belly");
		this.boobsJiggle = boobsJiggle;
		this.bellyJiggle = bellyJiggle;
		this.bellyInflation = bellyInflation;
		this.simpleBellyJiggle = simpleBellyJiggle;
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {		
		this.root.getAllParts().forEach(ModelPart::resetPose);
				
		if (entity.hasEffect(MinepreggoModMobEffects.LACTATION.get())) {
			this.boobs.y += milkingBoobsYPos;		
			this.boobs.xScale = milkingBoobsXScale;
			this.boobs.yScale = milkingBoobsYScale;
			this.boobs.zScale = milkingBoobsZScale;	
		} 
		
		final var armor = entity.getItemBySlot(EquipmentSlot.CHEST);
			
		if (armor.isEmpty()) {
			bellyJiggle.setupAnim(entity, belly, simpleBellyJiggle);
			boobsJiggle.setupAnim(entity, boobs, leftBoob, rightBoob);
			animBelly(entity, ageInTicks);
			if (!boobs.visible) {
	    		boobs.visible = true;
	    	}
		}
		else { 		
			if (armor.getItem() instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed()) {
				boobsJiggle.setupAnim(entity, boobs, leftBoob, rightBoob);		
				if (!boobs.visible) {
		    		boobs.visible = true;
		    	}
			}
			else if (boobs.visible) {
	    		boobs.visible = false;
	    	}
		}
	}
	
	protected void animBelly(AbstractClientPlayer entity, float ageInTicks) {
		entity.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> this.animate(femaleData.getPregnancySystem().bellyAnimationState, bellyInflation.animation, ageInTicks))
		);
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
