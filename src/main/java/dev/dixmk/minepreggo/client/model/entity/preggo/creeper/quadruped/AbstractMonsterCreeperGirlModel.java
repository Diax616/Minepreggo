package dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.client.animation.preggo.QuadrupedCreeperGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterQuadrupedCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlModel<E extends AbstractMonsterQuadrupedCreeperGirl> extends AbstractCreeperGirlModel<E> {

	protected AbstractMonsterCreeperGirlModel(ModelPart root) {
		super(root);
		this.belly.visible = false;
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		
		this.moveHead(entity, netHeadYaw, headPitch);
		
		if (entity.isAttacking()) {
		    this.animate(entity.attackAnimationState, QuadrupedCreeperGirlAnimation.ATTACK, ageInTicks, 1f);
		}
		
		if (entity.walkAnimation.isMoving()) {
			if (entity.isAggressive()) {
				this.animateWalk(QuadrupedCreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
			else {
				this.animateWalk(QuadrupedCreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
			}
		} 

		this.animate(entity.loopAnimationState, QuadrupedCreeperGirlAnimation.IDLE, ageInTicks, 1f);						
	}
}
