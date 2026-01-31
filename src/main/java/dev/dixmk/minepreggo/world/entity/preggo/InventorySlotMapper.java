package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.world.entity.EquipmentSlot;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.annotations.VisibleForTesting;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class InventorySlotMapper {
	
	public static final int DEFAULT_INVALID_SLOT_INDEX = -1;
	
    private final Set<InventorySlot> availableSlots;
    private final Object2IntMap<InventorySlot> slotToIndex;
    private final Int2ObjectMap<InventorySlot> indexToSlot;
    private final int totalSlots;
    private final int extraInventorySlots;
    private final IntIntPair inventorySlotsRange;
    private final IntIntPair extraSlotsRange;
       
    public InventorySlotMapper(Set<InventorySlot> availableSlots, int extraInventorySlots) throws IllegalArgumentException {
        if (extraInventorySlots < 1) {
        	throw new IllegalArgumentException("extraInventorySlots must be at least 1");
		}
    	
    	this.availableSlots = Collections.unmodifiableSet(EnumSet.copyOf(availableSlots));
        this.extraInventorySlots = extraInventorySlots;
        this.slotToIndex = new Object2IntOpenHashMap<>();
        this.slotToIndex.defaultReturnValue(DEFAULT_INVALID_SLOT_INDEX);
        this.indexToSlot = new Int2ObjectOpenHashMap<>(); 
        this.indexToSlot.defaultReturnValue(null);
        
        int mappedCount = 0;
        
        for (InventorySlot slot : availableSlots) {
            slotToIndex.put(slot, mappedCount);
            indexToSlot.put(mappedCount, slot);
            mappedCount++;
        }
   
        this.inventorySlotsRange = IntIntImmutablePair.of(0, mappedCount);
        this.extraSlotsRange = IntIntImmutablePair.of(mappedCount, mappedCount + extraInventorySlots);
          
        this.totalSlots = mappedCount + extraInventorySlots;
    }

    public int getSlotIndex(InventorySlot slot) {
        return slotToIndex.getInt(slot);
    }

    public Optional<InventorySlot> getInventorySlot(int index) {
        return Optional.ofNullable(indexToSlot.get(index));
    }
    
    public boolean isVanillaEquipmentSlot(int index) {
        return getInventorySlot(index)
            .map(slot -> slot.vanilla.isPresent())
            .orElse(false);
    }
    
    public boolean isCustomEquipmentSlot(int index) {
        return getInventorySlot(index)
            .map(slot -> slot.type == InventorySlot.Type.EQUIPMENT && slot.vanilla.isEmpty())
            .orElse(false);
    }

    public boolean isInventorySlot(int index) {
        return getInventorySlot(index)
            .map(slot -> slot.type == InventorySlot.Type.INVENTORY)
            .orElse(false);
    }

    public boolean isExtraSlot(int index) {
        int mappedSlotsCount = slotToIndex.size();
        return index >= mappedSlotsCount && index < totalSlots;
    }

    public Optional<EquipmentSlot> getVanillaEquipmentSlot(int index) {
        return getInventorySlot(index)
            .flatMap(slot -> slot.vanilla);
    }
    
    public boolean hasSlot(InventorySlot slot) {
        return availableSlots.contains(slot);
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getMappedSlotsCount() {
        return slotToIndex.size();
    }
    
    public int getExtraSlots() {
        return extraInventorySlots;
    }

    public Set<InventorySlot> getAvailableSlots() {
        return availableSlots;
    }

    public IntIntPair getInventorySlotsRange() {
		return inventorySlotsRange;
	}
    
    public IntIntPair getExtraSlotsRange() {
    	return extraSlotsRange;
    }
    
    @VisibleForTesting
    public Map<Integer, String> getDebugMapping() {
        Map<Integer, String> debug = new TreeMap<>();
        
        for (Map.Entry<Integer, InventorySlot> entry : indexToSlot.int2ObjectEntrySet()) {
            int index = entry.getKey();
            InventorySlot slot = entry.getValue();
            
            String vanillaInfo = slot.vanilla
                .map(v -> " -> Vanilla: " + v.getName())
                .orElse("");
            
            debug.put(index, String.format("Slot %d: %s (type: %s, id: %d)%s", 
                index, slot.name(), slot.type, slot.id, vanillaInfo));
        }
        
        int mappedCount = slotToIndex.size();
        for (int i = mappedCount; i < totalSlots; i++) {
            debug.put(i, String.format("Slot %d: EXTRA #%d", i, i - mappedCount));
        }
        
        return debug;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        InventorySlotMapper that = (InventorySlotMapper) obj;
        
        return extraInventorySlots == that.extraInventorySlots &&
        		availableSlots.equals(that.availableSlots);
    }

    @Override
    public int hashCode() {
        int result = availableSlots.hashCode();
        result = 31 * result + availableSlots.hashCode();
        return result;
    }
    
    public static Set<InventorySlot> createHumanoidDefault() {
		return EnumSet.of(
				InventorySlot.HEAD,
				InventorySlot.CHEST,
				InventorySlot.LEGS,
				InventorySlot.FEET,
				InventorySlot.MAINHAND,
				InventorySlot.OFFHAND,
				InventorySlot.FOOD
			);
    }
    
    public static boolean isHumanoidDefault(InventorySlotMapper info) {
		return info.getAvailableSlots().equals(EnumSet.of(
				InventorySlot.HEAD,
				InventorySlot.CHEST,
				InventorySlot.LEGS,
				InventorySlot.FEET,
				InventorySlot.MAINHAND,
				InventorySlot.OFFHAND,
				InventorySlot.FOOD
			));
	}
}