package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.LivingEntity;

public class SyncedSetPregnancySymptom extends SetPregnancySymptom {
	private final LivingEntity entity;
	private final EntityDataAccessor<Byte> dataAccessor;
	private final Set<PregnancySymptom> cache = EnumSet.noneOf(PregnancySymptom.class);
	
	public SyncedSetPregnancySymptom(LivingEntity entity, EntityDataAccessor<Byte> dataAccessor) {
		super();
		this.entity = entity;
		this.dataAccessor = dataAccessor;
	}

	@Override
	public boolean add(PregnancySymptom symptom) {
		boolean result = super.add(symptom);
		if (result) {
			sync();
		}
		return result;
	}

	@Override
	public boolean remove(Object symptom) {
		boolean result = super.remove(symptom);
		if (result) {
			sync();
		}
		return result;
	}
	
	@Override
	public void clear() {
	    super.clear();
	    sync();
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);
		sync();
	}
	
	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public Iterator<PregnancySymptom> iterator() {
		return cache.iterator();
	}

	@Override
	public boolean contains(Object symptom) {
		return cache.contains(symptom);
	}
	
	@Override
	public boolean containsAll(Collection<?> symptoms) {
		return cache.containsAll(symptoms);
	}
	
	@Override
	public boolean isEmpty() {
		return cache.isEmpty();
	}
	
	@Override
	public Set<PregnancySymptom> getSymptoms() {
		return Sets.immutableEnumSet(cache);
	}
	
	private void sync() {
	    entity.getEntityData().set(dataAccessor, PregnancySymptom.toBitMask(this));
	    cache.clear();
	    cache.addAll(PregnancySymptom.fromBitMask(entity.getEntityData().get(dataAccessor)));
	}
}
