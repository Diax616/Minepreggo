package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.world.pregnancy.MaleEntityImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public class MalePlayerImpl extends MaleEntityImpl implements IMalePlayer {

	private int fapTimer = 0;
	private int fap = 0;
	
	public int getFapTimer() {
		return fapTimer;
	}

	public void setFapTimer(int ticks) {
		this.fapTimer = Math.max(0, ticks);
		
	}

	public void incrementFapTimer() {
		++this.fapTimer;	
	}

	@Override
	public void resetFapTimer() {
		this.fapTimer = 0;	
	}
	
	public boolean canFap() {
		return this.fap >= 6;
	}

	@Override
	public int getFap() {
		return this.fap;
	}
	
	public void setFap(int amount) {
		this.fap = Mth.clamp(amount, 0, MAX_FAP);
	}

	public void incrementFap(int amount) {
		setFap(this.fap + Math.abs(amount));
	}

	public void decrementFap(int amount) {
		setFap(this.fap - Math.abs(amount));	
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = super.serializeNBT();
		nbt.putInt("DataFapTimer", fapTimer);
		nbt.putInt("DataFap", fap);
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);
		fapTimer = nbt.getInt("DataFapTimer");
		fap = nbt.getInt("Datafap");
	}
}
