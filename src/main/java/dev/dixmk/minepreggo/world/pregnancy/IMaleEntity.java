package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Set;
import java.util.UUID;

public interface IMaleEntity extends IBreedable {

	Set<UUID> getPregnantFemaleEntitiesByHim();
	
	boolean addPregnantEntity(UUID pregnantEntity);
	boolean removePregnantEntity(UUID pregnantEntity);
}
