package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.world.pregnancy.MaleEntityImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public class MalePlayerImpl extends MaleEntityImpl {

	private static final int MAX_FAP = 20;
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

	public boolean canFap() {
		return this.fap >= 7;
	}

	public void setFap(int amount) {
		this.fap = Mth.clamp(amount, 0, MAX_FAP);
	}

	public void incrementFap(int amount) {
		this.fap = Math.max(this.fap + Math.max(0, amount), MAX_FAP);
	}

	public void reduceFap(int amount) {
		this.fap = Math.min(this.fap - Math.max(0, amount), 0);		
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
