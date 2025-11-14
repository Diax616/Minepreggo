package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SexCinematicControlS2CPacket;
import dev.dixmk.minepreggo.server.ServerSexCinematicManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

public class PreggoMobSystem<E extends PreggoMob & ITamablePreggoMob> {
	
	public static final int MIN_FULLNESS_TO_HEAL = 16;
	public static final int MIN_FULLNESS_TO_TAME_AGAIN = 12;
	
	protected final RandomSource randomSource;	
	protected final E preggoMob;
	protected int autoFeedingCooldownTimer = 0;
	protected int healingCooldownTimer = 0;
	protected final int totalTicksOfHungry;
	
    private long cinematicEndTime = -1; 
    private ServerPlayer cinematicOwner = null;

	
	public PreggoMobSystem(@Nonnull E preggoMob, int totalTicksOfHungry) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
		this.totalTicksOfHungry = totalTicksOfHungry;
	}
	
	protected boolean tryStartSavage() {		
		if (preggoMob.getFullness() <= 0) {
			preggoMob.setSavage(true);
			return true;
		}
		return false;
	}

	protected void evaluateSavage(Level level) {		
		if (preggoMob.getTarget() == null && preggoMob.isTame()) {
	        final Vec3 center = new Vec3(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ());      
	        var player = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(32)).stream()
	        		.sorted((p1, p2) -> Double.compare(p1.distanceToSqr(preggoMob), p2.distanceToSqr(preggoMob)))
	        		.findFirst();          
	        player.ifPresent(preggoMob::setTarget);        
		}       
	}
	
	protected void evaluateHealing() {  
	    final var currentHungry = preggoMob.getFullness();
		if (currentHungry >= MIN_FULLNESS_TO_HEAL
				&& preggoMob.getHealth() < preggoMob.getMaxHealth()) {     	
			if (healingCooldownTimer >= 60) {
		 	preggoMob.heal(1F);
		 	preggoMob.setFullness(currentHungry - 1);
		 	healingCooldownTimer = 0;
			}
			else {
				++healingCooldownTimer;
			}
		} 
	}
	
	protected void evaluateHungryTimer() {			
	    final var currentHungry = preggoMob.getFullness();
	    var currentHungryTimer = preggoMob.getHungryTimer();
		    	
	    if (currentHungry >= ITamablePreggoMob.MAX_FULLNESS) {
	    	return;
	    }
	    
        int timerIncrement = 1;
        if (preggoMob.getDeltaMovement().x() != 0 || preggoMob.getDeltaMovement().z() != 0) {
            timerIncrement += 1;              
            if (preggoMob.isInWater()) {
                timerIncrement += 2;
            }
        }
       
        currentHungryTimer += timerIncrement;
        
        if (currentHungryTimer >= totalTicksOfHungry) {
        	preggoMob.resetHungryTimer();
        	preggoMob.incrementFullness(1);
        }
        else {
        	preggoMob.setHungryTimer(currentHungryTimer);
        } 
	}
		
	public boolean canFeedHerself() {
		final var currentHungry = preggoMob.getFullness();
		return (currentHungry < 10 || (preggoMob.getHealth() < 20 && currentHungry < 18)) && !preggoMob.isAggressive();
	}
	
	protected void evaluateAutoFeeding(Level level) {
	
		if (!this.canFeedHerself()) {
			return;
		}
				
		if (autoFeedingCooldownTimer < 40) {
			++autoFeedingCooldownTimer;
			return;
		}
		
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(capability -> {		
			ItemStack food = capability.extractItem(ITamablePreggoMob.FOOD_INVENTORY_SLOT, 1, false);
			
			if (food.isEmpty()) {
				return;
			}
			
			final var foodProperties = food.getFoodProperties(preggoMob);
			
			if (foodProperties == null) {
				return;
			}
			
			preggoMob.incrementFullness(foodProperties.getNutrition());	
	        level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	          	
		
			MinepreggoMod.LOGGER.debug("AUTO FEEDING: id={}, class={}, food={}",
					preggoMob.getId(), preggoMob.getClass().getSimpleName(), food.getDisplayName().getString());
		
			autoFeedingCooldownTimer = 0;			
		});
	}
	
	public final void onServerTick() {
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {
			return;
		}
		
		if (preggoMob.isSavage()) {
			evaluateSavage(level);
			return;
		}
		else {
			tryStartSavage();
		}
			
		evaluateHungryTimer();
		evaluateHealing();
		evaluateAutoFeeding(level);
	}
	
	
	
	
	public InteractionResult onRightClick(Player source) {
		if (!preggoMob.isOwnedBy(source)) {
			return InteractionResult.PASS;
		}			
		var level = preggoMob.level();		
		Result result = evaluateFullness(level, source);
		
		if (result == null) {
			return InteractionResult.PASS;
		}
				
		if (level instanceof ServerLevel serverLevel) {
			spawnParticles(preggoMob, serverLevel, result);
		}	
		return InteractionResult.sidedSuccess(level.isClientSide);
	}
	
	public boolean canOwnerAccessGUI(Player source) {			
		return preggoMob.isOwnedBy(source)
				&& !preggoMob.isAggressive()
				&& !preggoMob.isSavage()
				&& !preggoMob.isFood(source.getMainHandItem());
	}
	
	@Nullable
	protected Result evaluateFullness(Level level, Player source) {		
		
	    var mainHandItem = source.getMainHandItem();
	    var currentFullness = preggoMob.getFullness();
    
	    if (currentFullness >= ITamablePreggoMob.MAX_FULLNESS) {
	    	return null;
	    }

        if (preggoMob.isFood(mainHandItem)) {      	           	
        	final var foodProperties = mainHandItem.getFoodProperties(preggoMob);
        	
        	if (foodProperties == null) {
        		return null;
        	}
        	    	
        	int foodValue = foodProperties.getNutrition();          	
            source.getInventory().clearOrCountMatchingItems(p -> mainHandItem.getItem() == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
            currentFullness += foodValue;          
            preggoMob.setFullness(currentFullness);
        	        	      
            if (!level.isClientSide) {
                level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	          	
                 
                if (preggoMob.isSavage() && preggoMob.isTame() && currentFullness >= MIN_FULLNESS_TO_TAME_AGAIN) {
                	preggoMob.setSavage(false);
                } 
            }

            return Result.SUCCESS;       	        
        } 
	      
	    return null;		
	}
	
	public static<E extends TamableAnimal & ITamablePreggoMob> void spawnParticles(E preggoMob, ServerLevel serverLevel, Result result) {

		ParticleOptions particleoptions;
			
		if (result == Result.SUCCESS)
			particleoptions = ParticleTypes.HEART;
		else if (result == Result.FAIL)
			particleoptions = ParticleTypes.SMOKE;
		else if (result == Result.ANGRY)
			particleoptions = ParticleTypes.ANGRY_VILLAGER;
		else 
			return;
					
		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
		    if (player.distanceToSqr(preggoMob) <= 512.0) {
				serverLevel.sendParticles(player, particleoptions, true, preggoMob.getRandomX(1.0), preggoMob.getRandomY() + 1, preggoMob.getRandomZ(1.0),
						7, serverLevel.random.nextGaussian() * 0.3, serverLevel.random.nextGaussian() * 0.5, serverLevel.random.nextGaussian() * 0.3, 0.02);
		    }
		}
	}
	
    public void setCinematicOwner(ServerPlayer player) { 
    	this.cinematicOwner = player;
    }
   
    public void setCinematicEndTime(long time) { this.cinematicEndTime = time; }
	
    
	public void cinematicTick() {
        if (cinematicEndTime > 0 && preggoMob.level().getGameTime() >= cinematicEndTime) {
            endCinematic();
        }
	}
	
	private void endCinematic() {
        if (cinematicOwner != null && ServerSexCinematicManager.isInCinematic(cinematicOwner)) {
            ServerSexCinematicManager.end(cinematicOwner);
            preggoMob.setState(PreggoMobState.IDLE);
            MinepreggoModPacketHandler.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> cinematicOwner),
                new SexCinematicControlS2CPacket(false, preggoMob.getId())
            );
        }
        cinematicOwner = null;
        cinematicEndTime = -1;
    }
	
	protected enum Result {
		ANGRY,
		FAIL,
		SUCCESS
	}
}

