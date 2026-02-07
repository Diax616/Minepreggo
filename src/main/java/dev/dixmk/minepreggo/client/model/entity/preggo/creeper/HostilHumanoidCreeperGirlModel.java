package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilHumanoidCreeperGirlModel extends AbstractHostilHumanoidCreeperGirlModel<HostilHumanoidCreeperGirl> {
	public HostilHumanoidCreeperGirlModel(ModelPart root) {
		super(root);
	}
	
	@Override
	public void setupAnim(HostilHumanoidCreeperGirl entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);	
		if (this.young) {
			this.boobs.setPos(0F, 1.5F, -0.075F);
		}	
	}
}
