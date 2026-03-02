package dev.dixmk.minepreggo.world.entity.preggo.ender;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.EatGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.PreggoMobFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlotMapper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.TamablePreggoMobDataImpl;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.AbstractEnderWomanInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.AbstractEnderWomanMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.EnderWomanMenuHelper;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

public abstract class AbstractTamableEnderWoman extends AbstractEnderWoman implements ITamablePreggoMob<IFemaleEntity> {
    private static final TamablePreggoMobDataImpl.DataAccessor<AbstractTamableEnderWoman> DATA_HOLDER = new TamablePreggoMobDataImpl.DataAccessor<>(AbstractTamableEnderWoman.class);
	
	protected final ITamablePreggoMobSystem tamablePreggoMobSystem;
	
	protected final IFemaleEntity femaleEntity;

	protected final ITamablePreggoMobData tamablePreggoMobData = new TamablePreggoMobDataImpl<>(DATA_HOLDER, this);
	
	protected final Inventory inventory;

	protected boolean breakBlocks = false;
	
	protected AbstractTamableEnderWoman(EntityType<? extends AbstractEnderWoman> entityType, Level level, Creature typeOfCreature) {
		super(entityType, level, typeOfCreature);
		this.femaleEntity = createFemaleEntityData();   
		this.tamablePreggoMobSystem = createTamablePreggoMobSystem();
		this.inventory = createInventory();
		xpReward = 12;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	protected abstract @Nonnull ITamablePreggoMobSystem createTamablePreggoMobSystem();
	
	protected abstract @Nonnull IFemaleEntity createFemaleEntityData();
	
	protected abstract @Nonnull Inventory createInventory();
	
    @Override
    protected boolean shouldRandomlyTeleport() {
    	return !this.isTame() || getTamableData().isSavage();
    }
	
	@Override
	public void setBreakBlocks(boolean breakBlocks) {
		this.breakBlocks = breakBlocks;
	}

	@Override
	public boolean canBreakBlocks() {
		return this.breakBlocks;
	}
	
	@Override
	public boolean canBeLeashed(Player source) {
		return super.canBeLeashed(source) && this.isOwnedBy(source) && !this.tamablePreggoMobData.isSavage();
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		DATA_HOLDER.defineSynchedData(this);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.put("InventoryCustom", inventory.getHandler().serializeNBT());
		compound.put("defaultFemaleEntityImpl", this.femaleEntity.serializeNBT());
		compound.put("TamableData", tamablePreggoMobData.serializeNBT());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.get("InventoryCustom") instanceof CompoundTag inventoryTag)	
			inventory.getHandler().deserializeNBT(inventoryTag);		
		femaleEntity.deserializeNBT(compound.getCompound("defaultFemaleEntityImpl"));
		tamablePreggoMobData.deserializeNBT(compound.getCompound("TamableData"));
		
		if (!this.level().isClientSide && this.isTame()) {   
			if (!this.tamablePreggoMobData.isSavage()) {
				this.registerGoalsBeingTameAndNotSavage();
			}
			else {
				this.registerGoalsBeingTameAndSavage();
			}
		}
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return true;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
		if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && side == null)
			return LazyOptional.of(inventory::getHandler).cast();
		return super.getCapability(capability, side);
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		var handler = inventory.getHandler();
		var slotMapper = inventory.getSlotMapper();
		for (int i = inventory.getSlotMapper().getExtraSlotsRange().leftInt(); i < handler.getSlots(); ++i) {
			ItemStack itemstack = handler.getStackInSlot(i);
			if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
				this.spawnAtLocation(itemstack);
			}
		}
		slotMapper.getCustomSlots().forEach(slot -> {
			ItemStack itemstack = inventory.getHandler().getStackInSlot(slotMapper.getSlotIndex(slot));
			if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
				this.spawnAtLocation(itemstack);
			}
		});
	}	
	
	@Override
   	public void aiStep() {
      super.aiStep();
      this.updateSwingTime();      
      if (this.isAlive()) {	  
          this.tamablePreggoMobSystem.onServerTick();       
      }
	}
	
	@Override
	public void tick() {
		super.tick();		
		if (this.level().isClientSide) return;	
		
		this.tamablePreggoMobSystem.cinematicTick();		
	}
	
	@Override
	protected void afterTaming() {
		if (!this.level().isClientSide) {
			this.tamablePreggoMobData.setSavage(false);
		}
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return this.tamablePreggoMobData.isWaiting() && !this.tamablePreggoMobData.isPanic();
	}
	
	@Override
	public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {		
		return !(target instanceof Ghast 
				|| target instanceof TamableAnimal tamableTarget && tamableTarget.isOwnedBy(owner)
				|| target instanceof AbstractHorse houseTarget && houseTarget.isTamed()
				|| target instanceof Player pTarget && owner instanceof Player pOwmer && !(pOwmer).canHarmPlayer(pTarget)) ;
	}
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mate) {
		return null;
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack candidate, ItemStack existing) {
		final var slot = LivingEntity.getEquipmentSlotForItem(candidate);				
		if (slot.getType() == EquipmentSlot.Type.HAND) {
			return super.canReplaceCurrentItem(candidate, existing) && !this.isCarring();
		}
		return super.canReplaceCurrentItem(candidate, existing);
	}

	@Override
	protected void pickUpItem(ItemEntity stack) {		
		ItemStack itemstack = stack.getItem();
		ItemStack itemstack1 = this.equipItemIfPossible(itemstack.copy());			
		if (!itemstack1.isEmpty()) {
			this.onItemPickup(stack);
			this.take(stack, itemstack1.getCount());
			itemstack.shrink(itemstack1.getCount());		
			if (itemstack.isEmpty()) {
				stack.discard();
			}
		}
		else {
			PreggoMobHelper.storeItemInExtraSlots(this, stack);	
		}
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {				
		boolean result = super.hurt(damagesource, amount);	
		if (result && !this.level().isClientSide) {
			PreggoMobHelper.tryToDamageArmor(this, damagesource);	
	
			if (this.tamablePreggoMobData.canBePanicking()
					&& damagesource.is(DamageTypes.GENERIC)
					&& !this.isOwnedBy(this.getLastHurtByMob())) {			
					this.setTarget(this.getLastHurtByMob());							
					this.tamablePreggoMobData.setPanic(true);
			}	
			
			if (this.getOwner() instanceof ServerPlayer serverPlayer
					&& (serverPlayer.containerMenu instanceof AbstractEnderWomanInventoryMenu<?> || serverPlayer.containerMenu instanceof AbstractEnderWomanMainMenu<?>)) {
				serverPlayer.closeContainer();
			}			
			
			this.tamablePreggoMobSystem.onHurtSuccessful(damagesource);
		}		
		return result;
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {		
		boolean result = super.doHurtTarget(target);	
		if (result && !this.level().isClientSide) {
			PreggoMobHelper.tryToDamageItemOnMainHand(this);
			this.tamablePreggoMobSystem.onDoHurtTargetSuccessful(target);		
		}
		return result;
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {				
		var retval = super.mobInteract(sourceentity, hand);		
		if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
			return retval;
		}		
		if (tamablePreggoMobSystem.canOwnerAccessGUI(sourceentity)) {			
			if (!this.level().isClientSide && sourceentity instanceof ServerPlayer serverPlayer) {
				EnderWomanMenuHelper.showMainMenuForMonster(serverPlayer, this);			
				
				// TODO: Find a better way to stop panicking when owner interacts with the mob.
				if (this.tamablePreggoMobData.isPanic())
					this.tamablePreggoMobData.setPanic(false);
			}	
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}
		else {			
			return tamablePreggoMobSystem.onRightClick(sourceentity);
		}	
	}	
	
	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AbstractEnderWoman.EnderWomanFreezeWhenLookedAt(this));       
		this.goalSelector.addGoal(10, new AbstractEnderWoman.EnderWomanLeaveBlockGoal(this));
        this.goalSelector.addGoal(11, new AbstractEnderWoman.EnderWomanTakeBlockGoal(this) {
            @Override
            public boolean canUse() {
            	return super.canUse()
            			&& abstractEnderWoman.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()
            			&& abstractEnderWoman.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty();
            }
        });
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false) {
			@Override
			public boolean canUse() {
				return super.canUse()
				&& ((isTame() && !getTamableData().isWaiting()) || getTamableData().isSavage());		
			}
		});
		this.goalSelector.addGoal(5, new AbstractEnderWoman.EnderWomanTeleportToTargetGoal(this, 196F, 25F) {
			@Override
			public boolean canUse() {
				return super.canUse()
						&& ((isTame() && !getTamableData().isWaiting()) || getTamableData().isSavage());		
			}
		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));		
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6F) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !getTamableData().isWaiting()
				&& !LivingEntityHelper.hasValidTarget(mob);
			}
			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !LivingEntityHelper.isTargetStillValid(mob);
			}
		});
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (getTamableData().isSavage() || !isTame());		
			}
		});	      
        this.targetSelector.addGoal(1, new AbstractEnderWoman.EnderWomanLookForPlayerGoal(this, this::isAngryAt) {
			@Override
			public boolean canUse() {
				return super.canUse() && pendingTarget != null && !abstractEnderWoman.isOwnedBy(pendingTarget);	
			}
		});
	}
	
	@Override
	protected void reassessTameGoals() {
		if (this.isTame()) {		
			GoalHelper.addGoalWithReplacement(this, 6, new EatGoal<>(this, 0.6F, 20));
			GoalHelper.addGoalWithReplacement(this, 3, new OwnerHurtByTargetGoal(this) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !getTamableData().isSavage()
					&& !getTamableData().isWaiting();	
				}
			});
			GoalHelper.addGoalWithReplacement(this, 4, new OwnerHurtTargetGoal(this) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !getTamableData().isSavage()
					&& !getTamableData().isWaiting();			
				}
			}, true);
			GoalHelper.addGoalWithReplacement(this, 2, new PreggoMobFollowOwnerGoal<>(this, 1.2D, 6F, 2F, false));
			GoalHelper.addGoalWithReplacement(this, 7, new AbstractEnderWoman.EnderWomanTeleportToOwnerGoal(this, 196F, 81F) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& getTamableData().isFollowing()
					&& !getTamableData().isSavage();			
				}
			});
			GoalHelper.removeGoalByClass(this.goalSelector, WaterAvoidingRandomStrollGoal.class);
		}
		else {
			GoalHelper.removeGoalByClass(this.goalSelector, Set.of(
					EatGoal.class,
					OwnerHurtByTargetGoal.class,
					PreggoMobFollowOwnerGoal.class,
					AbstractEnderWoman.EnderWomanTeleportToOwnerGoal.class
			));
			GoalHelper.removeGoalByClass(this.targetSelector, OwnerHurtTargetGoal.class);
			GoalHelper.addGoalWithReplacement(this, 6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		}	
	}
	
	protected void registerGoalsBeingTameAndNotSavage() {
		if (this.tamablePreggoMobData.isWandering()) {
			PreggoMobHelper.addWanderingGoals(this, 6, 3);
		}	
	}
	
	protected void registerGoalsBeingTameAndSavage() {
		GoalHelper.addGoalWithReplacement(this, 6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	}
	
	// ITamablePreggomob START
	@Override
	public Inventory getInventory() {
		return inventory;
	}
	
	@Override
	public void setCinematicOwner(ServerPlayer player) {
		this.tamablePreggoMobSystem.setCinematicOwner(player);
	}

	@Override
	public void setCinematicEndTime(long time) {
		this.tamablePreggoMobSystem.setCinematicEndTime(time);
	}
		
	@Override
	public IFemaleEntity getGenderedData() {
		return femaleEntity;
	}
	
	@Override
	public ITamablePreggoMobData getTamableData() {
		return tamablePreggoMobData;
	}
	
	// ITamablePreggomob END
	
	public boolean teleportWithOwner(BlockPos targetPos) {
	    if (this.canTeleportWithOwner()) {
	        double destX = targetPos.getX() + 0.5;
	        double destY = targetPos.getY() + 1.0;
	        double destZ = targetPos.getZ() + 0.5;
	        
	        boolean flag = teleport(destX, destY, destZ);
	        if (!this.isVehicle() && this.getOwner() != null) {
	            var owner = this.getOwner();
	            owner.teleportTo(destX, destY, destZ + 0.5);		
	        }
	        if (flag && !this.isSilent()) {
	            this.level().playSound(null, destX, destY, destZ, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
	            this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
	        }
	        return flag;	
	    }
	    return false;
	}
	
	public boolean canTeleportWithOwner() {
		return false;
	}
   
	public boolean canBeMountedBy(LivingEntity target) {
		return false;
	}
	
    public static void syncBlockToInventory(AbstractTamableEnderWoman enderWoman) {
        Inventory inventory = enderWoman.getInventory();
    	InventorySlotMapper slotMapper = inventory.getSlotMapper();
    	
        int customIndex = slotMapper.getSlotIndex(InventorySlot.BOTH_HANDS);
        if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
            return;
        }
        
        BlockState blockStack = enderWoman.getCarriedBlock();
        
        if (blockStack == null) {
			inventory.getHandler().setStackInSlot(customIndex, ItemStack.EMPTY);
		} else {
			inventory.getHandler().setStackInSlot(customIndex, new ItemStack(blockStack.getBlock()));
		}
    }
    
    public static void syncBlockToVanilla(AbstractTamableEnderWoman enderWoman) {
    	Inventory inventory = enderWoman.getInventory();
		InventorySlotMapper slotMapper = inventory.getSlotMapper();
		
		int customIndex = slotMapper.getSlotIndex(InventorySlot.BOTH_HANDS);
		if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX) {
			return;
		}
		
		ItemStack blockStack = inventory.getHandler().getStackInSlot(customIndex);
		
		if (blockStack.isEmpty()) {
			enderWoman.setCarriedBlock(null);
		} else if (blockStack.getItem() instanceof BlockItem blockItem) {
			enderWoman.setCarriedBlock(blockItem.getBlock().defaultBlockState());
		}
    }
    
    public static boolean syncBlockToVanillaIfChanged(AbstractTamableEnderWoman enderWoman) {
        Inventory inventory = enderWoman.getInventory();
        InventorySlotMapper slotMapper = inventory.getSlotMapper();
        int customIndex = slotMapper.getSlotIndex(InventorySlot.BOTH_HANDS);
        if (customIndex == InventorySlotMapper.DEFAULT_INVALID_SLOT_INDEX)
            return false;

        ItemStack customStack = inventory.getHandler().getStackInSlot(customIndex);
        BlockState blockState = enderWoman.getCarriedBlock();
        
        Item customItem = customStack.getItem();
        Item blockItem = blockState != null ? blockState.getBlock().asItem() : Items.AIR;

        if (customItem != blockItem) {
			if (customStack.isEmpty()) {
				enderWoman.setCarriedBlock(null);
				return true;
			}
			else if (customStack.getItem() instanceof BlockItem blockItem2) {
				enderWoman.setCarriedBlock(blockItem2.getBlock().defaultBlockState());
			} 
			return true;
		}

        return false;
    }
 }
