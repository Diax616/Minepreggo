package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.util.EnumSet;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.ai.goal.EatGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlotMapper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.EntityType;
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
	
	static AttributeSupplier.Builder createTamableAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 32D)
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
	
    public static boolean replaceItemstackInMouth(AbstractTamableCreeperGirl creeperGirl, ItemStack itemStack) {
    	if (creeperGirl.getTypeOfCreature() != Creature.MONSTER) {
			return false;
		}
    	  	
    	Inventory inventory = creeperGirl.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        
        if (!slotMapper.hasSlot(InventorySlot.MOUTH)) {
            return false;
        }
        
        int slotIndex = slotMapper.getSlotIndex(InventorySlot.MOUTH);
        
        if (slotIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
            return false;
        }
        
        ItemStack currentStack = inventory.getHandler().getStackInSlot(slotIndex);
        
        if (!currentStack.isEmpty()) {
        	PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(creeperGirl, InventorySlot.MOUTH);
        	creeperGirl.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
        
        ItemStack copy = itemStack.copy();
        inventory.getHandler().setStackInSlot(slotIndex, copy);         
        creeperGirl.setItemSlot(EquipmentSlot.MAINHAND, copy);
	
        return true;      
    }
	
	static Inventory createInventory() {
		return new Inventory(EnumSet.of(InventorySlot.HEAD, InventorySlot.MOUTH), 6);
	}
	
	public static EntityType<? extends AbstractTamablePregnantMonsterCreeperGirl> getEntityType(PregnancyPhase phase) {	
		return switch (phase) {
			case P0 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P0.get();
			case P1 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P1.get();
			case P2 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P2.get();
			case P3 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P3.get();
			case P4 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P4.get();
			case P5 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P5.get();
			case P6 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P6.get();
			case P7 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P7.get();	
			case P8 -> MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P8.get();	
			default -> throw new IllegalArgumentException("Invalid pregnancy phase: " + phase);
		};
	}
	
	static AbstractCreeperGirl.ExplosionData getExplosionValuesByPregnancyPhase(PregnancyPhase pregnancyPhase) {
		int explosionIntensity = AbstractCreeperGirl.DEFAULT_EXPLOSION_DATA.explosionItensity();
		int explosionRadius = AbstractCreeperGirl.DEFAULT_EXPLOSION_DATA.explosionRadius();
		int maxSwell = AbstractCreeperGirl.DEFAULT_EXPLOSION_DATA.maxSwell();

		switch (pregnancyPhase) {
			case P2, P3 -> explosionRadius += 1;		
			case P4, P5 -> {
				explosionIntensity += 2;
				explosionRadius += 2;
				maxSwell += 3;
			}
			case P6, P7 -> {
				explosionIntensity += 3;
				explosionRadius += 3;
				maxSwell += 6;
			}
			case P8 -> {
				explosionIntensity += 4;
				explosionRadius += 4;
				maxSwell += 9;
			}
			default -> {
				break;
			}
		}

		return new AbstractCreeperGirl.ExplosionData(explosionIntensity, explosionRadius, maxSwell);
	}
	
	static void reassessTameGoals(AbstractTamableCreeperGirl creeperGirl) {
		if (creeperGirl.isTame()) {	
			GoalHelper.addGoalWithReplacement(creeperGirl, 6, new EatGoal<>(creeperGirl, 0.6F, 20, InventorySlot.MOUTH));
		}
		else {
			GoalHelper.removeGoalByClass(creeperGirl.goalSelector, EatGoal.class);
		}	
	}
	
	static void reassessTameGoalsBeingPregnant(AbstractTamablePregnantCreeperGirl creeperGirl) {
		if (creeperGirl.isTame()) {		
			GoalHelper.addGoalWithReplacement(creeperGirl, 6, new EatGoal<>(creeperGirl, 0.6F, 30, InventorySlot.MOUTH) {
				@Override
				public boolean canUse() {
					return super.canUse() 	
					&& !creeperGirl.getPregnancyData().isIncapacitated();
				}
				
				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse() 
					&& !creeperGirl.getPregnancyData().isIncapacitated();
				}
			});
		}
		else {
			GoalHelper.removeGoalByClass(creeperGirl.goalSelector, EatGoal.class);
		}
	}
}
