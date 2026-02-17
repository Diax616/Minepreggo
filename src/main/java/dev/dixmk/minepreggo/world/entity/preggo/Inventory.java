package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Set;

import net.minecraftforge.items.ItemStackHandler;

public class Inventory {
	private final InventorySlotMapper slotMapper;
	private final ItemStackHandler handler;
	
	public Inventory(Set<InventorySlot> availableSlots, int extraInventorySlots) throws IllegalArgumentException {
		this.slotMapper = new InventorySlotMapper(availableSlots, extraInventorySlots);
		this.handler = new ItemStackHandler(this.slotMapper.getTotalSlots());
	}

	public InventorySlotMapper getSlotMapper() {
		return slotMapper;
	}

	public ItemStackHandler getHandler() {
		return handler;
	}
}
