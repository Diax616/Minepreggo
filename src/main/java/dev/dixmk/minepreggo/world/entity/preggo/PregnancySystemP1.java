package dev.dixmk.minepreggo.world.entity.preggo;

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
import net.minecraft.util.RandomSource;
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

public abstract class PregnancySystemP1<
	E extends PreggoMob & ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> {

	protected final RandomSource randomSource;	
	protected final E preggoMob;
	
	protected PregnancySystemP1(@Nonnull E preggoMob) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
	}
	
	protected void evaluatePregnancyTimer() {
        if (preggoMob.getPregnancyTimer() >= MinepreggoModConfig.getTotalTicksByPregnancyDay()) {
        	preggoMob.resetPregnancyTimer();
        	preggoMob.incrementDaysPassed();
        	preggoMob.reduceDaysToGiveBirth();
        } else {
        	preggoMob.incrementPregnancyTimer();
        }
	}
	
	protected void evaluateMiscarriage(ServerLevel serverLevel, double x, double y, double z, final int totalTicksOfMiscarriage) {	    
        if (preggoMob.getPregnancyPainTimer() < totalTicksOfMiscarriage) {
        	preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);	        		        	
    		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
    		    if (player.distanceToSqr(preggoMob) <= 512.0) { 
    				serverLevel.sendParticles(player, ParticleTypes.FALLING_DRIPSTONE_LAVA, true, x, (y + preggoMob.getBbHeight() * 0.35), z,
    						1, 0, 1, 0, 0.02);
    		    }
    		}
        
        } else {        	
        	PreggoMobHelper.setItemstackOnOffHand(preggoMob, new ItemStack(Baby.getDeadBabyItem(preggoMob.getDefaultTypeOfBaby()), IBreedable.getOffspringsByMaxPregnancyStage(preggoMob.getLastPregnancyStage())));
        	initPostMiscarriage();	 
        	preggoMob.discard();
        }	
	}
	
	protected void evaluateCravingTimer(final int totalTicksOfCraving) {   				
		if (preggoMob.getCraving() < PregnancySystemConstants.MAX_CRAVING_LEVEL) {
	        if (preggoMob.getCravingTimer() >= totalTicksOfCraving) {
	        	preggoMob.incrementCraving();
	        	preggoMob.resetCravingTimer();
	        }
	        else {
	        	preggoMob.incrementCravingTimer();
	        }
		}	
	}
	
	protected void evaluateAngry(Level level, double x, double y, double z, final float angerProbability) {
	    if (!preggoMob.isAngry() && this.canBeAngry()) {
	    	preggoMob.setAngry(true);
	    } else {
	        if (!PreggoMobHelper.hasValidTarget(preggoMob) && randomSource.nextFloat() < angerProbability) {
	            final Vec3 center = new Vec3(x, y, z);      
	            var players = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(12), preggoMob::isOwnedBy);
	                  
	            if (!players.isEmpty()) {
	            	var owner = players.get(0);
		            if (!PreggoMobHelper.isPlayerInCreativeOrSpectator(owner)) {
		            	preggoMob.setTarget(owner);
		            } 
	            }
	        }
	    }
	}

	protected void evaluatePregnancyPains() {
		if (preggoMob.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (preggoMob.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS) {
				preggoMob.clearPregnancyPain();
				preggoMob.resetPregnancyPainTimer();
			} else {
				preggoMob.incrementPregnancyPainTimer();
			}
		}
	}
	
	protected void evaluatePregnancySymptoms() {	
		if (preggoMob.getPregnancySymptom() == PregnancySymptom.CRAVING && preggoMob.getCraving() <= PregnancySystemConstants.DESACTIVATE_CRAVING_SYMPTOM) {
			preggoMob.clearPregnancySymptom();
			preggoMob.clearTypeOfCraving();
		}
	}
	
	public void evaluateOnSuccessfulHurt(DamageSource damagesource) {	
		if ((preggoMob.hasEffect(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get()) && randomSource.nextFloat() < 0.9F)
				|| (!damagesource.is(DamageTypes.FALL) && !preggoMob.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && randomSource.nextFloat() < 0.5)) {
			return;
		}
		
		int damage = 0;
		var currentPregnancyHealth = preggoMob.getPregnancyHealth();
		
		if (preggoMob.getHealth() < preggoMob.getMaxHealth() / 2F) {
			damage = preggoMob.getCurrentPregnancyStage().ordinal() + randomSource.nextInt(3);
				
			if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
				damage *= 2;
			}

			currentPregnancyHealth = Math.max(0, currentPregnancyHealth - damage);
			preggoMob.setPregnancyHealth(currentPregnancyHealth);			
		} 
		else if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
			currentPregnancyHealth = Math.max(0, currentPregnancyHealth - 5);
			preggoMob.setPregnancyHealth(currentPregnancyHealth);
		}
		
		if (damage > 0) {		
			if (preggoMob.getPregnancyHealth() < 40) {
				MessageHelper.warningOwnerPossibleMiscarriageEvent(preggoMob);
			}					
			MinepreggoMod.LOGGER.debug("PREGNANCY HEALTH: id={}, class={}, pregnancyHealth={}, damage={}, damageSource={}",
					preggoMob.getId(), preggoMob.getClass().getSimpleName(), currentPregnancyHealth, damage, damagesource);
		}
	}
	
	public boolean isMiscarriageActive() {
	    return preggoMob.getPregnancyPain() == PregnancyPain.MISCARRIAGE;
	}
	
	public boolean canAdvanceNextPregnancyPhase() {
	    return preggoMob.getDaysPassed() >= preggoMob.getDaysByStage();
	}
	
	public boolean canTriggerMiscarriage() {
	    return preggoMob.getPregnancyHealth() <= 0;
	}
	
	public boolean hasPregnancyPain() {
	    return preggoMob.getPregnancyPain() != null;
	}
	
	public boolean hasPregnancySymptom() {
	    return preggoMob.getPregnancySymptom() != null;
	}
	
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY) {
	        preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        preggoMob.resetPregnancyPainTimer();
	        return true;
	    }
	    return false;
	}
	
	protected boolean tryInitPregnancySymptom() {
		if (preggoMob.getCraving() >= PregnancySystemConstants.ACTIVATE_CRAVING_SYMPTOM) {
	    	preggoMob.setPregnancySymptom(PregnancySymptom.CRAVING);
	    	preggoMob.setTypeOfCraving(Craving.getRandomCraving(randomSource));
	    	return true;		
		}
	    return false;
	}
	
	@Nullable
	protected Result evaluateCraving(Level level, Player source) {
		if (preggoMob.getPregnancySymptom() != PregnancySymptom.CRAVING) {
			return null;
		}
		
	    var mainHandItem = source.getMainHandItem().getItem();
	    var currentCraving = preggoMob.getCraving();
	    
	    if (currentCraving > PregnancySystemConstants.DESACTIVATE_CRAVING_SYMPTOM) {    	
	    	if (!preggoMob.isValidCraving(mainHandItem) && !(mainHandItem instanceof IItemCraving)) {
	    		return Result.ANGRY;
	    	}
	    	    	
    		source.getInventory().clearOrCountMatchingItems(p -> mainHandItem == p.getItem(), 1, source.inventoryMenu.getCraftSlots());         
            preggoMob.setCraving(currentCraving - ((IItemCraving)mainHandItem).getGratification()); 
            preggoMob.increaseFullness(2);    

            if (!level.isClientSide) {
            	level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	          
            }
            
            return Result.SUCCESS; 
	    }
	    
	    return null;
	}
	
	
	
	
	public boolean canBeAngry() {
		return preggoMob.getCraving() >= 20 || preggoMob.getFullness() <= 2;
	}
	
	
	public void onServerTick() {
		final var level = preggoMob.level();	
		if (level.isClientSide()) {			
			return;
		}
	
		if (isMiscarriageActive()) {
			if (level instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE);
			}		
			return;
		}
		
		this.evaluatePregnancyTimer();
		if (canAdvanceNextPregnancyPhase()) {
			advanceToNextPregnancyPhase();
			preggoMob.discard();
			return;
		}
		
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP1());
	
		if (!hasPregnancyPain()) {
			tryInitRandomPregnancyPain();	
		}
		else {
			evaluatePregnancyPains();
		}
		
		if (!hasPregnancySymptom()) {
			tryInitPregnancySymptom();
		}
		else {
			evaluatePregnancySymptoms();
		}
			
		evaluateAngry(level, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemConstants.LOW_ANGER_PROBABILITY);		
	}
	
	public InteractionResult onRightClick(Player source) {		
		if (!preggoMob.isOwnedBy(source) || preggoMob.isIncapacitated()) {
			return InteractionResult.FAIL;
		}				
		var level = preggoMob.level();

		Result result = evaluateCraving(level, source);
		
		if (result != null) {			
			if (level instanceof ServerLevel serverLevel) {
				PreggoMobSystem.spawnParticles(preggoMob, serverLevel, result);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		return InteractionResult.PASS;
	}

	protected abstract void advanceToNextPregnancyPhase();
	
	protected abstract void initPostMiscarriage();
}

