package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.CreeperGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlModel extends AbstractTamableHumanoidCreeperGirlModel<TamableHumanoidCreeperGirl> {

	public TamableHumanoidCreeperGirlModel(ModelPart root) {
		super(root, new HierarchicalModel<TamableHumanoidCreeperGirl>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableHumanoidCreeperGirl creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				
			    if (creeperGirl.isAttacking()) {
				    this.animate(creeperGirl.attackAnimationState, CreeperGirlAnimation.ATTACK, ageInTicks, 1f);	
			    }
				
				if (creeperGirl.walkAnimation.isMoving()) {
					if (creeperGirl.isAggressive()) {
						this.animateWalk(CreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
					else {
						this.animateWalk(CreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
				}

				if (creeperGirl.isPanic()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);						
					return;
				} 
				
				if (creeperGirl.isWaiting()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.WAIT1, ageInTicks, 1f);										
				}
				else if (creeperGirl.isPassenger()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.RIDING, ageInTicks, 1f);						
				}
				else {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);						
				}
			}	
		});
	}
	
	@Override
	public void setupAnim(TamableHumanoidCreeperGirl creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(creeperGirl, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if (creeperGirl.getPostPartumLactation() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM) {
			this.boobs.y -= 0.34F;
			this.boobs.xScale = 1.2F;
			this.boobs.zScale = 1.1F;
			this.boobs.yScale = 1.2F;
		}
	}
}