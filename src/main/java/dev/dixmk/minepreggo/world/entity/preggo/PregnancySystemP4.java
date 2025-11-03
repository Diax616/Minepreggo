package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public abstract class PregnancySystemP4<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystem & IPregnancyP4> extends PregnancySystemP3<E> {

	protected final PregnancyStage currentPregnancyStage;
	
	protected PregnancySystemP4(@Nonnull E preggoMob) {
		super(preggoMob);
		this.currentPregnancyStage = preggoMob.getCurrentPregnancyStage();
	}
	
	protected Result evaluteBirth(ServerLevel serverLevel, double x, double y, double z, final int totalTicksOfPrebirth, final int totalTicksOfBirth) {	
		if (preggoMob.getPregnancyPain() == PregnancyPain.PREBIRTH) {		
			if (preggoMob.getPregnancyPainTimer() >= totalTicksOfPrebirth) {
				preggoMob.setPregnancyPain(PregnancyPain.BIRTH);
	    		preggoMob.setPregnancyPainTimer(0);    		
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.CHEST);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.MAINHAND);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.OFFHAND);
			}	
			else {
	    		preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);
	    		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
	    		    if (player.distanceToSqr(preggoMob) <= 512.0) { // 32 blocks
	    				serverLevel.sendParticles(player, ParticleTypes.FALLING_DRIPSTONE_WATER, true, x, (y + preggoMob.getBbHeight() * 0.35), z,
	    						1, 0, 1, 0, 0.02);
	    		    }
	    		}
			}
			
			return Result.SUCCESS;
		}
		else if (preggoMob.getPregnancyPain() == PregnancyPain.BIRTH) {
			if (preggoMob.getPregnancyPainTimer() >= totalTicksOfBirth) {
	        	PreggoMobHelper.setItemstackOnOffHand(preggoMob, new ItemStack(Baby.getAliveBabyItem(preggoMob.getTypeOfBaby()), PreggoMobHelper.getNumberOfChildrens(preggoMob.getLastPregnancyStage())));
				this.postBirth();
	        	this.preggoMob.discard();
			}	
			else {
	    		preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);
			}
			
			return Result.SUCCESS;
		}
			
		return Result.NOTHING;
	}
	
	protected final boolean hasToGiveBirth() {
		return preggoMob.getLastPregnancyStage() == currentPregnancyStage;
	}
	
	@Override
	protected final Result evaluatePregnancyStageChange() {
	    if (preggoMob.getDaysPassed() >= preggoMob.getDaysByStage()) {
	    	if (this.hasToGiveBirth()) {		
	    		preggoMob.setPregnancyPain(PregnancyPain.PREBIRTH);	  
	    		preggoMob.setPregnancyPainTimer(0);
	    	}
	    	else {
	    		changePregnancyStage();
	    	}	  	
	    	
	        return Result.SUCCESS;
	    }
	    return Result.NOTHING;
	}
	
	@Override
	protected boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getHorny() >= 20;
	}
	
	
	protected final void evaluatePregnancyPains(final float morningSicknessProbability, final float pregnancyPainProbability, final int totalTicksOfKicking, final int totalTicksOfContraction) {
		final var pregnancyPain = preggoMob.getPregnancyPain();

		if (pregnancyPain == PregnancyPain.NONE) {		
			boolean flag = false;
			if (randomSource.nextFloat() < morningSicknessProbability) {
				preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);	
				flag = true;
			}
			else if (randomSource.nextFloat() < pregnancyPainProbability) {			
				if (preggoMob.getLastPregnancyStage() == currentPregnancyStage) {
					preggoMob.setPregnancyPain(PregnancyPain.CONTRACTION);		
				}
				else {
					preggoMob.setPregnancyPain(PregnancyPain.KICKING);		
				}
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.CHEST);
				flag = true;
			}	
			
			if (flag) {
				MinepreggoMod.LOGGER.debug("PREGNANCY PAIN ACTIVE: id={}, class={}, pregnancyPain={}, maxPregnancyStage={}",
						preggoMob.getId(), preggoMob.getClass().getSimpleName(), pregnancyPain, preggoMob.getLastPregnancyStage());
			}
		} 
		else {	
			final var pregnancyPainTimer = preggoMob.getPregnancyPainTimer();

			if ((pregnancyPain == PregnancyPain.MORNING_SICKNESS && pregnancyPainTimer >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS)
					|| (pregnancyPain == PregnancyPain.KICKING && pregnancyPainTimer >= totalTicksOfKicking)
					|| (pregnancyPain == PregnancyPain.CONTRACTION && pregnancyPainTimer >= totalTicksOfContraction)) {
				preggoMob.setPregnancyPainTimer(0);
				preggoMob.setPregnancyPain(PregnancyPain.NONE);
			}
			else {
				preggoMob.setPregnancyPainTimer(pregnancyPainTimer + 1);
			}
		}
	}
	
	@Override
	protected final void evaluatePregnancySymptoms() {		
		if (preggoMob.getPregnancySymptom() == PregnancySymptom.NONE) {		
			if (preggoMob.getCraving() >= PregnancySystemConstants.ACTIVATE_CRAVING_SYMPTOM) {		
				preggoMob.setPregnancySymptom(PregnancySymptom.CRAVING);	
			}
			else if (preggoMob.getMilking() >= PregnancySystemConstants.ACTIVATE_MILKING_SYMPTOM) {
				preggoMob.setPregnancySymptom(PregnancySymptom.MILKING);
			}
			else if (preggoMob.getBellyRubs() >= PregnancySystemConstants.ACTIVATE_BELLY_RUBS_SYMPTOM) {
				preggoMob.setPregnancySymptom(PregnancySymptom.BELLY_RUBS);
			}
			else if (preggoMob.getHorny() >= PregnancySystemConstants.ACTIVATE_HORNY_SYMPTOM) {
				preggoMob.setPregnancySymptom(PregnancySymptom.HORNY);
			}
		}
		else {
			if (preggoMob.getPregnancySymptom() == PregnancySymptom.CRAVING
					&& preggoMob.getTypeOfCraving() == Craving.NONE) {
				preggoMob.setTypeOfCraving(CravingFactory.getRandomCraving(randomSource));
			}
		}
	}
	
	protected void evaluateHornyTimer(final int totalTicksOfHorny) {
		if (preggoMob.getHorny() < 20) {
	        if (preggoMob.getHornyTimer() >= totalTicksOfHorny) {
	        	preggoMob.setHorny(preggoMob.getHorny() + 1);
	        	preggoMob.setHornyTimer(0);
	        }
	        else {
	        	preggoMob.setHornyTimer(preggoMob.getHornyTimer() + 1);
	        }
		}	
	}

	@Override
	public void evaluateOnTick() {
		
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {
			return;
		}
		
		final var x =  preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
		
		if (level instanceof ServerLevel serverLevel
				&& this.evaluteBirth(serverLevel, x, y, z,
				PregnancySystemConstants.TOTAL_TICKS_PREBIRTH_P4,
				PregnancySystemConstants.TOTAL_TICKS_BIRTH_P4) == Result.SUCCESS) {
			return;
		}
		
		if (this.evaluatePregnancyStageChange() == Result.SUCCESS) {
			return;
		}
		
		if (level instanceof ServerLevel serverLevel
				&& this.evaluateMiscarriage(serverLevel, x, y, z, PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) == Result.SUCCESS) {
			return; 
		}
		
		this.evaluatePregnancyTimer();
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP4());
		this.evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP4());
		this.evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP4());
		this.evaluateHornyTimer(MinepreggoModConfig.getTotalTicksOfHornyP4());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.HIGH_ANGER_PROBABILITY);		
		this.evaluatePregnancySymptoms();
		this.evaluatePregnancyPains(
				PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY,
				PregnancySystemConstants.MEDIUM_PREGNANCY_PAIN_PROBABILITY,
				PregnancySystemConstants.TOTAL_TICKS_KICKING_P4,
				PregnancySystemConstants.TOTAL_TICKS_CONTRACTION_P4);
	}
	
	protected abstract void postBirth();
	
}
