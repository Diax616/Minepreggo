package dev.dixmk.minepreggo.world.entity.preggo.ender;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.item.ItemHelper;

public class IllMonsterEnderWoman extends AbstractHostilMonsterEnderWoman implements Ill {
	
	public IllMonsterEnderWoman(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.ILL_ENDER_WOMAN.get(), world);
	}

	public IllMonsterEnderWoman(EntityType<IllMonsterEnderWoman> type, Level world) {
		super(type, world, Creature.MONSTER);
		xpReward = 12;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
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
		
		this.setItemSlot(EquipmentSlot.HEAD, helmet);
		if (this.random.nextFloat() < 0.3F) {
			this.setCarriedBlock(Blocks.DARK_OAK_WOOD.defaultBlockState());
		}
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);
		if (this.getOwner() instanceof ScientificIllager owner && owner.isAlive() && !this.level().isClientSide) {
			owner.removePet(this.getUUID());
		}		
	}
	
	@Override
    protected boolean shouldRandomlyTeleport() {
    	return !this.isTame() || this.getOwner() == null;
    }
	
	@Override
	public boolean isAlliedTo(Entity other) {
	    if (other instanceof Ill || other instanceof AbstractIllager) 
	        return true;    
	    return super.isAlliedTo(other);
	}
	
    @Override
    protected void registerGoals() {
    	this.addBehaviourGoals();
		this.goalSelector.addGoal(5, new AbstractEnderWoman.EnderWomanTeleportToTargetGoal(this, 196F, 25F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));  
    }
    
	@Override
	protected void reassessTameGoals() {
		if (this.isTame()) {
	    	Ill.addTamableBehaviourGoals(this);
			GoalHelper.addGoalWithReplacement(this, 7, new AbstractEnderWoman.EnderWomanTeleportToOwnerGoal(this, 196F, 81F));
		} else {
			Ill.removeTamableBehaviourGoals(this);
			GoalHelper.removeGoalByClass(this.goalSelector, AbstractEnderWoman.EnderWomanTeleportToOwnerGoal.class);
		}
	}
    
	@Override
	public ItemStack getPickResult() {
	    return ItemStack.EMPTY;
	}
    
    public static AttributeSupplier.Builder createAttributes() {
        return MonsterEnderWomanHelper.createBasicAttributes(0.35D);
	}

	@Override
	public boolean isFoodToTame(ItemStack stack) {
		return false;
	}

	@Override
	public boolean hasCustomHeadAnimation() {
		return false;
	}
}
