package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.network.packet.s2c.SexCinematicControlP2MS2CPacket;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import dev.dixmk.minepreggo.world.item.AbstractBreastMilk;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.PacketDistributor;

public class PreggoMobSystem<E extends PreggoMob & ITamablePreggoMob<?>> implements ITamablePreggoMobSystem {
	
	public static final int MIN_FULLNESS_TO_HEAL = 16;
	public static final int MIN_FULLNESS_TO_TAME_AGAIN = 12;
	
	protected final RandomSource randomSource;	
	protected final E preggoMob;
	protected int healingCooldownTimer = 0;
	protected final int totalTicksOfHungry;
	protected final int totalTicksOfSexualAppetive;
	protected final float attackOwnerBeingAngryProbability;
	private int angryTicks = 0;
    private long cinematicEndTime = -1; 
    private ServerPlayer cinematicOwner = null;
    private int angryParticlesTimer = 0;
    
	public PreggoMobSystem(@Nonnull E preggoMob, @Nonnegative int totalTicksOfHungry, @Nonnegative int totalTicksOfSexualAppetitve, float attackOwnerBeingAngryProbability) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
		this.totalTicksOfHungry = totalTicksOfHungry;
		this.totalTicksOfSexualAppetive = totalTicksOfSexualAppetitve;	
		this.attackOwnerBeingAngryProbability = attackOwnerBeingAngryProbability;
	}
	
	public PreggoMobSystem(@Nonnull E preggoMob, @Nonnegative int totalTicksOfHungry, @Nonnegative int totalTicksOfSexualAppetitve) {
		this(preggoMob, totalTicksOfHungry, totalTicksOfSexualAppetitve, PregnancySystemHelper.LOW_ANGER_PROBABILITY);
	}
	
	protected boolean tryStartSavage() {
		final var tamableData = preggoMob.getTamableData();
		if (tamableData.getFullness() <= 0 && !tamableData.isSavage()) {
			this.sendMessageToOwner(Component.translatable("chat.minepreggo.preggo_mob.message.is_savage", preggoMob.getSimpleNameOrCustom()));		
			tamableData.setSavage(true);
			if (preggoMob.isLeashed()) {
				preggoMob.dropLeash(true, true);
			}	
			return true;
		}
		return false;
	}

	protected void evaluateSavage() {		
		if (this.angryParticlesTimer > 200) {
			this.angryParticlesTimer = 0;
			spawnParticles(preggoMob, Result.ANGRY);
		}
		else {
			++this.angryParticlesTimer;
		}
	}
	
	protected void evaluateHealing() {  
		final var tamableData = preggoMob.getTamableData();
	    final var currentHungry = tamableData.getFullness();
		if (currentHungry >= MIN_FULLNESS_TO_HEAL
				&& preggoMob.getHealth() < preggoMob.getMaxHealth()) {     	
			if (healingCooldownTimer >= 20) {
		 	preggoMob.heal(1F);
		 	tamableData.decrementFullness(1);
		 	healingCooldownTimer = 0;
			}
			else {
				++healingCooldownTimer;
			}
		} 
	}
	
	protected void evaluateSexualAppetiteTimer() {	
		final var breedableData = preggoMob.getGenderedData();
		if (breedableData.getSexualAppetite() < IBreedable.MAX_SEXUAL_APPETIVE) {
			if (breedableData.getSexualAppetiteTimer() > totalTicksOfSexualAppetive) {
				breedableData.setSexualAppetiteTimer(0);
				breedableData.incrementSexualAppetite(1);
			}
			else {
				breedableData.incrementSexualAppetiteTimer();
			}
		}
	}
	
	protected void evaluateHungryTimer() {	
		final var tamableData = preggoMob.getTamableData();
	    final var currentHungry = tamableData.getFullness();
	    var currentHungryTimer = tamableData.getHungryTimer();
		    	
	    if (currentHungry <= 0) {
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
        	tamableData.resetHungryTimer();
        	tamableData.decrementFullness(1);
        }
        else {
        	tamableData.setHungryTimer(currentHungryTimer);
        } 
	}
		
	public final void onServerTick() {
		if (preggoMob.level().isClientSide || !preggoMob.isTame()) {
			return;
		}
		
		if (preggoMob.getTamableData().isSavage()) {
			evaluateSavage();
			return;
		}
		else {
			tryStartSavage();
		}
			
		evaluateHungryTimer();
		evaluateHealing();
		evaluateSexualAppetiteTimer();
		evaluateAngry();
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
		
		result = evaluateMilk(level, source);
		
		if (result != null) {
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
		return InteractionResult.PASS;
	}
	
	public boolean canOwnerAccessGUI(Player source) {			
		return preggoMob.isOwnedBy(source)
				&& !preggoMob.isAggressive()
				&& !preggoMob.getTamableData().isSavage()
				&& !preggoMob.isFood(source.getMainHandItem())
				&& !source.getMainHandItem().is(Items.POTION)
				&& !ItemHelper.isMilk(source.getMainHandItem());
	}

	@Nullable
	protected Result evaluateFullness(Level level, Player source) {		
		
	    var mainHandItem = source.getMainHandItem();
		final var tamableData = preggoMob.getTamableData();
	    var currentFullness = tamableData.getFullness();

	    if (currentFullness >= ITamablePreggoMob.MAX_FULLNESS) {
	    	return null;
	    }

        if (preggoMob.isFood(mainHandItem)) {      	           	
        	final var foodProperties = mainHandItem.getFoodProperties(preggoMob);
        	
        	if (foodProperties == null) {
        		return null;
        	}
        	    	    	 
            preggoMob.playSound(SoundEvents.GENERIC_EAT, 0.8F, 0.8F + preggoMob.getRandom().nextFloat() * 0.3F);
        	
            if (!level.isClientSide) {
            	int foodValue = foodProperties.getNutrition();          	
            	mainHandItem.shrink(1);
                source.getInventory().setChanged(); 
                currentFullness += foodValue;          
                tamableData.setFullness(currentFullness);             
                
                if (tamableData.isSavage() && preggoMob.isTame() && currentFullness >= MIN_FULLNESS_TO_TAME_AGAIN) {
                	tamableData.setSavage(false);
                	
        			this.sendMessageToOwner(Component.translatable("chat.minepreggo.preggo_mob.message.is_no_longer_savage", preggoMob.getSimpleNameOrCustom()));		

                	if (preggoMob.getTarget() != null && preggoMob.isOwnedBy(preggoMob.getTarget())) {
						preggoMob.setTarget(null);
					}
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
        	
            preggoMob.playSound(SoundEvents.GENERIC_DRINK, 0.8F, 0.8F + preggoMob.getRandom().nextFloat() * 0.3F);
      	
        	if (!level.isClientSide) {    
                for (MobEffectInstance effect : effects) {
                    // Handle instant effects separately to ensure applyInstantenousEffect is called
                    if (effect.getEffect().isInstantenous()) {
                        effect.getEffect().applyInstantenousEffect(null, null, preggoMob, effect.getAmplifier(), 0.75D);
                    } else {
                        preggoMob.addEffect(new MobEffectInstance(effect));
                    }
                }
                heldStack.shrink(1);
            	ItemHandlerHelper.giveItemToPlayer(source, new ItemStack(Items.GLASS_BOTTLE));                         
        	}                         
            return Result.SUCCESS;
        }

	    return null;
	}

	protected @Nullable Result evaluateMilk(Level level, Player source) {
	    ItemStack heldStack = source.getMainHandItem();
	    
	    if (!ItemHelper.isMilk(heldStack)) {
	    	return null;
	    }
	    
        preggoMob.playSound(SoundEvents.GENERIC_DRINK, 0.8F, 0.8F + preggoMob.getRandom().nextFloat() * 0.3F);

    	if (!level.isClientSide) {   
    		Item temp = heldStack.getItem();
    		heldStack.finishUsingItem(level, preggoMob);
    		heldStack.shrink(1);
    		if (temp instanceof AbstractBreastMilk) {
    			ItemHandlerHelper.giveItemToPlayer(source, new ItemStack(Items.GLASS_BOTTLE));	
    		}
    		else if (temp == Items.MILK_BUCKET) {
    			ItemHandlerHelper.giveItemToPlayer(source, new ItemStack(Items.BUCKET));	
    		}
    	}

    	return Result.SUCCESS;
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
					
		ServerParticleHelper.spawnRandomlyFromServer(preggoMob, particleoptions);
	}

	@Override
	public void onDoHurtTargetSuccessful(Entity source) {
		var tamableData = preggoMob.getTamableData();
		if (source instanceof Player player && !tamableData.isSavage() && preggoMob.isOwnedBy(player) && tamableData.isAngry()) {
			preggoMob.setTarget(null);
			spawnParticles(preggoMob, Result.ANGRY);		
		}	
	}
	
	@Override
	public boolean onHurtSuccessful(DamageSource damagesource) {
		if (damagesource.getEntity() instanceof Player player && preggoMob.isOwnedBy(player) && preggoMob.getTamableData().isSavage()) {
			LivingEntity target = preggoMob.getTarget();
			if (target == null || !target.getUUID().equals(player.getUUID())) {
				preggoMob.setTarget(player);
			}
			return true;
		}	
		return false;
	}
	
	public boolean canBeAngry() {
		return preggoMob.getTamableData().getFullness() <= 4;
	}
	
	protected void evaluateAngry() {
	    final var angry = preggoMob.getTamableData().isAngry();
	    
	    if (!canBeAngry()) {
	        if (angry) {
	        	MinepreggoMod.LOGGER.debug("PreggoMob {} stopped being angry", preggoMob.getSimpleName());
    			this.sendMessageToOwner(Component.translatable("chat.minepreggo.preggo_mob.message.is_no_longer_angry", preggoMob.getSimpleNameOrCustom()));		
	        	preggoMob.getTamableData().setAngry(false);
	            angryTicks = 0;
	            if (preggoMob.getTarget() instanceof Player player && preggoMob.isOwnedBy(player)) {
	            	preggoMob.setTarget(null);
	            }
	        }	        
	        return;
	    }
	    
	    if (!angry) {
	    	MinepreggoMod.LOGGER.debug("PreggoMob {} became angry", preggoMob.getSimpleName());
			this.sendMessageToOwner(Component.translatable("chat.minepreggo.preggo_mob.message.is_angry", preggoMob.getSimpleNameOrCustom()));		
	    	preggoMob.getTamableData().setAngry(true);
	    }    
	    
	    if (angryTicks < 100) {
	    	++angryTicks;
	    	return;
	    }   
	    angryTicks = 0;
	        
	    if (randomSource.nextFloat() < attackOwnerBeingAngryProbability) {
	        Player owner = preggoMob.level().getNearestPlayer(
	        	preggoMob.getX(), 
	            preggoMob.getY(), 
	            preggoMob.getZ(), 
	            144, 
	            entity -> entity instanceof Player livingEntity && preggoMob.isOwnedBy(livingEntity)
	        );
	        
	        if (owner != null && !PlayerHelper.isInvencible(owner) && !LivingEntityHelper.hasValidTarget(preggoMob)) {        	
	        	if (preggoMob instanceof AbstractEnderWoman && owner.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_RECOGNITION.get())) {
	        		return;
	        	}	      		        	
	        	else if (preggoMob instanceof AbstractCreeperGirl creeperGirl) {
	        		var combatMode = creeperGirl.getCombatMode();
	        		if (combatMode == CombatMode.EXPLODE || combatMode == CombatMode.FIGHT_AND_EXPLODE) {
	        			creeperGirl.setCombatMode(CombatMode.DONT_EXPLODE);
	        		}
	        	}
	        	preggoMob.setTarget(owner);
	        }
	    }
	}
	
	private void sendMessageToOwner(Component message) {
		if (preggoMob.getOwner() instanceof ServerPlayer serverPlayer) {
			MessageHelper.sendTo(serverPlayer, message, true);
		}
	}
}