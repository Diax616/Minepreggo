package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlModel extends AbstractTamableZombieGirlModel<TamableZombieGirl> {
	
	public TamableZombieGirlModel(ModelPart root) {
		super(root, new ZombieGirlAnimator.TamableZombieGirlAnimator<>(root));
	}
	
	@Override
	public void setupAnim(TamableZombieGirl zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(zombieGirl, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);		
		zombieGirl.getSyncedPostPartumLactation().ifPresent(lactation -> {
			if (lactation >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM) {
				this.boobs.y -= 0.34F;
				this.boobs.xScale = 1.2F;
				this.boobs.zScale = 1.1F;
				this.boobs.yScale = 1.2F;
			}
		});
	}
}