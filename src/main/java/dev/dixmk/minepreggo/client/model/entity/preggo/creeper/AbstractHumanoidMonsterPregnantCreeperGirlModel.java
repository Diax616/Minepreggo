package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidMonsterPregnantCreeperGirlModel<E extends AbstractMonsterPregnantHumanoidCreeperGirl> extends AbstractHumanoidCreeperGirlModel<E> {

	protected AbstractHumanoidMonsterPregnantCreeperGirlModel(ModelPart root, HumanoidCreeperGirlAnimator<E> animator) {
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
}
