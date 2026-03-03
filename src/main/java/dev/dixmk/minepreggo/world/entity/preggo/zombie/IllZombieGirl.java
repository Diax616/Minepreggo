package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoEntities;
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import net.minecraftforge.network.PlayMessages;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

public class IllZombieGirl extends AbstractHostileZombieGirl implements Ill {
	
	public IllZombieGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoEntities.ILL_ZOMBIE_GIRL.get(), world);
	}

	public IllZombieGirl(EntityType<IllZombieGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}

	@Override
	public void tameByIllager(ScientificIllager illagerScientific) {
		this.setOwnerUUID(illagerScientific.getUUID());
		this.setTame(true);
	}
	
	@Override
	@Nullable
	public LivingEntity getOwner() {	
        if (this.getOwnerUUID() == null || this.level().isClientSide) return null;
        return (LivingEntity) ((ServerLevel) this.level()).getEntity(this.getOwnerUUID());
	}
	
	@Override
	public void removeIllagerOwner() {
    	this.setTame(false);
		this.setOwnerUUID(null);
		Ill.addBehaviourGoalsWhenOwnerDies(this);
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);
		if (this.getOwner() instanceof ScientificIllager owner && owner.isAlive() && !this.level().isClientSide) {
			owner.removePet(this.getUUID());
		}		
	}
	
	@Override
	public boolean isAlliedTo(Entity other) {
	    if (other instanceof Ill || other instanceof AbstractIllager) 
	        return true;    
	    return super.isAlliedTo(other);
	}
	
	@Override
	protected void registerGoals() {
		Ill.addBehaviourGoals(this);
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));	
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));		
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
	}

	@Override
	protected void reassessTameGoals() {
		if (this.isTame()) {
	    	Ill.addTamableBehaviourGoals(this);
		} else {
			Ill.removeTamableBehaviourGoals(this);
		}
	}
	
	@Override
	public ItemStack getPickResult() {
	    return ItemStack.EMPTY;
	}
	
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag dataTag) {
		if (spawnGroupData instanceof Ill.IllGroupData illGroupData && illGroupData.hasOwner()) {	
			ItemStack helmet = this.getItemBySlot(EquipmentSlot.HEAD);
			if (helmet.isEmpty()) {
				this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ItemHelper.getRandomHelmet(this.random)));
			}
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
		}
		return super.finalizeSpawn(level, difficulty, spawnType, null, dataTag);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractHostileZombieGirl.getBasicAttributes(0.235);
	}
}
