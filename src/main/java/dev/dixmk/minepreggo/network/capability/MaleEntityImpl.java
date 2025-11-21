package dev.dixmk.minepreggo.network.capability;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;

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
	public void serializeNBT(@NonNull Tag tag) {
		super.serializeNBT(tag);
		CompoundTag nbt = (CompoundTag) tag;
		ListTag list = new ListTag();
		this.pregnantEntities.forEach(value -> {		
			CompoundTag pair = new CompoundTag();
		    pair.putUUID("value", value);
			list.add(pair);
		});
		nbt.put("DataPregnantEntitiesByHim", list);
	}
	
	@Override
	public void deserializeNBT(@NonNull Tag tag) {
		super.deserializeNBT(tag);
		CompoundTag nbt = (CompoundTag) tag;	
		ListTag list = nbt.getList("DataPregnantEntitiesByHim", Tag.TAG_COMPOUND);		
	    for (var t : list) {
	        CompoundTag pair = (CompoundTag) t;
	        UUID value = pair.getUUID("value");
	        pregnantEntities.add(value);	        
	    }    
	}
}
