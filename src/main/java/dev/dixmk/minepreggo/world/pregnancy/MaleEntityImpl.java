package dev.dixmk.minepreggo.world.pregnancy;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class MaleEntityImpl extends AbstractBreedableEntity implements IMaleEntity {

	private Set<UUID> pregnantEntities = new HashSet<>();
	
	public MaleEntityImpl() {
		super(Gender.MALE);
	}
	
	@Override
	public Set<UUID> getPregnantFemaleEntitiesByHim() {
		return pregnantEntities;
	}

	@Override
	public boolean addPregnantEntity(UUID pregnantEntity) {
		return this.pregnantEntities.add(pregnantEntity);
	}

	@Override
	public boolean removePregnantEntity(UUID pregnantEntity) {
		return this.pregnantEntities.remove(pregnantEntity);
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = super.serializeNBT();	
		if (!this.pregnantEntities.isEmpty()) {
			ListTag list = new ListTag();
			this.pregnantEntities.forEach(value -> {		
				CompoundTag pair = new CompoundTag();
			    pair.putUUID("value", value);
				list.add(pair);
			});
			nbt.put("DataPregnantEntitiesByHim", list);
		}
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);
		if (nbt.contains("DataPregnantEntitiesByHim", Tag.TAG_LIST)) {
			ListTag list = nbt.getList("DataPregnantEntitiesByHim", Tag.TAG_COMPOUND);
			this.pregnantEntities.clear();
			list.forEach(value -> {
				CompoundTag pair = (CompoundTag) value;
				this.pregnantEntities.add(pair.getUUID("value"));
			});
		}
	}
}
