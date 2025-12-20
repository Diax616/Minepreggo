package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.ZombieGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlModel extends AbstractTamableZombieGirlModel<TamableZombieGirl> {
	
	public TamableZombieGirlModel(ModelPart root) {
		super(root, new HierarchicalModel<TamableZombieGirl>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableZombieGirl zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
	
	@Override
	public void setupAnim(TamableZombieGirl zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(zombieGirl, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if (zombieGirl.getPostPartumLactation() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM) {
			this.boobs.y -= 0.34F;
			this.boobs.xScale = 1.2F;
			this.boobs.zScale = 1.1F;
			this.boobs.yScale = 1.2F;
		}
	}
}