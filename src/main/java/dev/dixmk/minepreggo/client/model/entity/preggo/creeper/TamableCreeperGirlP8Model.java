package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP8;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP8Model extends AbstractTamablePregnantCreeperGirlModel<TamableCreeperGirlP8> {
	public TamableCreeperGirlP8Model(ModelPart root) {
		super(root,  TamableCreeperGirlP4Model.createDefaultP4HierarchicalModel(root));
	} 
}
