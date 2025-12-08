package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableHumanoidPregnantCreeperGirlModel<E extends AbstractTamablePregnantHumanoidCreeperGirl<?,?>> extends AbstractHumanoidCreeperGirlModel<E> {

	protected float milkingBoobsXScale = 1.25F;
	protected float milkingBoobsYScale = 1.1F;
	protected float milkingBoobsZScale = 1.2F;
	protected float milkingBoobsYPos = -0.375F;
	
	protected AbstractTamableHumanoidPregnantCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);
		this.belly.visible = true;
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
		
		if (entity.getPregnancySymptoms().contains(PregnancySymptom.MILKING)) {
			this.boobs.y += milkingBoobsYPos;
			this.boobs.xScale = milkingBoobsXScale;
			this.boobs.zScale = milkingBoobsYScale;
			this.boobs.yScale = milkingBoobsZScale;			
		} 
	}
}
