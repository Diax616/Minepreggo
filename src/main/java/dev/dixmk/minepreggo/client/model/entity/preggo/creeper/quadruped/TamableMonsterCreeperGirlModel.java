package dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableMonsterCreeperGirlModel extends AbstractTamableMonsterCreeperGirlModel<TamableMonsterCreeperGirl> {
	public TamableMonsterCreeperGirlModel(ModelPart root) {
		super(root);
	}
}
