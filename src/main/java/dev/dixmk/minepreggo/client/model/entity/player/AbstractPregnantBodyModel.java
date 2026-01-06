package dev.dixmk.minepreggo.client.model.entity.player;

import java.util.UUID;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.jiggle.JigglePhysicsManager;
import dev.dixmk.minepreggo.client.jiggle.PlayerJiggleData;
import dev.dixmk.minepreggo.client.jiggle.PlayerJiggleDataFactory;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.AnimationState;
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
		
	// Pregnancy phase and model type for factory-based jiggle creation
	protected final PregnancyPhase pregnancyPhase;
	protected final SkinType modelType;

	protected float milkingBoobsXScale = 1.15F;
	protected float milkingBoobsYScale = 1.05F;
	protected float milkingBoobsZScale = 1.25F;
	protected float milkingBoobsYPos = -0.42F;
	
	private final boolean simpleBellyJiggle;
	
	protected AbstractPregnantBodyModel(ModelPart root, BellyInflation bellyInflation, 
			PregnancyPhase pregnancyPhase, 
			SkinType modelType,
			boolean simpleBellyJiggle) {		
		this.root = root;
		this.body = root.getChild("body");
		this.boobs = this.body.getChild("boobs");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
		this.belly = this.body.getChild("belly");
		this.pregnancyPhase = pregnancyPhase;
		this.modelType = modelType;
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
			
		UUID playerId = entity.getUUID();
		PlayerJiggleData jiggleData = JigglePhysicsManager.getInstance().getOrCreate(playerId, () -> PlayerJiggleDataFactory.create(pregnancyPhase, modelType));
	
		if (armor.isEmpty()) {
			jiggleData.getBoobsJiggle().setupAnim(entity, boobs, leftBoob, rightBoob);
			jiggleData.getBellyJiggle().ifPresent(jiggle -> jiggle.setupAnim(entity, belly, simpleBellyJiggle));

			animBellyIdle(entity, ageInTicks);
			animBellySlapping(entity, ageInTicks);
			if (!boobs.visible) {
	    		boobs.visible = true;
	    	}
		}
		else { 		
			if (armor.getItem() instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed()) {
				jiggleData.getBoobsJiggle().setupAnim(entity, boobs, leftBoob, rightBoob);
	
				if (!boobs.visible) {
		    		boobs.visible = true;
		    	}
			}
			else if (boobs.visible) {
	    		boobs.visible = false;
	    	}
		}
	}
	
	protected void animBellyIdle(AbstractClientPlayer entity, float ageInTicks) {
		entity.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> this.animate(femaleData.getPregnancySystem().bellyAnimationState, bellyInflation.animation, ageInTicks))
		);
	}
	
	protected void animBellySlapping(AbstractClientPlayer entity, float ageInTicks) {
		UUID playerId = entity.getUUID(); 
        if (BellyAnimationManager.getInstance().isAnimating(playerId)) {
    		AnimationState state = BellyAnimationManager.getInstance().getAnimationState(playerId);
            AnimationDefinition animation = BellyAnimationManager.getInstance().getCurrentAnimation(playerId);
            
            if (state != null && animation != null) {
                this.animate(state, animation, ageInTicks);
            }
        }
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
