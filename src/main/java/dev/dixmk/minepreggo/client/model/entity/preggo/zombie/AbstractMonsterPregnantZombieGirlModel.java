package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.client.animation.preggo.ZombieGirlAnimation;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterPregnantZombieGirlModel<E extends AbstractMonsterPregnantZombieGirl> extends AbstractMonsterZombieGirlModel<E> {

	protected AbstractMonsterPregnantZombieGirlModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));		
	}

	protected AbstractMonsterPregnantZombieGirlModel(ModelPart root, HierarchicalModel<E> animate) {
		super(root, animate);
		this.belly.visible = true;
	}
		
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		tryHideBoobs(entity, PregnancySystemHelper::shouldBoobsBeHidden);
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		if (entity.hasPregnancyPain()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHeadWithHat(entity, netHeadYaw, headPitch);
		}
	}
	
	private static<E extends AbstractMonsterPregnantZombieGirl> HierarchicalModel<E> createDefaultHierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);	
				
			    if (zombieGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
			    	this.animate(zombieGirl.loopAnimationState, BellyAnimation.MEDIUM_BELLY_INFLATION, ageInTicks, 1f);
			    }
			    		    
			    if (zombieGirl.isAttacking()) {
				    this.animate(zombieGirl.attackAnimationState, ZombieGirlAnimation.ATTACK, ageInTicks, 1f);	
			    }
				
				if (zombieGirl.walkAnimation.isMoving()) {
					if (zombieGirl.isAggressive()) {
						this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(ZombieGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				}	
				if (zombieGirl.hasPregnancyPain()) {
				    this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.CONTRACTION2, ageInTicks, 1f);	
				}
				else if (zombieGirl.isPassenger()) {
				    this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.RIDING, ageInTicks, 1f);	
				}
				else {
				    this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);	
				}			
			}
		};
	}
}
