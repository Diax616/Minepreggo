package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.ZombieGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterZombieGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterZombieGirlModel<E extends AbstractMonsterZombieGirl> extends AbstractZombieGirlModel<E> {

	protected AbstractMonsterZombieGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);
		this.belly.visible = false;
	}

	protected AbstractMonsterZombieGirlModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		this.moveHeadWithHat(entity, netHeadYaw, headPitch);
	}
	
	private static<E extends AbstractMonsterZombieGirl> HierarchicalModel<E> createDefaultHierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
						
				if (entity.isAttacking()) {
				    this.animate(entity.attackAnimationState, ZombieGirlAnimation.ATTACK, ageInTicks, 1f);
				}
				
				if (entity.walkAnimation.isMoving()) {
					if (entity.isAggressive()) {
						this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
					else {
						this.animateWalk(ZombieGirlAnimation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
				} 
				
				this.animate(entity.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);						
			}	
		};
	}
}
