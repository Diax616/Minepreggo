package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP6;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP6Model extends AbstractTamablePregnantCreeperGirlModel<TamableCreeperGirlP6> {
	
	public TamableCreeperGirlP6Model(ModelPart root) {
		super(root,  TamableCreeperGirlP4Model.createDefaultP4HierarchicalModel(root));
	}

}
