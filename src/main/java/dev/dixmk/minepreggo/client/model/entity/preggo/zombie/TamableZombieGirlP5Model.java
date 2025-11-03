package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP5;
import net.minecraft.client.model.geom.ModelPart;

public class TamableZombieGirlP5Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP5> {
	
	public TamableZombieGirlP5Model(ModelPart root) {
		super(root, TamableZombieGirlP4Model.createDefaultP4HierarchicalModel(root));
	}
}
