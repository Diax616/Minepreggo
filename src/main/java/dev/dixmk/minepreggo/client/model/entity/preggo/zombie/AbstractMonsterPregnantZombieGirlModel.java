package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterPregnantZombieGirlModel<E extends AbstractMonsterPregnantZombieGirl> extends AbstractMonsterZombieGirlModel<E> {

	protected AbstractMonsterPregnantZombieGirlModel(ModelPart root, ZombieGirlAnimator<E> animate) {
		super(root, animate);
		this.belly.visible = true;
	}
		
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		tryHideBoobs(entity, PregnancySystemHelper::shouldBoobsBeHidden);
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHeadWithHat(entity, netHeadYaw, headPitch);
		}
	}
	
	protected void animBelly(E entity, float ageInTicks) {
		
	}
}
