package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterHumanoidCreeperGirlModel extends AbstractHumanoidMonsterCreeperGirlModel<MonsterHumanoidCreeperGirl> {
	public MonsterHumanoidCreeperGirlModel(ModelPart root) {
		super(root);
	}
	
	@Override
	public void setupAnim(MonsterHumanoidCreeperGirl entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);	
		if (this.young) {
			this.boobs.setPos(0F, 1.5F, -0.075F);
		}	
	}
}
