package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.Species;

public interface IFemaleEntity extends IBreedable {

    int getPregnancyInitializerTimer();
    void setPregnancyInitializerTimer(int ticks);
    void incrementPregnancyInitializerTimer();
    
    boolean isPregnant();
    boolean canGetPregnant();
    boolean tryImpregnate(@Nonnegative int fertilizedEggs, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father);
     
    boolean tryCancelPregnancy();
    
    @Nullable UUID getFather();
    
    Optional<PrePregnancyData> getPrePregnancyData();
    
    boolean hasNaturalPregnancy();
    
    @Nullable PostPregnancy getPostPregnancyPhase();

    boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy);
    
    int getPostPregnancyTimer();
    void setPostPregnancyTimer(int ticks);
    void incrementPostPregnancyTimer();
}
