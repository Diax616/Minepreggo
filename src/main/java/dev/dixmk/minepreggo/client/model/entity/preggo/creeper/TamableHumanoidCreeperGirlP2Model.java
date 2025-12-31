package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.HumanoidCreeperGirlAnimation;

import java.util.UUID;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP2;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP2Model extends AbstractTamableHumanoidPregnantCreeperGirlModel<TamableHumanoidCreeperGirlP2> {
	public TamableHumanoidCreeperGirlP2Model(ModelPart root) {
		super(root, new HierarchicalModel<>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableHumanoidCreeperGirlP2 creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
			    if (creeperGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {		    	
			    	this.animate(creeperGirl.bellyAnimationState, BellyAnimation.LOW_BELLY_INFLATION, ageInTicks);		    
			    	UUID preggoMobId = creeperGirl.getUUID();       
			        if (BellyAnimationManager.getInstance().isAnimating(preggoMobId)) {
						AnimationState state = BellyAnimationManager.getInstance().getAnimationState(preggoMobId);
				        AnimationDefinition animation = BellyAnimationManager.getInstance().getCurrentAnimation(preggoMobId);        
				        if (state != null && animation != null) {
				            this.animate(state, animation, ageInTicks);
				        }
			        }
			    }  
			    
			    if (creeperGirl.isAttacking()) {
				    this.animate(creeperGirl.attackAnimationState, HumanoidCreeperGirlAnimation.ATTACK, ageInTicks);	
			    }
				
				if (creeperGirl.walkAnimation.isMoving()) {
					if (creeperGirl.isAggressive()) {
						this.animateWalk(HumanoidCreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(HumanoidCreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				}
						
				if (creeperGirl.isIncapacitated()) {
					switch (creeperGirl.getPregnancyPain()) {
					case MORNING_SICKNESS: {
						this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.MORNING_SICKNESS, ageInTicks);										
						break;
					}
					case MISCARRIAGE: {
						this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.MISCARRIAGE, ageInTicks);						
						break;
					}		
					default:
						break;						
					}	
				} 
				
				if (creeperGirl.isPanic()) {
					this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.IDLE, ageInTicks);						
				} 					
				else if (creeperGirl.isWaiting()) {
					this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.WAIT1, ageInTicks);										
				}
				else if (creeperGirl.isPassenger()) {
					this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.RIDING, ageInTicks);						
				}
				else {
					this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.IDLE, ageInTicks);						
				}
			}	
		});
	}
}
