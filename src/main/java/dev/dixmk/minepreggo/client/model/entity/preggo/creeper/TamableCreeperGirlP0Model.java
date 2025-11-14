package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.CreeperGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP0;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP0Model extends AbstractTamableCreeperGirlModel<TamableCreeperGirlP0> {

	public TamableCreeperGirlP0Model(ModelPart root) {
		super(root, new HierarchicalModel<TamableCreeperGirlP0>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableCreeperGirlP0 creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
}
