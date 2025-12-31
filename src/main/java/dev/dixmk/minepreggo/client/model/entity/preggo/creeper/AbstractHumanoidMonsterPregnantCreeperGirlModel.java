package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.HumanoidCreeperGirlAnimation;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidMonsterPregnantCreeperGirlModel<E extends AbstractMonsterPregnantHumanoidCreeperGirl> extends AbstractHumanoidCreeperGirlModel<E> {

	protected AbstractHumanoidMonsterPregnantCreeperGirlModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));		
	}

	protected AbstractHumanoidMonsterPregnantCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);	
		this.belly.visible = true;
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHeadWithHat(entity, netHeadYaw, headPitch);
		}
	}
	
	private static<E extends AbstractMonsterPregnantCreeperGirl> HierarchicalModel<E> createDefaultHierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {

			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
			    if (creeperGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
			    	this.animate(creeperGirl.loopAnimationState, BellyAnimation.MEDIUM_BELLY_INFLATION, ageInTicks, 1f);
			    }
								
			    if (creeperGirl.isAttacking()) {
				    this.animate(creeperGirl.attackAnimationState, HumanoidCreeperGirlAnimation.ATTACK, ageInTicks, 1f);	
			    }
				
				if (creeperGirl.walkAnimation.isMoving()) {
					if (creeperGirl.isAggressive()) {
						this.animateWalk(HumanoidCreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(HumanoidCreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				} 
				
				if (creeperGirl.isIncapacitated()) {
				    this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.CONTRACTION2, ageInTicks, 1f);	
				}
				else if (creeperGirl.isPassenger()) {
				    this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.RIDING, ageInTicks, 1f);	
				}
				else {
				    this.animate(creeperGirl.loopAnimationState, HumanoidCreeperGirlAnimation.IDLE, ageInTicks, 1f);	
				}
			}
		};
	}
	
}
