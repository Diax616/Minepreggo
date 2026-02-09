package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostilZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilZombieGirlModel<E extends AbstractHostilZombieGirl> extends AbstractZombieGirlModel<E> {

	protected AbstractHostilZombieGirlModel(ModelPart root, ZombieGirlAnimator<E> animator, PregnancyPhase phase, boolean simpleBellyJiggle) {
		super(root, animator, phase, simpleBellyJiggle);
		this.belly.visible = false;
	}
	
	protected AbstractHostilZombieGirlModel(ModelPart root) {
		this(root, new ZombieGirlAnimator.BasicZombieGirlAnimator<>(root), null, false);
	}
	
	@Override
	protected @Nonnull EntityJiggleDataFactory.JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobs(this.boobs.y);
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHeadWithHat(entity, netHeadYaw, headPitch);
		}
	}
}
