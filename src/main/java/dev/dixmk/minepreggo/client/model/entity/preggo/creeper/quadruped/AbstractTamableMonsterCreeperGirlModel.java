package dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.client.animation.preggo.CreeperGirlAnimation;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableMonsterCreeperGirlModel<E extends AbstractTamableCreeperGirl> extends AbstractCreeperGirlModel<E> {

	protected AbstractTamableMonsterCreeperGirlModel(ModelPart root) {
		super(root, null);
		this.belly.visible = false;
	}

	@Override
	protected JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobs(this.boobs.y);
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
			
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHead(netHeadYaw, headPitch);
		}	
		
		this.updateJiggle(entity);
		
		if (entity.isAttacking()) {
		    this.animate(entity.attackAnimationState, CreeperGirlAnimation.ATTACK, ageInTicks);
		}
		
		if (entity.walkAnimation.isMoving()) {
			if (entity.isAggressive()) {
				this.animateWalk(CreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
			else {
				this.animateWalk(CreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
		} 
		final var tamableData = entity.getTamableData();
		if (tamableData.isPanic()) {
			this.animate(entity.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks);						
		} 		
		else if (tamableData.isWaiting()) {
			this.animate(entity.loopAnimationState, CreeperGirlAnimation.WAIT, ageInTicks);										
		}
		else {
			this.animate(entity.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks);						
		}	
	}	
}
