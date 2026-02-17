package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllHumanoidCreeperGirlModel extends AbstractHostileHumanoidCreeperGirlModel<IllHumanoidCreeperGirl> {
	public IllHumanoidCreeperGirlModel(ModelPart root) {
		super(root);
	}
}
