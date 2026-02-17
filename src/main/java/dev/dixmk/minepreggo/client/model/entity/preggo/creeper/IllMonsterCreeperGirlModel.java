package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllMonsterCreeperGirlModel extends AbstractHostileMonsterCreeperGirlModel<IllMonsterCreeperGirl> {
	public IllMonsterCreeperGirlModel(ModelPart root) {
		super(root);
	}
}
