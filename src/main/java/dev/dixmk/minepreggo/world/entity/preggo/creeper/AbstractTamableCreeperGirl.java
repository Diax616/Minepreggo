package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.network.capability.FemaleEntityImpl;
import dev.dixmk.minepreggo.network.capability.Gender;
import dev.dixmk.minepreggo.network.capability.IFemaleEntity;
import dev.dixmk.minepreggo.world.entity.ai.goal.PreggoMobAIHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlMenuHelper;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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

public abstract class AbstractTamableCreeperGirl<S extends PreggoMobSystem<?>> extends AbstractCreeperGirl implements ITamablePreggoMob, IFemaleEntity {
	
	/* TODO A lot of EntityDataAccessor can be normal fields, only kept as EntityDataAccessor for syncing purposes. 
	 * Consider refactoring those that don't need to be synced.
	 * DATA_HUNGRY - can be normal field
	 * DATA_COMBAT_MODE - can be normal field
	 * DATA_BREAK_BLOCKS - can be normal field
	 * DATA_PICKUP_ITEMS - can be normal field
	 * DATA_ANGRY - can be normal field
	 * DATA_PANIC - can be normal field
	 */
	 
	protected static final EntityDataAccessor<Integer> DATA_HUNGRY = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> DATA_SAVAGE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> DATA_ANGRY = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> DATA_WAITING = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> DATA_PANIC = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<PreggoMobState> DATA_STATE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.STATE);
	protected static final EntityDataAccessor<CombatMode> DATA_COMBAT_MODE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.COMBAT_MODE);
	protected static final EntityDataAccessor<Boolean> DATA_BREAK_BLOCKS = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> DATA_PICKUP_ITEMS = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	
	public static final int INVENTORY_SIZE = 13;
	protected final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE);
	protected final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));	
	private int hungryTimer = 0;
	private int poweredTimer = 0; 
	protected final S preggoMobSystem;
	
	protected final FemaleEntityImpl defaultFemaleEntityImpl = new FemaleEntityImpl();

	protected AbstractTamableCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_) {
	      super(p_21803_, p_21804_);
	      this.reassessTameGoals();	   
	      this.preggoMobSystem = createPreggoMobSystem();
	}
		 
	@Nonnull
	protected abstract S createPreggoMobSystem();
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_HUNGRY, 4);		
		this.entityData.define(DATA_SAVAGE, false);
		this.entityData.define(DATA_ANGRY, false);
		this.entityData.define(DATA_WAITING, false);
		this.entityData.define(DATA_PANIC, false);
		this.entityData.define(DATA_BREAK_BLOCKS, false);
		this.entityData.define(DATA_PICKUP_ITEMS, this.canPickUpLoot());
		this.entityData.define(DATA_STATE, PreggoMobState.IDLE);
		this.entityData.define(DATA_COMBAT_MODE, CombatMode.FIGHT_AND_EXPLODE);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.put("InventoryCustom", inventory.serializeNBT());
		compound.putInt("DataHungry", this.entityData.get(DATA_HUNGRY));
		compound.putInt("DataHungryTimer", this.hungryTimer);	
		compound.putBoolean("DataSavage", this.entityData.get(DATA_SAVAGE));
		compound.putBoolean("DataWaiting", this.entityData.get(DATA_WAITING));
		compound.putBoolean("DataAngry", this.entityData.get(DATA_ANGRY));
		compound.putBoolean("DataPanic", this.entityData.get(DATA_PANIC));
		compound.putBoolean("DataBreakBlocks", this.entityData.get(DATA_BREAK_BLOCKS));
		compound.putBoolean("DataPickUpItems", this.entityData.get(DATA_PICKUP_ITEMS));
		compound.putInt("DataPoweredTimer", this.poweredTimer);
		compound.putInt("DataState", this.entityData.get(DATA_STATE).ordinal());
		compound.putInt("DataCombatMode", this.entityData.get(DATA_COMBAT_MODE).ordinal());
		this.defaultFemaleEntityImpl.serializeNBT(compound);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		Tag inventoryCustom = compound.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag inventoryTag)
			inventory.deserializeNBT(inventoryTag);	
		this.entityData.set(DATA_HUNGRY, compound.getInt("DataHungry"));
		this.hungryTimer = compound.getInt("DataHungryTimer");		
		this.entityData.set(DATA_SAVAGE, compound.getBoolean("DataSavage"));		
		this.entityData.set(DATA_WAITING, compound.getBoolean("DataWaiting"));		
		this.entityData.set(DATA_ANGRY, compound.getBoolean("DataAngry"));	
		this.entityData.set(DATA_PANIC, compound.getBoolean("DataPanic"));
		this.entityData.set(DATA_BREAK_BLOCKS, compound.getBoolean("DataBreakBlocks"));	
		this.entityData.set(DATA_PICKUP_ITEMS, compound.getBoolean("DataPickUpItems"));	
		this.poweredTimer = compound.getInt("DataPoweredTimer");
		this.entityData.set(DATA_STATE, PreggoMobState.values()[compound.getInt("DataState")]);
		this.entityData.set(DATA_COMBAT_MODE, CombatMode.values()[compound.getInt("DataCombatMode")]);
		this.defaultFemaleEntityImpl.deserializeNBT(compound);
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
	
	public void setcombatMode(CombatMode value) {
		this.entityData.set(DATA_COMBAT_MODE, value);
		if (value == CombatMode.EXPLODE)
			this.maxDistance = 4D;
	}
	
	public CombatMode getcombatMode() {
		return this.entityData.get(DATA_COMBAT_MODE);
	}
	
	@Override
	public boolean canExplode() {
		switch (this.getcombatMode()) {
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
		if (result && !this.level().isClientSide()) {
			PreggoMobHelper.tryToDamageArmor(this, damagesource);			
			if (this.isWaiting() 
					&& !this.isPanic()
					&& damagesource.is(DamageTypes.GENERIC)
					&& !this.isOwnedBy(this.getLastHurtByMob())) {			
					this.setTarget(this.getLastHurtByMob());							
					this.setPanic(true);
			}										
		}		
		return result;
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {		
		boolean result = super.doHurtTarget(target);	
		if (result && !this.level().isClientSide()) {
			PreggoMobHelper.tryToDamageItemOnMainHand(this);
		}
		return result;
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {				
		var retval = super.mobInteract(sourceentity, hand);		
		if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
			return retval;
		}		
		if (preggoMobSystem.canOwnerAccessGUI(sourceentity)) {			
			if (!this.level().isClientSide && sourceentity instanceof ServerPlayer serverPlayer) {
				CreeperGirlMenuHelper.showMainMenu(serverPlayer, this);
			}	
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}
		else {			
			return preggoMobSystem.onRightClick(sourceentity);
		}	
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return isWaiting();
	}
	
	@Override
	public void tick() {
		super.tick();
			
		if (this.level().isClientSide()) return;
				
		preggoMobSystem.cinematicTick();
				
		if (this.isPowered()) {		
			if (this.poweredTimer > 24000) {
				this.poweredTimer = 0;
				this.setPower(false);
			}
			else {
				--this.poweredTimer;
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
		if (this.typeOfCreature != Creature.HUMANOID) return;
		
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
	public int getFullness() {
	    return this.entityData.get(DATA_HUNGRY);
	}

	@Override
	public void setFullness(int hungry) {
	    this.entityData.set(DATA_HUNGRY, hungry);
	}

	@Override
	public int getHungryTimer() {
	    return this.hungryTimer;
	}

	@Override
	public void setHungryTimer(int ticks) {
	    this.hungryTimer = ticks;
	}
	
	@Override
	public void incrementFullness(int amount) {
		this.setFullness(Math.min(this.getFullness() + amount, ITamablePreggoMob.MAX_FULLNESS));
	}


	@Override
	public void reduceFullness(int amount) {
		this.setFullness(Math.max(this.getFullness() - amount, 0));
		
	}

	@Override
	public void incrementHungryTimer() {
		++this.hungryTimer;
	}

	@Override
	public void resetHungryTimer() {
		this.hungryTimer = 0;	
	}
	
	@Override
	public PreggoMobState getState() {
		return this.entityData.get(DATA_STATE);
	}

	@Override
	public void setState(PreggoMobState state) {
		this.entityData.set(DATA_STATE, state);
	}
	
	@Override
	public boolean isSavage() {
	    return this.entityData.get(DATA_SAVAGE);
	}
	
	@Override
	public void setSavage(boolean savage) {
	    this.entityData.set(DATA_SAVAGE, savage);
	}
	
	@Override
	public boolean isWaiting() {
	    return this.entityData.get(DATA_WAITING);
	}
	
	@Override
	public void setWaiting(boolean waiting) {
	    this.entityData.set(DATA_WAITING, waiting);
	}
	
	@Override
	public boolean isAngry() {
	    return this.entityData.get(DATA_ANGRY);
	}
	
	@Override
	public void setAngry(boolean angry) {
	    this.entityData.set(DATA_ANGRY, angry);
	}
	
	@Override
	public boolean isPanic() {
		return this.entityData.get(DATA_PANIC);
	}

	@Override
	public void setPanic(boolean panic) {
	    this.entityData.set(DATA_PANIC, panic);
	}
	
	@Override
	public void setCinematicOwner(ServerPlayer player) {
		preggoMobSystem.setCinematicOwner(player);
	}

	@Override
	public void setCinematicEndTime(long time) {
		preggoMobSystem.setCinematicEndTime(time);
	}
	
	@Override
	public boolean canPickUpItems() {
		return this.entityData.get(DATA_PICKUP_ITEMS);
	}

	@Override
	public void setPickUpItems(boolean value) {
		this.entityData.set(DATA_PICKUP_ITEMS, value);	
		this.setCanPickUpLoot(value);
	}

	@Override
	public boolean canBreakBlocks() {
		return this.entityData.get(DATA_BREAK_BLOCKS);
	}

	@Override
	public void setBreakBlocks(boolean value) {
		this.entityData.set(DATA_BREAK_BLOCKS, value);
	}

	// ITamablePreggoMob END
	
	
	
	
	// IFemaleEntity START
	
	@Override
	public int getPregnancyInitializerTimer() {
		return this.defaultFemaleEntityImpl.getPregnancyInitializerTimer();
	}

	@Override
	public void setPregnancyInitializerTimer(int ticks) {
		this.defaultFemaleEntityImpl.setPregnancyInitializerTimer(ticks);
	}

	@Override
	public void incrementPregnancyInitializerTimer() {
		this.defaultFemaleEntityImpl.incrementPregnancyInitializerTimer();
	}

	@Override
	public boolean isPregnant() {
		return this.defaultFemaleEntityImpl.isPregnant();
	}

	@Override
	public boolean canGetPregnant() {
		return this.defaultFemaleEntityImpl.canGetPregnant();
	}

	@Override
	public boolean tryImpregnate(@Nullable UUID father) {
		return this.defaultFemaleEntityImpl.tryImpregnate(father);
	}

	@Override
	public boolean tryCancelPregnancy() {
		return this.defaultFemaleEntityImpl.tryCancelPregnancy();
	}

	@Override
	public @Nullable UUID getFather() {
		return this.defaultFemaleEntityImpl.getFather();
	}

	@Override
	public boolean hasNaturalPregnancy() {
		return this.defaultFemaleEntityImpl.hasNaturalPregnancy();
	}

	@Override
	public @Nullable PostPregnancy getPostPregnancyPhase() {
		return this.defaultFemaleEntityImpl.getPostPregnancyPhase();
	}

	@Override
	public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
		return this.defaultFemaleEntityImpl.tryActivatePostPregnancyPhase(postPregnancy);
	}

	@Override
	public int getPostPregnancyTimer() {
		return this.defaultFemaleEntityImpl.getPostPregnancyTimer();
	}

	@Override
	public void setPostPregnancyTimer(int ticks) {
		this.defaultFemaleEntityImpl.setPostPregnancyTimer(ticks);
	}

	@Override
	public void incrementPostPregnancyTimer() {
		this.defaultFemaleEntityImpl.incrementPostPregnancyTimer();	
	}
	
	@Override
	public int getFertilityRateTimer() {
		return this.defaultFemaleEntityImpl.getFertilityRateTimer();
	}

	@Override
	public void setFertilityRateTimer(int ticks) {
		this.defaultFemaleEntityImpl.setFertilityRateTimer(ticks);
	}

	@Override
	public void incrementFertilityRateTimer() {
		this.defaultFemaleEntityImpl.incrementFertilityRateTimer();
	}

	@Override
	public float getFertilityRate() {
		return this.defaultFemaleEntityImpl.getFertilityRate();
	}

	@Override
	public void setFertilityRate(float rate) {
		this.defaultFemaleEntityImpl.setFertilityRate(rate);
	}

	@Override
	public void incrementFertilityRate(float rate) {
		this.defaultFemaleEntityImpl.incrementFertilityRate(rate);
	}

	@Override
	public int getSexualAppetite() {
		return this.defaultFemaleEntityImpl.getSexualAppetite();
	}

	@Override
	public void setSexualAppetite(int sexualAppetite) {
		this.defaultFemaleEntityImpl.setSexualAppetite(sexualAppetite);
	}

	@Override
	public void reduceSexualAppetite(int amount) {
		this.defaultFemaleEntityImpl.reduceSexualAppetite(amount);
	}

	@Override
	public void incrementSexualAppetite(int amount) {
		this.defaultFemaleEntityImpl.incrementSexualAppetite(amount);
	}

	@Override
	public int getSexualAppetiteTimer() {
		return this.defaultFemaleEntityImpl.getSexualAppetiteTimer();
	}

	@Override
	public void setSexualAppetiteTimer(int timer) {
		this.defaultFemaleEntityImpl.setSexualAppetiteTimer(timer);
	}

	@Override
	public void incrementSexualAppetiteTimer() {
		this.defaultFemaleEntityImpl.incrementSexualAppetiteTimer();
	}

	@Override
	public Gender getGender() {
		return this.defaultFemaleEntityImpl.getGender();
	}

	@Override
	public boolean canFuck() {
		return this.defaultFemaleEntityImpl.canFuck();
	}

	// IFemaleEntity END
}
