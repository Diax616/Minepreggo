package dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllCreeperGirlModel extends AbstractMonsterCreeperGirlModel<IllCreeperGirl> {
	public IllCreeperGirlModel(ModelPart root) {
		super(root);
	}
}
