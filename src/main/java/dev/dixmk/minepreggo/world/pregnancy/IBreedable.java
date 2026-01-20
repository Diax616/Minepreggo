package dev.dixmk.minepreggo.world.pregnancy;

import javax.annotation.Nonnegative;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IBreedable extends INBTSerializable<CompoundTag> {

	public static final float MAX_FERTILITY_RATE = 1.0f;
	public static final int MAX_SEXUAL_APPETIVE = 20;
	
    int getFertilityRateTimer();
    void setFertilityRateTimer(int ticks);
    void incrementFertilityRateTimer();
    void resetFertilityRateTimer();
    
    float getFertilityRate();
    void setFertilityRate(float rate);
    void incrementFertilityRate(float rate);
    void resetFertilityRate();
  
    int getSexualAppetite();
    void setSexualAppetite(@Nonnegative int sexualAppetite);
    void reduceSexualAppetite(@Nonnegative int amount);
    void incrementSexualAppetite(@Nonnegative int amount);
    
    int getSexualAppetiteTimer();
    void setSexualAppetiteTimer(int timer);
    void incrementSexualAppetiteTimer();
    
	Gender getGender();
    
    boolean canFuck();
}