package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.entity.animation.preggo.HumanoidGirlAnimation;
import dev.dixmk.minepreggo.client.entity.animation.preggo.zombie.ZombieGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP4;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP4Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP4> {
	
	public TamableZombieGirlP4Model(ModelPart root) {
		super(root, createDefaultP4HierarchicalModel(root));
	}
	
	public static<E extends AbstractTamablePregnantZombieGirl<?,?>> HierarchicalModel<E> createDefaultP4HierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {		
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
			    if (zombieGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
			    	this.animate(zombieGirl.loopAnimationState, HumanoidGirlAnimation.MEDIUM_BELLY_INFLATION, ageInTicks, 1f);
			    }
				
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
						
				if (zombieGirl.isIncapacitated()) {
					switch (zombieGirl.getPregnancyPain()) {
					case MORNING_SICKNESS: {
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.MORNING_SICKNESS, ageInTicks, 1f);										
						break;
					}
					case MISCARRIAGE: {
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.MISCARRIAGE, ageInTicks, 1f);						
						break;
					}
					case PREBIRTH: {
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.MISCARRIAGE, ageInTicks, 1f);						
						break;
					}
					case BIRTH: {
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.BIRTH, ageInTicks, 1f);						
						break;
					}
					case KICKING: {
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.KICKING1, ageInTicks, 1f);						
						break;
					}
					case CONTRACTION: {
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.CONTRACTION1, ageInTicks, 1f);						
						break;
					}
					default:
						break;						
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
		};
	}
}

