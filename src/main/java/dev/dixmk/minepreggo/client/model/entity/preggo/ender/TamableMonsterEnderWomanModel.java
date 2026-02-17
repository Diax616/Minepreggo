package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableMonsterEnderWomanModel extends AbstractTamableMonsterEnderWomanModel<TamableMonsterEnderWoman>{
	public TamableMonsterEnderWomanModel(ModelPart root) {
		super(root);
	}
	
	@Override
	public void setupAnim(TamableMonsterEnderWoman enderWoman, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(enderWoman, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		enderWoman.getSyncedPostPartumLactation().ifPresent(lactation -> {
			if (lactation >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM) {
				this.boobs.y -= 0.34F;
				this.boobs.xScale = 1.2F;
				this.boobs.zScale = 1.1F;
				this.boobs.yScale = 1.2F;
			}
		});
	}
}
