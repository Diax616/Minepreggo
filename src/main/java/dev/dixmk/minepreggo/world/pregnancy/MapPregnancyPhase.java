package dev.dixmk.minepreggo.world.pregnancy;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.RandomSource;

public class MapPregnancyPhase {
	private static final ImmutableMap<PregnancyPhase, List<ImmutablePair<PregnancyPhase, Float>>> PREGNANCY_PHASES_WEIGHTS = ImmutableMap.of(
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
	
	private static final int DEFAULT_INT_VALUE = -145328;
	
	private static final String NBT_KEY = "DataMapPregnancyPhase";
	private static final String NBT_KEY_ORIGINAL = "OriginalDataMapPregnancyPhase";
	
	private Object2IntMap<PregnancyPhase> daysByPregnancyPhase;
	private final ImmutableMap<PregnancyPhase, Integer> originalDaysByPregnancyPhase;
	
    public MapPregnancyPhase(@Nonnegative int totalDays, PregnancyPhase lastPregnancyPhase) {	
		PregnancyPhase last = lastPregnancyPhase;
		
		if (last.compareTo(PregnancyPhase.P4) < 0) {
			last = PregnancyPhase.P4;
		}
    	
		final var weights = PREGNANCY_PHASES_WEIGHTS.get(last);
		
		this.daysByPregnancyPhase = new Object2IntOpenHashMap<>();
		this.daysByPregnancyPhase.defaultReturnValue(DEFAULT_INT_VALUE);
		
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
		
		originalDaysByPregnancyPhase = ImmutableMap.copyOf(daysByPregnancyPhase);
    }
	
    private MapPregnancyPhase(@Nonnull Object2IntMap<PregnancyPhase> daysByPregnancyPhase, @Nonnull ImmutableMap<PregnancyPhase, Integer> originalDaysByPregnancyPhase) {
		this.daysByPregnancyPhase = daysByPregnancyPhase;
		this.originalDaysByPregnancyPhase = originalDaysByPregnancyPhase;
		this.daysByPregnancyPhase.defaultReturnValue(DEFAULT_INT_VALUE);
    }
    
    public Set<PregnancyPhase> getPregnancyPhases() {
		return daysByPregnancyPhase.keySet();
	}
    
    public Collection<Integer> getDaysValues() {
    	return daysByPregnancyPhase.values();
    }
    
    public int getDaysByPregnancyPhase(PregnancyPhase phase) {
    	var days = daysByPregnancyPhase.getInt(phase);
    	return days != DEFAULT_INT_VALUE ? days : 0;
    }
    
    public boolean containsPregnancyPhase(PregnancyPhase phase) {
    	return daysByPregnancyPhase.containsKey(phase);
    }
    
    public boolean isEmpty() {
    	return daysByPregnancyPhase.isEmpty();
    }
    
    public boolean addPregnancyPhase(int days) {
		for (PregnancyPhase phase : PregnancyPhase.values()) {
			if (daysByPregnancyPhase.putIfAbsent(phase, days) == DEFAULT_INT_VALUE) {
				return true;
			}
		}
		return false;
	}
    
    public boolean modifyDaysByPregnancyPhase(PregnancyPhase phase, @Nonnegative int days) {
		return daysByPregnancyPhase.computeIntIfPresent(phase, (key, value) -> days) != DEFAULT_INT_VALUE;
	}

    public void resetToOriginal() {
		daysByPregnancyPhase = new Object2IntOpenHashMap<>(originalDaysByPregnancyPhase);
	}
    
    public @Nonnull ImmutableMap<PregnancyPhase, Integer> getOriginalMap() {
		return originalDaysByPregnancyPhase;
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
		ListTag current = new ListTag();
		ListTag original = new ListTag();
		
		daysByPregnancyPhase.forEach((key, value) -> {		
			CompoundTag pair = new CompoundTag();
		    pair.putString("pregnancyPhase", key.name());
		    pair.putInt("days", value);
		    current.add(pair);
		});
		
		originalDaysByPregnancyPhase.forEach((key, value) -> {		
			CompoundTag pair = new CompoundTag();
		    pair.putString("originalPregnancyPhase", key.name());
		    pair.putInt("originalDays", value);
		    original.add(pair);
		});
			
		nbt.put(NBT_KEY, current);
		nbt.put(NBT_KEY_ORIGINAL, original);
		
		return nbt;
    }
    
    @CheckForNull
    public static MapPregnancyPhase fromNBT(CompoundTag nbt) {	
    	if (nbt.contains(NBT_KEY, Tag.TAG_LIST) && nbt.contains(NBT_KEY_ORIGINAL, Tag.TAG_LIST)) {	
        	ListTag current = nbt.getList(NBT_KEY, Tag.TAG_COMPOUND);	
        	ListTag original = nbt.getList(NBT_KEY_ORIGINAL, Tag.TAG_COMPOUND);
        	Object2IntMap<PregnancyPhase> map = new Object2IntOpenHashMap<>();
        	Object2IntMap<PregnancyPhase> originalMap = new Object2IntOpenHashMap<>();
    	    for (var t : current) {
    	        CompoundTag pair = (CompoundTag) t;
    	        PregnancyPhase key = PregnancyPhase.valueOf(pair.getString("pregnancyPhase"));
    	        int value = pair.getInt("days");
    	        map.put(key, value);
    	    }  
    	    for (var t : original) {
		        CompoundTag pair = (CompoundTag) t;
		        PregnancyPhase key = PregnancyPhase.valueOf(pair.getString("originalPregnancyPhase"));
		        int value = pair.getInt("originalDays");
		        originalMap.put(key, value);
		    }
    	    return new MapPregnancyPhase(map, ImmutableMap.copyOf(originalMap));
    	}	
    	else {
    		MinepreggoMod.LOGGER.error("{} is not present in nbt", NBT_KEY);
    	}
    	return null;
    }
    
    public static int calculateRandomTotalDaysElapsed(PregnancyPhase currentPregnancyStage, PregnancyPhase maxPregnancyStage, RandomSource random) {
        PregnancyPhase last = maxPregnancyStage;
        if (last.ordinal() < 4) {
            last = PregnancyPhase.P4;
        }
        var weights = PREGNANCY_PHASES_WEIGHTS.get(last);
        int totalDays = PregnancySystemHelper.DEFAULT_TOTAL_PREGNANCY_DAYS;

        Object2IntMap<PregnancyPhase> daysByPhase = new Object2IntOpenHashMap<>();
        int total = 0;
        for (var pair : weights) {
            int days = Math.round(totalDays * pair.getRight());
            daysByPhase.put(pair.getLeft(), days);
            total += days;
        }
        int rest = totalDays - total;
        if (rest > 0) {
            daysByPhase.computeIfPresent(last, (key, value) -> value + rest);
        }
        
        int elapsed = 0;
        for (PregnancyPhase phase : PregnancyPhase.values()) {
            if (phase.ordinal() < currentPregnancyStage.ordinal()) {
                elapsed += daysByPhase.getOrDefault(phase, 0);
            } else if (phase == currentPregnancyStage) {
                int phaseDays = daysByPhase.getOrDefault(phase, 0);
                elapsed += random.nextInt(phaseDays + 1);
                break;
            }
        }
        return elapsed;
    }
}