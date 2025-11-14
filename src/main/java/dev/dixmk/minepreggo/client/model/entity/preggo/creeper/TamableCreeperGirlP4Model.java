package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.CreeperGirlAnimation;
import dev.dixmk.minepreggo.client.animation.preggo.HumanoidGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP4;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP4Model extends AbstractTamablePregnantCreeperGirlModel<TamableCreeperGirlP4> {
	
	public TamableCreeperGirlP4Model(ModelPart root) {
		super(root, createDefaultP4HierarchicalModel(root));
	}

	public static<E extends AbstractTamablePregnantCreeperGirl<?,?>> HierarchicalModel<E> createDefaultP4HierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {		
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
								
			    if (creeperGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
			    	this.animate(creeperGirl.loopAnimationState, HumanoidGirlAnimation.MEDIUM_BELLY_INFLATION, ageInTicks, 1f);
			    }    	
		    
			    if (creeperGirl.isAttacking()) {
				    this.animate(creeperGirl.attackAnimationState, CreeperGirlAnimation.ATTACK, ageInTicks, 1f);	
			    }
				
				if (creeperGirl.walkAnimation.isMoving()) {
					if (creeperGirl.isAggressive()) {
						this.animateWalk(CreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(CreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				}
						
				if (creeperGirl.isIncapacitated()) {
					switch (creeperGirl.getPregnancyPain()) {
					case MORNING_SICKNESS: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.MORNING_SICKNESS, ageInTicks, 1f);										
						break;
					}
					case MISCARRIAGE: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.MISCARRIAGE, ageInTicks, 1f);						
						break;
					}
					case PREBIRTH: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.MISCARRIAGE, ageInTicks, 1f);						
						break;
					}
					case BIRTH: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.BIRTH, ageInTicks, 1f);						
						break;
					}
					case KICKING: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.KICKING1, ageInTicks, 1f);						
						break;
					}
					case CONTRACTION: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.CONTRACTION1, ageInTicks, 1f);						
						break;
					}
					default:
						break;						
					}	
				} 
				
				if (creeperGirl.isPanic()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);						
					return;
				} 
								
				if (creeperGirl.isWaiting()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.WAIT2, ageInTicks, 1f);										
				}
				else if (creeperGirl.isPassenger()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.RIDING, ageInTicks, 1f);						
				}
				else {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);						
				}				
			}	
		};
	}
}

