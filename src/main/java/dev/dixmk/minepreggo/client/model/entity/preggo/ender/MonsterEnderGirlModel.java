package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.ender.MonsterEnderGirlP0;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterEnderGirlModel extends AbstractMonsterEnderGirlModel<MonsterEnderGirlP0>{
	public MonsterEnderGirlModel(ModelPart root) {
		super(root);
	}
}
