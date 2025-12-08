package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.util.UUID;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MonsterCreeperGirl extends AbstractMonsterQuadrupedCreeperGirl {
	private static final UUID SPEED_MODIFIER_BABY_UUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
	private static final AttributeModifier SPEED_MODIFIER_BABY = new AttributeModifier(SPEED_MODIFIER_BABY_UUID, "Baby speed boost", 0.2D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(MonsterCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	
	public MonsterCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_CREEPER_GIRL.get(), world);
	}

	public MonsterCreeperGirl(EntityType<MonsterCreeperGirl> type, Level world) {
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
	protected float getStandingEyeHeight(Pose p_34313_, EntityDimensions p_34314_) {
		return this.isBaby() ? 0.93F : 1.74F;
	}
	
	@Override
	public void setBaby(boolean p_34309_) {
		this.getEntityData().set(DATA_BABY_ID, p_34309_);
		if (this.level() != null && !this.level().isClientSide) {
			AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
			attributeinstance.removeModifier(SPEED_MODIFIER_BABY);
			if (p_34309_) {
				attributeinstance.addTransientModifier(SPEED_MODIFIER_BABY);
			}
		}
	}
	
	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_34307_) {
		if (DATA_BABY_ID.equals(p_34307_)) {
			this.refreshDimensions();
		}

		super.onSyncedDataUpdated(p_34307_);
	}
	
	/*
	@Override
	public boolean canBeTamedByPlayer() {
		return true;
	}
	*/
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {	
		ItemStack itemstack = sourceentity.getItemInHand(hand);

		if  (itemstack.getItem() instanceof SpawnEggItem) 
			return InteractionResult.sidedSuccess(this.level().isClientSide());	
		
		return super.mobInteract(sourceentity, hand);
	}
	
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		MonsterCreeperGirl retval = MinepreggoModEntities.MONSTER_CREEPER_GIRL.get().create(serverWorld);
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
}
