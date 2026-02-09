package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

public class IllZombieGirl extends AbstractHostilZombieGirl implements Ill {
	
	public IllZombieGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.ILL_ZOMBIE_GIRL.get(), world);
	}

	public IllZombieGirl(EntityType<IllZombieGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}

	@Override
	public void tameByIllager(ScientificIllager illagerScientific) {
		this.setTame(true);
		this.setOwnerUUID(illagerScientific.getUUID());
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
	public void onFinalizeSpawnWithOwner() {
		var helmet = new ItemStack(ItemHelper.getRandomHelmet(this.random));
		helmet.setDamageValue(this.random.nextInt(helmet.getMaxDamage() / 2));
		var axe = new ItemStack(ItemHelper.getRandomAxe(this.random));
		axe.setDamageValue(this.random.nextInt(axe.getMaxDamage() / 2));
		
		this.setItemSlot(EquipmentSlot.HEAD, helmet);
		this.setItemSlot(EquipmentSlot.MAINHAND, axe);
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
		Ill.addTamableBehaviourGoals(this);
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));	
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));		
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(11, new FloatGoal(this));
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
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractHostilZombieGirl.getBasicAttributes(0.235);
	}
}
