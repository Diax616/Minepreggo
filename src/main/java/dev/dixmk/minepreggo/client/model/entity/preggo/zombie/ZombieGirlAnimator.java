package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import java.util.UUID;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.client.animation.preggo.ZombieGirlAnimation;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantPreggoMobAnimator;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ZombieGirlAnimator<T extends AbstractZombieGirl> extends PregnantPreggoMobAnimator<T> {
   
    protected ZombieGirlAnimator(ModelPart root) {
    	super(root);   	
    }
      
    protected void animateAttack(T zombieGirl, float ageInTicks) {
        if (zombieGirl.isAttacking()) {
        	this.animate(zombieGirl.attackAnimationState, ZombieGirlAnimation.ATTACK, ageInTicks);
        }
    }
    
    protected void animateMovement(T zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks) {
        if (zombieGirl.walkAnimation.isMoving()) {
            if (zombieGirl.isAggressive()) {
            	this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
            } else {
            	this.animateWalk(ZombieGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
            }
        }
    }
        
    protected void animateLoopState(T zombieGirl, float ageInTicks) {
		if (zombieGirl.isPassenger()) {
		    this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.RIDING, ageInTicks);	
		}
		else {
	    	this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks);						
		}
    }
        
    @OnlyIn(Dist.CLIENT)
	public static class BasicZombieGirlAnimator<E extends AbstractZombieGirl> extends ZombieGirlAnimator<E> {
		public BasicZombieGirlAnimator(ModelPart root) {
			super(root);
		}
	
		@Override
		protected void animateBelly(AbstractZombieGirl zombieGirl, float ageInTicks) {}

		@Override
		protected void animatePregnancyPain(AbstractZombieGirl zombieGirl, float ageInTicks) {}
	}
	
    @OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlAnimator<E extends AbstractTamableZombieGirl> extends ZombieGirlAnimator<E> {
		public TamableZombieGirlAnimator(ModelPart root) {
			super(root);
		}

		@Override
		protected void animateBelly(E zombieGirl, float ageInTicks) {}

		@Override
		protected void animatePregnancyPain(E zombieGirl, float ageInTicks) {}
		
		@Override
	    protected void animateLoopState(E zombieGirl, float ageInTicks) {
			final var tamableData = zombieGirl.getTamableData();
			if (tamableData.isPanic()) {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks);						
			} 		
			else if (tamableData.isWaiting()) {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.WAIT1, ageInTicks);										
			}
			else if (zombieGirl.isPassenger()) {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.RIDING, ageInTicks);						
			}
			else {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks);						
			}
	    }
	}	
	
    @OnlyIn(Dist.CLIENT)
	public static class MonsterPregnantZombieGirlAnimator<E extends AbstractMonsterPregnantZombieGirl> extends ZombieGirlAnimator<E> {
		private final BellyInflation bellyInflation;
		private final @Nullable FetalMovementIntensity fetalMovementIntensity;
		
		public MonsterPregnantZombieGirlAnimator(ModelPart root, BellyInflation bellyInflation, @Nullable FetalMovementIntensity fetalMovementIntensity) {
			super(root);
			this.bellyInflation = bellyInflation;
			this.fetalMovementIntensity = fetalMovementIntensity;
		}

		@Override
		protected void animateBelly(E zombieGirl, float ageInTicks) {
		    if (zombieGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
				if (fetalMovementIntensity != null && zombieGirl.getPregnancyData().isIncapacitated()) {
			    	this.animate(zombieGirl.loopAnimationState, fetalMovementIntensity.animation, ageInTicks);		    
		    	}
		    	else {
			    	this.animate(zombieGirl.loopAnimationState, bellyInflation.animation, ageInTicks);		    
		    	}
		    }		
		}

		@Override
		protected void animatePregnancyPain(E zombieGirl, float ageInTicks) {
			if (zombieGirl.getPregnancyData().isIncapacitated()) {
			    this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.CONTRACTION2, ageInTicks);	
			}
		}
	}
	
    @OnlyIn(Dist.CLIENT)
	public static class TamablePregnantZombieGirlAnimator<E extends AbstractTamablePregnantZombieGirl> extends TamableZombieGirlAnimator<E> {
		private final BellyInflation bellyInflation;
		private final @Nullable FetalMovementIntensity fetalMovementIntensity;
		
		public TamablePregnantZombieGirlAnimator(ModelPart root, BellyInflation bellyInflation, @Nullable FetalMovementIntensity fetalMovementIntensity) {
			super(root);
			this.bellyInflation = bellyInflation;
			this.fetalMovementIntensity = fetalMovementIntensity;
		}

		@Override
		protected void animateBelly(E zombieGirl, float ageInTicks) {
		    if (zombieGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {		    			        	
				if (fetalMovementIntensity != null && zombieGirl.getPregnancyData().getPregnancyPain() ==  PregnancyPain.FETAL_MOVEMENT) {
			    	this.animate(zombieGirl.bellyAnimationState, fetalMovementIntensity.animation, ageInTicks);		    
		    	}
		    	else {
			    	this.animate(zombieGirl.bellyAnimationState, bellyInflation.animation, ageInTicks);		    
		    	}
		    	    	
		    	UUID preggoMobId = zombieGirl.getUUID();       
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
		protected void animatePregnancyPain(E zombieGirl, float ageInTicks) {
			final var pregnancyData = zombieGirl.getPregnancyData();
			final var pregnancyPain = pregnancyData.getPregnancyPain();	
			if (pregnancyPain == PregnancyPain.MORNING_SICKNESS) {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.MORNING_SICKNESS, ageInTicks);										
			}
			else if (pregnancyPain == PregnancyPain.MISCARRIAGE) {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.MISCARRIAGE, ageInTicks);						
			}
			else if (pregnancyPain == PregnancyPain.PREBIRTH) {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.MISCARRIAGE, ageInTicks);						
			}
			else if (pregnancyPain == PregnancyPain.BIRTH) {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.BIRTH, ageInTicks);						
			}
			else if (pregnancyPain == PregnancyPain.CONTRACTION) {
				this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.CONTRACTION1, ageInTicks);						
			}
		}
	}
}
