package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.ender.IllMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllMonsterEnderWomanModel extends AbstractHostilMonsterEnderWomanModel<IllMonsterEnderWoman>{
	public IllMonsterEnderWomanModel(ModelPart root) {
		super(root);
	}
}
