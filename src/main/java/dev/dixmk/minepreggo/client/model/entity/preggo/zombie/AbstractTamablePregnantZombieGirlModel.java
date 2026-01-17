package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantZombieGirlModel<E extends AbstractTamablePregnantZombieGirl> extends AbstractTamableZombieGirlModel<E> {
	
	protected float milkingBoobsXScale = 1.15F;
	protected float milkingBoobsYScale = 1.05F;
	protected float milkingBoobsZScale = 1.25F;
	protected float milkingBoobsYPos = -0.36F;
	
	protected AbstractTamablePregnantZombieGirlModel(ModelPart root, ZombieGirlAnimator<E> animator) {
		super(root, animator);
		this.belly.visible = true;
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		
		if (entity.getPregnancyData().getSyncedPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.MILKING)) {
			this.boobs.y += milkingBoobsYPos;
			this.boobs.xScale = milkingBoobsXScale;
			this.boobs.yScale = milkingBoobsYScale;		
			this.boobs.zScale = milkingBoobsZScale;	
		} 
	}
}
