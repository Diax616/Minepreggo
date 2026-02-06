package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.client.animation.preggo.EnderWomanAnimation;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantPreggoMobAnimator;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class EnderWomanAnimator<T extends AbstractEnderWoman> extends PregnantPreggoMobAnimator<T> {

	protected EnderWomanAnimator(ModelPart root) {
		super(root);
	}

    protected void animateAttack(T enderWoman, float ageInTicks) {
		if (!enderWoman.isCarring() && enderWoman.isAttacking()) {
		    this.animate(enderWoman.attackAnimationState, EnderWomanAnimation.ATTACK, ageInTicks);
		}
    }
    
    protected void animateMovement(T enderWoman, float limbSwing, float limbSwingAmount, float ageInTicks) {
		if (enderWoman.walkAnimation.isMoving()) {
			if (enderWoman.isCarring()) {
				this.animateWalk(EnderWomanAnimation.WALK2, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
			else {
				this.animateWalk(EnderWomanAnimation.WALK1, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
		} 
    }
        
    protected void animateLoopState(T enderWoman, float ageInTicks) {	
    	if (enderWoman.isCarring()) {
		    this.animate(enderWoman.loopAnimationState, EnderWomanAnimation.CARRING, ageInTicks);
		}
    	if (enderWoman.isPassenger()) {
			this.animate(enderWoman.loopAnimationState, EnderWomanAnimation.RIDING, ageInTicks);						
		}
		else {
			this.animate(enderWoman.loopAnimationState, EnderWomanAnimation.IDLE, ageInTicks);						
		}	
    }
        
    @OnlyIn(Dist.CLIENT)
	public static class BasicEnderWomanAnimator<E extends AbstractEnderWoman> extends EnderWomanAnimator<E> {
		public BasicEnderWomanAnimator(ModelPart root) {
			super(root);
		}
	
		@Override
		protected void animateBelly(E enderWoman, float ageInTicks) {}

		@Override
		protected void animatePregnancyPain(E enderWoman, float ageInTicks) {}
	}
	
    @OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanAnimator<E extends AbstractTamableEnderWoman> extends EnderWomanAnimator<E> {
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
			    this.animate(enderWoman.loopAnimationState, EnderWomanAnimation.CARRING, ageInTicks);
			}
			if (tamableData.isPanic()) {
				this.animate(enderWoman.loopAnimationState, EnderWomanAnimation.IDLE, ageInTicks);						
			} 		
			else if (tamableData.isWaiting()) {
				this.animate(enderWoman.loopAnimationState, EnderWomanAnimation.WAIT, ageInTicks);										
			}
			else if (enderWoman.isPassenger()) {
				this.animate(enderWoman.loopAnimationState, EnderWomanAnimation.RIDING, ageInTicks);						
			}
			else {
				this.animate(enderWoman.loopAnimationState, EnderWomanAnimation.IDLE, ageInTicks);						
			}
	    }
	}	
}
