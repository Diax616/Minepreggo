package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableHumanoidCreeperGirlModel <E extends AbstractTamableCreeperGirl> extends AbstractHumanoidCreeperGirlModel<E> {
	
	protected AbstractTamableHumanoidCreeperGirlModel(ModelPart root, HumanoidCreeperGirlAnimator<E> animator) {
		super(root, animator, null, false);
		this.belly.visible = false;
	}
	
	protected AbstractTamableHumanoidCreeperGirlModel(ModelPart root, HumanoidCreeperGirlAnimator<E> animator, PregnancyPhase phase, boolean simpleBellyJiggle) {
		super(root, animator, phase, simpleBellyJiggle);
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
