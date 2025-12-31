package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import java.util.UUID;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.client.animation.preggo.HumanoidCreeperGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP6;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP6Model extends AbstractTamableHumanoidPregnantCreeperGirlModel<TamableHumanoidCreeperGirlP6> {
	
	public TamableHumanoidCreeperGirlP6Model(ModelPart root) {
		super(root, new HierarchicalModel<>() {		
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableHumanoidCreeperGirlP6 creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
								
			    if (creeperGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {		    	
			    	if (creeperGirl.getPregnancyPain() ==  PregnancyPain.FETAL_MOVEMENT) {
				    	this.animate(creeperGirl.bellyAnimationState, BellyAnimation.FETAL_MOVEMENT_P6, ageInTicks);		    
			    	}
			    	else {
				    	this.animate(creeperGirl.bellyAnimationState, BellyAnimation.HIGH_BELLY_INFLATION, ageInTicks);		    
			    	}

			    	UUID preggoMobId = creeperGirl.getUUID();       
			        if (!BellyAnimationManager.getInstance().isAnimating(preggoMobId)) {
			            return;
			        }
					AnimationState state = BellyAnimationManager.getInstance().getAnimationState(preggoMobId);
			        AnimationDefinition animation = BellyAnimationManager.getInstance().getCurrentAnimation(preggoMobId);
			        
			        if (state != null && animation != null) {
			            this.animate(state, animation, ageInTicks);
			        }
			    }    	
		    
			    if (creeperGirl.isAttacking()) {
				    this.animate(creeperGirl.attackAnimationState, HumanoidCreeperGirlAnimation.ATTACK, ageInTicks, 1f);	
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
						this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.MORNING_SICKNESS, ageInTicks, 1f);										
						break;
					}
					case MISCARRIAGE: {
						this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.MISCARRIAGE, ageInTicks, 1f);						
						break;
					}
					case PREBIRTH: {
						this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.MISCARRIAGE, ageInTicks, 1f);						
						break;
					}
					case BIRTH: {
						this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.BIRTH, ageInTicks, 1f);						
						break;
					}
					case CONTRACTION: {
						this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.CONTRACTION1, ageInTicks, 1f);						
						break;
					}
					default:
						break;						
					}	
				} 
				
				if (creeperGirl.isPanic()) {
					this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.IDLE, ageInTicks, 1f);						
				} 
								
				if (creeperGirl.isWaiting()) {
					this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.WAIT2, ageInTicks, 1f);										
				}
				else if (creeperGirl.isPassenger()) {
					this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.RIDING, ageInTicks, 1f);						
				}
				else {
					this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.IDLE, ageInTicks, 1f);						
				}				
			}	
		});
	}
}
