package dev.dixmk.minepreggo.world.entity.ai.goal;

import java.util.EnumSet;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlotMapper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

public class EatGoal<E extends PreggoMob & ITamablePreggoMob<?>> extends Goal {
    protected final E mob;
    private final float healThreshold;
    private final int eatDuration;
    private int eatTimer;
    private boolean isEating;
    private ItemStack food = ItemStack.EMPTY;
    private InteractionHand hand = null;

    public EatGoal(E mob, float healThreshold, int eatDuration) {
    	this.mob = mob;
        this.healThreshold = healThreshold;
        this.eatDuration = eatDuration;
        this.eatTimer = 0;
        this.isEating = false;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.isEating) {
            return false;
        }
        
        float healthPercentage = this.mob.getHealth() / this.mob.getMaxHealth();
        int fullness = mob.getTamableData().getFullness();
        
        return (fullness < ITamablePreggoMob.MAX_FULLNESS * 0.5 || 
        		(healthPercentage < this.healThreshold && fullness < ITamablePreggoMob.MAX_FULLNESS * 0.9))
        		&& hasFoodInInventory();
    }

    @Override
    public boolean canContinueToUse() {
        return this.isEating && this.eatTimer < this.eatDuration;
    }

    @Override
    public void start() {
        this.isEating = true;
        this.eatTimer = 0;
        this.mob.getNavigation().stop();
        this.food = getFoodItem();
        
    	// TODO: it does not handle if mob's hand is not empty, it must implement a function that swaps between current itemstack in target hand and food itemstack.       	
    	if (this.mob.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
    		this.hand = InteractionHand.OFF_HAND;
    		this.mob.setItemInHand(hand, food);
    	}
    	else if (this.mob.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
    		this.hand = InteractionHand.MAIN_HAND;
    		this.mob.setItemInHand(hand, food);
    	}
   
        this.mob.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
    }

    @Override
    public void tick() {
        this.eatTimer++;
      
        if (this.eatTimer % 5 == 0) {
            this.mob.playSound(SoundEvents.GENERIC_EAT, 0.5F + this.mob.getRandom().nextFloat() * 0.3F, 
                              0.8F + this.mob.getRandom().nextFloat() * 0.4F);
        }
        
        if (!this.mob.level().isClientSide && this.eatTimer % 4 == 0) {
            spawnEatingParticles();
        }
    }

    @Override
    public void stop() {
        if (this.eatTimer >= this.eatDuration) {                           
			final var foodProperties = this.food.getFoodProperties(mob);  			
			if (foodProperties == null) {
				return;
			}         	
			mob.getTamableData().incrementFullness(foodProperties.getNutrition());
        	this.mob.eat(mob.level(), this.food);
            this.mob.playSound(SoundEvents.PLAYER_BURP, 1.0F, 1.0F);
            
            if (hand != null) {
        		this.mob.setItemInHand(hand, ItemStack.EMPTY);
            }
        }
        
        this.isEating = false;
        this.eatTimer = 0;
        this.food = ItemStack.EMPTY;
    }

    private void spawnEatingParticles() {
    	if (this.food.isEmpty()) {
    		return;
    	}
        
        for (int i = 0; i < 5; i++) {
            double offsetX = (this.mob.getRandom().nextDouble() - 0.5) * 0.1;
            double offsetY = this.mob.getRandom().nextDouble() * 0.1;
            double offsetZ = (this.mob.getRandom().nextDouble() - 0.5) * 0.1;
            
            double particleX = this.mob.getX() + this.mob.getLookAngle().x * 0.5 + offsetX;
            double particleY = this.mob.getY() + this.mob.getEyeHeight() * 0.8 + offsetY;
            double particleZ = this.mob.getZ() + this.mob.getLookAngle().z * 0.5 + offsetZ;
            
            ((ServerLevel) this.mob.level()).sendParticles(
                new ItemParticleOption(ParticleTypes.ITEM, this.food),
                particleX, particleY, particleZ,
                1,
                offsetX, offsetY + 0.05, offsetZ,
                0.05
            );
        }
    }

    public boolean isEating() {
        return this.isEating;
    }

    public float getEatingProgress() {
        return this.isEating ? (float) this.eatTimer / (float) this.eatDuration : 0.0F;
    }
  
    private ItemStack getFoodItem() {
    	var inventory = mob.getInventory();
    	var slotMapper = inventory.getSlotMapper();
    	int foodSlotIndex = slotMapper.getSlotIndex(InventorySlot.FOOD);
		return inventory.getHandler().getStackInSlot(foodSlotIndex);	
    }
    
    private boolean hasFoodInInventory() {
    	var inventory = mob.getInventory();
    	var slotMapper = inventory.getSlotMapper();
    	int foodSlotIndex = slotMapper.getSlotIndex(InventorySlot.FOOD);
	
    	if (foodSlotIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
    		MinepreggoMod.LOGGER.warn("Mob {} has no food slot mapped in its inventory!", mob.getDisplayName().getString());
			return false;
		}
    	
		var foodStack = inventory.getHandler().getStackInSlot(foodSlotIndex);
		return !foodStack.isEmpty();
    }
}
