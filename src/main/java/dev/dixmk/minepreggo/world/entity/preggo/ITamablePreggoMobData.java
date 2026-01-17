package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;

import org.jetbrains.annotations.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ITamablePreggoMobData extends INBTSerializable<CompoundTag> {
 
	int getFullness();
    void setFullness(@Nonnegative int fullness);
    void incrementFullness(@Nonnegative int amount);
    void decrementFullness(@Nonnegative int amount);
    
    boolean isWaiting();
    void setWaiting(boolean waiting);
    
    boolean isSavage();
    void setSavage(boolean savage);
    
    boolean isAngry();
    void setAngry(boolean angry);
    
    int getHungryTimer();
    void setHungryTimer(@Nonnegative int ticks);
    void incrementHungryTimer();
    void resetHungryTimer();
    
    boolean isPanic();
    void setPanic(boolean panic);
    boolean canBePanicking();
    
    @Nullable PreggoMobFace getFaceState();
	void setFaceState (@Nullable PreggoMobFace state);
	void cleanFaceState ();
	
	@Nullable PreggoMobBody getBodyState();
	void setBodyState (@Nullable PreggoMobBody state);
	void cleanBodyState ();
}
