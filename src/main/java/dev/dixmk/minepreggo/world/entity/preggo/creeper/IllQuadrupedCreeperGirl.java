package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

public class IllQuadrupedCreeperGirl extends AbstractMonsterQuadrupedCreeperGirl implements Ill {

	public IllQuadrupedCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.ILL_QUADRUPED_CREEPER_GIRL.get(), world);
	}

	public IllQuadrupedCreeperGirl(EntityType<IllQuadrupedCreeperGirl> type, Level world) {
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
		this.setTame(true);
		this.setOwnerUUID(illagerScientific.getUUID());
	}
	
	@Override
	public void removeIllagerOwner() {
    	this.setTame(false);	
		this.setOwnerUUID(null);
		Ill.removeBehaviourGoals(this);
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
	protected void registerGoals() {	
		IllCreeperGirl.addDefaultGoals(this);
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {					
		return IllCreeperGirl.mobInteract(this, sourceentity, hand);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractMonsterQuadrupedCreeperGirl.getBasicAttributes(0.25);
	}
}
