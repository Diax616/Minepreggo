package dev.dixmk.minepreggo.world.entity.preggo.ender;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntityType;
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
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;

public class IllEnderWoman extends AbstractMonsterEnderWoman implements Ill {
	
	public IllEnderWoman(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.ILL_ENDER_WOMAN.get(), world);
	}

	public IllEnderWoman(EntityType<IllEnderWoman> type, Level world) {
		super(type, world);
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
		Ill.removeBehaviourGoals(this);
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
    	Ill.addBehaviourGoals(this);
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));  
    }
    
    public static AttributeSupplier.Builder createAttributes() {
        return createBasicAttributes(0.35D);
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
