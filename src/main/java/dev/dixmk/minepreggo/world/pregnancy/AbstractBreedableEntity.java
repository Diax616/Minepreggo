package dev.dixmk.minepreggo.world.pregnancy;

import javax.annotation.Nonnegative;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class AbstractBreedableEntity implements IBreedable, INBTSerializable<CompoundTag> {
	protected final Gender gender;
	protected float fertility = 0.4F;
	protected int fertilityRateTimer = 0;
	
	protected int sexualAppetiteTimer = 0;
	protected int sexualAppetite = IBreedable.MAX_SEXUAL_APPETIVE;
	
	protected AbstractBreedableEntity(Gender gender) {
		this.gender = gender;
	}

	@Override
	public Gender getGender() {
		return this.gender;
	}

	@Override
	public float getFertilityRate() {
		return this.fertility;
	}

	@Override
	public void setFertilityRate(float percentage) {
		this.fertility = Mth.clamp(percentage, 0, 1F);
	}

	@Override
	public void incrementFertilityRate(float amount) {
		this.fertility = Mth.clamp(this.fertility + amount, 0, MAX_FERTILITY_RATE);
	}

	@Override
	public void resetFertilityRate() {
		this.fertility = 0F;
	}
	
	@Override
	public int getFertilityRateTimer() {
		return this.fertilityRateTimer;
	}

	@Override
	public void setFertilityRateTimer(int ticks) {
		this.fertilityRateTimer = Math.max(0, ticks);
		
	}
	
	@Override
	public void resetFertilityRateTimer() {
		this.fertilityRateTimer = 0;
	}

	@Override
	public void incrementFertilityRateTimer() {
		++this.fertilityRateTimer;
	}

	@Override
	public int getSexualAppetite() {
		return this.sexualAppetite;
	}

	@Override
	public void setSexualAppetite(@Nonnegative int sexualAppetite) {
		this.sexualAppetite = Mth.clamp(sexualAppetite, 0, MAX_SEXUAL_APPETIVE);	
	}

	@Override
	public void reduceSexualAppetite(@Nonnegative int amount) {
		setSexualAppetite(sexualAppetite - Math.max(0, amount));
	}

	@Override
	public void incrementSexualAppetite(@Nonnegative int amount) {
		setSexualAppetite(sexualAppetite + Math.max(0, amount));
	}

	@Override
	public int getSexualAppetiteTimer() {
		return this.sexualAppetiteTimer;
	}

	@Override
	public void setSexualAppetiteTimer(int timer) {
		this.sexualAppetiteTimer = Math.max(0, timer);
	}

	@Override
	public void incrementSexualAppetiteTimer() {
		++this.sexualAppetiteTimer;
	}

	@Override
	public boolean canFuck() {
		return sexualAppetite >= 5;
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putFloat("DataFertility", fertility);
		nbt.putInt("DataFertilityRateTimer", fertilityRateTimer);
		nbt.putInt("DataSexualAppetive", sexualAppetite);
		nbt.putInt("DataSexualAppetiveTimer", sexualAppetiteTimer);	
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		fertility = nbt.getFloat("DataFertility");
		fertilityRateTimer = nbt.getInt("DataFertilityRateTimer");
		sexualAppetite = nbt.getInt("DataSexualAppetive");
		sexualAppetiteTimer = nbt.getInt("DataSexualAppetiveTimer");
	}
}