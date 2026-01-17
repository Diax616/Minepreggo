package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidMonsterCreeperGirlModel<E extends AbstractMonsterHumanoidCreeperGirl> extends AbstractHumanoidCreeperGirlModel<E> {

	protected AbstractHumanoidMonsterCreeperGirlModel(ModelPart root, HumanoidCreeperGirlAnimator<E> animator) {
		super(root, animator);
		this.belly.visible = false;
	}

	protected AbstractHumanoidMonsterCreeperGirlModel(ModelPart root) {
		this(root, new HumanoidCreeperGirlAnimator.BasicHumanoidZombieGirlAnimator<>(root));
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		this.moveHeadWithHat(entity, netHeadYaw, headPitch);
	}
}