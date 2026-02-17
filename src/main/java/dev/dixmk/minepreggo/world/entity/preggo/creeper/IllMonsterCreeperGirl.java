package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

public class IllMonsterCreeperGirl extends AbstractHostileMonsterCreeperGirl implements Ill {

	public IllMonsterCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), world);
	}

	public IllMonsterCreeperGirl(EntityType<IllMonsterCreeperGirl> type, Level world) {
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
	public boolean canExplode() {
		return this.getOwner() == null || !this.isTame();
	}

	@Override
	public void tameByIllager(ScientificIllager illagerScientific) {
		this.setOwnerUUID(illagerScientific.getUUID());
		this.setTame(true);
	}
	
	@Override
	public void removeIllagerOwner() {
		this.setOwnerUUID(null);
    	this.setTame(false);	
		Ill.addBehaviourGoalsWhenOwnerDies(this);
	}
	
	@Override
	@Nullable
	public LivingEntity getOwner() {	
        if (this.getOwnerUUID() == null || this.level().isClientSide) return null;
        return (LivingEntity) ((ServerLevel) this.level()).getEntity(this.getOwnerUUID());
	}
	
	@Override
	public boolean isAlliedTo(Entity other) {
	    if (other instanceof Ill || other instanceof AbstractIllager) 
	        return true;    
	    return super.isAlliedTo(other);
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);
		if (this.getOwner() instanceof ScientificIllager owner && owner.isAlive() && !this.level().isClientSide) {
			owner.removePet(this.getUUID());
		}		
	}
	
	@Override
	public void onFinalizeSpawnWithOwner() {
		if (this.random.nextFloat() < 0.15F) {
			this.setPower(true);
		}
	}
	
	@Override
	protected void registerGoals() {	
		Ill.addBehaviourGoals(this);
		IllHumanoidCreeperGirl.addDefaultGoals(this);
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
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {					
		return IllHumanoidCreeperGirl.mobInteract(this, sourceentity, hand);
	}
	
	@Override
	public ItemStack getPickResult() {
	    return ItemStack.EMPTY;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return MonsterCreeperHelper.createBasicAttributes(0.25);
	}
}
