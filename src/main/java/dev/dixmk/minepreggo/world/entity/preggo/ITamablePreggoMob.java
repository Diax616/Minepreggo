package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;

public interface ITamablePreggoMob<G extends IBreedable> {
	
	static final int HEAD_INVENTORY_SLOT = EquipmentSlot.HEAD.getFilterFlag();
	static final int CHEST_INVENTORY_SLOT = EquipmentSlot.CHEST.getFilterFlag();
	static final int LEGS_INVENTORY_SLOT = EquipmentSlot.LEGS.getFilterFlag();
	static final int FEET_INVENTORY_SLOT = EquipmentSlot.FEET.getFilterFlag();
	static final int MAINHAND_INVENTORY_SLOT = EquipmentSlot.MAINHAND.getFilterFlag();
	static final int OFFHAND_INVENTORY_SLOT = EquipmentSlot.OFFHAND.getFilterFlag();
	static final int FOOD_INVENTORY_SLOT = 6;
	
	static final int MAX_FULLNESS = 20;
	
	ITamablePreggoMobData getTamableData();
	
    G getGenderedData();
    
    boolean hasCustomHeadAnimation();
    
	void setCinematicOwner(ServerPlayer player);
    
    void setCinematicEndTime(long time);
    
    int getInventorySize();
   
    void setBreakBlocks(boolean breakBlocks);
    boolean canBreakBlocks();
}