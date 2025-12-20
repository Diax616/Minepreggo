package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.CreeperGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterHumanoidCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidMonsterCreeperGirlModel<E extends AbstractMonsterHumanoidCreeperGirl> extends AbstractHumanoidCreeperGirlModel<E> {

	protected AbstractHumanoidMonsterCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);
		this.belly.visible = false;
	}

	protected AbstractHumanoidMonsterCreeperGirlModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		this.moveHeadWithHat(entity, netHeadYaw, headPitch);
	}
	
	private static<E extends AbstractMonsterCreeperGirl> HierarchicalModel<E> createDefaultHierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
						
				if (entity.isAttacking()) {
				    this.animate(entity.attackAnimationState, CreeperGirlAnimation.ATTACK, ageInTicks, 1f);
				}
				
				if (entity.walkAnimation.isMoving()) {
					if (entity.isAggressive()) {
						this.animateWalk(CreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
					else {
						this.animateWalk(CreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
				} 

				this.animate(entity.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);						
			}	
		};
	}
}