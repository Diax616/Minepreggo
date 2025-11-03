package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.item.ICraving;
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
	E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP1> {

	protected final RandomSource randomSource;	
	protected final E preggoMob;
	
	protected PregnancySystemP1(@Nonnull E preggoMob) {
		this.preggoMob = preggoMob;	
		this.randomSource = preggoMob.getRandom();
	}
	
	protected Result evaluatePregnancyStageChange() {
	    if (preggoMob.getDaysPassed() >= preggoMob.getDaysByStage()) {
	    	this.changePregnancyStage();
	    	
	    	MinepreggoMod.LOGGER.debug("PREGNANCY CHANGE STAGE: id={}, class={}, currentPregnancyStage={}",
	    		 	preggoMob.getId(), preggoMob.getClass().getSimpleName(), preggoMob.getCurrentPregnancyStage());
	        	    	    	
	    	this.preggoMob.discard();
	    	return Result.SUCCESS;
	    }
	    return Result.NOTHING;
	}
	
	protected void evaluatePregnancyTimer() {
        if (preggoMob.getPregnancyTimer() >= MinepreggoModConfig.getTotalTicksByDay()) {
        	preggoMob.setPregnancyTimer(0);
        	preggoMob.setDaysPassed(preggoMob.getDaysPassed() + 1);
        	preggoMob.setDaysToGiveBirth(preggoMob.getDaysToGiveBirth() - 1);
        } else {
        	preggoMob.setPregnancyTimer(preggoMob.getPregnancyTimer() + 1);
        }
	}
	
	protected Result evaluateMiscarriage(ServerLevel serverLevel, double x, double y, double z, final int totalTicksOfMiscarriage) {	    
	    if (preggoMob.getPregnancyPain() == PregnancyPain.MISCARRIAGE) {
	        if (preggoMob.getPregnancyPainTimer() < totalTicksOfMiscarriage) {
	        	preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);	        		        	
	    		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
	    		    if (player.distanceToSqr(preggoMob) <= 512.0) { 
	    				serverLevel.sendParticles(player, ParticleTypes.FALLING_DRIPSTONE_LAVA, true, x, (y + preggoMob.getBbHeight() * 0.35), z,
	    						1, 0, 1, 0, 0.02);
	    		    }
	    		}
	        
	        } else {        	
	        	PreggoMobHelper.setItemstackOnOffHand(preggoMob, new ItemStack(Baby.getDeadBabyItem(preggoMob.getTypeOfBaby()), PreggoMobHelper.getNumberOfChildrens(preggoMob.getLastPregnancyStage())));
	        	this.postMiscarriage();	 
	        	this.preggoMob.discard();
	        	return Result.SUCCESS;
	        }
	        return Result.PROCESS; 
	    }
	    
	    return Result.NOTHING;
	}
	
	protected void evaluateCravingTimer(final int totalTicksOfCraving) {   				
		if (preggoMob.getCraving() < 20) {
	        if (preggoMob.getCravingTimer() >= totalTicksOfCraving) {
	        	preggoMob.setCraving(preggoMob.getCraving() + 1);
	        	preggoMob.setCravingTimer(0);
	        }
	        else {
	        	preggoMob.setCravingTimer(preggoMob.getCravingTimer() + 1);
	        }
		}	
	}
	
	protected void evaluateAngry(Level level, double x, double y, double z, final float angerProbability) {

	    if (!preggoMob.isAngry() && this.canBeAngry()) {
	    	preggoMob.setAngry(true);
	    } else {
	        if (!PreggoMobHelper.hasValidTarget(preggoMob) && randomSource.nextFloat() < angerProbability) {
	            final Vec3 center = new Vec3(x, y, z);      
	            List<Player> owner = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(12), preggoMob::isOwnedBy)
	                    .stream()
	                    .sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(center)))
	                    .toList();	           
	            if (!owner.isEmpty() && !PreggoMobHelper.isPlayerInCreativeOrSpectator(owner.get(0))) {
	            	preggoMob.setTarget(owner.get(0));
	            }
	        }
	    }
	}

	protected void evaluatePregnancyPains() {
		if (preggoMob.getPregnancyPain() == PregnancyPain.NONE) {		
			if (randomSource.nextFloat() < PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY) {
				preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);		
			}		
		} 
		else {
			if (preggoMob.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS) {
				preggoMob.setPregnancyPainTimer(0);
				preggoMob.setPregnancyPain(PregnancyPain.NONE);
			}
			else {
				preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);
			}
		}
	}
	
	protected void evaluatePregnancySymptoms() {		
		if (preggoMob.getPregnancySymptom() == PregnancySymptom.NONE) {
			if (preggoMob.getCraving() >= PregnancySystemConstants.ACTIVATE_CRAVING_SYMPTOM) {		
				preggoMob.setPregnancySymptom(PregnancySymptom.CRAVING);	
			}
		}
		else {
			if (preggoMob.getTypeOfCraving() == Craving.NONE) {
				preggoMob.setTypeOfCraving(CravingFactory.getRandomCraving(randomSource));
			}
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
	
	public void evaluateOnTick() {		
		
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {			
			return;
		}
		
		if (this.evaluatePregnancyStageChange() == Result.SUCCESS) {
			return;
		}
		
		final var x =  preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
				
		if (level instanceof ServerLevel serverLevel
				&& this.evaluateMiscarriage(serverLevel, x, y, z, PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) == Result.SUCCESS) {
			return; 
		}
		
		this.evaluatePregnancyTimer();	
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP1());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.LOW_ANGER_PROBABILITY);	
		this.evaluatePregnancySymptoms();
		this.evaluatePregnancyPains();
	}
	
	protected boolean canBeAngry() {
		return preggoMob.getCraving() >= 20 || preggoMob.getHungry() <= 2;
	}

	public InteractionResult evaluateRightClick(Player source) {	
		final var level = preggoMob.level();
		
		if (!preggoMob.isOwnedBy(source) || level.isClientSide() || !(level instanceof ServerLevel serverLevel)) {
			return InteractionResult.PASS;
		}			
		
		Result result;
		
		if ((result = evaluateCraving(serverLevel, source)) != Result.NOTHING) {
			PreggoMobSystem.spawnParticles(preggoMob, serverLevel, result);
		}
		
		return PreggoMobSystem.onRightClickResult(result);	
	}
		
	protected Result evaluateCraving(Level level, Player source) {
		if (preggoMob.getPregnancySymptom() != PregnancySymptom.CRAVING) {
			return Result.NOTHING;
		}
		
	    var mainHandItem = source.getMainHandItem().getItem();
	    var currentCraving = preggoMob.getCraving();
	    
	    if (currentCraving > PregnancySystemConstants.DESACTIVATE_CRAVING_SYMPTOM) {    	

	    	if (!(mainHandItem instanceof ICraving craving)) {
	    		return Result.ANGRY;
	    	}
	    	    	
	    	if (preggoMob.isValidCraving(preggoMob.getTypeOfCraving(), mainHandItem)) {
	           
	    		source.getInventory().clearOrCountMatchingItems(p -> mainHandItem == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
	            currentCraving = Math.max(0, currentCraving - craving.getGratification());
                preggoMob.setCraving(currentCraving);
	            preggoMob.setHungry(Math.min(preggoMob.getHungry() + 2, 20));
                
            	level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	
                
	            if (currentCraving <= PregnancySystemConstants.DESACTIVATE_CRAVING_SYMPTOM) {
	    	    	preggoMob.setPregnancySymptom(PregnancySymptom.NONE);
	            }
	    		     
	            return Result.SUCCESS; 
	    	} else {
	    		return Result.FAIL;
	    	}
	    }
	    
	    return Result.NOTHING;
	}
	
	protected abstract void changePregnancyStage();
	
	protected abstract void postMiscarriage();
}

