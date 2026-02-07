package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.MonsterCreeperGirlAnimation;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilMonsterCreeperGirlModel<E extends AbstractHostilCreeperGirl> extends AbstractMonsterCreeperGirlModel<E> {

	protected AbstractHostilMonsterCreeperGirlModel(ModelPart root) {
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
