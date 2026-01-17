package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.ai.goal.PreggoMobAIHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.TamablePreggoMobDataImpl;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlInventoryMenu;
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
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;

public abstract class AbstractTamableCreeperGirl extends AbstractCreeperGirl implements ITamablePreggoMob<IFemaleEntity> {
	public static final int INVENTORY_SIZE = 13;
	private final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE);
	private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));	
	
    private static final TamablePreggoMobDataImpl.DataAccessor<AbstractTamableCreeperGirl> DATA_HOLDER = new TamablePreggoMobDataImpl.DataAccessor<>(AbstractTamableCreeperGirl.class);
	
	protected final ITamablePreggoMobSystem tamablePreggoMobSystem;
	
	protected final IFemaleEntity femaleEntity;

	protected final ITamablePreggoMobData tamablePreggoMobData = new TamablePreggoMobDataImpl<>(DATA_HOLDER, this);
	
	protected boolean breakBlocks = false;
	private	int poweredTimer = 0;
	
	protected AbstractTamableCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_, Creature typeOfCreature) {
	      super(p_21803_, p_21804_, typeOfCreature);
	      this.reassessTameGoals();	   
	      this.femaleEntity = createFemaleEntity();
	      this.tamablePreggoMobSystem = createTamableSystem();
	      
	      if (this.getTamableData() == null) {
	    	  MinepreggoMod.LOGGER.error("TamablePreggoMobData is null for entity {}", this);
	      }
	      else {
	    	  MinepreggoMod.LOGGER.info("TamablePreggoMobData created for entity {}", this);
	      }
	}
		 
	protected abstract @Nonnull ITamablePreggoMobSystem createTamableSystem();
	
	protected abstract @Nonnull IFemaleEntity createFemaleEntity();
	
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
		compound.put("InventoryCustom", inventory.serializeNBT());
		compound.put("defaultFemaleEntityImpl", this.femaleEntity.serializeNBT());
		compound.put("TamableData", tamablePreggoMobData.serializeNBT());
	}	
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		Tag inventoryCustom = compound.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag inventoryTag)
			inventory.deserializeNBT(inventoryTag);	
		femaleEntity.deserializeNBT(compound.getCompound("defaultFemaleEntityImpl"));
		tamablePreggoMobData.deserializeNBT(compound.getCompound("TamableData"));
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {
			@Override
			public boolean canUse() {				
				return super.canUse() && canExplode();								
			}
		});
		PreggoMobAIHelper.setTamableCreeperGirlGoals(this);
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
          this.tamablePreggoMobSystem.onServerTick();
      }
	}

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
		if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && side == null)
			return LazyOptional.of(() -> combined).cast();
		return super.getCapability(capability, side);
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		for (int i = ITamablePreggoMob.FOOD_INVENTORY_SLOT; i < inventory.getSlots(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);
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
					&& (serverPlayer.containerMenu instanceof AbstractCreeperGirlMainMenu<?> || serverPlayer.containerMenu instanceof AbstractCreeperGirlInventoryMenu<?>)) {
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
		if (tamablePreggoMobSystem.canOwnerAccessGUI(sourceentity)) {			
			if (!this.level().isClientSide && sourceentity instanceof ServerPlayer serverPlayer) {
				CreeperGirlMenuHelper.showMainMenu(serverPlayer, this);			
				
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
	public boolean hasCustomHeadAnimation() {
		return this.tamablePreggoMobData.isWaiting();
	}
	
	@Override
	public void tick() {
		super.tick();
			
		if (this.level().isClientSide) return;
				
		tamablePreggoMobSystem.cinematicTick();
				
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
			PreggoMobHelper.storeItemInSpecificRange(this, p_21471_, ITamablePreggoMob.FOOD_INVENTORY_SLOT + 1, INVENTORY_SIZE - 1);	
		}
	}
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
	
	// ITamablePreggoMob START
	@Override
	public int getInventorySize() {
		return INVENTORY_SIZE;
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
	public  ITamablePreggoMobData getTamableData() {
		return tamablePreggoMobData;
	}
	
	// ITamablePreggoMob END
}
