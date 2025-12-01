package dev.dixmk.minepreggo.world.entity.preggo;

import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.world.pregnancy.AbstractBreedableEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;

public interface ITamablePreggoMob<G extends AbstractBreedableEntity> {
	
	static final int HEAD_INVENTORY_SLOT = EquipmentSlot.HEAD.getFilterFlag();
	static final int CHEST_INVENTORY_SLOT = EquipmentSlot.CHEST.getFilterFlag();
	static final int LEGS_INVENTORY_SLOT = EquipmentSlot.LEGS.getFilterFlag();
	static final int FEET_INVENTORY_SLOT = EquipmentSlot.FEET.getFilterFlag();
	static final int MAINHAND_INVENTORY_SLOT = EquipmentSlot.MAINHAND.getFilterFlag();
	static final int OFFHAND_INVENTORY_SLOT = EquipmentSlot.OFFHAND.getFilterFlag();
	static final int FOOD_INVENTORY_SLOT = 6;
	
	static final int MAX_FULLNESS = 20;
	
    int getFullness();
    void setFullness(@NonNegative int fullness);
    void incrementFullness(@NonNegative int amount);
    void reduceFullness(@NonNegative int amount);
    
    boolean isWaiting();
    void setWaiting(boolean waiting);
    
    boolean isSavage();
    void setSavage(boolean savage);
    
    boolean isAngry();
    void setAngry(boolean angry);
    
    int getHungryTimer();
    void setHungryTimer(int ticks);
    void incrementHungryTimer();
    void resetHungryTimer();
    
    boolean isPanic();
    void setPanic(boolean panic);
      
    boolean hasCustomHeadAnimation();
	
	@Nullable PreggoMobFace getFaceState();
	void setFaceState (@Nullable PreggoMobFace state);
	void cleanFaceState ();
	
	@Nullable PreggoMobBody getBodyState();
	void setBodyState (@Nullable PreggoMobBody state);
	void cleanBodyState ();
	
	void setCinematicOwner(ServerPlayer player);
    
    void setCinematicEndTime(long time);
    
    int getInventorySize();
    
    boolean canPickUpItems();
    void setPickUpItems(boolean value);
    
    boolean canBreakBlocks();
    void setBreakBlocks(boolean value);
   
    G getGenderedData();
}