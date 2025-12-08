package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP7;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP7Model extends AbstractTamableHumanoidPregnantCreeperGirlModel<TamableHumanoidCreeperGirlP7> {
	public TamableHumanoidCreeperGirlP7Model(ModelPart root) {
		super(root,  TamableHumanoidCreeperGirlP4Model.createDefaultP4HierarchicalModel(root));
	} 
}
