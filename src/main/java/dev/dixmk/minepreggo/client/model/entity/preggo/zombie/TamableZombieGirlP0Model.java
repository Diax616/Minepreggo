package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.ZombieGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP0Model extends AbstractTamableZombieGirlModel<TamableZombieGirlP0> {
	
	public TamableZombieGirlP0Model(ModelPart root) {
		super(root, new HierarchicalModel<TamableZombieGirlP0>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableZombieGirlP0 zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
			    if (zombieGirl.isAttacking()) {
				    this.animate(zombieGirl.attackAnimationState, ZombieGirlAnimation.ATTACK, ageInTicks, 1f);	
			    }
				
				if (zombieGirl.walkAnimation.isMoving()) {
					if (zombieGirl.isAggressive()) {
						this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(ZombieGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				}
					
			
				if (zombieGirl.isPanic()) {
					this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);						
					return;
				} 	
				
				if (zombieGirl.isWaiting()) {
					this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.WAIT1, ageInTicks, 1f);										
				}
				else if (zombieGirl.isPassenger()) {
					this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.RIDING, ageInTicks, 1f);						
				}
				else {
					this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);						
				}		
			}	
		});
	}
}
