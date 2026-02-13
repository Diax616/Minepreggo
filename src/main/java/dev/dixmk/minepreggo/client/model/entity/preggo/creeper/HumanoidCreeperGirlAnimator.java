package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import java.util.UUID;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.client.animation.preggo.HumanoidCreeperGirlAnimation;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantPreggoMobAnimator;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class HumanoidCreeperGirlAnimator<E extends AbstractCreeperGirl> extends PregnantPreggoMobAnimator<E> {

	protected HumanoidCreeperGirlAnimator(ModelPart root) {
		super(root);
	}
    
    protected void animateAttack(E creeperGirl, float ageInTicks) {
        if (creeperGirl.isAttacking()) {
        	this.animate(creeperGirl.attackAnimationState, HumanoidCreeperGirlAnimation.ATTACK, ageInTicks);
        }
    }
    
    protected void animateMovement(E creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks) {
        if (creeperGirl.walkAnimation.isMoving()) {
            if (creeperGirl.isAggressive()) {
            	this.animateWalk(HumanoidCreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
            } else {
            	this.animateWalk(HumanoidCreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
            }
        }
    }
        
    protected void animateLoopState(E creeperGirl, float ageInTicks) {  	
    	if (creeperGirl.isPassenger()) {
    		this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.RIDING, ageInTicks);						
    	}
    	else {
    		this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.IDLE, ageInTicks);						
    	}
    }
    

    @OnlyIn(Dist.CLIENT)
  	public static class BasicHumanoidZombieGirlAnimator<E extends AbstractCreeperGirl> extends HumanoidCreeperGirlAnimator<E> {
  		public BasicHumanoidZombieGirlAnimator(ModelPart root) {
  			super(root);
  		}

  		@Override
  		protected void animateBelly(E zombieGirl, float ageInTicks) {}

  		@Override
  		protected void animatePregnancyPain(E zombieGirl, float ageInTicks) {}
  	}
  	
    @OnlyIn(Dist.CLIENT)
  	public static class TamableHumanoidCreeperGirlAnimator<E extends AbstractTamableCreeperGirl> extends HumanoidCreeperGirlAnimator<E> {
  		public TamableHumanoidCreeperGirlAnimator(ModelPart root) {
  			super(root);
  		}

  		@Override
  		protected void animateBelly(E zombieGirl, float ageInTicks) {}

  		@Override
  		protected void animatePregnancyPain(E zombieGirl, float ageInTicks) {}
  		
  		@Override
  	    protected void animateLoopState(E creeperGirl, float ageInTicks) {
  			final var tamableData = creeperGirl.getTamableData();
  			if (tamableData.isPanic()) {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.IDLE, ageInTicks);						
  			} 		
  			else if (tamableData.isWaiting()) {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.WAIT1, ageInTicks);										
  			}
  			else if (creeperGirl.isPassenger()) {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.RIDING, ageInTicks);						
  			}
  			else {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.IDLE, ageInTicks);						
  			}
  	    }
  	}	
  	
	@OnlyIn(Dist.CLIENT)
  	public static class MonsterPregnantHumanoidCreeperGirlAnimator<E extends AbstractHostilePregnantHumanoidCreeperGirl> extends HumanoidCreeperGirlAnimator<E> {
  		private final BellyInflation bellyInflation;
  		private final @Nullable FetalMovementIntensity fetalMovementIntensity;
  		
  		public MonsterPregnantHumanoidCreeperGirlAnimator(ModelPart root, BellyInflation bellyInflation, @Nullable FetalMovementIntensity fetalMovementIntensity) {
  			super(root);
  			this.bellyInflation = bellyInflation;
  			this.fetalMovementIntensity = fetalMovementIntensity;
  		}

  		@Override
  		protected void animateBelly(E creeperGirl, float ageInTicks) {
  		    if (creeperGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
  				if (fetalMovementIntensity != null && creeperGirl.getPregnancyData().isIncapacitated()) {
  			    	this.animate(creeperGirl.loopAnimationState, fetalMovementIntensity.animation, ageInTicks);		    
  		    	}
  		    	else {
  			    	this.animate(creeperGirl.loopAnimationState, bellyInflation.animation, ageInTicks);		    
  		    	}
  		    }		
  		}

  		@Override
  		protected void animatePregnancyPain(E creeperGirl, float ageInTicks) {
  			if (creeperGirl.getPregnancyData().isIncapacitated()) {
  			    this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.CONTRACTION2, ageInTicks);	
  			}
  		}
  	}
  	
	@OnlyIn(Dist.CLIENT)
  	public static class TamablePregnantHumanoidCreeperGirlAnimator<E extends AbstractTamablePregnantCreeperGirl> extends TamableHumanoidCreeperGirlAnimator<E> {
  		private final BellyInflation bellyInflation;
  		private final @Nullable FetalMovementIntensity fetalMovementIntensity;
  		
  		public TamablePregnantHumanoidCreeperGirlAnimator(ModelPart root, BellyInflation bellyInflation, @Nullable FetalMovementIntensity fetalMovementIntensity) {
  			super(root);
  			this.bellyInflation = bellyInflation;
  			this.fetalMovementIntensity = fetalMovementIntensity;
  		}

  		@Override
  		protected void animateBelly(E creeperGirl, float ageInTicks) {
  		    if (creeperGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {		    			        	
  				if (fetalMovementIntensity != null && creeperGirl.getPregnancyData().getPregnancyPain() ==  PregnancyPain.FETAL_MOVEMENT) {
  			    	this.animate(creeperGirl.bellyAnimationState, fetalMovementIntensity.animation, ageInTicks);		    
  		    	}
  		    	else {
  			    	this.animate(creeperGirl.bellyAnimationState, bellyInflation.animation, ageInTicks);		    
  		    	}
  		    	    	
  		    	UUID preggoMobId = creeperGirl.getUUID();       
  		        if (BellyAnimationManager.getInstance().isAnimating(preggoMobId)) {
  					AnimationState state = BellyAnimationManager.getInstance().getAnimationState(preggoMobId);
  			        AnimationDefinition animation = BellyAnimationManager.getInstance().getCurrentAnimation(preggoMobId);
  			        
  			        if (state != null && animation != null) {
  			            this.animate(state, animation, ageInTicks);
  			        }
  		        } 
  		    } 	
  		}
  	
  		@Override
  		protected void animatePregnancyPain(E creeperGirl, float ageInTicks) {
  			final var pregnancyData = creeperGirl.getPregnancyData();
  			final var pregnancyPain = pregnancyData.getPregnancyPain();	
  			if (pregnancyPain == PregnancyPain.MORNING_SICKNESS) {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.MORNING_SICKNESS, ageInTicks);										
  			}
  			else if (pregnancyPain == PregnancyPain.MISCARRIAGE) {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.MISCARRIAGE, ageInTicks);						
  			}
  			else if (pregnancyPain == PregnancyPain.PREBIRTH) {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.MISCARRIAGE, ageInTicks);						
  			}
  			else if (pregnancyPain == PregnancyPain.BIRTH) {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.BIRTH, ageInTicks);						
  			}
  			else if (pregnancyPain == PregnancyPain.CONTRACTION) {
  				this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.CONTRACTION1, ageInTicks);						
  			}
  		}
  	}
}
