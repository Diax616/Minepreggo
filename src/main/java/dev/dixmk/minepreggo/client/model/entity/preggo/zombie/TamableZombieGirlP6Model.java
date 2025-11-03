package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP6;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP6Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP6> {
	
	public TamableZombieGirlP6Model(ModelPart root) {
		super(root, TamableZombieGirlP4Model.createDefaultP4HierarchicalModel(root));
	}
}
