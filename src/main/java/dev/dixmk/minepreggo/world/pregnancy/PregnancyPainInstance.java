package dev.dixmk.minepreggo.world.pregnancy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.util.INBTSerializable;

public class PregnancyPainInstance implements INBTSerializable<CompoundTag> {
	private PregnancyPain pain;
	private int severity;
	private int duration;
	private int elapsedTime = 0;
	
	public PregnancyPainInstance(PregnancyPain pain, int duration, int severity) {
		this.pain = pain;
		this.duration = duration;
		this.severity = severity;
	}
	
	public PregnancyPainInstance(CompoundTag nbt) throws IllegalArgumentException {
		deserializeNBT(nbt);
	}
	
	public PregnancyPainInstance(FriendlyByteBuf buffer) throws IllegalArgumentException {
		read(buffer);
	}
	
	public PregnancyPain getPain() {
		return pain;
	}
	
	public int getSeverity() {
		return severity;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getElapsedTime() {
		return elapsedTime;
	}
	
	public void tick() {
		elapsedTime++;
	}
	
	public boolean isExpired() {
		return elapsedTime >= duration;
	}
	
	@Override
	public int hashCode() {
		return pain.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		PregnancyPainInstance other = (PregnancyPainInstance) obj;
		return pain.equals(other.pain);
	}
	
	public void write(FriendlyByteBuf buffer) {
		buffer.writeByte(17);
		buffer.writeUtf(pain.name());
		buffer.writeInt(duration);
		buffer.writeInt(severity);
		buffer.writeInt(elapsedTime);
	}
	
	public void read(FriendlyByteBuf buffer) throws IllegalArgumentException {
		if (buffer.readByte() != 17) {
			throw new IllegalArgumentException("Invalid buffer data for PregnancyPainInstance: Incorrect type identifier");
		}
		this.pain = PregnancyPain.valueOf(buffer.readUtf());
		this.duration = buffer.readInt();
		this.severity = buffer.readInt();
		this.elapsedTime = buffer.readInt();
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		CompoundTag wrapper = new CompoundTag();
		nbt.putString("Pain", pain.name());
		nbt.putInt("Severity", severity);
		nbt.putInt("Duration", duration);
		nbt.putInt("ElapsedTime", elapsedTime);
		wrapper.put("PregnancyPainInstance", nbt);
		return wrapper;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) throws IllegalArgumentException {
		if (nbt.contains("PregnancyPainInstance")) {
			CompoundTag wrapper = nbt.getCompound("PregnancyPainInstance");
			String painName = wrapper.getString("Pain");
			this.pain = PregnancyPain.valueOf(painName);
			this.severity = wrapper.getInt("Severity");
			this.duration = wrapper.getInt("Duration");
			this.elapsedTime = wrapper.getInt("ElapsedTime");
		} 	
		else {
			throw new IllegalArgumentException("Invalid NBT data for PregnancyPainInstance: Missing 'PregnancyPainInstance' tag");
		}
	}
}
