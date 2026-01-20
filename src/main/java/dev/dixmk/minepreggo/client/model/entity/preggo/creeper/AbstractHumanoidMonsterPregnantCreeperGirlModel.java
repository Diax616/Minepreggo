package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidMonsterPregnantCreeperGirlModel<E extends AbstractMonsterPregnantHumanoidCreeperGirl> extends AbstractHumanoidCreeperGirlModel<E> {

	protected AbstractHumanoidMonsterPregnantCreeperGirlModel(ModelPart root, HumanoidCreeperGirlAnimator<E> animator, @Nonnull PregnancyPhase phase, boolean simpleBellyJiggle) {
		super(root, animator, phase, simpleBellyJiggle);	
		this.belly.visible = true;
	}
	
	@Override
	protected @Nonnull EntityJiggleDataFactory.JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobsAndBelly(this.boobs.y, this.belly.y);
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
