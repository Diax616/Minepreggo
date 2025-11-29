package dev.dixmk.minepreggo.network.capability;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.pregnancy.MaleEntityImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
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
	public void serializeNBT(@NonNull Tag tag) {
		super.serializeNBT(tag);
		CompoundTag nbt = (CompoundTag) tag;
		nbt.putInt("DataFapTimer", fapTimer);
		nbt.putInt("DataFap", fap);
	}
	
	@Override
	public void deserializeNBT(@NonNull Tag tag) {
		super.deserializeNBT(tag);
		CompoundTag nbt = (CompoundTag) tag;
		fapTimer = nbt.getInt("DataFapTimer");
		fap = nbt.getInt("Datafap");
	}
	
	public void copyFrom(@NonNull MalePlayerImpl target) {
	    CompoundTag nbt = new CompoundTag();
	    target.serializeNBT(nbt);
	    this.deserializeNBT(nbt);
	}
}
