package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.item.IItemCraving;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PreggoMobPregnancySystemP1<
	E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends AbstractPregnancySystem<E> {

	private int pregnancyPainTicks = 0;
	private int pregnancysymptonsTicks = 0;
	
	protected @Nonnegative int totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP1();
	
	protected PreggoMobPregnancySystemP1(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	// It has to be executed on server side
	protected void evaluatePregnancySystem() {
		if (isMiscarriageActive()) {
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel);
			}		
			return;
		}
		
		this.evaluatePregnancyTimer();
		if (canAdvanceNextPregnancyPhase()) {
			advanceToNextPregnancyPhase();
			pregnantEntity.discard();
			return;
		}
		
		if (!hasPregnancyPain()) {
			if (pregnancyPainTicks > 40) {
				tryInitRandomPregnancyPain();
				pregnancyPainTicks = 0;
			}
			else {
				++pregnancyPainTicks;
			}
		}
		else {
			evaluatePregnancyPains();
		}
		
		if (!hasPregnancySymptom()) {
			if (pregnancysymptonsTicks > 40) {
				tryInitPregnancySymptom();
				pregnancysymptonsTicks = 0;
			}
			else {
				++pregnancysymptonsTicks;
			}
		}
		else {
			evaluatePregnancySymptoms();
		}	
		
		evaluatePregnancyEffects();
		
		evaluateAngry(pregnantEntity.level(), pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ(), PregnancySystemHelper.LOW_ANGER_PROBABILITY);		
	}
	
	protected void evaluatePregnancyEffects() {	
		evaluateCravingTimer();	
	}
	
	public final void onServerTick() {
	
		if (pregnantEntity.level().isClientSide) {			
			return;
		}		
		
		evaluatePregnancySystem();
	}
	
	public InteractionResult onRightClick(Player source) {		
		if (!pregnantEntity.isOwnedBy(source) || pregnantEntity.isIncapacitated()) {
			return InteractionResult.FAIL;
		}				
		var level = pregnantEntity.level();

		// Belly rubs has priority over other right click actions
		if (canOwnerRubBelly(source)) {		
			if (!level.isClientSide && level instanceof ServerLevel serverLevel) {		
				PreggoMobSystem.spawnParticles(pregnantEntity, serverLevel, evaluateBellyRubs(serverLevel, source));
			}			
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
					
		Result result = evaluateCraving(level, source);	
		if (result != null) {			
			if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
				PreggoMobSystem.spawnParticles(pregnantEntity, serverLevel, result);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
		return InteractionResult.PASS;
	}
	
	
	
	
	
	
	
	
	protected void evaluatePregnancyTimer() {
        if (pregnantEntity.getPregnancyTimer() >= MinepreggoModConfig.getTotalTicksByPregnancyDay()) {
        	pregnantEntity.resetPregnancyTimer();
        	pregnantEntity.incrementDaysPassed();
        	pregnantEntity.reduceDaysToGiveBirth();
        } else {
        	pregnantEntity.incrementPregnancyTimer();
        }
	}
	
	protected void evaluateMiscarriage(ServerLevel serverLevel) {	    
        if (pregnantEntity.getPregnancyPainTimer() < PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE) {
        	pregnantEntity.setPregnancyPainTimer(pregnantEntity.getPregnancyPainTimer() + 1);	        		        	
    		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
    		    if (player.distanceToSqr(pregnantEntity) <= 512.0) { 
    				serverLevel.sendParticles(player, ParticleTypes.FALLING_DRIPSTONE_LAVA, true, pregnantEntity.getX(), (pregnantEntity.getY() + pregnantEntity.getBbHeight() * 0.35), pregnantEntity.getZ(),
    						1, 0, 1, 0, 0.02);
    		    }
    		}
    		MinepreggoMod.LOGGER.debug("Miscarriage in progress: class={}, timer={}", pregnantEntity.getClass().getSimpleName(), pregnantEntity.getPregnancyPainTimer());
        } else {  
        	final var babyItem = PregnancySystemHelper.getDeadBabyItem(pregnantEntity.getDefaultTypeOfBaby());
        	if (babyItem == null) {
				MinepreggoMod.LOGGER.error("Failed to get dead baby item for miscarriage event. mobId={}, mobClass={}",
						pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
			} else {
				ItemStack droppedItem = new ItemStack(babyItem, IBreedable.getOffspringsByMaxPregnancyStage(pregnantEntity.getLastPregnancyStage()));
				PreggoMobHelper.setItemstackOnOffHand(pregnantEntity, droppedItem);
			}      	
        	initPostMiscarriage();	 
        	pregnantEntity.discard();
        	MinepreggoMod.LOGGER.debug("Miscarriage completed: id={}, class={}", pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
        }	
	}
	
	protected void evaluateCravingTimer() {   				
		if (pregnantEntity.getCraving() < PregnancySystemHelper.MAX_CRAVING_LEVEL
				&& pregnantEntity.getPregnancySymptom() != PregnancySymptom.CRAVING) {
	        if (pregnantEntity.getCravingTimer() >= totalTicksOfCraving) {
	        	pregnantEntity.incrementCraving();
	        	pregnantEntity.resetCravingTimer();
	        }
	        else {
	        	pregnantEntity.incrementCravingTimer();
	        }
		}	
	}
	
	protected void evaluateAngry(Level level, double x, double y, double z, final float angerProbability) {
	    if (!pregnantEntity.isAngry() && this.canBeAngry()) {
	    	pregnantEntity.setAngry(true);
	    } else {
	        if (!PreggoMobHelper.hasValidTarget(pregnantEntity) && randomSource.nextFloat() < angerProbability) {
	            final Vec3 center = new Vec3(x, y, z);      
	            var players = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(12), pregnantEntity::isOwnedBy);
	                  
	            if (!players.isEmpty()) {
	            	var owner = players.get(0);
		            if (!PreggoMobHelper.isPlayerInCreativeOrSpectator(owner)) {
		            	pregnantEntity.setTarget(owner);
		            } 
	            }
	        }
	    }
	}

	protected void evaluatePregnancyPains() {
		if (pregnantEntity.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS) {
				pregnantEntity.clearPregnancyPain();
				pregnantEntity.resetPregnancyPainTimer();
			} else {
				pregnantEntity.incrementPregnancyPainTimer();
			}
		}
	}
	
	protected void evaluatePregnancySymptoms() {	
		if (pregnantEntity.getPregnancySymptom() == PregnancySymptom.CRAVING && pregnantEntity.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
			pregnantEntity.clearPregnancySymptom();
			pregnantEntity.clearTypeOfCraving();
		}
	}
	
	public void evaluateOnSuccessfulHurt(DamageSource damagesource) {	
		if ((pregnantEntity.hasEffect(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get()) && randomSource.nextFloat() < 0.9F)
				|| (!damagesource.is(DamageTypes.FALL) && !pregnantEntity.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && randomSource.nextFloat() < 0.5)) {
			return;
		}
		
		int damage = 0;
		var currentPregnancyHealth = pregnantEntity.getPregnancyHealth();
		
		if (pregnantEntity.getHealth() < pregnantEntity.getMaxHealth() / 2F) {
			damage = pregnantEntity.getCurrentPregnancyStage().ordinal() + randomSource.nextInt(3);
				
			if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
				damage *= 2;
			}

			currentPregnancyHealth = Math.max(0, currentPregnancyHealth - damage);
			pregnantEntity.setPregnancyHealth(currentPregnancyHealth);			
		} 
		else if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
			currentPregnancyHealth = Math.max(0, currentPregnancyHealth - 5);
			pregnantEntity.setPregnancyHealth(currentPregnancyHealth);
		}
		
		if (damage > 0) {		
			if (pregnantEntity.getPregnancyHealth() < 40) {
				MessageHelper.warningOwnerPossibleMiscarriageEvent(pregnantEntity);
			}					
			MinepreggoMod.LOGGER.debug("PREGNANCY HEALTH: id={}, class={}, pregnancyHealth={}, damage={}, damageSource={}",
					pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName(), currentPregnancyHealth, damage, damagesource);
		}
	}
	
	public boolean isMiscarriageActive() {
	    return pregnantEntity.getPregnancyPain() == PregnancyPain.MISCARRIAGE;
	}
	
	public boolean canAdvanceNextPregnancyPhase() {
	    return pregnantEntity.getDaysPassed() >= pregnantEntity.getDaysByStage();
	}
	
	public boolean canTriggerMiscarriage() {
	    return pregnantEntity.getPregnancyHealth() <= 0;
	}
	
	public boolean hasPregnancyPain() {
	    return pregnantEntity.getPregnancyPain() != null;
	}
	
	public boolean hasPregnancySymptom() {
	    return pregnantEntity.getPregnancySymptom() != null;
	}
	
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
	        pregnantEntity.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        pregnantEntity.resetPregnancyPainTimer();
	        return true;
	    }
	    return false;
	}
	
	protected boolean tryInitPregnancySymptom() {
		if (pregnantEntity.getCraving() >= PregnancySystemHelper.ACTIVATE_CRAVING_SYMPTOM) {
	    	pregnantEntity.setPregnancySymptom(PregnancySymptom.CRAVING);
	    	pregnantEntity.setTypeOfCraving(PregnancySystemHelper.getRandomCraving(randomSource));
	    	return true;		
		}
	    return false;
	}
	
	@Nullable
	protected Result evaluateCraving(Level level, Player source) {
		if (pregnantEntity.getPregnancySymptom() != PregnancySymptom.CRAVING) {
			return null;
		}
		
	    var mainHandItem = source.getMainHandItem().getItem();
	    var currentCraving = pregnantEntity.getCraving();
	    
	    if (currentCraving > PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {    	
	    	if (!pregnantEntity.isValidCraving(mainHandItem) || !(mainHandItem instanceof IItemCraving)) {
	    		return Result.ANGRY;
	    	}
	    	    	
    		source.getInventory().clearOrCountMatchingItems(p -> mainHandItem == p.getItem(), 1, source.inventoryMenu.getCraftSlots());         
            pregnantEntity.setCraving(currentCraving - ((IItemCraving)mainHandItem).getGratification());    

            if (!level.isClientSide) {
            	level.playSound(null, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	          
            }
            
            return Result.SUCCESS; 
	    }
	    
	    return null;
	}
	
	public boolean canBeAngry() {
		return pregnantEntity.getCraving() >= 20 || pregnantEntity.getFullness() <= 2;
	}

	
	public boolean canOwnerRubBelly(Player source) {
		return source.isShiftKeyDown()
				&& source.getMainHandItem().isEmpty() 
				&& source.getDirection() == pregnantEntity.getDirection().getOpposite()
				&& pregnantEntity.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
	}
	
	
	@Nullable
	protected Result evaluateBellyRubs(Level level, Player source) {
		// In this pregnancy phase, the belly is not large enough to do some action
		return Result.FAIL;
	}
	
	@Override
	protected final boolean hasToGiveBirth() {
		return pregnantEntity.getLastPregnancyStage() == pregnantEntity.getCurrentPregnancyStage();
	}
	
	@Override
	protected boolean isInLabor() {
		final var pain = pregnantEntity.getPregnancyPain();
		return pain == PregnancyPain.PREBIRTH || pain == PregnancyPain.BIRTH;
 	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {	
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void startLabor() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void initPostPartum() {
		// This pregnancy phase does not support birth yet
	}
}