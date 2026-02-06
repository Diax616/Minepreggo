package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.util.EnumSet;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlotMapper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class MonsterCreeperHelper {
	
	static final String SIMPLE_NAME = "Monster Creeper Girl";
	
	private MonsterCreeperHelper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
		
	static AttributeSupplier.Builder createBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 26D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
				.add(Attributes.FOLLOW_RANGE, 35D)
				.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
	public static boolean syncItemOnMouthToCustom(AbstractTamableCreeperGirl creeperGirl) {
		if (creeperGirl.getTypeOfCreature() != Creature.MONSTER) {
			return false;
		}
		
		Inventory inventory = creeperGirl.getInventory();
		InventorySlotMapper slotMapper = inventory.getSlotMapper();
		int customIndex = slotMapper.getSlotIndex(InventorySlot.MOUTH);
		if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX)
			return false;
				
		inventory.getHandler().setStackInSlot(customIndex, creeperGirl.getItemBySlot(EquipmentSlot.MAINHAND));

		return true;
	}
	
	public static boolean syncItemOnMouthToVanillaIfChanged(AbstractTamableCreeperGirl creeperGirl) {
        if (creeperGirl.getTypeOfCreature() != Creature.MONSTER) {
        	return false;
        }
		
		Inventory inventory = creeperGirl.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        int customIndex = slotMapper.getSlotIndex(InventorySlot.MOUTH);
        if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX)
            return false;
		
        ItemStack customMouthItem = inventory.getHandler().getStackInSlot(customIndex);
        ItemStack vanillaMouthItem = creeperGirl.getItemBySlot(EquipmentSlot.MAINHAND);
        
        if (!ItemStack.matches(customMouthItem, vanillaMouthItem)) {
        	if (customMouthItem.isEmpty()) {
        		creeperGirl.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
			}
        	else {
            	creeperGirl.setItemSlot(EquipmentSlot.MAINHAND, customMouthItem);
        	}
    		return true;
        }
    
        return false;
	}
	
	static Inventory createInventory() {
		return new Inventory(EnumSet.of(InventorySlot.HEAD, InventorySlot.MOUTH), 6);
	}
}
