package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.client.animation.preggo.EnderGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractMonsterEnderWoman;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterEnderWomanModel
	<E extends AbstractMonsterEnderWoman> extends AbstractEnderWomanModel<E> {
	
	protected AbstractMonsterEnderWomanModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);
		this.belly.visible = false;
	}
	
	protected AbstractMonsterEnderWomanModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));
	}
	
	private static<E extends AbstractMonsterEnderWoman> HierarchicalModel<E> createDefaultHierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {		
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
						
				boolean isCarring = entity.isCarring();
				
				if (!isCarring && entity.isAttacking()) {
				    this.animate(entity.attackAnimationState, EnderGirlAnimation.ATTACK, ageInTicks, 1f);
				}
				
				if (isCarring) {
				    this.animate(entity.loopAnimationState, EnderGirlAnimation.CARRING, ageInTicks, 1f);
				}
				
				if (entity.isCreepy()) {
				    this.animate(entity.loopAnimationState, EnderGirlAnimation.CREEPY, ageInTicks, 1f);
				}
						
				if (entity.walkAnimation.isMoving()) {
					if (isCarring) {
						this.animateWalk(EnderGirlAnimation.WALK2, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
					else {
						this.animateWalk(EnderGirlAnimation.WALK1, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
				} 
				
				if (entity.isPassenger()) {
					this.animate(entity.loopAnimationState, EnderGirlAnimation.RIDING, ageInTicks, 1f);						
				}
				else {
					this.animate(entity.loopAnimationState, EnderGirlAnimation.IDLE, ageInTicks, 1f);						
				}		
			}	
		};
	}
}
