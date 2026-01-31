package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class InventoryHelper {

    private InventoryHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    /**
     * Transfers items between two inventories respecting the InventorySlot mapping
     * @return true if at least one item was successfully transferred
     */
    public static boolean transferAll(Inventory source, Inventory destination) {
		boolean anySuccess = false;
		InventorySlotMapper sourceMapper = source.getSlotMapper();
		InventorySlotMapper destMapper = destination.getSlotMapper();
		ItemStackHandler sourceHandler = source.getHandler();
		ItemStackHandler destHandler = destination.getHandler();
		
		for (InventorySlot slot : sourceMapper.getAvailableSlots()) {
			if (destMapper.hasSlot(slot)) {
				destHandler.setStackInSlot(destMapper.getSlotIndex(slot), sourceHandler.getStackInSlot(sourceMapper.getSlotIndex(slot)).copy());
				sourceHandler.setStackInSlot(sourceMapper.getSlotIndex(slot), ItemStack.EMPTY);
				anySuccess = true;
			}
		}

        int sourceExtraCount = sourceMapper.getExtraSlots();
        int destExtraCount = destMapper.getExtraSlots();
        int sourceBaseIndex = sourceMapper.getMappedSlotsCount();
        int destBaseIndex = destMapper.getMappedSlotsCount();
        
        int slotsToTransfer = Math.min(sourceExtraCount, destExtraCount);
        
        for (int i = 0; i < slotsToTransfer; i++) {
            int sourceIndex = sourceBaseIndex + i;
            int destIndex = destBaseIndex + i;
            destHandler.setStackInSlot(destIndex, sourceHandler.getStackInSlot(sourceIndex).copy());
			sourceHandler.setStackInSlot(sourceIndex, ItemStack.EMPTY);
            anySuccess = true;
        }

		return anySuccess;
	}
    
    /**
     * Copies items between inventories (does not move them, duplicates them)
     * @return true if at least one item was successfully copied
     */
    public static boolean copyAll(Inventory source, Inventory destination) {
        boolean anySuccess = false;
		InventorySlotMapper sourceMapper = source.getSlotMapper();
		InventorySlotMapper destMapper = destination.getSlotMapper();
		ItemStackHandler sourceHandler = source.getHandler();
		ItemStackHandler destHandler = destination.getHandler();
		
        for (InventorySlot slot : sourceMapper.getAvailableSlots()) {
            if (destMapper.hasSlot(slot)) {
                anySuccess = true;
                destHandler.setStackInSlot(destMapper.getSlotIndex(slot), sourceHandler.getStackInSlot(sourceMapper.getSlotIndex(slot)).copy());   
            }          
        }
        
        int sourceExtraCount = sourceMapper.getExtraSlots();
        int destExtraCount = destMapper.getExtraSlots();
        int sourceBaseIndex = sourceMapper.getMappedSlotsCount();
        int destBaseIndex = destMapper.getMappedSlotsCount();
        
        int slotsToCopy = Math.min(sourceExtraCount, destExtraCount);
        
        for (int i = 0; i < slotsToCopy; i++) {
            int sourceIndex = sourceBaseIndex + i;
            int destIndex = destBaseIndex + i;
            destHandler.setStackInSlot(destIndex, sourceHandler.getStackInSlot(sourceIndex).copy());
            anySuccess = true;
        }

        return anySuccess;
    }

    /**
     * Completely empties an inventory
     * @return List of extracted items
     */
    public static List<ItemStack> clearInventory(Inventory inventory, boolean includeExtras) {
        List<ItemStack> items = new ArrayList<>();
        
        for (InventorySlot slot : inventory.getSlotMapper().getAvailableSlots()) {
            int index = inventory.getSlotMapper().getSlotIndex(slot);
            ItemStack stack = inventory.getHandler().extractItem(index, Integer.MAX_VALUE, false);
            if (!stack.isEmpty()) {
                items.add(stack);
            }
        }
        
        if (includeExtras) {
            int baseIndex = inventory.getSlotMapper().getMappedSlotsCount();
            int extraCount = inventory.getSlotMapper().getExtraSlots();
            
            for (int i = 0; i < extraCount; i++) {
                ItemStack stack = inventory.getHandler().extractItem(baseIndex + i, Integer.MAX_VALUE, false);
                if (!stack.isEmpty()) {
                    items.add(stack);
                }
            }
        }
        
        return items;
    }
    
    /**
     * Checks if an inventory is empty
     * @return true if all slots are empty
     */
    public static boolean isEmpty(Inventory inventory, boolean checkExtras) {
        for (InventorySlot slot : inventory.getSlotMapper().getAvailableSlots()) {
            int index = inventory.getSlotMapper().getSlotIndex(slot);
            if (!inventory.getHandler().getStackInSlot(index).isEmpty()) {
                return false;
            }
        }
        
        if (checkExtras) {
            int baseIndex = inventory.getSlotMapper().getMappedSlotsCount();
            int extraCount = inventory.getSlotMapper().getExtraSlots();
            
            for (int i = 0; i < extraCount; i++) {
                if (!inventory.getHandler().getStackInSlot(baseIndex + i).isEmpty()) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Synchronizes the entire custom inventory with the vanilla one
     * @param inventory Custom inventory
     * @param preggoMob Mob with vanilla equipment
     * @return true if at least one slot was successfully synchronized
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean syncToVanilla(E preggoMob) {
        boolean anySuccess = false;
        Inventory inventory = preggoMob.getInventory();
        for (InventorySlot slot : inventory.getSlotMapper().getAvailableSlots()) {
            if (slot.vanilla.isPresent() && syncSlotToVanilla(preggoMob, slot)) {
            	anySuccess = true;
            } 
        }
        
        return anySuccess;
    }
    
    /**
     * Synchronizes the entire vanilla inventory with the custom one
     * @param preggoMob Mob with vanilla equipment
     * @param inventory Custom inventory
     * @return true if at least one slot was successfully synchronized
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean syncFromVanilla(E preggoMob) {
        boolean anySuccess = false;
        Inventory inventory = preggoMob.getInventory();
        for (InventorySlot slot : inventory.getSlotMapper().getAvailableSlots()) {
            if (slot.vanilla.isPresent() && syncSlotFromVanilla(preggoMob, slot)) {
                    anySuccess = true;
            }   
        }
        
        return anySuccess;
    }
    
    /**
     * Synchronizes a specific slot from custom to vanilla
     * @return true if the synchronization was successful
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean syncSlotToVanilla(E preggoMob, InventorySlot slot) {
        if (slot.vanilla.isEmpty()) {
            return false;
        }
        Inventory inventory = preggoMob.getInventory();
        int customIndex = inventory.getSlotMapper().getSlotIndex(slot);
        if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
            return false;
        }
        
        EquipmentSlot vanillaSlot = slot.vanilla.get();
        
        ItemStack customStack = inventory.getHandler().getStackInSlot(customIndex);
        
        preggoMob.setItemSlot(vanillaSlot, customStack.copy());
        
        return true;
    }
    
    /**
     * Synchronizes a specific slot from vanilla to custom
     * @return true if the synchronization was successful
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean syncSlotFromVanilla(E preggoMob, InventorySlot slot) {
        if (slot.vanilla.isEmpty()) {
            return false;
        }
        
        Inventory inventory = preggoMob.getInventory();
        
        int customIndex = inventory.getSlotMapper().getSlotIndex(slot);
        if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
            return false;
        }
        
        EquipmentSlot vanillaSlot = slot.vanilla.get();
        
        ItemStack vanillaStack = preggoMob.getItemBySlot(vanillaSlot);
        
        inventory.getHandler().setStackInSlot(customIndex, vanillaStack);
        
        return true;
    }
    
    /**
     * Synchronizes only if there are differences (custom -> vanilla)
     * @return true if there were changes and they were synchronized
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean syncToVanillaIfChanged(E preggoMob) {
        boolean hadChanges = false;
        
        Inventory inventory = preggoMob.getInventory();
        for (InventorySlot slot : inventory.getSlotMapper().getAvailableSlots()) {
            
            // Get the index in the custom handler
            int customIndex = inventory.getSlotMapper().getSlotIndex(slot);
            if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX || slot.vanilla.isEmpty())
                continue;
            
            EquipmentSlot vanillaSlot = slot.vanilla.get();
            
            ItemStack customStack = inventory.getHandler().getStackInSlot(customIndex);
            ItemStack vanillaStack = preggoMob.getItemBySlot(vanillaSlot);
            
            if (!ItemStack.matches(customStack, vanillaStack)) {
                preggoMob.setItemSlot(vanillaSlot, customStack);
                hadChanges = true;
            }
        }
        
        return hadChanges;
    }
    
    /**
     * Synchronizes only if there are differences (vanilla -> custom)
     * @return true if there were changes and they were synchronized
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean syncFromVanillaIfChanged(E preggoMob) {
        boolean hadChanges = false;
        Inventory inventory = preggoMob.getInventory();
        for (InventorySlot slot : inventory.getSlotMapper().getAvailableSlots()) {    
            int customIndex = inventory.getSlotMapper().getSlotIndex(slot);
            if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX || slot.vanilla.isEmpty()) continue;
            
            EquipmentSlot vanillaSlot = slot.vanilla.get();
            
            ItemStack vanillaStack = preggoMob.getItemBySlot(vanillaSlot);
            ItemStack customStack = inventory.getHandler().getStackInSlot(customIndex);
            
            if (!ItemStack.matches(vanillaStack, customStack)) {
                inventory.getHandler().setStackInSlot(customIndex, vanillaStack);
                hadChanges = true;
            }
        }
        
        return hadChanges;
    }
    
    /**
     * Bidirectional synchronization: detects which one changed and propagates it
     * Prioritizes vanilla over custom in case of conflict
     * @return true if there were changes and they were synchronized
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean syncBidirectional(E preggoMob, ItemStack[] lastKnownState) {
        boolean hadChanges = false;
        Inventory inventory = preggoMob.getInventory();
        for (InventorySlot slot : inventory.getSlotMapper().getAvailableSlots()) {
            // Get the index in the custom handler
            int customIndex = inventory.getSlotMapper().getSlotIndex(slot);
            if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX || slot.vanilla.isEmpty()) continue;
            
            // Get the vanilla EquipmentSlot
            EquipmentSlot vanillaSlot = slot.vanilla.get();
            
            // Get current stacks
            ItemStack vanillaStack = preggoMob.getItemBySlot(vanillaSlot);
            ItemStack customStack = inventory.getHandler().getStackInSlot(customIndex);
            ItemStack lastKnown = lastKnownState[customIndex];
            
            // If both are equal to the last known, nothing changed
            if (ItemStack.matches(vanillaStack, lastKnown) && 
                ItemStack.matches(customStack, lastKnown)) {
                continue;
            }
            
            // If custom changed but vanilla did not
            if (!ItemStack.matches(customStack, lastKnown) && 
                ItemStack.matches(vanillaStack, lastKnown)) {
                preggoMob.setItemSlot(vanillaSlot, customStack);
                lastKnownState[customIndex] = customStack;
                hadChanges = true;
                continue;
            }
            
            // If vanilla changed but custom did not
            if (!ItemStack.matches(vanillaStack, lastKnown) && 
                ItemStack.matches(customStack, lastKnown)) {
                inventory.getHandler().setStackInSlot(customIndex, vanillaStack);
                lastKnownState[customIndex] = vanillaStack;
                hadChanges = true;
                continue;
            }
            
            // If both changed, prioritize vanilla
            inventory.getHandler().setStackInSlot(customIndex, vanillaStack);
            lastKnownState[customIndex] = vanillaStack;
            hadChanges = true;
        }
        
        return hadChanges;
    }
    
    /**
     * Forces synchronization of a specific EquipmentSlot to custom
     * @return true if successfully synchronized
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean forceVanillaToCustom(E preggoMob, EquipmentSlot vanillaSlot) {
        Inventory inventory = preggoMob.getInventory();
        Optional<InventorySlot> inventorySlot = findInventorySlotByVanilla(inventory.getSlotMapper(), vanillaSlot);
        if (inventorySlot.isEmpty()) {
            return false;
        }
        
        return syncSlotFromVanilla(preggoMob, inventorySlot.get());
    }
    
    /**
     * Forces synchronization of a specific InventorySlot to vanilla
     * @return true if successfully synchronized
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean forceCustomToVanilla(E preggoMob, InventorySlot inventorySlot) {
        if (inventorySlot.vanilla.isEmpty()) {
            return false;
        }
        
        return syncSlotToVanilla(preggoMob, inventorySlot);
    }
    
    /**
     * Checks if there are differences between custom and vanilla
     * @return true if there is at least one difference
     */
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean hasDesync(E preggoMob) {
        Inventory inventory = preggoMob.getInventory();
        for (InventorySlot slot : inventory.getSlotMapper().getAvailableSlots()) {
            int customIndex = inventory.getSlotMapper().getSlotIndex(slot);
            
            if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX || slot.vanilla.isEmpty())
                continue;
            
            EquipmentSlot vanillaSlot = slot.vanilla.get();
            ItemStack customStack = inventory.getHandler().getStackInSlot(customIndex);
            ItemStack vanillaStack = preggoMob.getItemBySlot(vanillaSlot);
            
            if (!ItemStack.matches(customStack, vanillaStack)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Creates a snapshot of the current state of the custom inventory
     */
    public static ItemStack[] createSnapshot(Inventory inventory) {
        int totalSlots = inventory.getSlotMapper().getTotalSlots();
        ItemStack[] snapshot = new ItemStack[totalSlots];
        
        for (int i = 0; i < totalSlots; i++) {
            snapshot[i] = inventory.getHandler().getStackInSlot(i).copy();
        }
        
        return snapshot;
    }
    
    /**
     * Finds an InventorySlot by its corresponding vanilla EquipmentSlot
     */
    private static Optional<InventorySlot> findInventorySlotByVanilla(InventorySlotMapper mapper, EquipmentSlot vanillaSlot) {
        for (InventorySlot slot : mapper.getAvailableSlots()) {
            if (slot.vanilla.isPresent() && slot.vanilla.get() == vanillaSlot) {
                return Optional.of(slot);
            }
        }
        return Optional.empty();
    }
}
