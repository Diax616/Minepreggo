package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;

public abstract class AbstractTamablePregnantZombieGirlModel<E extends AbstractTamablePregnantZombieGirl<?,?>> extends AbstractTamableZombieGirlModel<E> {

	protected AbstractTamablePregnantZombieGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);
		this.belly.visible = true;
	}

	
}
