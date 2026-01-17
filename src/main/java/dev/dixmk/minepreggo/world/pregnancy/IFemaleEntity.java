package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;

public interface IFemaleEntity extends IBreedable {

    int getPregnancyInitializerTimer();
    void setPregnancyInitializerTimer(int ticks);
    void incrementPregnancyInitializerTimer();
    
    boolean isPregnant();
    boolean tryImpregnate(@Nonnegative int fertilizedEggs, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father);
    boolean tryCancelPregnancy();
    
    Optional<PrePregnancyData> getPrePregnancyData();
    Optional<IPostPregnancyDataHolder> getPostPregnancyData();
    
    boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy);
    boolean tryRemovePostPregnancyPhase();
}
