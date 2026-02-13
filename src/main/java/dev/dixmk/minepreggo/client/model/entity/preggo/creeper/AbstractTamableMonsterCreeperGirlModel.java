package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.animation.preggo.MonsterCreeperGirlAnimation;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableMonsterCreeperGirlModel<E extends AbstractTamableCreeperGirl> extends AbstractMonsterCreeperGirlModel<E> {

	protected AbstractTamableMonsterCreeperGirlModel(ModelPart root) {
		super(root, null);
		this.belly.visible = false;
	}

	protected AbstractTamableMonsterCreeperGirlModel(ModelPart root, ImmutablePair<PregnancyPhase, Boolean> pregnancyPhaseAndSimpleBellyJiggle) {
		super(root, pregnancyPhaseAndSimpleBellyJiggle);
	}
	
	@Override
	protected JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobs(this.boobs.y);
	}	
	
	@Override
	protected void animate(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isAttacking()) {
		    this.animate(entity.attackAnimationState, MonsterCreeperGirlAnimation.ATTACK, ageInTicks);
		}
		
		if (entity.walkAnimation.isMoving()) {
			if (entity.isAggressive()) {
				this.animateWalk(MonsterCreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
			else {
				this.animateWalk(MonsterCreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
		} 
		final var tamableData = entity.getTamableData();
		if (tamableData.isPanic()) {
			this.animate(entity.loopAnimationState, MonsterCreeperGirlAnimation.IDLE, ageInTicks);						
		} 		
		else if (tamableData.isWaiting()) {
			this.animate(entity.loopAnimationState, MonsterCreeperGirlAnimation.WAIT, ageInTicks);										
		}
		else {
			this.animate(entity.loopAnimationState, MonsterCreeperGirlAnimation.IDLE, ageInTicks);						
		}	
	}	
}
