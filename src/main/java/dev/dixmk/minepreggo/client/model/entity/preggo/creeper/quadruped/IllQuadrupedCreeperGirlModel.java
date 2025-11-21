package dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllQuadrupedCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllQuadrupedCreeperGirlModel extends AbstractMonsterQuadrupedCreeperGirlModel<IllQuadrupedCreeperGirl> {
	public IllQuadrupedCreeperGirlModel(ModelPart root) {
		super(root);
	}
}
