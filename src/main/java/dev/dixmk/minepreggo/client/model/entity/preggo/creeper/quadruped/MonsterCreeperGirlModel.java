package dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlModel extends AbstractMonsterCreeperGirlModel<MonsterCreeperGirl> {
	public MonsterCreeperGirlModel(ModelPart root) {
		super(root);
	}
}
