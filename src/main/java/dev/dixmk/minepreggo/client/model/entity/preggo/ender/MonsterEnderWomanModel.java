package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.ender.HostilMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterEnderWomanModel extends AbstractHostilMonsterEnderWomanModel<HostilMonsterEnderWoman>{
	public MonsterEnderWomanModel(ModelPart root) {
		super(root);
	}
}
