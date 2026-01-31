package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Optional;

import net.minecraft.world.entity.EquipmentSlot;

public enum InventorySlot {	
	MAINHAND(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.MAINHAND), 0),
	OFFHAND(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.OFFHAND), 1),
	HEAD(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.HEAD), 2),
	CHEST(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.CHEST), 3),
	LEGS(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.LEGS), 4),
	FEET(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.FEET), 5),
	FOOD(InventorySlot.Type.INVENTORY, Optional.empty(), 6),
	BOTH_HANDS(InventorySlot.Type.EQUIPMENT, Optional.empty(), 7),
	MOUTH(InventorySlot.Type.EQUIPMENT, Optional.empty(), 8);
	
	public final Type type;
	public final Optional<EquipmentSlot> vanilla;
	public final int id;
	   
	InventorySlot(InventorySlot.Type type, Optional<EquipmentSlot> vanilla, int id) {
		this.type = type;
		this.vanilla = vanilla;
		this.id = id;
	}
	
	public enum Type {
		INVENTORY,
		EQUIPMENT;
	}
}
