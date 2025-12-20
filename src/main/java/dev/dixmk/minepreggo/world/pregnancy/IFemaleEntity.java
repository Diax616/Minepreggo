package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
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
    Optional<PostPregnancyData> getPostPregnancyData();
    
    boolean hasNaturalPregnancy();
    
    
    /*
     * TODO: This methods are present in PostPregnancyData, they are only present here because of synchronization issues between server and client
     * some fields need to be present on client side for rendering purposes, but the PostPregnancyData is not synchronized properly.
     * */
    @Nullable PostPregnancy getPostPregnancyPhase();
    boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy);
    boolean tryRemovePostPregnancyPhase();
    int getPostPartumLactation();
    void setPostPartumLactation(int amount); 
    int getPostPregnancyTimer();
    void setPostPregnancyTimer(int ticks);
    void incrementPostPregnancyTimer();
}
