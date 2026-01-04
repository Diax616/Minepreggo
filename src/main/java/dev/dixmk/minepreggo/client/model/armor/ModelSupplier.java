package dev.dixmk.minepreggo.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;

@FunctionalInterface
interface ModelSupplier {
	HumanoidModel<LivingEntity> create();
}
