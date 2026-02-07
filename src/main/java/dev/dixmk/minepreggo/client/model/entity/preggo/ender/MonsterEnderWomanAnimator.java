package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import java.util.UUID;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.MonsterEnderWomanAnimation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantPreggoMobAnimator;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class MonsterEnderWomanAnimator<T extends AbstractEnderWoman> extends PregnantPreggoMobAnimator<T> {

	protected MonsterEnderWomanAnimator(ModelPart root) {
		super(root);
	}

    protected void animateAttack(T enderWoman, float ageInTicks) {
		if (!enderWoman.isCarring() && enderWoman.isAttacking()) {
		    this.animate(enderWoman.attackAnimationState, MonsterEnderWomanAnimation.ATTACK, ageInTicks);
		}
    }
    
    protected void animateMovement(T enderWoman, float limbSwing, float limbSwingAmount, float ageInTicks) {
		if (enderWoman.walkAnimation.isMoving()) {
			if (enderWoman.isCarring()) {
				this.animateWalk(MonsterEnderWomanAnimation.WALK2, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
			else {
				this.animateWalk(MonsterEnderWomanAnimation.WALK1, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
		} 
    }
        
    protected void animateLoopState(T enderWoman, float ageInTicks) {	
    	if (enderWoman.isCarring()) {
		    this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.CARRING, ageInTicks);
		}
    	if (enderWoman.isPassenger()) {
			this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.RIDING, ageInTicks);						
		}
		else {
			this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.IDLE, ageInTicks);						
		}	
    }
        
    @OnlyIn(Dist.CLIENT)
	public static class BasicEnderWomanAnimator<E extends AbstractEnderWoman> extends MonsterEnderWomanAnimator<E> {
		public BasicEnderWomanAnimator(ModelPart root) {
			super(root);
		}
	
		@Override
		protected void animateBelly(E enderWoman, float ageInTicks) {}

		@Override
		protected void animatePregnancyPain(E enderWoman, float ageInTicks) {}
	}
	
    @OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanAnimator<E extends AbstractTamableEnderWoman> extends MonsterEnderWomanAnimator<E> {
		public TamableMonsterEnderWomanAnimator(ModelPart root) {
			super(root);
		}

		@Override
		protected void animateBelly(E enderWoman, float ageInTicks) {}

		@Override
		protected void animatePregnancyPain(E enderWoman, float ageInTicks) {}
		
		@Override
	    protected void animateLoopState(E enderWoman, float ageInTicks) {
			final var tamableData = enderWoman.getTamableData();			
	    	if (enderWoman.isCarring()) {
			    this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.CARRING, ageInTicks);
			}
			if (tamableData.isPanic()) {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.IDLE, ageInTicks);						
			} 		
			else if (tamableData.isWaiting()) {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.WAIT, ageInTicks);										
			}
			else if (enderWoman.isPassenger()) {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.RIDING, ageInTicks);						
			}
			else {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.IDLE, ageInTicks);						
			}
	    }
	}	
    
    @OnlyIn(Dist.CLIENT)
	public static class TamablePregnantMonsterEnderWomanAnimator<E extends AbstractTamablePregnantEnderWoman> extends TamableMonsterEnderWomanAnimator<E> {
		private final BellyInflation bellyInflation;
		private final @Nullable FetalMovementIntensity fetalMovementIntensity;
		
		public TamablePregnantMonsterEnderWomanAnimator(ModelPart root, BellyInflation bellyInflation, @Nullable FetalMovementIntensity fetalMovementIntensity) {
			super(root);
			this.bellyInflation = bellyInflation;
			this.fetalMovementIntensity = fetalMovementIntensity;
		}  
		
		@Override
		protected void animateBelly(E enderWoman, float ageInTicks) {
		    if (enderWoman.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {		    			        	
				if (fetalMovementIntensity != null && enderWoman.getPregnancyData().getPregnancyPain() ==  PregnancyPain.FETAL_MOVEMENT) {
			    	this.animate(enderWoman.bellyAnimationState, fetalMovementIntensity.animation, ageInTicks);		    
		    	}
		    	else {
			    	this.animate(enderWoman.bellyAnimationState, bellyInflation.animation, ageInTicks);		    
		    	}
		    	    	
		    	UUID preggoMobId = enderWoman.getUUID();       
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
		protected void animatePregnancyPain(E enderWoman, float ageInTicks) {
			final var pregnancyData = enderWoman.getPregnancyData();
			final var pregnancyPain = pregnancyData.getPregnancyPain();	
			if (pregnancyPain == PregnancyPain.MORNING_SICKNESS) {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.MORNING_SICKNESS, ageInTicks);										
			}
			else if (pregnancyPain == PregnancyPain.MISCARRIAGE) {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.MISCARRIAGE, ageInTicks);						
			}
			else if (pregnancyPain == PregnancyPain.PREBIRTH) {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.PREBIRTH, ageInTicks);						
			}
			else if (pregnancyPain == PregnancyPain.BIRTH) {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.BIRTH, ageInTicks);						
			}
			else if (pregnancyPain == PregnancyPain.CONTRACTION) {
				this.animate(enderWoman.loopAnimationState, MonsterEnderWomanAnimation.CONTRACTION, ageInTicks);						
			}
		}
    }
}
