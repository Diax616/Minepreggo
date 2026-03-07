package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Optional;

import net.minecraft.world.entity.EquipmentSlot;

public enum InventorySlot {	
	MAINHAND(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.MAINHAND)),
	OFFHAND(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.OFFHAND)),
	HEAD(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.HEAD)),
	CHEST(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.CHEST)),
	LEGS(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.LEGS)),
	FEET(InventorySlot.Type.EQUIPMENT, Optional.of(EquipmentSlot.FEET)),
	FOOD(InventorySlot.Type.INVENTORY, Optional.empty()),
	BOTH_HANDS(InventorySlot.Type.EQUIPMENT, Optional.empty()),
	MOUTH(InventorySlot.Type.EQUIPMENT, Optional.empty());
	
	public final Type type;
	public final Optional<EquipmentSlot> vanilla;
	   
	InventorySlot(InventorySlot.Type type, Optional<EquipmentSlot> vanilla) {
		this.type = type;
		this.vanilla = vanilla;
	}
	
	public enum Type {
		INVENTORY,
		EQUIPMENT;
	}
}
