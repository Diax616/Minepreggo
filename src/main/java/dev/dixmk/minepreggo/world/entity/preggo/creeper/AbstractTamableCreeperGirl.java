package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.PreggoMobFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.TamablePreggoMobDataImpl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractHumanoidCreeperGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlMenuHelper;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

public abstract class AbstractTamableCreeperGirl extends AbstractCreeperGirl implements ITamablePreggoMob<IFemaleEntity> {
    private static final TamablePreggoMobDataImpl.DataAccessor<AbstractTamableCreeperGirl> DATA_HOLDER = new TamablePreggoMobDataImpl.DataAccessor<>(AbstractTamableCreeperGirl.class);
	   
	protected final ITamablePreggoMobSystem preggoMobSystem;
	
	protected final IFemaleEntity femaleEntityData;

	protected final ITamablePreggoMobData tamablePreggoMobData = new TamablePreggoMobDataImpl<>(DATA_HOLDER, this);

    protected final Inventory inventory;
	
	protected boolean breakBlocks = false;
	private	int poweredTimer = 0;
	
	protected AbstractTamableCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_, Creature typeOfCreature) {
	      super(p_21803_, p_21804_, typeOfCreature);
	      this.femaleEntityData = createFemaleEntityData();
	      this.preggoMobSystem = createTamablePreggoMobSystem();    
	      this.inventory = createInventory();
	}
		 
	protected abstract @Nonnull ITamablePreggoMobSystem createTamablePreggoMobSystem();
	
	protected abstract @Nonnull IFemaleEntity createFemaleEntityData();
	
	protected abstract @Nonnull Inventory createInventory();
	
	@Override
	public void setBreakBlocks(boolean breakBlocks) {
		this.breakBlocks = breakBlocks;
	}

	@Override
	public boolean canBreakBlocks() {
		return this.breakBlocks;
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
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {
			@Override
			public boolean canUse() {				
				return super.canUse() && canExplode();								
			}
		});
		
		this.goalSelector.addGoal(2, new FloatGoal(this));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Ocelot.class, 6F, 1, 1.2));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Cat.class, 6F, 1, 1.2));		
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));		
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (getTamableData().isSavage() || !isTame());	
			}
		});
		
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
		
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
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
					&& !getTamableData().isWaiting();				
				}
			});
			GoalHelper.addGoalWithReplacement(this, 3, new OwnerHurtTargetGoal(this) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !getTamableData().isWaiting();			
				}
			}, true);
			GoalHelper.addGoalWithReplacement(this, 6, new PreggoMobFollowOwnerGoal<>(this, 1.2D, 7F, 2F, false));	
		} else {
			GoalHelper.removeGoalByClass(this.goalSelector, Set.of(PreggoMobFollowOwnerGoal.class, OwnerHurtByTargetGoal.class));
			GoalHelper.removeGoalByClass(this.targetSelector, OwnerHurtTargetGoal.class);
		}
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return true;
	}
	
	@Override
	public boolean canExplode() {
		switch (this.getCombatMode()) {
		case FIGHT_AND_EXPLODE: {
			return this.getHealth() <= this.getMaxHealth() / 2F;
		}
		case DONT_EXPLODE: {
			return false;
		}	
		default:
			return true;
		}
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      this.updateSwingTime();   
      
      if (this.isAlive()) {
          this.preggoMobSystem.onServerTick();
      }
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
		for (int i = inventory.getSlotMapper().getExtraSlotsRange().leftInt(); i < inventory.getHandler().getSlots(); ++i) {
			ItemStack itemstack = inventory.getHandler().getStackInSlot(i);
			if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
				this.spawnAtLocation(itemstack);
			}
		}
	}
	
	@Override
	public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {			
		return !(target instanceof Ghast 
				|| target instanceof TamableAnimal tamableTarget && tamableTarget.isOwnedBy(owner)
				|| target instanceof AbstractHorse houseTarget && houseTarget.isTamed()
				|| target instanceof Player pTarget && owner instanceof Player pOwmer && !(pOwmer).canHarmPlayer(pTarget)) ;
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
					&& (serverPlayer.containerMenu instanceof AbstractCreeperGirlMainMenu<?> || serverPlayer.containerMenu instanceof AbstractHumanoidCreeperGirlInventoryMenu<?>)) {
				serverPlayer.closeContainer();
			}			
		}		
		return result;
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {		
		boolean result = super.doHurtTarget(target);	
		if (result && !this.level().isClientSide) {
			PreggoMobHelper.tryToDamageItemOnMainHand(this);
		}
		return result;
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
		if (preggoMobSystem.canOwnerAccessGUI(sourceentity)) {			
			if (!this.level().isClientSide && sourceentity instanceof ServerPlayer serverPlayer) {
	
				if (this.getTypeOfCreature() == Creature.HUMANOID) {
					CreeperGirlMenuHelper.showMainMenuForHumanoid(serverPlayer, this);	
				}
				else {
					CreeperGirlMenuHelper.showMainMenuForMonster(serverPlayer, this);	
				}
				
				// TODO: Find a better way to stop panicking when owner interacts with the mob.
				if (this.tamablePreggoMobData.isPanic())
					this.tamablePreggoMobData.setPanic(false);
			}	
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}
		else {			
			return preggoMobSystem.onRightClick(sourceentity);
		}	
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return this.tamablePreggoMobData.isWaiting();
	}
	
	@Override
	public void tick() {
		super.tick();
			
		if (this.level().isClientSide) return;
				
		preggoMobSystem.cinematicTick();
				
		if (this.isPowered()) {		
			if (this.poweredTimer > 24000) {
				this.poweredTimer = 0;
				this.setPower(false);
			}
			else {
				++this.poweredTimer;
			}
		}
	}
	
	@Override
	public void thunderHit(ServerLevel p_32286_, LightningBolt p_32287_) {
		super.thunderHit(p_32286_, p_32287_);
		if (this.isTame() && !this.level().isClientSide()) {
			this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 24000, 0, false, true));
			this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 24000, 0, false, true));
			this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 24000, 0, false, true));
		}
	}
	
	@Override
	protected void pickUpItem(ItemEntity p_21471_) {	
		if (this.getTypeOfCreature() != Creature.HUMANOID) return;
		
		ItemStack itemstack = p_21471_.getItem();
		ItemStack itemstack1 = this.equipItemIfPossible(itemstack.copy());			
		if (!itemstack1.isEmpty()) {
			this.onItemPickup(p_21471_);
			this.take(p_21471_, itemstack1.getCount());
			itemstack.shrink(itemstack1.getCount());		
			if (itemstack.isEmpty()) {
				p_21471_.discard();
			}
		}
		else {
			PreggoMobHelper.storeItemInExtraSlots(this, p_21471_);	
		}
	}
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
	
	// ITamablePreggoMob START
	
	@Override
	public Inventory getInventory() {
		return this.inventory;
	}
	
	@Override
	public void setCinematicOwner(ServerPlayer player) {
		this.preggoMobSystem.setCinematicOwner(player);
	}

	@Override
	public void setCinematicEndTime(long time) {
		this.preggoMobSystem.setCinematicEndTime(time);
	}
		
	@Override
	public IFemaleEntity getGenderedData() {
		return femaleEntityData;
	}
	
	@Override
	public  ITamablePreggoMobData getTamableData() {
		return tamablePreggoMobData;
	}
	
	// ITamablePreggoMob END
}
