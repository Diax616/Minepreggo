package dev.dixmk.minepreggo.world.pregnancy;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class PregnancySymptomSet extends AbstractSet<PregnancySymptom> implements INBTSerializable<CompoundTag>{
	private final Set<PregnancySymptom> internal = EnumSet.noneOf(PregnancySymptom.class);

	public PregnancySymptomSet() {}
	
	public PregnancySymptomSet(CompoundTag nbt) throws IllegalArgumentException {
		deserializeNBT(nbt);
	}
		
	@Override
	public boolean add(PregnancySymptom symptom) {
		return internal.add(symptom);
	}
	
	@Override
	public boolean remove(Object symptom) {
		return internal.remove(symptom);
	}
	
	public boolean remove(PregnancySymptom symptom) {
		return remove((Object) symptom);
	}
	
	@Override
	public int size() {
		return internal.size();
	}

	@Override
	public Iterator<PregnancySymptom> iterator() {
		return internal.iterator();
	}

	@Override
	public boolean contains(Object symptom) {
		return internal.contains(symptom);
	}
	
	public boolean contains(PregnancySymptom symptom) {
		return contains((Object) symptom);
	}
	
	@Override
	public boolean containsAll(Collection<?> symptoms) {
		return internal.containsAll(symptoms);
	}

	@Override
	public boolean isEmpty() {
		return internal.isEmpty();
	}
	
	@Override
	public void clear() {
		internal.clear();
	}
	
	public void from(byte bitmask) {
		internal.clear();
		internal.addAll(PregnancySymptom.fromBitMask(bitmask));
	}
	
	public void from(Set<PregnancySymptom> symptoms) {
		internal.clear();
		internal.addAll(symptoms);
	}
	
	/**
	 * Returns an immutable copy of the symptoms in this set.
	 */
	public Set<PregnancySymptom> getSymptoms() {
		return Sets.immutableEnumSet(internal);
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		CompoundTag symptomTags = new CompoundTag();
		symptomTags.putByte("bitmask", PregnancySymptom.toBitMask(internal));
		nbt.put("symptoms", symptomTags);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) throws IllegalArgumentException {
		if (nbt.contains("symptoms", Tag.TAG_COMPOUND)) {
			CompoundTag symptomTags = nbt.getCompound("symptoms");
			byte bitmask = symptomTags.getByte("bitmask");
			internal.clear();
			internal.addAll(PregnancySymptom.fromBitMask(bitmask));
		}
		else {
			throw new IllegalArgumentException("NBT does not contain 'symptoms' compound tag");
		}
	}
}
