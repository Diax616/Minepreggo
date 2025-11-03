package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Comparator;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SexCinematicControlPacket;
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
import net.minecraft.world.entity.Entity;
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
	
	public static final int MIN_HUNGRY_TO_HEAL = 16;
	public static final int MIN_HUNGRY_TO_TAME_AGAIN = 12;
	
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
	
    public void setCinematicOwner(ServerPlayer player) { this.cinematicOwner = player; }
   
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
                new SexCinematicControlPacket(false, preggoMob.getId())
            );
        }
        cinematicOwner = null;
        cinematicEndTime = -1;
    }
	
	
	protected void evaluateHungryTimer(Level level, double x, double y, double z) {

	    final var currentHungry = preggoMob.getHungry();
	    final var currentHungryTimer = preggoMob.getHungryTimer();
	      
	    if (!preggoMob.isSavage()) {
	        if (currentHungry > 0) {     	
	            int timerIncrement = 1;
	            if (preggoMob.getDeltaMovement().x() != 0 || preggoMob.getDeltaMovement().z() != 0) {
	                timerIncrement += 1;              
		            if (preggoMob.isInWater()) {
		                timerIncrement += 2;
		            }
	            }
	            
	            if (currentHungryTimer >= totalTicksOfHungry) {
	            	preggoMob.setHungryTimer(0);
	            	preggoMob.setHungry(currentHungry + 1);
	            }
	            else {
	            	preggoMob.setHungryTimer(currentHungryTimer + timerIncrement);
	            }
	                    
	            if (currentHungry >= MIN_HUNGRY_TO_HEAL
	            		&& preggoMob.getHealth() < preggoMob.getMaxHealth()) {     	
	            	if (healingCooldownTimer >= 60) {
		            	preggoMob.heal(1F);
		            	preggoMob.setHungry(currentHungry - 1);
		            	healingCooldownTimer = 0;
	            	}
	            	else {
	            		++healingCooldownTimer;
	            	}
	            }            
	        } 
	        else {
	        	preggoMob.setSavage(true);
	        }
	    } else {
	        if (preggoMob.isTame() && !PreggoMobHelper.hasValidTarget(preggoMob)) {
	            final var center = new Vec3(x, y, z);
	        		
	            Player player = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(12), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null);
	        	
	        	if (player != null && !PreggoMobHelper.isPlayerInCreativeOrSpectator(player))
	        		preggoMob.setTarget(player);
	        }
	    }
	}
		
	protected boolean canFeedHerself() {
		final var currentHungry = preggoMob.getHungry();
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
		
		preggoMob.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {		
			ItemStack food = capability.extractItem(ITamablePreggoMob.FOOD_INVENTORY_SLOT, 1, false);
			
			if (food.isEmpty()) {
				return;
			}
			
			final var foodProperties = food.getFoodProperties(preggoMob);
			
			if (foodProperties == null) {
				return;
			}
			
			preggoMob.setHungry(Math.min(preggoMob.getHungry() + foodProperties.getNutrition(), 25));	
	        level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	          	
		
			MinepreggoMod.LOGGER.debug("AUTO FEEDING: id={}, class={}, food={}",
					preggoMob.getId(), preggoMob.getClass().getSimpleName(), food.getDisplayName().getString());
		
			autoFeedingCooldownTimer = 0;			
		});
	}
	
	public void evaluateOnTick() {
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {
			return;
		}
			
		this.evaluateHungryTimer(level, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ());
		this.evaluateAutoFeeding(level);
	}
	
	public InteractionResult evaluateRightClick(Player source) {			
		final var level = preggoMob.level();
		
		if (!preggoMob.isOwnedBy(source) || level.isClientSide()) {
			return InteractionResult.PASS;
		}	
		
		Result result;
		
		if ((result = this.evaluateHungry(level, source)) != Result.NOTHING && level instanceof ServerLevel serverLevel) {
			spawnParticles(preggoMob, serverLevel, result);
		}
			
		return onRightClickResult(result);	
	}
	
	public boolean canOwnerAccessGUI(Player source) {			
		return preggoMob.isOwnedBy(source)
				&& !preggoMob.isAggressive()
				&& !preggoMob.isSavage()
				&& !preggoMob.isFood(source.getMainHandItem());
	}
	
	protected Result evaluateHungry(Level level, Player source) {		
		
	    var mainHandItem = source.getMainHandItem();
	    var currentHunger = preggoMob.getHungry();
    
	    if (currentHunger < 20) {
	        int foodValue = 0;

	        if (preggoMob.isFood(mainHandItem)) {      	           	
	        	final var foodProperties = mainHandItem.getFoodProperties(preggoMob);
	        	
	        	if (foodProperties == null) {
	        		return Result.NOTHING;
	        	}
	        	
	        	foodValue = foodProperties.getNutrition();     
	        	
		        if (foodValue > 0) {      	

	                source.getInventory().clearOrCountMatchingItems(p -> mainHandItem.getItem() == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
	                currentHunger += foodValue;          
	                preggoMob.setHungry(Math.min(currentHunger, 25));
		        	        	
	                level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	          	
	      
	                if (preggoMob.isSavage() && preggoMob.isTame() && currentHunger >= MIN_HUNGRY_TO_TAME_AGAIN) {
	                	preggoMob.setSavage(false);
	                } 
		        	
		            return Result.SUCCESS;
		        }	       	        
	        }      
	    }
	    
	    return Result.NOTHING;		
	}
	
	public static final InteractionResult onRightClickResult(Result result) {
		if (result == Result.SUCCESS) {
			return InteractionResult.SUCCESS;
		}
		else if (result == Result.NOTHING) {
			return InteractionResult.PASS;
		}
		else {
			return InteractionResult.CONSUME;
		}
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
		    if (player.distanceToSqr(preggoMob) <= 1024.0) { // 32 blocks
				serverLevel.sendParticles(player, particleoptions, true, preggoMob.getRandomX(1.0), preggoMob.getRandomY() + 0.5, preggoMob.getRandomZ(1.0),
						7, serverLevel.random.nextGaussian() * 0.3, serverLevel.random.nextGaussian() * 0.5, serverLevel.random.nextGaussian() * 0.3, 0.02);
		    }
		}
	}
	
	
	
	protected enum Result {
		ANGRY,
		FAIL,
		PROCESS,
		NOTHING,
		SUCCESS
	}
}

