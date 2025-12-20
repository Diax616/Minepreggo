package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.world.entity.ai.goal.PreggoMobAIHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobBody;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlInventoryMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlMainMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlMenuHelper;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.PrePregnancyData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;

public abstract class AbstractTamableZombieGirl<P extends PreggoMobSystem<?>> extends AbstractZombieGirl implements ITamablePreggoMob<FemaleEntityImpl>, IFemaleEntity {
	private static final EntityDataAccessor<Integer> DATA_HUNGRY = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> DATA_SAVAGE = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_ANGRY = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_WAITING = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_BREAK_BLOCKS = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_PICKUP_ITEMS = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	
	private static final EntityDataAccessor<Optional<PreggoMobFace>> DATA_FACE = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, MinepreggoModEntityDataSerializers.OPTIONAL_PREGGO_MOB_FACE);
	private static final EntityDataAccessor<Optional<PreggoMobBody>> DATA_BODY = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, MinepreggoModEntityDataSerializers.OPTIONAL_PREGGO_MOB_BODY);
	
	private final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE);
	private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));
	public static final int INVENTORY_SIZE = 15;

	private boolean panic = false;
	private int hungryTimer = 0;
	protected final P preggoMobSystem;
	
	protected final FemaleEntityImpl defaultFemaleEntityImpl = new FemaleEntityImpl();
	
	protected AbstractTamableZombieGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_) {
	      super(p_21803_, p_21804_, Creature.HUMANOID);
	      this.reassessTameGoals();	     
	      this.preggoMobSystem = createPreggoMobSystem();
	}
	
	@Nonnull
	protected abstract P createPreggoMobSystem();
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_HUNGRY, 12);		
		this.entityData.define(DATA_SAVAGE, true);
		this.entityData.define(DATA_ANGRY, false);
		this.entityData.define(DATA_WAITING, false);
		this.entityData.define(DATA_BREAK_BLOCKS, false);
		this.entityData.define(DATA_PICKUP_ITEMS, this.canPickUpLoot());
		this.entityData.define(DATA_FACE, Optional.empty());
		this.entityData.define(DATA_BODY, Optional.empty());
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.put("InventoryCustom", inventory.serializeNBT());
		compound.putInt("DataHungry", this.entityData.get(DATA_HUNGRY));
		compound.putInt("DataHungryTimer", hungryTimer);
		compound.putBoolean("DataSavage", this.entityData.get(DATA_SAVAGE));
		compound.putBoolean("DataWaiting", this.entityData.get(DATA_WAITING));
		compound.putBoolean("DataAngry", this.entityData.get(DATA_ANGRY));
		compound.putBoolean("DataBreakBlocks", this.entityData.get(DATA_BREAK_BLOCKS));
		compound.putBoolean("DataPickUpItems", this.entityData.get(DATA_PICKUP_ITEMS));
		compound.putBoolean("DataPanic", this.panic);	
		final var face = getFaceState();
		if (face != null) {
			compound.putString(PreggoMobFace.NBT_KEY, face.name());
		}
		final var body = getBodyState();
		if (body != null) {
			compound.putString(PreggoMobBody.NBT_KEY, body.name());
		}
		compound.put("defaultFemaleEntityImpl", this.defaultFemaleEntityImpl.serializeNBT());
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
		this.entityData.set(DATA_BREAK_BLOCKS, compound.getBoolean("DataBreakBlocks"));	
		this.entityData.set(DATA_PICKUP_ITEMS, compound.getBoolean("DataPickUpItems"));
		
		this.panic = compound.getBoolean("DataPanic");
		
		if (compound.contains(PreggoMobFace.NBT_KEY, Tag.TAG_STRING)) {
			setFaceState(PreggoMobFace.valueOf(compound.getString(PreggoMobFace.NBT_KEY)));
		}
		if (compound.contains(PreggoMobBody.NBT_KEY, Tag.TAG_STRING)) {
			setBodyState(PreggoMobBody.valueOf(compound.getString(PreggoMobBody.NBT_KEY)));
		}
		if (compound.contains("defaultFemaleEntityImpl", Tag.TAG_COMPOUND)) {
			this.defaultFemaleEntityImpl.deserializeNBT(compound.getCompound("defaultFemaleEntityImpl"));
		}
	}

	@Override
	public boolean canBeTamedByPlayer() {
		return true;
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
	protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {
		super.populateDefaultEquipmentSlots(p_219165_, p_219166_);
		if (p_219165_.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
			int i = p_219165_.nextInt(3);
			if (i == 0) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
			}
		}
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		boolean result = super.hurt(damagesource, amount);	
		if (result) {
			PreggoMobHelper.tryToDamageArmor(this, damagesource);
			
			if (canBePanicking()) {	
				if ((damagesource.is(DamageTypes.GENERIC))
						&& !this.isOwnedBy(this.getLastHurtByMob())) {
					this.setTarget(this.getLastHurtByMob());							
					this.setPanic(true);
				}
				else if(damagesource.is(DamageTypes.IN_FIRE) || damagesource.is(DamageTypes.ON_FIRE)) {
					this.setPanic(true);
				}								
			}	
			
			if (this.getOwner() instanceof ServerPlayer serverPlayer
					&& (serverPlayer.containerMenu instanceof AbstractZombieGirlMainMenu<?> || serverPlayer.containerMenu instanceof AbstractZombieGirlInventoryMenu<?>)) {
				serverPlayer.closeContainer();
			}	
		}		
		return result;
	}
	
	public boolean canBePanicking() {
		return this.isWaiting() && !this.isPanic();
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
	protected void registerGoals() {
		PreggoMobAIHelper.setTamableZombieGirlGoals(this);
		this.goalSelector.addGoal(8, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0D, 3){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isWaiting();
			}
		});	
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
	protected void afterTaming() {
		if (!this.level().isClientSide) {
			this.setSavage(false);
		}
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {	
		var retval = super.mobInteract(sourceentity, hand); 		
	
		if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
			return retval;
		}
		
		if (preggoMobSystem.canOwnerAccessGUI(sourceentity)) {			
			if (!this.level().isClientSide() && sourceentity instanceof ServerPlayer serverPlayer) {
				ZombieGirlMenuHelper.showMainMenu(serverPlayer, this);
				if (this.isPanic()) setPanic(false);
			}			
			return InteractionResult.SUCCESS;
		}
		else {		
			return preggoMobSystem.onRightClick(sourceentity);
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
		return this.isWaiting() && !this.isPanic();
	}
	
	@Override
	protected void pickUpItem(ItemEntity p_21471_) {
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
	
	
	// ITamablePreggomob START
	@Override
	public int getInventorySize() {
		return INVENTORY_SIZE;
	}
	
	@Override
	public int getFullness() {
	    return this.entityData.get(DATA_HUNGRY);
	}

	@Override
	public void setFullness(int hungry) {
		this.entityData.set(DATA_HUNGRY, Mth.clamp(hungry, 0, ITamablePreggoMob.MAX_FULLNESS));
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
		this.setFullness(this.getFullness() + amount);
	}
	
	@Override
	public void reduceFullness(int amount) {
		this.setFullness(this.getFullness() - amount);		
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
		return this.panic;
	}

	@Override
	public void setPanic(boolean panic) {
	    this.panic = panic;
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


	@Override
	public @Nullable PreggoMobFace getFaceState() {
		return this.entityData.get(DATA_FACE).orElse(null);
	}

	@Override
	public void setFaceState(@Nullable PreggoMobFace state) {
		this.entityData.set(DATA_FACE, Optional.ofNullable(state));
	}

	@Override
	public void cleanFaceState() {
		this.entityData.set(DATA_FACE, Optional.empty());
	}

	@Override
	public @Nullable PreggoMobBody getBodyState() {
		return this.entityData.get(DATA_BODY).orElse(null);
	}

	@Override
	public void setBodyState(@Nullable PreggoMobBody state) {
		this.entityData.set(DATA_BODY, Optional.ofNullable(state));	
	}

	@Override
	public void cleanBodyState() {
		this.entityData.set(DATA_BODY, Optional.empty());			
	}
	
	@Override
	public FemaleEntityImpl getGenderedData() {
		return defaultFemaleEntityImpl;
	}
	
	// ITamablePreggomob END
	
	
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
	public boolean tryImpregnate(@Nonnegative int fertilizedEggs, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father) {
		return this.defaultFemaleEntityImpl.tryImpregnate(fertilizedEggs, father);
	}
	
	@Override
	public Optional<PrePregnancyData> getPrePregnancyData() {
		return this.defaultFemaleEntityImpl.getPrePregnancyData();
	}

	@Override
	public Optional<PostPregnancyData> getPostPregnancyData() {
		return this.defaultFemaleEntityImpl.getPostPregnancyData();
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

	
	
	// START WARNING: These methods will be removed from IFemaleEntity in future versions, they are only present here because of synchronization issues between server and client
	@Override
	public @Nullable PostPregnancy getPostPregnancyPhase() {
		return this.defaultFemaleEntityImpl.getPostPregnancyPhase();
	}
	
	@Override
	public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
		return this.defaultFemaleEntityImpl.tryActivatePostPregnancyPhase(postPregnancy);
	}
	
	@Override
	public boolean tryRemovePostPregnancyPhase() {
		return this.defaultFemaleEntityImpl.tryRemovePostPregnancyPhase();
	}
	
	@Override
	public int getPostPartumLactation() {
		return this.defaultFemaleEntityImpl.getPostPartumLactation();
	}
	
	@Override
	public int getPostPregnancyTimer() {
		return this.defaultFemaleEntityImpl.getPostPregnancyTimer();
	}
	
	@Override
	public void setPostPartumLactation(int amount) {
		this.defaultFemaleEntityImpl.setPostPartumLactation(amount);
	}

	@Override
	public void setPostPregnancyTimer(int ticks) {
		this.defaultFemaleEntityImpl.setPostPregnancyTimer(ticks);
	}
	// END WARNIN
	
	
	
	
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
	
	@Override
	public void resetFertilityRateTimer() {
		this.defaultFemaleEntityImpl.resetFertilityRateTimer();	
	}

	@Override
	public void resetFertilityRate() {
		this.defaultFemaleEntityImpl.resetFertilityRate();	
	}	

	// IFemaleEntity END
}
