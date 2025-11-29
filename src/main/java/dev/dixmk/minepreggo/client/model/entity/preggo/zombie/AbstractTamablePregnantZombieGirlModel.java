package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantZombieGirlModel<E extends AbstractTamablePregnantZombieGirl<?,?>> extends AbstractTamableZombieGirlModel<E> {

	protected float extraBoobsXScale = 1.2F;
	protected float extraBoobsYScale = 1.1F;
	protected float extraBoobsZScale = 1.2F;
	protected float extraBoobsYPos = -0.34F;
	
	protected AbstractTamablePregnantZombieGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);
		this.belly.visible = true;
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		
		if (entity.getPregnancySymptoms().contains(PregnancySymptom.MILKING)) {
			this.boobs.y += extraBoobsYPos;
			this.boobs.xScale = extraBoobsXScale;
			this.boobs.zScale = extraBoobsZScale;
			this.boobs.yScale = extraBoobsYScale;			
		} 
	}
}
