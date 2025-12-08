package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableHumanoidCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableHumanoidCreeperGirlModel <E extends AbstractTamableHumanoidCreeperGirl<?>> extends AbstractHumanoidCreeperGirlModel<E> {
	
	protected AbstractTamableHumanoidCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);
		this.belly.visible = false;
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHeadWithHat(entity, netHeadYaw, headPitch);
		}	
	}
}
