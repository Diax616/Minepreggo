package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterZombieGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterZombieGirlModel<E extends AbstractMonsterZombieGirl> extends AbstractZombieGirlModel<E> {

	protected AbstractMonsterZombieGirlModel(ModelPart root, ZombieGirlAnimator<E> animator) {
		super(root, animator);
		this.belly.visible = false;
	}
	
	protected AbstractMonsterZombieGirlModel(ModelPart root) {
		this(root, new ZombieGirlAnimator.BasicZombieGirlAnimator<>(root));
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		this.moveHeadWithHat(entity, netHeadYaw, headPitch);
	}
}
