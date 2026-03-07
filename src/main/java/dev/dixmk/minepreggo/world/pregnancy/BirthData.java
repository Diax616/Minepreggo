package dev.dixmk.minepreggo.world.pregnancy;

import dev.dixmk.minepreggo.init.BirthClientData;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class BirthData implements INBTSerializable<CompoundTag> {
	private BabyData babyData;
	private int birthDuration;
	private int elapsedTime = 0;
	
	public BirthData(BabyData babyData, int birthDuration) {
		this.babyData = babyData;
		this.birthDuration = birthDuration;
	}
	
	public BirthData(CompoundTag nbt) throws IllegalArgumentException {
		this.deserializeNBT(nbt);
	}

	public BabyData getBabyData() {
		return babyData;
	}
	
	public int getBirthDuration() {
		return birthDuration;
	}
	
	public int getElapsedTime() {
		return elapsedTime;
	}
	
	public void tick() {
		this.elapsedTime++;
	}
	
	public boolean isBirthComplete() {
		return elapsedTime >= birthDuration;
	}

	public BirthClientData createClientData() {
		return new BirthClientData(babyData);
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		CompoundTag wrapper = new CompoundTag();	
		nbt.put("BabyData", babyData.toNBT());
		nbt.putInt("BirthDuration", birthDuration);
		nbt.putInt("ElapsedTime", elapsedTime);
		wrapper.put("BirthData", nbt);
		return wrapper;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) throws IllegalArgumentException {
		if (!nbt.contains("BirthData")) {
			throw new IllegalArgumentException("NBT does not contain BirthData");
		}
		CompoundTag wrapper = nbt.getCompound("BirthData");
		this.babyData = BabyData.fromNBT(wrapper.getCompound("BabyData"));
		this. birthDuration = wrapper.getInt("BirthDuration");
		this. elapsedTime = wrapper.getInt("ElapsedTime");
	}
}
