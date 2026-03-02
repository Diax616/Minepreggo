package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;

public class HostileZombieGirl extends AbstractHostileZombieGirl {

	private static final UUID SPEED_MODIFIER_BABY_UUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
	private static final AttributeModifier SPEED_MODIFIER_BABY = new AttributeModifier(SPEED_MODIFIER_BABY_UUID, "Baby speed boost", 0.2D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(HostileZombieGirl.class, EntityDataSerializers.BOOLEAN);
	
	public HostileZombieGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL.get(), world);
	}

	public HostileZombieGirl(EntityType<HostileZombieGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_BABY_ID, false);
	}

	@Override
	public boolean isBaby() {
		return this.getEntityData().get(DATA_BABY_ID);
	}

	@Override
	public int getExperienceReward() {
		if (this.isBaby()) {
			this.xpReward = (int)(this.xpReward * 2.5D);
		}

		return super.getExperienceReward();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return this.isBaby() ? 0.93F : 1.74F;
	}
	
	@Override
	public void setBaby(boolean value) {
		this.getEntityData().set(DATA_BABY_ID, value);
		if (this.level() != null && !this.level().isClientSide) {
			AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
			attributeinstance.removeModifier(SPEED_MODIFIER_BABY);
			if (value) {
				attributeinstance.addTransientModifier(SPEED_MODIFIER_BABY);
			}
		}
	}
	
	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
		if (DATA_BABY_ID.equals(accessor)) {
			this.refreshDimensions();
		}
		super.onSyncedDataUpdated(accessor);
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return true;
	}
	
	@Override
	protected void afterTaming() {
		if (this.level() instanceof ServerLevel serverLevel) {
			TamableZombieGirl next = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get().spawn(serverLevel, BlockPos.containing(this.getX(), this.getY(), this.getZ()), MobSpawnType.CONVERSION);
			LivingEntityHelper.copyRotation(this, next);
			EntityHelper.copyName(this, next);
			LivingEntityHelper.copyHealth(this, next);
			LivingEntityHelper.transferSlots(this, next);
			PreggoMobHelper.syncFromEquipmentSlotToInventory(next);
			PreggoMobHelper.copyOwner(this, next);
			next.getTamableData().setSavage(false);		
			this.discard();
		}
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {	
		ItemStack itemstack = sourceentity.getItemInHand(hand);

		if  (itemstack.getItem() instanceof SpawnEggItem) 
			return InteractionResult.sidedSuccess(this.level().isClientSide());	
		
		return super.mobInteract(sourceentity, hand);
	}
	
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		HostileZombieGirl retval = MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		retval.setBaby(true);
		return retval;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("IsBaby", this.isBaby());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setBaby(compound.getBoolean("IsBaby"));
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.235);
	}
}