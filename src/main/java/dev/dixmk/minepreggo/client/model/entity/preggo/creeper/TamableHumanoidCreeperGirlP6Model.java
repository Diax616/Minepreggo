package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP6;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP6Model extends AbstractTamableHumanoidPregnantCreeperGirlModel<TamableHumanoidCreeperGirlP6> {
	
	public TamableHumanoidCreeperGirlP6Model(ModelPart root) {
		super(root,  TamableHumanoidCreeperGirlP4Model.createDefaultP4HierarchicalModel(root));
	}

}
