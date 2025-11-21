package dev.dixmk.minepreggo.network.capability;

import java.util.Set;
import java.util.UUID;

import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;

public interface IMaleEntity extends IBreedable {

	Set<UUID> getPregnantFemaleEntitiesByHim();
	
	boolean addPregnantEntity(UUID pregnantEntity);
	boolean removePregnantEntity(UUID pregnantEntity);
}
