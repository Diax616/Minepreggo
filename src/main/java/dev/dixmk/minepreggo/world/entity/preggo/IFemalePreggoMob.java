package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Optional;

import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;

public interface IFemalePreggoMob<E extends PreggoMob> extends IFemaleEntity {

	Optional<SyncedPostPregnancyData<E>> getSyncedPostPregnancyData();
    
}
