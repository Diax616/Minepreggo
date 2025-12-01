package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class MapPregnancyPhase {
	protected static final ImmutableMap<PregnancyPhase, List<ImmutablePair<PregnancyPhase, Float>>> PREGNANCY_PHASES_WEIGHTS = ImmutableMap.of(
			PregnancyPhase.P4, List.of(
					ImmutablePair.of(PregnancyPhase.P0, 0.05F),
					ImmutablePair.of(PregnancyPhase.P1, 0.2F),
					ImmutablePair.of(PregnancyPhase.P2, 0.2F),
					ImmutablePair.of(PregnancyPhase.P3, 0.25F),
					ImmutablePair.of(PregnancyPhase.P4, 0.3F)),		
			PregnancyPhase.P5, List.of(
					ImmutablePair.of(PregnancyPhase.P0, 0.05F),
					ImmutablePair.of(PregnancyPhase.P1, 0.1F),
					ImmutablePair.of(PregnancyPhase.P2, 0.2F),
					ImmutablePair.of(PregnancyPhase.P3, 0.2F),
					ImmutablePair.of(PregnancyPhase.P4, 0.2F),
					ImmutablePair.of(PregnancyPhase.P5, 0.25F)),	
			PregnancyPhase.P6, List.of(
					ImmutablePair.of(PregnancyPhase.P0, 0.05F),
					ImmutablePair.of(PregnancyPhase.P1, 0.1F),
					ImmutablePair.of(PregnancyPhase.P2, 0.1F),
					ImmutablePair.of(PregnancyPhase.P3, 0.1F),
					ImmutablePair.of(PregnancyPhase.P4, 0.2F),
					ImmutablePair.of(PregnancyPhase.P5, 0.2F),
					ImmutablePair.of(PregnancyPhase.P6, 0.25F)),	
			PregnancyPhase.P7, List.of(
					ImmutablePair.of(PregnancyPhase.P0, 0.05F),
					ImmutablePair.of(PregnancyPhase.P1, 0.1F),
					ImmutablePair.of(PregnancyPhase.P2, 0.1F),
					ImmutablePair.of(PregnancyPhase.P3, 0.1F),
					ImmutablePair.of(PregnancyPhase.P4, 0.15F),
					ImmutablePair.of(PregnancyPhase.P5, 0.15F),
					ImmutablePair.of(PregnancyPhase.P6, 0.15F),
					ImmutablePair.of(PregnancyPhase.P7, 0.2F)),
			PregnancyPhase.P8, List.of(
					ImmutablePair.of(PregnancyPhase.P0, 0.05F),
					ImmutablePair.of(PregnancyPhase.P1, 0.1F),
					ImmutablePair.of(PregnancyPhase.P2, 0.1F),
					ImmutablePair.of(PregnancyPhase.P3, 0.1F),
					ImmutablePair.of(PregnancyPhase.P4, 0.1F),
					ImmutablePair.of(PregnancyPhase.P5, 0.1F),
					ImmutablePair.of(PregnancyPhase.P6, 0.15F),
					ImmutablePair.of(PregnancyPhase.P7, 0.15F),
					ImmutablePair.of(PregnancyPhase.P8, 0.15F))
			);
	
	private static final String NBT_KEY = "DataMapPregnancyPhase";
	
	private	Map<PregnancyPhase, Integer> daysByPregnancyPhase;
	
    public MapPregnancyPhase(@Nonnegative int totalDays, PregnancyPhase lastPregnancyPhase) {	
		PregnancyPhase last = lastPregnancyPhase;
		
		if (last.ordinal() < 4) {
			last = PregnancyPhase.P4;
		}
    	
		final var weights = PREGNANCY_PHASES_WEIGHTS.get(last);
		
		this.daysByPregnancyPhase = new EnumMap<>(PregnancyPhase.class);
		
		int total = 0;
		for (final var pair : weights) {		
			int floor = Math.round(totalDays * pair.getRight());		
			daysByPregnancyPhase.put(pair.getLeft(), floor);
			total += floor;
		}
		final int rest = totalDays - total;
				
		if (rest > 0) {
			daysByPregnancyPhase.computeIfPresent(last, (key, value) -> value + rest);
		}
    }
    
    private MapPregnancyPhase() {}
	
    public Set<PregnancyPhase> getPregnancyPhases() {
		return daysByPregnancyPhase.keySet();
	}
    
    public Collection<Integer> getDaysValues() {
    	return daysByPregnancyPhase.values();
    }
    
    public int getDaysByPregnancyPhase(PregnancyPhase phase) {
    	return daysByPregnancyPhase.getOrDefault(phase, 0);
    }
    
    public boolean containsPregnancyPhase(PregnancyPhase phase) {
    	return daysByPregnancyPhase.containsKey(phase);
    }
    
    public boolean isEmpty() {
    	return daysByPregnancyPhase.isEmpty();
    }
    
    public boolean addPregnancyPhase(int days) {
		for (PregnancyPhase phase : PregnancyPhase.values()) {
			if (daysByPregnancyPhase.putIfAbsent(phase, days) != null) {
				return true;
			}
		}
		return false;
	}
    
    public boolean modifyDaysByPregnancyPhase(PregnancyPhase phase, @Nonnegative int days) {
		return daysByPregnancyPhase.computeIfPresent(phase, (key, value) -> days) != null;
	}
    
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		daysByPregnancyPhase.forEach((key, value) -> 
			sb.append(key.name()).append(": ").append(value).append(" days; ")
		);
		return sb.toString();
	}
    
    
    public CompoundTag toNBT() {
    	CompoundTag nbt = new CompoundTag();
		ListTag list = new ListTag();
		daysByPregnancyPhase.forEach((key, value) -> {		
			CompoundTag pair = new CompoundTag();
		    pair.putString("pregnancyPhase", key.name());
		    pair.putInt("days", value);
			list.add(pair);
		});
		nbt.put(NBT_KEY, list);
		return nbt;
    }
    
    @CheckForNull
    public static MapPregnancyPhase fromNBT(CompoundTag nbt) {
    	if (nbt.contains(NBT_KEY, Tag.TAG_LIST)) {
        	ListTag list = nbt.getList(NBT_KEY, Tag.TAG_COMPOUND);	        	
    		Map<PregnancyPhase, Integer> map = new EnumMap<>(PregnancyPhase.class);
    	    for (var t : list) {
    	        CompoundTag pair = (CompoundTag) t;
    	        PregnancyPhase key = PregnancyPhase.valueOf(pair.getString("pregnancyPhase"));
    	        int value = pair.getInt("days");
    	        map.put(key, value);
    	    }
    	    MapPregnancyPhase temp = new MapPregnancyPhase();
    	    temp.daysByPregnancyPhase = map;
    		return temp;
    	}	
    	else {
    		MinepreggoMod.LOGGER.error("{} is not present in nbt", NBT_KEY);
    	}
    	return null;
    }
}
