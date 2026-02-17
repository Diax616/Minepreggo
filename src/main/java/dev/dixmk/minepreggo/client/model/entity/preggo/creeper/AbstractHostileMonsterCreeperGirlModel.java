package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.animation.preggo.MonsterCreeperGirlAnimation;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostileCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostileMonsterCreeperGirlModel<E extends AbstractHostileCreeperGirl> extends AbstractMonsterCreeperGirlModel<E> {

	protected AbstractHostileMonsterCreeperGirlModel(ModelPart root) {
		super(root, null);
		this.belly.visible = false;
	}
	
	protected AbstractHostileMonsterCreeperGirlModel(ModelPart root, PregnancyPhase phase, boolean simpleBellyJiggle) {
		super(root, ImmutablePair.of(phase, simpleBellyJiggle));
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

		this.animate(entity.loopAnimationState, MonsterCreeperGirlAnimation.IDLE, ageInTicks);	
	}
}
