package dev.dixmk.minepreggo.network.capability;

import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;

public interface IFemaleEntity extends IBreedable {

    int getPregnancyInitializerTimer();
    void setPregnancyInitializerTimer(int ticks);
    void incrementPregnancyInitializerTimer();
    
    boolean isPregnant();
    boolean canGetPregnant();
    boolean tryImpregnate(@Nullable UUID father);
     
    boolean tryCancelPregnancy();
    
    @Nullable UUID getFather();
    
    boolean hasNaturalPregnancy();
    
    @Nullable PostPregnancy getPostPregnancyPhase();

    boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy);
    
    int getPostPregnancyTimer();
    void setPostPregnancyTimer(int ticks);
    void incrementPostPregnancyTimer();
}
