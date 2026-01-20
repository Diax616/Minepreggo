package dev.dixmk.minepreggo.world.entity.preggo.ender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.ai.goal.BreakBlocksToFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.EatGoal;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.TamablePreggoMobDataImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Endermite;
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

public abstract class AbstractTamableEnderWoman extends AbstractEnderWoman implements ITamablePreggoMob<IFemaleEntity> {
	private final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE);
	private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));
	public static final int INVENTORY_SIZE = 15;

    private static final TamablePreggoMobDataImpl.DataAccessor<AbstractTamableEnderWoman> DATA_HOLDER = new TamablePreggoMobDataImpl.DataAccessor<>(AbstractTamableEnderWoman.class);
	
	protected final ITamablePreggoMobSystem tamablePreggoMobSystem;
	
	protected final IFemaleEntity femaleEntity;

	protected final ITamablePreggoMobData tamablePreggoMobData = new TamablePreggoMobDataImpl<>(DATA_HOLDER, this);
	
	protected boolean breakBlocks = false;
	
	protected AbstractTamableEnderWoman(EntityType<? extends AbstractEnderWoman> p_32485_, Level p_32486_, Creature typeOfCreature) {
		super(p_32485_, p_32486_, typeOfCreature);
		this.femaleEntity = createFemaleEntity();   
		this.tamablePreggoMobSystem = createTamableSystem();
		xpReward = 12;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	protected abstract @Nonnull ITamablePreggoMobSystem createTamableSystem();
	
	protected abstract @Nonnull IFemaleEntity createFemaleEntity();
	
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
		if (compound.get("InventoryCustom") instanceof CompoundTag inventoryTag)	
			inventory.deserializeNBT(inventoryTag);		
		femaleEntity.deserializeNBT(compound.getCompound("defaultFemaleEntityImpl"));
		tamablePreggoMobData.deserializeNBT(compound.getCompound("TamableData"));
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
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
		
	@Override
	protected void registerGoals() {
		addTamableBehaviourGoals();
	}

	protected void addTamableBehaviourGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AbstractEnderWoman.EnderWomanFreezeWhenLookedAt(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (getTamableData().isSavage() || !isTame());	
			}
        });       
		this.goalSelector.addGoal(10, new AbstractEnderWoman.EnderWomanLeaveBlockGoal(this));
        this.goalSelector.addGoal(11, new AbstractEnderWoman.EnderWomanTakeBlockGoal(this));
        this.targetSelector.addGoal(1, new AbstractEnderWoman.EnderWomanLookForPlayerGoal(this, this::isAngryAt) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (getTamableData().isSavage() || !isTame());	
			}
        });
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
		this.goalSelector.addGoal(5, new AbstractEnderWoman.EnderWomanTeleportToTargetGoal(this, 400F, 25F));
			
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));		
		this.targetSelector.addGoal(4, new BreakBlocksToFollowOwnerGoal<>(this, 2, 7));	
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (getTamableData().isSavage() || !isTame());	
			}
		});
				
		this.goalSelector.addGoal(6, new EatGoal<>(this, 0.6F, 20));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
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
				&& !PreggoMobHelper.hasValidTarget(mob);
			}		
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !PreggoMobHelper.isTargetStillValid(mob);
			}
		});			
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (getTamableData().isSavage() || !isTame());		
			}
		});
	}
	

	// ITamablePreggomob START
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
	public ITamablePreggoMobData getTamableData() {
		return tamablePreggoMobData;
	}
	
	// ITamablePreggomob END
	
	
	
    protected static AttributeSupplier.Builder createTamableAttributes(double movementSpeed) {
        return Mob.createMobAttributes()
        		.add(Attributes.MAX_HEALTH, 46.0D)
        		.add(Attributes.MOVEMENT_SPEED, movementSpeed)
        		.add(Attributes.ATTACK_DAMAGE, 7.0D)
        		.add(Attributes.FOLLOW_RANGE, 64.0D);
	}
}
