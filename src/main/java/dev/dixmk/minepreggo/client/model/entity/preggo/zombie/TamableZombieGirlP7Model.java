package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import java.util.UUID;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.client.animation.preggo.ZombieGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP7Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP7> {
	
	public TamableZombieGirlP7Model(ModelPart root) {
		super(root, new HierarchicalModel<>() {		
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableZombieGirlP7 zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
			    if (zombieGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {		    	
			    	if (zombieGirl.getPregnancyPain() ==  PregnancyPain.FETAL_MOVEMENT) {
				    	this.animate(zombieGirl.loopAnimationState, BellyAnimation.FETAL_MOVEMENT_P7, ageInTicks);		    
			    	}
			    	else {
				    	this.animate(zombieGirl.loopAnimationState, BellyAnimation.HIGH_BELLY_INFLATION, ageInTicks);		    
			    	}

			    	UUID preggoMobId = zombieGirl.getUUID();       
			        if (!BellyAnimationManager.getInstance().isAnimating(preggoMobId)) {
			            return;
			        }
					AnimationState state = BellyAnimationManager.getInstance().getAnimationState(preggoMobId);
			        AnimationDefinition animation = BellyAnimationManager.getInstance().getCurrentAnimation(preggoMobId);
			        
			        if (state != null && animation != null) {
			            this.animate(state, animation, ageInTicks);
			        }
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
					case FETAL_MOVEMENT: {
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
		});
	}
}
