package dev.dixmk.minepreggo.world.pregnancy;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;

public class PrenatalCheckupCostHolder implements INBTSerializable<CompoundTag> {
    private CompoundTag savedData;   
    private Lazy<PrenatalCheckupCost> lazyValue;
    private boolean isInitialized = false;
	private final int min;
	private final int max;
    
    public PrenatalCheckupCostHolder(@Nonnegative int min, @Nonnegative int max) {
        this.savedData = new CompoundTag();
		this.min = min;
		this.max = max;
        this.lazyValue = createLazy();
    }
    
    private Lazy<PrenatalCheckupCost> createLazy() {
        return Lazy.of(() -> {
            isInitialized = true;
            if (savedData.contains(PrenatalCheckupCost.NBT_KEY)) {
                return PrenatalCheckupCost.fromNBT(savedData);
            }
            return new PrenatalCheckupCost(min, max);
        });
    }
    
    public PrenatalCheckupCost getValue() {
        return lazyValue.get();
    }
	
    public boolean isInitialized() {
    	return isInitialized;
    }

	@Override
	public CompoundTag serializeNBT() { 
		CompoundTag tag;
        if (isInitialized) {
        	tag = lazyValue.get().toNBT();
        } else if (savedData.contains(PrenatalCheckupCost.NBT_KEY)) {
        	tag = savedData.copy();
        } else {
			tag = new CompoundTag();
		}
        tag.putBoolean("PrenatalCheckupCostHolderInitialized", isInitialized);
        return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
        this.savedData = nbt.copy();
        this.isInitialized = nbt.getBoolean("PrenatalCheckupCostHolderInitialized");
        this.lazyValue = createLazy();	
	}
	
	public static class PrenatalCheckupCost {
		
		public static final String NBT_KEY = "DataPrenatalCheckupCost";
		
	    private Map<PrenatalCheckup, Integer> map;

	    public PrenatalCheckupCost(@Nonnegative int min, @Nonnegative int max) {	
	    	map = new EnumMap<>(PrenatalCheckup.class);
	    	for (final var prenatalCheckup : PrenatalCheckup.values()) {
	    		map.put(prenatalCheckup, PregnancySystemHelper.RANDOM_SOURCE.nextInt(prenatalCheckup.defaultEmeraldCount - Math.abs(min), prenatalCheckup.defaultEmeraldCount + Math.abs(max)));
	    	}  	
	    }
	    
	    private PrenatalCheckupCost() {}

	    public int getCost(PrenatalCheckup prenatalCheckup) {
	    	return this.map.getOrDefault(prenatalCheckup, 1);
	    }
	    
	    public CompoundTag toNBT() {
	        CompoundTag tag = new CompoundTag();  
			ListTag list = new ListTag();
			map.forEach((key, value) -> {		
				CompoundTag pair = new CompoundTag();
			    pair.putString(PrenatalCheckup.NBT_KEY, key.name());
			    pair.putInt("cost", value);
				list.add(pair);
			});
			tag.put(PrenatalCheckupCost.NBT_KEY, list);
			return tag;
	    }
	    
	    public static PrenatalCheckupCost fromNBT(CompoundTag tag) {
			Map<PrenatalCheckup, Integer> map = new EnumMap<>(PrenatalCheckup.class);
	    	ListTag list = tag.getList(PrenatalCheckupCost.NBT_KEY, Tag.TAG_COMPOUND);	
			for (var t : list) {
		        CompoundTag pair = (CompoundTag) t;
		        PrenatalCheckup key = PrenatalCheckup.valueOf(pair.getString(PrenatalCheckup.NBT_KEY));
		        int value = pair.getInt("cost");
		        map.put(key, value);
		    }
			PrenatalCheckupCost temp = new PrenatalCheckupCost();
			temp.map = map;
			return temp;
	    }
	}
}
