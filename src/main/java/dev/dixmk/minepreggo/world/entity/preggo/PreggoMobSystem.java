package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.particle.ParticleHelper;
import dev.dixmk.minepreggo.network.packet.SexCinematicControlP2MS2CPacket;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.world.pregnancy.AbstractBreedableEntity;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.PacketDistributor;

public class PreggoMobSystem<E extends PreggoMob & ITamablePreggoMob<?>> {
	
	public static final int MIN_FULLNESS_TO_HEAL = 16;
	public static final int MIN_FULLNESS_TO_TAME_AGAIN = 12;
	
	protected final RandomSource randomSource;	
	protected final E preggoMob;
	protected int autoFeedingCooldownTimer = 0;
	protected int healingCooldownTimer = 0;
	protected final int totalTicksOfHungry;
	protected final int totalTicksOfSexualAppetive;
	
    private long cinematicEndTime = -1; 
    private ServerPlayer cinematicOwner = null;
    
    private final AbstractBreedableEntity abstractBreedableEntity;
	
	public PreggoMobSystem(@Nonnull E preggoMob, @Nonnegative int totalTicksOfHungry, @Nonnegative int totalTicksOfSexualAppetitve) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
		this.totalTicksOfHungry = totalTicksOfHungry;
		this.totalTicksOfSexualAppetive = totalTicksOfSexualAppetitve;
		this.abstractBreedableEntity = preggoMob.getGenderedData();
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
			if (healingCooldownTimer >= 40) {
		 	preggoMob.heal(1F);
		 	preggoMob.setFullness(currentHungry - 1);
		 	healingCooldownTimer = 0;
			}
			else {
				++healingCooldownTimer;
			}
		} 
	}
	
	protected void evaluateSexualAppetiteTimer() {
		if (abstractBreedableEntity.getSexualAppetiteTimer() > totalTicksOfSexualAppetive
				&& abstractBreedableEntity.getSexualAppetite() < IBreedable.MAX_SEXUAL_APPETIVE) {
			abstractBreedableEntity.setSexualAppetiteTimer(0);
			abstractBreedableEntity.incrementSexualAppetite(1);
		}
		else {
			abstractBreedableEntity.incrementSexualAppetiteTimer();
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
		
	public final void onServerTick() {
		final var level = preggoMob.level();
		
		if (level.isClientSide) {
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
		evaluateSexualAppetiteTimer();
	}
	
	public InteractionResult onRightClick(Player source) {
		if (!preggoMob.isOwnedBy(source)) {
			return InteractionResult.PASS;
		}		
		
		var level = preggoMob.level();		
		Result result = evaluateFullness(level, source);
		
		if (result != null) {
			spawnParticles(preggoMob, result);	
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
					
		result = evaluatePotions(level, source);
			
		if (result != null) {
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
		return InteractionResult.PASS;
	}
	
	public boolean canOwnerAccessGUI(Player source) {			
		return preggoMob.isOwnedBy(source)
				&& !preggoMob.isAggressive()
				&& !preggoMob.isSavage()
				&& !preggoMob.isFood(source.getMainHandItem())
				&& !source.getMainHandItem().is(Items.POTION);
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
        	    	    	        	      
            if (!level.isClientSide) {
            	int foodValue = foodProperties.getNutrition();          	
            	mainHandItem.shrink(1);
                source.getInventory().setChanged(); 
                currentFullness += foodValue;          
                preggoMob.setFullness(currentFullness);             
                preggoMob.playSound(SoundEvents.GENERIC_EAT, 0.8F, 0.8F + preggoMob.getRandom().nextFloat() * 0.3F);
                
                if (preggoMob.isSavage() && preggoMob.isTame() && currentFullness >= MIN_FULLNESS_TO_TAME_AGAIN) {
                	preggoMob.setSavage(false);
                } 
            }

            return Result.SUCCESS;       	        
        } 
	      
	    return null;		
	}
	
	protected @Nullable Result evaluatePotions(Level level, Player source) {		
	    ItemStack heldStack = source.getMainHandItem();
	    	    
	    if (!heldStack.is(Items.POTION)) {
	    	return null;
	    }
	        
    	var effects = PotionUtils.getMobEffects(heldStack);
        if (!effects.isEmpty()) { 
        	if (!level.isClientSide) {    
                for (MobEffectInstance effect : effects) {
                    preggoMob.addEffect(new MobEffectInstance(effect));
                }
                preggoMob.playSound(SoundEvents.GENERIC_DRINK, 0.8F, 0.8F + preggoMob.getRandom().nextFloat() * 0.3F);
            	ItemHandlerHelper.giveItemToPlayer(source, new ItemStack(Items.GLASS_BOTTLE));
                heldStack.shrink(1);          
        	}                         
            return Result.SUCCESS;
        }

	    return null;
	}

	// Cinematic START
    public void setCinematicOwner(ServerPlayer player) { 
    	this.cinematicOwner = player;
    }
   
    public void setCinematicEndTime(long time) { 
    	this.cinematicEndTime = time; 
    }
	
	public void cinematicTick() {
        if (cinematicEndTime > 0 && preggoMob.level().getGameTime() >= cinematicEndTime) {
            endCinematic();
        }
	}
	
	private void endCinematic() {
        if (cinematicOwner != null) {
        	ServerCinematicManager.getInstance().end(cinematicOwner);
            MinepreggoModPacketHandler.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> cinematicOwner),
                new SexCinematicControlP2MS2CPacket(false, preggoMob.getId())
            );
        }
        cinematicOwner = null;
        cinematicEndTime = -1;
    }
	// Cinematic END
	
	protected enum Result {
		ANGRY,
		FAIL,
		SUCCESS
	}	
	
	public static<E extends TamableAnimal & ITamablePreggoMob<?>> void spawnParticles(E preggoMob, Result result) {
		ParticleOptions particleoptions;
			
		if (result == Result.SUCCESS)
			particleoptions = ParticleTypes.HEART;
		else if (result == Result.FAIL)
			particleoptions = ParticleTypes.SMOKE;
		else if (result == Result.ANGRY)
			particleoptions = ParticleTypes.ANGRY_VILLAGER;
		else 
			return;
					
		ParticleHelper.spawnRandomlyFromServer(preggoMob, particleoptions);
	}
}