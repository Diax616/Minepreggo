package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilMonsterCreeperGirlModel extends AbstractHostilMonsterCreeperGirlModel<HostilMonsterCreeperGirl> {
	public HostilMonsterCreeperGirlModel(ModelPart root) {
		super(root);
	}
}
