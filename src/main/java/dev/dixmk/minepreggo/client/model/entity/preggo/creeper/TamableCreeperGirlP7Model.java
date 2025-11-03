package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP7;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP7Model extends AbstractTamablePregnantCreeperGirlModel<TamableCreeperGirlP7> {
	public TamableCreeperGirlP7Model(ModelPart root) {
		super(root,  TamableCreeperGirlP4Model.createDefaultP4HierarchicalModel(root));
	} 
}
