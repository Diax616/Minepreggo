package dev.dixmk.minepreggo.world.item.checkup;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class PrenatalCheckups {
	private Map<PrenatalCheckup, PrenatalCheckupData> map = new EnumMap<>(PrenatalCheckup.class);
	
	private PrenatalCheckups() {
		for (var key : PrenatalCheckup.values()) {
			map.put(key, null);
		}
	}
	
	private PrenatalCheckups(PrenatalCheckupData regular, PrenatalCheckupData ultrasoundScan, PrenatalCheckupData paternityTest) {
		map.put(PrenatalCheckup.REGULAR, regular);
		map.put(PrenatalCheckup.ULTRASOUND_SCAN, ultrasoundScan);
		map.put(PrenatalCheckup.PATERNITY_TEST, paternityTest);
	}
	
    public PrenatalCheckupData get(PrenatalCheckup key) {
        return map.get(key);
    }

    public void set(PrenatalCheckup key, PrenatalCheckupData value) {
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        map.put(key, value);
    }

    public Iterable<PrenatalCheckup> keys() {
        return map.keySet();
    }
	
    public static PrenatalCheckups from(PrenatalCheckupData regular, PrenatalCheckupData ultrasound, PrenatalCheckupData parternity) {
    	return new PrenatalCheckups(regular, ultrasound, parternity);
    }
    
    public static int count() {
    	return PrenatalCheckup.values().length;
    }
    
    
	public enum PrenatalCheckup {
		REGULAR(20, PregnancyPhase.P1),
		ULTRASOUND_SCAN(25, PregnancyPhase.P2),
		PATERNITY_TEST(30, PregnancyPhase.P2);
		
		public final int defaultEmeraldCount;
		public final PregnancyPhase minRequiredPhase;
		
		PrenatalCheckup(int defaultEmeraldCount, PregnancyPhase minRequiredPhase) {
			this.defaultEmeraldCount = defaultEmeraldCount;
			this.minRequiredPhase = minRequiredPhase;
		}
		
		public static final String NBT_KEY = "DataPrenatalCheckup";
	} 
	
	public static class PrenatalCheckupData {
		public final int emeraldCount;
		final Supplier<ItemStack> outputFactory;
		
		public PrenatalCheckupData(int emeraldCost, Supplier<ItemStack> outputFactory) {
			this.emeraldCount = emeraldCost;
			this.outputFactory = outputFactory;
		}
		
	    public boolean canTrade(ItemStack stack) {
	        return stack.is(Items.EMERALD) && stack.getCount() >= emeraldCount;
	    }

	    public ItemStack createOutput() {
	        return outputFactory.get();
	    }
	}
}