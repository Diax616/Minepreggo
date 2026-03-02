package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.level.Level;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.BreakBlocksToFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.EatGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.PreggoMobFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlotMapper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.TamablePreggoMobDataImpl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlMenuHelper;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

public abstract class AbstractTamableZombieGirl extends AbstractZombieGirl implements ITamablePreggoMob<IFemaleEntity> {

    private static final TamablePreggoMobDataImpl.DataAccessor<AbstractTamableZombieGirl> DATA_HOLDER = new TamablePreggoMobDataImpl.DataAccessor<>(AbstractTamableZombieGirl.class);
	
	protected final ITamablePreggoMobSystem tamablePreggoMobSystem;
	
	protected final IFemaleEntity femaleEntityData;

	protected final ITamablePreggoMobData tamablePreggoMobData = new TamablePreggoMobDataImpl<>(DATA_HOLDER, this);
	
	protected final Inventory inventory = new Inventory(InventorySlotMapper.createHumanoidDefault(), 8);
	
	protected boolean breakBlocks = false;
	
	protected AbstractTamableZombieGirl(EntityType<? extends AbstractZombieGirl> entityType, Level level) {
		super(entityType, level, Creature.HUMANOID);
		this.femaleEntityData = createFemaleEntityData();
		this.tamablePreggoMobSystem = createTamablePreggoMobSystem();
	}

	protected abstract @Nonnull ITamablePreggoMobSystem createTamablePreggoMobSystem();
	
	protected abstract @Nonnull IFemaleEntity createFemaleEntityData();
	
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
		compound.put("defaultFemaleEntityImpl", this.femaleEntityData.serializeNBT());
		compound.put("TamableData", tamablePreggoMobData.serializeNBT());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.get("InventoryCustom") instanceof CompoundTag inventoryTag)	
			inventory.getHandler().deserializeNBT(inventoryTag);		
		
		if (compound.contains("defaultFemaleEntityImpl", Tag.TAG_COMPOUND)) {
			femaleEntityData.deserializeNBT(compound.getCompound("defaultFemaleEntityImpl"));
		}
		if (compound.contains("TamableData", Tag.TAG_COMPOUND)) {
			tamablePreggoMobData.deserializeNBT(compound.getCompound("TamableData"));
		}
		
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
		for (int i = slotMapper.getExtraSlotsRange().leftInt(); i < handler.getSlots(); ++i) {
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
	public boolean hurt(DamageSource damagesource, float amount) {
		boolean result = super.hurt(damagesource, amount);	
		if (!this.level().isClientSide && result) {
			PreggoMobHelper.tryToDamageArmor(this, damagesource);
			
			if (tamablePreggoMobData.canBePanicking()) {	
				if ((damagesource.is(DamageTypes.GENERIC))
						&& !this.isOwnedBy(this.getLastHurtByMob())) {
					this.setTarget(this.getLastHurtByMob());							
					this.tamablePreggoMobData.setPanic(true);
				}
				else if(damagesource.is(DamageTypes.IN_FIRE) || damagesource.is(DamageTypes.ON_FIRE)) {
					this.tamablePreggoMobData.setPanic(true);
				}								
			}	
			
			if (this.getOwner() instanceof ServerPlayer serverPlayer
					&& (serverPlayer.containerMenu instanceof AbstractZombieGirlMainMenu<?> || serverPlayer.containerMenu instanceof AbstractZombieGirlInventoryMenu<?>)) {
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
	protected void registerGoals() {
		this.goalSelector.addGoal(5, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0D, 3){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isOnFire()
				&& !tamablePreggoMobData.isWaiting();
			}
		});	
		
		this.goalSelector.addGoal(1, new RestrictSunGoal(this));
		this.goalSelector.addGoal(1, new FleeSunGoal(this, 1.2D));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));	
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2D, false));	
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse()
				&& !isOnFire()
				&& (getTamableData().isSavage() || !isTame());
			}
		});		
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isOnFire()		
				&& (getTamableData().isSavage() || !isTame());
			}
		});			
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isOnFire()		
				&& (getTamableData().isSavage() || !isTame());		
			}
		});		
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isOnFire()
				&& ((isTame() && !getTamableData().isWaiting()) || getTamableData().isSavage());
			}
		});	
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6F) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isOnFire()
				&& !getTamableData().isWaiting()
				&& !LivingEntityHelper.hasValidTarget(mob);
			}
			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !isOnFire()
				&& !LivingEntityHelper.isTargetStillValid(mob);
			}
		});			
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isOnFire()
				&& (getTamableData().isSavage() || !isTame());		
			}
		});	
	}
	
	@Override
	protected void reassessTameGoals() {
		if (this.isTame()) {
			GoalHelper.addGoalWithReplacement(this, 3, new OwnerHurtByTargetGoal(this) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !getTamableData().isSavage()
					&& !getTamableData().isWaiting()
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()			
					&& !isOnFire();	
				}
			});
			GoalHelper.addGoalWithReplacement(this, 3, new OwnerHurtTargetGoal(this) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !getTamableData().isSavage()		
					&& !getTamableData().isWaiting()
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire();	
				}
			}, true);		
			GoalHelper.addGoalWithReplacement(this, 2, new PreggoMobFollowOwnerGoal<>(this, 1.2D, 6F, 2F, false) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire();	
				}
			});
			GoalHelper.addGoalWithReplacement(this, 4, new BreakBlocksToFollowOwnerGoal<>(this, 2, 5) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire();	
				}
			}, true);
			GoalHelper.addGoalWithReplacement(this, 6, new EatGoal<>(this, 0.6F, 20) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire();	
				}
			});
			GoalHelper.removeGoalByClass(this.goalSelector, WaterAvoidingRandomStrollGoal.class);
		} else {
			GoalHelper.removeGoalByClass(this.targetSelector, Set.of(BreakBlocksToFollowOwnerGoal.class, OwnerHurtTargetGoal.class));
			GoalHelper.removeGoalByClass(this.goalSelector, Set.of(EatGoal.class, PreggoMobFollowOwnerGoal.class, OwnerHurtByTargetGoal.class));
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
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {	
		var retval = super.mobInteract(sourceentity, hand); 		
	
		if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
			return retval;
		}
		
		if (this.tamablePreggoMobSystem.canOwnerAccessGUI(sourceentity)) {			
			if (!this.level().isClientSide() && sourceentity instanceof ServerPlayer serverPlayer) {
				ZombieGirlMenuHelper.showMainMenu(serverPlayer, this);
				
				// TODO: Find a better way to stop panicking when owner interacts with the mob.
				if (this.tamablePreggoMobData.isPanic()) 
					this.tamablePreggoMobData.setPanic(false);
			}			
			return InteractionResult.SUCCESS;
		}
		else {		
			return this.tamablePreggoMobSystem.onRightClick(sourceentity);
		}	
	}
	
	@Override
	public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {		
		return !(target instanceof Ghast 
				|| target instanceof TamableAnimal tamableTarget && tamableTarget.isOwnedBy(owner)
				|| target instanceof AbstractHorse houseTarget && houseTarget.isTamed()
				|| target instanceof Player pTarget && owner instanceof Player pOwmer && !(pOwmer).canHarmPlayer(pTarget)) ;
	}
		
	protected static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 26D)
				.add(Attributes.ARMOR, 1D)
				.add(Attributes.ATTACK_DAMAGE, 3D)
				.add(Attributes.FOLLOW_RANGE, 35D)
				.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return this.tamablePreggoMobData.isWaiting() && !this.tamablePreggoMobData.isPanic();
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
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mate) {
		return null;
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
		return femaleEntityData;
	}
	
	@Override
	public ITamablePreggoMobData getTamableData() {
		return tamablePreggoMobData;
	}
	
	// ITamablePreggomob END
}
