package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP5;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP5Model extends AbstractTamableHumanoidPregnantCreeperGirlModel<TamableHumanoidCreeperGirlP5> {
	
	public TamableHumanoidCreeperGirlP5Model(ModelPart root) {
		super(root,  TamableHumanoidCreeperGirlP4Model.createDefaultP4HierarchicalModel(root));
	}
}
