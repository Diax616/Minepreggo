package dev.dixmk.minepreggo.world.entity.preggo;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public abstract class PreggoMob extends TamableAnimal {
	public final AnimationState loopAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	
	private final Species typeOfSpecies;
	private final Creature typeOfCreature;
	
	protected PreggoMob(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_, Species typeOfSpecies, Creature typeOfCreature) {
		super(p_21803_, p_21804_);
		this.typeOfSpecies = typeOfSpecies;
		this.typeOfCreature = typeOfCreature;
	}

	public abstract String getSimpleName();
	
	public abstract boolean canBeTamedByPlayer();
	
	public abstract boolean isFoodToTame(ItemStack stack);
	
	public abstract boolean hasCustomHeadAnimation();
	
	public abstract boolean hasJigglePhysics();
	
	protected void afterTaming() {
		
	}
	
	public @NonNull Species getTypeOfSpecies() {
		return this.typeOfSpecies;
	}
	
	public @NonNull Creature getTypeOfCreature() {
		return this.typeOfCreature;
	}
	
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) { // vanilla "swing/attack" animation event
            this.attackAnimationState.start(this.tickCount); 
        }
        else {
            super.handleEntityEvent(id);
        }
    }
	
	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
	}
	
    public boolean isAttacking() {
        return this.attackAnimationState.isStarted();
    }
	
	@Override
	public void tick() {
		super.tick();	
		if (this.level().isClientSide && !this.loopAnimationState.isStarted()) {
			this.loopAnimationState.start(this.tickCount);
		}		
	}
	
    @Override
    public boolean doHurtTarget(Entity target) {
        boolean result = super.doHurtTarget(target);
        if (result) 
            this.level().broadcastEntityEvent(this, (byte)4); // triggers handleEntityEvent client-side     
        return result;
    }
    
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {	
		ItemStack itemstack = sourceentity.getItemInHand(hand);

		if (this.isBaby() || !this.canBeTamedByPlayer() ) {
			return InteractionResult.FAIL;	
		}
		else if (!this.isTame() && this.isFoodToTame(itemstack)) {
			this.usePlayerItem(sourceentity, hand, itemstack);
			if (!this.level().isClientSide && this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
				this.tame(sourceentity);
				this.afterTaming();
				this.level().broadcastEntityEvent(this, (byte) 7);	
				for (EquipmentSlot slot : EquipmentSlot.values()) {
					this.setGuaranteedDrop(slot);
				}
			} else {
				this.level().broadcastEntityEvent(this, (byte) 6);
			}
			this.setPersistenceRequired();
			return InteractionResult.sidedSuccess(this.level().isClientSide());
		} 
		
		return InteractionResult.PASS;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(Entity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}
	
	@Override
	public void remove(RemovalReason reason) {
		if (!this.level().isClientSide && this.hasJigglePhysics()) {
			PreggoMobHelper.removeJigglePhysics(this);
		}
		super.remove(reason);
	}
}
